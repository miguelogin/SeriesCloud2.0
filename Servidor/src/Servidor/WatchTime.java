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
public class WatchTime implements Serializable{
    private int WatchTimeDias;
    private int WatchTimeHoras;
    private String login;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public WatchTime(String ip, int WatchTimeDias, int WatchTimeHoras, String login) {
        this.ip=ip;
        this.WatchTimeDias = WatchTimeDias;
        this.WatchTimeHoras = WatchTimeHoras;
        this.login = login;
    }

    public int getWatchTimeDias() {
        return WatchTimeDias;
    }

    public void setWatchTimeDias(int WatchTimeDias) {
        this.WatchTimeDias = WatchTimeDias;
    }

    public int getWatchTimeHoras() {
        return WatchTimeHoras;
    }

    public void setWatchTimeHoras(int WatchTimeHoras) {
        this.WatchTimeHoras = WatchTimeHoras;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    
}
