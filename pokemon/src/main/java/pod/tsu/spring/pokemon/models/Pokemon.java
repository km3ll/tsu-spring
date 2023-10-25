package pod.tsu.spring.pokemon.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokemon {

    private int id;
    private String name;
    private String type;

    private List<Review> reviews = new ArrayList<Review>();

}