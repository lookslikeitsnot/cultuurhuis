package be.vdab.entities;

public class Genre {
	private long id;
	private String genre;
	public Genre(long id, String genre) {
		this.id = id;
		this.genre = genre;
	}
	public long getId() {
		return id;
	}
	public String getGenre() {
		return genre;
	}
}
