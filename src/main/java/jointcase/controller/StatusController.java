package jointcase.controller;

import java.util.List;
import java.util.stream.Collectors;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.status.StatusResponseDto;
import jointcase.model.Status;
import jointcase.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statuses")
@AllArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final ResponseDtoMapper<StatusResponseDto, Status> statusResponseDtoMapper;

    @GetMapping
    public List<StatusResponseDto> getAll() {
        return statusService.getAll().stream()
                .map(statusResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
