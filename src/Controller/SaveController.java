package Controller;

import Model.DAO.GerenciadorArquivos;
import Model.Jogador;

import java.io.IOException;

public class SaveController {

    private static final String ARQUIVO = "dadosJogador.dat";

    public void salvar(Jogador jogador){
        try {
            GerenciadorArquivos.gravarArquivo(jogador,ARQUIVO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Jogador carregar(Jogador jogador){
        try{
            return (Jogador) GerenciadorArquivos.lerArquivo(ARQUIVO);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
