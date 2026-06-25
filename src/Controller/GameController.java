package Controller;

import Model.DAO.JogadorDAO;
import Model.Jogador;
import View.Menu;
import java.awt.*;
import java.io.IOException;
import Model.Jogador;
public class GameController {
    TempoController tempoController;
    SaveController saveController;
    public GameController() throws IOException, ClassNotFoundException {
    }

    public void passarDia(Jogador jogador){
        tempoController.passarDia(jogador.getFazenda());
        saveController.salvar(jogador);
    }

   public void comecarJogo() throws IOException, ClassNotFoundException {
        JogadorDAO jogadorDAO = JogadorDAO.getInstance();
        Menu menu = new Menu();
        if (jogadorDAO.getDados().isEmpty()){
            menu.criarJogador();
        } else {
            menu.menuJogador();
        }
   }
}
