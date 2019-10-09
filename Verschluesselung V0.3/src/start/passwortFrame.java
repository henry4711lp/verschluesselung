package start;

import datei.Interface;
import languages.languageChooser;
import textbased.DasFenster;
import textbased.PassGen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class passwortFrame implements ActionListener {

    private JFrame Frame2;              //Leinwand auf der Buttons und Textfelder aufgebaut werden können
    private JButton buttonEnde;
    private JButton buttonSchluss;
    private languages.languageChooser lC = new languageChooser();
    private JComboBox<String> Liste_Sicherheitslänge;  //ComboBoxes sind Dropdown Menüs welche mit einem Array gefüllt werden
    JComboBox<String> Liste_Sicherheitsart;
    private String PasswordLager;
    private String aufrufender;

    public String passwortFrame(String language, String aufrufer) {
        aufrufender = aufrufer;
        lC.setActualLanguage(language);
        Frame2 = new JFrame();
        Frame2.setTitle(lC.getTranslation("Passwortgenerator"));
        Frame2.setSize(500,1000);
        Frame2.setAlwaysOnTop(true); //Ist immer im Vordergrund

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6,2));

        String comboBoxListe[] = {"1","2","3","4"}; //Array mit dem Inhalt für das Dropdownmenü wird initialisiert und deklariert
        Liste_Sicherheitsart = new JComboBox<>(comboBoxListe);  //Neue ComboBox wird initialisiert mit dem zu Verwendenden Inhalt

        String comboBoxListe2[] = {"1","2","3","4","5"
                ,"6","7","8","9","10","11","12","13","14",
                "15","16","17","18","19","20"};
        Liste_Sicherheitslänge = new JComboBox<>(comboBoxListe2);

        JLabel SicherheitsartLabel = new JLabel(lC.getTranslation("Passwortstärke"));
        JLabel PasswordLengthLabel = new JLabel(lC.getTranslation("Passwortlänge"));

        buttonEnde = new JButton(lC.getTranslation("Übernehmen"));
        buttonEnde.addActionListener(this);

        buttonSchluss = new JButton(lC.getTranslation("Schließen"));
        buttonSchluss.addActionListener(this);

        panel2.add(PasswordLengthLabel);
        panel2.add(Liste_Sicherheitslänge);
        panel2.add(SicherheitsartLabel);
        panel2.add(Liste_Sicherheitsart);
        panel2.add(buttonEnde);
        panel2.add(buttonSchluss);

        Frame2.add(panel2);
        Frame2.setVisible(true);
        Frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            return this.getPasswort();
    }

    @Override
    public  void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == this.buttonEnde)
        {
            this.Auslager();
        }
        if(ae.getSource() == this.buttonSchluss)
        {
            Frame2.dispose(); //Frame wird geschlossen
        }

    }
    public String getPasswort()
    {
        return PasswordLager;
    }
    private void Auslager()
    {
        System.out.println("Übergeben");
        short L_S = (short)(Liste_Sicherheitsart.getSelectedIndex()+1);
        PasswordLager = (PassGen.Generate(L_S,Liste_Sicherheitslänge.getSelectedIndex()+1));
        if(aufrufender.equals("Text")) {
            DasFenster.PasswortUebernahme(PasswordLager);
        }
        if(aufrufender.equals("Datei"))
        {
            Interface.PasswortUebernahme(PasswordLager);
        }
    }
}
