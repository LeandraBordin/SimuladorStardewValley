package Model;

import Model.Enums.Estacoes;
import Model.Enums.NivelFazenda;
import Model.Enums.TipoPlanta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fazenda implements Serializable {
    private String nome;
    private Estacoes estacao;
    private int dia;
    private List<Planta> plantasPlantadas;
    private List<Planta> estoquePlantas;
    private NivelFazenda nivel;

    public Fazenda(String nome) {
        setNome(nome);
        setEstacao(Estacoes.PRIMAVERA);
        setDia(1);
        setPlantasPlantadas(new ArrayList<>());
        setEstoquePlantas(new ArrayList<>());
        setNivel(NivelFazenda.NIVEL_1);
    }
    public void passarDiaCrescimentoPlantas(){
        for (Planta planta : plantasPlantadas){
            planta.passarDiaCrescimento();
        }
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

    public void passarDia(){
        passarDiaCrescimentoPlantas();
        dia++;
    }

    public void passarEstacao(){
        if (dia > 28){
            Estacoes[] todas = Estacoes.values();
            Estacoes prox = todas[(estacao.ordinal()+1) % todas.length];
            setEstacao(prox);
        }
    }

    public Planta colher(Planta planta){
        if(planta.prontoParaColheita(planta) && planta.getTipo().getEstacao().contains(getEstacao())){
            guardarPlanta(planta);
            removerPlanta(planta);
        } else if (!planta.prontoParaColheita(planta) &planta.getTipo().getEstacao().contains(getEstacao())) {
            System.out.println("Ainda não é possível colher!");
        }
        return planta;
    }

    public void colherEmMassa(){
        List<Planta> aux = new ArrayList<>();
        while(!getPlantasPlantadas().isEmpty()){
            Planta plantaColhida = colher(getPlantasPlantadas().getFirst());
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

    public List<Planta> getPlantasPlantadas() {
        return plantasPlantadas;
    }

    public void setPlantasPlantadas(List<Planta> plantasPlantadas) {
        this.plantasPlantadas = plantasPlantadas;
    }

    public void plantarPlanta(Planta planta){
        plantasPlantadas.add(planta);
    }

    public void removerPlanta(Planta planta){
        plantasPlantadas.remove(planta);
    }

    public NivelFazenda getNivel() {
        return nivel;
    }

    public void setNivel(NivelFazenda nivel) {
        this.nivel = nivel;
    }

    public List<Planta> getEstoquePlantas() {
        return estoquePlantas;
    }

    public void setEstoquePlantas(List<Planta> estoquePlantas) {
        this.estoquePlantas = estoquePlantas;
    }

    public void guardarPlanta(Planta planta){
        estoquePlantas.add(planta);
    }

    @Override
    public String toString() {
        return "Fazenda{" +
                "nome='" + nome + '\'' +
                ", estacao=" + estacao +
                ", dia=" + dia +
                ", plantasPlantadas=" + plantasPlantadas +
                ", nivel=" + nivel +
                '}';
    }
}
