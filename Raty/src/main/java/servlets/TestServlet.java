package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

public class TestServlet extends Mockito {
	
	PrintWriter writer = mock(PrintWriter.class);
	
	@Test
	public void servletShouldNotGreenIfOneOfParameterIsEmpty() throws IOException, ServletException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		Raty servlet = new Raty();
		when(response.getWriter()).thenReturn(writer);
		when(request.getParameter("wnioskowanaKwotaKredytu")).thenReturn("");
		when(request.getParameter("oplataStala")).thenReturn("");
		when(request.getParameter("oprocentowanie")).thenReturn("");
		when(request.getParameter("iloscRat")).thenReturn("");
		when(request.getParameter("rodzajRat")).thenReturn("");
		servlet.doPost(request, response);
		verify(response).sendRedirect("/");
	}
}
