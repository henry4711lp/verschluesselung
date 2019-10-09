package languages;

public class languageChooser {
    private String ActualLanguage1;

    public void setActualLanguage(String givenLanguage){ActualLanguage1 = givenLanguage;}
    public String getActualLanguage(){return ActualLanguage1;}

    public String gT(String giver){ //gt = getTranslation shorted for convenience
        if(ActualLanguage1.equals("EN")){
            if(giver.equals("Bestätigen"))          return "Confirm";
            if(giver.equals("Eigenes Passwort"))    return "Own password";
            if(giver.equals("Erfolgsnachricht"))    return "Successfully en/decrypted!";
            if(giver.equals("Datei"))               return "File";
            if(giver.equals("Textbasiert"))         return "Textbased";
            if(giver.equals("Typwahl"))             return "Choose type of data";
            if(giver.equals("Typwähler"))           return "Typechooser";
            if(giver.equals("Passwort"))            return "Password";
            if(giver.equals("Ihr Text"))            return "Your text";
            if(giver.equals("Ihr Passwort"))        return "Your Password";
            if(giver.equals("Ihr Ergebnis"))        return "Your Result";
            if(giver.equals("Eingabetext"))         return "Inputtext";
            if(giver.equals("Ausgabefeld"))         return "Outputfield";
            if(giver.equals("Verschlüsseln"))       return "Encrypt";
            if(giver.equals("Entschlüsseln"))       return "Decrypt";
            if(giver.equals("Verlassen"))           return "Exit";
            if(giver.equals("Passwort generieren")) return "generate password";
            if(giver.equals("Passwortgenerator"))   return "Passwordgenerator";
            if(giver.equals("Passwortstärke"))      return "Passwordstrenght";
            if(giver.equals("Passwortlänge"))       return "Passwordlength";
            if(giver.equals("Übernehmen"))          return "apply";
            if(giver.equals("Schließen"))           return "Exit";
            if(giver.equals("Error1"))              return "5 tries until System is halted";
            if(giver.equals("Error2"))              return "Error";
            if(giver.equals("Error3"))              return "To many input tries";
            if(giver.equals("Error4"))              return "Deactivating system for 1 hour!";
            if(giver.equals("Übergeben"))           return "Apply";
        }
        if(ActualLanguage1.equals("DE")){
            if(giver.equals("Erfolgsnachricht"))return "Erfolgreich ent/verschlüsselt";
            if(giver.equals("Error1"))          return "5 Versuche bevor das System gesperrt wird";
            if(giver.equals("Error2"))          return "Fehler";
            if(giver.equals("Error3"))          return "Zu viele Versuche";
            if(giver.equals("Error4"))          return "System für 1 Stunde deaktiviert";
           return giver;
        }
        return null;
    }
}