package Servidor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;

public class BuscaWeb {

    public BuscaWeb(String BuscaNomeSerie, int PaginaBusca) throws IOException, ClassNotFoundException {
        this.BuscaNomeSerie = BuscaNomeSerie;
        this.PaginaBusca = PaginaBusca;
        BuscaGeral();
    }

    private String BuscaNomeSerie;
    private String[] NomeBusca = new String[11]; //11 é a limitação de resultados = 10 resultados
    private String[] AnoBusca = new String[11];
    private String TermoBusca;

    private int NumeroTotalResultados;
    public int PaginaBusca;
    private boolean SemResultados = false;
    private int QuatidadeCategorias;
    private int[] AnoInicio = new int[11];
    private int[] AnoFim = new int[11];
    private String[] UrlPosterSerie = new String[11];
    private String[][] UrlPosterEpisodio;
    private int DuracaoEpisodio;
    private String[] Categoria;
    private String SinopseSerie;
    private float NotaSerie;
    private int TotalTemporadas;
    private int TotalEpisodios;
    private int WatchTimeDias;
    private int WatchTimeHoras;
    private Socket servidor = new Socket("127.0.0.1", 12345);
    ObjectOutputStream ObjectOutputStream;
    
    private int MaiorNumeroEpisodiosPorTemporada;

    private int id;
    private String[] TotalEpisodiosTemporada;

    public int getWatchTimeMinutos() {
        return WatchTimeDias;
    }

    private String[][] NomeEpisodio;
    private String[][] ReleaseDate;
    private float[][] NotaEpisodio;

    private String getBuscaNomeSerie() {
        return BuscaNomeSerie;
    }

    public String[] getNomeBusca() {
        return NomeBusca;
    }

    public String[] getAnoBusca() {
        return AnoBusca;
    }

    public String[] getPosterSerie() {
        return UrlPosterSerie;
    }

    public boolean isSemResultados() {
        return SemResultados;
    }

    public int getNumeroTotalResultados() {
        return NumeroTotalResultados;
    }

    public int getPaginaBusca() {
        return PaginaBusca;
    }

    public int[] getAnoInicio() {
        return AnoInicio;
    }

    public int[] getAnoFim() {
        return AnoFim;
    }

    public void BotaPorcento(String NomeBusca) {
        String[] Anular = {" "};
        if (NomeBusca.contains(" ")) {
            for (String n : Anular) {
                TermoBusca = NomeBusca.replaceAll(n, "%20");
            }
        } else {
            TermoBusca = NomeBusca;
        }

        System.err.println(TermoBusca);
    }

