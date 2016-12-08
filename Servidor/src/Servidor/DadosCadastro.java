/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.Serializable;

/**
 *
 * @author migue_000
 */
public class DadosCadastro implements Serializable{
    
    public DadosCadastro(String ip, String email, String login) {
        this.ip=ip;
        this.email = email;
        this.login = login;
    }

    public DadosCadastro(String ip, String nome, String nascimento, String email, String login, String senha) {
        this.ip=ip;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }
    
    private String nome;
    private String nascimento;
    private String email;
    private String login;
    private String senha;
    private String ip;
    
    boolean ExisteLogin = false;//
    boolean ExisteEmail = false;//

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public boolean isExisteLogin() {
        return ExisteLogin;
    }

    public void setExisteLogin(boolean ExisteLogin) {
        this.ExisteLogin = ExisteLogin;
    }

    public boolean isExisteEmail() {
        return ExisteEmail;
    }

    public void setExisteEmail(boolean ExisteEmail) {
        this.ExisteEmail = ExisteEmail;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
}
