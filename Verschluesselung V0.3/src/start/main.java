/*
First parameter settings
Author: Jan Sellerbeck
V0.1
 */
package start;

import languages.languageChooser;

import javax.swing.*;
import java.net.URL;

class main {
    private languageChooser lC = new languageChooser();

    public void main()
    {
        Object[] options = {"DE","EN"};
        URL urlBild1 = this.getClass().getResource("/assets/language.png");
        ImageIcon icon1 = new ImageIcon(urlBild1);
        String answer =(String)JOptionPane.showInputDialog(null, "Choose Language", "Language Chooser", JOptionPane.QUESTION_MESSAGE,icon1 , options, options[1]);
        if(answer.equals("DE"))
        {
            System.out.println("German was chosen");
            lC.setActualLanguage("DE");
            this.TextbasedOrDataBased();
        }
        if(answer.equals("EN"))
        {
            System.out.println("English was chosen");
            lC.setActualLanguage("EN");
            this.TextbasedOrDataBased();
        }
    }
    private void TextbasedOrDataBased()
    {
        URL urlBild1 = this.getClass().getResource("/assets/vodafone.png");
        ImageIcon icon1 = new ImageIcon(urlBild1);

        Object[] options = {lC.getTranslation("Datei"), lC.getTranslation("Textbasiert")};
        String answer =(String)JOptionPane.showInputDialog(null, lC.getTranslation("Typwahl"), lC.getTranslation("Typwähler"), JOptionPane.QUESTION_MESSAGE,icon1 , options, options[1]);

        if(answer.equals(lC.getTranslation("Datei")))
        {
            datei.Interface iF = new datei.Interface();
            iF.main(lC.getActualLanguage());
        }
        if(answer.equals(lC.getTranslation("Textbasiert")))
        {

            System.out.println("Textbased wird gestartet");
            JFrame fenster = new textbased.DasFenster(lC.getActualLanguage()); //Vorraussetzung um das Fenster zu Starten
            fenster.setAlwaysOnTop(true);
            fenster.setResizable(false);

        }
    }
}
