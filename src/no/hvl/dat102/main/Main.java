package no.hvl.dat102.main;

import no.hvl.dat102.kjedet.KjedetOrdnetListe;
import no.hvl.dat102.listeklient.Person;
import no.hvl.dat102.tabell.TabellOrdnetListe;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KjedetOrdnetListe<Person> personer = new KjedetOrdnetListe<>();
        TabellOrdnetListe<Person> tabellPersoner = new TabellOrdnetListe<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, how many people do you want to add to the list?");
        int numberOfPersons = scanner.nextInt();
        int index = 0;
        while (index < numberOfPersons) {
            System.out.println("Hello, please give information regarding the person that you want to add");
            System.out.print("First name: ");
            String firstName = scanner.next();
            System.out.print("Last name: ");
            String lastName = scanner.next();
            System.out.print("Year of birth: ");
            int yearOfBirth = scanner.nextInt();
            Person loopPerson = new Person(firstName, lastName, yearOfBirth);
            personer.leggTil(loopPerson);
            tabellPersoner.leggTil(loopPerson);
            index++;
        }
        System.out.println(personer);
        System.out.println(tabellPersoner);
    }
}
