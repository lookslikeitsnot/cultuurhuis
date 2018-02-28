package be.vdab.valueObjects;

public class Reservatie {
	private long id;
	private long klantenid;
	private long voorstellingsid;
	private long plaatsen;
	public Reservatie(long id, long klantenid, long voorstellingsid, long plaatsen) {
		this.id = id;
		this.klantenid = klantenid;
		this.voorstellingsid = voorstellingsid;
		this.plaatsen = plaatsen;
	}
	public long getId() {
		return id;
	}
	public long getKlantenid() {
		return klantenid;
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
		result = prime * result + (int) (klantenid ^ (klantenid >>> 32));
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
		if (klantenid != other.klantenid)
			return false;
		if (plaatsen != other.plaatsen)
			return false;
		if (voorstellingsid != other.voorstellingsid)
			return false;
		return true;
	}
	
	
}
