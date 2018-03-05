package be.vdab.valueObjects;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import be.vdab.entities.Voorstelling;
import be.vdab.repositories.VoorstellingenRepository;

public class Mandje {
	Set<Reservatie> mandje;
	
	public Mandje() {
		mandje = new LinkedHashSet<>();
	}
	
	public Set<Reservatie> getMandje() {
		return mandje;
	}
	
	public Map<Voorstelling, Integer> getVoorstellingenEnPlaatsen(VoorstellingenRepository voorstellingenRepository){
		Map<Voorstelling, Integer> voorstellingen = new LinkedHashMap<>();
		for (Reservatie reservatie : mandje) {
			Optional<Voorstelling> voorstelling = voorstellingenRepository.findById(reservatie.getVoorstellingsid());
			if (voorstelling.isPresent()) {
				voorstellingen.put(voorstelling.get(), reservatie.getPlaatsen());
			}
		}
		return voorstellingen;
	}
	
	public BigDecimal getMandjeWaarde(VoorstellingenRepository voorstellingenRepository) {
		BigDecimal mandjeWaarde = BigDecimal.ZERO;
		for (Reservatie reservatie : mandje) {
			BigDecimal voorstellingPrijs = voorstellingenRepository.findById(reservatie.getVoorstellingsid())
					.get().getPrijs();
			mandjeWaarde = mandjeWaarde.add(voorstellingPrijs.multiply(
							BigDecimal.valueOf(reservatie.getPlaatsen())));
		}
		return mandjeWaarde;
	}
	
	public boolean isEmpty() {
		return mandje.isEmpty();
	}
	
	public void remove(long voorstellingId) {
		mandje.removeIf(reservatie -> reservatie.getVoorstellingsid()==voorstellingId);
	}
	
	public boolean containsVoorstelling(long voorstellingId) {
		return mandje.stream().anyMatch(reservatie -> reservatie.getVoorstellingsid()==voorstellingId);
	}
	
	public int get(long voorstellingId) {
		return mandje.stream()
				.filter(reservatie -> reservatie.getVoorstellingsid()==voorstellingId)
				.findAny()
				.get()
				.getPlaatsen();
	}
	
	public void add(Reservatie reservatie) {
		if(mandje.contains(reservatie)) {
			mandje.remove(reservatie);
		}
		mandje.add(reservatie);
	}
}
