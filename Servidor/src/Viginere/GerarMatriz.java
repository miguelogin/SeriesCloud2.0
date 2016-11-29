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
public class GerarMatriz {

    public char[][] gerarMatriz() {
        int contador;
        Alfabeto g = new Alfabeto();
        char abcTemp[] = g.gerarAlfabeto();
        char abc[] = new char[abcTemp.length * 2];

        for (int c = 0; c < 96; c++) {
            abc[c] = abcTemp[c];
            abc[c + 96] = abcTemp[c];
        }

        char[][] matriz = new char[96][96];

        for (int i = 0; i < 96; i++) {
            contador = 0;

            for (int j = 0; j < 96; j++) {
                matriz[i][j] = abc[contador + i];
                contador++;
            }
        }
        return matriz;
    }
}
