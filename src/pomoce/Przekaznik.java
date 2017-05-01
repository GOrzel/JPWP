package pomoce;

import biblioteka.Ksiazka;
import biblioteka.Uzytkownik;
import java.util.ArrayList;

/**
 * Created by User on 2017-01-16.g
 */
public class Przekaznik {       //ok
    public ArrayList<Integer> listaNumerowFilii = new ArrayList<>();
    public ArrayList<Ksiazka> listaWypozyczonychKsiazek = new ArrayList<>();           //nie trzeba 3 razy przeszukiwac
    public ArrayList<String> listaTytulowWypozyczonych = new ArrayList<>();            //te trzy, te same obiekty na tych samych indeksach
    public ArrayList<Uzytkownik> listaUzytkownikowWypozyczajacych = new ArrayList<>();
    public ArrayList<String> listaDanychUzytkownikow = new ArrayList<>();
    public int liczbaRegalow;
    public int liczbaKsiazek;
}
