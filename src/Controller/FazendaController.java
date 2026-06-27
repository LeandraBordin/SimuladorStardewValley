package Controller;

import Model.Fazenda;
import Model.Planta;

public class FazendaController {
    private Fazenda fazenda;

    public FazendaController(Fazenda fazenda){
        this.fazenda = fazenda;
    }

    public boolean plantar(Planta planta){
        if(!planta.getTipo().getEstacao().contains(fazenda.getEstacao())){
            System.out.println("Essa planta não pode ser plantada na estação atual: "+fazenda.getEstacao());
            return false;
        }
        if(fazenda.getNivel().getCapacidade() == fazenda.getPlantasPlantadas().size()){
            System.out.println("Limite de plantação máxima atingido");
            return false;
        }
        fazenda.plantarPlanta(planta);
        fazenda.removerSementeEstoque(planta.getTipo());
        return true;
    }

    public Planta colherPlanta(Planta planta){
        return fazenda.colher(planta);
    }

    public void colherEmMassa(){
        fazenda.colherEmMassa(); // ou service
    }

    public void passarDia(){
        fazenda.passarDia();
    }

    public int passarEstacao(){
        return fazenda.passarEstacao();
    }
}
