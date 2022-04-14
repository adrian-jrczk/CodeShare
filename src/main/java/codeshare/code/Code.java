package codeshare.code;

import codeshare.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class Code {

    @Id
    @JsonIgnore
    private String uuid;
    private String name;
    @Lob
    private String code;
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