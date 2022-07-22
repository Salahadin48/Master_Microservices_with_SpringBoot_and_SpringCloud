package learning.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "USER_TABLE")
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ROLE")
    private String role;

    public User(String name, String role) {
        super();
        this.name = name;
        this.role = role;
    }
}
