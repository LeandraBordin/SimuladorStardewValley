package Model;

import Model.Enums.TipoConstrucao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Construcao implements Serializable {
    private TipoConstrucao tipo;
    private List<Animal> animais;

    public Construcao(TipoConstrucao tipo) {
        this.tipo = tipo;
        this.animais = new ArrayList<>();
    }

    public boolean adicionarAnimal(Animal animal) {
        if (animais.size() >= tipo.getCapacidade()) {
            throw new IllegalStateException(tipo.getNome() + " está cheio! Capacidade: " + tipo.getCapacidade());
        }
        animais.add(animal);
        return true;
    }

    public boolean removerAnimal(Animal animal) {
        return animais.remove(animal);
    }

    public int getCapacidadeDisponivel() {
        return tipo.getCapacidade() - animais.size();
    }

    public TipoConstrucao getTipo() { return tipo; }
    public void setTipo(TipoConstrucao tipo) { this.tipo = tipo; }
    public List<Animal> getAnimais() { return animais; }

    @Override
    public String toString() {
        return "Construcao{" +
                "tipo=" + tipo +
                ", animais=" + animais +
                '}';
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}