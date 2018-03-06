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
import javax.sql.DataSource;

import be.vdab.entities.Gemeente;
import be.vdab.entities.Klant;
import be.vdab.repositories.KlantenRepository;
import be.vdab.utils.StringUtils;
import be.vdab.valueObjects.Adres;

@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
	private static final String REDIRECT_URL = "/bevestig.htm";
	private final transient KlantenRepository klantenRepository = 
			new KlantenRepository();
	
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
		Optional<Klant> klant = maakKlant(request);
		if(klant.isPresent()) {
			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		} else {
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}
	
	private Optional<Klant> maakKlant(HttpServletRequest request){
		Optional<Klant> optionalKlant;
		Map<String,String> fouten = new LinkedHashMap<>();
		String voornaam = request.getParameter("voornaam");
		String familienaam = request.getParameter("familienaam");
		String straat = request.getParameter("straat");
		String huisnr = request.getParameter("huisnr");
		String postcode = request.getParameter("postcode");
		String gemeente = request.getParameter("gemeente");
		String gebruikersnaam = request.getParameter("gebruikersnaam");
		String paswoord = request.getParameter("paswoord");
		String paswoordHerhaald = request.getParameter("paswoordHerhaald");
		if(!StringUtils.isStringValid(voornaam)) {
			fouten.put("Voornaam","niet ingevuld");
		}
		if(!StringUtils.isStringValid(familienaam)) {
			fouten.put("Familienaam","niet ingevuld");
		}
		if(!StringUtils.isStringValid(straat)) {
			fouten.put("Straat","niet ingevuld");
		}
		if(!StringUtils.isStringValid(huisnr)) {
			fouten.put("Huisnr","niet ingevuld");
		}
		if(!StringUtils.isStringValid(postcode)) {
			fouten.put("Postcode","niet ingevuld");
		}
		if(!StringUtils.isStringValid(gemeente)) {
			fouten.put("Gemeente","niet ingevuld");
		}
		if(!StringUtils.isStringValid(gebruikersnaam)) {
			fouten.put("Gebruikersnaam","niet ingevuld");
		}
		if(!StringUtils.isStringValid(paswoord)) {
			fouten.put("Paswoord","niet ingevuld");
		}
		if(!StringUtils.isStringValid(paswoordHerhaald)) {
			fouten.put("Herhaalde Paswoord","niet ingevuld");
		}
		if(fouten.isEmpty() && !paswoord.equals(paswoordHerhaald)) {
			fouten.put("Passwoorden", "zijn verschillend");
		}
		if(fouten.isEmpty() && klantenRepository.gebruikersnaamExists(gebruikersnaam)) {
			fouten.put("Gebruikersnaam", "bestaat al");
		}
		if(fouten.isEmpty()) {
			Klant klant = new Klant(
					voornaam
					,familienaam
					,new Adres(
							straat
							,huisnr
							,new Gemeente(
									gemeente
									,postcode))
					,gebruikersnaam
					,paswoord
					);
			klantenRepository.nieuweKlant(klant);
			optionalKlant = Optional.of(klant);
					
		} else {
			optionalKlant = Optional.empty();
		}
		request.setAttribute("fouten", fouten);
		return optionalKlant;
	}
}
