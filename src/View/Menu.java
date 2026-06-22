package View;

import Controller.LojaController;
import Model.DAO.GerenciadorArquivos;
import Model.DAO.JogadorDAO;
import Model.Enums.Estacoes;
import Model.Fazenda;
import Model.Jogador;
import Model.Loja;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Loja loja = new Loja();
    private final LojaView lojaView = new LojaView();
    private final LojaController lojaController = new LojaController(loja,lojaView);

    public void menuPrincipal(Jogador jogador) {
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
    public Estacoes selecionarEstacao() {

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
    public void menuJogador() throws IOException, ClassNotFoundException {
        int op = 0;
        do {
            System.out.println("""
                    1 - Selecionar jogo
                    2 - Criar novo jogo
                    3 - Excluir jogo
                    0 - Sair
                    """);
            op = scanner.nextInt();
            switch (op){
                case 1:selecionarJogador();break;
                case 2:criarJogador();break;
            }
        }while(op != 0);
    }
    public void criarJogador() throws IOException, ClassNotFoundException {
        JogadorDAO dao = JogadorDAO.getInstance();
        System.out.println("Digite o nome do jogador:");
        String nome = scanner.next();
        System.out.println("Digite o nome da fazenda:");
        String nomeFazenda = scanner.next();
        Jogador jogador = new Jogador(nome);
        Fazenda fazenda = new Fazenda(nomeFazenda);
        jogador.adicionarFazenda(fazenda);

        boolean adicionado = dao.add(jogador);
        if (adicionado) {
            System.out.println("Jogador criado com sucesso!");
            GerenciadorArquivos.gravarArquivo(dao.getMap(), "dadosJogador.dat");
        } else {
            System.out.println("Limite de jogadores atingido!");
        }
    }
    public Jogador selecionarJogador() throws IOException, ClassNotFoundException {
        JogadorDAO dao = JogadorDAO.getInstance();
        Collection<Jogador> dados = dao.getDados();

        System.out.println("=== Jogadores disponíveis ===");
        for (Jogador j : dados) {
            System.out.println("ID: " + j.getId() + " | Nome: " + j.getNome());
        }

        System.out.print("Digite o ID do jogador: ");
        int id = scanner.nextInt();

        Jogador jogador = dao.find(id);
        if (jogador != null) {
            System.out.println("Jogador selecionado: " + jogador);
        } else {
            System.out.println("ID inválido!");
        }
        return jogador;
    }
}