    //http://www.omdbapi.com/?t=Doctor%20Who&y=2005&Season=1&episode=1
    public void BuscaGeral() throws IOException, ClassNotFoundException {
        try {
            NumeroTotalResultados = 0;
            //REALIZA E CAPTA OS DADOS DE BUSCA PARA O USUÁRIO
            BotaPorcento(getBuscaNomeSerie());
            URL url = new URL("http://www.omdbapi.com/?s=" + TermoBusca + "&type=series&page=" + PaginaBusca);
            System.out.println("BUSCA SÉRIES = " + url);
            URLConnection spoof = url.openConnection();
            //Engana o site para pareçamos um navegador
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
            BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            //Percorre todas as linha do site e salva em uma String
            String DadosRetornoSite = in.readLine();
            String[] ResultadoSplitado = DadosRetornoSite.split("\"Title\":");
            try {
                for (int x = 1; x <= 10; x++) {
                    //Separa o nome da série
                    NomeBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"") + 1, ResultadoSplitado[x].indexOf("\","));
                    //Separa o ano da série
                    AnoBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("Year\":\"") + 7, ResultadoSplitado[x].indexOf("\",\"imdbID\":\""));
                    //o - que separa as datas n é um - normal e mesmo utilizando o original estava dando bug no .split
                    //por isso tivemos de fazer essa gambiarra com o .substring;
                    if (AnoBusca[x].length() == 4) {                      // 2005
                        AnoInicio[x] = Integer.parseInt(AnoBusca[x]);
                        AnoFim[x] = 0;
                    } else if (AnoBusca[x].length() == 5) {                //2005-
                        AnoInicio[x] = Integer.parseInt(AnoBusca[x].substring(0, 4));
                        AnoFim[x] = 0;
                    } else {                                              //2005-2010
                        AnoInicio[x] = Integer.parseInt(AnoBusca[x].substring(0, 4));
                        AnoFim[x] = Integer.parseInt(AnoBusca[x].substring(5, 9));
                    }
                    //Separa o URL do poster
                    UrlPosterSerie[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"Poster\":\"") + 10, ResultadoSplitado[x].indexOf("\"}"));
                    NumeroTotalResultados++;
                }
            } catch (Exception e) { //se cair nesse catch quer dizer que a pesquisa não retorna mais de 10 resultados
                if (NumeroTotalResultados == 0) {
                    SemResultados = true;
                }
            }
        } catch (IOException e) {
            System.err.println("OPEN IMDB API OFFLINE");
        }
    }

    public void BuscaSerieEspecifica(String NomeBusca, int Ano, int posicaoTabela) throws IOException, ClassNotFoundException {
        BotaPorcento(NomeBusca);
        String ResultadoSerie;
        QuatidadeCategorias = 0;
        URL URLSerie = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&type=series&y=" + Ano);
        System.out.println("BUSCA DE DADOS DE " + NomeBusca + " = " + URLSerie);
        URLConnection spoofSerie = URLSerie.openConnection();
        //Spoof the connection so we look like a web browser
        spoofSerie.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
        BufferedReader in = new BufferedReader(new InputStreamReader(spoofSerie.getInputStream()));
        //Loop through every line in the source
        ResultadoSerie = in.readLine();
        String[] ResultadoSplitado = ResultadoSerie.split("\"Title\":");

        //Categoria
        String Categorias = ResultadoSerie.substring(ResultadoSerie.indexOf("Genre\":\"") + 8, ResultadoSerie.indexOf("\",\"Director"));
        Categoria = Categorias.split(", ");
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
        DuracaoEpisodio = Integer.parseInt(ResultadoSerie.substring(ResultadoSerie.indexOf("\"Runtime\":\"") + 11, ResultadoSerie.indexOf(" min\",")));
        System.out.println(DuracaoEpisodio);

        ////////////////caputa algumas tradução se existirem
        try {
            BuscaTraducao(NomeBusca, Ano);
        } catch (Exception e) { /// SE CAIR NESSE CATCH NÃO HÁ TRADUÇÃO PARA A SINOPSE
            System.err.println("Não pude encontrar uma tradução para a sinopse deste série");
        }

        System.err.println("! INICIANDO A CAPTURA DE DADOS DOS EPISÓDIOS! ");
        //////////////////////////////////CAPTURA DADOS DOS EPISÓDIOS////////////////////////////////////////////////
        TotalEpisodios = 0;
        MaiorNumeroEpisodiosPorTemporada = 0;
        ///////////PEGA ALGUNS DADOS COMO O MAIOR NUMERO DE EPISÓDIOS POR TEMPORADA PRA DAIR PODER COMEÇAR A ARMAZENAR OS EPISÓDIOS EM UM ARRAY
        for (int xTemp = 1; xTemp <= TotalTemporadas; xTemp++) {
            try{
                URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&type=series&y=" + Ano + "&Season=" + xTemp);
            if (xTemp == 1) {
                System.out.println("BUSCA EPISÓDIOS DE " + NomeBusca + " - " + URLEpisodio);
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
            }catch(Exception e){
                System.out.println("ih carai n achei o ep");
            }
        }
        
////////////////////////////////////////////////////////////////////////////
        DadosSerie VerificaExistencia = new DadosSerie(true, NomeBusca, TotalEpisodios, TotalTemporadas);
        System.out.println("O cliente se conectou ao servidor! Verificando existência da série");
        ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
        ObjectOutputStream.writeObject(VerificaExistencia);
        ObjectOutputStream.close();
        servidor.close();
        //ao retortnar
        VerificaExistencia.setVerificaExistencia(false);
/////////////////////////////////////////////////////////
        WatchTimeDias = (((TotalEpisodios * DuracaoEpisodio/*totalminutos*/) / 60/*horas*/) / 24/*dias*/);
        WatchTimeHoras = (((TotalEpisodios * DuracaoEpisodio/*totalminutos*/) / 60/*horas*/) - (WatchTimeDias * 24));
        System.err.println(WatchTimeDias + " dias " + WatchTimeHoras + " horas para concluir");

        NomeEpisodio = new String[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        ReleaseDate = new String[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        NotaEpisodio = new float[TotalTemporadas + 1][MaiorNumeroEpisodiosPorTemporada + 1];
        TotalEpisodiosTemporada = new String[MaiorNumeroEpisodiosPorTemporada];
        for (int xTemp = 1; xTemp <= TotalTemporadas; xTemp++) {//for das temporadas
            try{
            URL URLEpisodio = new URL("http://www.omdbapi.com/?t=" + TermoBusca + "&y=" + Ano + "&Season=" + xTemp);
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
                //SE CHEGAR A ENTRAR AQUI É POR ERRO NO BANCO DE DADOS DO OMDB TIPO TEM APENAS O EPISÓDIO 1 E 5 REGISTRADOS (FALTANDO 2,3,4);
                //O NOSSO PROGRAMA VAI ARMAZER ENTÃO APENAS OS EPISÓDIOS 1 E 5, SENDO O EPISÓDIO 1 O 1ª EPISÓDIO DA TEMPORADA E O 5 O 2º
            }
            }catch(Exception e){
                System.out.println("Esse OPEN API é uma merda, faltando as temporada tudo!!!");
            }

            ///////////////////////////POSTER EPISODIO URLS
            UrlPosterEpisodio = new String[TotalTemporadas][MaiorNumeroEpisodiosPorTemporada];

        }
        DadosSerie d = new DadosSerie(NomeBusca, AnoInicio[posicaoTabela-1], AnoFim[posicaoTabela-1], SinopseSerie, Categoria[0], TotalTemporadas, TotalEpisodios, NotaSerie, NomeEpisodio, ReleaseDate, NotaEpisodio, DuracaoEpisodio);
        servidor = new Socket("127.0.0.1", 12345);
        System.out.println("O cliente se conectou ao servidor!");
        ObjectOutputStream = new ObjectOutputStream(servidor.getOutputStream());
        ObjectOutputStream.writeObject(d);
        ObjectOutputStream.close();
        servidor.close();
    }

    public void DadosTemporarios(String NomeBusca, int PosicaoTabela) {
        try {
            File arquivoBackupPesquisa = new File("src/DadosTemporariosSerie.txt");
            FileWriter arquivoWriter = new FileWriter(arquivoBackupPesquisa, true);
            PrintWriter escrever = new PrintWriter(arquivoWriter);
            escrever.println(NomeBusca);
            escrever.println("Categoria" + Categoria[0]);
            escrever.println("AnoInicio" + AnoInicio[PosicaoTabela]);
            escrever.println(AnoFim[PosicaoTabela]);
            escrever.println(TotalEpisodios);
            escrever.println(WatchTimeDias);
            escrever.println(WatchTimeHoras);
            escrever.println(TotalTemporadas);
            escrever.println(SinopseSerie);
            escrever.println(NotaSerie);
            escrever.println(NomeEpisodio);
            escrever.println();
            escrever.println();
            escrever.println();
            arquivoWriter.close();
            BufferedReader leitor_buffer = new BufferedReader(new FileReader("src/backupPesquisa.txt"));
            while (leitor_buffer.ready()) {
                String linha = leitor_buffer.readLine(); // lê até a última linha
            }
            leitor_buffer.close();
        } catch (Exception ex) {
        }
    }

    public void BuscaTraducao(String NomeBusca, int Ano) throws IOException {
        /////////////////////PESQUISA SINOPSE TRADUZIDA 
        id = 57243;
        BotaPorcento(NomeBusca);
        URL URLSerieTraduzida = new URL("https://api.themoviedb.org/3/search/tv?api_key=5544cda46810347ff08bf66491167824&language=pt-BR&query=" + TermoBusca + "&page=1&first_air_date_year=" + Ano);
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

    public void EnviarDadosSerie() {
        /*  System.out.println("sai?" + NomeEpisodio[1][1]);
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
         //d.setNomeEpisodio("hahaha");*/
    }
}
