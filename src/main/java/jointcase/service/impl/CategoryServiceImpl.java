package jointcase.service.impl;

import java.util.List;
import jointcase.dao.CategoryDao;
import jointcase.model.Category;
import jointcase.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public Category add(Category category) {
        return categoryDao.add(category);
    }

    @Override
    public Category get(Long id) {
        return categoryDao.get(id).orElseThrow(
                () -> new RuntimeException("Category with id: " + id + " not found.")
        );
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
