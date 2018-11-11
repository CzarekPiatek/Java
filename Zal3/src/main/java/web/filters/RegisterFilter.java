package web.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
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

import hsqldb.ConnectDB;
import hsqldb.Users;

@WebFilter("/Register")
public class RegisterFilter implements Filter {

	public RegisterFilter() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute("login") != null) {
			request.setAttribute("notify", "Jestes zalogowany! Nie mozesz zarejestrowac sie drugi raz! <br>");
			RequestDispatcher view = request.getRequestDispatcher("/Profile");
			view.forward(request, response);
			return;
		}
		Users users = new Users(request.getParameter("login").trim(), request.getParameter("password").trim(),
				request.getParameter("confirmPassword").trim(), request.getParameter("email").trim());
		response.setContentType("text/html");
		if (users.getUsername().isEmpty() || users.getPassword().isEmpty() || users.getConfirmPassword().isEmpty()
				|| users.getEmail().isEmpty()) {
			request.setAttribute("error", "Czy wypelniles wszystkie pola?");
			RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
			view.forward(request, response);
		} else {
			Connection con;
			Statement stmt;
			ResultSet resultLogin;
			ResultSet resultEmail;
			try {
				String username = null;
				String email = null;
				con = ConnectDB.getConnection();
				stmt = con.createStatement();
				resultLogin = stmt.executeQuery("select login from users where login = '" + users.getUsername() + "'");
				if (resultLogin.next()) {
					username = resultLogin.getString("login");
				}
				resultEmail = stmt.executeQuery("select email from users where email = '" + users.getEmail() + "'");
				if (resultEmail.next()) {
					email = resultEmail.getString("email");
				}
				if (!users.getPassword().equals(users.getConfirmPassword())) {
					request.setAttribute("error", "Podane hasla nie sa jednakowe");
					RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
					view.forward(request, response);
				} else if (!(username == null)) {
					request.setAttribute("error", "Podany login juz istnieje");
					RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
					view.forward(request, response);
				} else if (!(email == null)) {
					request.setAttribute("error", "Podany email juz istnieje");
					RequestDispatcher view = request.getRequestDispatcher("Register.jsp");
					view.forward(request, response);
				} else {
					chain.doFilter(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
}