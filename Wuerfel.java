package pis.hue1;

/**
 * Klasse Wuerfel, die die Schnittstelle Codec implementiert.
 */
public class Wuerfel implements Codec //Anlegen der Klasse Wuerfel und Implementierung der Schnittstelle Codec.
{
    //Variablendeklaration.
    String strLosungSwort;
    String Key;
    int laengeKlarText;
    String hilfe; //LocaleVariable zur Dekodierung.
    char buchstabe, prufung;

    public Wuerfel(String strLosungWort) {
        this.strLosungSwort = strLosungWort;
    }

    // ---------------------------- -------------------------- KODIERUNG --------------------------- -------------------------------------- ----------------

    /**
     * @param klartext <p>
     *                 Hier wird die Position der einzelnen Buchstaben des Loesungsworts in der Reihefolge des Alphabets gespeichert.
     *                 Diese Position wird im Klartext verwendet, d.h. jeweils der Wert mit der entsprechenden Position finden und ihn mit der Laenge des Loesungsworts addieren, um der Geheimtext schritt für schritt aufzubauen.
     *                 </p>
     * @return Gibt das Ergebnis bwz. Kodierung des Wuerfels.
     */

    @Override
    public String kodiere(String klartext) {
        //Variablendeklaration.
        this.hilfe = klartext;
        Key = this.strLosungSwort;
        String losung = "";
        for (buchstabe = 'a'; buchstabe <= 'z'; buchstabe++) {
            prufung = buchstabe;
            char großbuchstabe = Character.toUpperCase(prufung); //Umwandeln die erste kleine Buchstabe in groß.
            for (int n = 0; n < Key.length(); n++) {
                if (prufung != Key.charAt(n) && großbuchstabe != Key.charAt(n)) //Überprüfung der anwesenden Buchstabe,um die Position zur Weiterbearbeitung zu abspeichern.
                {
                    continue;
                } else {
                    for (int i = n; i < klartext.length(); i += Key.length()) {
                        losung += klartext.charAt(i); //jeweilliges Abspeichern der entsprechenden Buchstaben.
                    }
                }
            }
        }
        return losung;
    }

    //------------- ------------- ------------- DEKODIERUNG ------------------------- ------------------------- --------------------------

    /**
     * @param geheimtext <p>
     *                   Ich habe ein eindimensionales Arrray angelegt,wo einzelnes Buchstabe des Geheimtexts in ihrer richtigen Position reinkommt.
     *                   Am Ende ist das Arrays also voll und beinhaltet den entsprechenden Klartext.
     *                   </p>
     * @return gibt das ausgefüllte Array bzw. den Klartext aus.
     */
    @Override
    public String dekodiere(String geheimtext) {
        //Variablendeklaration.
        Key = this.strLosungSwort;
        String losung = "";
        int m = 0;
        char Tabelle[] = new char[geheimtext.length()]; //Arrays zur Positionierung des jeweilligen Indexs des Geheimtetxtes gemäß  einzelnes Buchstaben des Losungswort.

        for (buchstabe = 'a'; buchstabe <= 'z'; buchstabe++) {
            prufung = buchstabe;
            char großbuchstabe = Character.toUpperCase(prufung); //Umwandeln die erste kleine Buchstabe in groß.
            for (int k = 0; k < Key.length(); k++) {
                if (prufung != Key.charAt(k) && großbuchstabe != Key.charAt(k)) {
                    continue;
                } else {
                    for (int h = k; h < geheimtext.length(); h += Key.length()) {
                        if (m < geheimtext.length()) {
                            Tabelle[h] = geheimtext.charAt(m);
                            m++;
                        }
                    }
                }
            }
        }
        for (int v = 0; v < Tabelle.length; v++) {
            losung += Tabelle[v];
        }

        return losung;
    }

    // -------------------------------------- -------------------------------- SETZELOSUNG --------------------- ---------------------------------- --------------

    /**
     * @param schluessel Key zum Kodieren und Dekodieren der entsprechenden Klasse.
     *                   Es wird eine Exception geworfen,wenn der Key nicht geeignet ist.
     */
    @Override
    public void setzeLosung(String schluessel) {
        String regex = "[a-zA-Z]+"; //String zum Ausschließen eines falschen Loesungsworts.
        if (!schluessel.matches(regex) || schluessel == null) {
            throw new IllegalArgumentException("Aufgrund eines nicht legalen Zeichen konnte Ihr Befehl leider nicht berücksichtigt werden !!");
        } else {
            this.strLosungSwort = schluessel;
        }
        return;
    }

    //-------------------------------------- --------------------------- GIBLOSUNG ----------------------- -------------------------------- --------------------------

    /**
     * @return Gibt der schluessel bwz. das Loesungswort aus.
     */
    @Override
    public String gibLosung() {
        return this.strLosungSwort;
    }
}
