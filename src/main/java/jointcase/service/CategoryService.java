package jointcase.service;

import java.util.List;
import jointcase.model.Category;

public interface CategoryService {
    Category add(Category category);

    Category get(Long id);

    List<Category> getAll();
}
