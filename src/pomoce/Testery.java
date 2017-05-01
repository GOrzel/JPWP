package pomoce;

import biblioteka.*;

/**
 * Created by User on 2016-r12-28.e
 */
class Testery { //JEST OK

    boolean testFilia(Biblioteka biblioteka,int nrFilii){
        for(Filia a :biblioteka.getFilie()){
            if(a.getNumerFilii()==nrFilii) return true;
        }
        return false;
    }
    boolean testRegal(Biblioteka biblioteka, int nrFilii, int nrRegalu){
        for(Regal a : biblioteka.getFilia(nrFilii).getRegaly()){
            if(a.getNumerRegalu()==nrRegalu) return true;
        }
        return false;
    }
    boolean testKsiazka(Biblioteka biblioteka, int nrFilii, int nrRegalu, int nrKsiazki){
        for(Ksiazka a : biblioteka.getFilia(nrFilii).getRegal(nrRegalu).getKsiazki()){
            if(a.getNumerKatalogu()==nrKsiazki) return true;                                                            //tytuły i numery isbn mogą się powtarzać
        }
        return false;
    }
    boolean testUzytkownik(Biblioteka biblioteka, int nrUzytkownika){
        for(Uzytkownik a : biblioteka.getUzytkownicy()){
            if(a.getNumerUzytkownika()==nrUzytkownika) return true;
        }
        return false;
    }
}


