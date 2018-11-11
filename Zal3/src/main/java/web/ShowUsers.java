package web;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.server.ServerAcl.AclFormatException;

import hsqldb.ConnectDB;

@WebServlet("/ShowUsers")
public class ShowUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowUsers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection con;
		Statement stmt = null;
		ResultSet result;
		try {
			con = ConnectDB.getConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery("select * from users");
			out.println(
					"<table border=1><tr><td>Login</td><td>Haslo</td><td>E-mail</td><td>Premium</td><td>Admin</td></tr>");
			while (result.next()) {
				out.println("<tr><td>" + result.getString("login") + "</td><td>" + result.getString("password")
						+ "</td><td>" + result.getString("email") + "</td><td>" + result.getBoolean("premium") + "</td><td>"
						+ result.getBoolean("admin") + "</td></tr>");
			}
			out.println("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AclFormatException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
