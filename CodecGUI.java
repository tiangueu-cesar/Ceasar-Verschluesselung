package pis.hue1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Klasse GUI (Grafische User Interface) zur Interaktion mit Klassen Caesar und Wuerfel.</p>
 */
public class CodecGUI extends JFrame implements ActionListener //Implementierung der Interface ActionListener.
{
   //Inhalt des Fernsters
    JPanel containt1=new JPanel();
    JPanel containt2=new JPanel();
    JPanel containt3=new JPanel();
    JPanel containt4=new JPanel();
    //Feld zur Eingabe
    TextArea text1=new TextArea("Klartext",15,80);
    TextArea text2=new TextArea("Geheimtext",15,80);
    JTextField loesungWort1=new JTextField(15);
    TextField loesungWort2=new TextField(15);
    //Überschriftungen
    JLabel Überschriftung1=new JLabel("Klartext");
    JLabel Überschriftung2=new JLabel("Geheimtext");
    JLabel Überschriftung3=new JLabel("Loesungswort 1");
    JLabel Überschriftung4=new JLabel("Loesungswort 2");
    //Button zum Ankreuzen
    JButton button1=new JButton("Verschlüsseln");
    JButton button2=new JButton("Entschlüsseln");
    JRadioButton box1=new JRadioButton("Caesar");
    JRadioButton box2=new JRadioButton("Wuerfel");


    /**
     * Klasse zur Ausführung der Öberfläsche.
     */
    public CodecGUI ()
    {
        //GUI
        super("Grafische Öberfläche");
        setLayout(new FlowLayout());
        GridBagConstraints space=new GridBagConstraints();
        space.insets=new Insets(150,0,0,0);
        setSize(800,650);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Layout von Konpomenten im Fernster.
        containt1.add(Überschriftung1);
        containt1.add(text1);
        add(containt1,BorderLayout.NORTH);
        containt2.add(Überschriftung2);
        containt2.add(text2);
        add(containt2,BorderLayout.NORTH);
        containt3.add(Überschriftung3);
        containt3.add(loesungWort1);
        containt3.add(Überschriftung4);
        containt3.add(loesungWort2);
        ButtonGroup selection=new ButtonGroup(); //Objekt zur Berücksichtigung des Ankreuzens
        selection.add(box1);
        selection.add(box2);
        containt3.add(box1);
        containt3.add(box2);
        add(containt3,BorderLayout.CENTER);

        containt4.add(button1);
        containt4.add(button2);
        add(containt4,BorderLayout.SOUTH);
        button1.addActionListener(this);
        button2.addActionListener(this);

        //Ereignisverarbeitung
        button1.addActionListener(this);
        button2.addActionListener(this);
    }

    /**
     * @param ae
     * Objekt ae der Methode actionPerformed der Klasse ActionListener für jeweilligen Ereignis
     */
    public void actionPerformed(ActionEvent ae) //Methode der Schnittstelle ActionListener.
    {
        /*
        die Methode getSource() bestimmt, auf welchen Knopf gedrückt wurde,um die entsprechende Action ausführen zu können.
         */

            String regex="[a-zA-Z]+"; //Regex zur Überprüfung von Sonderzeichen in Loesungsworten.
            String ergebnis;
            String speicher;
            String valueOfKlartext=text1.getText();
            String valueOfGeheimtext=text2.getText();
            String valueOfLoesungWort1=loesungWort1.getText();
            String valueOfLoesungWort2=loesungWort2.getText();

            if(ae.getSource()==this.button1)
            {

                if((valueOfKlartext!=null && !valueOfKlartext.isEmpty()) || (valueOfGeheimtext!=null && !valueOfGeheimtext.isEmpty()))
                {
                    if((valueOfLoesungWort1.matches(regex)) || (valueOfLoesungWort2.matches(regex))) //Überprüft,ob es Zeichen außer Buchstaben im Loesungswort drin steht. Wenn der Fall ist,eine Exception wird werfen.
                    {
                        if(box1.isSelected()) //Wenn Verschüsseln ausgewählt wird.
                        {
                            Codec caesar=new Caesar();
                            caesar.setzeLosung(valueOfLoesungWort1);
                            ergebnis=caesar.kodiere(valueOfKlartext);
                            text2.setText(ergebnis);
                        }
                        if(box2.isSelected())
                        {
                            Codec wuerfel=new Wuerfel(valueOfLoesungWort1);
                            wuerfel.setzeLosung(valueOfLoesungWort1);
                            speicher=wuerfel.kodiere(valueOfKlartext);
                            Codec wuerfel_1=new Wuerfel(valueOfLoesungWort2);
                            ergebnis=wuerfel_1.kodiere(speicher);
                            text2.setText(ergebnis);
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Ihr Loesungswort beinhaltet ein Sonderzeichen oder wurden die Texte oder wurde ein Text nicht eingegeben!");
                        throw  new IllegalArgumentException();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Der Klartext und/oder der Geheimtext ist leer,muss aber einen Text beinhalten");
                }
            }
            if(ae.getSource()==this.button2)
            {
                if((valueOfKlartext!=null && !valueOfKlartext.isEmpty()) || (valueOfGeheimtext!=null && !valueOfGeheimtext.isEmpty()))
                {
                    if(valueOfLoesungWort1.matches(regex) || valueOfLoesungWort2.matches(regex)) //Überprüft,ob es Zeichen außer Buchstaben im Loesungswort drin steht. Wenn der Fall ist,eine Exception wird werfen.
                    {
                        if(box1.isSelected()) //Wenn Entschüsseln ausgewählt wird.
                        {
                            Codec caesar=new Caesar();
                            caesar.setzeLosung(valueOfLoesungWort1);
                            ergebnis=caesar.dekodiere(valueOfGeheimtext);
                            text1.setText(ergebnis);
                        }
                        if(box2.isSelected())
                        {
                            Codec wuerfel1=new Wuerfel(valueOfLoesungWort2);
                            wuerfel1.setzeLosung(valueOfLoesungWort2);
                            speicher=wuerfel1.dekodiere(valueOfGeheimtext);
                            Codec wuerfel_2=new Wuerfel(valueOfLoesungWort1);
                            ergebnis=wuerfel_2.dekodiere(speicher);
                            text1.setText(ergebnis);
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Ihr Loesungswort beinhaltet ein Sonderzeichen oder wurden die Texte oder wurde ein Text nicht eingegeben!");
                         throw new IllegalArgumentException();
                    }
                }
                else
                {
                        JOptionPane.showMessageDialog(this,"Der Klartext und/oder der Geheimtext ist leer,muss aber einen Text beinhalten");
                        throw new IllegalArgumentException();
                }
            }
    }

    /**
     * @param args
     * Main-Methoden zum Durchführen des Programms
     */
     public static void main(String[] args)
     {
         Runnable i=new Runnable() {
             @Override
             public void run()
             {
                 CodecGUI Oeberflaeche=new CodecGUI();
             }
         };
         SwingUtilities.invokeLater(i);
     }
}
