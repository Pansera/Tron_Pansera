import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BDD {

	int winner=0;
	int game_time=0;
	public void writeBDD() {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	try {
		/**
		 * Ajout des drivers SQL
		 */
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		/**
		 * Connection � la Base de donn�e
		 */
		String connectionUrl = "jdbc:mysql://localhost/tron?autoReconnect=true&useSSL=false";
		String connectionUser = "root";
		String connectionPassword = "";
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		stmt = conn.createStatement();
		/**
		 * �criture dans la base de donn�e
		 */
		rs = stmt.executeQuery(" INSERT INTO Tron(Vainqueur, Temps), ("+winner+","+game_time+")");
		/**
		 * Gestion des Exceptions (d�faut de connexion � la DB par exemple)
		 */
		while (rs.next()) {
			//Variable = rs.getInt("Nom Variable dans BDD");
			
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	}
}}