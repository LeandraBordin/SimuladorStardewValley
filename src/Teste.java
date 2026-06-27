import Model.Carpintaria;
import Model.Enums.TipoAnimal;
import Model.Enums.TipoConstrucao;
import Model.Fazenda;
import Model.Jogador;
import Model.Rancho;

public class Teste {
    public static void main(String[] args) {
        Jogador jogador = new Jogador("Leandra");
        Fazenda fazenda = new Fazenda("Gale");
        jogador.adicionarFazenda(fazenda);
        jogador.setDinheiro(10000000);
        Carpintaria carpintaria = new Carpintaria();
        Rancho rancho = new Rancho();
        carpintaria.construir(TipoConstrucao.CELEIRO,jogador);
        rancho.comprar(jogador, TipoAnimal.VACA,4,jogador.getFazenda().getConstrucoes().get(0));
        rancho.comprar(jogador, TipoAnimal.GALINHA,4,jogador.getFazenda().getConstrucoes().get(0));
        System.out.println(jogador.getFazenda());
    }
}
