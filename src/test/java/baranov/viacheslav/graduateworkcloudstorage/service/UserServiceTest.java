package baranov.viacheslav.graduateworkcloudstorage.service;

import baranov.viacheslav.graduateworkcloudstorage.model.entity.User;
import baranov.viacheslav.graduateworkcloudstorage.repository.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @MockBean
    private UserRepository userRepositoryTest;

    @Autowired
    private UserService userServiceTest;

    @Test
    @SneakyThrows
    void loadUserByUsernameCorrectRequest() {
        //arrange
        String testUsername = "testUser@mail.ru";
        User user = User.builder()
                .email(testUsername).build();
        when(userRepositoryTest.findUserByEmail(Mockito.any())).thenReturn(Optional.of(user));

        //act
        User result = userServiceTest.findUserByEmail(testUsername).orElse(null);

        //assert
        Assertions.assertEquals(user, result);
    }
}