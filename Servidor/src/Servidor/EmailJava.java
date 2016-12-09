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
     

   public void EnviarEmail (String Email) throws EmailException{
       Email email = new SimpleEmail();
       email.setHostName("smtp.googlemail.com");
       email.setSmtpPort(465);
       email.setAuthenticator(new DefaultAuthenticator("seriescloudrecupera@gmail.com", "recuper@"));
       email.setSSLOnConnect(true);
       email.setFrom("SerisCloud@gmail.com");
       email.setSubject("Recuperação de Senha");
       email.setMsg("Sua nova senha é 123456789, entre com sua nova senha e mude assim que possível.");
       email.addTo(Email);
       email.send();
    }

}