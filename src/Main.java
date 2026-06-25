import Model.DAO.JogadorDAO;
import Model.Jogador;
import View.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu menu = new Menu();

        Jogador jogador = menu.menuJogador();

        if (jogador != null) {
            menu.menuPrincipal(jogador);
        } else {
            System.out.println("Nenhum jogador selecionado. Encerrando o jogo.");
        }
    }
}