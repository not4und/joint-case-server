package jointcase.controller;

import java.util.List;
import java.util.stream.Collectors;
import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.category.CategoryResponseDto;
import jointcase.model.Category;
import jointcase.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ResponseDtoMapper<CategoryResponseDto, Category> categoryResponseDtoMapper;

    @GetMapping
    public List<CategoryResponseDto> getAll() {
        return categoryService.getAll().stream()
                .map(categoryResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
