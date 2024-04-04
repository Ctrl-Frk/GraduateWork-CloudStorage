package baranov.viacheslav.graduateworkcloudstorage.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class JwtTokenUtilsTest {
    static UserDetails userDetails;
    static String testEmail = "test@mail.ru";
    static String testPassword = "test";
    static String testRole = "ROLE_USER";
    String testToken;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @BeforeAll
    static void prepare() {
        userDetails = User.builder()
                .username(testEmail)
                .password(testPassword)
                .authorities(testRole)
                .build();
    }

    @BeforeEach
    void setUp() {
        testToken = jwtTokenUtils.generateToken(userDetails);
    }

    @Test
    void generateToken() {
        //arrange
        userDetails = User.builder()
                .username(testEmail)
                .password(testPassword)
                .authorities(testRole)
                .build();

        //act
        String testToken = jwtTokenUtils.generateToken(userDetails);

        //assert
        Assertions.assertNotNull(testToken);
    }


    @Test
    void getUsernameFromToken() {
        //act
        String result = jwtTokenUtils.getUsernameFromToken(testToken);

        //assert
        Assertions.assertEquals(testEmail, result);
    }

    @Test
    void getRole() {
        //act
        String result = jwtTokenUtils.getRole(testToken);

        //assert
        Assertions.assertEquals(testRole, result);
    }
}