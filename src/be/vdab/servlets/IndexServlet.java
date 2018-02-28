package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Genre;
import be.vdab.repositories.GenresRepository;

@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenresRepository genresRepository = 
			new GenresRepository();
	
	@Resource(name = GenresRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		genresRepository.setDataSource(dataSource);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Genre> genres = genresRepository.findAll();
		request.setAttribute("genres", genres);
		request.getRequestDispatcher(VIEW).forward(request, response);
	
	}
}
