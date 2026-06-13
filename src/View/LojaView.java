package View;

import Model.Enums.Estacoes;
import Model.Enums.TipoPlanta;
import Model.Jogador;

import java.util.List;

public class LojaView {

    public void exibirMensagemBoasVindas(Jogador jogador){
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

        if (texto.length() >= largura){
            return texto;
        }

        int espacos = largura - texto.length();
        int esquerda = espacos / 2;
        int direita = espacos - esquerda;
        return " ".repeat(esquerda) + texto + " ".repeat(direita);
    }

    public void exibirLoja(List<TipoPlanta> lista) {
        String topo  = "╔════════════════╦══════════════════╦════════╦════════╗";
        String linha = "╠════════════════╬══════════════════╬════════╬════════╣";
        String base  = "╚════════════════╩══════════════════╩════════╩════════╝";
        System.out.println(
                "╔═════════════════════════════════════════════════════╗\n" +
                "║                        CATÁLOGO                     ║\n" +
                "╚═════════════════════════════════════════════════════╝");
        System.out.println(topo);
        System.out.printf("║%s║%s║%s║%s║%n",
                centralizar("Nome", 16),
                centralizar("Estação", 18),
                centralizar("Compra", 8),
                centralizar("Venda", 8));
        System.out.println(linha);
        for (TipoPlanta planta : lista) {
            StringBuilder estacoes = new StringBuilder();
            boolean primeiro = true;
            for (Estacoes e : planta.getEstacao()) {
                if (!primeiro) estacoes.append(" / ");
                estacoes.append(e.name().toLowerCase());
                primeiro = false;
            }
            System.out.printf("║%s║%s║%s║%s║%n",
                    centralizar(planta.getNome(), 16),
                    centralizar(estacoes.toString(), 18),
                    centralizar(String.valueOf(planta.getPrecoCompra()), 8),
                    centralizar(String.valueOf(planta.getPrecoVenda()), 8));
        }
        System.out.println(base);
    }

    public void exibirOpcoesLoja(){
        System.out.println("Selecione uma opção:\n" +
                            "1 - Exibir Catálogo Geral\n"+
                            "2 - Exibir Catálogo por estação");
    }

}
