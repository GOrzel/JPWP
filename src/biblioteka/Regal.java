package biblioteka;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-12-23.a
 */

public class Regal {        //jest ok

    private int pojemnosc;
    private List<Ksiazka> ksiazki;
    private List<String> listaKsiazek;
    private int numerRegalu;

    public Regal(int pojemnosc, int numerRegalu ) {
        this.pojemnosc = pojemnosc;
        this.numerRegalu = numerRegalu;
        this.ksiazki  = new ArrayList<>();
        this.listaKsiazek = new ArrayList<>();
    }

    List<String> getListyKsiazek(){
        listaKsiazek.clear();
        for (Ksiazka a: this.getKsiazki()) {
            listaKsiazek.add(a.getTytul());
        }
        return listaKsiazek;
    }
    public int getNumerRegalu() {
        return numerRegalu;
}
    public int getPojemnosc() {
        return pojemnosc;
}
    public List<Ksiazka> getKsiazki() {
        return ksiazki;
}
    public Ksiazka getKsiazka(int numerKatalogowy) {
        for (Ksiazka a : this.getKsiazki()) {
            if (a.getNumerKatalogu() == numerKatalogowy) return a;
        }
        return null;                                                                             //nie powinno zwracac ksiazki bez zmiany regalu, nie powinno sie zdazyc
    }


}
