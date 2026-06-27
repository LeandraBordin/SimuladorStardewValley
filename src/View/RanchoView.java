package View;

import Model.Animal;
import Model.Construcao;
import Model.Enums.TipoAnimal;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RanchoView {

    private final Scanner scanner = new Scanner(System.in);

    public void exibirBoasVindas() {
        System.out.println("""
                ╔══════════════════════════════════════════════╗
                ║              RANCHO DA MARNIE                ║
                ╚══════════════════════════════════════════════╝
                """);
    }

    public void exibirOpcoes() {
        System.out.println("""
                1 - Comprar animais
                2 - Ver animais atuais
                0 - Sair
                """);
    }

    /**
     * Exibe o catálogo de animais disponíveis para compra.
     *
     * @param catalogo Lista de tipos de animal a exibir
     */
    public void exibirCatalogo(List<TipoAnimal> catalogo) {
        String topo  = "╔════╦════════════════╦════════════╦════════╦══════════╦══════════════╗";
        String linha = "╠════╬════════════════╬════════════╬════════╬══════════╬══════════════╣";
        String base  = "╚════╩════════════════╩════════════╩════════╩══════════╩══════════════╝";

        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         ANIMAIS DISPONIVEIS                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println(topo);
        System.out.printf("║%s║%s║%s║%s║%s║%s║%n",
                centralizar("#", 4),
                centralizar("Nome", 16),
                centralizar("Preco", 12),
                centralizar("Drop", 8),
                centralizar("Vlr.Drop", 10),
                centralizar("Construcao", 14));
        System.out.println(linha);

        int numero = 1;
        for (TipoAnimal animal : catalogo) {
            System.out.printf("║%s║%s║%s║%s║%s║%s║%n",
                    centralizar(String.valueOf(numero), 4),
                    centralizar(animal.getNomeAnimal(), 16),
                    centralizar("G$" + animal.getPrecoCompra(), 12),
                    centralizar(animal.getDrop(), 8),
                    centralizar("G$" + animal.getValorDrop(), 10),
                    centralizar(animal.getConstrucaoCompativel().name(), 14));
            numero++;
        }
        System.out.println(base);
    }

    public void exibirCatalogoNumerado(List<TipoAnimal> catalogo, int saldo) {
        exibirCatalogo(catalogo);
        System.out.printf("%nSeu saldo atual: G$%d%n", saldo);
        System.out.println("Digite 0 para cancelar.");
    }

    /**
     * Exibe as construções compatíveis com o animal escolhido que ainda
     * possuem espaço disponível.
     *
     * @param construcoes Construções compatíveis com espaço livre
     */
    public void exibirConstrucoesCompativeis(List<Construcao> construcoes) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║    ESCOLHA UMA CONSTRUCAO PARA ALOJAR    ║");
        System.out.println("╚══════════════════════════════════════════╝");

        for (int i = 0; i < construcoes.size(); i++) {
            Construcao c = construcoes.get(i);
            System.out.printf("%d - %s (Ocupacao: %d/%d)%n",
                    i + 1,
                    c.getTipo().getNome(),
                    c.getAnimais().size(),
                    c.getTipo().getCapacidade());
        }
        System.out.println("0 - Cancelar");
    }

    /**
     * Exibe todos os animais que o jogador já possui, agrupados por construção.
     *
     * @param construcoes Construções da fazenda do jogador
     */
    public void exibirAnimaisAtuais(List<Construcao> construcoes) {
        boolean temAnimais = construcoes.stream().anyMatch(c -> !c.getAnimais().isEmpty());

        if (!temAnimais) {
            System.out.println("Voce ainda nao possui animais na fazenda.");
            return;
        }

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                  SEUS ANIMAIS                    ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        for (Construcao c : construcoes) {
            if (c.getAnimais().isEmpty()) continue;

            System.out.printf("%n%s (%d/%d):%n",
                    c.getTipo().getNome(),
                    c.getAnimais().size(),
                    c.getTipo().getCapacidade());

            c.getAnimais().stream()
                    .map(Animal::getTipoAnimal)
                    .distinct()
                    .forEach(tipo -> {
                        long qtd = c.getAnimais().stream()
                                .filter(a -> a.getTipoAnimal() == tipo)
                                .count();
                        System.out.printf("  - %dx %s%n", qtd, tipo.getNomeAnimal());
                    });
        }
        System.out.println();
    }

    /**
     * Lê e valida a opção digitada pelo jogador.
     *
     * @param max Número total de opções disponíveis
     * @return Opção escolhida (1 a max) ou 0 para cancelar
     */
    public int lerOpcao(int max) {
        int opcao = -1;
        while (opcao < 0 || opcao > max) {
            System.out.printf("Escolha uma opcao (0-%d): ", max);
            try {
                opcao = scanner.nextInt();
                if (opcao < 0 || opcao > max)
                    System.out.println("Opcao invalida! Tente novamente.");
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }
        return opcao;
    }

    /**
     * Lê a quantidade de animais que o jogador deseja comprar, respeitando
     * o saldo disponível e o espaço livre na construção escolhida.
     *
     * @param animal           Animal selecionado
     * @param saldo             Saldo atual do jogador
     * @param espacoDisponivel  Vagas livres na construção escolhida
     * @return Quantidade desejada ou 0 para cancelar
     */
    public int lerQuantidade(TipoAnimal animal, int saldo, int espacoDisponivel) {
        int maxPorSaldo = animal.getPrecoCompra() > 0 ? saldo / animal.getPrecoCompra() : 0;
        int maxPossivel = Math.min(maxPorSaldo, espacoDisponivel);

        System.out.printf("%n--- %s ---%n", animal.getNomeAnimal());
        System.out.printf("Preco por unidade  : G$%d%n", animal.getPrecoCompra());
        System.out.printf("Seu saldo          : G$%d%n", saldo);
        System.out.printf("Espaco disponivel  : %d vaga(s)%n", espacoDisponivel);
        System.out.printf("Maximo possivel    : %d unidade(s)%n", maxPossivel);

        if (maxPossivel == 0) {
            System.out.println("Nao e possivel comprar este animal agora (saldo ou espaco insuficiente).");
            return 0;
        }

        int quantidade = -1;
        while (quantidade < 0 || quantidade > maxPossivel) {
            System.out.printf("Quantos deseja comprar? (1-%d) ou 0 para cancelar: ", maxPossivel);
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
     * Exibe o resumo da compra e solicita confirmação.
     *
     * @param animal     Animal a ser comprado
     * @param quantidade Quantidade selecionada
     * @param total      Valor total
     * @param saldo      Saldo atual
     * @param construcao Construção de destino
     * @return true se confirmado, false se cancelado
     */
    public boolean confirmarCompra(TipoAnimal animal, int quantidade, int total, int saldo, Construcao construcao) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      RESUMO DA COMPRA        ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("  Animal     : %s%n", animal.getNomeAnimal());
        System.out.printf("  Quantidade : %d unidade(s)%n", quantidade);
        System.out.printf("  Destino    : %s%n", construcao.getTipo().getNome());
        System.out.printf("  Total      : G$%d%n", total);
        System.out.printf("  Saldo apos : G$%d%n", saldo - total);
        System.out.println("Confirmar compra? (1 - Sim / 0 - Nao)");
        return lerConfirmacao();
    }

    public void exibirSucessoCompra(TipoAnimal animal, int quantidade, int total, int saldoRestante) {
        System.out.printf("%n✔ Compra realizada com sucesso!%n  %dx %s adicionado(s) ao rancho.%n  Valor pago    : G$%d%n  Saldo restante: G$%d%n%n",
                quantidade, animal.getNomeAnimal(), total, saldoRestante);
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

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    private String centralizar(String texto, int largura) {
        if (texto.length() >= largura) return texto;
        int espacos = largura - texto.length();
        int esquerda = espacos / 2;
        return " ".repeat(esquerda) + texto + " ".repeat(espacos - esquerda);
    }
}