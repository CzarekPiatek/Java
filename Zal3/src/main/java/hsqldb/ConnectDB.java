package hsqldb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.server.Server;
import org.hsqldb.server.ServerAcl.AclFormatException;

public class ConnectDB {

	private static String url = "jdbc:hsqldb:hsql://localhost";
	private static String driver = "org.hsqldb.jdbc.JDBCDriver";
	private static String admin = "SA";
	private static String password = "";
	private static Connection con;

	public static Connection getConnection() throws IOException, AclFormatException {
		try {
			Class.forName(driver);
			try {
				HsqlProperties p = new HsqlProperties();
				p.setProperty("server.dbname.0", "users");
				Server server = new Server();
				server.setProperties(p);
				con = DriverManager.getConnection(url, admin, password);
			} catch (SQLException e) {
				System.out.println("Blad przy polaczeniu");
			}
		} catch (ClassNotFoundException e) {
			System.out.print("Nieznaleziono sterownika");
		}
		return con;
	}
}
