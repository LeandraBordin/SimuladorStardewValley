package Model;

import Model.Enums.*;

import java.io.Serializable;
import java.util.*;

public class Fazenda implements Serializable {
    private String nome;
    private Estacoes estacao;
    private int dia;
    private int totalDias;
    private List<Planta> plantasPlantadas;
    private List<TipoPlanta> estoqueSementes;
    private List<Armazenavel> estoque;
    private List <Construcao> construcoes;
    private NivelFazenda nivel;

    public Fazenda(String nome) {
        setNome(nome);
        setEstacao(Estacoes.PRIMAVERA);
        setDia(1);
        setPlantasPlantadas(new ArrayList<>());
        setEstoqueSementes(new ArrayList<>());
        setEstoque(new ArrayList<>());
        setConstrucoes(new ArrayList<>());
        setNivel(NivelFazenda.NIVEL_1);
    }
    public void passarDiaCrescimentoPlantas(){
        for (Planta planta : plantasPlantadas){
            planta.passarDiaCrescimento();
        }
    }

    public void passarDiaAnimais(){
        for (Construcao construcao : construcoes) {
            for (Animal animal : construcao.getAnimais()) {
                animal.passarDia();
            }
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

    public List<Armazenavel> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Armazenavel> estoque) {
        this.estoque = estoque;
    }

    public List<Construcao> getConstrucoes() {
        return construcoes;
    }

    public void setConstrucoes(List<Construcao> construcoes) {
        this.construcoes = construcoes;
    }

    public void adicionarConstrucao(Construcao construcao){
        construcoes.add(construcao);
    }

    public void passarDia(){
        dia++;
        totalDias++;
        passarDiaCrescimentoPlantas();
        passarDiaAnimais();
    }

    public int passarEstacao() {
        if (dia > 28) {
            Estacoes[] todas = Estacoes.values();
            Estacoes prox = todas[(estacao.ordinal() + 1) % todas.length];
            setEstacao(prox);
            dia = 1;

            int antes = plantasPlantadas.size();
            plantasPlantadas.removeIf(planta ->
                    !planta.getTipo().getEstacao().contains(estacao));
            return antes - plantasPlantadas.size();
        }
        return 0;
    }

    public Planta colher(Planta planta) {
        if (!planta.getTipo().getEstacao().contains(getEstacao())) {
            System.out.println("Fora de estação, não é possível colher!");
            return planta;
        }

        if (!planta.prontoParaColheita()) {
            System.out.println("Ainda não é possível colher!");
            return planta;
        }

        guardarNoEstoque(planta);
        planta.colher();

        if (!planta.getTipo().temRecrescimento()) {
            removerPlanta(planta);
            removerSementeEstoque(planta.getTipo());
        }

        return planta;
    }

    public void colherEmMassa() {
        List<Planta> prontas = plantasPlantadas.stream()
                .filter(p -> p.prontoParaColheita()
                        && p.getTipo().getEstacao().contains(getEstacao()))
                .toList();

        if (prontas.isEmpty()) {
            System.out.println("Nenhuma planta pronta para colheita.");
            return;
        }

        HashMap<TipoPlanta, Integer> colhidas = new HashMap<>();
        boolean temRegrowth = false;

        for (Planta planta : prontas) {
            colher(planta);
            colhidas.merge(planta.getTipo(), 1, Integer::sum);
            if (planta.getTipo().temRecrescimento()) temRegrowth = true;
        }

        System.out.println("Colheita feita com sucesso!");
        for (Map.Entry<TipoPlanta, Integer> entry : colhidas.entrySet()) {
            TipoPlanta tipo = entry.getKey();
            String sufixo = tipo.temRecrescimento()
                    ? " (regenera em " + tipo.getDiasRecresce() + " dias)"
                    : "";
            System.out.println(tipo.getNome() + ": " + entry.getValue() + sufixo);
        }

        if (temRegrowth) {
            System.out.println("Plantas em regeneração não foram removidas do campo.");
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
    public void removerSementeEstoque(TipoPlanta tipoPlanta){
        estoqueSementes.remove(tipoPlanta);
    }

    public List<TipoPlanta> getEstoqueSementes() {
        return estoqueSementes;
    }

    public void setEstoqueSementes(List<TipoPlanta> estoqueSementes) {
        this.estoqueSementes = estoqueSementes;
    }

    public NivelFazenda getNivel() {
        return nivel;
    }

    public void setNivel(NivelFazenda nivel) {
        this.nivel = nivel;
    }

    public int getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(int totalDias) {
        this.totalDias = totalDias;
    }

    public void guardarNoEstoque(Armazenavel item){
        estoque.add(item);
    }

    @Override
    public String toString() {
        return "Fazenda{" +
                "nome='" + nome + '\'' +
                ", estacao=" + estacao +
                ", dia=" + dia +
                ", totalDias=" + totalDias +
                ", plantasPlantadas=" + plantasPlantadas +
                ", estoqueSementes=" + estoqueSementes +
                ", estoque=" + estoque +
                ", construcoes=" + construcoes +
                ", nivel=" + nivel +
                '}';
    }
}
