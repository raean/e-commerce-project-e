package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

@WebServlet("/Prime.do")
public class Prime extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		Engine engine = Engine.getInstance();
		Writer out = response.getWriter();
		response.setContentType("text/json");
		try
		{
			int result = engine.doPrime(min, max);
			out.write("{\"status\":1, \"result\":" + result + "}");
		} catch (Exception e)
		{
			out.write("{\"status\":0, \"error\":\"" + e.getMessage() + "\"}");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
