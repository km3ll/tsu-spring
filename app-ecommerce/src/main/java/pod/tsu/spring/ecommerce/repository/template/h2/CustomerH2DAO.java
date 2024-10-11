package pod.tsu.spring.ecommerce.repository.template.h2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = CustomerH2DAO.TABLE_NAME)
public class CustomerH2DAO {

    public static final String TABLE_NAME = "customers";

    @Id
    private Long id;
    private String name;

}
