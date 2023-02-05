package jointcase.dto.request.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDto {
    private String name;
    private String description;
    private Long categoryId;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private Integer slots;
    private boolean isPrivate;
    private Long[] membersIds;
    private MultipartFile[] files;
}
