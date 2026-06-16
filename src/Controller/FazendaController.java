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

    public Planta colher(Planta planta){
        if(planta.prontoParaColheita(planta) && planta.getTipo().getEstacao().contains(fazenda.getEstacao())){
            fazenda.guardarPlanta(planta);
            fazenda.removerPlanta(planta);
        } else if (!planta.prontoParaColheita(planta) &planta.getTipo().getEstacao().contains(fazenda.getEstacao())) {
            System.out.println("Ainda não é possível colher!");
        }
        return planta;
    }

    public void colherEmMassa(){
        List<Planta> aux = new ArrayList<>();
        while(!fazenda.getPlantasPlantadas().isEmpty()){
            Planta plantaColhida = colher(fazenda.getPlantasPlantadas().getFirst());
            aux.add(plantaColhida);
        }
        HashMap<TipoPlanta, Integer> colhidas = new HashMap<>();
        TipoPlanta tipo = null;
        for(Planta planta : aux){
            tipo = planta.getTipo();
            if(colhidas.containsKey(tipo)){
                colhidas.put(tipo, colhidas.get(tipo) + 1);
            } else {
                colhidas.put(tipo, 1);
            }
        }
        System.out.println("Colheita feita com sucesso!");
        for(Map.Entry<TipoPlanta, Integer> entry : colhidas.entrySet()){
            System.out.println(entry.getKey().getNome() + ": " + entry.getValue());
        }
    }

    public void passarDia(){
        fazenda.passarDia();
    }

    public void passarEstacao(){
        passarEstacao();
    }
}
