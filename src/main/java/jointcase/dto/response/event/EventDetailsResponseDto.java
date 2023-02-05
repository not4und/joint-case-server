package jointcase.dto.response.event;

import java.util.List;
import jointcase.dto.response.file.AttachedFileResponseDto;
import jointcase.dto.response.user.UserResponseDto;
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
public class EventDetailsResponseDto extends EventResponseDto {
    private String description;
    private UserResponseDto owner;
    private List<UserResponseDto> members;
    private List<AttachedFileResponseDto> files;
}
