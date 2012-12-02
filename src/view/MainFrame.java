package view;

import javax.media.opengl.awt.GLJPanel;

/**
 *
 */
public final class MainFrame extends javax.swing.JFrame {
    
    // Global state of scene.
    private GlobalState gs;

    /**
     * Creates new form MainFrame.
     */
    public MainFrame(GlobalState globalState) {
        this.gs = globalState;
        
        initComponents();
        updateElements();
    }
    
    /**
     * Update UI elements to match global state.
     */
    public void updateElements() {
        axesCombo.setSelectedIndex(gs.showAxes ? 0 : 1);
        stickCombo.setSelectedIndex(gs.showStick ? 1 : 0);
        trackCombo.setSelectedIndex(gs.trackNr);
        perspectiveCombo.setSelectedIndex(gs.persp ? 1 : 0);
        cameraCombo.setSelectedIndex(gs.camMode);
        lightCombo.setSelectedIndex(gs.lightCamera ? 1 : 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glPanel = new GLJPanel();
        toolBar = new javax.swing.JToolBar();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        axesCombo = new javax.swing.JComboBox();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        stickCombo = new javax.swing.JComboBox();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        trackCombo = new javax.swing.JComboBox();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        perspectiveCombo = new javax.swing.JComboBox();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        cameraCombo = new javax.swing.JComboBox();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        lightCombo = new javax.swing.JComboBox();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        resetButton = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");

        glPanel.setPreferredSize(new java.awt.Dimension(800, 800));
        glPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                glPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout glPanelLayout = new javax.swing.GroupLayout(glPanel);
        glPanel.setLayout(glPanelLayout);
        glPanelLayout.setHorizontalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1093, Short.MAX_VALUE)
        );
        glPanelLayout.setVerticalGroup(
            glPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(glPanel, java.awt.BorderLayout.CENTER);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.add(filler6);

        axesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Visible axes", "Invisible axes" }));
        axesCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                axesComboItemStateChanged(evt);
            }
        });
        toolBar.add(axesCombo);
        toolBar.add(filler1);

        stickCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Full robots", "Stick figures" }));
        stickCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stickComboItemStateChanged(evt);
            }
        });
        toolBar.add(stickCombo);
        toolBar.add(filler2);

        trackCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "O track", "D track", "L track", "Custom track" }));
        trackCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                trackComboItemStateChanged(evt);
            }
        });
        toolBar.add(trackCombo);
        toolBar.add(filler3);

        perspectiveCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Isometric projection", "Perspective projection" }));
        perspectiveCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                perspectiveComboItemStateChanged(evt);
            }
        });
        toolBar.add(perspectiveCombo);
        toolBar.add(filler4);

        cameraCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Overview mode", "Helicopter mode", "Motorcycle mode", "First person mode", "Auto mode" }));
        cameraCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cameraComboItemStateChanged(evt);
            }
        });
        toolBar.add(cameraCombo);
        toolBar.add(filler5);

        lightCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Static light", "Attached light" }));
        lightCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lightComboItemStateChanged(evt);
            }
        });
        toolBar.add(lightCombo);
        toolBar.add(filler8);

        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        toolBar.add(resetButton);
        toolBar.add(filler7);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void axesComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_axesComboItemStateChanged
        gs.showAxes = axesCombo.getSelectedIndex() == 0;
    }//GEN-LAST:event_axesComboItemStateChanged

    private void stickComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stickComboItemStateChanged
        gs.showStick = stickCombo.getSelectedIndex() == 1;
    }//GEN-LAST:event_stickComboItemStateChanged

    private void trackComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_trackComboItemStateChanged
        gs.trackNr = trackCombo.getSelectedIndex();
    }//GEN-LAST:event_trackComboItemStateChanged

    private void perspectiveComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_perspectiveComboItemStateChanged
        gs.persp = perspectiveCombo.getSelectedIndex() == 1;
    }//GEN-LAST:event_perspectiveComboItemStateChanged

    private void cameraComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cameraComboItemStateChanged
        gs.camMode = cameraCombo.getSelectedIndex();
    }//GEN-LAST:event_cameraComboItemStateChanged

    private void lightComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lightComboItemStateChanged
        gs.lightCamera = lightCombo.getSelectedIndex() == 1;
    }//GEN-LAST:event_lightComboItemStateChanged

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        gs.reset();
        updateElements();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void glPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_glPanelMouseClicked
        glPanel.requestFocusInWindow();
    }//GEN-LAST:event_glPanelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox axesCombo;
    private javax.swing.JComboBox cameraCombo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    protected javax.swing.JPanel glPanel;
    private javax.swing.JComboBox lightCombo;
    private javax.swing.JComboBox perspectiveCombo;
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox stickCombo;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JComboBox trackCombo;
    // End of variables declaration//GEN-END:variables
}
