package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/mandje.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/mandje.jsp";
	private static final String MANDJE = "mandje";
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();

	@Resource(name = VoorstellingenRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		voorstellingenRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			List<Voorstelling> voorstellingen = new ArrayList<>();
			if (mandje != null) {
				for (Map.Entry<Long, Integer> voorstellingEnPlaatsen : mandje.entrySet()) {
					Voorstelling voorstelling = voorstellingenRepository.findById(voorstellingEnPlaatsen.getKey())
							.get();
					if (voorstelling != null) {
						voorstelling.setGereserveerdePlaatsen(voorstellingEnPlaatsen.getValue());
						voorstellingen.add(voorstelling);
					}
				}
				if (mandje.isEmpty()) {
					fouten.put("mandje", "Het mandje bevat geen voorstellingen!");
				}
				request.setAttribute("voorstellingen", voorstellingen);
			} else {
				fouten.put("mandje", "Het mandje is nog niet gemaakt");
			}
		} else {
			fouten.put("mandje", "Het mandje kan niet gemaakt worden. Check uw browser instellingen");
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String[] voorstellingIdStrings = request.getParameterValues("voorstellingId");
		if (session != null && voorstellingIdStrings != null) {
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				for (String voorstellingIdString : voorstellingIdStrings) {
					if (StringUtils.isLong(voorstellingIdString)) {
						mandje.remove(Long.parseLong(voorstellingIdString));
					}
				}
				session.setAttribute("mandje", mandje);
				
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

}
