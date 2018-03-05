package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
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

import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.utils.StringUtils;
import be.vdab.valueObjects.Mandje;
import be.vdab.valueObjects.Reservatie;

@WebServlet("/reservatie.htm")
public class ReservatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatie.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	private static final String REDIRECT_HOME_URL = "/index.htm";
	private static final String MANDJE = "mandje";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getQueryString() != null) {
			Map<String, String> fouten = new HashMap<>();
			String voorstellingidStr = request.getParameter("id");
			if (!StringUtils.isLong(voorstellingidStr)) {
				fouten.put("voorstelling", "Voorstelling niet gevonden");
			} else {
				long voorstellingId = Long.parseLong(voorstellingidStr);
				Optional<Voorstelling> voorstelling = voorstellingenRepository.findById(voorstellingId);
				if (voorstelling.isPresent()) {
					request.setAttribute("voorstelling", voorstelling.get());
					HttpSession session = request.getSession(false);
					if (session != null) {
						Mandje mandje = (Mandje) session.getAttribute(MANDJE);
						if (mandje != null) {
							if (mandje.containsVoorstelling(voorstellingId)) {
								request.setAttribute("alinmandje", mandje.get(voorstellingId));
							}
						}
					}
				} else {
					fouten.put("voorstelling", "Voorstelling niet gevonden");
				}
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}

			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + REDIRECT_HOME_URL);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String aantalPlaatsenStr = request.getParameter("aantalplaatsen");
		String voorstellingIdStr = request.getParameter("id");

		if (!StringUtils.isInt(aantalPlaatsenStr)) {
			fouten.put("aantalplaatsen", "Moet een geheel getal zijn");
		} else {
			int aantalPlaatsen = Integer.parseInt(aantalPlaatsenStr);
			if (StringUtils.isLong(voorstellingIdStr)) {
				long voorstellingId = Long.parseLong(voorstellingIdStr);
				Optional<Voorstelling> voorstelling = voorstellingenRepository.findById(voorstellingId);
				if (voorstelling.isPresent()) {
					int vrijePlaatsen = voorstelling.get().getVrijePlaatsen();
					if (vrijePlaatsen >= aantalPlaatsen && aantalPlaatsen > 0) {
						HttpSession session = request.getSession();

						Mandje mandje = (Mandje) session.getAttribute(MANDJE);
						if (mandje == null) {
							mandje = new Mandje();
						}
						mandje.add(new Reservatie(voorstellingId, aantalPlaatsen));
						session.setAttribute("mandje", mandje);
						response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
					} else {
						fouten.put("aantalplaatsen", "Moet tussen 1 en " + vrijePlaatsen);
					}
				} else {
					fouten.put("id", "Ongeldig");
				}

			} else {
				fouten.put("id", "Ongeldig");
			}

		}
//		if (!fouten.isEmpty()) {
//			response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
//		} else {
			request.setAttribute("fouten", fouten);
//		}
		
	}

}
