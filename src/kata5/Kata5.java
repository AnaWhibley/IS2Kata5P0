package kata5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ana Isabel Santana Media
 */
public class Kata5 {

    public static void main(String[] args) {
        String URL_BD_SQLite = new String("jdbc:sqlite:C:\\Users\\Usuario\\Desktop\\db_ana.db"); 
        Connection connection = connect(URL_BD_SQLite);
    }

    private static Connection connect(String URL_BD_SQLite) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL_BD_SQLite);
            System.out.println("Base de Datos conectada..");
            selectData_PEOPLE(connection);
            System.out.println("***************");
            insertData_PEOPLE(connection);
            selectData_PEOPLE(connection);
        }
        catch(SQLException exception) {
            System.out.println("Error en Kata5::connect(SQLException) " + exception.getMessage());
        }
        finally {
            try {
                if(connection != null) {
                    connection.close();
                }
            }
            catch(SQLException exception) {
            System.out.println("Error en Kata5::connect(SQLException) " + exception.getMessage());
            }
        }
        return connection;
    }
    
    private static void selectData_PEOPLE(Connection connection) {
        String SQL = "SELECT * FROM PEOPLE";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(SQL);
            System.out.println("ID \t NOMBRE \t APELLIDOS \t DEPARTAMENTO ");
            while(resultset.next()) {
                System.out.println(resultset.getInt("ID") + "\t " 
                                    + resultset.getString("Nombre") + "\t\t " 
                                    + resultset.getString("Apellidos") + "\t "
                                    + resultset.getString("Departamento"));
            }
        }
        catch(SQLException exception) {
            System.out.println("Error en Kata5::select(SQLException) " + exception.getMessage());
        }
    }
    
    private static void insertData_PEOPLE(Connection connection) {
        String SQL = "INSERT INTO PEOPLE(ID,Nombre,Apellidos,Departamento) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(SQL);
            preparedstatement.setInt(1,26);
            preparedstatement.setString(2,"Albert");
            preparedstatement.setString(3,"Rivera");
            preparedstatement.setString(4, "Ciencias Políticas");
            preparedstatement.executeUpdate();
        }
        catch(SQLException exception) {
            System.out.println("Error en Kata5::preparedStatement(SQLException) " + exception.getMessage());
        }
    }
    
}
