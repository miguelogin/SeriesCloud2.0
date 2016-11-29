package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(12345);
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
            System.out.println("Cliente conectadooooo!");
            Scanner entrada = new Scanner(cliente.getInputStream());
            DadosSerie DadosSerieServidor;
            while (true) {
                // Create input and output streams to client
                ObjectOutputStream outToClient = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream inFromClient = new ObjectInputStream(cliente.getInputStream());
                DadosSerieServidor = (DadosSerie) inFromClient.readObject();
                while ((DadosSerieServidor = (DadosSerie) inFromClient.readObject()) != null) {
                    //DadosSerieServidor = (DadosSerie) inFromClient.readObject();
                    System.out.println("RECEBI? " + DadosSerieServidor.getNomeEpisodio()[1][1]);
                    break;
                }
                //DadosSerie DadosSerieServidor = (DadosSerie) inFromClient.readObject();

                /* Send the modified Message object back */
                //outToClient.writeObject(inMsg);
            }

            /*
             while (entrada.hasNextLine()) {
             System.out.println(entrada.nextLine());
             }
             */
        } catch (IOException ioe) {
            ioe.printStackTrace();
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
