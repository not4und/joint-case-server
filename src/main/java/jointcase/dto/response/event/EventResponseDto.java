package jointcase.dto.response.event;

import java.time.LocalDateTime;
import jointcase.dto.response.category.CategoryResponseDto;
import jointcase.dto.response.status.StatusResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class EventResponseDto {
    private Long id;
    private String name;
    private CategoryResponseDto category;
    private StatusResponseDto status;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private int slots;
    private boolean isFull;
}
