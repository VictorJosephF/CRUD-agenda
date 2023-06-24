package br.com.agenda.DAO;

import br.com.agenda.Factory.ConnecctionFactory;
import br.com.agenda.Model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    /*
    CRUD
    c: CREATE
    r: READ
    u: UPDATE
    d: DELETE
     */


    //CREATE
    public void save(Contato contato){
        String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            //criando uma conexão com o banco de dados.
            conn = ConnecctionFactory.createConnectionToMySQL();

            //adicionando os valores esperados pela query.
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            pstm.execute();
            System.out.println("Contato salvo com Sucesso!!!");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            //fechando as conexões.
            try{
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    //READ
    public List<Contato> getContato(){

        String sql = "SELECT * FROM contatos";
        List<Contato> contatos = new ArrayList<Contato>();
        Connection conn = null;
        PreparedStatement pstm = null;

        //classe que recupera os dados do banco
        ResultSet rst = null;
        try{
            conn = ConnecctionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rst = pstm.executeQuery();

            while(rst.next()){

                Contato contato = new Contato();

                // recuperando id
                contato.setId(rst.getInt("id"));

                //recuperando nome
                contato.setNome(rst.getString("nome"));

                //recuperando idade
                contato.setIdade(rst.getInt("idade"));

                //recuperando a data de cadastro
                contato.setDataCadastro(rst.getDate("dataCadastro"));

                contatos.add(contato);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(rst != null){
                    rst.close();
                }
                if(pstm != null){
                    pstm.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return contatos;
    }
    //UPDATE
    public void update(Contato contato){
        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            // Criando conexão com o banco
            conn = ConnecctionFactory.createConnectionToMySQL();

            //Criar a classe para executar a query
            pstm = conn.prepareStatement(sql);

            //adicionando os valores para atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //qual o id que deseja atualizar?
            pstm.setInt(4, contato.getId());

            pstm.execute();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    //DELETE
    public void deleteByID(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try{
            conn = ConnecctionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            pstm.execute();
            System.out.println("Contato deletado com sucesso!!");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null){
                    conn.close();
                }
                if(pstm != null){
                    pstm.close();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
