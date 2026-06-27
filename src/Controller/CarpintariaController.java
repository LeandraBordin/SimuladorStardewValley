package Controller;

import Model.Carpintaria;
import Model.Construcao;
import Model.Enums.CategoriaConstrucao;
import Model.Enums.TipoConstrucao;
import Model.Jogador;
import View.CarpintariaView;

import java.util.List;

public class CarpintariaController {

    private final Carpintaria carpintaria;
    private final CarpintariaView view;

    public CarpintariaController(Carpintaria carpintaria, CarpintariaView view) {
        this.carpintaria = carpintaria;
        this.view = view;
    }

    public void abrirMenu(Jogador jogador) {
        view.exibirBoasVindas();

        int op = -1;
        do {
            view.exibirOpcoes();
            op = view.lerOpcao(3);

            switch (op) {
                case 1 -> realizarConstrucao(jogador);
                case 2 -> realizarAprimoramento(jogador);
                case 3 -> view.exibirConstrucoesAtuais(jogador.getFazenda().getConstrucoes());
                case 0 -> view.exibirMensagem("Saindo da Carpintaria...");
            }

        } while (op != 0);
    }

    private void realizarConstrucao(Jogador jogador) {
        List<TipoConstrucao> catalogoBase = carpintaria.getCatalogo().stream()
                .filter(c -> c == TipoConstrucao.CELEIRO || c == TipoConstrucao.GALINHEIRO)
                .toList();

        view.exibirCatalogo(catalogoBase);

        int opcao = view.lerOpcao(catalogoBase.size());
        if (opcao == 0) {
            view.exibirMensagem("Construcao cancelada.");
            return;
        }

        TipoConstrucao escolhida = catalogoBase.get(opcao - 1);

        if (jogador.getDinheiro() < escolhida.getValor()) {
            view.exibirMensagem("Saldo insuficiente para construir " + escolhida.getNome() + ".");
            return;
        }

        view.exibirResumoCompraConstrucao(escolhida, jogador.getDinheiro());
        if (!view.lerConfirmacao()) {
            view.exibirMensagem("Construcao cancelada.");
            return;
        }

        boolean sucesso = carpintaria.construir(escolhida, jogador);
        if (sucesso) {
            view.exibirSucessoConstrucao(escolhida, jogador.getDinheiro());
        } else {
            view.exibirMensagem("Nao foi possivel construir.");
        }
    }

    private void realizarAprimoramento(Jogador jogador) {
        List<Construcao> construcoes = jogador.getFazenda().getConstrucoes();

        if (construcoes.isEmpty()) {
            view.exibirMensagem("Voce nao tem construcoes para aprimorar.");
            return;
        }

        view.exibirConstrucoesParaAprimorar(construcoes);
        int opcao = view.lerOpcao(construcoes.size());
        if (opcao == 0) {
            view.exibirMensagem("Aprimoramento cancelado.");
            return;
        }

        Construcao escolhida = construcoes.get(opcao - 1);
        TipoConstrucao atual = escolhida.getTipo();
        TipoConstrucao proximo = getProximoNivel(atual);

        if (proximo == null) {
            view.exibirMensagem(atual.getNome() + " ja esta no nivel maximo!");
            return;
        }

        if (jogador.getDinheiro() < proximo.getValor()) {
            view.exibirMensagem("Saldo insuficiente para aprimorar para " + proximo.getNome() + ".");
            return;
        }

        view.exibirResumoAprimoramento(atual, proximo, jogador.getDinheiro());
        if (!view.lerConfirmacao()) {
            view.exibirMensagem("Aprimoramento cancelado.");
            return;
        }

        boolean sucesso = carpintaria.aprimorar(escolhida, jogador); // passa Construcao
        if (sucesso) {
            view.exibirSucessoAprimoramento(atual, proximo, jogador.getDinheiro());
        } else {
            view.exibirMensagem("Nao foi possivel aprimorar.");
        }
    }

    private TipoConstrucao getProximoNivel(TipoConstrucao atual) {
        return carpintaria.getProximoNivel(atual);
    }
}