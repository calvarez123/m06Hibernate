package com.project;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        Manager.createSessionFactory();

        Ciutat ciu0 = Manager.addCiutat("Vancouver", "Canada", 98661);
        Ciutat ciu1 = Manager.addCiutat("Växjö", "Suècia", 35220);
        Ciutat ciu2 = Manager.addCiutat("Kyoto", "Canada", 5200461);

        Manager.addCiutada(ciu0.getCiutatId(), "Tony", "Happy", 20);
        Ciutada pep1 = Manager.addCiutada(ciu1.getCiutatId(), "Monica", "Mouse", 22);
        Manager.addCiutada(ciu1.getCiutatId(), "Eirika", "Erjo", 44);
        Ciutada pep3 = Manager.addCiutada(ciu1.getCiutatId(), "Ven", "Enrison", 48);
        Manager.addCiutada(ciu2.getCiutatId(), "Akira", "Akiko", 62);
        Ciutada pep5 = Manager.addCiutada(ciu2.getCiutatId(), "Masako", "Kubo", 66);

        Collection<?> ciutats = Manager.listCollection(Ciutat.class, "");
        for (Object obj : ciutats) {
            Ciutat cObj = (Ciutat) obj;
            System.out.println("Ciutadans de " + cObj.getNom() + ":");
            Collection<?> ciutadans = Manager.listCollection(Ciutada.class,
                    "ciutatId=" + cObj.getCiutatId());
            for (Object obj2 : ciutadans) {
                Ciutada cObj2 = (Ciutada) obj2;
                System.out.println("    " + cObj2.toString());
            }
        }

        Manager.delete(Ciutada.class, pep1.getCiutadaId());
        Manager.delete(Ciutada.class, pep3.getCiutadaId());
        Manager.delete(Ciutada.class, pep5.getCiutadaId());
        Manager.delete(Ciutat.class, ciu1.getCiutatId());

        System.out.println("Ciutats:");
        Collection<?> llista = Manager.listCollection(Ciutat.class, "");
        for (Object obj : ciutats) {
            Ciutat cObj = (Ciutat) obj;
            System.out.println("  " + cObj);
        }
        System.out.println("Ciutadans:");
        llista = Manager.listCollection(Ciutada.class, "");
        for (Object obj : llista) {
            Ciutada cObj = (Ciutada) obj;
            System.out.println("  " + cObj);
        }

        Manager.close();
    }
}
