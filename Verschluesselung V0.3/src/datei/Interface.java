package datei;

import languages.languageChooser;
import start.passwortFrame;

import javax.swing.*;
import java.io.File;
import java.net.URL;

public class Interface{
 private static languages.languageChooser lC = new languageChooser();
    private static File file;
    public void main(String language)
    {
        lC.setActualLanguage(language);
        JFileChooser fC = new JFileChooser();
        fC.setVisible(true);
        int returnValue =fC.showDialog(null, lC.getTranslation("Bestätigen"));

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            System.out.println(fC.getSelectedFile().getAbsolutePath());
            file = new File(fC.getSelectedFile().getAbsolutePath());

            URL urlBild1 = this.getClass().getResource("/assets/vodafone.png");
            ImageIcon icon1 = new ImageIcon(urlBild1);

            Object[] options1 = {lC.getTranslation("Verschlüsseln"), lC.getTranslation("Entschlüsseln")};
            String answer1 =(String)JOptionPane.showInputDialog(null, lC.getTranslation("Typwahl"), lC.getTranslation("Typwähler"), JOptionPane.QUESTION_MESSAGE,icon1 , options1, options1[1]);

            if(answer1.equals(lC.getTranslation("Verschlüsseln"))) {
                Object[] options = {lC.getTranslation("Eigenes Passwort"), lC.getTranslation("Passwortgenerator")};
                String answer = (String) JOptionPane.showInputDialog(null, lC.getTranslation("Typwahl"), lC.getTranslation("Typwähler"), JOptionPane.QUESTION_MESSAGE, icon1, options, options[1]);

                if (answer.equals(lC.getTranslation("Eigenes Passwort"))) {
                    String password = JOptionPane.showInputDialog(lC.getTranslation("Passwort"));

                    try {
                        encrypter.blowfish.encrypt(file, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (answer.equals(lC.getTranslation("Passwortgenerator"))) {
                    start.passwortFrame pF = new passwortFrame();
                    pF.passwortFrame(language, "Datei");
                    String pwd = pF.getPasswort();
                    System.out.println(pwd);
                }
            }
            if(answer1.equals(lC.getTranslation("Entschlüsseln")))
            {
                String passwordDecrypt = JOptionPane.showInputDialog(lC.getTranslation("Passwort"));
                try {
                    encrypter.blowfish.decrypt(file,passwordDecrypt);
                    JOptionPane.showMessageDialog(null, lC.getTranslation("Erfolgsnachricht"));
                    System.exit(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void PasswortUebernahme(String passwort)
    {
        try {
            encrypter.blowfish.encrypt(file, passwort);
            JOptionPane.showMessageDialog(null,lC.getTranslation("Erfolgsnachricht"));
            JOptionPane.showMessageDialog(null,lC.getTranslation("Passwort")+": " + passwort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
