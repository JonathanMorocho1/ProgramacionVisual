package pruebabd;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaBD {


    private static final String Base = "bdEjercicio1";
    private static final String usuario = "root";
    private static final String contraseña = "1234";
    private static final String lineaBase = "jdbc:mysql://localhost:3306/"+Base+"?serverTimezone=UTC";
    private Connection connect = null;

    public Connection ConexionBD() {
        try {
            connect = DriverManager.getConnection(lineaBase, usuario, contraseña);
            if (connect != null) {
                return connect;
            }

        } catch (Exception e) {
            System.out.println("Error al conectar\n" + e.getMessage());

        }
        return connect;
    }
}
