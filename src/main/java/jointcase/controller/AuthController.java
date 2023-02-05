package jointcase.controller;

import java.util.Map;
import javax.validation.Valid;
import jointcase.dto.request.user.UserSigninRequestDto;
import jointcase.dto.request.user.UserSignupRequestDto;
import jointcase.exception.AuthException;
import jointcase.model.User;
import jointcase.security.AuthService;
import jointcase.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid UserSignupRequestDto dto)
            throws AuthException {
        return createToken(authService.signup(dto.getEmail(), dto.getUsername(),
                dto.getPassword()));
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody @Valid UserSigninRequestDto dto)
            throws AuthException {
        return createToken(authService.signin(dto.getUsername(), dto.getPassword()));
    }

    private ResponseEntity<Object> createToken(User user) {
        return new ResponseEntity<>(Map.of("token",
                jwtTokenProvider.createToken(user.getName())), HttpStatus.OK);
    }
}
