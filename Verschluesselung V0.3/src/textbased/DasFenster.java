/*
Verschlüsseler
author: Jan Sellerbeck, Michael Szywala, Alexander Köster
V 0.3
 */

package textbased;      //Paket der Klasse

import encrypter.blowfish;
import languages.languageChooser;
import start.passwortFrame;

import javax.swing.*;                   // Interface Librarys werden importiert
import java.awt.*;                      //
import java.awt.event.ActionEvent;      //Trotz dem Vollimport von java.awt müssen die Action und Focus Anteile importiert werden
import java.awt.event.ActionListener;   //
import java.awt.event.FocusEvent;       //
import java.awt.event.FocusListener;    //

public class DasFenster extends JFrame implements ActionListener, FocusListener {  //

    /*
    Deklarieren der Variablen
    Private bei denen die Privat sein können um reverse engineering zu verhindern
    */
    private int i;  // Zähler
    private JTextArea Eingabe;     //TextArea sind Abschnitte mit Textfeld in denen mehrere Zeilen geschrieben werden können
    private static JTextField Passwort;    // TextField sind einzeilige Textfelder
    private JTextArea ErgebnisFeld;
    private JButton buttonEncrypt;  //JButton sind klickbare Knöpfe
    private JButton buttonDecrypt;
    private JButton buttonExit;
    private JButton buttonPasswort;
    private languages.languageChooser lC = new languageChooser();

    public DasFenster(String language)
    {
        lC.setActualLanguage(language);
        this.DasFensterMeth();
    }
    private void DasFensterMeth()
    {
        i =0; //Initialisierung des Zählers i
        //JFrames auf denen die Leinwand "Panel" aufgebaut wird
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Streckung des Frames auf die Größe des Bildschirmes
        frame.setUndecorated(true); //Entfernen der Ränder
        //Leinwand auf der Buttons und Textfelder aufgebaut werden können
        JPanel panel = new JPanel();

        //Schriftart
        Font schriftart = new Font("Arial", Font.ITALIC, 20);
        Font schriftart2 = new Font("Arial", Font.BOLD, 30);
        Font schriftart3 = new Font("Arial", Font.BOLD, 50);
        //Label sind Texte ohne Feld
        JLabel label1 = new JLabel("                   " + lC.getTranslation("Ihr Text") + "               ");
        label1.setFont(schriftart); //Schriftart des Panels wird gesetzt

        JLabel label2 = new JLabel("      +     " + lC.getTranslation("Ihr Passwort") + "                 ");
        label2.setFont(schriftart);

        JLabel label3 = new JLabel(lC.getTranslation("Ihr Ergebnis") + "         ");
        label3.setFont(schriftart);

        JLabel platzhalter1 = new JLabel("");

        Eingabe = new JTextArea(lC.getTranslation("Eingabetext"));  //  Eingabe wird mit Standarttext initialisiert
        Eingabe.addFocusListener(this); //Der FocusListener wird hinzugefügt: FocusListener beobachtet ob das Objekt im Fokus des Benutzers mit der Maus ist -> wird das Feld angeklickt?
        Eingabe.setForeground(Color.BLUE);                    //Textfarbe wird gesetzt
        Eingabe.setPreferredSize(new Dimension(600,200));// Größe der TextArea wird gesetzt
        Eingabe.setLineWrap(true); //Automatischer Zeilenumbruch
        Eingabe.setWrapStyleWord(true); //Automatischer Zeilenumbruch mit ganzen Wörtern
        Eingabe.setFont(schriftart2); //Schriftart des Feldes setzen

        Passwort = new JTextField(lC.getTranslation("Passwort"), 20); //Passwort wird mit Standarttext und Spaltenbreite initialisiert
        Passwort.addFocusListener(this);
        Passwort.addActionListener(this); //ActionListener wird hinzugefügt. Guckt ob eine Aktion durchgeführt wird
        Passwort.setPreferredSize(new Dimension(100,50)); //Die prefferierte Größe wird festgelegt mit den Dimensionen die angegeben sind
        Passwort.setFont(schriftart2);

        ErgebnisFeld = new JTextArea(lC.getTranslation("Ausgabe Feld"));
        ErgebnisFeld.addFocusListener(this);
        ErgebnisFeld.setForeground(Color.blue);
        ErgebnisFeld.setFont(schriftart2);
        ErgebnisFeld.setPreferredSize(new Dimension(600,200));
        Eingabe.setLineWrap(true);
        Eingabe.setWrapStyleWord(true);

        buttonEncrypt = new JButton(lC.getTranslation("Verschlüsseln")); //Button wird mit Aufgesetzem Text initialisiert
        buttonEncrypt.setFont(schriftart3); //Font des Button wird gesetzt
        buttonEncrypt.addActionListener(this); //ActionListener wird zum Button hinzugefügt

        buttonDecrypt = new JButton(lC.getTranslation("Entschlüsseln"));
        buttonDecrypt.setFont(schriftart3);
        buttonDecrypt.addActionListener(this);

        buttonExit = new JButton(lC.getTranslation("Verlassen"));
        buttonExit.setFont(schriftart3);
        buttonExit.addActionListener(this);

        buttonPasswort = new JButton(lC.getTranslation("Passwort generieren"));
        buttonPasswort.setFont(schriftart3);
        buttonPasswort.addActionListener(this);


        panel.add(label1);  //Einheiten werden auf das Panel hinzugefügt
        panel.add(Eingabe);
        panel.add(label2);
        panel.add(Passwort);
        panel.add(buttonPasswort);
        panel.add(buttonEncrypt);
        panel.add(platzhalter1);
        panel.add(buttonDecrypt);
        panel.add(label3);
        panel.add(ErgebnisFeld);
        panel.add(buttonExit);
        panel.setLayout(new GridLayout(6,2)); //Das Layout des Panels wird gesetzt. Hier: GridLayout. Alle Einheiten sind gleich groß in einem Grid

        frame.add(panel); // panel wird auf das Grid gesetzt
        frame.setVisible(true); //Frame wird sichtbar gemacht
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Wenn schließen gedrückt wird, wird das Programm beendet



    }


