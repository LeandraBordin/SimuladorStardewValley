import Controller.LojaController;
import Model.Enums.CorCabelo;
import Model.Enums.CorOlhos;
import Model.Enums.Estacoes;
import Model.Jogador;
import Model.Loja;
import View.LojaView;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Loja loja = new Loja();
    private final LojaView lojaView = new LojaView();
    private final LojaController lojaController = new LojaController(loja,lojaView);
    Jogador jogador = new Jogador("Leandra", CorCabelo.CASTANHO, CorOlhos.VERDE);

    public void menuPrincipal() {
        System.out.println("Bem vindo ao jogo!");
        int opcao;
        do {
            System.out.println("""
                    1 - Ir para loja
                    0 - Sair
                    """);
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> {
                    lojaView.exibirMensagemBoasVindas(jogador);
                    lojaView.exibirOpcoesLoja();
                    int op = scanner.nextInt();
                    switch (op){
                        case 1 -> lojaController.exibirCatalogo();
                        case 2 ->{
                            Estacoes estacao = selecionarEstacao();
                            lojaController.exibirCatalogoPorEstacao(estacao);
                        }
                    }
                }
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private Estacoes selecionarEstacao() {

        System.out.println("""
            1 - Verão
            2 - Primavera
            3 - Outono
            4 - Inverno
            """);

        int opcao = scanner.nextInt();

        Estacoes estacao = switch (opcao) {
            case 1 -> Estacoes.VERAO;
            case 2 -> Estacoes.PRIMAVERA;
            case 3 -> Estacoes.OUTONO;
            case 4 -> Estacoes.INVERNO;
            default -> null;
        };

        if (estacao != null) {
            lojaController.exibirCatalogoPorEstacao(estacao);
        }
        return estacao;
    }
}
