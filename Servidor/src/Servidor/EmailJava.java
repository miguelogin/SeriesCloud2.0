/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Cezar Vasconcelos
 */
public class EmailJava {
     public static void main(String[] args) throws EmailException {
         EmailJava email = new EmailJava();
         email.EnviarEmail();
     }

   public void EnviarEmail () throws EmailException{
       Email email = new SimpleEmail();
       email.setHostName("smtp.googlemail.com");
       email.setSmtpPort(465);
       email.setAuthenticator(new DefaultAuthenticator("seriescloudrecupera@gmail.com", "recuper@"));
       email.setSSLOnConnect(true);
       email.setFrom("seriescloudrecupera@gmail.com");
       email.setSubject("TestMail");
       email.setMsg("ESSA PORRA VAI FUNCIONAR?");
       email.addTo("crolys@gmail.com");
       email.send();
    }

}