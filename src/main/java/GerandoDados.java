import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GerandoDados {

    private String nome;
    private String link;
    private int valor;
    private  int desconto;


    public GerandoDados(String nome, String link, int valor, int desconto){
    this.nome = nome;
    this.link = link;
    this.valor = valor;
    this.desconto = desconto;

    }

    public void criandoNoBanco() {
     Conexao conexao = new Conexao();
     try {
             PreparedStatement psl = conexao.conn.prepareStatement("INSERT INTO produtosML (nome, link, preco, desconto) VALUES (?,?,?,?)");
             psl.setString(1, nome);
             psl.setString(2, link);
             psl.setInt(3, valor);
             psl.setInt(4, desconto);
             psl.executeUpdate();




     }catch (SQLException e){
         e.printStackTrace();
     }




    }


}
