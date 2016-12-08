package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectaBanco {

    String serverName = "localhost";
    String mydatabase = "seriescloud";
    String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
    String username = "root";
    String password = "";

    Connection Conecta;

    ConectaBanco() throws SQLException {
        Conecta = DriverManager.getConnection(url, username, password);
    }

    public int VerificaUsuario(DadosUsuario p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM `usuario` WHERE `LOGIN` LIKE '" + p.getLogin() + "' AND `SENHA` LIKE '" + p.getSenha() + "'");
        int UsuarioId = 0;
        while (rs.next()) {
            UsuarioId = Integer.parseInt(rs.getString("tipo"));
            if (UsuarioId == 1) {
                UsuarioId = 1;
            } else {
                UsuarioId = 0;
            }
            p.setTipo(UsuarioId);
        }
        return UsuarioId;
    }

    public void CadastraUsuario(DadosCadastro p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        stAcoesnoBanco.executeUpdate("INSERT INTO `usuario` (`NOME_USER`, `LOGIN`, `SENHA`, `EMAIL`, `NASCIMENTO`, `TIPO`) VALUES (NULL, '" + p.getNome() + "', '" + p.getLogin() + "', '" + p.getSenha() + "', '" + p.getEmail() + "', '" + p.getNascimento() + "', '2');");
    }

    public String VerificaCadastro(DadosCadastro p) throws SQLException {
        String verificados = "false#false";
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM `usuario` WHERE `LOGIN` LIKE '" + p.getLogin() + "'");
        String ResultNome;
        if (rs.next()) {
            ResultNome = rs.getString("login");
            System.out.println("O login informado NÃO esta livre");
        } else {
            ResultNome = "";
            System.out.println("O login informado esta livre");
        }
        ResultSet rss = stAcoesnoBanco.executeQuery(("SELECT * FROM `usuario` WHERE `EMAIL` LIKE '" + p.getEmail() + "'"));
        String ResultEmail;
        if (rss.next()) {
            ResultEmail = rss.getString("email");
            System.out.println("O email informado NÃO esta livre");
        } else {
            ResultEmail = "";
            System.out.println("O email informado esta livre");
        }
        if (ResultNome.equals(p.getLogin()) && ResultEmail.equals(p.getEmail())) {
            verificados = "tem#tem";
        } else if ((!ResultNome.equals(p.getLogin()) && !ResultEmail.equals(p.getEmail()))) {
            verificados = "ntem#ntem";
        } else if (ResultNome.equals(p.getLogin())) {
            verificados = "tem#ntem";                       //login#senha
        } else if (ResultEmail.equals(p.getEmail())) {
            verificados = "ntem#tem";
        }

        return verificados;
    }

    public String WatchTime(WatchTime p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM `usuario` WHERE `LOGIN` LIKE '" + p.getLogin() + "'");
        System.out.println("rss");
        rs.next();
        int UsuarioId = rs.getInt("id_usuario");
        System.err.println("UsuarioID = " + UsuarioId);
        rs = stAcoesnoBanco.executeQuery("SELECT * FROM `ep_user` WHERE `ID_USER_FK` LIKE '" + UsuarioId + "'");
        int Duracao = 0;
        String WatchTime = "0#0";
        //pegar total de resultados retornados
        rs.last();
        int linhas = rs.getRow();
        System.err.println("LINHAS = " + linhas);
        rs.beforeFirst();
        int[] IdEp = new int[linhas];
        int contador = 0;
        while (rs.next()) {
            IdEp[contador] = rs.getInt("id_ep_fk");
            contador++;
        }

        ResultSet rss;
        for (int x = 0; x < linhas; x++) {
            System.err.println("EPISODIO ID = " + IdEp[x]);
            rss = stAcoesnoBanco.executeQuery("SELECT * FROM `episodio` WHERE `ID_EP` LIKE '" + IdEp[x] + "'");
            rss.next();
            int SerieID = rss.getInt("id_serie_fk");
            System.err.println("SERIE ID = " + SerieID);
            rss = stAcoesnoBanco.executeQuery("SELECT * FROM `serie` WHERE `id_serie` LIKE '" + SerieID + "'");
            rss.next();
            Duracao = Duracao + rss.getInt("duracao");
            System.out.println(Duracao);
        }
        System.err.println("Duração total = " + Duracao);
        int WatchTimeDias = ((Duracao / 60/*horas*/) / 24/*dias*/);
        int WatchTimeHoras = ((Duracao / 60/*horas*/) - (WatchTimeDias * 24));
        WatchTime = WatchTimeDias + "#" + WatchTimeHoras;
        System.out.println("WatchTime = " + WatchTime);
        return WatchTime;
    }

    public void InserirSerie(DadosSerie p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        stAcoesnoBanco.executeUpdate("INSERT INTO `serie` (`ID_SERIE`, `NOME`, `CATEGORIA`, `N_TEMPORADAS`, `N_EPISODIOS`, `DT_INICIO`, `DT_FIM`, `SINOPSE`) VALUES (NULL, '" + p.getNomeSerie() + "', '" + p.getCategoria() + "', '" + p.getTotalTemporadas() + "', '" + p.getTotalEpisodios() + "', '" + p.getAnoInicio() + "', '" + p.getAnoFim() + "', '" + p.getSinopse() + "');");
    }

    public void InserirEpisodio(DadosSerie p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        stAcoesnoBanco.executeUpdate("INSERT INTO episodio (`ID_Serie_FK`, `NOME_EP`, `N_EP`) VALUES ('10','" + p.getNomeEpisodio()[1][1] + "','1')");
    }

    /*
     public ResultSet ConsultarEpisodio(String Episodio) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM epsiodio WHERE NOME_EP LIKE '" + Episodio + "%'");
     return rs;
     }

     public ResultSet ConsultarComMedico() throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     ResultSet rs = stAcoesnoBanco.executeQuery("SELECT medico FROM consulta INNER JOIN pessoa WHERE consulta.cod_consulta=pessoa.cod_consulta ORDER BY pessoa.pessoa_id");
     return rs;
     }

     public ResultSet ConsultarConsulta() throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM consulta");
     return rs;
     }

     public ResultSet ConsultarConsulta(String nome) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     ResultSet rs = stAcoesnoBanco.executeQuery("SELECT * FROM consulta WHERE medico LIKE '" + nome + "%'");
     return rs;
     }

     public void InserirConsulta(Pessoa p) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     stAcoesnoBanco.executeUpdate("INSERT INTO consulta (`medico`) VALUES ('" + p.nome + "')");

     }

     public void AtualizaDados(Pessoa p) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     stAcoesnoBanco.executeUpdate("UPDATE pessoa SET nome='" + p.nome + "',idade=" + p.idade + ",cod_consulta=" + p.cod_consulta + " WHERE pessoa_id=" + p.id);
     }

     public void ExcluirPessoa(Pessoa p) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     stAcoesnoBanco.executeUpdate("DELETE FROM pessoa WHERE pessoa_id=" + p.id);
     }

     public void ExcluirConsulta(Pessoa p) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     stAcoesnoBanco.executeUpdate("DELETE FROM consulta WHERE cod_consulta=" + p.id);
     }

     public void AtualizaConsulta(Pessoa p) throws SQLException {
     ConectaBanco Banco = new ConectaBanco();
     Statement stAcoesnoBanco = Banco.Conecta.createStatement();
     stAcoesnoBanco.executeUpdate("UPDATE consulta SET medico='" + p.nome + "' WHERE cod_consulta=" + p.id);
     }
     */
}
