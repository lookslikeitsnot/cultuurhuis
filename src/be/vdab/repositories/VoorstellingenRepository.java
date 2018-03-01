package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import be.vdab.entities.Voorstelling;
import be.vdab.exceptions.RepositoryException;

public class VoorstellingenRepository extends AbstractRepository{
	private static final String SELECT = 
			"SELECT id, datum, titel, uitvoerders, genreid, prijs, vrijeplaatsen "
			+ "FROM voorstellingen "; 
	private static final String SELECT_BY_GENRE = 
			SELECT + "WHERE genreid = ? AND datum >= CURDATE()";
	private static final String SELECT_BY_ID = 
			SELECT + "WHERE id = ? ";
	
	public List<Voorstelling> findByGenre(int genreid){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_GENRE)) {
			List<Voorstelling> voorstellingen =  new ArrayList<>();
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setInt(1, genreid);
			try(ResultSet resultSet = statement.executeQuery()){
				while(resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
				}
			}
			connection.commit();
			return voorstellingen;
			
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}
	
	public Optional<Voorstelling> findById(long id){
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
			Optional<Voorstelling> voorstelling;
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					voorstelling = Optional.of(resultSetRijNaarVoorstelling(resultSet));
				} else {
					voorstelling = Optional.empty();
				}
			}
			connection.commit();
			return voorstelling;
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
		
	}
	
	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet) throws SQLException{
		return new Voorstelling(
				resultSet.getLong("id"),
				resultSet.getString("titel"),
				resultSet.getString("uitvoerders"),
				resultSet.getTimestamp("datum").toLocalDateTime(),
				resultSet.getLong("genreid"),
				resultSet.getBigDecimal("prijs"),
				resultSet.getInt("vrijeplaatsen"));
	}
}
