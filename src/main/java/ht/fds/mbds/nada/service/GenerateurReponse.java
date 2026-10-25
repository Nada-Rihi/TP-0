package ht.fds.mbds.nada.service;

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;

/**
 * Service qui génère la réponse à une question.
 * Portée Dependent : une instance par backing bean Bb dans lequel elle est injectée.
 * Serializable car Bb (@ViewScoped) est sérialisable.
 *
 * Traitement personnel : la réponse contient la question avec l'ordre des mots inversé,
 * suivie du nombre de mots et du nombre de voyelles de la question.
 */
@Dependent
public class GenerateurReponse implements Serializable {

    private static final String VOYELLES = "aeiouàâäéèêëîïôöùûüœ";

    /**
     * Inverse l'ordre des mots de la question et ajoute des statistiques.
     *
     * @param question    la question posée par l'utilisateur (non vide)
     * @param roleSysteme le rôle de l'API (non utilisé dans ce traitement)
     * @return la réponse construite
     */
    public String genererReponse(String question, String roleSysteme) {
        String[] mots = question.trim().split("\\s+");

        StringBuilder inverse = new StringBuilder();
        for (int i = mots.length - 1; i >= 0; i--) {
            inverse.append(mots[i]);
            if (i > 0) {
                inverse.append(' ');
            }
        }

        int nbVoyelles = 0;
        for (char c : question.toLowerCase().toCharArray()) {
            if (VOYELLES.indexOf(c) >= 0) {
                nbVoyelles++;
            }
        }

        return inverse + "\n(" + mots.length + " mot(s), " + nbVoyelles + " voyelle(s))";
    }
}