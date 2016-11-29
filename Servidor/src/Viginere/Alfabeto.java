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
public class Alfabeto {

    //Cria um array de characteres com 96 posições
    public char[] gerarAlfabeto() {
        char[] abc = new char[96];

        for (int i = 32; i <= 127; i++) {
            abc[i - 32] = (char) i;
        }
        return abc;
    }
}
