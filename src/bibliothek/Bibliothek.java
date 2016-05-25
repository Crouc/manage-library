package bibliothek;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.GregorianCalendar;


public class Bibliothek {
    private ArrayList <Kunde> kundenListe;		// in der kundenListe werden alle Kunden gespeichert
    private ArrayList <Artikel> artikelListe;		// in der artikelListe werden alle Artikel gespeichert
    private ArrayList <Ausleihen> ausleihListe;		// in der ausleihListe werden alle Kunden mit Artikeln gespeichert, sofern er diese ausgeliehen hat
    private ArrayList <Merken> merkListe;		// in der merkListe werden alle Artikel gespeichert, die nachbestellt (anzahl = 0) bzw. bestellt (Artikel noch nicht im Inventar) werden müssen.
    
    final static int FELDLAENGE = 14;	// Von der Klasse definierte konstante Feldlaenge von 14, die nicht mehr veraendert werden darf
    
    public Bibliothek() {
        kundenListe = new ArrayList <Kunde>();
        artikelListe = new ArrayList <Artikel>();
        ausleihListe = new ArrayList <Ausleihen>();
        merkListe = new ArrayList <Merken>();
    }
    public void set_KundenListe(ArrayList liste) {
        this.kundenListe = liste;
    }
    public void set_ArtikelListe(ArrayList liste) {
        this.artikelListe = liste;
    }
    public void set_AusleihListe(ArrayList liste) {
        this.ausleihListe = liste;
    }
    public void set_MerkListe(ArrayList liste) {
        this.merkListe = liste;
    }
    public ArrayList get_KundenListe() {
        return this.kundenListe;
    }
    public ArrayList get_ArtikelListe() {
        return this.artikelListe;
    }
    public ArrayList get_AusleihListe() {
        return this.ausleihListe;
    }
    public ArrayList get_MerkListe() {
        return this.merkListe;
    }
    
    /***********************************************************************************/
    /*********************************Hauptmenue****************************************/
    /***********************************************************************************/
    private void mainMenue() {
        int eingabe = 0;
        System.out.println("HAUPTMENUE");
        System.out.println("------------------------------------------------------------");
        System.out.println("[1] Kundenmenü");
        System.out.println("[2] Artikelmenü");
        System.out.println("[3] Verwaltungsmenü");
        System.out.println("[4] Administratormenü");
        System.out.println("[5] Beenden");
        
        Scanner scannerVar = new Scanner(System.in);

        printAuswahlTreffen();
        try {
            eingabe = scannerVar.nextInt();
        }
        catch(Exception e) {
            System.out.println("keine gültige Eingabe");
        }
        
        switch (eingabe) {
            case 1: kundenMenue();
                    break;
            case 2: artikelMenue();
                    break;
            case 3: verwaltungsMenue();
                    break;
            case 4: administratorMenue();
                    break;
            case 5: close();
                    break;
            default: printEingabeFehler();
        }
    }
    
    private void close() {
        save();                 // speichert alle Daten vor dem Beenden des Programms
        System.exit(0);		// beendet das Programm
    }
    
    /***********************************************************************************/
    /*************************Kundenmenue - Menuefuehrung*******************************/
    /***********************************************************************************/
    private void kundenMenue() { 
        int eingabe = 0;
        boolean menuewechsel = false;
        do {
            System.out.println("KUNDENMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] neuer Kunde anlegen");
            System.out.println("[2] Kunde suchen"); 
            System.out.println("[3] aktuelle Ausleihen zum Kunden anzeigen");
            System.out.println("[0] Hauptmenü");
        
            Scanner scannerVar = new Scanner(System.in);

            printAuswahlTreffen();
            try{
                eingabe = scannerVar.nextInt();
            }
            catch(Exception e){
                System.out.println("keine gültige Eingabe");
            }
            switch (eingabe) {
                case 0: mainMenue();
                        break;
                case 1: kundeAnlegen();
                        break;
                case 2: kundeSuchen();
                        break;
                case 3: ausleihenAnzeigen();
                        break;
                default: printEingabeFehler();
            }
        } while (!menuewechsel);
    }
    
    /***********************************************************************************/
    /**************************Artikelmenue - Menuefuehrung*****************************/
    /***********************************************************************************/
    private void artikelMenue() {
        int eingabe = 0;
        boolean menuewechsel = false;
        do {
            System.out.println("ARTIKELMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Alle Artikel anzeigen");
            System.out.println("[2] Artikel suchen"); 
            System.out.println("[3] neuer Artikel anlegen");
            System.out.println("[4] Artikel ausleihen"); 
            System.out.println("[5] ausgeliehenen Artikel zurücknehmen");
            System.out.println("[6] vorgemerkte Artikel anzeigen");
            System.out.println("[7] Artikel löschen");
            System.out.println("[0] Hauptmenü");
        
            Scanner scannerVar = new Scanner(System.in);

            printAuswahlTreffen();
            try {
                eingabe = scannerVar.nextInt();
            }
            catch(Exception e){
                System.out.println("keine gültige Eingabe");
            }
            switch (eingabe) {
                case 0: mainMenue();
                        break;
                case 1: artikelAnzeigen();
                        break;
                case 2: artikelSuchen();
                        break;
                case 3: artikelAnlegen();
                        break;
                case 4: artikelAusleihen();
                        break;
                case 5: artikelBack();
                        break;
                case 6: vorgemerkteArtikelAnzeigen();
                        break;
                case 7: artikelLoeschen();
                        break;
		default: printEingabeFehler();
            }
        } while (!menuewechsel);  
    }
 
