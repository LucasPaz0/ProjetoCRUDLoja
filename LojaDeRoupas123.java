import java.util.ArrayList;
import java.util.List;
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
    private Mercadoria mercadoria;
    private int quantidade;
    private double valorTotal;

    public Compra(Mercadoria mercadoria, int quantidade, double valorTotal) {
        this.mercadoria = mercadoria;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}

public class LojaDeRoupas123 {
    private List<Mercadoria> estoque;
    private List<Compra> historicoCompras;
    private double receitaTotal;

    public LojaDeRoupas123() {
        estoque = new ArrayList<>();
        historicoCompras = new ArrayList<>();
        receitaTotal = 0.0;
    }

    public void adicionarMercadoria(String nome, int quantidade, double valor) {
        Mercadoria mercadoria = new Mercadoria(nome, quantidade, valor);
        estoque.add(mercadoria);
    }

    public int realizarCompra(int indiceMercadoria, int quantidade) {
        if (indiceMercadoria >= 0 && indiceMercadoria < estoque.size()) {
            Mercadoria mercadoria = estoque.get(indiceMercadoria);
            if (quantidade <= mercadoria.getQuantidade()) {
                double valorTotal = quantidade * mercadoria.getValor();
                Compra compra = new Compra(mercadoria, quantidade, valorTotal);
                historicoCompras.add(compra);
                mercadoria.setQuantidade(mercadoria.getQuantidade() - quantidade);
                receitaTotal += valorTotal;
                return 0;   
            } else {
                return -2; 
            }
        } else {
            return -1; 
        }
    }

    public static void main(String[] args) {
        LojaDeRoupas123 loja = new LojaDeRoupas123();
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println("Digite o índice da mercadoria a ser removida:");
                    int indice = scanner.nextInt();
                    scanner.nextLine();

                    if (indice >= 0 && indice < loja.estoque.size()) {
                        loja.estoque.remove(indice);
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 3:
                    System.out.println("Estoque:");
                    for (int i = 0; i < loja.estoque.size(); i++) {
                        Mercadoria mercadoria = loja.estoque.get(i);
                        System.out.println("Índice: " + i + ", Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor: " + mercadoria.getValor());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Selecione o índice da mercadoria para comprar:");
                    System.out.println("Estoque:");
                    for (int i = 0; i < loja.estoque.size(); i++) {
                        Mercadoria mercadoria = loja.estoque.get(i);
                        System.out.println("Índice: " + i + ", Nome: " + mercadoria.getNome() + ", Quantidade: " + mercadoria.getQuantidade() + ", Valor: " + mercadoria.getValor());
                    }
                    int indiceCompra = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Digite a quantidade da mercadoria a ser comprada:");
                    int quantidadeCompra = scanner.nextInt();
                    scanner.nextLine();

                    int resultadoCompra = loja.realizarCompra(indiceCompra, quantidadeCompra);
                    if (resultadoCompra == -1) {
                        System.out.println("Índice de mercadoria inválido.");
                    } else if (resultadoCompra == -2) {
                        System.out.println("Quantidade indisponível no estoque.");
                    } else {
                        System.out.println("Compra realizada com sucesso.");
                        System.out.println("Valor total: " + loja.historicoCompras.get(loja.historicoCompras.size() - 1).getValorTotal());
                    }
                    break;
                case 5:
                    System.out.println("Histórico de Compras:");
                    for (Compra compra : loja.historicoCompras) {
                        Mercadoria mercadoria = compra.getMercadoria();
                        System.out.println("Nome: " + mercadoria.getNome() + ", Quantidade: " + compra.getQuantidade() + ", Valor Total: " + compra.getValorTotal());
                    }
                    System.out.println("Receita Total: " + loja.receitaTotal);
                    System.out.println();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}