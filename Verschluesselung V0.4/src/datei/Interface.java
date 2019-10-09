package datei;

import encrypter.blowfish;
import languages.languageChooser;
import pass.passwortFrame;
import javax.swing.*;
import java.io.File;
import java.net.URL;

public class Interface{
 private static final languages.languageChooser lC = new languageChooser();
    private static File file;
    public void main(String language)
    {
        lC.setActualLanguage(language);
        JFileChooser fC = new JFileChooser();
        fC.setVisible(true);
        int returnValue =fC.showDialog(null, lC.gT("Bestätigen"));

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            System.out.println(fC.getSelectedFile().getAbsolutePath());
            file = new File(fC.getSelectedFile().getAbsolutePath());

            URL urlBild1 = this.getClass().getResource("/assets/vodafone.png");
            ImageIcon icon1 = new ImageIcon(urlBild1);

            Object[] options1 = {lC.gT("Verschlüsseln"), lC.gT("Entschlüsseln")};
            String answer1 =(String)JOptionPane.showInputDialog(null, lC.gT("Typwahl"), lC.gT("Typwähler"), JOptionPane.QUESTION_MESSAGE,icon1 , options1, options1[1]);

            if(answer1.equals(lC.gT("Verschlüsseln"))) {
                Object[] options = {lC.gT("Eigenes Passwort"), lC.gT("Passwortgenerator")};
                String answer = (String) JOptionPane.showInputDialog(null, lC.gT("Typwahl"), lC.gT("Typwähler"), JOptionPane.QUESTION_MESSAGE, icon1, options, options[1]);

                if (answer.equals(lC.gT("Eigenes Passwort"))) {
                    String password = JOptionPane.showInputDialog(lC.gT("Passwort"));

                    try {
                        blowfish.encrypt(file, password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (answer.equals(lC.gT("Passwortgenerator"))) {
                    passwortFrame pF = new passwortFrame();
                    pF.passwortFrame1(language, "Datei");
                    String pwd = pF.getPasswort();
                    System.out.println(pwd);
                }
            }
            if(answer1.equals(lC.gT("Entschlüsseln")))
            {
                String passwordDecrypt = JOptionPane.showInputDialog(lC.gT("Passwort"));
                try {
                    blowfish.decrypt(file,passwordDecrypt);
                    JOptionPane.showMessageDialog(null, lC.gT("Erfolgsnachricht"));
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
            blowfish.encrypt(file, passwort);
            JOptionPane.showMessageDialog(null,lC.gT("Erfolgsnachricht"));
            JOptionPane.showMessageDialog(null,lC.gT("Passwort")+": " + passwort);
        }
        catch (Exception e){e.printStackTrace();}
    }
}
