/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protótipos_de_tela;

/**
 *
 * @author migue_000
 */
public class TelaSerie extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public TelaSerie() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton2 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton3 = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jToggleButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jToggleButton2.setText("Trailer");
        getContentPane().add(jToggleButton2);
        jToggleButton2.setBounds(10, 330, 70, 30);

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextPane1.setText("The Doctor is an alien Time Lord from the planet Gallifrey who travels through all of time and space in his TARDIS. He has a long list of friends and companions who have shared journeys with him. Instead of dying, the Doctor is able to “regenerate” into a new body, taking on a new personality with each regeneration. Twelve actors, plus John Hurt, have played The Doctor thus far.");
        jTextPane1.setOpaque(false);
        jScrollPane1.setViewportView(jTextPane1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 550, 250, 80);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 310, 200, 60);

        jButton3.setText("< Voltar");
        getContentPane().add(jButton3);
        jButton3.setBounds(10, 10, 73, 23);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("987 episódios");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(10, 440, 300, 70);

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Temporada" }));
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(10, 510, 190, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Acompanhando", "Arquivar", "Remover Série" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(10, 370, 250, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("28 dias 7 horas e 50 min para concluir");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(10, 460, 300, 70);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Ficção Científica - Aventura");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(10, 400, 300, 70);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("1963 - Presente");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 420, 300, 70);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Doctor_Who_Series_9.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 10, 250, 350);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Doctor Who");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 380, 300, 70);

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jToggleButton1.setText("OK");
        getContentPane().add(jToggleButton1);
        jToggleButton1.setBounds(210, 510, 50, 30);

        jButton2.setText("<");
        getContentPane().add(jButton2);
        jButton2.setBounds(280, 290, 41, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grey.png"))); // NOI18N
        jLabel2.setText("Print");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -10, 270, 690);

        jButton1.setText(">");
        getContentPane().add(jButton1);
        jButton1.setBounds(1180, 280, 41, 50);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nomedoepisodio - 43min - 11/11/11");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(540, 330, 390, 29);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 88)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("S10E01");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(560, 240, 350, 80);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Doctor Who");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(580, 170, 300, 70);

        jToggleButton3.setText("MARCAR COMO ASSISTIDO");
        getContentPane().add(jToggleButton3);
        jToggleButton3.setBounds(620, 440, 230, 40);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(630, 370, 200, 60);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Doctor.Who.2005.S09E12.Hell.Bent.720p.WEB-DL.DD5.1.H.264-CtrlHD.mkv_003513762.jpg"))); // NOI18N
        jLabel13.setText("Print");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(260, 0, 980, 640);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSerie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables
}
