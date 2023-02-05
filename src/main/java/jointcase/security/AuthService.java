package jointcase.security;

import jointcase.exception.AuthException;
import jointcase.model.User;

public interface AuthService {
    User signup(String email, String username, String password)
            throws AuthException;

    User signin(String username, String password) throws AuthException;
}
