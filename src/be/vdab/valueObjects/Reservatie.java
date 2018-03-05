package be.vdab.valueObjects;

public class Reservatie {
	private long id;
	private long klantid;
	private long voorstellingsid;
	private int plaatsen;
	public Reservatie(long voorstellingsid, int plaatsen) {
		this.voorstellingsid = voorstellingsid;
		this.plaatsen = plaatsen;
	}
	public void setKlantid(long klantid) {
		this.klantid = klantid;
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
	public int getPlaatsen() {
		return plaatsen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (klantid ^ (klantid >>> 32));
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
		if (voorstellingsid != other.voorstellingsid)
			return false;
		return true;
	}
	
	
}
