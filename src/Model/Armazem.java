package Model;

import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;

import java.util.ArrayList;
import java.util.List;

public class Armazem {

    private List<TipoPlanta> catalogo;

    public Armazem() {
       setCatalogo(new ArrayList<>(List.of(TipoPlanta.values())));
    }

    public List<TipoPlanta> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<TipoPlanta> catalogo) {
        this.catalogo = catalogo;
    }

    public List<TipoPlanta> getCatalogoPorEstacao(Estacoes estacao) {
        List<TipoPlanta> catalogoPorEstacao = new ArrayList<>();
        for (TipoPlanta planta : catalogo) {
            if (planta.getEstacao().contains(estacao)) {
                catalogoPorEstacao.add(planta);
            }
        }
        return catalogoPorEstacao;
    }

    /**
     * Realiza a compra de sementes, debitando o valor do saldo do jogador
     * e adicionando as plantas ao estoque da fazenda.
     *
     * @param jogador    Jogador que está comprando
     * @param tipo       Tipo de planta selecionado
     * @param quantidade Quantidade de sementes desejada
     * @return true se a compra foi realizada, false se saldo insuficiente
     */
    public boolean comprar(Jogador jogador, TipoPlanta tipo, int quantidade) {
        int totalAPagar = tipo.getPrecoCompra() * quantidade;

        if (jogador.getDinheiro() < totalAPagar) {
            return false;
        }

        jogador.setDinheiro(jogador.getDinheiro() - totalAPagar);

        for (int i = 0; i < quantidade; i++) {
            jogador.getFazenda().getEstoqueSementes().add(tipo);
        }

        return true;
    }

    /**
     * Realiza a venda das plantas colhidas do estoque da fazenda.
     * Remove as plantas vendidas do estoque e credita o valor ao jogador.
     *
     * @param jogador    Jogador que está vendendo
     * @param itemSelecionado       Tipo de item a vender
     * @param quantidade Quantidade a vender
     * @return Valor total recebido pela venda, ou -1 se não havia plantas suficientes
     */
    public int vender(Jogador jogador, Armazenavel itemSelecionado, int quantidade) {
        List<Armazenavel> estoque = jogador.getFazenda().getEstoque();

        List<Armazenavel> itensEncontrados = estoque.stream()
                .filter(item -> item.getNome().equals(itemSelecionado.getNome()))
                .toList();

        if (itensEncontrados.size() < quantidade) {
            return -1;
        }

        int totalRecebido = 0;

        List<Armazenavel> aRemover = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            Armazenavel item = itensEncontrados.get(i);
            aRemover.add(item);
            totalRecebido += item.getValorVenda();
        }

        estoque.removeAll(aRemover);

        jogador.setDinheiro(
                jogador.getDinheiro() + totalRecebido
        );

        return totalRecebido;
    }
}