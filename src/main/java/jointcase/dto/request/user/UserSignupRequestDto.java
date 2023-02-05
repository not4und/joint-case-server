package jointcase.dto.request.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import jointcase.util.validator.annotation.Email;
import jointcase.util.validator.annotation.PasswordCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordCheck(field = "password", fieldMatch = "repeatPassword")
public class UserSignupRequestDto {
    @Email
    private String email;
    @NotBlank(message = "The username couldn't be empty.")
    @Size(min = 3, message = "The username must be at least 3 symbols long.")
    private String username;
    @NotBlank(message = "The password couldn't be empty.")
    @Size(min = 6, message = "The password must be at least 6 symbols long.")
    private String password;
    private String repeatPassword;
}
