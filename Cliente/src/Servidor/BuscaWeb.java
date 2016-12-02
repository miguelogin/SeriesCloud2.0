package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import jdk.nashorn.internal.parser.TokenType;

public class BuscaWeb {

    public BuscaWeb(String BuscaNomeSerie, int PaginaBusca) throws IOException, ClassNotFoundException {
        this.BuscaNomeSerie = BuscaNomeSerie;
        this.PaginaBusca = PaginaBusca;
        BuscaGeral();
    }

    private String BuscaNomeSerie;
    private String[] NomeBusca = new String[11]; //11 é a limitação de resultados = 10 resultados
    private String[] AnoBusca = new String[11];
    private String[] PosterBusca = new String[11];
    private int NumeroTotalResultados;
    public int PaginaBusca;
    private boolean LimitePesquisa = false;

    private String getBuscaNomeSerie() {
        return BuscaNomeSerie;
    }

    private void setBuscaNomeSerie(String BuscaNomeSerie) {
        this.BuscaNomeSerie = BuscaNomeSerie;
    }

    public String[] getNomeBusca() {
        return NomeBusca;
    }

    private void setNomeBusca(String[] NomeBusca) {
        this.NomeBusca = NomeBusca;
    }

    public String[] getAnoBusca() {
        return AnoBusca;
    }

    private void setAnoBusca(String[] AnoBusca) {
        this.AnoBusca = AnoBusca;
    }

    public String[] getPosterBusca() {
        return PosterBusca;
    }

    private void setPosterBusca(String[] PosterBusca) {
        this.PosterBusca = PosterBusca;
    }

    public boolean isLimitePesquisa() {
        return LimitePesquisa;
    }

    public void setLimitePesquisa(boolean LimitePesquisa) {
        this.LimitePesquisa = LimitePesquisa;
    }

    public int getNumeroTotalResultados() {
        return NumeroTotalResultados;
    }

    private void setNumeroTotalResultados(int NumeroTotalResultados) {
        this.NumeroTotalResultados = NumeroTotalResultados;
    }
    
    public int getPaginaBusca() {
        return PaginaBusca;
    }

    public void setPaginaBusca(int PaginaBusca) {
        this.PaginaBusca = PaginaBusca;
    }

