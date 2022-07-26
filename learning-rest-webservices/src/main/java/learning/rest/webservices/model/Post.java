package learning.rest.webservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@Entity(name = "POST_TABLE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @Getter(value = AccessLevel.NONE)
//    @Setter(value = AccessLevel.NONE)
    private User user;
}
