package bibliothek;

import java.util.GregorianCalendar;

public class Ausleihen {
    // Datum hinzufügen das beim ausleihen eines Artikels in der Datei ausgeliehen.txt gespeichert wird.
    GregorianCalendar now = new GregorianCalendar();
    private Kunde kunde;
    private Artikel artikel;
    
    public Ausleihen() {
        
    }
    public Ausleihen(Kunde kunde, Artikel artikel, GregorianCalendar now) {
        this.now = now;
        this.kunde = kunde;
        this.artikel = artikel;
    }
    public void set_Date(GregorianCalendar now) {
        this.now = now;
    }
    public void set_Kunde(Kunde kunde) {
        this.kunde = kunde;
    }
    public void set_Artikel(Artikel artikel) {
        this.artikel = artikel;
    }
    public GregorianCalendar get_Date() {
        return this.now;
    }
    public Kunde get_Kunde() {
        return this.kunde;
    }
    public Artikel get_Artikel() {
        return this.artikel;
    }
}
