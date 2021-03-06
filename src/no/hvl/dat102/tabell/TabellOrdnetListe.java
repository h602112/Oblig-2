package no.hvl.dat102.tabell;

import no.hvl.dat102.adt.OrdnetListeADT;
import no.hvl.dat102.exceptions.EmptyCollectionException;

public class TabellOrdnetListe<T extends Comparable<T>> implements OrdnetListeADT<T> {

	private final static int STDK = 100;
	private final static int IKKE_FUNNET = -1;
	private int bak;
	private T[] liste;

	public TabellOrdnetListe() {
		this(STDK);
	}

	public TabellOrdnetListe(int startKapasitet) {
		bak = 0;
		liste = (T[]) (new Comparable[startKapasitet]);
	}

	@Override
	public T fjernSiste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		if (foerste().equals(siste())) {
			resultat = foerste();
		}
		if (antall() == 0) {
			return resultat;
		} else {
			resultat = liste[bak];
			liste[bak] = liste[bak-1];
			bak--;

		}
		return resultat;
	}

	@Override
	public T fjernFoerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = null;
		resultat = liste[0];
		bak--;
		for (int i = bak; i <= bak && i >= 0; i--) {
			liste[i] = liste[i-1];
		}
		return resultat;
	}

	@Override
	public T foerste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");

		T resultat = liste[0];
		return resultat;
	}

	@Override
	public T siste() {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		
		return liste[bak-1];
	}

	@Override
	public boolean erTom() {
		return (bak == 0);
	}

	@Override
	public int antall() {
		return bak;
	}

	@Override
	public void leggTil(T element) {
		if (bak == liste.length) {
			utvid();
		}
		int i = 0;
		while (i < bak && element.compareTo(liste[i]) > 0) {
			i++;
		}
		for(int j = bak+1; j > i; j--) {
			liste[j] = liste[j-1];
		}
		liste[i] = element;
		bak++;

	}

	@Override
	public boolean inneholder(T element) {
		return (finn(element) != IKKE_FUNNET);
	}

	@Override
	public T fjern(T element) {
		if (erTom())
			throw new EmptyCollectionException("ordnet liste");
		T resultat = null;
		int indeks = finn(element);
		if (indeks != IKKE_FUNNET) {
			resultat = liste[indeks];
			bak--;
			for (int i = indeks; i < bak; i++) {
				liste[i] = liste[i + 1];
			}
			liste[bak] = null;
		}
		return resultat;

	}

	private int finn(T el) {
		int i = 0, resultat = IKKE_FUNNET;
		// ...Fyll ut
		if (foerste() == null) {
			return 0;
		}
		int mid = ((bak) / 2);
		int stoerste = bak;
		boolean run = true;
		while (run && el != null) {
			if(el.compareTo(liste[i]) == 0){
				run =  false;
				resultat = i;
			}else if(el.compareTo(liste[i]) < 0){
				run = false;
			}
			i++;
			/* Fors??k p?? en bin??rs??k l??sning
			if (liste[mid].equals(el)) {
				resultat = mid;
			} else if (el.compareTo(liste[mid]) < 0) {
				stoerste = mid;
				mid /= 2;

			} else {
				mid = stoerste-mid / 2;
			}

			 */
		}
		return resultat;
	}

	public String toString() {
		String resultat = "";

		for (int i = 0; i < bak; i++) {
			resultat = resultat + liste[i].toString() + "\n";
		}
		return resultat;
	}

	private void utvid() {
		T[] hjelpeTabell = (T[]) (new Comparable[liste.length * 2]);

		for (int i = 0; i < liste.length; i++) {
			hjelpeTabell[i] = liste[i];
		}

		liste = hjelpeTabell;
	}

}// class
