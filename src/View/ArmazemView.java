package View;

import Model.Armazenavel;
import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;
import Model.Jogador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArmazemView {

    private final Scanner scanner = new Scanner(System.in);

    public void exibirMensagemBoasVindas(Jogador jogador) {
        System.out.printf("""
╔══════════════════════════════════════════════╗
║               ARMAZÉM DO PIERRE              ║
╚══════════════════════════════════════════════╝

Olá, %s!

Bem-vindo(a) ao Armazém do Pierre.
Aqui você encontrará sementes de qualidade para todas as estações do ano.

Planeje suas colheitas com cuidado e transforme sua fazenda em um verdadeiro sucesso!


""", jogador.getNome());
    }

    private String centralizar(String texto, int largura) {
        if (texto.length() >= largura) return texto;
        int espacos = largura - texto.length();
        int esquerda = espacos / 2;
        return " ".repeat(esquerda) + texto + " ".repeat(espacos - esquerda);
    }

    /**
     * Exibe o catálogo de sementes disponíveis para compra.
     *
     * @param lista Lista de tipos de planta a exibir
     */
    public void exibirLoja(List<TipoPlanta> lista) {
        String topo  = "╔════╦════════════════╦══════════════════╦════════╦════════╗";
        String linha = "╠════╬════════════════╬══════════════════╬════════╬════════╣";
        String base  = "╚════╩════════════════╩══════════════════╩════════╩════════╝";

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                         CATÁLOGO                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println(topo);
        System.out.printf("║%s║%s║%s║%s║%s║%n",
                centralizar("#", 4), centralizar("Nome", 16),
                centralizar("Estação", 18), centralizar("Compra", 8), centralizar("Venda", 8));
        System.out.println(linha);

        int numero = 1;
        for (TipoPlanta planta : lista) {
            StringBuilder estacoes = new StringBuilder();
            boolean primeiro = true;
            for (Estacoes e : planta.getEstacao()) {
                if (!primeiro) estacoes.append("/");
                estacoes.append(e.name().toLowerCase());
                primeiro = false;
            }
            System.out.printf("║%s║%s║%s║%s║%s║%n",
                    centralizar(String.valueOf(numero), 4),
                    centralizar(planta.getNome(), 16),
                    centralizar(estacoes.toString(), 18),
                    centralizar("G$" + planta.getPrecoCompra(), 8),
                    centralizar("G$" + planta.getPrecoVenda(), 8));
            numero++;
        }
        System.out.println(base);
    }

    /**
     * Exibe o estoque de plantas colhidas disponíveis para venda.
     *
     * @param itens   Itens do estoque
     * @param estoque Lista completa de itens no estoque
     * @param saldo   Saldo atual do jogador
     */
    public void exibirEstoqueParaVenda(List<Armazenavel> itens, List<Armazenavel> estoque, int saldo) {

        String topo  = "╔════╦════════════════╦══════════╦════════╗";
        String linha = "╠════╬════════════════╬══════════╬════════╣";
        String base  = "╚════╩════════════════╩══════════╩════════╝";

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║           ITENS PARA VENDER              ║");
        System.out.println("╚══════════════════════════════════════════╝");

        System.out.println(topo);

        System.out.printf("║%s║%s║%s║%s║%n",
                centralizar("#", 4),
                centralizar("Nome", 16),
                centralizar("Qtd", 10),
                centralizar("Venda", 8));

        System.out.println(linha);

        List<Armazenavel> itensUnicos = estoque.stream()
                .collect(java.util.stream.Collectors.toMap(
                        Armazenavel::getNome,
                        item -> item,
                        (a, b) -> a
                ))
                .values()
                .stream()
                .toList();

        int numero = 1;

        for (Armazenavel itemBase : itensUnicos) {

            long quantidade = estoque.stream()
                    .filter(item -> item.getNome().equals(itemBase.getNome()))
                    .count();

            if (quantidade == 0) continue;

            System.out.printf("║%s║%s║%s║%s║%n",
                    centralizar(String.valueOf(numero), 4),
                    centralizar(itemBase.getNome(), 16),
                    centralizar(String.valueOf(quantidade), 10),
                    centralizar("G$" + itemBase.getValorVenda(), 8));

            numero++;
        }

        System.out.println(base);
        System.out.printf("Saldo atual: G$%d%n", saldo);
        System.out.println("Digite 0 para cancelar.");
    }
    public void exibirLojaNumerada(List<TipoPlanta> lista, int saldo) {
        exibirLoja(lista);
        System.out.printf("%nSeu saldo atual: G$%d%n", saldo);
        System.out.println("Digite 0 para cancelar.");
    }

    /**
     * Lê e valida a opção digitada pelo jogador.
     *
     * @param totalOpcoes Número total de opções disponíveis
     * @return Opção escolhida (1 a totalOpcoes) ou 0 para cancelar
     */
    public int lerOpcaoPlanta(int totalOpcoes) {
        int opcao = -1;
        while (opcao < 0 || opcao > totalOpcoes) {
            System.out.printf("Escolha uma opcao (1-%d) ou 0 para cancelar: ", totalOpcoes);
            try {
                opcao = scanner.nextInt();
                if (opcao < 0 || opcao > totalOpcoes)
                    System.out.println("Opcao invalida! Tente novamente.");
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }
        return opcao;
    }

    /**
     * Lê a quantidade de sementes que o jogador deseja comprar.
     *
     * @param planta Planta selecionada
     * @param saldo  Saldo atual do jogador
     * @return Quantidade desejada ou 0 para cancelar
     */
    public int lerQuantidade(TipoPlanta planta, int saldo) {
        int maxPossivel = saldo / planta.getPrecoCompra();

        System.out.printf("%n--- %s ---%n", planta.getNome());
        System.out.printf("Preco por unidade : G$%d%n", planta.getPrecoCompra());
        System.out.printf("Seu saldo         : G$%d%n", saldo);
        System.out.printf("Maximo possivel   : %d unidade(s)%n", maxPossivel);

        if (maxPossivel == 0) {
            System.out.println("Saldo insuficiente para comprar esta planta.");
            return 0;
        }

        int quantidade = -1;
        while (quantidade < 0 || quantidade > maxPossivel) {
            System.out.printf("Quantas deseja comprar? (1-%d) ou 0 para cancelar: ", maxPossivel);
            try {
                quantidade = scanner.nextInt();
                if (quantidade < 0 || quantidade > maxPossivel)
                    System.out.printf("Valor invalido! Digite entre 1 e %d, ou 0 para cancelar.%n", maxPossivel);
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }
        return quantidade;
    }

    /**
     * Lê a quantidade de plantas que o jogador deseja vender.
     *
     * @param item               item selecionado
     * @param quantidadeDisponivel Quantidade disponível no estoque
     * @return Quantidade desejada ou 0 para cancelar
     */
    public int lerQuantidadeVenda(Armazenavel item, int quantidadeDisponivel) {

        System.out.printf("%n--- %s ---%n", item.getNome());
        System.out.printf("Preco de venda por unidade: G$%d%n", item.getValorVenda());
        System.out.printf("Disponivel no estoque     : %d unidade(s)%n", quantidadeDisponivel);

        int quantidade = -1;

        while (quantidade < 0 || quantidade > quantidadeDisponivel) {

            System.out.printf(
                    "Quantas deseja vender? (1-%d) ou 0 para cancelar: ",
                    quantidadeDisponivel
            );

            try {
                quantidade = scanner.nextInt();

                if (quantidade < 0 || quantidade > quantidadeDisponivel) {
                    System.out.printf(
                            "Valor invalido! Digite entre 1 e %d, ou 0 para cancelar.%n",
                            quantidadeDisponivel
                    );
                }

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }

        return quantidade;
    }

    /**
     * Exibe o resumo da compra e solicita confirmação.
     *
     * @param planta     Planta a ser comprada
     * @param quantidade Quantidade selecionada
     * @param total      Valor total
     * @param saldo      Saldo atual
     * @return true se confirmado, false se cancelado
     */
    public boolean confirmarCompra(TipoPlanta planta, int quantidade, int total, int saldo) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      RESUMO DA COMPRA        ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("  Planta    : %s%n", planta.getNome());
        System.out.printf("  Quantidade: %d unidade(s)%n", quantidade);
        System.out.printf("  Total     : G$%d%n", total);
        System.out.printf("  Saldo apos: G$%d%n", saldo - total);
        System.out.println("Confirmar compra? (1 - Sim / 0 - Nao)");
        return lerConfirmacao();
    }

    /**
     * Exibe o resumo da venda e solicita confirmação.
     *
     * @param item     Planta a ser vendida
     * @param quantidade Quantidade selecionada
     * @param total      Valor total a receber
     * @param saldo      Saldo atual
     * @return true se confirmado, false se cancelado
     */
    public boolean confirmarVenda(Armazenavel item, int quantidade, int total, int saldo) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      RESUMO DA VENDA         ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("  Planta    : %s%n", item.getNome());
        System.out.printf("  Quantidade: %d unidade(s)%n", quantidade);
        System.out.printf("  Receber   : G$%d%n", total);
        System.out.printf("  Saldo apos: G$%d%n", saldo + total);
        System.out.println("Confirmar venda? (1 - Sim / 0 - Nao)");
        return lerConfirmacao();
    }

    private boolean lerConfirmacao() {
        int resposta = -1;
        while (resposta != 0 && resposta != 1) {
            try {
                resposta = scanner.nextInt();
                if (resposta != 0 && resposta != 1)
                    System.out.println("Digite 1 para confirmar ou 0 para cancelar.");
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas 1 ou 0.");
                scanner.nextLine();
            }
        }
        return resposta == 1;
    }

    public void exibirSucessoCompra(TipoPlanta planta, int quantidade, int total, int saldoRestante) {
        System.out.printf("%n✔ Compra realizada com sucesso!%n  %dx %s adicionado(s) ao estoque.%n  Valor pago    : G$%d%n  Saldo restante: G$%d%n%n",
                quantidade, planta.getNome(), total, saldoRestante);
    }

    /**
     * Exibe confirmação de venda realizada com sucesso.
     *
     * @param item        Planta vendida
     * @param quantidade    Quantidade vendida
     * @param total         Valor recebido
     * @param saldoAtual    Saldo após a venda
     */
    public void exibirSucessoVenda(Armazenavel item, int quantidade, int total, int saldoAtual) {
        System.out.printf("%n✔ Venda realizada com sucesso!%n  %dx %s vendido(s).%n  Valor recebido: G$%d%n  Saldo atual   : G$%d%n%n",
                quantidade, item.getNome(), total, saldoAtual);
    }

    public void exibirOpcoesLoja() {
        System.out.println("""
                Selecione uma opcao:
                1 - Comprar sementes (catalogo geral)
                2 - Comprar sementes por estacao
                3 - Vender plantas colhidas
                0 - Sair da armazem""");
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}