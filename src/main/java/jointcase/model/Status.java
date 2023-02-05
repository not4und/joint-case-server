package jointcase.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "statuses")
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Status extends EnumEntity<Status.StatusType> {
    public enum StatusType {
        IN_PROCESS,
        PENDING,
        CLOSED
    }
}
