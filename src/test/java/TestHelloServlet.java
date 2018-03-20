import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.HelloServlet;

public class TestHelloServlet extends Mockito{
	
	@Test
	public void servlet_should_not_greet_the_user_if_the_name_is_empty() throws IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);

		
		HelloServlet servlet = new HelloServlet();
		
		when(request.getParameter("name")).thenReturn("");
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
		
					}
	@Test
	public void servlet_should_greet_the_user_when_the_name_is_provided() throws IOException{
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		PrintWriter writer = mock(PrintWriter.class);
		
		
		
		when(request.getParameter("name")).thenReturn("jan");
		when(response.getWriter()).thenReturn(writer);
		
		
		
		new HelloServlet().doPost(request, response);
		
		verify(writer).println("<h1>Hello jan</h1>");
		
					}
	@Test
	public void servlet_should_not_work_when_creditAmmountString_is_string() throws IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		PrintWriter writer = mock(PrintWriter.class);
		when(response.getWriter()).thenReturn(writer);

		
		HelloServlet servlet = new HelloServlet();
		
		when(request.getParameter("creditAmmountString")).thenReturn("0");
		
		servlet.doPost(request, response);
		
		verify(response).sendRedirect("/");
		
					}
	
}