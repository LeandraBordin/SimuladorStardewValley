package Controller;

import Model.Enums.TipoPlanta;
import Model.Fazenda;
import Model.Planta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FazendaController {
    private Fazenda fazenda;

    public FazendaController(Fazenda fazenda){
        this.fazenda = fazenda;
    }

    public boolean plantar(Planta planta){
        if(fazenda.getNivel().getCapacidade() == fazenda.getPlantasPlantadas().size()){
            System.out.println("Limite de plantação máxima atingido");
            return false;
        }
        fazenda.plantarPlanta(planta);
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

    public void passarEstacao(){
        fazenda.passarEstacao();
    }
}
