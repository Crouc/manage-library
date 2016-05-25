package bibliothek;

public class Kunde {
    private int kdnr;
    private String vorname;
    private String nachname;
    private String mobil;

    public Kunde() {
        
    }
    public Kunde(String vname, String nname, String mob) {
        this.vorname = vname;
        this.nachname = nname;
        this.mobil = mob;
        this.kdnr = kdnr;
    }
    public void set_Vorname(String vname) {
        this.vorname = vname;
    }
    public void set_Nachname(String nname) {
        this.nachname = nname;
    }
    public void set_Mobil(String mob) {
        this.mobil = mob;
    }
    public void set_Kdnr(int kdnr) {
        this.kdnr = kdnr;
    }
    public String get_Vorname() {
        return this.vorname;
    }
    public String get_Nachname() {
        return this.nachname;
    }
    public String get_Mobil() {
        return this.mobil;
    }
    public int get_Kdnr() {
        return this.kdnr;
    }
  
}
