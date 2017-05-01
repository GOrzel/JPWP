package biblioteka;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import watek.*;
import pomoce.*;

public class ObslugaBiblioteki implements Listener {
    private JTabbedPane tabbdpane;
    private JTabbedPane tabbedPane1;
    private JButton dodajButton;
    private JPanel Baza;
    private JTextField numerUzytkownika;
    private JTextField daneUzytkownika;
    private JTextField numerFilii;
    private JTextField maxLiczbaRegalow;
    private JTextField numerRegalu;
    private JTextField numer_ISBN;
    private JTextField tytul;
    private JTextField pojemnosc_regalu;
    private JButton usunButton;
    private JList listaUzytkownikow;
    private JList listaFilii;
    private JTextPane aktualnyPodglad;
    private JTextField iloscFilii;
    private JTextField numerFilii3;
    private JTextField iloscRegalow;
    private JTextField numerRegalu3;
    private JTextField iloscKsiazek;
    private JTextField iloscUzytkownikow;
    private JButton generujButton;
    private JTextField pojemnoscRegalu3;
    private JList listaWypozyczonych;
    private JButton ogolnyPodgladButton;
    private JList listaRegalow;
    private JButton wypozyczOddajButton;
    private JList listaKsiazek;
    private JTextArea watekPodglad;
    private JButton startButton;
    private JButton stopButton;
    private JButton czyscHistorieWatkuButton;

    private ArrayList<Integer> listaNumerowFilii;
    private ArrayList<String> listaDanychUzytkownikow;
    private ArrayList<String> listaTytulowWypozyczonych;            //te trzy, te same obiekty na tych samych indeksach
    private ArrayList<Ksiazka> listaWypozyczonychKsiazek;           //nie trzeba 3 razy przeszukiwac
    private ArrayList<Uzytkownik> listaUzytkownikowWypozyczajacych;
    private Przekaznik przekaz;

    private Biblioteka bibliotekaNarodowa;
    private Generatory generator;
    private Wypozyczanie wypozycz;
    private int indexUzytkownik;
    private int indexFilie;
    private int indexRegaly;
    private int indexKsiazki;
    private int indexWypozyczoncyh;
    private int typ;
    private String[] puste = new String[0];
    private String numerFilii4;        //wypozycz
    private String numerRegalu4;
    private String numerKatalogowy4;
    private String numerUzytkownika4;
    private String numerFilii2 = "";       //usun
    private String numerRegalu2 = "";
    private String numerKatalogowy2= "";
    private String numerUzytkownika2;

    public void zmiana(int watekNumerFilii, int watekNumerRegalu, int watekNumerKatalogowy, int indeksUzytkownika, int typ){
        if(typ>=0) {
            watekPodglad.append("Użytkownik " + bibliotekaNarodowa.getUzytkownicy().get(indeksUzytkownika).getDaneOsobowe() + ((typ == 2) ? " wypożyczył/a: " : " oddał/a: ") + bibliotekaNarodowa.getFilia(watekNumerFilii).getRegal(watekNumerRegalu).getKsiazka(watekNumerKatalogowy).getTytul() + "\n");
            wyswietlAktualnosc(5);
        }
    }

