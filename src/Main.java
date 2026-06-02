import Model.Enums.CorCabelo;
import Model.Enums.CorOlhos;
import Model.Enums.TipoPlanta;
import Model.Fazenda;
import Model.Jogador;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<TipoPlanta> lista = new ArrayList<>(List.of(TipoPlanta.values()));
//        lista.forEach(tipoPlanta -> System.out.println(tipoPlanta));


        Jogador leandra = new Jogador("Leandra", CorCabelo.CASTANHO, CorOlhos.VERDE);
        Fazenda fazenda1 = new Fazenda("Fazenda da lele");
        leandra.adicionarFazenda(fazenda1);
        System.out.println(leandra);
    }

}
