package View;

import Controller.*;
import Model.*;
import Model.DAO.GerenciadorArquivos;
import Model.DAO.JogadorDAO;
import Model.Enums.CategoriaConstrucao;
import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final Armazem armazem = new Armazem();
    private final ArmazemView armazemView = new ArmazemView();
    private final ArmazemController lojaController = new ArmazemController(armazem, armazemView);
    private final Carpintaria carpintaria = new Carpintaria();
    private final CarpintariaView carpintariaView = new CarpintariaView();
    private final CarpintariaController carpintariaController = new CarpintariaController(carpintaria, carpintariaView);
    private final Rancho rancho = new Rancho();
    private final RanchoView ranchoView = new RanchoView();
    private final RanchoController ranchoController = new RanchoController(rancho, ranchoView);
    /**
     * Menu principal do jogo, exibido após o login do jogador.
     *
     * @param jogador Jogador logado
     */
    public void menuPrincipal(Jogador jogador) {
        SaveController saveController = new SaveController();
        System.out.printf("Bem vindo ao jogo, %s!%n", jogador.getNome());
        int opcao = -1;
        do {
            System.out.printf("""
                    Saldo: G$%d
                    1 - Ir para a fazenda
                    2 - Ir para o Armazém
                    3 - Ir para o Rancho
                    4 - Ir para a Carpintaria
                    0 - Sair
                    """, jogador.getDinheiro());
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                scanner.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> menuFazenda(jogador);
                case 2 -> menuLoja(jogador);
                case 3 -> ranchoController.abrirMenu(jogador);
                case 4 -> carpintariaController.abrirMenu(jogador);
                case 0 -> {
                    System.out.println("Ate logo!");
                    saveController.salvar(jogador);
                }
                default -> System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);
    }

    /**
     * Menu da fazenda: plantar, passar dia e colher.
     *
     * @param jogador Jogador dono da fazenda
     */
    public void menuFazenda(Jogador jogador) {
        Fazenda fazenda = jogador.getFazenda();
        FazendaController fazendaController = new FazendaController(fazenda);
        TempoController tempoController = new TempoController();
        SaveController saveController = new SaveController();

        int op = -1;
        do {
            // Busca construções disponíveis
            List<Construcao> galinheiros = fazenda.getConstrucoes().stream()
                    .filter(c -> c.getTipo().getCategoriaCompativel() == CategoriaConstrucao.GALINHEIRO)
                    .toList();

            List<Construcao> celeiros = fazenda.getConstrucoes().stream()
                    .filter(c -> c.getTipo().getCategoriaCompativel() == CategoriaConstrucao.CELEIRO)
                    .toList();

            System.out.printf(
                    "╔══════════════════════════════════════════════╗%n" +
                            "║  FAZENDA: %-35s║%n" +
                            "║  Dia: %-2d | Estacao: %-25s║%n" +
                            "║  Plantadas: %d/%d | Estoque: %d itens(s)       ║%n" +
                            "╚══════════════════════════════════════════════╝%n" +
                            "1 - Plantar%n" +
                            "2 - Passar dia%n" +
                            "3 - Colher tudo%n",
                    fazenda.getNome(),
                    fazenda.getDia(),
                    fazenda.getEstacao().name().toLowerCase(),
                    fazenda.getPlantasPlantadas().size(),
                    fazenda.getNivel().getCapacidade(),
                    fazenda.getEstoque().size());

            // Exibe opção de galinheiro só se tiver
            if (!galinheiros.isEmpty()) {
                System.out.println("4 - Ir para o Galinheiro");
            }

            // Exibe opção de celeiro só se tiver
            if (!celeiros.isEmpty()) {
                System.out.println("5 - Ir para o Celeiro");
            }

            System.out.println("0 - Voltar");

            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                scanner.nextLine();
                continue;
            }

            switch (op) {
                case 1 -> menuPlantar(fazenda, fazendaController);
                case 2 -> {
                    tempoController.passarDia(fazenda);
                    saveController.salvar(jogador);
                    int mortas = tempoController.passarEstacao(fazenda);

                    System.out.printf("Dia %d! Estacao: %s%n",
                            fazenda.getDia(),
                            fazenda.getEstacao().name().toLowerCase());

                    if (mortas > 0) {
                        System.out.println("╔══════════════════════════════════════════════╗");
                        System.out.println("║           MUDANCA DE ESTACAO!                ║");
                        System.out.printf( "║  %d planta(s) nao sobreviveram ao novo clima. ║%n", mortas);
                        System.out.println("╚══════════════════════════════════════════════╝");
                    }
                }
                case 3 -> fazendaController.colherEmMassa();
                case 4 -> {
                    if (!galinheiros.isEmpty()) {
                        menuConstrucao(galinheiros, jogador);
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 5 -> {
                    if (!celeiros.isEmpty()) {
                        menuConstrucao(celeiros, jogador);
                    } else {
                        System.out.println("Opcao invalida!");
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida!");
            }

        } while (op != 0);
    }
    private void menuConstrucao(List<Construcao> construcoes, Jogador jogador) {
        // Se só tiver uma, entra direto
        if (construcoes.size() == 1) {
            menuAnimais(construcoes.get(0), jogador);
            return;
        }

        System.out.println("=== Escolha a construcao ===");
        for (int i = 0; i < construcoes.size(); i++) {
            Construcao c = construcoes.get(i);
            System.out.printf("%d - %s | Animais: %d/%d%n",
                    i + 1,
                    c.getTipo().getNome(),
                    c.getAnimais().size(),
                    c.getTipo().getCapacidade());
        }
        System.out.println("0 - Voltar");

        int op = -1;
        try {
            op = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            scanner.nextLine();
            return;
        }

        if (op == 0) return;
        if (op < 1 || op > construcoes.size()) {
            System.out.println("Opcao invalida!");
            return;
        }

        menuAnimais(construcoes.get(op - 1), jogador);
    }
    private void menuAnimais(Construcao construcao, Jogador jogador) {
        int op = -1;
        do {
            // Conta drops disponíveis
            long dropsDisponiveis = construcao.getAnimais().stream()
                    .filter(Animal::isDropDisponivel)
                    .count();

            System.out.printf("""
                ╔══════════════════════════════════════════════╗
                ║  %-43s║
                ║  Animais: %d/%d | Drops prontos: %d             ║
                ╚══════════════════════════════════════════════╝
                1 - Ver animais
                2 - Coletar drops
                0 - Voltar
                """,
                    construcao.getTipo().getNome(),
                    construcao.getAnimais().size(),
                    construcao.getTipo().getCapacidade(),
                    dropsDisponiveis);

            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                scanner.nextLine();
                continue;
            }

            switch (op) {
                case 1 -> listarAnimais(construcao);
                case 2 -> coletarDrops(construcao, jogador);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida!");
            }

        } while (op != 0);
    }

    private void listarAnimais(Construcao construcao) {
        List<Animal> animais = construcao.getAnimais();

        if (animais.isEmpty()) {
            System.out.println("Nenhum animal aqui ainda.");
            return;
        }

        System.out.println("=== ANIMAIS ===");
        for (int i = 0; i < animais.size(); i++) {
            Animal a = animais.get(i);
            String status = a.isAdulto() ? "Adulto" : "Filhote (dia " + a.getDias() + "/5)";
            String drop = a.isDropDisponivel() ? "✔ drop pronto" : "✘ sem drop";
            System.out.printf("%d - %s | %s | %s%n",
                    i + 1,
                    a.getTipoAnimal().getNomeAnimal(),
                    status,
                    drop);
        }
    }

    private void coletarDrops(Construcao construcao, Jogador jogador) {
        List<Animal> comDrop = construcao.getAnimais().stream()
                .filter(Animal::isDropDisponivel)
                .toList();

        if (comDrop.isEmpty()) {
            System.out.println("Nenhum drop disponivel ainda. Passe o dia!");
            return;
        }

        int totalColetado = 0;
        for (Animal a : comDrop) {
            a.coletarDrop();
            jogador.getFazenda().getEstoque().add(a.getTipoAnimal()); // getNome() do TipoAnimal agora retorna o drop
            totalColetado++;
        }

        System.out.printf("%d %s(s) coletado(s) e adicionado(s) ao estoque!%n",
                totalColetado,
                comDrop.get(0).getTipoAnimal().getDrop());
    }

    /**
     * Submenu de plantio: lista sementes do estoque e planta a quantidade escolhida.
     * @param fazenda           Fazenda do jogador
     * @param fazendaController Controller da fazenda
     */
    private void menuPlantar(Fazenda fazenda, FazendaController fazendaController) {
        List<Planta> estoque = new ArrayList<>();
        for (int i = 0; i < fazenda.getEstoqueSementes().size(); i++){
            TipoPlanta novoTipo = fazenda.getEstoqueSementes().get(i);
            Planta novaPlanta = new Planta(novoTipo);
            estoque.add(novaPlanta);
        }

        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio! Va a loja comprar sementes.");
            return;
        }

        List<TipoPlanta> tiposDisponiveis = new ArrayList<>();
        for (Planta p : estoque) {
            if (!tiposDisponiveis.contains(p.getTipo())) {
                tiposDisponiveis.add(p.getTipo());
            }
        }

        System.out.println("=== SEMENTES NO ESTOQUE ===");
        for (int i = 0; i < tiposDisponiveis.size(); i++) {
            TipoPlanta tipo = tiposDisponiveis.get(i);
            long quantidade = estoque.stream().filter(p -> p.getTipo() == tipo).count();
            System.out.printf("%d - %s (x%d) | %d dias para crescer%n",
                    i + 1, tipo.getNome(), quantidade, tipo.getDiasCrescimento());
        }
        System.out.println("0 - Cancelar");

        System.out.print("Escolha a semente: ");
        int opcao = -1;
        try {
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            scanner.nextLine();
            return;
        }

        if (opcao == 0) return;
        if (opcao < 1 || opcao > tiposDisponiveis.size()) {
            System.out.println("Opcao invalida!");
            return;
        }

        TipoPlanta tipoEscolhido = tiposDisponiveis.get(opcao - 1);

        System.out.print("Quantas deseja plantar? ");
        int quantidade = -1;
        try {
            quantidade = scanner.nextInt();
            if (quantidade > fazenda.getNivel().getCapacidade()){
                quantidade = fazenda.getNivel().getCapacidade();
            }
        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            scanner.nextLine();
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade invalida!");
            return;
        }

        int plantadas = 0;
        for (int j = 0; j < quantidade; j++) {
            Planta plantaParaPlantar = null;
            for (Planta p : estoque) {
                if (p.getTipo() == tipoEscolhido) {
                    plantaParaPlantar = p;
                    break;
                }
            }
            if (plantaParaPlantar == null) {
                System.out.println("Estoque esgotado para esta semente!");
                break;
            }
            boolean sucesso = fazendaController.plantar(plantaParaPlantar);
            if (sucesso) {
                estoque.remove(plantaParaPlantar);
                plantadas++;
            } else {
                break; // fazenda cheia
            }
        }

        if (plantadas > 0) {
            System.out.printf("%d %s plantado(s) com sucesso!%n", plantadas, tipoEscolhido.getNome());
        }
    }

    /**
     * Menu da loja com opções de visualização e compra.
     * @param jogador Jogador que está acessando a loja
     */
    public void menuLoja(Jogador jogador) {
        armazemView.exibirMensagemBoasVindas(jogador);

        int op = -1;
        do {
            armazemView.exibirOpcoesLoja();
            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                scanner.nextLine();
                continue;
            }

            switch (op) {
                case 1 -> lojaController.realizarCompra(jogador, armazem.getCatalogo());
                case 2 -> {
                    Estacoes estacao = selecionarEstacao();
                    if (estacao != null) {
                        List<TipoPlanta> catalogoEstacao = armazem.getCatalogoPorEstacao(estacao);
                        if (catalogoEstacao.isEmpty()) {
                            System.out.println("Nenhuma planta disponivel para esta estacao.");
                        } else {
                            lojaController.realizarCompra(jogador, catalogoEstacao);
                        }
                    }
                }
                case 3 -> lojaController.realizarVenda(jogador);
                case 0 -> System.out.println("Saindo da loja...");
                default -> System.out.println("Opcao invalida!");
            }

        } while (op != 0);
    }

    /**
     * Solicita ao jogador que selecione uma estação do ano.
     * @return Estação selecionada ou null se a opção for inválida
     */
    public Estacoes selecionarEstacao() {
        System.out.println("""
                1 - Verao
                2 - Primavera
                3 - Outono
                4 - Inverno
                """);

        int opcao = -1;
        try {
            opcao = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opcao invalida!");
            scanner.nextLine();
            return null;
        }

        return switch (opcao) {
            case 1 -> Estacoes.VERAO;
            case 2 -> Estacoes.PRIMAVERA;
            case 3 -> Estacoes.OUTONO;
            case 4 -> Estacoes.INVERNO;
            default -> {
                System.out.println("Estacao invalida!");
                yield null;
            }
        };
    }

    /**
     * Menu inicial: seleciona ou cria jogador e retorna o jogador para entrar no jogo.
     * @return Jogador selecionado, ou null se o usuário saiu sem selecionar
     */
    public Jogador menuJogador() throws IOException, ClassNotFoundException {
        int op = 0;
        do {
            System.out.println("""
                    1 - Selecionar jogo
                    2 - Criar novo jogo
                    3 - Excluir jogo
                    0 - Sair
                    """);
            try {
                op = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                scanner.nextLine();
                continue;
            }
            switch (op) {
                case 1 -> {
                    Jogador jogador = selecionarJogador();
                    if (jogador != null) {
                        return jogador;
                    }
                }
                case 2 -> criarJogador();
            }
        } while (op != 0);

        return null;
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

        if (dados.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado!");
            return null;
        }

        System.out.println("=== Jogadores disponíveis ===");
        for (Jogador j : dados) {
            System.out.println("ID: " + j.getId() + " | Nome: " + j.getNome());
        }

        System.out.print("Digite o ID do jogador: ");
        int id = -1;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ID invalido!");
            scanner.nextLine();
            return null;
        }

        Jogador jogador = dao.find(id);
        if (jogador != null) {
            System.out.println("Jogador selecionado: " + jogador.getNome());
        } else {
            System.out.println("ID invalido!");
        }
        return jogador;
    }

    public void excluirJogador(){

    }
}