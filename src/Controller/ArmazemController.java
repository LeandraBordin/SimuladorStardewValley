package Controller;

import Model.Armazem;
import Model.Armazenavel;
import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;
import Model.Jogador;
import Model.Planta;
import View.ArmazemView;

import java.util.ArrayList;
import java.util.List;

public class ArmazemController {

    private Armazem armazem;
    private ArmazemView armazemView;

    public ArmazemController(Armazem armazem, ArmazemView armazemView) {
        this.armazem = armazem;
        this.armazemView = armazemView;
    }

    public void exibirCatalogo() {
        armazemView.exibirLoja(armazem.getCatalogo());
    }

    public void exibirCatalogoPorEstacao(Estacoes estacao) {
        armazemView.exibirLoja(armazem.getCatalogoPorEstacao(estacao));
    }

    /**
     * Fluxo completo de compra: exibe catálogo, lê escolha, confirma e finaliza.
     *
     * @param jogador  Jogador que está comprando
     * @param catalogo Lista de plantas disponíveis para compra
     */
    public void realizarCompra(Jogador jogador, List<TipoPlanta> catalogo) {
        armazemView.exibirLojaNumerada(catalogo, jogador.getDinheiro());

        int opcaoPlanta = armazemView.lerOpcaoPlanta(catalogo.size());
        if (opcaoPlanta == 0) {
            armazemView.exibirMensagem("Compra cancelada.");
            return;
        }

        TipoPlanta plantaSelecionada = catalogo.get(opcaoPlanta - 1);

        int quantidade = armazemView.lerQuantidade(plantaSelecionada, jogador.getDinheiro());
        if (quantidade == 0) {
            armazemView.exibirMensagem("Compra cancelada.");
            return;
        }

        int totalAPagar = plantaSelecionada.getPrecoCompra() * quantidade;
        boolean confirmado = armazemView.confirmarCompra(plantaSelecionada, quantidade, totalAPagar, jogador.getDinheiro());
        if (!confirmado) {
            armazemView.exibirMensagem("Compra cancelada.");
            return;
        }

        boolean sucesso = armazem.comprar(jogador, plantaSelecionada, quantidade);
        if (sucesso) {
            armazemView.exibirSucessoCompra(plantaSelecionada, quantidade, totalAPagar, jogador.getDinheiro());
        } else {
            armazemView.exibirMensagem("Saldo insuficiente para realizar a compra.");
        }
    }

    /**
     * Fluxo completo de venda: lista plantas colhidas no estoque, lê escolha,
     * confirma e credita o valor ao jogador.
     *
     * @param jogador Jogador que está vendendo
     */

    public void realizarVenda(Jogador jogador) {

        List<Armazenavel> estoque = jogador.getFazenda().getEstoque();

        if (estoque.isEmpty()) {
            armazemView.exibirMensagem("Voce nao tem itens para vender.");
            return;
        }

        // Lista de itens únicos
        List<Armazenavel> itensDisponiveis = estoque.stream()
                .distinct()
                .toList();

        armazemView.exibirEstoqueParaVenda(itensDisponiveis, estoque, jogador.getDinheiro());

        int opcao = armazemView.lerOpcaoPlanta(itensDisponiveis.size());

        if (opcao == 0) {
            armazemView.exibirMensagem("Venda cancelada.");
            return;
        }

        Armazenavel itemSelecionado = itensDisponiveis.get(opcao - 1);


        long quantidadeDisponivel = estoque.stream()
                .filter(item -> item.getNome().equals(itemSelecionado.getNome()))
                .count();

        int quantidade = armazemView.lerQuantidadeVenda(
                itemSelecionado,
                (int) quantidadeDisponivel
        );

        if (quantidade == 0) {
            armazemView.exibirMensagem("Venda cancelada.");
            return;
        }

        // ✔️ CORRIGIDO: comparação por nome
        Armazenavel exemplo = estoque.stream()
                .filter(item -> item.getNome().equals(itemSelecionado.getNome()))
                .findFirst()
                .orElse(null);

        if (exemplo == null) {
            armazemView.exibirMensagem("Erro ao encontrar item.");
            return;
        }

        int totalAReceber = exemplo.getValorVenda() * quantidade;

        boolean confirmado = armazemView.confirmarVenda(
                itemSelecionado,
                quantidade,
                totalAReceber,
                jogador.getDinheiro()
        );

        if (!confirmado) {
            armazemView.exibirMensagem("Venda cancelada.");
            return;
        }

        int recebido = armazem.vender(jogador, itemSelecionado, quantidade);

        if (recebido >= 0) {
            armazemView.exibirSucessoVenda(
                    itemSelecionado,
                    quantidade,
                    recebido,
                    jogador.getDinheiro()
            );
        } else {
            armazemView.exibirMensagem("Erro ao realizar a venda. Tente novamente.");
        }
    }
}