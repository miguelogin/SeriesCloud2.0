/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viginere;

import Servidor.CodViginere;

/**
 *
 * @author Cezar Vasconcelos
 */
public class Testar {

    public static void main(String[] args) {
        CodViginere cv = new CodViginere("fq)vv", "CLOUDSERIES");
        cv.cifrar();
        cv.decifrar();
    }
}
