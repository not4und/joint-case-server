package jointcase.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import jointcase.dto.mapper.RequestDtoMapper;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.request.event.EventRequestDto;
import jointcase.dto.response.event.EventDetailsResponseDto;
import jointcase.dto.response.event.EventResponseDto;
import jointcase.exception.AuthException;
import jointcase.model.AttachedFile;
import jointcase.model.Event;
import jointcase.model.User;
import jointcase.service.AttachedFileFsService;
import jointcase.service.AttachedFileService;
import jointcase.service.EventService;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {
    private final UserService userService;
    private final EventService eventService;
    private final AttachedFileService attachedFileService;
    private final AttachedFileFsService attachedFileFsService;
    private final ResponseDtoMapper<EventDetailsResponseDto, Event>
            eventDetailsResponseDtoMapper;
    private final ResponseDtoMapper<EventResponseDto, Event> eventResponseDtoMapper;
    private final RequestDtoMapper<EventRequestDto, Event> eventRequestDtoMapper;

    @PostMapping
    public EventDetailsResponseDto add(Authentication auth,
            @RequestBody EventRequestDto requestDto) {
        Event event = eventRequestDtoMapper.mapToModel(requestDto);
        event.setOwner(parseUser(auth));
        event.setFiles(Arrays.stream(requestDto.getFiles())
                .map((f) -> attachedFileService.add(AttachedFile.builder()
                                .name(f.getOriginalFilename())
                                .type(f.getContentType())
                                .path(attachedFileFsService.add(f))
                        .build()))
                .collect(Collectors.toList()));
        return eventDetailsResponseDtoMapper.mapToDto(eventService.add(event));
    }

    @PutMapping("/{id}")
    public EventDetailsResponseDto update(Authentication auth,
                                          @PathVariable Long id,
                                          @RequestBody EventRequestDto requestDto)
            throws AuthException {
        Event event = eventService.get(id);
        if (!event.getOwner().equals(parseUser(auth))) {
            throw new AuthException("You aren't an owner.");
        }
        Event newEvent = eventRequestDtoMapper.mapToModel(requestDto);
        newEvent.setId(event.getId());
        newEvent.setOwner(event.getOwner());
        //files
        return eventDetailsResponseDtoMapper.mapToDto(eventService.update(newEvent));
    }

    @GetMapping("/{id}")
    public EventDetailsResponseDto get(Authentication auth, @PathVariable Long id)
            throws AuthException {
        Event event = eventService.get(id);
        User user = parseUser(auth);
        if (event.isPrivate()
                && !(event.getOwner().equals(user) || event.getMembers().contains(user))) {
            throw new AuthException("Event is a private.");
        }
        return eventDetailsResponseDtoMapper.mapToDto(event);
    }

    @GetMapping("/by-category/{id}")
    public List<EventResponseDto> getAllByCategoryAndUser(Authentication auth,
                                                   @PathVariable Long id,
                                                   @RequestParam Long userId) {
        List<Event> events;
        if (userId == null) {
            events = eventService.getAllByCategoryAndUser(id, parseUser(auth).getId(), true);
        } else {
            events = eventService.getAllByCategoryAndUser(id, userId, false);
        }
        return events.stream()
                .map(eventResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    private User parseUser(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }
}
