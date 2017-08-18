package Controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Named
@RequestScoped
@NoArgsConstructor
@Getter
@Setter
public class RegistrationController {

    private String username;
    private String password;

    public void resgister() {
        System.out.println("Sie haben sich als" + username + "mit dem Passwort" + password + "registriert");
    }
}
