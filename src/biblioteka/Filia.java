package biblioteka;

import java.util.ArrayList;
import java.util.List;

public class Filia {        //jest ok

    private int aktualnyNumerKatalogowy;                                                                                //na starcie jeden, rosnie z kazda ksiazka
    private int aktualnyNumerRegalu;
    private int maksymlanaLiczbaRegalow;
    private int numerFilii;
    private List<Regal> regaly;
    private List<Integer> listaRegalow;

    public Filia(int maksymalnaLiczbaRegalow, int numerFilii){
        this.aktualnyNumerKatalogowy=1;
        this.aktualnyNumerRegalu=1;                                                                                     //aktualny numer regalu nie musi pokrywac sie z realna liczba regalow
        this.maksymlanaLiczbaRegalow = maksymalnaLiczbaRegalow;
        this.numerFilii = numerFilii;
        this.regaly = new ArrayList<>();
        this.listaRegalow = new ArrayList<>();
    }
    public int getAktualnyNumerKatalogowy() {
        return aktualnyNumerKatalogowy;
    }
    public int getAktualnyNumerRegalu() {
        return aktualnyNumerRegalu;
    }
    public List<Regal> getRegaly() {
        return regaly;
    }
    public int getMaksymlanaLiczbaRegalow() {
        return maksymlanaLiczbaRegalow;
    }
    public int getNumerFilii() {
        return numerFilii;
    }
    public void zwiekszAktualnyNumerKatalogowy() {
        this.aktualnyNumerKatalogowy = this.aktualnyNumerKatalogowy+1;
    }
    public void zwiekszAktualnyNumerRegalu() {
        this.aktualnyNumerRegalu = this.aktualnyNumerRegalu+1;
    }

    public void setAktualnyNumerKatalogowy(int aktualnyNumerKatalogowy) {
        this.aktualnyNumerKatalogowy = aktualnyNumerKatalogowy;
    }
    List<Integer> getListaNumerowRegalow(){
        listaRegalow.clear();
        for(Regal a : this.getRegaly()){
            listaRegalow.add(a.getNumerRegalu());
        }
        return listaRegalow;
    }
    public Regal getRegal(int numerRegalu){                                                                             //zrobiłbym to kreatorem ale filia nie może wywołać ogólnego kratora, bo nie wie do jakiej biblioteki należy
        for(Regal a : this.getRegaly()){
            if (a.getNumerRegalu()==numerRegalu) return a;
        }
        this.getRegaly().add(new Regal(0,numerRegalu));                                                                //brak możliwości pojawienia sie duplikatu
        this.zwiekszAktualnyNumerRegalu();
        return this.getRegaly().get(this.getRegaly().size()-1);
    }
    int getLiczbaKsiazek(){
        int liczbaKsiazek=0;
        for(Regal a : this.getRegaly()){
            liczbaKsiazek+=a.getKsiazki().size();
        }
        return liczbaKsiazek;
    }
    public int getNumerRegaluOdKsiazki(Ksiazka ksiazka){
        for(Regal a : this.getRegaly()){
            if(a.getKsiazki().contains(ksiazka)) return a.getNumerRegalu()  ;
        }
        return -1;
    }
}
