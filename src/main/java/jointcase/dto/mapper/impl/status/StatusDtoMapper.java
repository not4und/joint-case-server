package jointcase.dto.mapper.impl.status;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.status.StatusResponseDto;
import jointcase.model.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusDtoMapper implements ResponseDtoMapper<StatusResponseDto, Status> {
    @Override
    public StatusResponseDto mapToDto(Status status) {
        return StatusResponseDto.builder()
                .id(status.getId())
                .name(status.getType().name())
                .build();
    }
}
