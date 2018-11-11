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
import javax.servlet.http.HttpSession;

import org.hsqldb.server.ServerAcl.AclFormatException;

import hsqldb.ConnectDB;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection con;
		Statement stmt;
		ResultSet result;
		String username = null;
		try {
			con = ConnectDB.getConnection();
			stmt = con.createStatement();
			result = stmt.executeQuery(
					"select login from users where login = '" + request.getParameter("login").trim() + "'");
			if (result.next()) {
				username = result.getString("login");
			}
			session.setAttribute("login", username);
			RequestDispatcher view = request.getRequestDispatcher("Profile");
			view.forward(request, response);
		} catch (AclFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}