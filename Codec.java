package pis.hue1;

public interface Codec //Schnittstelle zur Implementierung von Wuerfel und Caesar.
{
    //Schnittstellemethoden.
    String kodiere(String klartext);
    String dekodiere(String geheimtext);
    void setzeLosung(String schluessel);
    String gibLosung();
}
