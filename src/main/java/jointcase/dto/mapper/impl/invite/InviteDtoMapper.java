package jointcase.dto.mapper.impl.invite;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.event.EventResponseDto;
import jointcase.dto.response.invite.InviteResponseDto;
import jointcase.model.Event;
import jointcase.model.Invite;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InviteDtoMapper implements ResponseDtoMapper<InviteResponseDto, Invite> {
    private final ResponseDtoMapper<EventResponseDto, Event> eventResponseDtoMapper;

    @Override
    public InviteResponseDto mapToDto(Invite invite) {
        return InviteResponseDto.builder()
                .id(invite.getId())
                .event(eventResponseDtoMapper.mapToDto(invite.getEvent()))
                .build();
    }
}
