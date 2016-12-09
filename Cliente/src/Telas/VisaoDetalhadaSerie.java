package Telas;

import Servidor.BuscaWeb;
import Servidor.BuscaWebEspecifica;
import Servidor.DadosCadastro;
import Servidor.DadosSerie;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class VisaoDetalhadaSerie extends javax.swing.JFrame {

    public VisaoDetalhadaSerie(String login, String NomeSerie, int PaginaBusca, int posicao, BuscaWeb BuscaWeb, BuscaWebEspecifica BuscaWebEspecifica, String ipServidor, String ip) throws IOException, ClassNotFoundException, ParseException {
        initComponents();
        this.login = login;
        this.ipServidor = ipServidor;
        this.ip = ip;
        this.BuscaWeb = BuscaWeb;
        this.BuscaWebEspecifica = BuscaWebEspecifica;
        jButtonAnteriorEpisodio.setVisible(false);
        File arquivoBackupPesquisa = new File("src/backupPesquisa.txt");
        try {
            FileWriter arquivoWriter = new FileWriter(arquivoBackupPesquisa, true);
            PrintWriter escrever = new PrintWriter(arquivoWriter);
            escrever.println(NomeSerie + "#" + PaginaBusca);
            arquivoWriter.close();
        } catch (Exception ex) {
            System.err.println("Não consegui escrever o arquivo bakcupPesquisa.txt");
        }
        arquivoBackupPesquisa.deleteOnExit();

        InsereDadosTela(posicao, BuscaWeb, BuscaWebEspecifica, ipServidor, ip);
    }

    private String ipServidor;
    private String ip;
    private BuscaWeb BuscaWeb;
    private BuscaWebEspecifica BuscaWebEspecifica;
    private DadosSerie DadosSerie;
    private String login;
    private int NavegacaoTemporada = 1;
    private int NavegacaoEpisodio = 1;

    private void InsereDadosTela(int posicao, BuscaWeb BuscaWeb, BuscaWebEspecifica BuscaWebEspecifica, String ipServidor, String ip) throws IOException, ClassNotFoundException, ParseException {
        File ImagePoster = new File("src//images//temp//temp_poster.png");
        BufferedImage BufferedImage = ImageIO.read(ImagePoster);
        ImageIcon Poster = new ImageIcon(BufferedImage.getScaledInstance(250, 349, Image.SCALE_SMOOTH));
        jLabelPosterSerie.setIcon(Poster);
        jLabelNomeSerie.setText(BuscaWebEspecifica.getNomeSerie());
        System.err.println(BuscaWebEspecifica.getNotaSerie());
        if (BuscaWebEspecifica.getNotaSerie() <= 1) {
            ImageIcon estrelas_1 = new ImageIcon("src/images/estrelas_nota_1.png");
            jLabelNotaSerie.setIcon(estrelas_1);
        } else if (BuscaWebEspecifica.getNotaSerie() > 1 && BuscaWebEspecifica.getNotaSerie() <= 2) {
            ImageIcon estrelas_2 = new ImageIcon("src/images/estrelas_nota_2.png");
            jLabelNotaSerie.setIcon(estrelas_2);
        } else if (BuscaWebEspecifica.getNotaSerie() > 2 && BuscaWebEspecifica.getNotaSerie() <= 3) {
            ImageIcon estrelas_3 = new ImageIcon("src/images/estrelas_nota_3.png");
            jLabelNotaSerie.setIcon(estrelas_3);
        } else if (BuscaWebEspecifica.getNotaSerie() > 3 && BuscaWebEspecifica.getNotaSerie() <= 4) {
            ImageIcon estrelas_4 = new ImageIcon("src/images/estrelas_nota_4.png");
            jLabelNotaSerie.setIcon(estrelas_4);
        } else if (BuscaWebEspecifica.getNotaSerie() > 4 && BuscaWebEspecifica.getNotaSerie() <= 5) {
            ImageIcon estrelas_5 = new ImageIcon("src/images/estrelas_nota_5.png");
            jLabelNotaSerie.setIcon(estrelas_5);
        } else if (BuscaWebEspecifica.getNotaSerie() > 5 && BuscaWebEspecifica.getNotaSerie() <= 6) {
            ImageIcon estrelas_6 = new ImageIcon("src/images/estrelas_nota_6.png");
            jLabelNotaSerie.setIcon(estrelas_6);
        } else if (BuscaWebEspecifica.getNotaSerie() > 6 && BuscaWebEspecifica.getNotaSerie() <= 7) {
            ImageIcon estrelas_7 = new ImageIcon("src/images/estrelas_nota_7.png");
            jLabelNotaSerie.setIcon(estrelas_7);
        } else if (BuscaWebEspecifica.getNotaSerie() > 7 && BuscaWebEspecifica.getNotaSerie() <= 8) {
            ImageIcon estrelas_8 = new ImageIcon("src/images/estrelas_nota_8.png");
            jLabelNotaSerie.setIcon(estrelas_8);
        } else if (BuscaWebEspecifica.getNotaSerie() > 8 && BuscaWebEspecifica.getNotaSerie() <= 9) {
            ImageIcon estrelas_9 = new ImageIcon("src/images/estrelas_nota_9.png");
            jLabelNotaSerie.setIcon(estrelas_9);
        } else {
            ImageIcon estrelas_10 = new ImageIcon("src/images/estrelas_nota_10.png");
            jLabelNotaSerie.setIcon(estrelas_10);
        }

        if (BuscaWebEspecifica.getQuatidadeCategorias() == 0) {
            jLabelCategoriasSerie.setText(BuscaWebEspecifica.getCategoria()[0]);
        } else {
            jLabelCategoriasSerie.setText(BuscaWebEspecifica.getCategoria()[0] + " - " + BuscaWebEspecifica.getCategoria()[1]);
        }
        if (BuscaWeb.getAnoFim()[posicao] == 0) {
            jLabelAnoSerie.setText(String.valueOf(BuscaWeb.getAnoInicio()[posicao]));
        } else if (BuscaWeb.getAnoFim()[posicao] == 1) {
            jLabelAnoSerie.setText(String.valueOf(BuscaWeb.getAnoInicio()[posicao] + " - Continuando"));
        } else {
            jLabelAnoSerie.setText(String.valueOf(BuscaWeb.getAnoInicio()[posicao]) + "-" + String.valueOf(BuscaWeb.getAnoFim()[posicao]));
        }
        jLabelTotalEpisodios.setText(String.valueOf(BuscaWebEspecifica.getTotalEpisodios()) + " episódios");
        jLabelWatchTime.setText(String.valueOf(BuscaWebEspecifica.getWatchTimeDias()) + " dias e " + String.valueOf(BuscaWebEspecifica.getWatchTimeHoras()) + " horas para concluir esta série");
        for (int x = 1; x <= BuscaWebEspecifica.getTotalTemporadas(); x++) {
            jComboBoxTemporada.addItem("Temporada " + x);
        }
        jTextPaneSinopse.setText(BuscaWebEspecifica.getSinopseSerie());
        jTextPaneSinopse.setCaretPosition(0);

        //DEFINE DADOS DO VISUALIZADOS DE EPISÓDIOS
        Navega();
    }

    private void RegistraSerie(BuscaWeb BuscaWeb, BuscaWebEspecifica BuscaWebEspecifica, String ipServidor, String ip) throws IOException, ClassNotFoundException, ParseException {
        try {
            Socket servidor = new Socket(ipServidor, 1234);
            DataOutputStream dOut = new DataOutputStream(servidor.getOutputStream());
            dOut.writeUTF("RegistrarSerie");
            dOut.flush();
            DadosSerie = new DadosSerie(BuscaWebEspecifica.getNomeSerie(), BuscaWeb.getAnoInicio()[1], BuscaWeb.getAnoFim()[1], BuscaWebEspecifica.getSinopseSerie(), BuscaWebEspecifica.getQuatidadeCategorias(), BuscaWebEspecifica.getCategoria()[0], BuscaWebEspecifica.getTotalTemporadas(), BuscaWebEspecifica.getTotalEpisodios(), BuscaWebEspecifica.getNotaSerie(), BuscaWebEspecifica.getNomeEpisodio(), BuscaWebEspecifica.getReleaseDate(), BuscaWebEspecifica.getNotaEpisodio(), BuscaWebEspecifica.getDuracaoEpisodio(), ip);
            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
            ObjectOutputStream.writeObject(DadosSerie);
            ObjectOutputStream.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Cadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Navega() throws ParseException {
        if (NavegacaoTemporada == 1 && NavegacaoEpisodio == 1) {
            jButtonAnteriorEpisodio.setVisible(false);
        } else {
            jButtonAnteriorEpisodio.setVisible(true);
        }
        if (NavegacaoTemporada == BuscaWebEspecifica.getTotalTemporadas() && NavegacaoEpisodio == Integer.parseInt(BuscaWebEspecifica.getTotalEpisodiosTemporada()[BuscaWebEspecifica.getTotalTemporadas()])) {
            jButtonProximoEpisodio.setVisible(false);
        } else {
            jButtonProximoEpisodio.setVisible(true);
        }
        try {
            if (NavegacaoEpisodio < Integer.parseInt(BuscaWebEspecifica.getTotalEpisodiosTemporada()[NavegacaoTemporada])) {
                jLabelSeasonEpisode.setText("S" + NavegacaoTemporada + "E" + NavegacaoEpisodio);
                jLabelNomeEpisodio.setText(BuscaWebEspecifica.getNomeEpisodio()[NavegacaoTemporada][NavegacaoEpisodio]);

                SimpleDateFormat Americana = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat Brasileira = new SimpleDateFormat("dd-MM-yyyy");
                Date dataAmericana = Americana.parse(BuscaWebEspecifica.getReleaseDate()[NavegacaoTemporada][NavegacaoEpisodio]);
                jLabelDuracaoData.setText(String.valueOf(BuscaWebEspecifica.getDuracaoEpisodio()) + " min - " + Brasileira.format(dataAmericana));
                System.err.println(BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio]);
                if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 1) {
                    ImageIcon estrelas_1 = new ImageIcon("src/images/estrelas_nota_1.png");
                    jLabelNotaEpisodio.setIcon(estrelas_1);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 1 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 2) {
                    ImageIcon estrelas_2 = new ImageIcon("src/images/estrelas_nota_2.png");
                    jLabelNotaEpisodio.setIcon(estrelas_2);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 2 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 3) {
                    ImageIcon estrelas_3 = new ImageIcon("src/images/estrelas_nota_3.png");
                    jLabelNotaEpisodio.setIcon(estrelas_3);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 3 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 4) {
                    ImageIcon estrelas_4 = new ImageIcon("src/images/estrelas_nota_4.png");
                    jLabelNotaEpisodio.setIcon(estrelas_4);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 4 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 5) {
                    ImageIcon estrelas_5 = new ImageIcon("src/images/estrelas_nota_5.png");
                    jLabelNotaEpisodio.setIcon(estrelas_5);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 5 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 6) {
                    ImageIcon estrelas_6 = new ImageIcon("src/images/estrelas_nota_6.png");
                    jLabelNotaEpisodio.setIcon(estrelas_6);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 6 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 7) {
                    ImageIcon estrelas_7 = new ImageIcon("src/images/estrelas_nota_7.png");
                    jLabelNotaEpisodio.setIcon(estrelas_7);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 7 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 8) {
                    ImageIcon estrelas_8 = new ImageIcon("src/images/estrelas_nota_8.png");
                    jLabelNotaEpisodio.setIcon(estrelas_8);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 8 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 9) {
                    ImageIcon estrelas_9 = new ImageIcon("src/images/estrelas_nota_9.png");
                    jLabelNotaEpisodio.setIcon(estrelas_9);
                } else {
                    ImageIcon estrelas_10 = new ImageIcon("src/images/estrelas_nota_10.png");
                    jLabelNotaEpisodio.setIcon(estrelas_10);
                }
            } else if (NavegacaoEpisodio == Integer.parseInt(BuscaWebEspecifica.getTotalEpisodiosTemporada()[NavegacaoTemporada])) {
                jLabelSeasonEpisode.setText("S" + NavegacaoTemporada + "E" + NavegacaoEpisodio);
                jLabelNomeEpisodio.setText(BuscaWebEspecifica.getNomeEpisodio()[NavegacaoTemporada][NavegacaoEpisodio]);

                SimpleDateFormat Americana = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat Brasileira = new SimpleDateFormat("dd-MM-yyyy");
                Date dataAmericana = Americana.parse(BuscaWebEspecifica.getReleaseDate()[NavegacaoTemporada][NavegacaoEpisodio]);
                jLabelDuracaoData.setText(String.valueOf(BuscaWebEspecifica.getDuracaoEpisodio()) + " min - " + Brasileira.format(dataAmericana));
                System.err.println(BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio]);
                if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 1) {
                    ImageIcon estrelas_1 = new ImageIcon("src/images/estrelas_nota_1.png");
                    jLabelNotaEpisodio.setIcon(estrelas_1);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 1 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 2) {
                    ImageIcon estrelas_2 = new ImageIcon("src/images/estrelas_nota_2.png");
                    jLabelNotaEpisodio.setIcon(estrelas_2);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 2 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 3) {
                    ImageIcon estrelas_3 = new ImageIcon("src/images/estrelas_nota_3.png");
                    jLabelNotaEpisodio.setIcon(estrelas_3);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 3 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 4) {
                    ImageIcon estrelas_4 = new ImageIcon("src/images/estrelas_nota_4.png");
                    jLabelNotaEpisodio.setIcon(estrelas_4);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 4 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 5) {
                    ImageIcon estrelas_5 = new ImageIcon("src/images/estrelas_nota_5.png");
                    jLabelNotaEpisodio.setIcon(estrelas_5);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 5 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 6) {
                    ImageIcon estrelas_6 = new ImageIcon("src/images/estrelas_nota_6.png");
                    jLabelNotaEpisodio.setIcon(estrelas_6);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 6 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 7) {
                    ImageIcon estrelas_7 = new ImageIcon("src/images/estrelas_nota_7.png");
                    jLabelNotaEpisodio.setIcon(estrelas_7);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 7 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 8) {
                    ImageIcon estrelas_8 = new ImageIcon("src/images/estrelas_nota_8.png");
                    jLabelNotaEpisodio.setIcon(estrelas_8);
                } else if (BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] > 8 && BuscaWebEspecifica.getNotaEpisodio()[NavegacaoTemporada][NavegacaoEpisodio] <= 9) {
                    ImageIcon estrelas_9 = new ImageIcon("src/images/estrelas_nota_9.png");
                    jLabelNotaEpisodio.setIcon(estrelas_9);
                } else {
                    ImageIcon estrelas_10 = new ImageIcon("src/images/estrelas_nota_10.png");
                    jLabelNotaEpisodio.setIcon(estrelas_10);
                }
                NavegacaoTemporada++;
                NavegacaoEpisodio = 0;
            }
        } catch (Exception e) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneSinopseSerie = new javax.swing.JScrollPane();
        jTextPaneSinopse = new javax.swing.JTextPane();
        jLabelNotaSerie = new javax.swing.JLabel();
        jToggleButtonAcompanhar = new javax.swing.JToggleButton();
        jButton3 = new javax.swing.JButton();
        jLabelPosterSerie = new javax.swing.JLabel();
        jLabelNomeSerie = new javax.swing.JLabel();
        jLabelCategoriasSerie = new javax.swing.JLabel();
        jLabelAnoSerie = new javax.swing.JLabel();
        jLabelTotalEpisodios = new javax.swing.JLabel();
        jLabelWatchTime = new javax.swing.JLabel();
        jComboBoxTemporada = new javax.swing.JComboBox();
        jButtonAnteriorEpisodio = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonProximoEpisodio = new javax.swing.JButton();
        jLabelDuracaoData = new javax.swing.JLabel();
        jLabelNomeEpisodio = new javax.swing.JLabel();
        jLabelSeasonEpisode = new javax.swing.JLabel();
        jToggleButtonMarcarAssistido = new javax.swing.JToggleButton();
        jLabelNotaEpisodio = new javax.swing.JLabel();
        jLabelImagemEpisodio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jTextPaneSinopse.setEditable(false);
        jTextPaneSinopse.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextPaneSinopse.setOpaque(false);
        jScrollPaneSinopseSerie.setViewportView(jTextPaneSinopse);

        getContentPane().add(jScrollPaneSinopseSerie);
        jScrollPaneSinopseSerie.setBounds(10, 550, 250, 90);

        jLabelNotaSerie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNotaSerie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabelNotaSerie);
        jLabelNotaSerie.setBounds(10, 300, 250, 60);

        jToggleButtonAcompanhar.setText("Acompanhar");
        jToggleButtonAcompanhar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAcompanharActionPerformed(evt);
            }
        });
        getContentPane().add(jToggleButtonAcompanhar);
        jToggleButtonAcompanhar.setBounds(10, 370, 250, 30);

        jButton3.setText("< Voltar");
        jButton3.setFocusPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(-7, -7, 80, 30);

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
        jComboBoxTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTemporadaActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxTemporada);
        jComboBoxTemporada.setBounds(10, 510, 250, 30);

        jButtonAnteriorEpisodio.setText("<");
        jButtonAnteriorEpisodio.setEnabled(false);
        jButtonAnteriorEpisodio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnteriorEpisodioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonAnteriorEpisodio);
        jButtonAnteriorEpisodio.setBounds(280, 290, 41, 50);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grey.png"))); // NOI18N
        jLabel2.setText("Print");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -10, 270, 690);

        jButtonProximoEpisodio.setText(">");
        jButtonProximoEpisodio.setEnabled(false);
        jButtonProximoEpisodio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProximoEpisodioActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonProximoEpisodio);
        jButtonProximoEpisodio.setBounds(1180, 280, 41, 50);

        jLabelDuracaoData.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelDuracaoData.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDuracaoData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDuracaoData.setText("43min - 11/11/11");
        getContentPane().add(jLabelDuracaoData);
        jLabelDuracaoData.setBounds(330, 350, 840, 29);

        jLabelNomeEpisodio.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabelNomeEpisodio.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeEpisodio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeEpisodio.setText("Nome Episódio");
        getContentPane().add(jLabelNomeEpisodio);
        jLabelNomeEpisodio.setBounds(330, 270, 840, 80);

        jLabelSeasonEpisode.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        jLabelSeasonEpisode.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSeasonEpisode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeasonEpisode.setText("S1E1");
        getContentPane().add(jLabelSeasonEpisode);
        jLabelSeasonEpisode.setBounds(330, 200, 840, 70);

        jToggleButtonMarcarAssistido.setText("MARCAR COMO ASSISTIDO");
        jToggleButtonMarcarAssistido.setEnabled(false);
        getContentPane().add(jToggleButtonMarcarAssistido);
        jToggleButtonMarcarAssistido.setBounds(630, 460, 230, 40);

        jLabelNotaEpisodio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNotaEpisodio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cinco_estrelas.png"))); // NOI18N
        getContentPane().add(jLabelNotaEpisodio);
        jLabelNotaEpisodio.setBounds(330, 390, 840, 60);

        jLabelImagemEpisodio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Doctor.Who.2005.S09E12.Hell.Bent.720p.WEB-DL.DD5.1.H.264-CtrlHD.mkv_003513762.jpg"))); // NOI18N
        jLabelImagemEpisodio.setText("Print");
        getContentPane().add(jLabelImagemEpisodio);
        jLabelImagemEpisodio.setBounds(260, 0, 990, 650);

        setSize(new java.awt.Dimension(1252, 677));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            this.dispose();
            InsereNovaSerie InsereNovaSerie = new InsereNovaSerie(login, ipServidor, ip);
        } catch (IOException ex) {
            Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jToggleButtonAcompanharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAcompanharActionPerformed
        if (jToggleButtonAcompanhar.isSelected()) {
            try {
                RegistraSerie(BuscaWeb, BuscaWebEspecifica, ipServidor, ip);
            } catch (IOException ex) {
                Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
            }
            jButtonAnteriorEpisodio.setEnabled(true);
            jButtonProximoEpisodio.setEnabled(true);
            jToggleButtonMarcarAssistido.setEnabled(true);
        } else {
            jButtonAnteriorEpisodio.setEnabled(false);
            jButtonProximoEpisodio.setEnabled(false);
            jToggleButtonMarcarAssistido.setEnabled(false);
        }
    }//GEN-LAST:event_jToggleButtonAcompanharActionPerformed

    private void jButtonProximoEpisodioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProximoEpisodioActionPerformed
        try {
            NavegacaoEpisodio++;
            Navega();
        } catch (ParseException ex) {
            Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonProximoEpisodioActionPerformed

    private void jButtonAnteriorEpisodioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnteriorEpisodioActionPerformed
        try {
            if (NavegacaoEpisodio == 1) {
                NavegacaoTemporada--;
                NavegacaoEpisodio = Integer.parseInt(BuscaWebEspecifica.getTotalEpisodiosTemporada()[NavegacaoTemporada]);
            } else {
                NavegacaoEpisodio--;
            }
            Navega();
        } catch (ParseException ex) {
            Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAnteriorEpisodioActionPerformed

    private void jComboBoxTemporadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTemporadaActionPerformed
        try {
            int temporada = jComboBoxTemporada.getSelectedIndex() + 1;
            NavegacaoTemporada = temporada;
            NavegacaoEpisodio = 1;
            Navega();
        } catch (ParseException ex) {
            Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBoxTemporadaActionPerformed

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
            java.util.logging.Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisaoDetalhadaSerie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAnteriorEpisodio;
    private javax.swing.JButton jButtonProximoEpisodio;
    private javax.swing.JComboBox jComboBoxTemporada;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAnoSerie;
    private javax.swing.JLabel jLabelCategoriasSerie;
    private javax.swing.JLabel jLabelDuracaoData;
    private javax.swing.JLabel jLabelImagemEpisodio;
    private javax.swing.JLabel jLabelNomeEpisodio;
    private javax.swing.JLabel jLabelNomeSerie;
    private javax.swing.JLabel jLabelNotaEpisodio;
    private javax.swing.JLabel jLabelNotaSerie;
    private javax.swing.JLabel jLabelPosterSerie;
    private javax.swing.JLabel jLabelSeasonEpisode;
    private javax.swing.JLabel jLabelTotalEpisodios;
    private javax.swing.JLabel jLabelWatchTime;
    private javax.swing.JScrollPane jScrollPaneSinopseSerie;
    private javax.swing.JTextPane jTextPaneSinopse;
    private javax.swing.JToggleButton jToggleButtonAcompanhar;
    private javax.swing.JToggleButton jToggleButtonMarcarAssistido;
    // End of variables declaration//GEN-END:variables

    private InsereNovaSerie BuscaNovaSerie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
