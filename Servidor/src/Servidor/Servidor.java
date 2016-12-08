package Servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.provider.VerificationProvider;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(1234);
            System.out.println("Servidor inicializado. Aguardando conexão...");
            while (true) {
                // cria a thread do cliente a cada nova conexão
                Socket cliente = servidor.accept();
                TrataCliente trataCliente = new TrataCliente(cliente);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

class TrataCliente extends Thread {

    Socket cliente;

    public TrataCliente(Socket cliente) {
        this.cliente = cliente;
        this.start();
    }

    public void run() {
        try {
            DataInputStream dIn = new DataInputStream(cliente.getInputStream());
            String readUTF = dIn.readUTF();
            if (readUTF.equals("Login")) {
                ConectaBanco CBD = new ConectaBanco();
                ObjectInputStream inFromClient = new ObjectInputStream(cliente.getInputStream());
                DadosUsuario DadosUsuario;
                while ((DadosUsuario = (DadosUsuario) inFromClient.readObject()) != null) {
                    System.err.println("Usuário tentando se conectar");
                    if (CBD.VerificaUsuario(DadosUsuario) != 0) {
                        System.out.println("Usuário e senha conferem!");
                    }
                    //envia a reposta para o cliente
                    Socket cliente = new Socket(DadosUsuario.getIp(), 12345);
                    ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
                    ObjectOutputStream.writeObject(DadosUsuario);
                    ObjectOutputStream.close();
                    cliente.close();
                    break;
                }
                inFromClient.close();
            } else if (readUTF.equals("WatchTime")) {
                ConectaBanco CBD = new ConectaBanco();
                ObjectInputStream inFromClient = new ObjectInputStream(cliente.getInputStream());
                WatchTime WatchTime;
                while ((WatchTime = (WatchTime) inFromClient.readObject()) != null) {
                    System.out.println("WatchTime!");
                    String DiasHoras = CBD.WatchTime(WatchTime);
                    String[] WatchTimeSplitada = DiasHoras.split("#");
                    WatchTime.setWatchTimeDias(Integer.parseInt(WatchTimeSplitada[0]));
                    WatchTime.setWatchTimeHoras(Integer.parseInt(WatchTimeSplitada[1]));
                    //envia a reposta para o cliente
                    Socket cliente = new Socket(WatchTime.getIp(), 12345);
                    ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
                    ObjectOutputStream.writeObject(WatchTime);
                    ObjectOutputStream.close();
                    cliente.close();
                    break;
                }
                inFromClient.close();
            } else if (readUTF.equals("VerificaCadastro")) {
                ConectaBanco CBD = new ConectaBanco();
                ObjectInputStream inFromClient = new ObjectInputStream(cliente.getInputStream());
                DadosCadastro DadosCadastro;
                while ((DadosCadastro = (DadosCadastro) inFromClient.readObject()) != null) {
                    String verificados = CBD.VerificaCadastro(DadosCadastro);
                    String[] verificadosSplitado = verificados.split("#");
                    if(verificadosSplitado[0].equals("tem")){
                        DadosCadastro.setExisteLogin(true);
                    }else{
                        DadosCadastro.setExisteLogin(false);
                    }
                    if(verificadosSplitado[1].equals("tem")){
                        DadosCadastro.setExisteEmail(true);
                    }else{
                        DadosCadastro.setExisteEmail(false);
                    }
                    //envia a reposta para o cliente
                    Socket cliente = new Socket(DadosCadastro.getIp(), 12345);
                    ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
                    ObjectOutputStream.writeObject(DadosCadastro);
                    ObjectOutputStream.close();
                    cliente.close();
                    break;
                }
                inFromClient.close();
            }

            /*
             System.err.println("2");
             DadosSerie DadosSerieServidor;
             while ((DadosSerieServidor = (DadosSerie) inFromClient.readObject()) != null) {
             if (DadosSerieServidor.isVerificaExistencia()) {
             System.out.println("Já existe no banco");
             CBD.VerificaExistencia(DadosSerieServidor);
             DadosSerieServidor.setVerificaExistencia(false);
             }
             System.out.println("Recebendo dados do cliente!");
             System.out.println(DadosSerieServidor.getAnoInicio());
             CBD.InserirSerie(DadosSerieServidor);
             break;
             } */
            dIn.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*
         String mensagemRecebida = (String) in.readObject();
         String mensagemEnviar = "";
         while (!mensagemRecebida.equals("fim")) {
         if (!mensagemRecebida.equals("envioObjeto")) {
         System.out.println("Recebido do cliente: " + mensagemRecebida);
         System.out.println("Enviar para o cliente: ");
         mensagemEnviar = entrada.nextLine();                    
         } else {
         out.writeObject("Ok");
         Aluno meuAluno = (Aluno)in.readObject();
         System.out.println("Recebido do cliente: Nome: " + meuAluno.getNome() + ", Curso: " + meuAluno.getCurso());
         mensagemEnviar = "Objeto adicionado!    ";
         }
         out.writeObject(mensagemEnviar);
         mensagemRecebida = (String)in.readObject();
         }      
         */
    }

}
