package pomoce;



import biblioteka.*;
import java.util.Random;


/**
 * Created by User on 2016-12-24.
 */
public class Generatory {               //JEST OK                                                                                   //długa linijka tytułów
    private final String[] tytuly = {"Gotuj z ciocią", "Przepisy babci", "Smaczne dania", "Dobre jedzenie z resztek", "Przygody Billa", "Dziki zachód Janusza", "Podróże kształcą", "Tajemniczy świat Afryki", "Niezwykłe zabójcze robaki Australii", "Tajemnicza wyprawa Sędziwoja", "Zrób to sam!", "Boso do obuwniczego", "Typowe i nietypowe przygody", "Czterech i pies w odległej galaktyce", "Pancerny nosorożec", "Dwa dni w Sosnowcu", "Pamiętnik rosyjskiego żołnierza", "Hobbit, czyli tam i gdzie indziej", "Choinka i kot", "Magia świata dysku", "Mickiewicz. Prawda czy fałsz?", "Budowa głośników wysokofazowych", "Specyfikacja słuchawek TC-145", "Bulbulatory i jak je zrobić", "Przypadki pana Władka"};
    private final int[] numeryIsbn={1145, 1146, 1147, 1148, 1149, 1164, 1165, 3333, 3321, 3456, 4567, 5678, 6789, 6844, 4487, 4499, 4416, 5587, 5564, 6676, 6624, 7723, 7745, 8875, 9935};
    private final String[] daneOsobowe = {"Mirosław Pszczółka","Gall Anonim","Tadeusz Rydz","Paweł Karczek","Bernard Śmigły-Makowiecki","Barbara Andrzejczak","Agata Minoga","Aleksandra Wiślańska","Elżbieta Trzaska","Natalia Kowalska","Patrycja Wójcik","Jan Bogacki", "Maciej Kosztrzyn", "Dominik Albrecht", "Tadeusz Tatarczuk", "Bogusław Linda", "Norbert Kania", "Maciej Boruta", "Nefertete", "Andrzej Monet", "Brajan Brzechwa", "Mikołaj Rej", "Janusz Gałgan", "Eliza Łęcka", "Gerwazy Szkop", "Antoni Mikołajczuk"};

    private Random generator=new Random();                                                                              //jeden uniwersalny

    private Ksiazka generujKsiazke(int aktualnyNumerKatalogowy){
        int tmp=generator.nextInt(tytuly.length);
        return new Ksiazka(numeryIsbn[tmp],tytuly[tmp],aktualnyNumerKatalogowy);
    }
    private Regal generujRegal(int pojemnosc, int numerRegalu, int aktualnyNumerKatalogowy){
        Regal tmp = new Regal(pojemnosc,numerRegalu);
        int liczba = generator.nextInt(pojemnosc)+1;
        for (int i = 0; i < liczba; i++) {
            tmp.getKsiazki().add(generujKsiazke(aktualnyNumerKatalogowy++));                                            //Nie ma opcji wstawienia książki na inny niż aktualny numer katalogowy,
        }                                                                                                               //Na regale nie ma ciągłości numerow
        return tmp;
    }

    private Filia generujFilie(Biblioteka biblioteka){                                                                  //generuje cala filie, niepotrzebne testy
        Filia tmp = new Filia((int)generator.nextInt(biblioteka.getAktualnyNumerFilii())+5, biblioteka.getAktualnyNumerFilii());                     //numer regalu nie musi pokrywac sie z max. liczba regalow, liczy sie faktyczna ilosc
        int liczba = generator.nextInt(tmp.getMaksymlanaLiczbaRegalow())+1;
        for(int i=0;i<liczba;i++){
            tmp.getRegaly().add(generujRegal(generator.nextInt(20)+20,tmp.getAktualnyNumerRegalu(),tmp.getAktualnyNumerKatalogowy()));
            tmp.setAktualnyNumerKatalogowy(tmp.getAktualnyNumerKatalogowy()+tmp.getRegaly().get(i).getKsiazki().size());
            tmp.zwiekszAktualnyNumerRegalu();                                                                           //numery regałów zaczynają się od jeden
        }
        return tmp;
    }

    private Uzytkownik generujUzytkownika(int numerUzytkownika) {
        int liczba = generator.nextInt(daneOsobowe.length);
        return new Uzytkownik(numerUzytkownika, daneOsobowe[liczba]);
    }

