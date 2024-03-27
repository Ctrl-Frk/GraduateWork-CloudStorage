package baranov.viacheslav.graduateworkcloudstorage.service;

import baranov.viacheslav.graduateworkcloudstorage.jwt.JwtTokenUtils;
import baranov.viacheslav.graduateworkcloudstorage.model.AuthRequest;
import baranov.viacheslav.graduateworkcloudstorage.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    public ResponseEntity<?> login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().header("Description", "Bad credentials").build();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok()
                .header("Description", "Success authorization")
                .body(new AuthResponse(token));
    }
}