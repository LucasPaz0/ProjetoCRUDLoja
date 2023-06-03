import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Mercadoria {
    private String nome;
    private int quantidade;
    private double valor;

    public Mercadoria(String nome, int quantidade, double valor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

class Compra {
    private String nomeMercadoria;
    private int quantidade;
    private double valorTotal;

    public Compra(String nomeMercadoria, int quantidade, double valorTotal) {
        this.nomeMercadoria = nomeMercadoria;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public String getNomeMercadoria() {
        return nomeMercadoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

public class LojaDeRoupas123 {
    private Map<String, Mercadoria> estoque;
    private List<Compra> historicoCompras;
    private double ReceitaTotal;

    public LojaDeRoupas123() {
        estoque = new HashMap<>();
        historicoCompras = new ArrayList<>();
        ReceitaTotal = 0.0;
    }

    public void adicionarMercadoria(String nome, int quantidade, double valor) {
        Mercadoria mercadoria = new Mercadoria(nome, quantidade, valor);
        estoque.put(nome, mercadoria);
    }

    public void removerMercadoria(String nome) {
        estoque.remove(nome);
    }

    public void exibirEstoque() {
        System.out.println("Estoque:");
        for (Mercadoria mercadoria : estoque.values()) {
            System.out.println("Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor: " + mercadoria.getValor());
        }
        System.out.println();
    }

    public void realizarCompra(String nomeMercadoria, int quantidade) {
        Mercadoria mercadoria = estoque.get(nomeMercadoria);
        if (mercadoria == null) {
            System.out.println("Mercadoria não encontrada no estoque.");
            return;
        }

        if (quantidade > mercadoria.getQuantidade()) {
            System.out.println("Quantidade indisponível no estoque.");
            return;
        }

        double valorTotal = quantidade * mercadoria.getValor();
        Compra compra = new Compra(nomeMercadoria, quantidade, valorTotal);
        historicoCompras.add(compra);
        mercadoria.setQuantidade(mercadoria.getQuantidade() - quantidade);
        ReceitaTotal += valorTotal;

        System.out.println("Compra realizada com sucesso.");
        System.out.println("Valor total: " + valorTotal);
        System.out.println();
    }

    public void exibirHistoricoCompras() {
        System.out.println("Histórico de Compras:");
        for (Compra compra : historicoCompras) {
          System.out.println("Nome: " + compra.getNomeMercadoria() + ", Quantidade: " + compra.getQuantidade() + ", Valor Total: " + compra.getValorTotal());
        }
        System.out.println("Receita Total: " + ReceitaTotal);
        System.out.println();
    }

    public static void main(String[] args) {
        LojaDeRoupas123 loja = new LojaDeRoupas123();
        try (Scanner scanner = new Scanner(System.in)) {
          while (true) {
              System.out.println("Selecione uma opção:");
              System.out.println("1. Adicionar mercadoria");
              System.out.println("2. Remover mercadoria");
              System.out.println("3. Exibir estoque");
              System.out.println("4. Realizar compra");
              System.out.println("5. Exibir histórico de compras");
              System.out.println("0. Sair");

              int opcao = scanner.nextInt();
              scanner.nextLine(); 

              switch (opcao) {
                  case 1:
                      System.out.println("Digite o nome da mercadoria:");
                      String nome = scanner.nextLine();
                      System.out.println("Digite a quantidade da mercadoria:");
                      int quantidade = scanner.nextInt();
                      System.out.println("Digite o valor da mercadoria:");
                      double valor = scanner.nextDouble();
                      loja.adicionarMercadoria(nome, quantidade, valor);
                      break;
                  case 2:
                      System.out.println("Digite o nome da mercadoria a ser removida:");
                      nome = scanner.nextLine();
                      loja.removerMercadoria(nome);
                      break;
                  case 3:
                      loja.exibirEstoque();
                      break;
                  case 4:
                      System.out.println("Selecione uma mercadoria para comprar:");
                      int opcaoCompra = 1;
                      int contador = 1;
                      for (Mercadoria mercadoria : loja.estoque.values()) {
                          System.out.println(contador + ". Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor: " + mercadoria.getValor());
                          contador++;
                      }
                      opcaoCompra = scanner.nextInt();
                      scanner.nextLine(); 

                      if (opcaoCompra < 1 || opcaoCompra > loja.estoque.size()) {
                          System.out.println("Opção inválida.");
                          break;
                      }

                      Mercadoria mercadoriaSelecionada = (Mercadoria) loja.estoque.values().toArray()[opcaoCompra - 1];

                      System.out.println("Digite a quantidade da mercadoria a ser comprada:");
                      quantidade = scanner.nextInt();
                      scanner.nextLine(); 

                      loja.realizarCompra(mercadoriaSelecionada.getNome(), quantidade);
                      break;
                  case 5:
                      loja.exibirHistoricoCompras();
                      break;
                  case 0:
                      System.exit(0);
                      break;
                  default:
                      System.out.println("Opção inválida.");
                      break;
              }
          }
        }
    }
}
