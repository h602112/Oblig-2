package no.hvl.dat102.kjedet;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

/**
 * 
 * @param <T> elementypen
 */
public class KjedetOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {
	private int antall;
	private LinearNode<T> foerste, siste;

	/**
	 * Lager en ny tom liste.
	 */
	public KjedetOrdnetListe() {
		antall = 0;
		foerste = null;
		siste = null;
	}

	@Override
	public T fjernFoerste() {
		T resultat = null;
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		if (foerste == null) {
			return null;
		}
		if (foerste != null) {
			resultat = foerste.getElement();
			foerste = foerste.getNeste();
			antall--;
		}


		return resultat;
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		if (foerste.equals(siste)) {
			resultat = foerste.getElement();
		}
		if (antall == 0) {
			foerste = null;
			siste = null;
		} else {
			siste = foerste;
			for (int i = 1; i < antall; i++) {
				siste = siste.getNeste();
			}
			resultat = siste.getElement();
			siste.setNeste(null);
			antall--;
		}
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T svar = foerste.getElement();

		return svar;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = siste.getElement();

		return resultat;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public int antall() {
		return antall;
	}

	@Override
	public void leggTil(T element) {
		LinearNode<T> newNode = new LinearNode<>();
		LinearNode<T> current = foerste;
		LinearNode<T> previous = null;
		while (current != null && (current.getElement().compareTo(element) != 0
				|| current.getElement().compareTo(element) != -1)) {
			previous = current;
			current = current.getNeste();
		}
		if (previous == null) {
			foerste = newNode;
		} else {
			previous.getNeste() = newNode;
		}
		newNode.getNeste() = current;





		// ...Fyll ut
	}

	@Override
	public T fjern(T element) {
		T svar = null;
		LinearNode<T> forrige = null, denne = foerste;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			forrige = denne;
			denne = denne.getNeste();
		}
		if (denne != null && element.equals(denne.getElement())) { // funnet
			antall--;
			svar = denne.getElement();
			if (forrige == null) { // Første element
				foerste = foerste.getNeste();
				if (foerste == null) { // Tom liste
					siste = null;
				}
			} else { // Inni listen eller bak
				forrige.setNeste(denne.getNeste());
				if (denne == siste) { // bak
					siste = forrige;
				}
			}
		} // ikke-funn
		return svar;
	}

	@Override
	public boolean inneholder(T element) {
		LinearNode<T> denne = foerste;
		boolean resultat = false;
		while (denne != null && element.compareTo(denne.getElement()) > 0) {
			denne = denne.getNeste();
		}
		if (denne != null) {
			if (element.equals(denne.getElement())) {
				resultat = true;
			}
		} // ikke-funn
		return resultat;
	}

}// class
