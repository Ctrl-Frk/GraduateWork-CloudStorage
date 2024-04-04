package baranov.viacheslav.graduateworkcloudstorage.controller;

import baranov.viacheslav.graduateworkcloudstorage.model.AuthRequest;
import baranov.viacheslav.graduateworkcloudstorage.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/logout")
    public void logout() {
        //SpringSecurity сам выходит
    }
}