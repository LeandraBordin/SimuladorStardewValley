import Controller.LojaController;
import Model.Enums.CorCabelo;
import Model.Enums.CorOlhos;
import Model.Enums.Estacoes;
import Model.Jogador;
import Model.Loja;
import Service.LojaService;
import View.LojaView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();
        LojaService lojaService = new LojaService(loja);
        LojaView lojaView = new LojaView();
        Jogador jogador = new Jogador("Leandra", CorCabelo.CASTANHO, CorOlhos.VERDE);
            System.out.println("Bem vindo ao jogo!");
            System.out.println("Selecione uma opção:\n"+
                    "1 - Ir para loja\n");
        System.out.println("Digite a opção:");
            int opFazenda = scanner.nextInt();
            switch (opFazenda){
                case 1:{
                    lojaView.exibirMensagemBoasVindas(jogador);
                    lojaView.exibirOpcoesLoja();
                    int opLoja = scanner.nextInt();
                    switch (opLoja){
                        case 1 :{
                            lojaView.exibirLoja(lojaService.listarCatalogo());
                        }break;
                        case 2:{
                            System.out.println("Selecione uma estação:\n"+
                                    "1 - Verão\n"+
                                    "2 - Primavera\n"+
                                    "3 - Outono\n"+
                                    "4 - Inverno\n");
                            int opEstacao = scanner.nextInt();
                            Estacoes estacao = Estacoes.values()[opEstacao - 1];
                            lojaView.exibirLoja(lojaService.listarCatalogoPorEstacao(estacao));
                        }break;
                    }
                }

            }
    }

}
