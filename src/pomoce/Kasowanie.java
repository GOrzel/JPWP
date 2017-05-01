package pomoce;

import biblioteka.Biblioteka;

/**r
 * Created by User on 2016-12-28.
 */
public class Kasowanie{ //JEST OK
    public static int kasujObiekt( Biblioteka danaBiblioteka2, String numerFilii2, String numerRegalu2, String numerKatalogowy2, String numerUzytkownika2) {
        Testery testuj = new Testery();
        try {
            if ( numerFilii2.equals("") ) {
                if ( ( !numerUzytkownika2.equals("") ) && numerRegalu2.equals("") && numerKatalogowy2.equals("")) {
                    if ( testuj.testUzytkownik(danaBiblioteka2, Integer.parseInt(numerUzytkownika2)) ) {
                        if(danaBiblioteka2.getUzytkownicy().get(danaBiblioteka2.getIndeksUzytkownik( Integer.parseInt(numerUzytkownika2) )).isCzyWypozyczyl())  danaBiblioteka2.getUzytkownicy().get(danaBiblioteka2.getIndeksUzytkownik( Integer.parseInt(numerUzytkownika2) )).getWypozyczonaKsiazka().setCzyWypozyczona(false);
                        danaBiblioteka2.getUzytkownicy().remove(danaBiblioteka2.getIndeksUzytkownik(Integer.parseInt(numerUzytkownika2)));
                        return 4;                                                                                       //kasuj uzytkownika
                    } else Powiadomienie.komunikat("Nie istnieje taki użytkownik", "Niepoprawne dane");
                }else Powiadomienie.komunikat("Zły zestaw danych", "Niedobór danych");
            } else {
                if (testuj.testFilia(danaBiblioteka2, Integer.parseInt(numerFilii2))) {
                        if (!numerRegalu2.equals("")) {
                            if(testuj.testRegal(danaBiblioteka2, Integer.parseInt(numerFilii2), Integer.parseInt(numerRegalu2))) {
                                if (!numerKatalogowy2.equals("")) {
                                    if(testuj.testKsiazka(danaBiblioteka2,Integer.parseInt(numerFilii2),Integer.parseInt(numerRegalu2),Integer.parseInt(numerKatalogowy2))) {
                                        danaBiblioteka2.getFilia(Integer.parseInt(numerFilii2)).getRegal(Integer.parseInt(numerRegalu2)).getKsiazki().remove(danaBiblioteka2.getFilia(Integer.parseInt(numerFilii2)).getRegal(Integer.parseInt(numerRegalu2)).getKsiazka(Integer.parseInt(numerKatalogowy2)));
                                        return 2;                                                                       //kasuj ksiazke
                                    }else Powiadomienie.komunikat("Nie istnieje taka książka","Niepoprawne dane");
                                } else {
                                        danaBiblioteka2.getFilia(Integer.parseInt(numerFilii2)).getRegaly().remove(danaBiblioteka2.getFilia(Integer.parseInt(numerFilii2)).getRegal(Integer.parseInt(numerRegalu2)));
                                        return 3;                                                                       //kasuj regal
                                }
                            }else Powiadomienie.komunikat("Nie istnieje taki regał","Niepoprawne dane");
                        } else {
                            if(numerKatalogowy2.equals("")) {
                                danaBiblioteka2.getFilie().remove(danaBiblioteka2.getFilia(Integer.parseInt(numerFilii2)));
                                return 1;                                                                               //kasuj filie
                            }else Powiadomienie.komunikat("Zły zestaw danych", "Niedobór danych");
                        }
                } else Powiadomienie.komunikat("Nie istnieje taka filia", "Niepoprawne dane");
            }
        }catch(NumberFormatException e){ Powiadomienie.komunikat("Podaj liczby i znaki do opdpowiednich pól", "Błąd formatu wprowadzonych danych");}
    return -1;
    }
}
