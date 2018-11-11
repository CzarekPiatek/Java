package web.filters;

import hsqldb.ConnectDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hsqldb.server.ServerAcl.AclFormatException;

@WebFilter("/Login")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		Connection con;
		Statement stmt;
		ResultSet result;
		String username = null;
		String password = null;
		if (session.getAttribute("login") != null) {
			request.setAttribute("notify", "Jestes zalogowany! Nie mozesz zalogowac sie drugi raz! <br>");
			RequestDispatcher view = request.getRequestDispatcher("Profile");
			view.forward(request, response);
			return;
		} else {
			try {
				con = ConnectDB.getConnection();
				stmt = con.createStatement();
				result = stmt.executeQuery("select login, password from users where login = '"
						+ request.getParameter("login").trim() + "'");
				if (result.next()) {
					username = result.getString("login");
					password = result.getString("password");
				}
				if (username == null || password == null || !password.equals(request.getParameter("password"))) {
					request.setAttribute("BadLoginOrPassword", "Zly login lub haslo");
					RequestDispatcher view = request.getRequestDispatcher("Login.jsp");
					view.forward(request, response);
				} else {
					chain.doFilter(request, response);
				}
			} catch (AclFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}