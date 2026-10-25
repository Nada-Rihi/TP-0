package ht.fds.mbds.nada.jsf;

import jakarta.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public class GenerateurReponse implements Serializable {

    public String generer(String question, String roleSysteme, boolean premiereQuestion) {
        StringBuilder sb = new StringBuilder();
        if (premiereQuestion) {
            sb.append(roleSysteme.toUpperCase()).append("\n");
        }
        sb.append("||").append(changerCasse(question)).append("||");
        return sb.toString();
    }

    private String changerCasse(String texte) {
        StringBuilder sb = new StringBuilder();
        for (char c : texte.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }
}