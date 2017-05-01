
package biblioteka;
/**
 * Created by User on 2016-12-23.t
 */
public class Uzytkownik {       //jest ok

    private int numerUzytkownika;
    private Ksiazka wypozyczonaKsiazka;
    private String daneOsobowe;
    private boolean czyWypozyczyl;
    private int filia;

    public Uzytkownik(int numerUzytkownika, String daneOsobowe) {
        this.numerUzytkownika = numerUzytkownika;
        this.wypozyczonaKsiazka = null;
        this.daneOsobowe = daneOsobowe;
        this.filia=-1;
    }
    public int getNumerUzytkownika() {
        return numerUzytkownika;
    }
    public Ksiazka getWypozyczonaKsiazka() {
        return wypozyczonaKsiazka;
    }
    String getDaneOsobowe() {
        return daneOsobowe;
    }
    public boolean isCzyWypozyczyl() {
        return czyWypozyczyl;
    }

    public int getFilia() {
        return filia;
    }
    public void setCzyWypozyczyl(boolean czyWypozyczyl) {
        this.czyWypozyczyl = czyWypozyczyl;
    }
    public void setWypozyczonaKsiazka(Ksiazka ksiazka) {
        this.wypozyczonaKsiazka=ksiazka;
    }
    public void setFilia(int filia) {
        this.filia = filia;
    }
}
