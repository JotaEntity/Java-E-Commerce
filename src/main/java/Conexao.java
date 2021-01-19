import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
  // aqui é feita nossa conexão do banco de dados.
    private String url;
    private String usuario;
    private String senha;
    public  Connection conn;

    Conexao(){
      url = "url";
      usuario = "user";
      senha = "senha";

      try {
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection(url, usuario, senha);
      } catch (Exception e){
          e.printStackTrace();
      }




    }
}
