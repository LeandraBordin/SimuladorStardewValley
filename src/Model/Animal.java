package Model;

import Model.Enums.TipoAnimal;

public class Animal {
    private TipoAnimal tipoAnimal;
    private int dias;

    public Animal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
        this.dias = 1;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}
