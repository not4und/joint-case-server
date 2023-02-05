package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.Category;

public interface CategoryDao {
    Category add(Category category);

    Optional<Category> get(Long id);

    List<Category> getAll();

    void delete(Long id);
}
