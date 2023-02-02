package jointcase.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;

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
