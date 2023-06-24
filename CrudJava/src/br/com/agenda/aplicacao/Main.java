package br.com.agenda.aplicacao;

import br.com.agenda.DAO.ContatoDAO;
import br.com.agenda.Model.Contato;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Criando o contato
        ContatoDAO contatoDAO = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Victor Joseph Faria");
        contato.setIdade(22);
        contato.setDataCadastro(new Date());

        //contatoDAO.save(contato);


        //Atualizando o contato
        Contato c1 = new Contato();
        c1.setNome("Victor Joseph");
        c1.setIdade(23);
        c1.setDataCadastro(new Date());
        c1.setId(4);// numero que está no banco de dados da PK

        //contatoDAO.update(c1);

        //Deletando um contato
        contatoDAO.deleteByID(3);

        //vizualizando os registros do banco de dados

        for(Contato c : contatoDAO.getContato()){
            System.out.print("ID: "+c.getId()+" ,");
            System.out.print(" Nome: "+c.getNome()+" ,");
            System.out.print(" Idade: "+c.getIdade()+" e");
            System.out.print(" Data de cadastro: "+c.getDataCadastro()+"\n");
        }

    }
}
