
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

/* @author Miguel */
public class IMDB {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int ano;
        Scanner e = new Scanner(System.in);
        String TermoBusca;
        String[] anular = {" "};
        System.out.print("Informe o nome da série que deseja procurar > ");
        TermoBusca = e.nextLine();

        if (TermoBusca.contains(" ")) {
            for (String n : anular) {
                TermoBusca = TermoBusca.replaceAll(n, "%20");
            }
        }

        BuscaGeral(TermoBusca);
        System.err.print("Qual dessas séries você deseja começa a assistir > ");
        TermoBusca = e.nextLine();

        if (TermoBusca.contains(" ")) {

            for (String n : anular) {
                TermoBusca = TermoBusca.replaceAll(n, "%20");
            }
        }

        System.err.print("Informe o ano da série > ");
        ano = e.nextInt();

        BuscaEspecifica(TermoBusca, ano);
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
        DadosSerie d = new DadosSerie(NomeEpisodio);
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
    }
}
