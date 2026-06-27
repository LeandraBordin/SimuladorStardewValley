package View;

import Model.Construcao;
import Model.Enums.TipoConstrucao;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarpintariaView {

    private final Scanner scanner = new Scanner(System.in);

    public void exibirBoasVindas() {
        System.out.println("""
                ╔══════════════════════════════════════════════╗
                ║            CARPINTARIA DO ROBIN              ║
                ╚══════════════════════════════════════════════╝
                """);
    }

    public void exibirOpcoes() {
        System.out.println("""
                1 - Construir
                2 - Aprimorar
                3 - Ver construcoes atuais
                0 - Sair
                """);
    }

    public void exibirCatalogo(List<TipoConstrucao> catalogo) {
        String topo = "╔════╦══════════════════════╦════════════╦════════╗";
        String linha = "╠════╬══════════════════════╬════════════╬════════╣";
        String base  = "╚════╩══════════════════════╩════════════╩════════╝";

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║              CONSTRUCOES DISPONIVEIS             ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println(topo);
        System.out.printf("║%s║%s║%s║%s║%n",
                centralizar("#", 4),
                centralizar("Nome", 22),
                centralizar("Capacidade", 12),
                centralizar("Preco", 8));
        System.out.println(linha);

        for (int i = 0; i < catalogo.size(); i++) {
            TipoConstrucao c = catalogo.get(i);
            System.out.printf("║%s║%s║%s║%s║%n",
                    centralizar(String.valueOf(i + 1), 4),
                    centralizar(c.getNome(), 22),
                    centralizar(String.valueOf(c.getCapacidade()), 12),
                    centralizar("G$" + c.getValor(), 8));
        }
        System.out.println(base);
    }

    public void exibirConstrucoesAtuais(List<Construcao> construcoes) {
        if (construcoes.isEmpty()) {
            System.out.println("Voce ainda nao tem construcoes na fazenda.");
            return;
        }

        String topo = "╔════╦══════════════════════╦════════════╦══════════╗";
        String linha = "╠════╬══════════════════════╬════════════╬══════════╣";
        String base  = "╚════╩══════════════════════╩════════════╩══════════╝";

        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              SUAS CONSTRUCOES ATUAIS               ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        System.out.println(topo);
        System.out.printf("║%s║%s║%s║%s║%n",
                centralizar("#", 4),
                centralizar("Nome", 22),
                centralizar("Capacidade", 12),
                centralizar("Animais", 10));
        System.out.println(linha);

        for (int i = 0; i < construcoes.size(); i++) {
            Construcao c = construcoes.get(i);
            System.out.printf("║%s║%s║%s║%s║%n",
                    centralizar(String.valueOf(i + 1), 4),
                    centralizar(c.getTipo().getNome(), 22),
                    centralizar(String.valueOf(c.getTipo().getCapacidade()), 12),
                    centralizar(c.getAnimais().size() + "/" + c.getTipo().getCapacidade(), 10));
        }
        System.out.println(base);
    }

    public void exibirConstrucoesParaAprimorar(List<Construcao> construcoes) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║         ESCOLHA UMA CONSTRUCAO PARA APRIMORAR     ║");
        System.out.println("╚════════════════════════════════════════════════════╝");

        for (int i = 0; i < construcoes.size(); i++) {
            Construcao c = construcoes.get(i);
            System.out.printf("%d - %s (Capacidade: %d | Animais: %d/%d)%n",
                    i + 1,
                    c.getTipo().getNome(),
                    c.getTipo().getCapacidade(),
                    c.getAnimais().size(),
                    c.getTipo().getCapacidade());
        }
        System.out.println("0 - Cancelar");
    }

    public void exibirResumoCompraConstrucao(TipoConstrucao tipo, int saldo) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     RESUMO DA CONSTRUCAO     ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("  Construcao : %s%n", tipo.getNome());
        System.out.printf("  Capacidade : %d animais%n", tipo.getCapacidade());
        System.out.printf("  Preco      : G$%d%n", tipo.getValor());
        System.out.printf("  Saldo apos : G$%d%n", saldo - tipo.getValor());
        System.out.println("Confirmar? (1 - Sim / 0 - Nao)");
    }

    public void exibirResumoAprimoramento(TipoConstrucao atual, TipoConstrucao proximo, int saldo) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║    RESUMO DO APRIMORAMENTO   ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.printf("  Atual      : %s (cap. %d)%n", atual.getNome(), atual.getCapacidade());
        System.out.printf("  Proximo    : %s (cap. %d)%n", proximo.getNome(), proximo.getCapacidade());
        System.out.printf("  Preco      : G$%d%n", proximo.getValor());
        System.out.printf("  Saldo apos : G$%d%n", saldo - proximo.getValor());
        System.out.println("Confirmar? (1 - Sim / 0 - Nao)");
    }

    public void exibirSucessoConstrucao(TipoConstrucao tipo, int saldoRestante) {
        System.out.printf("%n✔ %s construido com sucesso!%n  Saldo restante: G$%d%n%n",
                tipo.getNome(), saldoRestante);
    }

    public void exibirSucessoAprimoramento(TipoConstrucao anterior, TipoConstrucao proximo, int saldoRestante) {
        System.out.printf("%n✔ %s aprimorado para %s com sucesso!%n  Saldo restante: G$%d%n%n",
                anterior.getNome(), proximo.getNome(), saldoRestante);
    }

    public int lerOpcao(int max) {
        int opcao = -1;
        while (opcao < 0 || opcao > max) {
            System.out.printf("Escolha (0-%d): ", max);
            try {
                opcao = scanner.nextInt();
                if (opcao < 0 || opcao > max)
                    System.out.println("Opcao invalida!");
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas numeros!");
                scanner.nextLine();
            }
        }
        return opcao;
    }

    public boolean lerConfirmacao() {
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