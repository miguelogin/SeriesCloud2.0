package Telas;

import Servidor.BuscaWeb;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TelaSerie extends javax.swing.JFrame {

    private File arquivoBackupPesquisa = new File("src/backupPesquisa.txt");
    
    public TelaSerie() throws IOException, ClassNotFoundException {
        initComponents();
        InsereDadosTela();
        arquivoBackupPesquisa.deleteOnExit();
    }

    private void InsereDadosTela() throws IOException, ClassNotFoundException {
        File ImagePoster = new File("src//images//temp//temp_poster.png");
        BufferedImage BufferedImage = ImageIO.read(ImagePoster);
        ImageIcon Poster = new ImageIcon(BufferedImage.getScaledInstance(250, 349, Image.SCALE_SMOOTH));
        jLabelPosterSerie.setIcon(Poster);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneSinopseSerie = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabelNotaSerie = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabelPosterSerie = new javax.swing.JLabel();
        jLabelNomeSerie = new javax.swing.JLabel();
        jLabelCategoriasSerie = new javax.swing.JLabel();
        jLabelAnoSerie = new javax.swing.JLabel();
        jLabelTotalEpisodios = new javax.swing.JLabel();
        jLabelWatchTime = new javax.swing.JLabel();
        jComboBoxTemporada = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelNomeEpisodio = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jToggleButton3 = new javax.swing.JToggleButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jTextPane1.setEditable(false);
        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextPane1.setText("The Doctor is an alien Time Lord from the planet Gallifrey who travels through all of time and space in his TARDIS. He has a long list of friends and companions who have shared journeys with him. Instead of dying, the Doctor is able to “regenerate” into a new body, taking on a new personality with each regeneration. Twelve actors, plus John Hurt, have played The Doctor thus far.");
        jTextPane1.setOpaque(false);
        jScrollPaneSinopseSerie.setViewportView(jTextPane1);

        getContentPane().add(jScrollPaneSinopseSerie);
        jScrollPaneSinopseSerie.setBounds(10, 550, 250, 80);

        jLabelNotaSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNotaSerie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabelNotaSerie);
        jLabelNotaSerie.setBounds(10, 300, 250, 60);

        jButton3.setText("< Voltar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(0, 0, 73, 23);

        jButton4.setText("Acompanhar");
        jButton4.setBorderPainted(false);
        getContentPane().add(jButton4);
        jButton4.setBounds(10, 370, 250, 30);

        jLabelPosterSerie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Doctor_Who_Series_9.png"))); // NOI18N
        getContentPane().add(jLabelPosterSerie);
        jLabelPosterSerie.setBounds(10, 10, 250, 350);

        jLabelNomeSerie.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNomeSerie.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeSerie.setText("Doctor Who");
        getContentPane().add(jLabelNomeSerie);
        jLabelNomeSerie.setBounds(10, 380, 300, 70);

        jLabelCategoriasSerie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategoriasSerie.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCategoriasSerie.setText("Ficção Científica - Aventura");
        getContentPane().add(jLabelCategoriasSerie);
        jLabelCategoriasSerie.setBounds(10, 400, 300, 70);

        jLabelAnoSerie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelAnoSerie.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAnoSerie.setText("1963 - Presente");
        getContentPane().add(jLabelAnoSerie);
        jLabelAnoSerie.setBounds(10, 420, 300, 70);

        jLabelTotalEpisodios.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTotalEpisodios.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalEpisodios.setText("987 episódios");
        getContentPane().add(jLabelTotalEpisodios);
        jLabelTotalEpisodios.setBounds(10, 440, 300, 70);

        jLabelWatchTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelWatchTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelWatchTime.setText("28 dias 7 horas e 50 min para concluir");
        getContentPane().add(jLabelWatchTime);
        jLabelWatchTime.setBounds(10, 460, 300, 70);

        jComboBoxTemporada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxTemporada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 Temporada" }));
        getContentPane().add(jComboBoxTemporada);
        jComboBoxTemporada.setBounds(10, 510, 250, 30);

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
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("43min - 11/11/11");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(330, 350, 840, 29);

        jLabelNomeEpisodio.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelNomeEpisodio.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeEpisodio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeEpisodio.setText("The Time of The Doctor");
        getContentPane().add(jLabelNomeEpisodio);
        jLabelNomeEpisodio.setBounds(330, 270, 840, 80);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("S10E01");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(330, 200, 840, 70);

        jToggleButton3.setText("MARCAR COMO ASSISTIDO");
        getContentPane().add(jToggleButton3);
        jToggleButton3.setBounds(630, 460, 230, 40);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(330, 390, 840, 60);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Doctor.Who.2005.S09E12.Hell.Bent.720p.WEB-DL.DD5.1.H.264-CtrlHD.mkv_003513762.jpg"))); // NOI18N
        jLabel13.setText("Print");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(260, 0, 980, 640);

        setSize(new java.awt.Dimension(1252, 677));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try {
            this.dispose();
            BuscaNovaSerie BuscaNovaSerie = new BuscaNovaSerie();
            BuscaNovaSerie.setVisible(true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(TelaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaSerie().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TelaSerie.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaSerie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBoxTemporada;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAnoSerie;
    private javax.swing.JLabel jLabelCategoriasSerie;
    private javax.swing.JLabel jLabelNomeEpisodio;
    private javax.swing.JLabel jLabelNomeSerie;
    private javax.swing.JLabel jLabelNotaSerie;
    private javax.swing.JLabel jLabelPosterSerie;
    private javax.swing.JLabel jLabelTotalEpisodios;
    private javax.swing.JLabel jLabelWatchTime;
    private javax.swing.JScrollPane jScrollPaneSinopseSerie;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JToggleButton jToggleButton3;
    // End of variables declaration//GEN-END:variables

    private BuscaNovaSerie BuscaNovaSerie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
