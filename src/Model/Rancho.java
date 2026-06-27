package Model;

import Model.Enums.TipoAnimal;
import java.util.ArrayList;
import java.util.List;

public class Rancho {

    private List<TipoAnimal> catalogo;

    public Rancho() {
        setCatalogo(new ArrayList<>(List.of(TipoAnimal.values())));
    }

    public List<TipoAnimal> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<TipoAnimal> catalogo) {
        this.catalogo = catalogo;
    }

    public boolean comprar(Jogador jogador, TipoAnimal tipoAnimal, int quantidade,Construcao construcao){
        int totalApagar = tipoAnimal.getPrecoCompra() * quantidade;
        if (tipoAnimal.getConstrucaoCompativel() != construcao.getTipo().getCategoriaCompativel()) {
            System.out.println("Construção incompatível para o tipo de animal");
            return false;
        }
        if (construcao.getAnimais().size() + quantidade > construcao.getTipo().getCapacidade()){
            System.out.println(construcao.getTipo().getNome() +" Cheio!");
            return false;
        }
        if (jogador.getDinheiro() < totalApagar){
            return false;
        }
        jogador.setDinheiro(jogador.getDinheiro()-totalApagar);
        for (int i =0; i < quantidade; i++){
            construcao.adicionarAnimal(new Animal(tipoAnimal));
        }
        return true;
    }
}
