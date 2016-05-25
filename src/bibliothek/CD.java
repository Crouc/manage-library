package bibliothek;

public class CD extends Artikel {
    int asin;
    String label;
    
    public CD() {
        
    }
    public CD(String name, int anzahl, int asin, String label) {
        super(name, anzahl);
        this.asin = asin;
        this.label = label;
    }
    public void set_Asin(int asin) {
        this.asin = asin;
    }
    public void set_Label(String label) {
        this.label = label;
    }
    public int get_Asin() {
        return this.asin;
    }
    public String get_Label() {
        return this.label;
    }
}
