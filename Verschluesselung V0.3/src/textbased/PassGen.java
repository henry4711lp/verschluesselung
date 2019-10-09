package textbased;

import java.lang.String;  //String verwenden
import java.util.Random;  //Random für die zufällige generation der Zeichen

public class PassGen {
    public static String Generate(short seclvl, int plength) {
        int i = 1;  //Zähler für die länge des Passworts
        char[] chars = "0".toCharArray();  //Zeichenpool wird auf ein Zeichen (0) als standard gesetzt
        if (seclvl == 1)  //SecurityLevel wird überprüft und der Zeichenpool wird zugewiesen
            chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        else if (seclvl == 2)
            chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        else if (seclvl == 3)
            chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        else if (seclvl == 4)
            chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-*/?!#.:,;".toCharArray();
        else {
            System.out.println("FEHLER: Falsches SecLevel!");
            System.out.println("Mehr Info beim Aufruf von: PassGen.ShowSecLvlInfo();");
        }
        StringBuilder stringbuilder = new StringBuilder();  //Neue Instanz von StringBuilder um das Passwort zusammen zu setzen
        Random r = new Random();  //Neue Instanz von Random um ein zufälliges Zeichen auszuwählen
        while (i <= plength) {  //Wird so oft wiederholt wie die länge festgelegt ist
            char zeichen = chars[r.nextInt(chars.length)];  //Zufälliges Zeichen wird aus 'chars' ausgewählt
            stringbuilder.append(zeichen);  //Fügt das ausgewählte Zeichen der String Kette an
            i++;  //Nächstes Zeichen
        }
        String pass = stringbuilder.toString();  //Fertige Zeichenkette in 'pass' speichern
        System.out.println("Passwort: " + pass);  //Passwort anzeigen (Konsole)
        return pass;  //Passwort zurückgeben
    }

    public static void ShowSecLvlInfo() {  //Info zu den Security Leveln ausgeben
        System.out.println();
        System.out.println("SecLvl Info:\n");
        System.out.println("SecLvl 1: Kleinbuchstaben (abcdefghijklmnopqrstuvwxyz)");
        System.out.println("SecLvl 2: Kleinbuchstaben + Großbuchstaben (abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ)");
        System.out.println("SecLvl 3: Kleinbuchstaben + Großbuchstaben + Zahlen (abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789)");
        System.out.println("SecLvl 4: Kleinbuchstaben + Großbuchstaben + Zahlen + Sonderzeichen (abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+-*/?!#.:,;)");
    }
}