package Controller;

import Model.DAO.GerenciadorArquivos;
import Model.DAO.JogadorDAO;
import Model.Jogador;

import java.io.IOException;

public class SaveController {

    public void salvar(Jogador jogador) {
        try {
            JogadorDAO dao = JogadorDAO.getInstance();
            dao.atualizar(jogador);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Jogador carregar(int id) {
        try {
            JogadorDAO dao = JogadorDAO.getInstance();
            return dao.find(id);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
