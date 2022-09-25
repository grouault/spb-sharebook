package com.udemy.sharebook.user;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value="/users")
    public ResponseEntity addUser(@Valid @RequestBody UserInfo user) {

        // Scenario de l'inscription.
        UserInfo userEmail = userRepository.findByEmail(user.getEmail());
        if (userEmail != null) {
            return new ResponseEntity("User already existing", HttpStatus.BAD_REQUEST);
        }
        UserInfo createdUser = userRepository.save(user);
        return new ResponseEntity(createdUser, HttpStatus.CREATED);

    }

}
