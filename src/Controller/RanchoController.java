package Controller;

import Model.Construcao;
import Model.Enums.TipoAnimal;
import Model.Jogador;
import Model.Rancho;
import View.RanchoView;

import java.util.List;

public class RanchoController {

    private final Rancho rancho;
    private final RanchoView view;

    public RanchoController(Rancho rancho, RanchoView view) {
        this.rancho = rancho;
        this.view = view;
    }

    public void abrirMenu(Jogador jogador) {
        view.exibirBoasVindas();

        int op = -1;
        do {
            view.exibirOpcoes();
            op = view.lerOpcao(2);

            switch (op) {
                case 1 -> realizarCompra(jogador);
                case 2 -> view.exibirAnimaisAtuais(jogador.getFazenda().getConstrucoes());
                case 0 -> view.exibirMensagem("Saindo do Rancho...");
            }

        } while (op != 0);
    }

    /**
     * Fluxo completo de compra de animais: exibe catálogo, lê a escolha do
     * animal, seleciona uma construção compatível com espaço disponível,
     * lê a quantidade, confirma e finaliza a compra.
     *
     * @param jogador Jogador que está comprando
     */
    private void realizarCompra(Jogador jogador) {
        List<TipoAnimal> catalogo = rancho.getCatalogo();

        view.exibirCatalogoNumerado(catalogo, jogador.getDinheiro());

        int opcaoAnimal = view.lerOpcao(catalogo.size());
        if (opcaoAnimal == 0) {
            view.exibirMensagem("Compra cancelada.");
            return;
        }

        TipoAnimal animalSelecionado = catalogo.get(opcaoAnimal - 1);

        List<Construcao> compativeis = jogador.getFazenda().getConstrucoes().stream()
                .filter(c -> c.getTipo().getCategoriaCompativel() == animalSelecionado.getConstrucaoCompativel())
                .filter(c -> c.getAnimais().size() < c.getTipo().getCapacidade())
                .toList();

        if (compativeis.isEmpty()) {
            view.exibirMensagem("Voce nao possui construcao compativel com espaco disponivel para " + animalSelecionado.getNomeAnimal() + ".");
            return;
        }

        view.exibirConstrucoesCompativeis(compativeis);
        int opcaoConstrucao = view.lerOpcao(compativeis.size());
        if (opcaoConstrucao == 0) {
            view.exibirMensagem("Compra cancelada.");
            return;
        }

        Construcao construcaoSelecionada = compativeis.get(opcaoConstrucao - 1);
        int espacoDisponivel = construcaoSelecionada.getTipo().getCapacidade() - construcaoSelecionada.getAnimais().size();

        int quantidade = view.lerQuantidade(animalSelecionado, jogador.getDinheiro(), espacoDisponivel);
        if (quantidade == 0) {
            view.exibirMensagem("Compra cancelada.");
            return;
        }

        int totalAPagar = animalSelecionado.getPrecoCompra() * quantidade;
        boolean confirmado = view.confirmarCompra(animalSelecionado, quantidade, totalAPagar, jogador.getDinheiro(), construcaoSelecionada);
        if (!confirmado) {
            view.exibirMensagem("Compra cancelada.");
            return;
        }

        boolean sucesso = rancho.comprar(jogador, animalSelecionado, quantidade, construcaoSelecionada);
        if (sucesso) {
            view.exibirSucessoCompra(animalSelecionado, quantidade, totalAPagar, jogador.getDinheiro());
        } else {
            view.exibirMensagem("Nao foi possivel realizar a compra. Verifique saldo, espaco ou compatibilidade.");
        }
    }
}