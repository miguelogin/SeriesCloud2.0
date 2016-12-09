package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BuscaWebEspecifica {

    private BotaPorcento BotaPorcento = null;
    private String TermoBusca;
    private String NomeSerie;
    private int QuatidadeCategorias;
    private String[][] UrlPosterEpisodio;
    private int DuracaoEpisodio;
    private String[] Categoria;
    private String SinopseSerie;
    private float NotaSerie;
    private int TotalTemporadas;
    private int TotalEpisodios;
    private int WatchTimeDias;
    private int WatchTimeHoras;
    private int MaiorNumeroEpisodiosPorTemporada;
    private String[] TotalEpisodiosTemporada;
    private String[][] NomeEpisodio;
    private String[][] ReleaseDate;
    private float[][] NotaEpisodio;

    public String getNomeSerie() {
        return NomeSerie;
    }

    public int getQuatidadeCategorias() {
        return QuatidadeCategorias;
    }

    public String[] getCategoria() {
        return Categoria;
    }

    public int getTotalEpisodios() {
        return TotalEpisodios;
    }

    public int getWatchTimeDias() {
        return WatchTimeDias;
    }

    public int getWatchTimeHoras() {
        return WatchTimeHoras;
    }

    public int getTotalTemporadas() {
        return TotalTemporadas;
    }

    public String getSinopseSerie() {
        return SinopseSerie;
    }

    public float getNotaSerie() {
        return NotaSerie;
    }

    public String[][] getNomeEpisodio() {
        return NomeEpisodio;
    }

    public int getDuracaoEpisodio() {
        return DuracaoEpisodio;
    }

    public String[][] getReleaseDate() {
        return ReleaseDate;
    }

    public float[][] getNotaEpisodio() {
        return NotaEpisodio;
    }

    public String[] getTotalEpisodiosTemporada() {
        return TotalEpisodiosTemporada;
    }

    public void BuscaWebEspecifica(String NomeSerie, int Ano, int posicao, String ipServidor, String ip) throws IOException, ClassNotFoundException {
        this.NomeSerie = NomeSerie;
        BotaPorcento BotaPorcento = new BotaPorcento();
        NomeSerie = BotaPorcento.BotaPorcento(NomeSerie);

        URL URLSerie = new URL("http://www.omdbapi.com/?t=" + NomeSerie + "&type=series&y=" + Ano);
        System.out.println("BUSCA DE DADOS DE " + NomeSerie + " = " + URLSerie);
        URLConnection spoofSerie = URLSerie.openConnection();
        //Spoof the connection so we look like a web browser
        spoofSerie.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader in = new BufferedReader(new InputStreamReader(spoofSerie.getInputStream()));
        //Loop through every line in the source
        String ResultadoSerie = in.readLine();
        String[] ResultadoSplitado = ResultadoSerie.split("\"Title\":");

        //Categoria
        String Categorias = ResultadoSerie.substring(ResultadoSerie.indexOf("Genre\":\"") + 8, ResultadoSerie.indexOf("\",\"Director"));
        Categoria = Categorias.split(", ");
        QuatidadeCategorias = 0;
        try {
            for (int x = 0; x <= 1; x++) {
                System.out.println(Categoria[x]);
                QuatidadeCategorias = QuatidadeCategorias + 1;
            }
        } catch (Exception e) {
        }
        System.out.println("QuatidadeCategorias = " + QuatidadeCategorias);

        //Sinopse
        SinopseSerie = ResultadoSerie.substring(ResultadoSerie.indexOf("\"Plot\":\"") + 8, ResultadoSerie.indexOf("\",\"Language"));
        System.out.println(SinopseSerie);

        //Avaliacao
        NotaSerie = Float.parseFloat(ResultadoSerie.substring(ResultadoSerie.indexOf("\"imdbRating\":\"") + 14, ResultadoSerie.indexOf("\",\"imdbVotes")));
        System.out.println(NotaSerie);

        //Temporadas
        TotalTemporadas = Integer.parseInt(ResultadoSerie.substring(ResultadoSerie.indexOf("\"totalSeasons\":\"") + 16, ResultadoSerie.indexOf("\",\"Response")));
        System.out.println(TotalTemporadas);

        //Tempo de Duração em minutos (fazer x nº de episódios x por temporada nº de temporadas 
        try {
            DuracaoEpisodio = Integer.parseInt(ResultadoSerie.substring(ResultadoSerie.indexOf("\"Runtime\":\"") + 11, ResultadoSerie.indexOf(" min\",")));
        } catch (Exception e) {
            DuracaoEpisodio = 1;
        }
        System.out.println(DuracaoEpisodio);

        ////////////////capta alguma tradução de sinopse se existir
        try {
            BuscaTraducao(NomeSerie, Ano);
        } catch (Exception e) { /// SE CAIR NESSE CATCH NÃO HÁ TRADUÇÃO PARA A SINOPSE
            System.err.println("Não pude encontrar uma tradução para a sinopse deste série");
        }

        System.err.println("! INICIANDO A CAPTURA DE DADOS DOS EPISÓDIOS! ");
        //////////////////////////////////CAPTURA DADOS DOS EPISÓDIOS////////////////////////////////////////////////
        TotalEpisodios = 0;
        MaiorNumeroEpisodiosPorTemporada = 0;
        ///////////PEGA ALGUNS DADOS COMO O MAIOR NUMERO DE EPISÓDIOS POR TEMPORADA PRA DAIR PODER COMEÇAR A ARMAZENAR OS EPISÓDIOS EM UM ARRAY
        for (int xTemp = 1; xTemp <= TotalTemporadas; xTemp++) {
            try {
                URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + NomeSerie + "&type=series&y=" + Ano + "&Season=" + xTemp);
                if (xTemp == 1) {
                    System.out.println("BUSCA EPISÓDIOS DE " + NomeSerie + " - " + URLEpisodio);
                }
                URLConnection spoofEpisodio = URLEpisodio.openConnection();
                //Spoof the connection so we look like a web browser
                spoofEpisodio.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
                BufferedReader inEpisodio = new BufferedReader(new InputStreamReader(spoofEpisodio.getInputStream()));
                //Loop through every line in the source
                String ResultadoEpisodio = inEpisodio.readLine();
                ResultadoSplitado = ResultadoEpisodio.split("\"Title\":");
                //Procura o total de episodios da Temporada
                String TotalEpisodiosTemporada = ResultadoEpisodio.substring(ResultadoEpisodio.lastIndexOf("Episode\":\"") + 10, ResultadoEpisodio.lastIndexOf("\",\"imdbRating"));
                TotalEpisodios = TotalEpisodios + Integer.parseInt(TotalEpisodiosTemporada);
                if (Integer.parseInt(TotalEpisodiosTemporada) > MaiorNumeroEpisodiosPorTemporada) {
                    MaiorNumeroEpisodiosPorTemporada = Integer.parseInt(TotalEpisodiosTemporada);
                }
            } catch (Exception e) {
                System.out.println("ih carai n achei o ep");
            }
        }

        WatchTimeDias = (((TotalEpisodios * DuracaoEpisodio/*totalminutos*/) / 60/*horas*/) / 24/*dias*/);
        WatchTimeHoras = (((TotalEpisodios * DuracaoEpisodio/*totalminutos*/) / 60/*horas*/) - (WatchTimeDias * 24));
        System.err.println(WatchTimeDias + " dias " + WatchTimeHoras + " horas para concluir");

        NomeEpisodio = new String[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        ReleaseDate = new String[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        NotaEpisodio = new float[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        TotalEpisodiosTemporada = new String[MaiorNumeroEpisodiosPorTemporada];

        for (int xTemp = 1; xTemp <= TotalTemporadas; xTemp++) {//for das temporadas
            try {
                URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + NomeSerie + "&y=" + Ano + "&Season=" + xTemp);
                URLConnection spoofEpisodio = URLEpisodio.openConnection();
                //Spoof the connection so we look like a web browser
                spoofEpisodio.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
                BufferedReader inEpisodio = new BufferedReader(new InputStreamReader(spoofEpisodio.getInputStream()));
                //Loop through every line in the source
                String ResultadoEpisodio = inEpisodio.readLine();
                ResultadoSplitado = ResultadoEpisodio.split("\"Title\":");
                //Procura o total de episodios da Temporada
                TotalEpisodiosTemporada[xTemp] = ResultadoEpisodio.substring(ResultadoEpisodio.lastIndexOf("Episode\":\"") + 10, ResultadoEpisodio.lastIndexOf("\",\"imdbRating"));
                TotalEpisodios = TotalEpisodios + Integer.parseInt(TotalEpisodiosTemporada[xTemp]);
                if (Integer.parseInt(TotalEpisodiosTemporada[xTemp]) > MaiorNumeroEpisodiosPorTemporada) {
                    MaiorNumeroEpisodiosPorTemporada = Integer.parseInt(TotalEpisodiosTemporada[xTemp]);
                }
                try {
                    for (int xEp = 1; xEp <= Integer.parseInt(TotalEpisodiosTemporada[xTemp]); xEp++) {//for dos episodios
                        NomeEpisodio[xTemp][xEp] = ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"") + 1, ResultadoSplitado[xEp + 1].indexOf("\",\"Released\":"));
                        ReleaseDate[xTemp][xEp] = ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"Released\":\"") + 12, ResultadoSplitado[xEp + 1].indexOf("\",\"Episode\""));
                        NotaEpisodio[xTemp][xEp] = Float.parseFloat(ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"imdbRating\":\"") + 14, ResultadoSplitado[xEp + 1].indexOf("\",\"imdbID\"")));
                        System.out.println("Temporada [" + xTemp + "] Episódio [" + xEp + "] " + NomeEpisodio[xTemp][xEp] + " | Exibido em: " + ReleaseDate[xTemp][xEp] + " | Nota = " + NotaEpisodio[xTemp][xEp]);
                    }
                } catch (Exception e) {
                    //SE CHEGAR A CAIR AQUI É POR ERRO NO BANCO DE DADOS DO OMDB TIPO TEM APENAS O EPISÓDIO 1 E 5 REGISTRADOS (FALTANDO 2,3,4);
                    //O NOSSO PROGRAMA VAI IGNORAR E CONTINUAR, SENDO ASSIM VAI ARMAZER APENAS OS EPISÓDIOS 1 E 5, SENDO O EPISÓDIO 1 O 1ª 
                    //EPISÓDIO DA TEMPORADA E O 5 O 2º
                }
            } catch (Exception e) {
                System.out.println("Temporada não registrada no OPEN API !!!");
            }
        }
    }

    public void BuscaTraducao(String NomeBusca, int Ano) throws IOException {
        /////////////////////PESQUISA SINOPSE TRADUZIDA 
        int id = 57243;
        URL URLSerieTraduzida = new URL("https://api.themoviedb.org/3/search/tv?api_key=5544cda46810347ff08bf66491167824&language=pt-BR&query=" + NomeBusca + "&page=1&first_air_date_year=" + Ano);
        System.out.println("BUSCA DE DADOS DE " + NomeBusca + " TRADUZIDOS = " + URLSerieTraduzida);
        URLConnection spoofSerieTraduzida = URLSerieTraduzida.openConnection();
        //Spoof the connection so we look like a web browser
        spoofSerieTraduzida.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader inTraduziada = new BufferedReader(new InputStreamReader(spoofSerieTraduzida.getInputStream()));
        //Loop through every line in the source
        String ResultadoSerie = inTraduziada.readLine();
        SinopseSerie = ResultadoSerie.substring(ResultadoSerie.indexOf("overview\":\"") + 11, ResultadoSerie.indexOf("\",\"first_air_date"));
        System.err.println(SinopseSerie);
        id = Integer.parseInt(ResultadoSerie.substring(ResultadoSerie.indexOf("\"id\":") + 5, ResultadoSerie.indexOf(",\"backdrop_path")));
        System.err.println("ID=" + id);
    }
}
