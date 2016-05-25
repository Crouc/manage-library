package bibliothek;

public class Merken {
    private Kunde kunde;
    private Artikel artikel;
    
    public Merken() {
        
    }
    public Merken(Kunde kunde, Artikel artikel) {
        this.kunde = kunde;
        this.artikel = artikel;
    }
    public void set_Kunde(Kunde kunde) {
        this.kunde = kunde;
    }
    public void set_Artikel(Artikel artikel) {
        this.artikel = artikel;
    }
    public Kunde get_Kunde() {
        return this.kunde;
    }
    public Artikel get_Artikel() {
        return this.artikel;
    }
}
