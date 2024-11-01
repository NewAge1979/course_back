package pl.anigram.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import pl.anigram.backend.model.SignUpSuccess;
import pl.anigram.backend.model.entity.User;
import pl.anigram.backend.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthControllerTest {
    public static final String API_1_0_AUTH_SIGN_UP = "/api/1.0/auth/signUp";

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void postSignUp_whenUserIsValid_receiveOk() {
        User user = getUser();
        ResponseEntity<Object> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void postSignUp_whenUserIsValid_userSavedToDatabase() {
        User user = getUser();
        restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    public void postSignUp_whenUserIsValid_receiveSuccessMessage() {
        User user = getUser();
        ResponseEntity<SignUpSuccess> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, SignUpSuccess.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postSignUp_whenUserIsValid_passwordIsHashedInDatabase() {
        User user = getUser();
        ResponseEntity<SignUpSuccess> response = restTemplate.postForEntity(API_1_0_AUTH_SIGN_UP, user, SignUpSuccess.class);
        List<User> users = userRepository.findAll();
        User user1 = users.get(0);
        assertThat(user1.getPassword()).isNotEqualTo(user.getPassword());
    }

    private static User getUser() {
        User user = new User();
        user.setUsername("test-user");
        user.setDisplayName("test-display");
        user.setPassword("password");
        return user;
    }
}