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
    
    public void InserirSerie(DadosSerie p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        stAcoesnoBanco.executeUpdate("INSERT INTO serie (`DT_INICIO_TEMP`, `DT_FIM_TEMP`, `TEMPORADA`, `NOME`, ``) VALUES ('"+p.getNomeEpisodio()[1][1]+"')");
    }
    
    public void InserirEpisodio(DadosSerie p) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        stAcoesnoBanco.executeUpdate("INSERT INTO episodio (`NOME_EP`) VALUES ('"+p.getNomeEpisodio()[1][1]+"')");
    }
    
    /*
    public ResultSet ConsultarPessoa() throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rsInserenoBanco = stAcoesnoBanco.executeQuery("SELECT * FROM pessoa");
        return rsInserenoBanco;
    }

    public ResultSet ConsultarEpisodio(String Episodio) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rsInserenoBanco = stAcoesnoBanco.executeQuery("SELECT * FROM epsiodio WHERE NOME_EP LIKE '" + Episodio + "%'");
        return rsInserenoBanco;
    }

    public ResultSet ConsultarComMedico() throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rsInserenoBanco = stAcoesnoBanco.executeQuery("SELECT medico FROM consulta INNER JOIN pessoa WHERE consulta.cod_consulta=pessoa.cod_consulta ORDER BY pessoa.pessoa_id");
        return rsInserenoBanco;
    }

    public ResultSet ConsultarConsulta() throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rsInserenoBanco = stAcoesnoBanco.executeQuery("SELECT * FROM consulta");
        return rsInserenoBanco;
    }

    public ResultSet ConsultarConsulta(String nome) throws SQLException {
        ConectaBanco Banco = new ConectaBanco();
        Statement stAcoesnoBanco = Banco.Conecta.createStatement();
        ResultSet rsInserenoBanco = stAcoesnoBanco.executeQuery("SELECT * FROM consulta WHERE medico LIKE '" + nome + "%'");
        return rsInserenoBanco;
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
