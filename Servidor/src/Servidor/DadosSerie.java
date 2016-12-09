package Servidor;

import java.io.FileInputStream;
import java.io.Serializable;
//CLIENTE
public class DadosSerie implements Serializable{ 
    
    public DadosSerie(String NomeSerie, int AnoInicio, int AnoFim, String Sinopse, int QuantidadeCategoria, String Categoria, int TotalTemporadas, int TotalEpisodios, float NotaSerie, String[][] NomeEpisodio, String[][] ReleaseEpisodio, float[][] NotaEpisodio, int DuracaoEpisodio, String ip) {
        this.ip = ip;
        this.NomeSerie = NomeSerie;
        this.AnoInicio = AnoInicio;
        this.AnoFim = AnoFim;
        this.Sinopse = Sinopse;
        this.Categoria = Categoria;
        this.TotalTemporadas = TotalTemporadas;
        this.TotalEpisodios = TotalEpisodios;
        this.NotaSerie = NotaSerie;
        this.NomeEpisodio = NomeEpisodio;
        this.ReleaseEpisodio = ReleaseEpisodio;
        this.NotaEpisodio = NotaEpisodio;
        this.DuracaoEpisodio = DuracaoEpisodio;
    }
    
    
    public DadosSerie(boolean VerificaExistencia, String NomeSerie, int TotalEpisodios, int TotalTemporadas) { //verificar se ja existe no banco
        this.VerificaExistencia = VerificaExistencia;
        this.NomeSerie = NomeSerie;
        this.TotalTemporadas = TotalTemporadas;
        this.TotalEpisodios = TotalEpisodios;
    }
    
    private boolean VerificaExistencia;

    public boolean isVerificaExistencia() {
        return VerificaExistencia;
    }

    public void setVerificaExistencia(boolean VerificaExistencia) {
        this.VerificaExistencia = VerificaExistencia;
    }
    
    private String ip;
    private String NomeSerie;
    private int AnoInicio;
    private int AnoFim;
    private String Sinopse;
    private String Categoria;
    private int QuantidadeCategorias;
    private int TotalTemporadas;
    private int TotalEpisodios;
    private float NotaSerie;
    private String[][] NomeEpisodio;
    private String[][] ReleaseEpisodio;
    private float [][] NotaEpisodio;
    private int DuracaoEpisodio;

    public String getIp() {
        return ip;
    }
    
    public String getNomeSerie() {
        return NomeSerie;
    }

    public void setNomeSerie(String NomeSerie) {
        this.NomeSerie = NomeSerie;
    }

    public int getAnoInicio() {
        return AnoInicio;
    }

    public void setAnoInicio(int AnoInicio) {
        this.AnoInicio = AnoInicio;
    }

    public int getAnoFim() {
        return AnoFim;
    }

    public void setAnoFim(int AnoFim) {
        this.AnoFim = AnoFim;
    }

    public String getSinopse() {
        return Sinopse;
    }

    public void setSinopse(String Sinopse) {
        this.Sinopse = Sinopse;
    }

    public int getQuantidadeCategorias() {
        return QuantidadeCategorias;
    }

    public void setQuantidadeCategorias(int QuantidadeCategorias) {
        this.QuantidadeCategorias = QuantidadeCategorias;
    }
    

    public int getTotalTemporadas() {
        return TotalTemporadas;
    }

    public void setTotalTemporadas(int TotalTemporadas) {
        this.TotalTemporadas = TotalTemporadas;
    }

    public int getTotalEpisodios() {
        return TotalEpisodios;
    }

    public void setTotalEpisodios(int TotalEpisodios) {
        this.TotalEpisodios = TotalEpisodios;
    }

    public float getNotaSerie() {
        return NotaSerie;
    }

    public void setNotaSerie(float NotaSerie) {
        this.NotaSerie = NotaSerie;
    }

    public String[][] getNomeEpisodio() {
        return NomeEpisodio;
    }

    public void setNomeEpisodio(String[][] NomeEpisodio) {
        this.NomeEpisodio = NomeEpisodio;
    }

    public String[][] getReleaseEpisodio() {
        return ReleaseEpisodio;
    }

    public void setReleaseEpisodio(String[][] ReleaseEpisodio) {
        this.ReleaseEpisodio = ReleaseEpisodio;
    }

    public float[][] getNotaEpisodio() {
        return NotaEpisodio;
    }

    public void setNotaEpisodio(float[][] NotaEpisodio) {
        this.NotaEpisodio = NotaEpisodio;
    }

    public int getDuracaoEpisodio() {
        return DuracaoEpisodio;
    }

    public void setDuracaoEpisodio(int DuracaoEpisodio) {
        this.DuracaoEpisodio = DuracaoEpisodio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
}
