
package biblioteka;
/**
 * Created by User on 2016-12-23.y
 */
public class Ksiazka {      //jest ok

    private int numerIsbn;
    private String tytul;
    private boolean czyWypozyczona;
    private int numerKatalogowy;

    public Ksiazka(int numerIsbn, String tytul, int numerKatalogowy) {
        this.czyWypozyczona = false;
        this.tytul = tytul;
        this.numerIsbn = numerIsbn;
        this.numerKatalogowy = numerKatalogowy;
    }

    public int getNumerKatalogu() {
        return numerKatalogowy;
    }
    int getNumerIsbn() {
        return numerIsbn;
    }
    String getTytul() {
        return tytul;
    }
    public boolean isCzyWypozyczona() {
        return czyWypozyczona;
    }

    public static int ala(){
        try{
            throw new Exception();
        }catch(Exception e){
            return 5;
        }finally {
            return 7;
        }
    }
    public static void main(String[] args) {
        System.out.println(ala());
    }
    public void setCzyWypozyczona(boolean czyWypozyczona) {
        this.czyWypozyczona = czyWypozyczona;
    }
}