    public int generujObiekt(Biblioteka danaBiblioteka,String danyNumerFilii, String liczbaFilii, String numerRegalu,String liczbaRegalow, String pojemnoscRegalu, String liczbaKsiazek, String liczbaUzytkownikow){
        Powiadomienie komunikat = new Powiadomienie();
        Testery testuj = new Testery();
        int aktualnyNumer;

        try{
            if(liczbaFilii.equals("")){
                if(danyNumerFilii.equals("")){
                    if(numerRegalu.equals("")&&liczbaRegalow.equals("")&&pojemnoscRegalu.equals("")&&liczbaKsiazek.equals("")&&(!liczbaUzytkownikow.equals(""))) {
                        aktualnyNumer=danaBiblioteka.getAktualnyNumerUzytkownika();
                        for (int licznik = 0; licznik < Integer.parseInt(liczbaUzytkownikow); licznik++) {
                            while(testuj.testUzytkownik(danaBiblioteka, aktualnyNumer)){ aktualnyNumer ++;}             //nie wygeneruje jesli juz istnieje
                            danaBiblioteka.getUzytkownicy().add(generujUzytkownika(aktualnyNumer));
                            danaBiblioteka.zwiekszAktualnyNumerUzytkownika();
                        }
                        return 4;                                                                                       //uzytkownicy
                    } else komunikat.komunikat("Niepoprawny zestaw danych","Niedobór danych");
                }else {
                    if (liczbaRegalow.equals("")) {
                            if (liczbaKsiazek.equals("")) {
                                komunikat.komunikat("Niepoprawny zestaw danych", "Niedobór danych");
                            } else {
                                    if (pojemnoscRegalu.equals("") && liczbaUzytkownikow.equals("") && (!(numerRegalu.equals("")))) {
                                        for (int licznik = 0; licznik < Integer.parseInt(liczbaKsiazek); licznik++) {
                                            if (danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getPojemnosc() > danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getKsiazki().size()) {
                                                danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegal(Integer.parseInt(numerRegalu)).getKsiazki().add(generujKsiazke(danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getAktualnyNumerKatalogowy()));
                                                danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).zwiekszAktualnyNumerKatalogowy();
                                            } else {
                                                komunikat.komunikat("Napełniono regał", "Brak miejsca");
                                                return 2;
                                            }
                                        }
                                        return 2;                                                                       //ksiazki
                                    } else komunikat.komunikat("Niepoprawny zestaw danych", "Niedobór danych");
                            }
                    } else {
                        if ((numerRegalu.equals("")) && (!pojemnoscRegalu.equals("") && liczbaKsiazek.equals("") && liczbaUzytkownikow.equals(""))) {  //znam liczbe regalow, numer filii , liczb afilii pusta
                            for (int licznik = 0; (licznik < Integer.parseInt(liczbaRegalow)); licznik++) {
                                if (danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().size() < danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getMaksymlanaLiczbaRegalow()) {
                                    danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().add(generujRegal(Integer.parseInt(pojemnoscRegalu), danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getAktualnyNumerRegalu(), danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getAktualnyNumerKatalogowy()));
                                    danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).zwiekszAktualnyNumerRegalu();
                                    danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).setAktualnyNumerKatalogowy(danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getAktualnyNumerKatalogowy() + danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly().get((danaBiblioteka.getFilia(Integer.parseInt(danyNumerFilii)).getRegaly/*przy getregal problem ziloscia regalow*/().size()-1)).getKsiazki().size());
                                } else {
                                    komunikat.komunikat("Napełniono filię","Przepełnienie");
                                    return 3;                                                                           //regaly
                                }
                            }
                            return 3;
                        } else komunikat.komunikat("Niepoprawny zestaw danych", "Niedobór danych");
                    }
                }
            }else{                                                                                                      //filia
                if(danyNumerFilii.equals("") && numerRegalu.equals("") && pojemnoscRegalu.equals("") && liczbaRegalow.equals("") && liczbaKsiazek.equals("") && liczbaUzytkownikow.equals("")){
                    for(int licznik =0; licznik < Integer.parseInt(liczbaFilii); licznik ++) {
                        danaBiblioteka.getFilie().add(generujFilie(danaBiblioteka));
                        danaBiblioteka.zwiekszAktualnyNumerFilii();
                    }
                    return 1;
                }else komunikat.komunikat("Zły zestaw danych","Niepoprawne dane");
            }
        }catch(NumberFormatException e) {komunikat.komunikat("Podaj liczby i znaki do opdpowiednich pól","Błąd formatu wprowadzonych danych");}
    return -1;
    }
}
