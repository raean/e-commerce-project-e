package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Engine;
import model.StudentBean;

@WebServlet("/Sis.do")
public class Sis extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Engine engine = Engine.getInstance();
		String name = request.getParameter("name");
		String minGpa = request.getParameter("minGpa");
		String sortOption = request.getParameter("sortBy");
		System.out.println(name + ", " + minGpa + ", " + sortOption);
		request.setAttribute("name", name);
		request.setAttribute("minGpa", minGpa);
		Writer out = response.getWriter();
		response.setContentType("text/json");
		try
		{
			List<StudentBean> result = engine.doSis(name, minGpa, sortOption);
			Gson gson = new Gson();
			String json = gson.toJson(result, ArrayList.class);
			out.write("{\"status\":1, \"result\":" + json + "}");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.write("{\"status\":0, \"error\":\"" + e.getMessage() + "\"}");
		}
	} 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
