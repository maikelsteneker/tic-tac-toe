package view;

import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.*;
import javax.media.opengl.GL;
import static javax.media.opengl.GL2.*;
import static java.lang.Math.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL2;
import javax.media.opengl.GLDrawable;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.*;

public class GUI extends Base {

    double fov = 90;
    Game game;
    ClickListener clickListener;

    /**
     * Called upon the start of the application. Primarily used to configure
     * OpenGL.
     */
    @Override
    public void initialize() {
        GLJPanel glPanel = (GLJPanel) frame.glPanel;
        clickListener = new ClickListener();
        glPanel.addMouseListener(clickListener);

        // Enable blending.
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Enable anti-aliasing.
        gl.glEnable(GL_LINE_SMOOTH);
        //gl.glEnable(GL_POLYGON_SMOOTH);
        gl.glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        //gl.glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);

        // Enable depth testing.
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);

        // Enable textures. 
        gl.glEnable(GL_TEXTURE_2D);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

        // Create game object
        game = new Game();
    }

    /**
     * Configures the viewing transform.
     */
    @Override
    public void setView() {
        // Select part of window.
        gl.glViewport(0, 0, gs.w, gs.h);

        // Set projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        if (gs.persp) {
            glu.gluPerspective(fov, gs.w / gs.h, 0.1, 1000);
        } else {
            float height = gs.vWidth / (gs.w / gs.h);
            gl.glOrtho(-0.5 * gs.vWidth, 0.5 * gs.vWidth, -0.5 * height, 0.5 * height, 0.1, 1000);
        }

        // Set camera.
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();


        Vector dir = new Vector(cos(gs.phi) * cos(gs.theta),
                sin(gs.phi) * cos(gs.theta),
                sin(gs.theta));

        Vector eye = gs.cnt.add(dir.scale(gs.vDist));

        glu.gluLookAt(eye.x(), eye.y(), eye.z(), // eye point
                gs.cnt.x(), gs.cnt.y(), gs.cnt.z(), // center point
                0.0, 0.0, 1.0);   // up axis
    }

    /**
     * Draws the entire scene.
     */
    @Override
    public void drawScene() {
        if (clickListener.x != -1) {
            int x = clickListener.x;
            int y = clickListener.y;

            clickListener.x = -1;
            clickListener.y = -1;
            handleMouseClick(x, y);
        }

        gl.glMatrixMode(GL_MODELVIEW);

        // Enable lighting
        gl.glEnable(GL_LIGHTING); //enable lighting (lighting influences color)
        gl.glEnable(GL_LIGHT0); //enable light source 0
        //gl.glLoadIdentity();
        float[] location = {Game.GRIDSIZE / 2, Game.GRIDSIZE / 2, 10, 1};
        gl.glLightfv(GL_LIGHT0, GL_POSITION, location, 0); //set location of ls0
        gl.glEnable(GL_COLOR_MATERIAL); //enable materials (material influences color)
        gl.glPushMatrix();
        gl.glTranslatef(location[0], location[1], location[2]);
        gl.glPopMatrix();

        draw();

        if (game.hasFinished()) {
            gl.glMatrixMode(GL_PROJECTION);
            gl.glPushMatrix();
            gl.glLoadIdentity();
            glu.gluOrtho2D(0.0, gs.w, 0.0, gs.h);

            gl.glMatrixMode(GL_MODELVIEW);
            gl.glPushMatrix();
            gl.glLoadIdentity();
            String s = "Game has ended."
                    + (game.hasWon(Cell.X) ? " X has won." : "")
                    + (game.hasWon(Cell.O) ? " O has won." : "");
            for (int i = 0; i < s.length(); i++) {
                glut.glutBitmapCharacter(GLUT.BITMAP_TIMES_ROMAN_24, s.charAt(i));
            }
            System.out.println(s);

            gl.glMatrixMode(GL_MODELVIEW);
            gl.glPopMatrix();

            gl.glMatrixMode(GL_PROJECTION);
            gl.glPopMatrix();
        }
    }

    private void draw() {
        // Background color.
        gl.glClearColor(1f, 1f, 1f, 0f);

        // Clear background.
        gl.glClear(GL_COLOR_BUFFER_BIT);

        // Clear depth buffer.
        gl.glClear(GL_DEPTH_BUFFER_BIT);

        // Set color to black.
        gl.glColor3f(0f, 0f, 0f);

        gl.glColor3f(1, 1, 1);

        gl.glPushMatrix();
        for (int i = 0; i < Game.GRIDSIZE; i++) {
            for (int j = 0; j < Game.GRIDSIZE; j++) {
                gl.glLoadName(i * Game.GRIDSIZE + j + 1);
                if (game.getCell(i, j) == null) {
                    empty.bind(gl);
                } else {
                    switch (game.getCell(i, j)) {
                        case X:
                            X.bind(gl);
                            break;
                        case O:
                            O.bind(gl);
                    }
                }

                gl.glBegin(GL_QUADS);
                gl.glTexCoord2d(0, 0);
                gl.glVertex3d(0, 0, 0);
                gl.glTexCoord2d(1, 0);
                gl.glVertex3d(1, 0, 0);
                gl.glTexCoord2d(1, 1);
                gl.glVertex3d(1, 1, 0);
                gl.glTexCoord2d(0, 1);
                gl.glVertex3d(0, 1, 0);
                gl.glEnd();

                gl.glTranslatef(1, 0, 0);
            }
            gl.glTranslatef(-Game.GRIDSIZE, 1, 0);
        }
        gl.glPopMatrix();
    }

    public void drawArrow() {
        gl.glPushMatrix();

        gl.glTranslatef(0f, 0, 0.5f);
        gl.glScalef(0.01f, 0.01f, 1f);
        glut.glutSolidCube(0.9f);

        gl.glPopMatrix();
        gl.glPushMatrix();

        gl.glTranslatef(0f, 0f, 1f);
        glut.glutSolidCone(0.05, 0.1, 15, 2);

        gl.glPopMatrix();
    }

    public void drawAxisFrame() {
        if (gs.showAxes) {
            gl.glColor3f(1.0f, 1.0f, 0);
            glut.glutSolidSphere(0.10f, 20, 20);

            gl.glPushMatrix();
            gl.glRotatef(90, 0, 1, 0);
            gl.glColor3f(1.0f, 0, 0);
            drawArrow();
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glRotatef(-90, 1, 0, 0);
            gl.glColor3f(0, 1.0f, 0);
            drawArrow();
            gl.glPopMatrix();

            gl.glPushMatrix();
            gl.glColor3f(0, 0, 1.0f);
            drawArrow();
            gl.glPopMatrix();
        }
    }

    /*
     * private void handleMouseClick(int x, int y) { this.clicki = 3;
     * this.clickj = 3; }
     */
    private void handleMouseClick(int x, int y) {
        if (game.hasFinished()) {
            game.reset();
        } else {
            y = gs.h - y;
            int buffsize = 64;
            IntBuffer buff = IntBuffer.allocate(buffsize);
            gl.glSelectBuffer(buffsize, buff);
            IntBuffer view = IntBuffer.allocate(4);
            gl.glGetIntegerv(GL_VIEWPORT, view);
            gl.glRenderMode(GL_SELECT);
            gl.glInitNames();
            gl.glPushName(0);
            gl.glMatrixMode(GL_PROJECTION);

            gl.glPushMatrix();
            gl.glLoadIdentity();
            glu.gluPickMatrix(x, y, 1.0, 1.0, view);
            if (gs.persp) {
                glu.gluPerspective(fov, gs.w / gs.h, 0.1, 1000);
            } else {
                float height = gs.vWidth / (gs.w / gs.h);
                gl.glOrtho(-0.5 * gs.vWidth, 0.5 * gs.vWidth, -0.5 * height, 0.5 * height, 0.1, 1000);
            }
            gl.glMatrixMode(GL_MODELVIEW);
            gl.glPushMatrix();
            draw();
            gl.glPopMatrix();
            gl.glMatrixMode(GL_PROJECTION);
            gl.glPopMatrix();

            int hits = gl.glRenderMode(GL_RENDER);
            int clickcode = 0;

            //get last element (N.B. usually i=3 is the actual correct value)
            for (int i = buffsize - 1; i >= 0; i--) {
                if (buff.get(i) != 0) {
                    clickcode = buff.get(i);
                    break;
                }
            }
            int i = hits > 0 ? (clickcode - 1) / Game.GRIDSIZE : -1;
            int j = hits > 0 ? (clickcode - 1) % Game.GRIDSIZE : -1;
            if (i >= 0 && j >= 0) {
                game.doMove(i, j);
            }
            gl.glMatrixMode(GL_MODELVIEW);
        }
    }

    private final class ClickListener implements MouseListener {

        int x = -1, y = -1;

        @Override
        public void mouseClicked(MouseEvent e) {
            x = e.getX();
            y = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String args[]) {
        new GUI();
    }
}
