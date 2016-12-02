package Servidor;

import java.io.Serializable;
//CLIENTE
public class DadosSerie implements Serializable{ 
    
    public DadosSerie(String NomeSerie, int AnoInicio, int AnoFim, String Sinopse, int Generos, int TotalTemporadas, int TotalEpisodios, float NotaSerie, String PosterSerie, String[][] NomeEpisodio, String[][] ReleaseEpisodio, float[][] NotaEpisodio, int[][] DuracaoEpisodio, String PosterEpisodio) {
        this.NomeSerie = NomeSerie;
        this.AnoInicio = AnoInicio;
        this.AnoFim = AnoFim;
        this.Sinopse = Sinopse;
        this.Generos = Generos;
        this.TotalTemporadas = TotalTemporadas;
        this.TotalEpisodios = TotalEpisodios;
        this.NotaSerie = NotaSerie;
        this.PosterSerie = PosterSerie;
        this.NomeEpisodio = NomeEpisodio;
        this.ReleaseEpisodio = ReleaseEpisodio;
        this.NotaEpisodio = NotaEpisodio;
        this.DuracaoEpisodio = DuracaoEpisodio;
        this.PosterEpisodio = PosterEpisodio;
    }

    public DadosSerie(String[] NomeBusca, String[] AnoBusca, String[] PosterBusca) {
        this.NomeBusca = NomeBusca;
        this.AnoBusca = AnoBusca;
        this.PosterBusca = PosterBusca;
    }

    private String[] NomeBusca;
    private String[] AnoBusca;
    private String[] PosterBusca;
    private String NomeSerie;
    private int AnoInicio;
    private int AnoFim;
    private String Sinopse;
    private int Generos;
    private int TotalTemporadas;
    private int TotalEpisodios;
    private float NotaSerie;
    private String PosterSerie;
    private String[][] NomeEpisodio;
    private String[][] ReleaseEpisodio;
    private float [][] NotaEpisodio;
    private int [][] DuracaoEpisodio;
    private String PosterEpisodio;

    public String[] getNomeBusca() {
        return NomeBusca;
    }

    public void setNomeBusca(String[] NomeBusca) {
        this.NomeBusca = NomeBusca;
    }

    public String[] getAnoBusca() {
        return AnoBusca;
    }

    public void setAnoBusca(String[] AnoBusca) {
        this.AnoBusca = AnoBusca;
    }

    public String[] getPosterBusca() {
        return PosterBusca;
    }

    public void setPosterBusca(String[] PosterBusca) {
        this.PosterBusca = PosterBusca;
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

    public int getGeneros() {
        return Generos;
    }

    public void setGeneros(int Generos) {
        this.Generos = Generos;
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

    public String getPosterSerie() {
        return PosterSerie;
    }

    public void setPosterSerie(String PosterSerie) {
        this.PosterSerie = PosterSerie;
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

    public int[][] getDuracaoEpisodio() {
        return DuracaoEpisodio;
    }

    public void setDuracaoEpisodio(int[][] DuracaoEpisodio) {
        this.DuracaoEpisodio = DuracaoEpisodio;
    }

    public String getPosterEpisodio() {
        return PosterEpisodio;
    }

    public void setPosterEpisodio(String PosterEpisodio) {
        this.PosterEpisodio = PosterEpisodio;
    }
}
