package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.entities.Genre;
import be.vdab.entities.Voorstelling;
import be.vdab.repositories.AbstractRepository;
import be.vdab.repositories.GenresRepository;
import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.utils.StringUtils;



@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenresRepository genresRepository = 
			new GenresRepository();
	private final transient VoorstellingenRepository voorstellingenRepository = 
			new VoorstellingenRepository();
	
	@Resource(name = AbstractRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		genresRepository.setDataSource(dataSource);
		voorstellingenRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Genre> genres = genresRepository.findAll();
		request.setAttribute("genres", genres);
		Map<String, String> fouten = new HashMap<>();
		
		if(request.getQueryString() != null) {
			
			String genreid = request.getParameter("genreid");
			if(!StringUtils.isInt(genreid)) {
				fouten.put("genreid", "Genre niet gevonden");
			} else {
				List<Voorstelling> voorstellingen = 
						voorstellingenRepository.findByGenre(Integer.parseInt(genreid));
				if(voorstellingen.isEmpty()) {
					fouten.put("voorstellingen", "Geen voorstellingen gevonden");
				} else {
					request.setAttribute("genre", genresRepository.findById(Integer.parseInt(genreid)).get());
					request.setAttribute("voorstellingen", voorstellingen);
				}
				
			}
		}
		if(!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	
	}
}
