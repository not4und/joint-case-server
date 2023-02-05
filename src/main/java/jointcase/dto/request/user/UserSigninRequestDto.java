package jointcase.dto.request.user;

import javax.validation.constraints.NotBlank;
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
public class UserSigninRequestDto {
    @NotBlank(message = "The username couldn't be empty.")
    private String username;
    @NotBlank(message = "The password couldn't be empty.")
    private String password;
}
