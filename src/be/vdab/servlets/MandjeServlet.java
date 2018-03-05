package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.utils.StringUtils;
import be.vdab.valueObjects.Mandje;

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
		Map<String, String> fouten = new LinkedHashMap<>();
		HttpSession session = request.getSession(false);
		if (session != null) {
			Mandje mandje = (Mandje) session.getAttribute(MANDJE);
			if (mandje != null && !mandje.isEmpty()) {
				request.setAttribute("voorstellingenEnPlaatsen",
						mandje.getVoorstellingenEnPlaatsen(voorstellingenRepository));
				request.setAttribute("mandjeWaarde", mandje.getMandjeWaarde(voorstellingenRepository));
			} else {
				fouten.put("mandje", "Het mandje bevat geen voorstellingen");
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
		String[] voorstellingIdStrings = request.getParameterValues("voorstellingIds");
		if (session != null && voorstellingIdStrings != null) {
			Mandje mandje = (Mandje) session.getAttribute(MANDJE);
			if (mandje != null) {
				for (String voorstellingIdString : voorstellingIdStrings) {
					if (StringUtils.isLong(voorstellingIdString)) {
						mandje.remove(Long.parseLong(voorstellingIdString));
					}
				}
				if (!mandje.isEmpty()) {
					session.setAttribute(MANDJE, mandje);
				} else {
					session.removeAttribute(MANDJE);
				}

			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}

}
