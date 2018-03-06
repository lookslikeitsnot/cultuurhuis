package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.Klant;
import be.vdab.repositories.KlantenRepository;

@WebServlet("/bevestig.htm")
public class BevestigingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private final transient KlantenRepository klantenRepository = new KlantenRepository();

	@Resource(name = KlantenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		klantenRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		HttpSession session = request.getSession(false);
		Map<String, String> fouten = new LinkedHashMap<>();
		if (session != null) {
			Optional<Klant> optionalKlant = klantenRepository.getKlantByGebruikersnaamEnPaswoord(gebruikersnaam,
					paswoord);
			if (optionalKlant.isPresent()) {
				session.setAttribute("klant", optionalKlant.get());
				response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
			} else {
				fouten.put("bevestig", "Verkeerde gebruikersnaam of paswoord.");
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			request.getRequestDispatcher(VIEW).forward(request, response);
		}

	}
}
