
import DAO.GerenciadorArquivos;
import DAO.JogadorDAO;
import Model.Fazenda;
import Model.Jogador;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author karen
 */
public class TestaDAO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu menu = new Menu();
//        menu.criarJogador();
        Jogador jogador = menu.selecionarJogador();
        jogador.setDinheiro(10000);
        JogadorDAO jogadorDAO = JogadorDAO.getInstance();
    }
}
