package web.filters;
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

import hsqldb.ConnectDB;

@WebFilter("/GrantPremium")
public class GrantPremiumFilter implements Filter {

	public GrantPremiumFilter() {
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
		boolean admin = false;
		if (session.getAttribute("login") == null) {
			request.setAttribute("notify", "Czy aby jestes zalogowany (z uprawnieniami admina)?<br>");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} else {
			try {
				con = ConnectDB.getConnection();
				stmt = con.createStatement();
				result = stmt
						.executeQuery("select admin from users where login ='" + session.getAttribute("login") + "'");
				if (result.next()) {
					admin = result.getBoolean("admin");
				}
								if (!admin) {
					request.setAttribute("notify", "Czy aby posiadasz uprawnienia administratora?<br>");
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
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
