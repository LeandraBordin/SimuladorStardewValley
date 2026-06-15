
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
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
    public static void main(String[] args) {
        try{
            PessoaDAO pdao = PessoaDAO.getInstance();
            Pessoa p1 = new Pessoa("Ana", 30);
            pdao.add(p1);
            Pessoa p2 = new Pessoa("Pedro", 23);
            pdao.add(p2);
            Pessoa p3 = new Pessoa("Marcia", 50);
            pdao.add(p3);
            Pessoa p4 = new Pessoa("Luiz", 15);
            pdao.add(p4);
            Collection dados = pdao.getDados();
            TreeSet setDados = new TreeSet(dados);
            Iterator it = setDados.iterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
            GerenciadorArquivos.gravarArquivo(pdao.getMap(), "dadosPessoa.dat");
        } catch(IOException ioe){
            System.out.println("Erro de IO");
        } catch(ClassNotFoundException cnfe){
            System.out.println("Erro de ClassNotFound");
        }
    }
        
    
    
}
