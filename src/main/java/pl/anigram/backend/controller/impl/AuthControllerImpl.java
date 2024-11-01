package pl.anigram.backend.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.anigram.backend.controller.AuthController;
import pl.anigram.backend.model.SignUpSuccess;
import pl.anigram.backend.model.entity.User;
import pl.anigram.backend.service.UserService;

@RestController
@RequestMapping("/api/1.0/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final UserService userService;

    @Override
    @PostMapping("/signUp")
    public ResponseEntity<SignUpSuccess> signUp(@RequestBody User user) {
        userService.signUp(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SignUpSuccess("Sign up successful"));
    }
}