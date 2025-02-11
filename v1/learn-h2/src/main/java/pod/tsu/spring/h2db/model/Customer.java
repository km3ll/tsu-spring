package pod.tsu.spring.h2db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = Customer.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    public static final String TABLE_NAME = "customers";

    @Id
    private Long id;

    private String name;

}