package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.server.ServerAcl.AclFormatException;

import hsqldb.ConnectDB;

@WebServlet("/GrantPremium")
public class GrantPremium extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GrantPremium() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con;
		Statement stmt;
		ResultSet result;
		String user = null;
		try {
			con = ConnectDB.getConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(
					"select login from users where login ='" + request.getParameter("login").trim() + "'");
			if (result.next()) {
				user = result.getString("login");
			}
			if (user == null) {
				request.setAttribute("BadLogin", "Taki login nie istnieje w bazie");
				RequestDispatcher view = request.getRequestDispatcher("GrantPremium.jsp");
				view.forward(request, response);
			} else {
				int resultUpdate = stmt.executeUpdate(
						"update users set premium = true where login = '" + request.getParameter("login").trim() + "'");
				request.setAttribute("notify",
						"Pomyslnie dodano premium uzytkownikowi: " + request.getParameter("login").trim() + "<br>");
				RequestDispatcher view = request.getRequestDispatcher("index.jsp");
				view.forward(request, response);
			}
		} catch (AclFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
