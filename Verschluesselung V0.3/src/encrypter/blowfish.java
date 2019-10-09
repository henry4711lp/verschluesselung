package encrypter;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;

import org.apache.commons.io.FilenameUtils;

public class blowfish {

    public static String encrypt(String plaintext,String password) throws Exception {
        String encText = "";

        try {
            // Erzeugt einen Symmetrischen Blowfish Key
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

    public static void encrypt(File inputFile, String password) throws Exception {
        byte[] encText;
        // Erzeugen der outputFile mit der Dateiendung .venc
        File outputFile = new File(inputFile.getAbsolutePath()+".venc");

        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // Erzeugen eines Input Streams aus der inputFile
            FileInputStream inputStream = new FileInputStream(inputFile);
            // Byte Array mit der länge der Datei erzeugen
            byte[] inputBytes = new byte[(int) inputFile.length()];
            //input Stream in das erzeugte Byte Array lesen
            inputStream.read(inputBytes);
            //Entschlüsseln des Byte Arrays in ein neues Byte Array outputBytes
            byte[] outputBytes = cipher.doFinal(inputBytes);
            // Bytes mit Base64Encodieren
            encText = Base64.getEncoder().encode(outputBytes);

            //Erzeugen des Output Streams
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            //Schreiben des output Streams in die outputFile
            outputStream.write(encText);

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
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
    public static void decrypt(File inputFile, String password) throws Exception {
        byte[] decText;
        File outputFile = new File(FilenameUtils.removeExtension(inputFile.getAbsolutePath()));

        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(),"Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = Base64.getDecoder().decode(inputBytes);
            decText = cipher.doFinal(outputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(decText);

            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
