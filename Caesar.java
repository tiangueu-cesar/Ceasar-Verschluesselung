package pis.hue1;

/**
 * Klasse Caesar, die die Schnittstelle-Methoden von Codec implementiert.
 */
public class Caesar implements Codec //Anlegen der Klasse Caesar und Implementierung der Schnittstelle Codec.
{

    //Variablendeklaration.
    String widergabe;
    String rueckgabe;
    int anzahlBuchstabe;
    char buchstabe;
    String losung = "";
    String großAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String kleinAlphabet = "abcdefghijklmnopqrstuvwxyz";
    String Key;

    //-------------------------------------------------------------- KODIERUNG ------------------------- ------------------------

    /**
     * @param klartext
     *<p>
     * Der Klartext wird am Anfang als Übergabeparameter mit dem Datentyp String angelegt.
     * Ich lege dann eine For-Schleife an,die jeder Wert des Klartexts mittels des Index herausfinden wird.
     * Zur Bestimmung des neues Buchstaben wird der jeweillige herausgefundene Wert mit der Länge des oben angelegten Parameter addieren.
     * Wenn diese Addition mehr als die Länge des Alphabets ist, muss ein modulo(%) gemacht werden, um am Anfang nochmal wiederzukommen.
     *</p>
     * @return
     * <p>
     *    Hier wird nach der kompletten Bearbeitung der Methode ein Ergebnis bwz. den Geheimtext ausgegeben
     * </p>
     */
    @Override
    public String kodiere(String klartext)
    {
        //Variabledeklaration.
        Key = this.widergabe;
        //Bestimmung der Länge des Lösungsworts.
        anzahlBuchstabe = Key.length();

        for (int i = 0; i <klartext.length(); i++)//For-Schleife zum Durchlaufen des Klartextes.
        {
            //Nimmt des ersten Buchstabes des Alphabets zur weiter Bearbeitung.
            buchstabe = klartext.charAt(i);
            if (Character.isLowerCase(buchstabe)) { //Überprüft,ob die Buchstabe klein ist.
                for (int j = 0; j < kleinAlphabet.length(); j++) {
                    if (kleinAlphabet.charAt(j) != buchstabe) { //Suche nach dem gespeicherten Buchstabe.
                        continue;
                    } else {
                        int stelleBuchstabe = (j+anzahlBuchstabe) % 26; //Variable zur Bestimmung der entprechenden Stelle des gespeicherten Buchstabe.
                        losung += kleinAlphabet.charAt(stelleBuchstabe); //Speichern des entsprechenden Buchstabes zum Aufbauen des Geheimtextes.
                    }
                    break;
                }
            } else if(Character.isUpperCase(buchstabe)) {
                for (int k = 0; k<großAlphabet.length(); k++) {
                    if (großAlphabet.charAt(k)!=buchstabe) {
                        continue;
                    } else {
                        int stelleBuchstabe = (k+anzahlBuchstabe) % 26 ;
                        losung+=großAlphabet.charAt(stelleBuchstabe); //Speichern des entsprechenden Buchstabes zum Aufbauen des Geheimtextes.
                    }
                    break;
                }
            }
            else
            {
                losung+=buchstabe;
            }
        }
        return losung; //Rückgabe des kodierten Texts.
    }

    //-------------------------------- ------------------------ DEKODIERUNG ----------------------------- -------------------------- ------------------

    /**
     * @param geheimtext
     *<p>
     *   Hier versuche das starte Buchstabe wiederzukriegen,indem ich die Position des jeweilligen Buchstabes finde und mache Minus der Länge des Loesungsworts.
     *   Das Modulo wird auch hier berücksichtigt,wenn der Index großer als die Laenge des Alphabets ist,um keine Exception zu haben.
     *</p>
     * @return
     * Hier wird nach der kompletten Bearbeitung der Methode ein Ergebnis bwz. den Klartext ausgegeben
     */
    @Override
    public String dekodiere(String geheimtext)
    {
        //Klasseninvariante.
        Key=this.widergabe;
        anzahlBuchstabe=Key.length();
        int stelleBuchstabe;

        //For-Schleife zum Durchlaufen des Geheimtextes.
        for(int i=0;i<geheimtext.length();i++)
        {
            buchstabe=geheimtext.charAt(i);
            if(Character.isLowerCase(buchstabe)) //Klein Buchstaben.
            {
                for (int l= 0; l<kleinAlphabet.length(); l++)
                {
                    if (kleinAlphabet.charAt(l) != buchstabe)
                    { //Suche nach dem gespeicherten Buchstabe.
                        continue;
                    }
                    else
                        {
                        stelleBuchstabe = (l-anzahlBuchstabe); //Variable zur Bestimmung der entprechenden Stelle des gespeicherten Buchstabe.
                        if(stelleBuchstabe>=0) {
                            losung += kleinAlphabet.charAt(stelleBuchstabe); //Speichern des entsprechenden Buchstabes zum Aufbauen des Geheimtextes.
                        }
                        else
                        {
                            stelleBuchstabe=(kleinAlphabet.length()-(anzahlBuchstabe-l)); //Formel zur Bestimmung des Klartextes.
                            losung+=kleinAlphabet.charAt(stelleBuchstabe);
                        }
                    }
                    break;
                }
            }else if(Character.isUpperCase(buchstabe)) //Große Buchstaben
            {
                for (int n =0; n<großAlphabet.length(); n++)
                {
                    if (großAlphabet.charAt(n)!=buchstabe)
                    {
                        continue;
                    } else {
                        stelleBuchstabe = (n-anzahlBuchstabe);
                        if(stelleBuchstabe>=0)
                        {
                            losung += großAlphabet.charAt(stelleBuchstabe); //Speichern des entsprechenden Buchstabes zum Aufbauen des Geheimtextes.
                        }
                        else
                        {
                            stelleBuchstabe=(großAlphabet.length()-(n+anzahlBuchstabe)); //Formel zur Bestimmung des Klartextes
                            stelleBuchstabe=(stelleBuchstabe-2)+anzahlBuchstabe;
                            losung+= großAlphabet.charAt(stelleBuchstabe);
                        }
                    }
                    break;
                }
            }
            else
            {
                losung+=buchstabe;
            }

        }
        return losung;
    }

    // ------------------------------------------------------------ SETZELOSUNG ------------------------------ ---------------------------------------

    /**
     * @param schluessel
     * <p>
     *     Key zum Kodieren und Dekodieren der entsprechenden Klasse.
     *     Es wird eine Exception geworfen,wenn der Key nicht geeignet ist.
     * </p>
     */
    @Override
    public void setzeLosung(String schluessel)
    {
        String regex="[a-zA-Z]+"; //String zum Ausschließen eines falschen Loesungsworts.
        if(!schluessel.matches(regex) || schluessel==null)
        {
            throw new IllegalArgumentException("Aufgrund eines nicht legalen Zeichen konnte Ihr Befehl leider nicht berücksichtigt werden !!");
        }
        else
        {
            this.widergabe=schluessel;
        }
        return ;
    }

    // ------------------------------------ --------------------- GIBLOSUNG --------------------------------- ------------------------------------------
    /**
     * @return
     * gibt der schluessel bzw. loesungswort aus.
     */
    @Override
    public String gibLosung()
    {
        return this.widergabe; //Gibt die Schlüssel aus.
    }
}