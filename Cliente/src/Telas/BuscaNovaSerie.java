package Telas;

import static java.awt.event.KeyEvent.*;
import Servidor.BuscaWeb;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class BuscaNovaSerie extends javax.swing.JFrame {

    private int PaginaBusca;
    private String TermoBusca;
    private boolean Local = false;
    private File arquivoBackupPesquisa = new File("src/backupPesquisa.txt");

    public boolean isLocal() {
        return Local;
    }

    public BuscaNovaSerie() throws IOException, ClassNotFoundException {
        initComponents();
        jTableResultadoWeb.setEnabled(false);
        jTableResultadoWeb.setVisible(false);
        if (arquivoBackupPesquisa.exists()) {
            FileReader leitor = new FileReader(arquivoBackupPesquisa);
            BufferedReader leitor_bufffer = new BufferedReader(leitor);
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            String linha = null;
            while ((linha = leitor_bufffer.readLine()) != null) {
                String colunas[] = linha.split("#"); //lê a linha contendo a palavra e a dica, sabendo q as mesmas sao separadas pelo #
                jTextFieldCampoBusca.setText(colunas[0]);
                PaginaBusca = Integer.parseInt(colunas[1]);
            }
            leitor_bufffer.close();
            try {
                PesquisarSerieWeb();
            } catch (InterruptedException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
            arquivoBackupPesquisa.deleteOnExit();
        }
    }

    public void PesquisarSerieWeb() throws IOException, ClassNotFoundException, InterruptedException {
        arquivoBackupPesquisa.delete();
        if (jTextFieldCampoBusca.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "O campo de busca está vazio!", "Busca nula", JOptionPane.ERROR_MESSAGE);
        } else {
            BuscaWeb BuscaWeb = new BuscaWeb(jTextFieldCampoBusca.getText(), PaginaBusca);
            DefaultTableModel TabelaResultadoWeb = (DefaultTableModel) jTableResultadoWeb.getModel();
            if (BuscaWeb.isSemResultados()) {
                int TotalLinhas = TabelaResultadoWeb.getRowCount();
                for (int x = TotalLinhas - 1; x >= 0; x--) {
                    TabelaResultadoWeb.removeRow(x);
                }
                JOptionPane.showMessageDialog(this, "Não encontramos nenhuma série envolvendo \"" + jTextFieldCampoBusca.getText() + "\".\nRevise ou refine a sua busca.", "Série não econtrada", JOptionPane.ERROR_MESSAGE);
            } else {
                jTableResultadoWeb.setVisible(true);
                jTableResultadoWeb.setEnabled(true);
                TableColumnModel columnModel = jTableResultadoWeb.getColumnModel();
                JTableRendererImage RendererImage = new JTableRendererImage();
                JTableRendererImage RendererBorder = new JTableRendererImage();
                TabelaResultadoWeb.setRowCount(0);
                jTableResultadoWeb.setRowHeight(150);
                jTableResultadoWeb.setRowMargin(5);
                jTableResultadoWeb.setFont(new Font("Tahoma", Font.PLAIN, 20));
                Color color = UIManager.getColor("Table.gridColor");
                for (int x = 1; x <= BuscaWeb.getNumeroTotalResultados(); x++) { //limitação de 10 resultados
                    URL url;
                    ImageIcon PosterSerie;
                    if (BuscaWeb.getPosterSerie()[x].equals("N/A")) {
                        File DefaultImage = new File("src//images//default_poster.png");
                        BufferedImage BufferedImage = ImageIO.read(DefaultImage);
                        PosterSerie = new ImageIcon(BufferedImage.getScaledInstance(113, 150, Image.SCALE_SMOOTH));
                    } else {
                        url = new URL(BuscaWeb.getPosterSerie()[x]);
                        BufferedImage BufferedImage = ImageIO.read(url);
                        PosterSerie = new ImageIcon(BufferedImage.getScaledInstance(113, 150, Image.SCALE_SMOOTH));
                    }
                    jLabelProfilePicture.setIcon(PosterSerie);
                    //coloca os dados da série na tabela
                    TabelaResultadoWeb.addRow(new Object[]{PosterSerie, BuscaWeb.getNomeBusca()[x], BuscaWeb.getAnoBusca()[x]});
                }
                if (BuscaWeb.getNumeroTotalResultados() == 10) {
                    File MaisResultadosImage = new File("src//images//mais.jpg");
                    BufferedImage BufferedImage = ImageIO.read(MaisResultadosImage);
                    ImageIcon MaisResultados = new ImageIcon(BufferedImage.getScaledInstance(113, 150, Image.SCALE_SMOOTH));
                    TabelaResultadoWeb.addRow(new Object[]{MaisResultados, "CARREGAR MAIS RESULTADOS", ""});
                }
                columnModel.getColumn(0).setCellRenderer(RendererImage);
                columnModel.getColumn(1).setCellRenderer(RendererBorder);
                columnModel.getColumn(2).setCellRenderer(RendererBorder);
                jTableResultadoWeb.requestFocus(true);
                jTableResultadoWeb.setRowSelectionInterval(0, 0);
                //sobreescreve os listeners padrões por novos /////////////////////////////////////////AÇÕES NA TABELAS/////////
                jTableResultadoWeb.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        //MAIS RESULTADOS
                        if (jTableResultadoWeb.getSelectedRow() == BuscaWeb.getNumeroTotalResultados()) { /////////// CLICA EM MAIS RESULTADOS
                            try {
                                if (BuscaWeb.getNumeroTotalResultados() == 10) {
                                    PaginaBusca++;
                                    PesquisarSerieWeb();
                                    jTableResultadoWeb.getSelectionModel().setSelectionInterval(0, 0);
                                    jTableResultadoWeb.scrollRectToVisible(new Rectangle(jTableResultadoWeb.getCellRect(0, 0, true)));
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else { //////////////////////////////////////////////////////////////////////////////// CLICA EM ALGUMA SÉRIE
                            try {
                                BuscaWeb.BuscaSerieEspecifica((BuscaWeb.getNomeBusca()[jTableResultadoWeb.getSelectedRow() + 1]), BuscaWeb.getAnoInicio()[jTableResultadoWeb.getSelectedRow() + 1], jTableResultadoWeb.getSelectedRowCount() + 1);
                                //POR ALGUM MOTIVO O BUFFERDIMAGE E O IMAGEICON N PASSAVAM VALORES PARA A OUTRA TELA
                                //MESMO CHECANDO AQUI Q ELES N ESTAVAM NULOS. DECIDI ENTÃO BAIXAR A IMAGEM PARA UTILIZAR NA OUTRA TELA
                                BaixarImagem(BuscaWeb.getPosterSerie()[jTableResultadoWeb.getSelectedRow() + 1], "src//images//temp//temp_poster.png");
                                TelaSerie TelaSerie = new TelaSerie();
                                BuscaNovaSerieDispose();
                                TelaSerie.setVisible(true);
                                Local = true;
                                try {
                                    FileWriter arquivoWriter = new FileWriter(arquivoBackupPesquisa, true);
                                    PrintWriter escrever = new PrintWriter(arquivoWriter);
                                    escrever.println(jTextFieldCampoBusca.getText() + "#" + PaginaBusca);
                                    arquivoWriter.close();
                                    BufferedReader leitor_buffer = new BufferedReader(new FileReader("src/backupPesquisa.txt"));
                                    while (leitor_buffer.ready()) {
                                        String linha = leitor_buffer.readLine(); // lê até a última linha
                                    }
                                    leitor_buffer.close();
                                } catch (Exception ex) {
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                });
                jTableResultadoWeb.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyPressed(java.awt.event.KeyEvent e) {
                        if (e.getKeyCode() == VK_ENTER) {
                            if (jTableResultadoWeb.getSelectedRow() == BuscaWeb.getNumeroTotalResultados()) {//////// ENTER EM MAIS RESULTADOS
                                try {
                                    if (BuscaWeb.getNumeroTotalResultados() == 10) {
                                        PaginaBusca++;
                                        PesquisarSerieWeb();
                                        jTableResultadoWeb.getSelectionModel().setSelectionInterval(0, 0);
                                        jTableResultadoWeb.scrollRectToVisible(new Rectangle(jTableResultadoWeb.getCellRect(0, 0, true)));
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {
                                    //////////////////////////////////////////////////////////////////////////////// ENTER EM ALGUMA SÉRIE
                                    BuscaWeb.BuscaSerieEspecifica((BuscaWeb.getNomeBusca()[jTableResultadoWeb.getSelectedRow() + 1]), BuscaWeb.getAnoInicio()[jTableResultadoWeb.getSelectedRow() + 1], jTableResultadoWeb.getSelectedRowCount() + 1);
                                    //POR ALGUM MOTIVO O BUFFERDIMAGE E O IMAGEICON N PASSAVAM VALORES PARA A OUTRA TELA
                                    //MESMO CHECANDO AQUI Q ELES N ESTAVAM NULOS. DECIDI ENTÃO BAIXAR A IMAGEM PARA UTILIZAR NA OUTRA TELA
                                    BaixarImagem(BuscaWeb.getPosterSerie()[jTableResultadoWeb.getSelectedRow() + 1], "src//images//temp//temp_poster.png");
                                    TelaSerie TelaSerie = new TelaSerie();
                                    BuscaNovaSerieDispose();
                                    TelaSerie.setVisible(true);
                                    Local = true;
                                    try {
                                        FileWriter arquivoWriter = new FileWriter(arquivoBackupPesquisa, true);
                                        PrintWriter escrever = new PrintWriter(arquivoWriter);
                                        escrever.println(jTextFieldCampoBusca.getText() + "#" + PaginaBusca);
                                        arquivoWriter.close();
                                        BufferedReader leitor_buffer = new BufferedReader(new FileReader("src/backupPesquisa.txt"));
                                        while (leitor_buffer.ready()) {
                                            String linha = leitor_buffer.readLine(); // lê até a última linha
                                        }
                                        leitor_buffer.close();
                                    } catch (Exception ex) {
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                        }
                    }
                });
            }
        }
    }

    public void BuscaNovaSerieDispose() {
        this.dispose();
    }

    public static void BaixarImagem(String imageUrl, String destinationFile) throws IOException {
        System.out.println(imageUrl);
        System.out.println(destinationFile);
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        is.close();
        os.close();
    }

    public class JTableRendererImage extends DefaultTableCellRenderer { //classe do tipo padrão de renderer de tabelas do java q aceita imageicon

        protected void setValue(Object value) {
            if (value instanceof ImageIcon) {
                if (value != null) {
                    ImageIcon d = (ImageIcon) value;
                    setIcon(d);
                } else {
                    setText("");
                    setIcon(null);
                }
            } else {
                super.setValue(value);
            }
            setBorder(noFocusBorder);
        }
    }

    public class JTableRendererBorder extends DefaultTableCellRenderer { //classe do tipo padrão de renderer de tabelas do java q aceita imageicon

        protected void setValue(Object value) {
            setBorder(noFocusBorder);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelProfilePicture = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCampoBusca = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableResultadoWeb = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("+ Buscar nova série");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 220, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("- Em Breve");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 220, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("- Terminadas");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 220, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("- Continuando");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 220, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("- Arquivadas");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 220, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("- Atualizadas");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 220, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("< VOLTAR");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("- Atrasadas");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 220, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("- Minhas séries");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 240, 60));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/yellow.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 250, 2));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("2 meses, 12 dias e 10 horas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 14));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("gastos assistindo séries incríveis");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 160, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Miguel Deitos");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 90, 14));

        jLabelProfilePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/game-shadowgun-icon.png"))); // NOI18N
        getContentPane().add(jLabelProfilePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 60, 70));

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grey.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 272, 643));

        jTextFieldCampoBusca.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jTextFieldCampoBusca.setText("Doctor Who");
        jTextFieldCampoBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCampoBuscaKeyPressed(evt);
            }
        });
        getContentPane().add(jTextFieldCampoBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 700, 60));

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });
        jButtonPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonPesquisarKeyPressed(evt);
            }
        });
        getContentPane().add(jButtonPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 240, 60));

        jTableResultadoWeb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Pôster", "Nome", "Ano"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableResultadoWeb.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableResultadoWeb);
        if (jTableResultadoWeb.getColumnModel().getColumnCount() > 0) {
            jTableResultadoWeb.getColumnModel().getColumn(0).setMinWidth(123);
            jTableResultadoWeb.getColumnModel().getColumn(0).setPreferredWidth(123);
            jTableResultadoWeb.getColumnModel().getColumn(0).setMaxWidth(123);
            jTableResultadoWeb.getColumnModel().getColumn(2).setMinWidth(125);
            jTableResultadoWeb.getColumnModel().getColumn(2).setPreferredWidth(125);
            jTableResultadoWeb.getColumnModel().getColumn(2).setMaxWidth(125);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 970, 560));

        setSize(new java.awt.Dimension(1245, 667));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        try {
            PaginaBusca = 1;
            PesquisarSerieWeb();
        } catch (IOException ex) {
            Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jTextFieldCampoBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCampoBuscaKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            try {
                PaginaBusca = 1;
                PesquisarSerieWeb();
            } catch (IOException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldCampoBuscaKeyPressed

    private void jButtonPesquisarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonPesquisarKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            try {
                PaginaBusca = 1;
                PesquisarSerieWeb();
            } catch (IOException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonPesquisarKeyPressed

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
            java.util.logging.Logger.getLogger(BuscaNovaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscaNovaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscaNovaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscaNovaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BuscaNovaSerie().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BuscaNovaSerie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProfilePicture;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableResultadoWeb;
    private javax.swing.JTextField jTextFieldCampoBusca;
    // End of variables declaration//GEN-END:variables
}
