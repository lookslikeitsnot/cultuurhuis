package be.vdab.repositories;

public class KlantenRepository extends AbstractRepository{
	private static final String SELECT_BEGIN = 
			"select id, voornaam, familienaam, straat, huisnr,"
			+ " postcode, gemeente, gebruikersnaam from klanten";
}
