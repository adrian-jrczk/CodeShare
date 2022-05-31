package codeshare.code;

import codeshare.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false, unique=true)
    @JsonIgnore
    private UUID uuid;
    private String name;
    @Lob
    private String stringValue;
    private boolean setAsPrivate;
    private boolean editable;
    private boolean viewRestricted;
    private int viewsAllowed;
    private boolean timeRestricted;
    private long minutesAllowed;

    @DateTimeFormat(pattern = "hh:mm:ss dd/MM/yyyy")
    private LocalDateTime createdAt;

    @DateTimeFormat(pattern = "hh:mm:ss dd/MM/yyyy")
    private LocalDateTime lastModified;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "creator_name")
    private User creator;
}