package bibliothek;

public class Tablet extends Artikel {
    private String marke;
    
    public Tablet() {
        
    }
    public Tablet(String name, int anzahl, String marke) {
        super(name, anzahl);
        this.marke = marke;
    }
    public void set_Marke(String marke) {
        this.marke = marke;
    }
    public String get_Marke() {
        return this.marke;
    }
}
