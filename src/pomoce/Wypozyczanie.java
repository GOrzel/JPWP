package pomoce;

import biblioteka.Biblioteka;
import biblioteka.Ksiazka;
import biblioteka.Uzytkownik;

/**
 * Created by User on 2016-12-30.rt
 */
public class Wypozyczanie {         //jest ok
    private void wypozycz(String numerFilii,Ksiazka ksiazka, Uzytkownik user) {
        if (!user.isCzyWypozyczyl()) {
            ksiazka.setCzyWypozyczona(true);
            user.setCzyWypozyczyl(true);
            user.setWypozyczonaKsiazka(ksiazka);
            user.setFilia(Integer.parseInt(numerFilii));
        } else Powiadomienie.komunikat("Ten użytkownik już coś wypożyczył", "Błędna operacja");
    }

    private void oddaj(Ksiazka ksiazka, Uzytkownik user) {
        if (user.isCzyWypozyczyl()) {
                ksiazka.setCzyWypozyczona(false);
                user.setCzyWypozyczyl(false);
                user.setWypozyczonaKsiazka(null);
                user.setFilia(-1);
        } else Powiadomienie.komunikat("Ten użytkownik nic nie wypożyczył", "Błędna operacja");
    }

    public int wypozycz_oddaj(Biblioteka biblioteka, String numerFilii, String numerRegalu, String numerKatalogowy, String numerUzytkownika) {
        Ksiazka ksiazka;
        Uzytkownik user;
        if ((!numerFilii.equals("")) && (!numerRegalu.equals("")) && (!numerKatalogowy.equals("")) && (!numerUzytkownika.equals(""))) {
            ksiazka = biblioteka.getFilia(Integer.parseInt(numerFilii)).getRegal(Integer.parseInt(numerRegalu)).getKsiazka(Integer.parseInt(numerKatalogowy));
            user = biblioteka.getUzytkownicy().get(biblioteka.getIndeksUzytkownik(Integer.parseInt(numerUzytkownika)));
            if (ksiazka.isCzyWypozyczona()) {
                if(user.getWypozyczonaKsiazka().equals(ksiazka)) {
                    oddaj(ksiazka,user);
                    return 1;
                } else {
                    Powiadomienie.komunikat("Ten użytkownik wypożyczył coś innego", "Błędna operacja");
                    return -1;
                }
            } else {
                wypozycz(numerFilii, ksiazka, user);
                return 2;
            }
        } else {
            Powiadomienie.komunikat("Podaj wszystkie dane", "Niedobór danych");
            return -1;
        }
    }
}
