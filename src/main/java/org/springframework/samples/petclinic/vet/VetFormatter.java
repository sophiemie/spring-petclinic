package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class VetFormatter implements Formatter<Vet> {

	private final VetRepository vetRepository;

	@Autowired
	public VetFormatter(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public Vet parse(String text, Locale locale) throws ParseException {
			Collection<Vet> findVets = vetRepository.findAll();
			for (Vet vet : findVets) {
				String vetFullName = vet.getFirstName() + " " + vet.getLastName();
				if (vetFullName.equals(text)) {
					return vet;
				}
			}
			throw new ParseException("type not found: " + text, 0);
	}

	@Override
	public String print(Vet vet, Locale locale) {
		return vet.getFirstName() + " " + vet.getLastName();
	}
}
