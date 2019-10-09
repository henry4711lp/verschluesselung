/*
Interface für den Verschlüsseler
author: Jan Sellerbeck
V 0.1
 */





package Interface;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DasFenster extends JFrame implements ActionListener {

    int i;
    Font schriftart;
    Font schriftart2;
    JFrame Frame;
    JFrame Frame2;
    JPanel panel;
    JPanel panel2;
    JPanel ergebnisPanel;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextArea Eingabe;
    JTextField Passwort;
    JTextArea ErgebnisFeld;
    JButton buttonEncrypt;
    private JButton buttonDecrypt;
    JButton buttonExit;
    JButton buttonPasswort;
    JButton buttonEnde;
    JButton buttonSchluss;
    JComboBox Liste_Sicherheitslänge;
    JComboBox Liste_Sicherheitsart;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public DasFenster()
    {
        i =0;
        Frame = new JFrame();                   //
        Frame.setTitle("Verschlüsseler");      //Frame wird aufgebaut
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setUndecorated(true);

        panel = new JPanel();                   //

        schriftart = new Font("Arial", Font.ITALIC, 20);
        schriftart2 = new Font("Arial", Font.BOLD,30);

        label1 = new JLabel("                   Ihr Text               ");   // Panel auf dem MainFrame wird erschaffen
        label1.setFont(schriftart);

        label2 = new JLabel("      +     Ihr Passwort                  ");
        label2.setFont(schriftart);

        label3 = new JLabel("Ihr Ergebnis         ");
        label3.setFont(schriftart);

        JLabel platzhalter1 = new JLabel("");
        JLabel platzhalter4 = new JLabel("");

        Eingabe = new JTextArea("Eingabe Text");  ////
        Eingabe.setForeground(Color.BLUE);                    //Eingabe Fenster wird erschaffen und angepasst
        Eingabe.setPreferredSize(new Dimension(600,200));//
        Eingabe.setLineWrap(true);
        Eingabe.setWrapStyleWord(true);
        Eingabe.setAlignmentX(100);
        Eingabe.setAlignmentY(-300);
        Eingabe.setFont(schriftart2);
        Frame.getContentPane().add(Eingabe);

        Passwort = new JTextField("Passwort", 20);
        Passwort.addActionListener(this);
        Passwort.setPreferredSize(new Dimension(100,50));
        Passwort.setFont(schriftart2);
        Frame.getContentPane().add(Passwort);

        ErgebnisFeld = new JTextArea("Ausgabe Feld");
        ErgebnisFeld.setForeground(Color.blue);
        ErgebnisFeld.setFont(schriftart2);
        ErgebnisFeld.setPreferredSize(new Dimension(600,200));
        ErgebnisFeld.setVisible(true);
        Eingabe.setLineWrap(true);
        Eingabe.setWrapStyleWord(true);

        buttonEncrypt = new JButton("Encrypt");
        buttonEncrypt.addActionListener(this);

        buttonDecrypt = new JButton("Decrypt");
        buttonDecrypt.addActionListener(this);

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(this);

        buttonPasswort = new JButton("Passwort generieren");
        buttonPasswort.addActionListener(this);


        panel.add(label1);
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
      /*

        panel.add(platzhalter4);*/
        panel.setLayout(new GridLayout(6,2));

        Frame.add(panel);
        Frame.setVisible(true);
        Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }
    public void passwortFrame() {
        Frame2 = new JFrame();                   //
        Frame2.setTitle("Passwortgenerator");      //Frame wird aufgebaut
        Frame2.setSize(500,1000);
        Frame2.setAlwaysOnTop(true);

        panel2 = new JPanel();//
        panel2.setLayout(new GridLayout(6,2));

        String comboBoxListe[] = {"1","2","3","4"};
        Liste_Sicherheitsart = new JComboBox(comboBoxListe);

        String comboBoxListe2[] = {"1","2","3","4","5"
                ,"6","7","8","9","10","11","12","13","14",
                "15","16","17","18","19","20"};
        Liste_Sicherheitslänge = new JComboBox(comboBoxListe2);

        JLabel SicherheitsartLabel = new JLabel("Sicherheitsart");
        JLabel SicherheitslängeLabel = new JLabel("Sicherheitslänge");

        buttonEnde = new JButton("Uebernehmen");
        buttonEnde.addActionListener(this);

        buttonSchluss = new JButton("Schließen");
        buttonSchluss.addActionListener(this);


        JLabel platzhalter2 = new JLabel("");
        JLabel platzhalter3 = new JLabel("");

        panel2.add(SicherheitslängeLabel);
        panel2.add(Liste_Sicherheitslänge);
        //panel.add(platzhalter2);
        panel2.add(SicherheitsartLabel);
        panel2.add(Liste_Sicherheitsart);
        //panel.add(platzhalter3);
        panel2.add(buttonEnde);
        panel2.add(buttonSchluss);


        schriftart = new Font("Arial", Font.ITALIC, 20);
        schriftart2 = new Font("Arial", Font.BOLD,30);

        Frame2.add(panel2);
        Frame2.setVisible(true);
        Frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == this.buttonEncrypt)
        {
            System.out.println("Eigegebener Text: '" + Eingabe.getText()+"'");
            System.out.println("Eingegebenes Passwort: '" + Passwort.getText()+"'");
            System.out.println("Encrypting");
            try {
                ErgebnisAnzeige(blowfish.encrypt(Eingabe.getText(), Passwort.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(ae.getSource() == this.buttonDecrypt)
        {
            i++;
            if(i == 5)
            {
                JOptionPane.showMessageDialog(null, "NOCH 5 VERSUCHE BIS ZUR SPERRUNG", "FEHLER", JOptionPane.ERROR_MESSAGE);
            }
            if(i == 10)
            {
                JOptionPane.showMessageDialog(null,"ZU VIELE EINGABEN","FEHLER",JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null,"Deaktiviere System für 1H","FEHLER", JOptionPane.ERROR_MESSAGE);
                try {
                    Thread.sleep(3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(1);
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
            Frame2.dispose();
        }
    }



    public void ErgebnisAnzeige (String Ergebnis)
    {
        ErgebnisFeld.setText(Ergebnis);
    }
    public void PasswortEingabe (String pwd)
    {
        Passwort.setText(pwd);
    }
}
