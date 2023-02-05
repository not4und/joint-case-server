package jointcase.dto.response.user;

import java.util.List;
import jointcase.dto.response.event.EventResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDetailsResponseDto extends UserResponseDto {
    private List<EventResponseDto> events;
}
