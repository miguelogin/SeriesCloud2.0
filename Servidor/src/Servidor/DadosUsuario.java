package Servidor;

import java.io.Serializable;

public class DadosUsuario implements Serializable{

    public DadosUsuario(String login, String senha, String ip) {
        this.login = login;
        this.senha = senha;
        this.ip = ip;
    }

    public DadosUsuario(int tipo) {
        this.tipo = tipo;
    }
 
    private String login;
    private String senha;
    private String ip;
    private int tipo; //0 = n existe 1 = admin 2 = usuario padrão

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
