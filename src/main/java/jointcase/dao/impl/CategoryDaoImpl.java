package jointcase.dao.impl;

import jointcase.dao.CategoryDao;
import jointcase.model.Category;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao  {
    public CategoryDaoImpl(SessionFactory factory) {
        super(factory, Category.class);
    }
}
