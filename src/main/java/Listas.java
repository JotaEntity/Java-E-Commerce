import java.util.ArrayList;
import java.util.List;

public class Listas {

      // aqui pra não estender muitas classes, decidi criar uma classe só para as listas.

    private static List<String> nomes = new ArrayList<>();
    private static List<String> links = new ArrayList<>();
    private static List<Integer> precos = new ArrayList<>();
    private static List<Integer> descontos = new ArrayList<>();
    private String dados;
    public Listas(String dados){
        this.dados = dados;
    }

    public void adicionarNome(){
        nomes.add(dados);
    }
    public void adicionarLink(){
        links.add(dados);
    }
    public void adicionarPrecos(){
        precos.add(Integer.valueOf(dados));
    }
    public void adicionarDescontos(){
        descontos.add(Integer.valueOf(dados));
    }

    public List<Integer> getDescontos() {
        return descontos;
    }

    public List<Integer> getPrecos() {
        return precos;
    }

    public List<String> getLinks() {
        return links;
    }

    public List<String> getNomes() {
        return nomes;
    }
}
