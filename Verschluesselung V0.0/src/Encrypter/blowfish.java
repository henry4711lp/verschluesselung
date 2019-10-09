package Encrypter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class blowfish {

    public static String encrypt(String plaintext,String password) throws Exception {
        String encText = "";

        try {
            // Erzeugt einen Symmetrischen Blowfisch Key
            //get.Bytes() wandelt das Passwort in ein byte[] um, Anforderung von SecretKeySpec
            SecretKeySpec key = new SecretKeySpec(password.getBytes(),"Blowfish");
            /*Erzeugen des Cipher Objektes
             Cipher unterstützt verschiedene Ver-/Enschlüsselungsalgorithmen
             Cipher wird auf Blowfisch eingestellt */
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //Schreiben in ein byte[], den veschlüsselten Text, mit UTF-8 Kodierung
            byte[] encrypted = cipher.doFinal(plaintext.getBytes());
            // Konvertiere byte[] in einen String
            encText = Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return encText;
    }

    public static String decrypt(String B64keytext, String password) throws Exception {
        String decText = "";

        try {
            // Erzeugt einen Symmetrischen Blowfisch Key
            // get.Bytes() wandelt das Passwort in ein byte[] um, Anforderung von SecretKeySpec
            SecretKeySpec key = new SecretKeySpec(password.getBytes(),"Blowfish");
            /*Erzeugen des Cipher Objektes
             Cipher unterstützt verschiedene Ver-/Enschlüsselungsalgorithmen
             Cipher wird auf Blowfisch eingestellt */
            Cipher cipher = Cipher.getInstance("Blowfish");
            // Cipher auf Entschlüsselung einstellen
            cipher.init(Cipher.DECRYPT_MODE, key);
            // Schreiben in ein byte[], den entschlüsselten Text
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(B64keytext));
            // Konvertiere byte[] in einen String
            decText = new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return decText;
    }
}
