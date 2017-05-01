package biblioteka;

import java.util.ArrayList;
import java.util.List;
import pomoce.Kreatory;
import pomoce.Przekaznik;

public class Biblioteka{       //jest ok

    private List<Uzytkownik> uzytkownicy;
    private List<Filia> filie;
    private int aktualnyNumerUzytkownika;
    private int aktualnyNumerFilii;
    private static Przekaznik przekaz;
    private int liczbaKsiazekBiblioteki;

    public int getLiczbaKsiazekBiblioteki() {
        return liczbaKsiazekBiblioteki;
    }
    public List<Uzytkownik> getUzytkownicy() { return uzytkownicy; }
    public List<Filia> getFilie() {
        return filie;
    }
    public int getAktualnyNumerUzytkownika() { return aktualnyNumerUzytkownika; }
    public int getAktualnyNumerFilii() { return aktualnyNumerFilii; }
    public void zwiekszAktualnyNumerFilii() { this.aktualnyNumerFilii ++; }
    public void zwiekszAktualnyNumerUzytkownika() {this.aktualnyNumerUzytkownika ++;}

    public Biblioteka(){
        this.aktualnyNumerUzytkownika=1;
        this.aktualnyNumerFilii=1;
        this.uzytkownicy=new ArrayList<>();
        this.filie= new ArrayList<>();
        przekaz = new Przekaznik();

    }
    Przekaznik getListy(){
        przekaz.liczbaRegalow = 0;
        przekaz.liczbaKsiazek = 0;
        przekaz.listaDanychUzytkownikow.clear();                                                                        //niezbyt fajne, ale inaczej bylaby funkcja biblioteki z 5 razy dla kazdej listy albo przenoszenie poprzez jakis dodatkowy obiekt-strukture
        przekaz.listaNumerowFilii.clear();
        przekaz.listaTytulowWypozyczonych.clear();
        przekaz.listaWypozyczonychKsiazek.clear();
        przekaz.listaUzytkownikowWypozyczajacych.clear();
        for (Filia a : this.getFilie()) {
            for (Regal b : a.getRegaly()) {
                for (Ksiazka c : b.getKsiazki()) {
                    przekaz.liczbaKsiazek++;
                    if (c.isCzyWypozyczona()) {
                        przekaz.listaTytulowWypozyczonych.add(c.getTytul());
                        przekaz.listaWypozyczonychKsiazek.add(c);
                        for(Uzytkownik user : this.getUzytkownicy()){
                            if(user.isCzyWypozyczyl()) {
                                if ((user.getWypozyczonaKsiazka().getNumerKatalogu() == c.getNumerKatalogu()) && (user.getFilia() == a.getNumerFilii())) {
                                    przekaz.listaUzytkownikowWypozyczajacych.add(user);                                 //ta sama kolejnosc indeksow pozwala na przyporzadkowanie uzytkownika do ksiazki
                                }
                            }
                        }
                    }
                }
                przekaz.liczbaRegalow++;
            }
            przekaz.listaNumerowFilii.add(a.getNumerFilii());
        }
        for(Uzytkownik user : this.getUzytkownicy()){
            przekaz.listaDanychUzytkownikow.add(user.getDaneOsobowe());
            if (user.isCzyWypozyczyl()){
                if (! (przekaz.listaWypozyczonychKsiazek.contains(user.getWypozyczonaKsiazka()))) {
                    user.setCzyWypozyczyl(false);
                    user.setWypozyczonaKsiazka(null);                                                                   //po usuniecia calego regalu usuwa znacznik wypozyczenia z uzytkownika
                }
            }
        }
        this.liczbaKsiazekBiblioteki=przekaz.liczbaKsiazek;
        return przekaz;
    }
    public Filia getFilia(int podanyNumerFilii){
        for(Filia a : this.getFilie()){
           if (a.getNumerFilii()==podanyNumerFilii) return a;
        }
        Kreatory.stworzObiekt(this,Integer.toString(podanyNumerFilii),"0","","","","","","");                           //tworzymy filie, nie ma szans na duplikat, ogólny kreator, sa testy wiec nie powinno sie zdarzyc
        return getFilia(podanyNumerFilii);
    }
    public int getIndeksUzytkownik(int numerUzytkownika){
        int licznik=0;
        for(Uzytkownik a : this.getUzytkownicy()){
            if(a.getNumerUzytkownika()==numerUzytkownika) return licznik;
            licznik++;
        }
        return -1;
    }
}
