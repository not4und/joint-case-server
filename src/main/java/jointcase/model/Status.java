package jointcase.model;

import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "statuses")
@EqualsAndHashCode(callSuper = true)
public class Status extends EnumEntity<Status.StatusType> {
    public enum StatusType {
        IN_PROCESS,
        PENDING,
        CLOSED,
        FULL
    }
}
