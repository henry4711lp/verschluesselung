package Interface;

import javax.swing.*;

public class Start {

    public static void main(String[] args)
    {
        System.out.println("Programm gestartet");
        JFrame fenster = new DasFenster();
        fenster.setAlwaysOnTop(true);
        fenster.setResizable(false);
    }
}
