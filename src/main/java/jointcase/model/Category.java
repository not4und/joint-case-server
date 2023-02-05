package jointcase.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Category extends EnumEntity<Category.CategoryType> {
    public enum CategoryType {
        ENTERTAINMENT,
        EDUCATION,
        MEETING
    }
}
