package watek;

import biblioteka.Biblioteka;
import pomoce.*;
import java.util.Random;

/**
 * Created by User on 2017-01-16.uu
 */
public class WatekWypozyczanie extends Thread implements PrzekazBiblioteki,Kontrolowany,Runnable{
    private Biblioteka biblioteka;
    private Listener listener;
    private int typ;
    boolean przelacznik = false;
    public void odbierz(Biblioteka biblioteka) {this.biblioteka = biblioteka;}
    public void subscribe(Listener o) { listener =o; }
    public void przelacz(boolean tryb) {this.przelacznik= tryb;}
    Random generator = new Random();
    Wypozyczanie wypozycz = new Wypozyczanie();
    public void run(){
        int numerFilii;
        int numerRegalu;
        int numerKatalogowy;
        int numerUzytkownika;
        int indeksFilii;
        int indeksRegalu;
        int indeksKatalogowy;
        int indeksUzytkownika;
        while(przelacznik) {
            try {                   //puste filiie, klasy
                if(biblioteka.getLiczbaKsiazekBiblioteki()>0) {
                    indeksUzytkownika = generator.nextInt(biblioteka.getUzytkownicy().size());
                    if (!(biblioteka.getUzytkownicy().get(indeksUzytkownika).isCzyWypozyczyl())) {        //wypozycz
                        do {
                            indeksFilii = generator.nextInt(biblioteka.getFilie().size());
                            indeksRegalu = generator.nextInt(biblioteka.getFilie().get(indeksFilii).getRegaly().size());
                            indeksKatalogowy = generator.nextInt(biblioteka.getFilie().get(indeksFilii).getRegaly().get(indeksRegalu).getKsiazki().size());
                            numerFilii = biblioteka.getFilie().get(indeksFilii).getNumerFilii();
                            numerRegalu = biblioteka.getFilia(numerFilii).getRegaly().get(indeksRegalu).getNumerRegalu();
                            numerKatalogowy = biblioteka.getFilia(numerFilii).getRegal(numerRegalu).getKsiazki().get(indeksKatalogowy).getNumerKatalogu();
                            numerUzytkownika = biblioteka.getUzytkownicy().get(indeksUzytkownika).getNumerUzytkownika();
                        }
                        while (biblioteka.getFilia(numerFilii).getRegal(numerRegalu).getKsiazka(numerKatalogowy).isCzyWypozyczona());
                    } else {          //oddaje
                        numerFilii = biblioteka.getUzytkownicy().get(indeksUzytkownika).getFilia();
                        numerRegalu = biblioteka.getFilia(numerFilii).getNumerRegaluOdKsiazki(biblioteka.getUzytkownicy().get(indeksUzytkownika).getWypozyczonaKsiazka());
                        numerKatalogowy = biblioteka.getUzytkownicy().get(indeksUzytkownika).getWypozyczonaKsiazka().getNumerKatalogu();
                        numerUzytkownika = biblioteka.getUzytkownicy().get(indeksUzytkownika).getNumerUzytkownika();
                    }
                    typ = wypozycz.wypozycz_oddaj(biblioteka, String.valueOf(numerFilii), String.valueOf(numerRegalu), String.valueOf(numerKatalogowy), String.valueOf(numerUzytkownika));
                    listener.zmiana(numerFilii, numerRegalu, numerKatalogowy, indeksUzytkownika, typ);
                    Thread.sleep(1000);
                } else {
                    Powiadomienie.komunikat("Biblioteka nie zawiera książek","Nieprawidłowa operacja");
                    break;
                }
            } catch (InterruptedException e) {
                Powiadomienie.komunikat("Błąd działania watku","Zakłócenie watku");
            }
        }

    }
}
