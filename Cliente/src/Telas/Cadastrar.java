/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Servidor.DadosCadastro;
import Servidor.WatchTime;
import static Telas.TelaInicial.ipCliente;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author migue_000
 */
public class Cadastrar extends javax.swing.JFrame {

    private String ipServidor;
    private String ip;

    public Cadastrar(String ipServidor, String ipCliente) {
        initComponents();
        this.setVisible(true);
        this.ipServidor = ipServidor;
        this.ip = ipCliente;
        jCheckBoxTermos.setOpaque(true);
        jCheckBoxTermos.setText("<html>Concordo com os Termos de Serviço<br> e a Política de Privacidade</html>");
    }

    public void RespotaVerificaCadastro() {
        try {
            ServerSocket resposta = new ServerSocket(12345);
            while (true) {
                Socket cliente = resposta.accept();
                ObjectInputStream inFromServidor = new ObjectInputStream(cliente.getInputStream());
                DadosCadastro DadosCadastro;
                while ((DadosCadastro = (DadosCadastro) inFromServidor.readObject()) != null) {
                    if (DadosCadastro.isExisteLogin()) {
                        JOptionPane.showMessageDialog(this, "Este login já esta sendo utilizado por outra pessoa.", "Login inválido", JOptionPane.ERROR_MESSAGE);
                    } else if (DadosCadastro.isExisteEmail()) {
                        JOptionPane.showMessageDialog(this, "Este e-mail já esta sendo utilizado por outra pessoa.", "E-mail inválido", JOptionPane.ERROR_MESSAGE);
                    } else if (!DadosCadastro.isExisteEmail() && !DadosCadastro.isExisteLogin()) {
                        try {
                            Socket servidor = new Socket(ipServidor, 1234);
                            DataOutputStream dOut = new DataOutputStream(servidor.getOutputStream());
                            dOut.writeUTF("Cadastro");
                            dOut.flush();
                            String mes = "00";
                            if(jComboBoxMesNascimento.getSelectedItem().equals("Janeiro")){
                                mes = "01";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Fevereiro")){
                                mes = "02";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Março")){
                                mes = "03";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Abril")){
                                mes = "04";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Maio")){
                                mes = "05";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Junho")){
                                mes = "06";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Julho")){
                                mes = "07";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Agosto")){
                                mes = "08";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Setembro")){
                                mes = "09";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Outubro")){
                                mes = "10";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Novembro")){
                                mes = "11";
                            }else if(jComboBoxMesNascimento.getSelectedItem().equals("Dezembro")){
                                mes = "12";
                            }
                            String Nascimento = jComboBoxAnoNascimento.getSelectedItem()+"-"+mes+"-"+jComboBoxDiaNascimento.getSelectedItem();
                            DadosCadastro = new DadosCadastro(ip, jTextFieldNome.getText(), Nascimento, jTextFieldEmail.getText(), jTextFieldLogin.getText(), jPasswordFieldSenha.getText());
                            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
                            ObjectOutputStream.writeObject(DadosCadastro);
                            ObjectOutputStream.close();
                            servidor.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Cadastrar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(this, "Cadastro concluído!\nAgora você já pode entrar no SeriesCloud", "Cadastro concluído", JOptionPane.INFORMATION_MESSAGE);
                        TelaInicial TelaInicial = new TelaInicial();
                        this.dispose();
                    }
                    break;
                }
                break;
            }
            resposta.close();
        } catch (IOException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxTermos = new javax.swing.JCheckBox();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jComboBoxMesNascimento = new javax.swing.JComboBox();
        jComboBoxAnoNascimento = new javax.swing.JComboBox();
        jComboBoxDiaNascimento = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jCheckBoxTermos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxTermos.setSelected(true);
        jCheckBoxTermos.setText("Concordo com os Termos de Serviço e Política de Privacidade");
        jCheckBoxTermos.setAutoscrolls(true);
        jCheckBoxTermos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jCheckBoxTermos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(jCheckBoxTermos);
        jCheckBoxTermos.setBounds(490, 480, 230, 30);

        jPasswordFieldSenha.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPasswordFieldSenha.setText("migueldeitos2");
        getContentPane().add(jPasswordFieldSenha);
        jPasswordFieldSenha.setBounds(490, 410, 300, 50);

        jButton2.setText("Cadastrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(490, 520, 300, 64);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Email:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(400, 280, 160, 29);

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldEmail.setText("migueldeitos2@gmail.com");
        getContentPane().add(jTextFieldEmail);
        jTextFieldEmail.setBounds(490, 270, 300, 50);

        jComboBoxMesNascimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        getContentPane().add(jComboBoxMesNascimento);
        jComboBoxMesNascimento.setBounds(560, 200, 130, 50);

        jComboBoxAnoNascimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900", "1899", "1898", "1897", "1896", "1895", "1894", "1893", "1892", "1891", "1890", "1889", "1888", "1887", "1886", "1885", "1884", "1883", "1882", "1881" }));
        getContentPane().add(jComboBoxAnoNascimento);
        jComboBoxAnoNascimento.setBounds(700, 200, 90, 50);

        jComboBoxDiaNascimento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));
        getContentPane().add(jComboBoxDiaNascimento);
        jComboBoxDiaNascimento.setBounds(490, 200, 60, 50);

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(400, 150, 160, 29);

        jTextFieldNome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldNome.setText("Miguel Deitos");
        getContentPane().add(jTextFieldNome);
        jTextFieldNome.setBounds(490, 140, 300, 50);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nascimento:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(330, 210, 160, 29);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(400, 420, 160, 29);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Login:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(400, 350, 160, 29);

        jTextFieldLogin.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextFieldLogin.setText("miguelogin");
        getContentPane().add(jTextFieldLogin);
        jTextFieldLogin.setBounds(490, 340, 300, 50);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SeriesCloud");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(580, 60, 160, 29);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/game-shadowgun-icon.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(500, 50, 64, 52);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abertura-melhores-series-TV-foto-citacoes-cinefilas.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1218, 662);

        setSize(new java.awt.Dimension(1229, 682));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextFieldNome.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "O campo de nome não pode ficar vazio.", "Campo de nome nulo", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldNome.getText().length() <= 1) {
            JOptionPane.showMessageDialog(this, "Este não é um nome válido.\nO nome deve ter ao menos 2 letras.", "Nome inválido", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldEmail.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "O campo de e-mail não pode ficar vazio deve ter ao menos 2 letras.", "Campo de e-mail nulo", JOptionPane.ERROR_MESSAGE);
        } else if (!jTextFieldEmail.getText().contains("@")) {
            JOptionPane.showMessageDialog(this, "Este não é um e-mail válido.", "E-mail inválido", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldEmail.getText().length() <= 5) {
            JOptionPane.showMessageDialog(this, "Este não é um e-mail válido.", "E-mail inválido", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldLogin.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "O campo de login não pode ficar vazio", "Campo de login nulo", JOptionPane.ERROR_MESSAGE);
        } else if (jTextFieldLogin.getText().length() < 5) {
            JOptionPane.showMessageDialog(this, "Este não é um login válido.\nUm login válido precisa ter ao menos 5 caracteres.", "Login inválido", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordFieldSenha.getText().contains(jTextFieldNome.getText())) {
            JOptionPane.showMessageDialog(this, "Por motivos de segurança a sua senha não deve conter o seu nome.", "Senha não permitida", JOptionPane.ERROR_MESSAGE);
        } else if (jPasswordFieldSenha.getText().length() < 8) {
            JOptionPane.showMessageDialog(this, "Por motivos de segurança a sua senha deve ter no mínimo 8 caracteres.", "Senha muito curta", JOptionPane.ERROR_MESSAGE);
        } else if (!jCheckBoxTermos.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Você deve concordar com nossos termos de serviço e política de privacidade para poder se cadastrar.", "Termo de Serviço e Política de Privacidade", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Socket servidor = new Socket(ipServidor, 1234);
                DataOutputStream dOut = new DataOutputStream(servidor.getOutputStream());
                dOut.writeUTF("VerificaCadastro");
                dOut.flush();
                DadosCadastro DadosCadastro = new DadosCadastro(ip, jTextFieldEmail.getText(), jTextFieldLogin.getText());
                ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
                ObjectOutputStream.writeObject(DadosCadastro);
                ObjectOutputStream.close();
                servidor.close();
                RespotaVerificaCadastro();
            } catch (IOException ex) {
                Logger.getLogger(Cadastrar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cadastrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxTermos;
    private javax.swing.JComboBox jComboBoxAnoNascimento;
    private javax.swing.JComboBox jComboBoxDiaNascimento;
    private javax.swing.JComboBox jComboBoxMesNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
