package learning.rest.webservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonFilter("SomeBeanFilter")
//@JsonIgnoreProperties(value = "value1") - static filtering at class level
public class SomeBean {
    private String field1;

    private String field2;

    //    @JsonIgnore - static filtering at field level
    private String field3;
}
