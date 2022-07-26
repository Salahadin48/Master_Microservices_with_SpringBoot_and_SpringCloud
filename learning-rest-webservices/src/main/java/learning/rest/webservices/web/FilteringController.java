package learning.rest.webservices.web;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import learning.rest.webservices.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {

    // field1, field2
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        Set<String> fieldsToInclude = Set.of("field1", "field2");
        return createFiltering(fieldsToInclude, "SomeBeanFilter", someBean);
    }

    // field2, field3
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean(){
        List<SomeBean> someBeanList = List.of(new SomeBean("Value1", "Value2", "Value3"), new SomeBean("Value4", "Value5", "Value6"), new SomeBean("Value7", "Value8", "Value9"));

        Set<String> fieldsToInclude = Set.of("field2", "field3");
        return createFiltering(fieldsToInclude, "SomeBeanFilter", someBeanList);
    }

    public MappingJacksonValue createFiltering(Set<String> fieldsToInclude, String filterName, Object object){
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToInclude);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);
        MappingJacksonValue mapping = new MappingJacksonValue(object);
        mapping.setFilters(filters);
        return mapping;
    }
}
