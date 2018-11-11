package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hsqldb.server.ServerAcl.AclFormatException;

import hsqldb.ConnectDB;
import hsqldb.Users;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		Connection con;
		PreparedStatement pst;
		Users users = new Users(request.getParameter("login").trim(), request.getParameter("password").trim(),
				request.getParameter("confirmPassword").trim(), request.getParameter("email").trim());
		try {
			con = ConnectDB.getConnection();
			pst = con.prepareStatement("insert into users values(?,?,?,false,false)");
			pst.clearParameters();
			pst.setString(1, users.getUsername());
			pst.setString(2, users.getPassword());
			pst.setString(3, users.getEmail());
			pst.executeUpdate();
			request.setAttribute("registered", "Utworzono konto, zaloguj sie!");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AclFormatException e) {
			e.printStackTrace();
		}

	}
}
