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
import be.vdab.entities.Voorstelling;
import be.vdab.exceptions.RepositoryException;
import be.vdab.repositories.AbstractRepository;
import be.vdab.repositories.ReservatiesRepository;
import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.valueObjects.Mandje;
import be.vdab.valueObjects.Reservatie;

@WebServlet("/overzicht.htm")
public class OverzichtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/overzicht.jsp";
	private final transient ReservatiesRepository reservatiesRepository = new ReservatiesRepository();
	private final transient VoorstellingenRepository voorstellingenRepository = new VoorstellingenRepository();
	private static final String MANDJE = "mandje";

	@Resource(name = AbstractRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		reservatiesRepository.setDataSource(dataSource);
		voorstellingenRepository.setDataSource(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		mandjeWegSchrijven(request);
		if (request.getAttribute("gelukt") == null && request.getAttribute("mislukt") == null) {
			response.sendRedirect(request.getContextPath());
		} else {
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

	private void mandjeWegSchrijven(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Mandje mandje = (Mandje) session.getAttribute(MANDJE);
		Klant klant = (Klant) session.getAttribute("klant");
		if (mandje != null && !mandje.isEmpty() && klant != null) {
			Map<Voorstelling, Integer> gelukt = new LinkedHashMap<>();
			Map<Voorstelling, Integer> mislukt = new LinkedHashMap<>();
			for (Reservatie reservatie : mandje.getMandje()) {
				if (reservatie != null) {
					Optional<Voorstelling> optioneleVoorstelling = voorstellingenRepository
							.findById(reservatie.getVoorstellingsid());
					if (optioneleVoorstelling.isPresent()) {
						Voorstelling voorstelling = optioneleVoorstelling.get();
						try {
							reservatie.setKlantid(klant.getId());
							reservatiesRepository.nieuweReservatie(reservatie);
							voorstellingenRepository.vrijePlaatsenVerminderen(reservatie.getVoorstellingsid(),
									reservatie.getPlaatsen());
							gelukt.put(voorstelling, reservatie.getPlaatsen());
						} catch (RepositoryException ex) {
							mislukt.put(voorstelling, reservatie.getPlaatsen());
						}
					}
				}
			}
			request.setAttribute("gelukt", gelukt);
			request.setAttribute("mislukt", mislukt);
			session.removeAttribute("mandje");
		}
	}
}
