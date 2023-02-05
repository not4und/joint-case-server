package jointcase.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.invite.InviteResponseDto;
import jointcase.model.Event;
import jointcase.model.Invite;
import jointcase.model.Status;
import jointcase.model.User;
import jointcase.service.EventService;
import jointcase.service.InviteService;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invites")
@AllArgsConstructor
public class InviteController {
    private final UserService userService;
    private final EventService eventService;
    private final InviteService inviteService;
    private final ResponseDtoMapper<InviteResponseDto, Invite> inviteResponseDtoMapper;

    //add
    @PostMapping("/events/{id}")
    public ResponseEntity<Object> add(Authentication auth,
                                      @PathVariable Long id,
                                      @RequestParam Long userId) {
        Event event = eventService.get(id);
        User owner = parseUser(auth);
        User user = userService.get(userId);
        if (owner.getId().equals(user.getId())) {
            throw new RuntimeException("You can't invite yourself.");
        }
        if (event.isFull() || event.getStatus().getType() == Status.StatusType.CLOSED) {
            throw new RuntimeException("Event is full or closed.");
        }
        inviteService.add(Invite.builder()
                        .event(event)
                        .receiver(user)
                .build());
        return new ResponseEntity<>(Map.of("userId", userId), HttpStatus.OK);
    }

    @GetMapping
    public List<InviteResponseDto> getAllByUser(Authentication auth) {
        return inviteService.getAllByUser(parseUser(auth).getId()).stream()
                .map(inviteResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}/join")
    public synchronized ResponseEntity<Object> join(Authentication auth,
                                                    @PathVariable Long id) {
        User user = parseUser(auth);
        Invite invite = inviteService.get(id);
        checkInviteReceiver(invite.getReceiver().getId(), user.getId());
        Event event = eventService.get(invite.getEvent().getId());
        inviteService.delete(id);
        if (event.isFull()) {
            throw new RuntimeException("That event is full.");
        }
        event.getMembers().add(user);
        if (event.getMembers().size() == event.getSlots()) {
            event.setFull(true);
        }
        eventService.update(event);
        return new ResponseEntity<>(Map.of("eventId", event.getId(), "userId", user.getId()),
                HttpStatus.OK);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Object> reject(Authentication auth,
                                         @PathVariable Long id) {
        User user = parseUser(auth);
        Invite invite = inviteService.get(id);
        checkInviteReceiver(invite.getReceiver().getId(), user.getId());
        inviteService.delete(id);
        return new ResponseEntity<>(Map.of("eventId", invite.getEvent().getId(),
                "userId", user.getId()), HttpStatus.OK);
    }

    private void checkInviteReceiver(Long receiverId, Long userId) {
        if (!receiverId.equals(userId)) {
            throw new RuntimeException("That invite not for you :)");
        }
    }

    private User parseUser(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }
}
