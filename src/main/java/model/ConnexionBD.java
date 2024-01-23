package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBD {
    // Mettez à jour ces informations avec les détails de votre base de données
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/tp_contacts";
    private static final String user = "root";
    private static final String password = "";
    private static   Connection  connecion;

    // Cette méthode établit une connexion à la base de données et la retourne
    public static Connection getConnection() throws Exception{

            Class.forName(driver);
            return connecion = DriverManager.getConnection(url,user,password);

    }
    public void SeDeconnecter() throws Exception{
        connecion.close();
    }
}
