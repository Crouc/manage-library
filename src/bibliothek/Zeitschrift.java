package bibliothek;

public class Zeitschrift extends Artikel {
    private String verlag;
    private String sprache;
    
    public Zeitschrift() {
        
    }
    public Zeitschrift(String name, int anzahl, String verlag, String sprache) {
        super(name, anzahl);
        this.verlag = verlag;
        this.sprache = sprache;
    }
    public void set_Verlag(String verlag) {
        this.verlag = verlag;
    }
    public void set_Sprache(String sprache) {
        this.sprache = sprache;
    }
    public String get_Verlag() {
        return this.verlag;
    }
    public String get_Sprache() {
        return this.sprache;
    }
}
