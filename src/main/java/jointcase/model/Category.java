package jointcase.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends EnumEntity<Category.CategoryType> {

    public enum CategoryType {
        ACTIVITY,
        EVENT,
        MEETING
    }
}
