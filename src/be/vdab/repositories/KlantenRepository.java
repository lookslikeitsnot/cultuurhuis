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
	private static final String BY_GEBRUIKERSNAAM = " WHERE gebruikersnaam=?";
	private static final String AND_PASWOORD = " AND paswoord=?";
	private static final String INSERT_KLANT = "INSERT INTO klanten("
			+ "voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord) "
			+ "VALUES (?,?,?,?,?,?,?,?)";

	public Optional<Klant> getKlantByGebruikersnaamEnPaswoord(String gebruikersnaam, String paswoord) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BEGIN + BY_GEBRUIKERSNAAM + AND_PASWOORD)) {
			Optional<Klant> klant;
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, gebruikersnaam);
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
	public boolean gebruikersnaamExists(String gebruikersnaam) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BEGIN + BY_GEBRUIKERSNAAM)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, gebruikersnaam);
			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
			finally {
				connection.commit();
			}
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
			
		
	}
	
	public void insertKlant(Klant klant) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_KLANT)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setString(1, klant.getVoornaam());
			statement.setString(2, klant.getFamilienaam());
			statement.setString(3, klant.getAdres().getStraat());
			statement.setString(4, klant.getAdres().getHuisnr());
			statement.setString(5, klant.getAdres().getGemeente().getPostcode());
			statement.setString(6, klant.getAdres().getGemeente().getNaam());
			statement.setString(7, klant.getGebruikersnaam());
			statement.setString(8, klant.getPaswoord());
			statement.executeUpdate();
			connection.commit();
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
