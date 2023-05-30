import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Essa classe representa uma mercadoria da loja, contendo atributos como nome, quantidade e valor.

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

// Essa classe representa uma compra realizada na loja, contendo informações como nome da mercadoria, quantidade e valor total da compra.

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

// Essa é a classe principal que gerencia a loja de roupas. Possui um mapa chamado "estoque" para armazenar as mercadorias disponíveis, onde a chave é o nome da mercadoria e o valor é uma instância da classe "Mercadoria". 
// Tem uma lista chamada "historicoCompras" para armazenar as compras realizadas, utilizando a classe Compra. Possui um atributo "lucroTotal" para acompanhar o lucro acumulado da loja.
// Contém métodos como "adicionarMercadoria", "removerMercadoria", "exibirEstoque", "realizarCompra" e "exibirHistoricoCompra"s para realizar diversas operações relacionadas à loja.

public class LojaDeRoupas123 {
    private Map<String, Mercadoria> estoque;
    private List<Compra> historicoCompras;
    private double lucroTotal;

    public LojaDeRoupas123() {
        estoque = new HashMap<>();
        historicoCompras = new ArrayList<>();
        lucroTotal = 0.0;
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

    // Essa classe é responsável por realizar uma compra na loja. Verifica se a mercadoria está disponível no estoque e se há quantidade suficiente para a compra, calcula o valor total da compra, cria uma instância da classe "Compra" e a adiciona ao histórico de compras e atualiza a quantidade da mercadoria no estoque e adiciona o valor total da compra ao lucro total da loja.

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
        lucroTotal += valorTotal;

        System.out.println("Compra realizada com sucesso.");
        System.out.println("Valor total: " + valorTotal);
        System.out.println();
    }

    // Essa classe exibe o histórico de compras realizado na loja, incluindo o nome da mercadoria, a quantidade e o valor total de cada compra. Utiliza o mapa "estoque" para obter o nome da mercadoria com base no nome armazenado em "Compra" e no final, exibe o lucro total acumulado pela loja. 

    public void exibirHistoricoCompras() {
        System.out.println("Histórico de Compras:");
        for (Compra compra : historicoCompras) {
            System.out.println("Nome: " + compra.getNomeMercadoria() + ", Quantidade: " + compra.getQuantidade() + ", Valor Total: " + compra.getValorTotal());
        }
        System.out.println("Lucro Total: " + lucroTotal);
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
                        System.out.println("Selecione as mercadorias para comprar (digite 0 para finalizar):");
                        int opcaoCompra = 1;
                        int contador = 1;
                        List<Mercadoria> mercadoriasSelecionadas = new ArrayList<>();

                        for (Mercadoria mercadoria : loja.estoque.values()) {
                            System.out.println(contador + ". Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor: " + mercadoria.getValor());
                            contador++;
                        }

                        while (true) {
                            opcaoCompra = scanner.nextInt();
                            scanner.nextLine();

                            if (opcaoCompra == 0) {
                                break;
                            }

                            if (opcaoCompra < 1 || opcaoCompra > loja.estoque.size()) {
                                System.out.println("Opção inválida.");
                                continue;
                            }

                            Mercadoria mercadoriaSelecionada = (Mercadoria) loja.estoque.values().toArray()[opcaoCompra - 1];
                            System.out.println("Digite a quantidade da mercadoria " + mercadoriaSelecionada.getNome() + " a ser comprada:");
                            quantidade = scanner.nextInt();
                            scanner.nextLine();

                            if (quantidade <= 0) {
                                System.out.println("Quantidade inválida. A compra será ignorada.");
                                continue;
                            }

                            if (quantidade > mercadoriaSelecionada.getQuantidade()) {
                                System.out.println("Quantidade indisponível no estoque. A compra será ignorada.");
                                continue;
                            }

                            mercadoriaSelecionada.setQuantidade(mercadoriaSelecionada.getQuantidade() - quantidade);
                            mercadoriasSelecionadas.add(new Mercadoria(mercadoriaSelecionada.getNome(), quantidade, mercadoriaSelecionada.getValor()));
                        }

                        if (mercadoriasSelecionadas.isEmpty()) {
                            System.out.println("Nenhuma compra realizada.");
                            break;
                        }

                        double valorTotal = 0.0;

                        System.out.println("Resumo da compra:");
                        for (Mercadoria mercadoria : mercadoriasSelecionadas) {
                            double valorItem = mercadoria.getQuantidade() * mercadoria.getValor();
                            valorTotal += valorItem;
                            System.out.println("Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor Total: " + valorItem);
                        }

                        loja.historicoCompras.add(new Compra("Compra Múltipla", 1, valorTotal));
                        loja.lucroTotal += valorTotal;

                        System.out.println("Valor total da compra: " + valorTotal);
                        System.out.println("Compra realizada com sucesso.");
                        System.out.println();
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
