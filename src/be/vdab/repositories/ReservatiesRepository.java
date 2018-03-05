package be.vdab.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import be.vdab.exceptions.RepositoryException;
import be.vdab.valueObjects.Reservatie;

public class ReservatiesRepository extends AbstractRepository {
	private static final String INSERT_RESERVATIE = "INSERT INTO " + "reservaties(klantid, voorstellingsid, plaatsen) "
			+ "VALUES(?,?,?)";

	public void insertReservatie(Reservatie reservatie) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_RESERVATIE)) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			statement.setLong(1, reservatie.getKlantid());
			statement.setLong(2, reservatie.getVoorstellingsid());
			statement.setLong(3, reservatie.getPlaatsen());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException ex) {
			throw new RepositoryException(ex);
		}
	}

}
