package jointcase.controller;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.user.UserResponseDto;
import jointcase.model.User;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> userResponseDtoMapper;

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        UserResponseDto responseDto = userResponseDtoMapper
                .mapToDto(userService.get(id));
        //TODO: add Avatar link
        return responseDto;
    }
}
