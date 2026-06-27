package Model;

import Model.Enums.TipoAnimal;

public class Animal {
    private TipoAnimal tipoAnimal;
    private int dias;
    private boolean adulto;
    private boolean dropDisponivel;

    public Animal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
        this.dias = 1;
        this.adulto = false;
        this.dropDisponivel = false;
    }

    public void passarDia() {
        dias++;
        if (!adulto && dias >= 5) {
            adulto = true;
        }
        // Só produz drop se for adulto
        if (adulto) {
            dropDisponivel = true;
        }
    }

    public void coletarDrop() {
        dropDisponivel = false;
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

    public boolean isAdulto() {
        return adulto;
    }

    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    public boolean isDropDisponivel() {
        return dropDisponivel;
    }

    public void setDropDisponivel(boolean dropDisponivel) {
        this.dropDisponivel = dropDisponivel;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "tipoAnimal=" + tipoAnimal +
                ", dias=" + dias +
                '}';
    }
}
