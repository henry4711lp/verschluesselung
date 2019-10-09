package textbased;

import javax.swing.*;

public class Start {

    public static void main(String[] args)
    {
        System.out.println("Textbased wird gestartet");
        System.out.println();
        DasFenster fenster = new DasFenster("EN"); //Vorraussetzung um das Fenster zu Starten
        fenster.setAlwaysOnTop(true);
        fenster.setResizable(false);
    }
}
