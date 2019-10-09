package Encrypter;

import javax.swing.*;

public class Start {

    public static void main(String[] args)
    {
        System.out.println("Programm gestartet");
        JFrame fenster = new DasFenster(); //Vorraussetzung um das Fenster zu Starten
        fenster.setAlwaysOnTop(true);
        fenster.setResizable(false);
    }
}
