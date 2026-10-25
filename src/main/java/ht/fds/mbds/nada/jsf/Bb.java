package ht.fds.mbds.nada.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class Bb implements Serializable {

    private String roleSysteme;
    private boolean roleSystemeChoisi;
    private String question;
    private String reponse;
    private StringBuilder conversation = new StringBuilder();

    private final List<String> roles =
            List.of("Assistant", "Traducteur français-anglais", "Guide touristique");

    @Inject
    private FacesContext facesContext;

    @Inject
    private GenerateurReponse generateur;

    public String envoyer() {
        if (question == null || question.isBlank()) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Texte question vide",
                    "Il manque le texte de la question"));
            return null;
        }
        if (roleSysteme == null || roleSysteme.isBlank()) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Rôle manquant",
                    "Choisissez le rôle de l'API"));
            return null;
        }
        boolean premiere = conversation.length() == 0;
        reponse = generateur.generer(question, roleSysteme, premiere);
        roleSystemeChoisi = true;
        conversation.append("== Utilisateur :\n").append(question)
                .append("\n== Réponse :\n").append(reponse).append("\n");
        return null;
    }

    public String nouveauChat() {
        return "index";
    }

    public String getRoleSysteme() { return roleSysteme; }
    public void setRoleSysteme(String roleSysteme) { this.roleSysteme = roleSysteme; }
    public boolean isRoleSystemeChoisi() { return roleSystemeChoisi; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getReponse() { return reponse; }
    public void setReponse(String reponse) { this.reponse = reponse; }
    public String getConversation() { return conversation.toString(); }
    public List<String> getRoles() { return roles; }
}