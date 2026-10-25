package ht.fds.mbds.nada.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

@Dependent
public class GenerateurReponse implements Serializable {

    private boolean premiereQuestion = true;

    public String genererReponse(String question, String roleSysteme) {
        String reponse = "||" + question.toLowerCase() + "||";
        if (premiereQuestion) {
            if (roleSysteme != null) {
                reponse = roleSysteme.toUpperCase() + "\n" + reponse;
            }
            premiereQuestion = false;
        }
        return reponse;
    }
}