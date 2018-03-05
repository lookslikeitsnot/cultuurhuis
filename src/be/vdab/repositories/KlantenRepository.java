package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import be.vdab.entities.Gemeente;
import be.vdab.entities.Klant;
import be.vdab.exceptions.RepositoryException;
import be.vdab.valueObjects.Adres;

public class KlantenRepository extends AbstractRepository {
	private static final String SELECT_BEGIN = "select id, voornaam, familienaam, straat, huisnr,"
			+ " postcode, gemeente, gebruikersnaam, paswoord from klanten";
	private static final String BY_GEBRUIKERSNAAM_WITH_PASWOORD = 
			" WHERE gebruikersnaam=? AND paswoord=?";

	public Optional<Klant> getKlantByNaamEnPaswoord(String naam, String paswoord) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BEGIN + BY_GEBRUIKERSNAAM_WITH_PASWOORD)) {
			Optional<Klant> klant;
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, naam);
			statement.setString(2, paswoord);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					klant = Optional.of(resultSetRijNaarKlant(resultSet));
				} else {
					klant = Optional.empty();
				}
			}
			connection.commit();
			return klant;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}

	}
	
	private Klant resultSetRijNaarKlant(ResultSet resultSet) throws SQLException {
		return new Klant(
				resultSet.getLong("id")
				,resultSet.getString("voornaam")
				,resultSet.getString("familienaam")
				,new Adres(
						resultSet.getString("straat")
						,resultSet.getString("huisnr")
						,new Gemeente(
								resultSet.getString("gemeente")
								,resultSet.getString("postcode")))
				,resultSet.getString("gebruikersnaam")
				,resultSet.getString("paswoord"));
	}
}
