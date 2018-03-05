package be.vdab.valueObjects;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingenRepository;

public class Mandje {
	Map<Long, Integer> mandje;
	
	public Mandje() {
		mandje = new LinkedHashMap<>();
	}
	
	public Map<Long, Integer> getMandje() {
		return mandje;
	}
	
	public Map<Voorstelling, Integer> getVoorstellingenEnPlaatsen(VoorstellingenRepository voorstellingenRepository){
		Map<Voorstelling, Integer> voorstellingen = new LinkedHashMap<>();
		for (Map.Entry<Long, Integer> voorstellingEnPlaatsen : mandje.entrySet()) {
			Voorstelling voorstelling = 
					voorstellingenRepository
					.findById(voorstellingEnPlaatsen.getKey())
					.get();
			if (voorstelling != null) {
				voorstellingen.put(voorstelling, voorstellingEnPlaatsen.getValue());
			}
		}
		return voorstellingen;
	}
	
	public BigDecimal getMandjeWaarde(VoorstellingenRepository voorstellingenRepository) {
		BigDecimal mandjeWaarde = BigDecimal.ZERO;
		for (Map.Entry<Long, Integer> voorstellingEnPlaatsen : mandje.entrySet()) {
			BigDecimal voorstellingPrijs = 
					voorstellingenRepository
					.findById(voorstellingEnPlaatsen.getKey())
					.get()
					.getPrijs();
			mandjeWaarde = mandjeWaarde.add(
					voorstellingPrijs.multiply(
							BigDecimal.valueOf(
									voorstellingEnPlaatsen.getValue())));
		}
		return mandjeWaarde;
	}
	
	public boolean isEmpty() {
		return mandje.isEmpty();
	}
	
	public void remove(long voorstellingId) {
		mandje.remove(voorstellingId);
	}
	
	public boolean containsVoorstelling(long voorstellingId) {
		return mandje.containsKey(voorstellingId);
	}
	
	public int get(long voorstellingId) {
		return mandje.get(voorstellingId);
	}
	
	public void put(long voorstellingId, int aantalGereserveerdePlaatsen) {
		mandje.put(voorstellingId, aantalGereserveerdePlaatsen);
	}
}
