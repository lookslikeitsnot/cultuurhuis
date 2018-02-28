package be.vdab.entities;

public class Gemeente {
	private String naam;
	private String postcode;
	public Gemeente(String naam, String postcode) {
		this.naam = naam;
		this.postcode = postcode;
	}
	public String getNaam() {
		return naam;
	}
	public String getPostcode() {
		return postcode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Gemeente))
			return false;
		Gemeente other = (Gemeente) obj;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return postcode + " " + naam;
	}
}
