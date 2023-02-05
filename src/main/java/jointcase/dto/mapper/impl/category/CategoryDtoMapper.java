package jointcase.dto.mapper.impl.category;

import jointcase.dto.mapper.ResponseDtoMapper;
import jointcase.dto.response.category.CategoryResponseDto;
import jointcase.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoMapper implements ResponseDtoMapper<CategoryResponseDto, Category> {
    @Override
    public CategoryResponseDto mapToDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getType().name())
                .build();
    }
}
