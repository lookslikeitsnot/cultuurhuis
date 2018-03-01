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
import javax.sql.DataSource;

import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingenRepository;
import be.vdab.utils.StringUtils;

@WebServlet("/reservatie.htm")
public class ReservatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatie.jsp";
	private static final String REDIRECT_URL = "/index.htm";
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
			String voorstellingid = request.getParameter("id");
			if (!StringUtils.isLong(voorstellingid)) {
				fouten.put("voorstelling", "Voorstelling niet gevonden");
			} else {
				Optional<Voorstelling> voorstelling = voorstellingenRepository
						.findById(Long.parseLong(voorstellingid));
				if (voorstelling.isPresent()) {
					request.setAttribute("voorstelling", voorstelling.get());
				} else {
					fouten.put("voorstelling", "Voorstelling niet gevonden");
				}
			}
			if (!fouten.isEmpty()) {
				request.setAttribute("fouten", fouten);
			}

			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		}
	}
}
