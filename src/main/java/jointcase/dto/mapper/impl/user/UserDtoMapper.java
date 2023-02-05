package jointcase.dto.mapper.impl.user;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.user.UserResponseDto;
import jointcase.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("usersDtoMapper")
@Primary
public class UserDtoMapper implements ResponseDtoMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getName())
                .build();
    }
}
