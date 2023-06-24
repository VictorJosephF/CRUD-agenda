package br.com.agenda.Factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnecctionFactory {
    //Nome do usuario do mysql.
    private static final String USERNAME = "root";
    //Senha do banco
    private static final String PASSWORD = "";

    //camimnho do banco de dados, poryta e o nomee do banco de dados.
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

    /*
    Conexão com o banco de dados
     */

    public static Connection createConnectionToMySQL() throws Exception {
        //Faz com que a classe seja carregada pela JVM
         //Class.forName("com.mysql.jdbc.Driver");

        //Criamos a conexão com o banco de dados.

        return DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);





    }

    public static void main(String[] args) throws Exception {
        //Recuperar uma conexão com o banco de dados.
        Connection con = createConnectionToMySQL();

        //testando se conexão e nula.
        if(con != null){
            System.out.println("Conexão obtida com sucesso!!");
            con.close();
        }
    }
}
