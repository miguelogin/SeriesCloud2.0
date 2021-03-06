/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import Servidor.DadosUsuario;
import Servidor.DadosSerie;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/* @author migue_000 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public TelaInicial() {
        try {
            initComponents();
            this.setVisible(true);
            InetAddress addr = InetAddress.getLocalHost();
            ipCliente = addr.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static String ipCliente;
    static String ipServidor = "127.0.0.1";
    private int TipoUsuario = 0;

    public boolean Respota() {
        boolean ok = false;
        try {
            ServerSocket resposta = new ServerSocket(12345);
            while (true) {
                Socket cliente = resposta.accept();
                ObjectInputStream inFromServidor = new ObjectInputStream(cliente.getInputStream());
                DadosUsuario DadosUsuario;
                while ((DadosUsuario = (DadosUsuario) inFromServidor.readObject()) != null) {
                    if (DadosUsuario.getTipo() == 1) {
                        System.out.println("ADMIN!");
                        TipoUsuario = 1;
                        ok = true;
                    } else if (DadosUsuario.getTipo() == 2) {
                        System.out.println("USUARIO!");
                        ok = true;
                    } else {
                        System.out.println("USUÁRIO INEXISTENTE OU SENHA ERRADA");
                        ok = false;
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
        return ok;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPasswordField1.setText("migueldeitos");
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(490, 330, 210, 50);

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(480, 420, 222, 64);

        jButton2.setText("Cadastrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(480, 500, 222, 64);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Senha:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(400, 340, 160, 29);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Login:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(400, 270, 160, 29);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setText("miguelogin");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(490, 260, 210, 50);

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SeriesCloud");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(560, 160, 160, 29);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/game-shadowgun-icon.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(480, 140, 64, 70);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abertura-melhores-series-TV-foto-citacoes-cinefilas.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1218, 662);

        setSize(new java.awt.Dimension(1229, 682));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Socket servidor = new Socket(ipServidor, 1234);

            DataOutputStream dOut = new DataOutputStream(servidor.getOutputStream());
            dOut.writeUTF("Login");
            dOut.flush();
            DadosUsuario DadosUsuario = new DadosUsuario(jTextField1.getText(), jPasswordField1.getText(), ipCliente);
            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
            ObjectOutputStream.writeObject(DadosUsuario);
            ObjectOutputStream.close();
            servidor.close();
            if (Respota() == true) {
                if (TipoUsuario == 1) {
                    InsereNovaSerie InsereNovaSerie = new InsereNovaSerie(DadosUsuario.getLogin(), ipServidor, ipCliente);
                    this.dispose();
                } else {

                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor verifique seu login e senha e tente novamente!", "Login ou senha incorretos", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Cadastrar Cadastrar = new Cadastrar(ipServidor, ipCliente);
        this.dispose();
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
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
