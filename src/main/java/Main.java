import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    public static void main(String[] args) {
        //Aqui é gerado todas as classes, sendo feito os loads dos produtos do Mercado Livre.
          LoadProdutosMercadoLivre loadProdutosMercadoLivre = new LoadProdutosMercadoLivre();
          loadProdutosMercadoLivre.LoadProdutosMercadoLivre();
          Listas listas = new Listas(null);
           for (int j = 0; listas.getNomes().size() > j; j++){
            String nome = listas.getNomes().get(j);
            String link = listas.getLinks().get(j);
            int preco = listas.getPrecos().get(j);
            int desconto;
            if (j >= listas.getDescontos().size()){
                desconto = 0;
            } else {
                desconto = listas.getDescontos().get(j);
            }
            // Aqui a gente vai pegar os dados gerados e vai enviar ao nosso banco de dados.
            GerandoDados gerandoDados = new GerandoDados(nome, link, preco, desconto);
            gerandoDados.criandoNoBanco();
        }

        //Aqui iremos pegar as informações solicitadas, menor preço e maior desconto.
        System.out.println("Informações filtradas:");


        System.out.println("");
        Conexao conexao = new Conexao();
        try {
            System.out.println("--------------------------------------------");
            System.out.println("Produto mais barato:");
            Statement statment = conexao.conn.createStatement();
            String sql = "SELECT Min(preco) from produtosML";
            ResultSet rs = statment.executeQuery(sql);
            if (rs.next()) {
                int coluna = listas.getPrecos().indexOf(rs.getInt(1));
                // eu utilizei listas, para não ter que puxar tudo do banco de dados, fazendo isso sobrecarregaria menos nosso banco.
                System.out.println("Nome do Produto: " + listas.getNomes().get(coluna));
                System.out.println("Link do Produto: " + listas.getLinks().get(coluna));
                System.out.println("Preço do Produto: " + listas.getPrecos().get(coluna));
                System.out.println("Desconto do Produto: " + listas.getDescontos().get(coluna));

            }
            System.out.println("");
            System.out.println("");
            System.out.println("--------------------------------------------");
            System.out.println("Produto com maior desconto:");
            String sql2 = "SELECT Max(desconto) from produtosML";
            ResultSet rs2 = statment.executeQuery(sql2);
            if (rs2.next()) {
                int coluna = listas.getDescontos().indexOf(rs2.getInt(1));
                System.out.println("Nome do Produto: " + listas.getNomes().get(coluna));
                System.out.println("Link do Produto: " + listas.getLinks().get(coluna));
                System.out.println("Preço do Produto: " + listas.getPrecos().get(coluna));
                System.out.println("Desconto do Produto: " + listas.getDescontos().get(coluna));
                System.out.println("--------------------------------------------");
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

          }


    }
