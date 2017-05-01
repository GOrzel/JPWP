package pomoce;
import biblioteka.*;
/**
 * Created by User on 2016-12-27.t
 */
public class Kreatory {     //jest ok
    public static int stworzObiekt(Biblioteka danaBiblioteka, String danyNumerFilii, String maksymalnaLiczbaRegalow, String pojemnoscRegalu, String numerRegalu, String numerIsbn, String tytul, String daneUzytkownika, String numerUzytkownika){
        Testery testuj = new Testery();
        //przerob testy
        //kasacja usuwa relacje wypozyczenia itp, wiec jesli jest wolny numer katalogowy, to mozna dodac na nizszy niz aktualne, generatory nie maja tej funkcji
        try {
            if (danyNumerFilii.equals("")){
            if(!(daneUzytkownika.equals(""))&&(!(numerUzytkownika.equals("")))){
                    if (!testuj.testUzytkownik(danaBiblioteka, Integer.parseInt(numerUzytkownika))) {
                        danaBiblioteka.getUzytkownicy().add(new Uzytkownik(Integer.parseInt(numerUzytkownika), daneUzytkownika));
                        return 4;
                    }
                    else Powiadomienie.komunikat("Jest już taki użytkownik","Duplikat");
                }
                else Powiadomienie.komunikat("Za mało danych","Dane");
            }
            else {
                if (pojemnoscRegalu.equals("")) {
                    if (numerIsbn.equals("")) {
                        if (daneUzytkownika.equals("")&&(tytul.equals(""))&&(numerUzytkownika.equals(""))) {
                            if(!testuj.testFilia(danaBiblioteka,Integer.parseInt(danyNumerFilii))) {
                                danaBiblioteka.getFilie().add(new Filia(Integer.parseInt(maksymalnaLiczbaRegalow), Integer.parseInt(danyNumerFilii)));
                                return 1;
                            }
                            else Powiadomienie.komunikat("Jest już taka filia","Duplikat");
                        } else {
                            Powiadomienie.komunikat("Podano zbyt dużo danych","Błąd formatu wprowadzonych danych");
                        }
                    } else {
                        if((daneUzytkownika.equals("") && numerUzytkownika.equals("") && maksymalnaLiczbaRegalow.equals(""))) {//brak szansy na zrobienie duplikatu, model nie ma dostepu do aktualnego numeru katalogowego
                            if(!tytul.equals("")) {
                                if ((testuj.testRegal(danaBiblioteka, Integer.parseInt(danyNumerFilii), Integer.parseInt(numerRegalu))) || (!(danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().size() == danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getMaksymlanaLiczbaRegalow()))) { //ten regał istnieje lub filia ma wolne miejsce
                                    if (!(danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getKsiazki().size() == danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getPojemnosc())) {                //ten regal ma wolne miejsca
                                        danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getKsiazki().add(new Ksiazka(Integer.parseInt(numerIsbn), tytul, danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getAktualnyNumerKatalogowy()));
                                        danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).zwiekszAktualnyNumerKatalogowy();
                                        return 2;
                                    } else Powiadomienie.komunikat("Przekroczono pojemność regału", "Przekroczenie pojemności");
                                } else Powiadomienie.komunikat("Przekroczono pojemność filii", "Przekroczono pojemność");
                            }else Powiadomienie.komunikat("Podaj tytuł","Niedobór danych");
                        }else Powiadomienie.komunikat("Podano zbyt dużo danych","Błąd formatu wprowadzonych danych");
                    }
                } else {
                    if((daneUzytkownika.equals("")) && (tytul.equals("")) && (numerIsbn.equals("")) && (numerUzytkownika.equals("")) && (maksymalnaLiczbaRegalow.equals("")) && (Integer.parseInt(numerRegalu)>0)) {
                        if (!(danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().size() == danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getMaksymlanaLiczbaRegalow())) { //jesli filia ma miejsce
                            if (!testuj.testRegal(danaBiblioteka, Integer.parseInt(danyNumerFilii), Integer.parseInt(numerRegalu))) {  //jesli ten regal nie istnieje
                                danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().add(new Regal(Integer.parseInt(pojemnoscRegalu), Integer.parseInt(numerRegalu)));
                                danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).zwiekszAktualnyNumerRegalu();
                                return 3;
                            } else Powiadomienie.komunikat("Jest już taki regał", "Duplikat");
                        } else Powiadomienie.komunikat("Przekroczono pojemność filii", "Przekroczono pojemność");
                    }
                    else Powiadomienie.komunikat("Podano zbyt dużo danych","Błąd formatu wprowadzonych danych");
                }
            }
        }catch(NumberFormatException e){
            Powiadomienie.komunikat("Podaj liczby i znaki do opdpowiednich pól","Błąd formatu wprowadzonych danych");
        }
        return -1;
    }


}
