import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadProdutosMercadoLivre {


    void LoadProdutosMercadoLivre(){
        Document doc;
        try {

            doc = Jsoup.connect("https://www.mercadolivre.com.br/ofertas#deal_print_id=682649b0-5a08-11eb-9110-59226e3cd79e").get();
            Elements carousel = doc.getElementsByClass("items_container");

            //Aqui vamos percorrer todos os dados da nossa div items_container
            for (Element dados : carousel){
                // aqui vamos pegar todos os valores que estão dentro do span, iremos passar eles por cada element para que assim nosso sistema dê 1 preço por vez para pormos em uma lista.
                Elements precos = dados.select(".promotion-item__price > span");
                for (Element nomes : precos){
                    Integer valores = Integer.valueOf(nomes.text().replace("R$", "").replace(" ", "").replace(".",""));
                    Listas listas = new Listas(String.valueOf(valores));
                    listas.adicionarPrecos();
                }
                // aqui iremos pegar os elementos com a, depois que possuem href, e trabalharam dentro da promotion-item.
                Elements links = doc.select("a");
                for (Element link : links) {
                    String attribute=link.attr("class");
                    if(attribute.equalsIgnoreCase("promotion-item__link-container")){
                        Listas listas = new Listas(link.attr(("href")));
                        listas.adicionarLink();
                    }
                }
                // aqui vamos pegar os possiveis produtos que possuem desconto, e adiciona-los em uma lista.
                Elements desconto = dados.select(".promotion-item__price-additional-info > span");
                for (Element desc : desconto){
                    Integer descfinal = Integer.valueOf(desc.text().replace("%", "").replace("OFF", "").replace(" ", ""));
                    Listas listas = new Listas(String.valueOf(descfinal));
                    listas.adicionarDescontos();
                }
                Elements nomes = dados.select(".promotion-item__title");
                for (Element nome : nomes){
                    String todos = nome.text();
                    Listas listas = new Listas(todos);
                    listas.adicionarNome();
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
