package learn.spring.boot.courses.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Course {
    private long id;
    private String name;
    private String author;
}
