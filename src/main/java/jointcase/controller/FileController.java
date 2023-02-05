package jointcase.controller;

import java.util.Map;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.file.AttachedFileResponseDto;
import jointcase.model.AttachedFile;
import jointcase.model.Event;
import jointcase.model.User;
import jointcase.service.AttachedFileFsService;
import jointcase.service.AttachedFileService;
import jointcase.service.AvatarFsService;
import jointcase.service.AvatarService;
import jointcase.service.EventService;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
    private final AttachedFileService attachedFileService;
    private final AttachedFileFsService attachedFileFsService;
    private final AvatarService avatarService;
    private final AvatarFsService avatarFsService;
    private final EventService eventService;
    private final UserService userService;
    private final ResponseDtoMapper<AttachedFileResponseDto, AttachedFile>
            attachedFileResponseDtoMapper;

    @PostMapping(value = "/events/{eventId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public AttachedFileResponseDto addFile(Authentication auth,
                                           @PathVariable Long eventId,
                                           @RequestParam MultipartFile file) {
        Event event = eventService.get(eventId);
        if (!parseUser(auth).getId().equals(event.getOwner().getId())) {
            throw new RuntimeException("You are not owner.");
        }
        AttachedFile attachedFile = AttachedFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .path(attachedFileFsService.add(file))
                .build();
        event.getFiles().add(attachedFileService.add(attachedFile));
        eventService.update(event);
        return attachedFileResponseDtoMapper.mapToDto(attachedFile);
    }

    @GetMapping("/events/{eventId}/{fileId}")
    public ResponseEntity<Object> getFile(Authentication auth,
                                          @PathVariable Long eventId,
                                          @PathVariable Long fileId) {
        Event event = eventService.get(eventId);
        User user = parseUser(auth);
        if (!event.getOwner().getId().equals(user.getId()) || !event.getMembers().contains(user)) {
            throw new RuntimeException("You are not an owner or member.");
        }
        return new ResponseEntity<>(Map.of("fileData",
                attachedFileFsService.get(attachedFileService.get(fileId).getPath())),
                HttpStatus.OK);
    }

    @DeleteMapping("/events/{eventId}/{fileId}")
    public void deleteFile(Authentication auth,
                           @PathVariable Long eventId,
                           @PathVariable Long fileId) {
        Event event = eventService.get(eventId);
        if (!parseUser(auth).getId().equals(event.getOwner().getId())) {
            throw new RuntimeException("You are not owner.");
        }
        AttachedFile attachedFile = attachedFileService.get(fileId);
        event.getFiles().remove(attachedFile);
        eventService.update(event);
        attachedFileFsService.delete(attachedFile.getPath());
        attachedFileService.delete(fileId);
    }

    @GetMapping("/avatars/{userId}")
    public ResponseEntity<Object> getAvatar(@PathVariable Long userId) {
        return new ResponseEntity<>(Map.of("imageData",
                avatarFsService.get(avatarService.get(userId).getPath())),
                HttpStatus.OK);
    }

    private User parseUser(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userService.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found.")
        );
    }
}
