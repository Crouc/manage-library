package bibliothek;

public class Buch extends Artikel {
    private String verlag;
    private String sprache;
    private String typ;             // Buchtyp (gebunden, ungebunden)
    private int isbn;

    public Buch() {
        
    }
    public Buch(String name, int anzahl, String verlag, String sprache, int isbn, String typ) {
        super(name, anzahl);
        this.verlag = verlag;
        this.sprache = sprache;
        this.isbn = isbn;
        this.typ = typ;
    }
    public void set_Verlag(String verlag) {
        this.verlag = verlag;
    }
    public void set_Sprache(String sprache) {
        this.sprache = sprache;
    }
    public void set_isbn(int isbn) {
        this.isbn = isbn;
    }
    public void set_typ(String typ) {
        this.typ = typ;
    }
    public String get_Verlag() {
        return this.verlag;
    }
    public String get_Sprache() {
        return this.sprache;
    }
    public int get_Isbn() {
        return this.isbn;
    }
    public String get_typ() {
        return this.typ;
    }
}
