package bibliothek;

public class Artikel {
    private String name;
    private int artikelnummer;
    private int anzahl;
    
    public Artikel() {
        
    }
    public Artikel(String name, int anzahl) {
        this.name = name;
        this.anzahl = anzahl;
        this.artikelnummer = artikelnummer;
    }
    public void set_Name(String name) {
        this.name = name;
    }
    public void set_artikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }
    public void set_anzahl(int anzahl) {
        this.anzahl = anzahl;
    }
    public String get_Name() {
        return this.name;
    }
    public int get_artikelnummer() {
        return this.artikelnummer;
    }
    public int get_anzahl() {
        return this.anzahl;
    }   
}
