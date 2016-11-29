

import java.io.Serializable;

public class DadosSerie implements Serializable{

    public DadosSerie(String NomeEpisodio[][]) {
        this.NomeEpisodio = NomeEpisodio;
    }

    private String[][] NomeEpisodio;
    private String[][] Release;
    private String[][] NotaEpisodio;

    public String[][] getNomeEpisodio() {
        return NomeEpisodio;
    }

    public void setNomeEpisodio(String NomeEpisodio[][]) {
        this.NomeEpisodio = NomeEpisodio;
    }
}
