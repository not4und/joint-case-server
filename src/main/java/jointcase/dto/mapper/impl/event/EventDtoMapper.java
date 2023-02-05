package jointcase.dto.mapper.impl.event;

import java.util.Arrays;
import java.util.stream.Collectors;
import jointcase.dto.mapper.RequestDtoMapper;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.request.event.EventRequestDto;
import jointcase.dto.response.category.CategoryResponseDto;
import jointcase.dto.response.event.EventResponseDto;
import jointcase.dto.response.status.StatusResponseDto;
import jointcase.model.Category;
import jointcase.model.Event;
import jointcase.model.Status;
import jointcase.service.CategoryService;
import jointcase.service.StatusService;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventDtoMapper implements ResponseDtoMapper<EventResponseDto, Event>,
        RequestDtoMapper<EventRequestDto, Event> {
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;
    private final ResponseDtoMapper<StatusResponseDto, Status> statusResponseDtoMapper;
    private final CategoryService categoryService;
    private final StatusService statusService;
    private final UserService userService;

    @Override
    public EventResponseDto mapToDto(Event event) {
        return EventResponseDto.builder()
                .id(event.getId())
                .name(event.getName())
                .category(categoryResponseDtoMapper.mapToDto(event.getCategory()))
                .status(statusResponseDtoMapper.mapToDto(event.getStatus()))
                .fromDateTime(event.getFromDateTime())
                .toDateTime(event.getToDateTime())
                .slots(event.getSlots())
                .isFull(event.isFull())
                .build();
    }

    @Override
    public Event mapToModel(EventRequestDto dto) {
        return Event.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(categoryService.get(dto.getCategoryId()))
                .status(statusService.getByName(Status.StatusType.PENDING))
                .fromDateTime(dto.getFromDateTime())
                .toDateTime(dto.getToDateTime())
                .slots(dto.getSlots())
                .isFull(false)
                .isPrivate(dto.isPrivate())
                .members(Arrays.stream(dto.getMembersIds())
                        .map(userService::get)
                        .collect(Collectors.toSet()))
                .build();
    }
}
