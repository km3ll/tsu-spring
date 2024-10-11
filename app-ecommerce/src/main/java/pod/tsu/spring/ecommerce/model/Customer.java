package pod.tsu.spring.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Customer {

    private Long id;
    private String name;

}