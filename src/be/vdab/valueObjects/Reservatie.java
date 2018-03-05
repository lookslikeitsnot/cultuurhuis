package be.vdab.valueObjects;

public class Reservatie {
	private long id;
	private long klantid;
	private long voorstellingsid;
	private long plaatsen;
	public Reservatie(long klantid, long voorstellingsid, long plaatsen) {
		this.klantid = klantid;
		this.voorstellingsid = voorstellingsid;
		this.plaatsen = plaatsen;
	}
	public long getId() {
		return id;
	}
	public long getKlantid() {
		return klantid;
	}
	public long getVoorstellingsid() {
		return voorstellingsid;
	}
	public long getPlaatsen() {
		return plaatsen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (klantid ^ (klantid >>> 32));
		result = prime * result + (int) (plaatsen ^ (plaatsen >>> 32));
		result = prime * result + (int) (voorstellingsid ^ (voorstellingsid >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reservatie))
			return false;
		Reservatie other = (Reservatie) obj;
		if (id != other.id)
			return false;
		if (klantid != other.klantid)
			return false;
		if (plaatsen != other.plaatsen)
			return false;
		if (voorstellingsid != other.voorstellingsid)
			return false;
		return true;
	}
	
	
}