    @Override
    public void actionPerformed(ActionEvent ae) { //Automatisch hinzugefügt Methode für denn Fall das eine Aktion ausgelöst wird
        if(ae.getSource() == this.buttonEncrypt) //wenn die ausgelöste Aktion vom buttonEncrypt ausgeht dann
        {
            System.out.println("Eigegebener Text: '" + Eingabe.getText()+"'"); //Ausgabe des Strings und des eigegebenen Tex
            System.out.println("Eingegebenes Passwort: '" + Passwort.getText()+"'");
            System.out.println("Encrypting");

            try { //Try Catch case wurde automatisch von der Funktion in der Klassse blowfisch eingefügt
                ErgebnisAnzeige(blowfish.encrypt(Eingabe.getText(), Passwort.getText())); //ErgebnisAnzeige wird auf die Rückgabe von der Blowfish encrypt Funktion gesetzt, an welche der Inhalt aus Eingabe und Passwort übergeben wird
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(ae.getSource() == this.buttonDecrypt)
        {
            i++; //Bei jedem Aufruf wird i um 1 erhöht
            if(i == 5)
            {
                JOptionPane.showMessageDialog(null, lC.getTranslation("Error1"), lC.getTranslation("Error2"), JOptionPane.ERROR_MESSAGE); //Popup mit Fehlermeldung
            }
            if(i == 10)
            {
                JOptionPane.showMessageDialog(null,lC.getTranslation("Error3"),lC.getTranslation("Error2"),JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null,lC.getTranslation("Error4"),lC.getTranslation("Error2"), JOptionPane.ERROR_MESSAGE);

                try {
                    Thread.sleep(3600000); //Programm für eine Stunde anhalten
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1); //Programm beenden mit Status 1 aka Fehler
            }
            System.out.println("Eigegebener Verschlüsselter Text: " + Eingabe.getText());
            System.out.println("Eingegebenes Passwort: '" + Passwort.getText()+"'");
            System.out.println("Decrypting");
            try {
                ErgebnisAnzeige(blowfish.decrypt(Eingabe.getText(), Passwort.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(ae.getSource() == this.buttonExit)
        {
            System.exit(0);
        }
        if(ae.getSource() == this.buttonPasswort) {
            start.passwortFrame pF = new passwortFrame();
            Passwort.setText(pF.passwortFrame(lC.getActualLanguage(), "Text"));
        }

    }



    private void ErgebnisAnzeige (String Ergebnis)
    {
        ErgebnisFeld.setText(Ergebnis);
    }
    public static void PasswortUebernahme(String PasswortString) {
        Passwort.setText(PasswortString);
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == Passwort)
        {
                Passwort.setText("");
        }
        if(e.getSource() == this.Eingabe)
        {
                Eingabe.setText("");
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
