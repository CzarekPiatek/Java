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

@WebFilter("/Premium")
public class PremiumFilter implements Filter {

	public PremiumFilter() {
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
		boolean premium = false;
		if (session.getAttribute("login") == null) {
			request.setAttribute("notify", "Czy aby jestes zalogowany (z uprawnieniami premium)?<br>");
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		} else {
			try {
				con = ConnectDB.getConnection();
				stmt = con.createStatement();
				result = stmt
						.executeQuery("select premium from users where login ='" + session.getAttribute("login") + "'");
				if (result.next()) {
					premium = result.getBoolean("premium");
				}
				if (!premium) {
					request.setAttribute("notify", "Czy aby posiadasz uprawnienia premium?<br>");
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
