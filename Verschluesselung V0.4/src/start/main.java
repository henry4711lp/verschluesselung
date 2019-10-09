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
    private final languageChooser lC = new languageChooser();

    public void main1() {
        Object[] options;
        URL urlBild1;
        ImageIcon icon1;
        String answer;

        options = new Object[] {"DE","EN"};
        urlBild1 = this.getClass().getResource("/assets/language.png");
        icon1 = new ImageIcon(urlBild1);
        answer =(String)JOptionPane.showInputDialog(null
                , "Choose Language", "Language Chooser",
                 JOptionPane.QUESTION_MESSAGE,icon1 , options, options[1]);
        if(answer.equals("DE"))this.DE();
        if(answer.equals("EN"))this.EN();
    }
    private void TextbasedOrDataBased() {
        URL urlBild1;
        String[] options;
        ImageIcon icon1;
        String answer;

        urlBild1 = this.getClass().getResource("/assets/vodafone.png");
        icon1 = new ImageIcon(urlBild1);
        options = new String[]{lC.gT("Datei"), lC.gT("Textbasiert")};
        answer =(String)JOptionPane.showInputDialog(null
                , lC.gT("Typwahl"), lC.gT("Typwähler"),
                JOptionPane.QUESTION_MESSAGE,icon1 , options, options[1]);
        if(answer.equals(lC.gT("Datei")))this.Datei();
        if(answer.equals(lC.gT("Textbasiert")))this.Textbased();
    }
    private void EN () {
        System.out.println("English was chosen");
        lC.setActualLanguage("EN");
        this.TextbasedOrDataBased();
    }
    private void DE() {
        System.out.println("German was chosen");
        lC.setActualLanguage("DE");
        this.TextbasedOrDataBased();
    }
    private void Datei(){
        datei.Interface iF = new datei.Interface();
        iF.main(lC.getActualLanguage());
    }
    private void Textbased(){
        System.out.println("Textbased wird gestartet");
        JFrame fenster = new textbased.DasFenster(lC.getActualLanguage()); //Vorraussetzung um das Fenster zu Starten
        fenster.setAlwaysOnTop(true);
        fenster.setResizable(false);
    }
}
