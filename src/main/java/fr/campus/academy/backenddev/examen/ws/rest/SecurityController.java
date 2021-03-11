package fr.campus.academy.backenddev.examen.ws.rest;

import fr.campus.academy.backenddev.examen.ws.rest.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE, value="/connected-user")
public class SecurityController {

    @GetMapping
    public ResponseEntity<UserDTO> currentUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return ResponseEntity.ok(new UserDTO(principal.getName(), "mail@mail.com"));
    }

}
