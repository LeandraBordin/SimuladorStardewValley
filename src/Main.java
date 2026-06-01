import Model.Planta;
import Model.TipoPlanta;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<TipoPlanta> lista = new ArrayList<>();
        lista.add(TipoPlanta.UVA);
        lista.forEach(planta -> System.out.println(
                        "nome: "+planta.getNome()+
                        " | valor: "+planta.getPrecoCompra()));

    }

}