    private ObslugaBiblioteki(){
        listaNumerowFilii = new ArrayList<>();
        listaDanychUzytkownikow = new ArrayList<>();
        listaTytulowWypozyczonych = new ArrayList<>();
        listaWypozyczonychKsiazek = new ArrayList<>();
        listaUzytkownikowWypozyczajacych = new ArrayList<>();
        przekaz = new Przekaznik();
        bibliotekaNarodowa = new Biblioteka();
        generator = new Generatory();
        wypozycz = new Wypozyczanie();
        wyswietlAktualnosc(0);
        WatekWypozyczanie losoweWypozyczanie = new WatekWypozyczanie();
        losoweWypozyczanie.odbierz(bibliotekaNarodowa);
        losoweWypozyczanie.subscribe(this);

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typ=Kreatory.stworzObiekt(bibliotekaNarodowa, numerFilii.getText(), maxLiczbaRegalow.getText(), pojemnosc_regalu.getText(), numerRegalu.getText(),numer_ISBN.getText(), tytul.getText(), daneUzytkownika.getText(), numerUzytkownika.getText());
                czyscPolaTekstowe(1);  //pola z numerem 1
                wyswietlAktualnosc(typ);
            }
        });
        usunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typ=Kasowanie.kasujObiekt(bibliotekaNarodowa, numerFilii2, numerRegalu2, numerKatalogowy2, String.valueOf(numerUzytkownika2));
                czyscPolaTekstowe(1);  //pola z numerem 2
                wyswietlAktualnosc(typ);
            }

        });
        generujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                typ= generator.generujObiekt(bibliotekaNarodowa, numerFilii3.getText(), iloscFilii.getText(), numerRegalu3.getText(), iloscRegalow.getText(), pojemnoscRegalu3.getText(), iloscKsiazek.getText(), iloscUzytkownikow.getText());
                czyscPolaTekstowe(1);    //pola z numerem 3
                wyswietlAktualnosc(typ);
            }
        });
        wypozyczOddajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wypozycz.wypozycz_oddaj(bibliotekaNarodowa, numerFilii4 ,numerRegalu4, numerKatalogowy4, numerUzytkownika4);
                czyscPolaTekstowe(1);    //wartosci z numerem 4
                wyswietlAktualnosc(5);
            }
        });
        ogolnyPodgladButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wyswietlAktualnosc(0);      //wartosci list nie zmieniaja sie
            }

        });
        listaUzytkownikow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if((indexUzytkownik = listaUzytkownikow.getSelectedIndex())>=0) {
                    aktualnyPodglad.setText("Numer użytkownika: " + bibliotekaNarodowa.getUzytkownicy().get(indexUzytkownik).getNumerUzytkownika() + "\nImię i nazwisko: " + bibliotekaNarodowa.getUzytkownicy().get(indexUzytkownik).getDaneOsobowe() + "\nWypożyczona książka: " + ((bibliotekaNarodowa.getUzytkownicy().get(indexUzytkownik).isCzyWypozyczyl()) ? bibliotekaNarodowa.getUzytkownicy().get(indexUzytkownik).getWypozyczonaKsiazka().getTytul() : "brak"));
                    czyscPolaTekstowe(0);
                    numerUzytkownika2 = numerUzytkownika4 = String.valueOf(bibliotekaNarodowa.getUzytkownicy().get(indexUzytkownik).getNumerUzytkownika());

                }
            }
        });
        listaWypozyczonych.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if((indexWypozyczoncyh = listaWypozyczonych.getSelectedIndex())>=0) {
                    aktualnyPodglad.setText("Tytuł: " + listaWypozyczonychKsiazek.get(indexWypozyczoncyh).getTytul() + "\nWypożyczył/a: " + listaUzytkownikowWypozyczajacych.get(indexWypozyczoncyh).getDaneOsobowe() + "\nFilia: " + listaUzytkownikowWypozyczajacych.get(indexWypozyczoncyh).getFilia());
                    numerFilii4 = String.valueOf(listaUzytkownikowWypozyczajacych.get(indexWypozyczoncyh).getFilia());
                    numerUzytkownika4 = String.valueOf(listaUzytkownikowWypozyczajacych.get(indexWypozyczoncyh).getNumerUzytkownika());
                    numerKatalogowy4 = String.valueOf(listaWypozyczonychKsiazek.get(indexWypozyczoncyh).getNumerKatalogu());
                    numerRegalu4 = String.valueOf(bibliotekaNarodowa.getFilia(listaUzytkownikowWypozyczajacych.get(indexWypozyczoncyh).getFilia()).getNumerRegaluOdKsiazki(listaWypozyczonychKsiazek.get(indexWypozyczoncyh)));
                }
            }
        });
        listaFilii.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if((indexFilie = listaFilii.getSelectedIndex())>=0) {
                    aktualnyPodglad.setText(("Numer filii: " + bibliotekaNarodowa.getFilie().get(indexFilie).getNumerFilii()) +"\nLiczba regałów: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().size() +"\nMaksymalna liczba regałów: "+ bibliotekaNarodowa.getFilie().get(indexFilie).getMaksymlanaLiczbaRegalow() +"\nLiczba książek: " + bibliotekaNarodowa.getFilie().get(indexFilie).getLiczbaKsiazek() + "\nAktualny numer katalogowy: " + bibliotekaNarodowa.getFilie().get(indexFilie).getAktualnyNumerKatalogowy() + "\nAktualny numer regału: " + bibliotekaNarodowa.getFilie().get(indexFilie).getAktualnyNumerRegalu());
                    listaRegalow.setListData(bibliotekaNarodowa.getFilie().get(indexFilie).getListaNumerowRegalow().toArray());
                    czyscPolaTekstowe(0);
                    numerFilii4 = numerFilii2 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getNumerFilii());
                    listaKsiazek.setListData(puste);
                }
            }
        });
        listaRegalow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if((indexRegaly = listaRegalow.getSelectedIndex())>=0) {
                    aktualnyPodglad.setText("Numer regału: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getNumerRegalu() + "\nLiczba książek: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().size() + "\nPojemność regału: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getPojemnosc());
                    listaKsiazek.setListData(bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getListyKsiazek().toArray());
                    czyscPolaTekstowe(0);
                    numerFilii4 = numerFilii2 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getNumerFilii());
                    numerRegalu2 = numerRegalu4 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getNumerRegalu());
                }
            }
        });
        listaKsiazek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if((indexKsiazki = listaKsiazek.getSelectedIndex())>=0) {
                    aktualnyPodglad.setText("Tytuł: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().get(indexKsiazki).getTytul() + "\nNumer katalogowy: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().get(indexKsiazki).getNumerKatalogu() + "\nNumer ISBN: " + bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().get(indexKsiazki).getNumerIsbn() + "\nCzy wypożyczona: " + ((bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().get(indexKsiazki).isCzyWypozyczona()) ? "tak" : "nie"));
                    czyscPolaTekstowe(0);
                    numerFilii4 = numerFilii2 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getNumerFilii());
                    numerRegalu2 = numerRegalu4 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getNumerRegalu());
                    numerKatalogowy2 = numerKatalogowy4 = String.valueOf(bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getKsiazki().get(indexKsiazki).getNumerKatalogu());
                }
            }
        });
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                losoweWypozyczanie.przelacz(true);
                new Thread(losoweWypozyczanie).start();

            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                losoweWypozyczanie.przelacz(false);

            }
        });
        czyscHistorieWatkuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                watekPodglad.setText("");
            }
        });
    }

    private void czyscPolaTekstowe(int typ){ //zaznaczasz filie, usuwa wpisanie tekstu uzytkownik w Kasuj
            numerFilii.setText("");         //upewnij sie ze nazwy list sa ok numery i normalizacja nazw
            numerFilii3.setText("");
            maxLiczbaRegalow.setText("");
            pojemnosc_regalu.setText("");
            numerRegalu.setText("");
            numerRegalu3.setText("");
            numer_ISBN.setText("");
            tytul.setText("");
            daneUzytkownika.setText("");
            numerUzytkownika.setText("");
            iloscFilii.setText("");
            iloscKsiazek.setText("");
            iloscRegalow.setText("");
            iloscUzytkownikow.setText("");
            pojemnoscRegalu3.setText("");
            numerFilii2 ="";
            numerRegalu2="";
            numerUzytkownika2 = "";
            numerKatalogowy2="";
        if(typ==1) {
            numerFilii4 = "";
            numerRegalu4 = "";
            numerKatalogowy4 = "";
            numerUzytkownika4 = "";
        }
    }

    private void wyswietlAktualnosc(int typ){
        przekaz= bibliotekaNarodowa.getListy();                                  //uzywam tu publicznych
        listaTytulowWypozyczonych= przekaz.listaTytulowWypozyczonych;
        listaWypozyczonychKsiazek= przekaz.listaWypozyczonychKsiazek;
        listaUzytkownikowWypozyczajacych = przekaz.listaUzytkownikowWypozyczajacych;
        listaNumerowFilii = przekaz.listaNumerowFilii;
        listaDanychUzytkownikow = przekaz.listaDanychUzytkownikow;
        aktualnyPodglad.setText("Liczba użytkowników: " + bibliotekaNarodowa.getUzytkownicy().size() + "\n" + "Liczba filii: " + bibliotekaNarodowa.getFilie().size() + "\n" + "Liczba regałów: " + przekaz.liczbaRegalow + "\n" + "Liczba książek: " + przekaz.liczbaKsiazek + "\nIlość wypożyczonych: " + listaTytulowWypozyczonych.size());
        listaWypozyczonych.setListData(listaTytulowWypozyczonych.toArray());
        switch(typ) {
            case 1 :
                listaFilii.clearSelection();
                listaFilii.setListData(listaNumerowFilii.toArray());
                listaKsiazek.setListData(puste);
                listaRegalow.setListData(puste);
                break;
            case 2 :
                listaKsiazek.clearSelection();      //zmieniasz ksiazke
                listaKsiazek.setListData(bibliotekaNarodowa.getFilie().get(indexFilie).getRegaly().get(indexRegaly).getListyKsiazek().toArray());
                break;
            case 3 :
                listaRegalow.clearSelection();      //regaly
                listaRegalow.setListData(bibliotekaNarodowa.getFilie().get(indexFilie).getListaNumerowRegalow().toArray());
                listaKsiazek.setListData(puste);
                break;
            case 4 :
                listaUzytkownikow.clearSelection();     //uzytkownikow
                listaUzytkownikow.setListData(listaDanychUzytkownikow.toArray());
                break;
            case 5 :
                listaWypozyczonych.clearSelection();
                listaKsiazek.clearSelection();
                listaUzytkownikow.clearSelection();
                break;

            default: break;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ObsługaBiblioteki");
        frame.setContentPane(new ObslugaBiblioteki().Baza);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
