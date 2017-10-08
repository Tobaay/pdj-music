package Controller;

import Service.RegistrationService;
import java.util.Objects;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.User;

@Named
@RequestScoped
@NoArgsConstructor
@Getter
@Setter
public class RegistrationController {

    private static final int MIN_PASSWORD_LENGTH = 8;
    
    private String username;
    private String password;
    private String passwordValidation;
    private String email;
    
    @Inject
    private RegistrationService registrationService;
    
    @Inject
    private MessageController messageController;

    public String register() {
        System.out.println("Sie haben sich als " + username + " mit dem Passwort " + password + " registriert");
        
        System.out.println("Passwörter identisch: " + compPassword());
        
        System.out.println("Input korrekt: " + validInput());
        
        if(validInput())
        {
            User result = registrationService.searchUser(username);
            System.out.println("result: " + result);
            
            if(result == null)
            {
                registrationService.setUser(username, password, email);
                
                messageController.sendFacesMessage(
                    "Erfolgreiche Registration!" ,
                    "Benutzer " + username + " Wurde Erstellt!",
                    FacesMessage.SEVERITY_INFO);
            }
            else
            {
                messageController.sendFacesMessage(
                    "Invalid Registration!",
                    "Username Already Taken, Please Try Again!",
                    FacesMessage.SEVERITY_WARN);
            }
            
        }
        else {
            
            messageController.sendFacesMessage(
                    "Invalid Registration!",
                    "Please Try Again!",
                    FacesMessage.SEVERITY_WARN);

        }
        username = password = passwordValidation = email = "";
        return "index";
    }
    
    public boolean compPassword(){
        return Objects.equals(password, passwordValidation) 
                && password.length() >= MIN_PASSWORD_LENGTH
                && passwordValidation.length() >= MIN_PASSWORD_LENGTH;
    }
    
    public boolean validInput(){
        return username.length() > 0
               && email.length() > 0 
               && compPassword();
    }
}
