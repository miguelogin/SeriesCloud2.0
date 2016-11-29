/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viginere;

/**
 *
 * @author Cezar Vasconcelos
 */
import java.text.Normalizer;

public class CodViginere {

    char[] mensagem;
    char[] chave;
    char[] resultado;
    char matriz[][];
    char[] texto;

    public CodViginere(String msg, String chave) {
        msg = RetiraAcento(msg);
        this.mensagem = msg.toCharArray();
        char[] chaveTemp = chave.toCharArray();
        this.chave = new char[msg.length()];
        int cont = 0;

        for (int i = 0; i < msg.length(); i++) {
            this.chave[i] = chaveTemp[cont];
            cont++;
            if (cont == chaveTemp.length) {
                cont = 0;
            }
        }

        GerarMatriz gm = new GerarMatriz();
        this.matriz = gm.gerarMatriz();
        //cifrar();
    }

    public static String RetiraAcento(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    public String cifrar() {

        char[] cifrado = new char[mensagem.length];
        String retorno = "";
        int x, y;

        for (int cont = 0; cont < mensagem.length; cont++) {
            x = (int) this.mensagem[cont] - 32;
            y = (int) this.chave[cont] - 32;
            cifrado[cont] = this.matriz[x][y];
        }
        this.resultado = cifrado;

        System.out.println(this.mensagem);
        System.out.println(this.chave);
        System.out.println(cifrado);

        for (int i = 0; i < cifrado.length; i++) {
            retorno += cifrado[i];
        }

        for (int k = 0; k < 96; k++) {
            System.out.println(this.matriz[k]);
        }

        return retorno;
    }

    public String decifrar() {
        char[] decifrado = new char[mensagem.length];
        String retorno = "";
        int x = 0, y = 0;

        for (int cont = 0; cont < mensagem.length; cont++) {
            x = (int) this.mensagem[cont] - 32;
            y = (int) this.chave[cont] - 32;

            char[] coluna = new char[96];

            for (int i = 0; i < 96; i++) {
                coluna[i] = this.matriz[i][y];
            }

            int a = 0;
            boolean teste = false;
            do {
                if (coluna[a] == mensagem[cont]) {
                    break;
                }
                a++;
            } while (teste == false);

            decifrado[cont] = this.matriz[a][0];

        }

        for (int i = 0; i < decifrado.length; i++) {
            retorno += decifrado[i];
        }

        System.out.println(this.mensagem);
        System.out.println(this.chave);
        System.out.println(decifrado);

        this.resultado = decifrado;
        return retorno;
    }
}