    //http://www.omdbapi.com/?t=Doctor%20Who&y=2005&Season=1&episode=1
    public void BuscaGeral() throws IOException, ClassNotFoundException {
        NumeroTotalResultados = 0;
        String[] Anular = {" "};
        String TermoBusca = getBuscaNomeSerie();
        System.out.println(TermoBusca);
        if (getBuscaNomeSerie().contains(" ")) {
            for (String n : Anular) {
                TermoBusca = TermoBusca.replaceAll(n, "%20");
            }
        } else {
            TermoBusca = getBuscaNomeSerie();
        }
        //REALIZA E CAPTA OS DADOS DE BUSCA PARA O USUÁRIO
        String DadosRetornoSite;
        URL url = new URL("http://www.omdbapi.com/?s=" + TermoBusca + "&type=series&page="+PaginaBusca);
        System.out.println("URL = " + url);
        URLConnection spoof = url.openConnection();
        //Engana o site para pareçamos um navegador
        spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
        //Percorre todas as linha do site e salva em uma String
        DadosRetornoSite = in.readLine();
        String[] ResultadoSplitado = DadosRetornoSite.split("\"Title\":");
        String[] AnoSplitado;
        try {
            for (int x = 1; x <= 10; x++) {
                //Separa o nome da série
                NomeBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"") + 1, ResultadoSplitado[x].indexOf("\","));
                //Separa o ano da série
                AnoBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("Year\":\"") + 7, ResultadoSplitado[x].indexOf("\",\"imdbID\":\""));
                //Separa o URL do poster
                PosterBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"Poster\":\"") + 10, ResultadoSplitado[x].indexOf("\"}"));
                NumeroTotalResultados++;
            }
            DadosSerie DadosSerie = new DadosSerie(NomeBusca, AnoBusca, PosterBusca);
        } catch (Exception e) { //se cair nesse catch quer dizer que a pesquisa não retorna mais de 10 resultados
            LimitePesquisa = true;
        }
    }

    public static void BuscaGeral(String TermoBusca) throws IOException {
        //REALIZA E CAPTA OS DADOS DE BUSCA PARA O USUÁRIO
        String ResultText;
        URL url = new URL("http://www.omdbapi.com/?s=" + TermoBusca + "&type=series");
        URLConnection spoof = url.openConnection();
        //Spoof the connection so we look like a web browser
        spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
        //Loop through every line in the source
        ResultText = in.readLine();
        try {
            String[] ResultadoSplitado = ResultText.split("\"Title\":");
            String[] Nome = new String[11];
            String[] Ano = new String[11];
            String[] Poster = new String[11];
            for (int x = 1; x <= 11; x++) {
                //Separa o nome da série
                Nome[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"") + 1, ResultadoSplitado[x].indexOf("\","));
                //Separa o ano da série
                Ano[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("Year\":\"") + 7, ResultadoSplitado[x].indexOf("\",\"imdbID\":\""));
                //Separa o URL do poster
                Poster[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"Poster\":\"") + 10, ResultadoSplitado[x].indexOf("\"}"));
                System.out.println(Nome[x] + " | " + Ano[x] + " | " + Poster[x]);
            }
        } catch (Exception e) {
        }
    }

    public static void BuscaEspecifica(String TermoBusca, int Ano) throws IOException, ClassNotFoundException {
        //REALIZA E CAPTA OS DADOS DE UMA SÉRIES ESPECÍFICA PARA O USUÁRIO
        String ResultadoSerie;
        URL URLSerie = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&y=" + Ano + "&plot=short&r");
        URLConnection spoofSerie = URLSerie.openConnection();
        //Spoof the connection so we look like a web browser
        spoofSerie.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader inSerie = new BufferedReader(new InputStreamReader(spoofSerie.getInputStream()));
        //Loop through every line in the source
        ResultadoSerie = inSerie.readLine();

        //Tempo de Duração em minutos (fazer x nº de episódios x por temporada nº de temporadas 
        String Duracao = ResultadoSerie.substring(ResultadoSerie.indexOf("\"Runtime\":\"") + 11, ResultadoSerie.indexOf(" min\","));
        System.out.println(Duracao);

        //Categoria
        String Categorias = ResultadoSerie.substring(ResultadoSerie.indexOf("Genre\":\"") + 8, ResultadoSerie.indexOf("\",\"Director"));
        String[] Categoria = Categorias.split(", ");
        System.out.println(Categoria[0]);
        System.out.println(Categoria[1]);

        //Ano de início e fim da série
        String AnoInicioSerie = ResultadoSerie.substring(ResultadoSerie.indexOf("\"Year\":\"") + 8, ResultadoSerie.indexOf("–"));
        System.err.println("INÍCIO DA SÉRIE = " + AnoInicioSerie);
        String AnoFimSerie = ResultadoSerie.substring(ResultadoSerie.indexOf("–") + 1, ResultadoSerie.indexOf("\",\"Rated"));
        System.err.println("FIM DA SÉRIE = " + AnoFimSerie);

        //Sinopse
        String Sinopse = ResultadoSerie.substring(ResultadoSerie.indexOf("\"Plot\":\"") + 8, ResultadoSerie.indexOf("\",\"Language"));
        System.out.println(Sinopse);

        //Avaliacao
        String IMDBRating = ResultadoSerie.substring(ResultadoSerie.indexOf("\"imdbRating\":\"") + 14, ResultadoSerie.indexOf("\",\"imdbVotes"));
        System.out.println(IMDBRating);

        //Temporadas
        String Temporadas = ResultadoSerie.substring(ResultadoSerie.indexOf("\"totalSeasons\":\"") + 16, ResultadoSerie.indexOf("\",\"Response"));
        System.out.println(Temporadas);

        System.out.println("!! INICIANDO CAPTURA DOS DADOS DOS EPISÓDIOS !!");
        String[] ResultadoSplitado;
        int TotalEpisodios = 0;
        //CAPTURA DADOS DOS EPISÓDIOS
        int MaiorNumeroEpisodiosPorTemporada = 0;
        for (int xTemp = 1; xTemp <= Integer.parseInt(Temporadas); xTemp++) {
            URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&y=" + Ano + "&Season=" + xTemp);
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
        }

        String[][] NomeEpisodio = new String[Integer.parseInt(Temporadas + 1)][MaiorNumeroEpisodiosPorTemporada + 1];
        String[][] Release = new String[Integer.parseInt(Temporadas + 1)][MaiorNumeroEpisodiosPorTemporada + 1];
        String[][] IMDBRatingEpisodio = new String[Integer.parseInt(Temporadas + 1)][MaiorNumeroEpisodiosPorTemporada + 1];

        for (int xTemp = 1; xTemp <= Integer.parseInt(Temporadas); xTemp++) {
            URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&y=" + Ano + "&Season=" + xTemp);
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
            for (int xEp = 1; xEp <= Integer.parseInt(TotalEpisodiosTemporada); xEp++) {
                NomeEpisodio[xTemp][xEp] = ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"") + 1, ResultadoSplitado[xEp + 1].indexOf("\",\"Released\":"));
                Release[xTemp][xEp] = ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"Released\":\"") + 12, ResultadoSplitado[xEp + 1].indexOf("\",\"Episode\""));
                IMDBRatingEpisodio[xTemp][xEp] = ResultadoSplitado[xEp + 1].substring(ResultadoSplitado[xEp + 1].indexOf("\"imdbRating\":\"") + 14, ResultadoSplitado[xEp + 1].indexOf("\",\"imdbID\""));
                System.out.println("Temporada [" + xTemp + "] Episódio [" + xEp + "] " + NomeEpisodio[xTemp][xEp] + " | Exibido em: " + Release[xTemp][xEp] + " | Nota = " + IMDBRatingEpisodio[xTemp][xEp]);
            }
        }
        System.out.println("sai?" + NomeEpisodio[1][1]);
        /*DadosSerie d = new DadosSerie(NomeEpisodio);
         d.setNomeEpisodio(NomeEpisodio);
         Socket cliente = new Socket("127.0.0.1", 12345);
         System.out.println("O cliente se conectou ao servidor!");
         ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(cliente.getOutputStream());
         ObjectOutputStream.writeObject(d);
         ObjectOutputStream.close();
         cliente.close();
         //http://stackoverflow.com/questions/19217420/sending-an-object-through-a-socket-in-java
         //http://www.java2s.com/Code/Java/Network-Protocol/ServerSocketandSocketforSerializableobject.htm
         //d.setNomeEpisodio("hahaha");
         */
    }
}
