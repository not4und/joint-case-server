package jointcase.dto.response.invite;

import jointcase.dto.response.event.EventResponseDto;
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
public class InviteResponseDto {
    private Long id;
    private EventResponseDto event;
}
