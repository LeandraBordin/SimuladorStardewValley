package Model;

import Model.Enums.Estacoes;
import Model.Enums.NivelFazenda;
import Model.Enums.TipoPlanta;

import java.util.ArrayList;
import java.util.List;

public class Fazenda {
    private String nome;
    private Estacoes estacao;
    private int dia;
    private List<Planta> plantasPlantadas;
    private List<TipoPlanta> sementes;
    private NivelFazenda nivel;

    public Fazenda(String nome) {
        setNome(nome);
        setEstacao(Estacoes.PRIMAVERA);
        setDia(1);
        setPlantasPlantadas(new ArrayList<>());
        setSementes(new ArrayList<>());
        setNivel(NivelFazenda.NIVEL_1);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estacoes getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacoes estacao) {
        this.estacao = estacao;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public List<Planta> getPlantasPlantadas() {
        return plantasPlantadas;
    }

    public void setPlantasPlantadas(List<Planta> plantasPlantadas) {
        this.plantasPlantadas = plantasPlantadas;
    }

    public List<TipoPlanta> getSementes() {
        return sementes;
    }

    public void setSementes(List<TipoPlanta> sementes) {
        this.sementes = sementes;
    }

    public NivelFazenda getNivel() {
        return nivel;
    }

    public void setNivel(NivelFazenda nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Fazenda{" +
                "nome='" + nome + '\'' +
                ", estacao=" + estacao +
                ", dia=" + dia +
                ", plantasPlantadas=" + plantasPlantadas +
                ", sementes=" + sementes +
                ", nivel=" + nivel +
                '}';
    }
}
