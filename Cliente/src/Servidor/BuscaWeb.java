package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public final class BuscaWeb {

    public BuscaWeb(String BuscaNomeSerie, int PaginaBusca) throws IOException, ClassNotFoundException {
        this.NomeSerie = BuscaNomeSerie;
        this.PaginaBusca = PaginaBusca;
        BuscaGeral();
    }
    
    public void BuscaWeb() throws IOException, ClassNotFoundException {
        
    }

    private String NomeSerie;
    private final String[] NomesSeriesBusca = new String[11]; //11 é a limitação de resultados = 10 resultados
    private final String[] AnosBusca = new String[11];
    private final int[] AnoInicio = new int[11];
    private final int[] AnoFim = new int[11];
    private final String[] UrlPosterSerie = new String[11];
    
    public int PaginaBusca;
    private int NumeroTotalResultados;
    private boolean SemResultados = false;

    private String getBuscaNomeSerie() {
        return NomeSerie;
    }

    public String[] getNomesSeriesBusca() {
        return NomesSeriesBusca;
    }

    public String[] getAnosSeriesBusca() {
        return AnosBusca;
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

    public void BuscaGeral() throws IOException, ClassNotFoundException {
        try {
            NumeroTotalResultados = 0;
            //REALIZA E CAPTA OS DADOS DE BUSCA PARA O USUÁRIO
            BotaPorcento BotaPorcento = new BotaPorcento();
            NomeSerie = BotaPorcento.BotaPorcento(getBuscaNomeSerie());
            URL url = new URL("http://www.omdbapi.com/?s=" + NomeSerie + "&type=series&page=" + PaginaBusca);
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
                    NomesSeriesBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("\"") + 1, ResultadoSplitado[x].indexOf("\","));
                    //Separa o ano da série
                    AnosBusca[x] = ResultadoSplitado[x].substring(ResultadoSplitado[x].indexOf("Year\":\"") + 7, ResultadoSplitado[x].indexOf("\",\"imdbID\":\""));
                    //o - que separa as datas n é um - normal e mesmo utilizando o original estava dando bug no .split
                    //por isso tivemos de fazer essa gambiarra com o .substring;
                    if (AnosBusca[x].length() == 4) {                      // 2005
                        AnoInicio[x] = Integer.parseInt(AnosBusca[x]);
                        AnoFim[x] = 0;
                    } else if (AnosBusca[x].length() == 5) {                //2005-
                        AnoInicio[x] = Integer.parseInt(AnosBusca[x].substring(0, 4));
                        AnoFim[x] = 1;
                    } else {                                              //2005-2010
                        AnoInicio[x] = Integer.parseInt(AnosBusca[x].substring(0, 4));
                        AnoFim[x] = Integer.parseInt(AnosBusca[x].substring(5, 9));
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
}