    /***********************************************************************************/
    /************************Administratormenü - Menuefuehrung**************************/
    /***********************************************************************************/
    private void administratorMenue() {
        int eingabe = 0;
        System.out.println("ADMINISTRATORMENUE");
        System.out.println("------------------------------------------------------------");
        System.out.println("[1] Tagesumsatz");
        System.out.println("[2] vorgemerkte Artikel anzeigen");
        System.out.println("[0] Hauptmenü");
        
        Scanner scannerVar = new Scanner(System.in);

        printAuswahlTreffen();
        try {
            eingabe = scannerVar.nextInt();
        }
        catch(Exception e) {
            System.out.println("keine gültige Eingabe");
        }
        
        switch (eingabe) {
            case 0: mainMenue();
                    break;
            case 1: tagesumsatzAnzeigen();
                    break;
            case 2: vorgemerkteArtikelAnzeigen();
                    break;
            default: printEingabeFehler();
        }
    }
    
    /***********************************************************************************/
    /***************************Kundenmenue - Funktionen********************************/
    /***********************************************************************************/
    private void kundeAnlegen() {
        String vorname, nachname, mobil;
        int zaehler = 999;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Vorname: ");
        vorname = scannerVar.next();
        System.out.println("Nachname: ");
        nachname = scannerVar.next();        
        System.out.println("Mobil: ");
        mobil = scannerVar.next();    
        
        Kunde k1 = new Kunde(vorname, nachname, mobil);		// Anlegen eines neuen Objektes der Klasse Kunde.
        Iterator <Kunde>iter = kundenListe.iterator();		// Iterator zum durchlaufen der kundenListe
        while(iter.hasNext()) {
            Kunde k  =  iter.next();       
            zaehler = k.get_Kdnr(); // Der zaehler wird gleich der Kundennummer des letzten Kundens in der Kundenliste gesetzt
        }    
        k1.set_Kdnr(zaehler+1);     // Setzt die Kundennummer des neuen Kundens und stellt sicher, dass keine gleichen Kundennummern vorkommen (zaehler + 1)
        kundenListe.add(k1);        // Kunde wird der KundenListe hinzugefügt
        save();                     // Speichern
        printLF();
        System.out.println("Der Kunde " + nachname + " wurde hinzugefuegt.");
        printLF();
        inKundenDateiSchreiben(kundenListe, new File("kunden.txt"));        // Schreibt die Kundenliste in die Datei "kunden.txt"
    }
    
