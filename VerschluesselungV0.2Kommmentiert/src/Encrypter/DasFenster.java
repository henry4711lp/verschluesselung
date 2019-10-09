/*
Verschlüsseler
author: Jan Sellerbeck, Michael Szywala, Alexander Köster
V 0.2
 */

package Encrypter;      //Paket der Klasse

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
    private Font schriftart; //Schriftart
    private Font schriftart2;
    private Font schriftart3;
    JFrame Frame;               //JFrames auf denen die Leinwand "Panel" aufgebaut wird
    JFrame Frame2;
    JPanel panel;               //Leinwand auf der Buttons und Textfelder aufgebaut werden können
    JPanel panel2;
    JLabel label1;              //Label sind Texte ohne Feld
    JLabel label2;
    JLabel label3;
    private JTextArea Eingabe;     //TextArea sind Abschnitte mit Textfeld in denen mehrere Zeilen geschrieben werden können
    private JTextField Passwort;    // TextField sind einzeilige Textfelder
    private JTextArea ErgebnisFeld;
    private JButton buttonEncrypt;  //JButton sind klickbare Knöpfe
    private JButton buttonDecrypt;
    private JButton buttonExit;
    private JButton buttonPasswort;
    private JButton buttonEnde;
    private JButton buttonSchluss;
    JComboBox<String> Liste_Sicherheitslänge;  //ComboBoxes sind Dropdown Menüs welche mit einem Array gefüllt werden
    JComboBox<String> Liste_Sicherheitsart;
    DasFenster()
    {
        i =0; //Initialisierung des Zählers i
        Frame = new JFrame(); // Initialisierung des ersten Frames
        Frame.setTitle("Verschlüsseler");      //Titel des Frames wird gesetzt
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Streckung des Frames auf die Größe des Bildschirmes
        Frame.setUndecorated(true); //Entfernen der Ränder

        panel = new JPanel();                   //Initialisierung des ersten panels

        schriftart = new Font("Arial", Font.ITALIC, 20); // Schriftart1 wird initialisiert mit beistehenden Eigenschaften
        schriftart2 = new Font("Arial", Font.BOLD,30);
        schriftart3 = new Font("Arial", Font.BOLD, 50);

        label1 = new JLabel("                   Ihr Text               ");   //Label1 wird initialisiert mit Inhalt
        label1.setFont(schriftart); //Schriftart des Panels wird gesetzt

        label2 = new JLabel("      +     Ihr Passwort                  ");
        label2.setFont(schriftart);

        label3 = new JLabel("Ihr Ergebnis         ");
        label3.setFont(schriftart);

        JLabel platzhalter1 = new JLabel("");

        Eingabe = new JTextArea("Eingabe Text");  //  Eingabe wird mit Standarttext initialisiert
        Eingabe.addFocusListener(this); //Der FocusListener wird hinzugefügt: FocusListener beobachtet ob das Objekt im Fokus des Benutzers mit der Maus ist -> wird das Feld angeklickt?
        Eingabe.setForeground(Color.BLUE);                    //Textfarbe wird gesetzt
        Eingabe.setPreferredSize(new Dimension(600,200));// Größe der TextArea wird gesetzt
        Eingabe.setLineWrap(true); //Automatischer Zeilenumbruch
        Eingabe.setWrapStyleWord(true); //Automatischer Zeilenumbruch mit ganzen Wörtern
        Eingabe.setFont(schriftart2); //Schriftart des Feldes setzen

        Passwort = new JTextField("Passwort", 20); //Passwort wird mit Standarttext und Spaltenbreite initialisiert
        Passwort.addFocusListener(this);
        Passwort.addActionListener(this); //ActionListener wird hinzugefügt. Guckt ob eine Aktion durchgeführt wird
        Passwort.setPreferredSize(new Dimension(100,50)); //Die prefferierte Größe wird festgelegt mit den Dimensionen die angegeben sind
        Passwort.setFont(schriftart2);

        ErgebnisFeld = new JTextArea("Ausgabe Feld");
        ErgebnisFeld.addFocusListener(this);
        ErgebnisFeld.setForeground(Color.blue);
        ErgebnisFeld.setFont(schriftart2);
        ErgebnisFeld.setPreferredSize(new Dimension(600,200));
        Eingabe.setLineWrap(true);
        Eingabe.setWrapStyleWord(true);

        buttonEncrypt = new JButton("Encrypt"); //Button wird mit Aufgesetzem Text initialisiert
        buttonEncrypt.setFont(schriftart3); //Font des Button wird gesetzt
        buttonEncrypt.addActionListener(this); //ActionListener wird zum Button hinzugefügt

        buttonDecrypt = new JButton("Decrypt");
        buttonDecrypt.setFont(schriftart3);
        buttonDecrypt.addActionListener(this);

        buttonExit = new JButton("Exit");
        buttonExit.setFont(schriftart3);
        buttonExit.addActionListener(this);

        buttonPasswort = new JButton("Passwort generieren");
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

        Frame.add(panel); // panel wird auf das Grid gesetzt
        Frame.setVisible(true); //Frame wird sichtbar gemacht
        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Wenn schließen gedrückt wird, wird das Programm beendet



    }
    private void passwortFrame() {
        Frame2 = new JFrame();
        Frame2.setTitle("Passwortgenerator");
        Frame2.setSize(500,1000);
        Frame2.setAlwaysOnTop(true); //Ist immer im Vordergrund

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6,2));

        String comboBoxListe[] = {"1","2","3","4"}; //Array mit dem Inhalt für das Dropdownmenü wird initialisiert und deklariert
        Liste_Sicherheitsart = new JComboBox<>(comboBoxListe);  //Neue ComboBox wird initialisiert mit dem zu Verwendenden Inhalt

        String comboBoxListe2[] = {"1","2","3","4","5"
                ,"6","7","8","9","10","11","12","13","14",
                "15","16","17","18","19","20"};
        Liste_Sicherheitslänge = new JComboBox<>(comboBoxListe2);

        JLabel SicherheitsartLabel = new JLabel("Sicherheitsart");
        JLabel PasswordLengthLabel = new JLabel("Sicherheitslänge");

        buttonEnde = new JButton("Uebernehmen");
        buttonEnde.addActionListener(this);

        buttonSchluss = new JButton("Schließen");
        buttonSchluss.addActionListener(this);

        panel2.add(PasswordLengthLabel);
        panel2.add(Liste_Sicherheitslänge);
        panel2.add(SicherheitsartLabel);
        panel2.add(Liste_Sicherheitsart);
        panel2.add(buttonEnde);
        panel2.add(buttonSchluss);


        schriftart = new Font("Arial", Font.ITALIC, 20);
        schriftart2 = new Font("Arial", Font.BOLD,30);

        Frame2.add(panel2);
        Frame2.setVisible(true);
        Frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


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
                JOptionPane.showMessageDialog(null, "NOCH 5 VERSUCHE BIS ZUR SPERRUNG", "FEHLER", JOptionPane.ERROR_MESSAGE); //Popup mit Fehlermeldung
            }
            if(i == 10)
            {
                JOptionPane.showMessageDialog(null,"ZU VIELE EINGABEN","FEHLER",JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null,"Deaktiviere System für 1H","FEHLER", JOptionPane.ERROR_MESSAGE);

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
            this.passwortFrame();
        }
        if(ae.getSource() == this.buttonEnde)
        {
            System.out.println("Übergeben");
            short L_S = (short)(Liste_Sicherheitsart.getSelectedIndex()+1);
            Passwort.setText(PassGen.Generate(L_S,Liste_Sicherheitslänge.getSelectedIndex()+1));
        }
        if(ae.getSource() == this.buttonSchluss)
        {
            Frame2.dispose(); //Frame wird geschlossen
        }
    }



    private void ErgebnisAnzeige (String Ergebnis)
    {
        ErgebnisFeld.setText(Ergebnis);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == this.Passwort)
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
