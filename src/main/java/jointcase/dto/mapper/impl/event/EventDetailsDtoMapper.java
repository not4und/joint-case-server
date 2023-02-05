package jointcase.dto.mapper.impl.event;

import java.util.stream.Collectors;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.category.CategoryResponseDto;
import jointcase.dto.response.event.EventDetailsResponseDto;
import jointcase.dto.response.file.AttachedFileResponseDto;
import jointcase.dto.response.status.StatusResponseDto;
import jointcase.dto.response.user.UserResponseDto;
import jointcase.model.AttachedFile;
import jointcase.model.Category;
import jointcase.model.Event;
import jointcase.model.Status;
import jointcase.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@AllArgsConstructor
public class EventDetailsDtoMapper
        implements ResponseDtoMapper<EventDetailsResponseDto, Event> {
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;
    private final ResponseDtoMapper<AttachedFileResponseDto, AttachedFile>
            attachedFileResponseDtoMapper;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;
    private final ResponseDtoMapper<StatusResponseDto, Status> statusResponseDtoMapper;

    @Override
    public EventDetailsResponseDto mapToDto(Event event) {
        return EventDetailsResponseDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .category(categoryResponseDtoMapper.mapToDto(event.getCategory()))
                .status(statusResponseDtoMapper.mapToDto(event.getStatus()))
                .fromDateTime(event.getFromDateTime())
                .toDateTime(event.getToDateTime())
                .slots(event.getSlots())
                .isFull(event.isFull())
                .owner(userResponseDtoMapper.mapToDto(event.getOwner()))
                .members(event.getMembers().stream()
                        .map(userResponseDtoMapper::mapToDto)
                        .collect(Collectors.toList()))
                .files(event.getFiles().stream()
                        .map(attachedFileResponseDtoMapper::mapToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