    private void inKundenDateiSchreiben(List list, File datei) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(datei))) {
            Iterator <Kunde>iter = list.iterator();
            while(iter.hasNext() ) {
                Kunde k = iter.next();
                printWriter.println("Name:   " + k.get_Vorname() + " " + k.get_Nachname());
                printWriter.println("Phone:  " + k.get_Mobil());
                printWriter.println("kdnr:   " + k.get_Kdnr());
                printWriter.println("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(Bibliothek.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void kundeSuchen() {
        String nachname;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Welche Person soll gesucht werden?");
        nachname = scannerVar.next(); 

        // Kontrolle über die Existenz eines Kunden mit dem Nachnamen "nachname" in der Kundenliste
        boolean treffer = false;
        Iterator <Kunde>iter = kundenListe.iterator();
        while(iter.hasNext()) {
            Kunde i  =  iter.next();
            if (i.get_Nachname().equalsIgnoreCase(nachname)) treffer = true;
        }

        if (!treffer) {
            printLF();
            System.out.println("Es gibt keinen Kunden mit dem Nachnamen " + nachname);
            printLF();
        } else {
            String s;

            printZentriert("Vorname");
            printZentriert("Nachname");
            printZentriert("Mobil");
            printZentriert("kdnr");
            printLF();
            printLinieLF(4); // Trennlinie für 4 Felder anzeigen
            Iterator <Kunde>iter2 = kundenListe.iterator();
            
            while(iter2.hasNext()) {
                Kunde i  =  iter2.next();
                
                // nur die Kunden ausgeben, die dem Suchkriterium entsprechen
                if (i.get_Nachname().equalsIgnoreCase(nachname))
                {
                    printLinksbuendig(i.get_Vorname());
                    printLinksbuendig(i.get_Nachname());
                    printLinksbuendig(i.get_Mobil());
                    s = castInt2String(i.get_Kdnr());
                    printZentriert(s);
                    printLF();
                }
            }
            printLF();
        }
    }
    
    private void ausleihenAnzeigen() {
        String nachname;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Ausleihen eines Kunden anzeigen.");
        System.out.println("Bitte geben Sie den Nachname des Kunden ein: ");
        nachname = scannerVar.next(); 
        // Kontrolle über die Existenz eines Kunden mit dem Nachnamen "nachname" in der Ausleihliste
        boolean treffer = false;
        Iterator <Ausleihen>iter = ausleihListe.iterator();
        while(iter.hasNext()) {
            Ausleihen i  =  iter.next();
            if (i.get_Kunde().get_Nachname().equalsIgnoreCase(nachname)) treffer = true;
        }

        if (!treffer) {
            printLF();
            System.out.println("Der Kunde " + nachname + " hat keine Artikel ausgeliehen.");
            printLF();
        } else {
            String s;

            printZentriert("Vorname");
            printZentriert("Nachname");
            printZentriert("Mobil");
            printZentriert("Artikelname");
            printZentriert("Datum");
            printLF();
            printLinieLF(5); // Trennlinie für 7 Felder anzeigen
            
            Iterator <Ausleihen>iter2 = ausleihListe.iterator();
            while(iter2.hasNext()) {
                Ausleihen a  =  iter2.next();
                // nur die Kunden ausgeben, die dem Suchkriterium entsprechen
                if (a.get_Kunde().get_Nachname().equalsIgnoreCase(nachname)) {
                    printLinksbuendig(a.get_Kunde().get_Vorname());
                    printLinksbuendig(a.get_Kunde().get_Nachname());
                    printLinksbuendig(a.get_Kunde().get_Mobil());
                    printLinksbuendig(a.get_Artikel().get_Name());
                    GregorianCalendar now = a.get_Date();
                    now.setTime( new Date() );
                    // Einzelne Felder extrahieren:
                    int year = now.get( Calendar.YEAR  );
                    int mnth = now.get( Calendar.MONTH ) + 1;           // nicht vergessen die 1 zu addieren, weil hier Monate 0 - 11 sind!
                    int date = now.get( Calendar.DATE  );
                    System.out.println( date + "." + mnth + "." + year ); 
                    
                    printLF();
                }
            }
            printLF();
        }
    }
    
    /***********************************************************************************/
    /**************************Artikelmenue - Funktionen********************************/
    /***********************************************************************************/
    private void artikelAnzeigen() {
        String s;

        printZentriert("Artikelnummer");
        printZentriert("Name");
        printZentriert("Anzahl");
        printZentriert("Typ");
        printZentriert("Sprache");
        printZentriert("Verlag");
        printZentriert("ISBN");
        printLF();
        printLinieLF(7); // Trennlinie für 7 Felder anzeigen

        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext())
        {
            Artikel i  =  iter.next();

            s = castInt2String(i.get_artikelnummer());
            printZentriert(s);
            printLinksbuendig(i.get_Name());
            s = castInt2String(i.get_anzahl());
            printZentriert(s);

            if (i instanceof Buch) { // wenn der Artikel ein Exemplar der Klasse Buch ist
                Buch buch = (Buch)i;
                printZentriert("Buch");
                printLinksbuendig(buch.get_Sprache());
                printLinksbuendig(buch.get_Verlag());      
                s = castInt2String(buch.get_Isbn());
                printZentriert(s);
            }
            if (i instanceof CD) {
                CD cd = (CD)i;
                printZentriert("CD");
                s = castInt2String(cd.get_Asin());
                printZentriert(s);
                printLinksbuendig(cd.get_Label()); 
            }
            if (i instanceof Zeitschrift) {
                Zeitschrift zeitschrift = (Zeitschrift)i;
                printZentriert("Zeitschrift");
                printLinksbuendig(zeitschrift.get_Sprache());
                printLinksbuendig(zeitschrift.get_Verlag());  
            }
            if (i instanceof Tablet) {
                Tablet tablet = (Tablet)i;
                printZentriert("Tablet");
                printLinksbuendig(tablet.get_Marke());
            }
            printLF();            
        }
        printLF();       
    }
    
    private void artikelSuchen() {
        String name;
        int eingabe= 0;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Welcher Artikel soll gesucht werden?");
        System.out.println("Bitte geben Sie den Namen ein:");
        name = scannerVar.next(); 
        
        // Kontrolle über die Existenz eines Artiekls mit der Artikelnummer "artikelnr" in der Artikelliste
        boolean treffer = false;
        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext()) {
            Artikel a  =  iter.next();
            if (a.get_Name().equalsIgnoreCase(name)) treffer = true;
        }

        if (!treffer) {
            printLF();
            System.out.println("Es gibt kein Artikel mit dem Namen:  " + name);
            printLF();
            System.out.println("Soll der Artikel für den Kunden vorgemerkt werden?");
            System.out.println("[1] JA Artikel vormerken");
            System.out.println("[2] NEIN weiter zum Hauptmenü");
            printAuswahlTreffen();
            try {
                eingabe = scannerVar.nextInt();
            }
            catch(Exception e){
                System.out.println("keine gültige Eingabe");
            }
            switch (eingabe) {
                case 1: artikelVormerken(name);
                        break;
                case 2: mainMenue();
                        break;
                }
        } else {
            String s;

            printZentriert("Artikelnummer");
            printZentriert("Name");
            printZentriert("Anzahl");
            printZentriert("Typ");
            printZentriert("Sprache");
            printZentriert("Verlag");
            printZentriert("ISBN");
            printLF();
            printLinieLF(7); // Trennlinie für 7 Felder anzeigen
            Iterator <Artikel>iter2 = artikelListe.iterator();
            
            while(iter2.hasNext()) {
                Artikel i  =  iter2.next();
                // nur die Artikel ausgeben, die dem Suchkriterium entsprechen
                if (i.get_Name().equalsIgnoreCase(name))
                {
                    s = castInt2String(i.get_artikelnummer());
                    printZentriert(s);
                    printLinksbuendig(i.get_Name());
                    s = castInt2String(i.get_anzahl());
                    printZentriert(s);

                    if (i instanceof Buch) { // wenn der Artikel ein Exemplar der Klasse Buch ist
                        Buch buch = (Buch)i;
                        printZentriert("Buch");
                        printLinksbuendig(buch.get_Sprache());
                        printLinksbuendig(buch.get_Verlag());         
                        s = castInt2String(buch.get_Isbn());
                        printZentriert(s);
                    }
                    if (i instanceof CD) {
                        CD cd = (CD)i;
                        printZentriert("CD");
                        s = castInt2String(cd.get_Asin());
                        printZentriert(s);
                    }
                    if (i instanceof Zeitschrift) {
                        Zeitschrift zeitschrift = (Zeitschrift)i;
                        printZentriert("Zeitschrift");
                        printLinksbuendig(zeitschrift.get_Sprache());
                        printLinksbuendig(zeitschrift.get_Verlag());  
                    }
                    if (i instanceof Tablet) {
                        Tablet tablet = (Tablet)i;
                        printZentriert("Tablet");
                        printLinksbuendig(tablet.get_Marke());
                    }
                    printLF();  
                }
                printLF(); 
            }      
        }   
    }
    
    private void artikelVormerken(String name) {
        int kdnr = 0;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Welcher Kunde soll benachrichtigt werden sobald der Artikel wieder verfügbar ist?");
        System.out.println("Bitte geben Sie die kdnr ein:");
        kdnr = scannerVar.nextInt(); 
        
        Kunde k = sucheKundezuKundennummer(kdnr);
        Artikel a = sucheArtikelzuArtikelName(name);
        Kunde k1 = new Kunde(k.get_Vorname(), k.get_Nachname(), k.get_Mobil());
        Artikel a1 = new Artikel();
        Merken m1 = new Merken(k1, a1);
        m1.get_Artikel().set_Name(name);
        merkListe.add(m1);
        save();
    }
    
    private void artikelAnlegen() {
        int eingabe = 0;
        boolean menuewechsel = false;
        do {
            System.out.println("ARTIKELAUSWAHL");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Buch");
            System.out.println("[2] CD"); 
            System.out.println("[3] Zeitschrift");
            System.out.println("[4] Tablet");
            System.out.println("[5] Artikelmenü");
            System.out.println("[6] Hauptmenü");
        
            Scanner scannerVar = new Scanner(System.in);

            printAuswahlTreffen();
            try{
                eingabe = scannerVar.nextInt();
            }
            catch(Exception e){
                System.out.println("keine gültige Eingabe");
            }
            switch (eingabe) {
                case 1: buchAnlegen();
                        break;
                case 2: cdAnlegen();
                        break;
                case 3: zeitschriftAnlegen();
                        break;
                case 4: tabletAnlegen();
                        break;
                case 5: artikelMenue();
                        break;
                case 6: mainMenue();
                        break;
                default: printEingabeFehler();
            }
        } while (!menuewechsel);     
    }
    
    private void vorgemerkteArtikelAnzeigen() {
        String s;

        printZentriert("Vorname");
        printZentriert("Nachname");
        printZentriert("Mobil");
        printZentriert("Artikelname");
        printLF();
        printLinieLF(4); // Trennlinie für 7 Felder anzeigen

        Iterator <Merken>iter = merkListe.iterator();
        while(iter.hasNext()) {
            Merken m  =  iter.next();
            printLinksbuendig(m.get_Kunde().get_Vorname());
            printLinksbuendig(m.get_Kunde().get_Nachname());
            printLinksbuendig(m.get_Kunde().get_Mobil());
            printLinksbuendig(m.get_Artikel().get_Name());
        }
        printLF();           
    }
    // Legt einen neuen Artikel vom Typ Buch an und speichert es in der Datei "artikel.txt"
    private void buchAnlegen() {
        String name, verlag, sprache, typ;
        int isbn, anzahl, zaehler = 999;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Name: ");
        name = scannerVar.next();
        System.out.println("Anzahl: ");
        anzahl = scannerVar.nextInt();
        System.out.println("Verlag: ");
        verlag = scannerVar.next();        
        System.out.println("Sprache: ");
        sprache = scannerVar.next(); 
        System.out.println("isbn: ");
        isbn = scannerVar.nextInt();
        System.out.println("Typ (gebunden/ungebunden): ");
        typ = scannerVar.next(); 
        
        Buch b1 = new Buch(name, anzahl, verlag, sprache, isbn, typ);                          
        Iterator <Artikel>iter2 = artikelListe.iterator();
        while(iter2.hasNext()) {
            Artikel a  =  iter2.next();       
            zaehler = a.get_artikelnummer(); 	// Der zaehler wird gleich der Artikelnummer des letzten Artikels in der Artikelliste gesetzt
        }    
        b1.set_artikelnummer(zaehler+1);   		// Setzt die Artikelnummer des neuen Artikels und stellt sicher, dass keine gleichen Artikelnummern vorkommen (zaehler + 1)
        artikelListe.add(b1);  					// Artikel wird der artikelListe hinzugefügt
        save();  								// Speichern
        printLF();
        System.out.println("Der Artikel " + name + " erhaelt die Artikelnummer " + b1.get_artikelnummer() + " und wurde " + anzahl + " mal hinzugefuegt.");
        printLF();
        ArtikelinDateiSchreibem(artikelListe, new File("artikel.txt"));        // Schreibt die Artikelliste in die Datei "artikel.txt"
        
        kontrolleArtikelMerkliste(name); // Kontrolliert ob dieser neu angelegte Artikel in der Merkliste vorhanden ist. Falls ja muss der Kunde benachrichtigt werden, da der Artikel nun vorhanden ist.
    }
    // Legt einen neuen Artikel vom Typ CD an und speichert es in der Datei "artikel.txt"
    private void cdAnlegen() {
        String name, label;
        int asin, anzahl, zaehler = 999;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Name: ");
        name = scannerVar.next();
        System.out.println("Anzahl: ");
        anzahl = scannerVar.nextInt();
        System.out.println("ASIN: ");
        asin = scannerVar.nextInt();
        System.out.println("Label: ");
        label = scannerVar.next();
        
        CD c1 = new CD(name, anzahl, asin, label);                          
        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext()) {
            Artikel a  =  iter.next();       
            zaehler = a.get_artikelnummer(); // Der zaehler wird gleich der Artikelnummer des letzten Artikels in der Artikelliste gesetzt
        }    
        c1.set_artikelnummer(zaehler+1);   // Setzt die Artikelnummer des neuen Artikels und stellt sicher, dass keine gleichen Artikelnummern vorkommen (zaehler + 1)
        artikelListe.add(c1);  // Artikel wird der artikelListe hinzugefügt
        save();  // Speichern
        printLF();
        System.out.println("Der Artikel " + name + " erhaelt die Artikelnummer " + c1.get_artikelnummer() + " und wurde " + anzahl + " mal hinzugefuegt.");
        printLF();
        ArtikelinDateiSchreibem(artikelListe, new File("artikel.txt"));
        
        kontrolleArtikelMerkliste(name); // Kontrolliert ob dieser neu angelegte Artikel in der Merkliste vorhanden ist. Falls ja muss der Kunde benachrichtigt werden, da der Artikel nun vorhanden ist.
    }
    // Legt einen neuen Artikel vom Typ Zeitschrift an und speichert es in der Datei "artikel.txt"
    private void zeitschriftAnlegen() {
        String name, verlag, sprache;
        int anzahl, zaehler = 999;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Name: ");
        name = scannerVar.next();
        System.out.println("Anzahl: ");
        anzahl = scannerVar.nextInt();
        System.out.println("Verlag: ");
        verlag = scannerVar.next();
        System.out.println("Sprache: ");
        sprache = scannerVar.next();
        
        Zeitschrift z1 = new Zeitschrift(name, anzahl, verlag, sprache);                          
        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext()) {
            Artikel a  =  iter.next();       
            zaehler = a.get_artikelnummer(); // Der zaehler wird gleich der Artikelnummer des letzten Artikels in der Artikelliste gesetzt
        }    
        z1.set_artikelnummer(zaehler+1);   // Setzt die Artikelnummer des neuen Artikels und stellt sicher, dass keine gleichen Artikelnummern vorkommen (zaehler + 1)
        artikelListe.add(z1);  // Artikel wird der artikelListe hinzugefügt
        save();  // Speichern
        printLF();
        System.out.println("Der Artikel " + name + " erhaelt die Artikelnummer " + z1.get_artikelnummer() + " und wurde " + anzahl + " mal hinzugefuegt.");
        printLF();
        ArtikelinDateiSchreibem(artikelListe, new File("artikel.txt"));
        
        kontrolleArtikelMerkliste(name); // Kontrolliert ob dieser neu angelegte Artikel in der Merkliste vorhanden ist. Falls ja muss der Kunde benachrichtigt werden, da der Artikel nun vorhanden ist.
    }
    // Legt einen neuen Artikel vom Typ Tablet an und speichert es in der Datei "artikel.txt"
    private void tabletAnlegen() {
        String marke, name;
        int anzahl, zaehler = 999;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Name: ");
        name = scannerVar.next();
        System.out.println("Anzahl: ");
        anzahl = scannerVar.nextInt();
        System.out.println("Marke: ");
        marke = scannerVar.next();
        
        Tablet t1 = new Tablet(name, anzahl, marke);                          
        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext()) {
            Artikel a  =  iter.next();       
            zaehler = a.get_artikelnummer(); // Der zaehler wird gleich der Artikelnummer des letzten Artikels in der Artikelliste gesetzt
        }    
        t1.set_artikelnummer(zaehler+1);   // Setzt die Artikelnummer des neuen Artikels und stellt sicher, dass keine gleichen Artikelnummern vorkommen (zaehler + 1)
        artikelListe.add(t1);  // Artikel wird der artikelListe hinzugefügt
        save();  // Speichern
        printLF();
        System.out.println("Der Artikel " + name + " erhaelt die Artikelnummer " + t1.get_artikelnummer() + " und wurde " + anzahl + " mal hinzugefuegt.");
        printLF();
        ArtikelinDateiSchreibem(artikelListe, new File("artikel.txt"));
        
        kontrolleArtikelMerkliste(name); // Kontrolliert ob dieser neu angelegte Artikel in der Merkliste vorhanden ist. Falls ja muss der Kunde benachrichtigt werden, da der Artikel nun vorhanden ist.
    }
    
    private void ArtikelinDateiSchreibem(List list, File datei) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(datei))) {
            Iterator <Artikel>iter = list.iterator();
            while (iter.hasNext()) {
                Artikel i = iter.next();
                printWriter.println("Name:    " + i.get_Name());
                printWriter.println("Anzahl:  " + i.get_anzahl());
                if (i instanceof Buch) {
                    Buch buch = (Buch)i;
                    printWriter.println("Verlag:  " + buch.get_Verlag());
                    printWriter.println("Sprache: " + buch.get_Sprache());
                    printWriter.println("ISBN:    " + buch.get_Isbn());
                    printWriter.println("Typ:     " + buch.get_typ());
                    printWriter.println("\n");
                }
                if (i instanceof CD) {
                    CD cd = (CD)i;
                    printWriter.println("ASIN:    " + cd.get_Asin());
                    printWriter.println("Label:   " + cd.get_Label());
                    printWriter.println("\n");
                }
                if (i instanceof Zeitschrift) {
                    Zeitschrift zeitschrift = (Zeitschrift)i;
                    printWriter.println("Verlag:  " + zeitschrift.get_Verlag());
                    printWriter.println("Sprache: " + zeitschrift.get_Sprache());
                    printWriter.println("\n");
                }
                if (i instanceof Tablet) {
                    Tablet tablet = (Tablet)i;
                    printWriter.println("Marke:   " + tablet.get_Marke());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Bibliothek.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void kontrolleArtikelMerkliste(String name) {
        Iterator <Merken>iter = merkListe.iterator();
        while(iter.hasNext()) {
            Merken m  =  iter.next();
            if (name.equals(m.get_Artikel().get_Name())) {
                printLF();
                System.out.println("Der Kunde " + m.get_Kunde().get_Vorname() + " " + m.get_Kunde().get_Nachname() + " hat dieses Artikel auf seiner Merkliste");
                System.out.println("Er wird benachrichtigt, dass der Artikel " + name + " wieder vorhanden ist.");
                printLF();
                System.out.println("Nachricht an:  " + m.get_Kunde().get_Vorname() + " " + m.get_Kunde().get_Nachname());
                System.out.println("Telefonnummer: " + m.get_Kunde().get_Mobil());
                System.out.println("Hallo " + m.get_Kunde().get_Vorname() + " " + m.get_Kunde().get_Nachname());
                System.out.println("Der Artikel " + m.get_Artikel().get_Name() + ", den wir für Sie vorgemerkt haben, ist ab sofort wieder verfügbar!");
                printLF();
            }
        }   
    }

    private void artikelAusleihen() {
        int kdnr, artikelnr;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Welcher Kunde möchte etwas ausleihen?");
        System.out.println("Bitte geben Sie die Kundennummer an: ");
        kdnr = scannerVar.nextInt();
        if(pruefeKundennummer(kdnr) != kdnr) {
            System.out.println("Kein Kunde mit der Kundennummer " + kdnr + " vorhanden.");
            artikelMenue();
        } else {
            System.out.println("Welcher Artikel soll ausgeliehen werden?");
            System.out.println("Bitte geben Sie die Artikelnummer an: ");
            artikelnr = scannerVar.nextInt();
            if (pruefeArtikelnummer(artikelnr) != artikelnr) {
                System.out.println("Kein Artikel mit der Artikelnummer" + artikelnr + " vorhanden.");
                artikelMenue();
            } else {
                Artikel a = sucheArtikelzuArtikelnummer(artikelnr);
                Kunde k = sucheKundezuKundennummer(kdnr);
                if(a.get_anzahl() == 0) {
                    printLF();
                    System.out.println("Der Artikel ist leider nicht mehr vorhanden");
                    printLF();
                } else if(a.get_anzahl() > 0) {
                    int anzahl = a.get_anzahl();
                    anzahl--;
                    a.set_anzahl(anzahl);
                    
                    int index = artikelListe.indexOf(a);
                    artikelListe.set(index, a);
                    ArtikelinDateiSchreibem(artikelListe, new File("artikel.txt")); 
                    GregorianCalendar now = new GregorianCalendar();

                    Kunde k1 = new Kunde(k.get_Vorname(), k.get_Nachname(), k.get_Mobil());
                    Artikel a1 = new Artikel(a.get_Name(), a.get_anzahl());
                    Ausleihen aus1 = new Ausleihen(k1, a1, now);
                    aus1.get_Kunde().set_Kdnr(kdnr);
                    aus1.get_Artikel().set_artikelnummer(artikelnr);
                    ausleihListe.add(aus1);
                    save();
                    
                    printLF();
                    System.out.println("Der Kunde " + k.get_Nachname() + " hat den Artikel " + a.get_Name() + " mit der Artikelnummer " + a.get_artikelnummer() + " ausgeliehen");
                    printLF();
                    inAusgeliehenDateiSchreiben(ausleihListe, new File("ausgeliehen.txt"));
                }         
            }
        }
    }

    private Kunde sucheKundezuKundennummer(int kdnr)  {
        Iterator <Kunde>iter = kundenListe.iterator(); //Iterator zum Durchsuchen von artikelListe starten
        while(iter.hasNext()) {
            Kunde j  =  iter.next();
            if(j.get_Kdnr() == kdnr){
                return j;   //gibt ein Objekt Artikel zurÃ¼ck
            }
        }
        return null; //Hier nÃ¶tig, da sonst Fehlermeldung. Wird aber nie erreicht, da PrÃ¼fung ob Artikel mit Artikelnummer artnr existiert bereits durchlaufen ist
    }
    
    private Artikel sucheArtikelzuArtikelnummer(int artikelnr)  {
        Iterator <Artikel>iter = artikelListe.iterator(); //Iterator zum Durchsuchen von artikelListe starten
        while(iter.hasNext()) {
            Artikel i  =  iter.next();
            if(i.get_artikelnummer() == artikelnr){
                return i;   //gibt ein Objekt Artikel zurück
            }
        }
        return null; // Hier nÃ¶tig, da sonst Fehlermeldung. Wird aber nie erreicht, da PrÃ¼fung ob Artikel mit Artikelnummer artnr existiert bereits durchlaufen ist
    }
    
    private Artikel sucheArtikelzuArtikelName(String name)  {
        Iterator <Artikel>iter = artikelListe.iterator(); //Iterator zum Durchsuchen von artikelListe starten
        while(iter.hasNext()) {
            Artikel i  =  iter.next();
            if(i.get_Name().equals(name)){
                return i;   //gibt ein Objekt Artikel zurück
            }
        }
        return null; //Hier nÃ¶tig, da sonst Fehlermeldung. Wird aber nie erreicht, da PrÃ¼fung ob Artikel mit Artikelnummer artnr existiert bereits durchlaufen ist
    }
    
    private void inAusgeliehenDateiSchreiben(List list, File datei) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(datei))) {
            Iterator <Ausleihen>iter = list.iterator();
            while(iter.hasNext() ) {
                Ausleihen a = iter.next();
                printWriter.println("Vorname:   " + a.get_Kunde().get_Vorname());
                printWriter.println("Nachname:  " + a.get_Kunde().get_Nachname());
                printWriter.println("Mobil:     " + a.get_Kunde().get_Mobil());
                printWriter.println("kdnr:      " + a.get_Kunde().get_Kdnr());
                printWriter.println("");
                printWriter.println("Hat folgende Artikel ausgeliehen:");
                printWriter.println("Name:      " + a.get_Artikel().get_Name());
                printWriter.println("Artikelnr: " + a.get_Artikel().get_artikelnummer());
                printWriter.println("Datum:     " + a.get_Date());
                printWriter.println("\n");
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Bibliothek.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private int pruefeKundennummer(int kdnr) {
        Iterator <Kunde>iter = kundenListe.iterator(); //Iterator zum durchsuchen von kundenListe starten
        while(iter.hasNext()) {
            Kunde k  =  iter.next();
            if(k.get_Kdnr() == kdnr) {
                return k.get_Kdnr();
            }
        }
        return 0;
    }
    
    private int pruefeArtikelnummer(int artikelnr) {
        Iterator <Artikel>iter = artikelListe.iterator(); //Iterator zum durchsuchen von artikelListe starten
        while(iter.hasNext()) {
            Artikel a  =  iter.next();
            if(a.get_artikelnummer() == artikelnr) {
                return a.get_artikelnummer();
            }
        }
        return 0;
    }
    
    private void artikelBack() {
        int artikelnr, kdnr;
        Scanner scannerVar = new Scanner(System.in);
        System.out.println("Welcher Kunde möchte ein Artikel zurück bringen?");
        System.out.println("Bitte geben Sie die Kundennummer an: ");
        kdnr = scannerVar.nextInt();
        if (pruefeKundennummer(kdnr) != kdnr) {
            System.out.println("Kein Kunde mit der Kundennummer " + kdnr + " vorhanden.");
            artikelMenue();
        } else {
            System.out.println("Welcher Artikel soll zurück gebracht werden?");
            System.out.println("Bitte geben Sie die Artikelnummer an: ");
            artikelnr = scannerVar.nextInt();
            if (pruefeArtikelnummer(artikelnr) != artikelnr) {
                System.out.println("Kein Artikel mit der Artikelnummer" + artikelnr + " vorhanden.");
                artikelMenue();
            } else {
                double gesamtpreis = 0;
                int eingabe = 0;
                Artikel a = sucheArtikelzuArtikelnummer(artikelnr);
                Kunde k = sucheKundezuKundennummer(kdnr);
                GregorianCalendar now = new GregorianCalendar(); 
                Iterator <Ausleihen>iter = ausleihListe.iterator(); //Iterator zum durchsuchen von Ausleihliste starten
                while(iter.hasNext()) {
                    Ausleihen aus  =  iter.next();
                    int tage = printDifferenz(now , aus.now);
                    printLF();
                    System.out.println("Der Kunde " + k.get_Nachname() + " hat den Artikel " + a.get_Name() + ", " + tage + " Tage lang ausgeliehen.");
                    gesamtpreis = berechnePreis(tage, artikelnr);

                    System.out.println("Möchte der Kunde einen weiteren Artikel zurück bringen?");
                    System.out.println("[1] JA weiteren Artikel zurück bringen");
                    System.out.println("[2] NEIN Preis berechnen");
                    printAuswahlTreffen();
                    try {
                        eingabe = scannerVar.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("keine gültige Eingabe");
                    }
                    switch (eingabe) {
                        case 1: artikelBack();
                                break;
                        case 2: printLF();
                                System.out.println("zu bezahlen: " + gesamtpreis + " Euro");
                                printLF();
                                artikelMenue();
                                break;
                    }
                }     
            }
        }      
    }
    
    private static int printDifferenz(GregorianCalendar now, GregorianCalendar past) {
        long differenz = now.getTimeInMillis() - past.getTimeInMillis();
        int dauer = (int)(differenz / (1000*60*60*24) + 1);
        return dauer;
    }
    
    private double berechnePreis(int tage, int artikelnr) {
        double preis;
        Iterator <Artikel>iter = artikelListe.iterator();
        while(iter.hasNext())
        {
            Artikel i  =  iter.next();
            
            if (i.get_artikelnummer() == artikelnr) {
                if (i instanceof Buch) {
                    Buch buch = (Buch)i;
                    if (buch.get_typ().equals("gebunden")) {
                        if (tage < 7) {
                            return preis = tage * 0.25;
                        } else {
                            return preis = (6 * 0.25) + ((tage - 6) * 0.5);
                        }
                    } else {
                    if (buch.get_typ().equals("ungebunden")) {
                        if (tage < 7) {
                            return preis = tage * 0.1;
                        } else {
                            return preis = (6 * 0.1) + ((tage - 6) * 0.2);
                        }    
                    }    
                    }
                }
                if (i instanceof CD) {
                    if (tage < 7) {
                        return preis = tage * 0.2;
                    } else {
                        return preis = (6 * 0.2) + ((tage - 6) * 0.4);
                    }
                }
                if (i instanceof Zeitschrift) {
                    if (tage < 7) {
                        return preis = tage * 0.3;
                    } else {
                        return preis = (6 * 0.3) + ((tage - 6) * 0.6);
                    }
                }
                // Der Preis für ein Tablet beträt 1 Euro pro Tag, ab dem 7. Tag verdoppeln sich die Ausleihkosten
                if (i instanceof Tablet) {
                    if (tage < 7) {
                        return preis = tage * 1;
                    } else {
                        return preis = (6 * 1) + ((tage - 6) * 2);
                    }
                } 
            }
        }
        return 0;
    }
    
    private void artikelLoeschen() {
        boolean wdh1 = false;
        int artikelnr = 0;
        do {
            wdh1 = false;
        try {
            Scanner scannerVar = new Scanner(System.in);
            System.out.println("Artikelnummer des Artikels der gelöscht werden soll: ");
            artikelnr = scannerVar.nextInt();
        }
            catch(Exception e){
                System.out.println("Fehler, keine gültige Eingabe gemacht!"); wdh1 = true;
            }
        }
        while(wdh1 == true);

        this.removeArtikel(artikelnr);
    }

    private void removeArtikel(int artikelnr) {
        Iterator <Artikel>iter = this.artikelListe.iterator();
        int j = 0;
        boolean found = false;
        boolean loeschen = false;
        boolean wdh = false;
        while(iter.hasNext() && found==false) {
            Artikel i  =  iter.next();
            int artnr = i.get_artikelnummer();
            if(artnr == artikelnr){
                found = true;
                printLF();
                System.out.println("Sind Sie sicher, dass Sie den Artikel  \"" + i.get_Name() + "\"  mit der Artikelnummer   " + artikelnr + "   löschen wollen?");
                printLF();
                do {
                    int eingabe = 0;
                    Scanner scannerVar = new Scanner(System.in);
                    System.out.println("[1] JA Artikel löschen");
                    System.out.println("[2] NEIN Artikel nicht löschen");
                    printAuswahlTreffen();
                    try {
                        eingabe = scannerVar.nextInt();
                    }
                    catch(Exception e){
                        System.out.println("keine gültige Eingabe");
                    }
                    switch (eingabe) {
                        case 1: wdh = false; 
                                loeschen = true;
                                break;
                        case 2: loeschen = false;    
                                wdh = false;
                                break;
                        default: System.out.println("Fehler, Ihre Eingabe wurde nicht erkannt!"); 
                                 wdh = true; 
                    }
                }
                while(wdh == true);

                if(loeschen == true) {
                    this.artikelListe.remove(j);
                    printLF();
                    System.out.println("Der Artikel mit der Artikelnummer " + artikelnr + " und der Bezeichnung " + i.get_Name() + " wurde erfolgreich gelöscht!\nKehre zurück zum Artikel...");
                    save();
                }
                if(loeschen == false) {
                    printLF(); 
                    System.out.println("Artikel wurde NICHT gelöscht! Kehre zurück zum Artikelmenü...");
                }
            }
            ++j;
        }
        if (found == false) {
            printLF(); 
            System.out.println("Es gibt keinen Artikel mit der Artikelnummer " + artikelnr + " !");}
    }
    
    /***********************************************************************************/
    /************************Administratormenü - Funktionen*****************************/
    /***********************************************************************************/
    private double tagesUmsatzBerechnen(GregorianCalendar now, double gesamtpreis) {
        // in Bearbeitung
        GregorianCalendar tagesumsatz = new GregorianCalendar();
        double preis = 0;
        if (tagesumsatz.compareTo(now) == 0) {
            return preis += gesamtpreis;
        }
        return 0;
    }
    private void tagesumsatzAnzeigen() {
        
    }
    
    /***********************************************************************************/
    /*******************Verwaltungsmenue - Daten laden/speichern************************/
    /***********************************************************************************/
    private void saveDataToXML() throws IOException {
        XMLEncoder o = new XMLEncoder(new FileOutputStream("Bibliothek.xml"));
        o.writeObject("Bibliothek");
        o.writeObject(this);
        o.close();
    }

    public Object loadDataFromXML() throws IOException {
        XMLDecoder o = new XMLDecoder(new FileInputStream("Bibliothek.xml"));
        o.readObject();
        Object obj = o.readObject();
        o.close();
        return obj;
    }

    private void save() {
        try {
            saveDataToXML();
        }
        catch(java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void load() {
        try {
            Bibliothek b = (Bibliothek) loadDataFromXML();
            // hier starten wir ein neues Programm (eine neue Instanz der Klasse Verwaltung, der wir auch die Kontrolle übergeben), damit wir an die geladenen Daten kommen
            b.mainMenue();
        }
        catch(java.io.IOException ioe) {
            ioe.printStackTrace();
        }
    }    
    
    private void verwaltungsMenue(){
        int eingabe = 0;
        boolean menuewechsel = false;
        do {
            System.out.println("VERWALTUNGSMENUE");
            System.out.println("------------------------------------------------------------");
            System.out.println("[1] Lade Daten aus XML-Datei");
            System.out.println("[2] Speichere Daten in XML-Datei");
            System.out.println("[0] HAUPTMENUE");

            Scanner scannerVar = new Scanner(System.in);
            printAuswahlTreffen();
            try{
                eingabe = scannerVar.nextInt();
            }
            catch(Exception e){
                System.out.println("keine gültige Eingabe");
            }
            switch (eingabe)
            {
                case 0: mainMenue();
                    break;
                case 1: load();
                    break;
                case 2: save();
					break;
				default: printEingabeFehler();
            }
        } while (!menuewechsel);
    }
    
    /***********************************************************************************/
    /***********************************Design******************************************/
    /***********************************************************************************/
    private String castInt2String(int meinInt) {
        return Integer.toString(meinInt);
        // die obige Zeile ist von der Funktion identisch zu
        // Integer i = new Integer(meinInt);
        // return i.toString();
    }

    private String castDouble2String(double meinDouble) {
        // hier nutzen wir die Format-Anweisung der Klasse String um die Nachkommastellen zu bestimmen etc.
        return String.format("%,8.2f", meinDouble);
    }

    private void printLF() {
        System.out.println();	// gibt eine neue Zeile aus
    }

    private void printZentriert(String s) {
        System.out.print(baueZentriertenString(s, FELDLAENGE));
    }

    private void printLinksbuendig(String s) {
        System.out.print(baueLinksbuendigenString(s, FELDLAENGE));
    }

    private void printRechtsbuendig(String s) {
        System.out.print(baueRechtsbuendigenString(s, FELDLAENGE));
    }

    private void printLinieLF(int anzahlFelder) {
        /* Besonderheit: hier Nutzung des StringBuilders statt direkt mit String zu arbeiten.
         * Ist sparsamer im Umgang mit Speicher.
         */
        StringBuilder s = new StringBuilder();
        int laenge = anzahlFelder*(FELDLAENGE+3);
        for (int i=1;i<=laenge;i++)
        {
            s=s.append("-");
        }
        System.out.println(s);
    }

    private String baueZentriertenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge)
        {
            s.substring(0, laenge);
        }
        else
        {
            int differenzLinks = (laenge - s.length())/2;
            for (int i=1;i<=differenzLinks;i++) s=" "+s+" ";
            if (s.length()<laenge) s=s+" ";
        }

        return s+" | ";
    }

    private String baueRechtsbuendigenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge)
        {
            s.substring(0, laenge);
        }
        else
        {
            int differenzLinks = (laenge - s.length());
            for (int i=1;i<=differenzLinks;i++) s=" "+s;
        }

        return s+" | ";
    }

    private String baueLinksbuendigenString(String s, int laenge) {
        // wir entfernen Leerzeichen am Anfang und Ende des Strings
        s.trim();
        // falls der String zu lang ist, kuerzen wir ihn
        if (s.length() > laenge)
        {
            s.substring(0, laenge);
        }
        else
        {
            int differenz = (laenge - s.length());
            for (int i=1;i<=differenz;i++) s=s+" ";
        }

        return s+" | ";
    }

    private void printEingabeFehler() {
        System.out.println("Ihre Eingabe wurde nicht erkannt.");
    }

    private void printAuswahlTreffen() {
        System.out.println("Bitte treffen Sie eine Auswahl ...");
    }
    
    public static void main(String[] args) {
        Bibliothek b = new Bibliothek();
        // Falls die Datei Bibliothek.xml bereits existiert, sollen die Daten daraus gelesen werden.
        if (new File("Bibliothek.xml").exists()) {
            b.load();   
        }
        b.mainMenue();
    }
}