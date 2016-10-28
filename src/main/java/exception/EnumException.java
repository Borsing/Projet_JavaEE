package exception;

/**
 * Created by adric on 28/10/2016.
 */
public enum EnumException {

    WRONG_LOGIN("Identifiant et/ou mot de passe incorrects"),
    USER_ALREADY_EXISTS("L'email est déjà utilisé pour un autre utilisateur"),
    SOMETHING_WRONG("Quelque chose s'est mal passé"),
    PARTICIPANT_ALREADY_EXISTS("Un participant avec cette adresse mail est déjà inscrit à l'événement");


    private String name = "";

    //Constructeur
    EnumException(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
