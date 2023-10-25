package pod.tsu.spring.pokemon.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    private int id;
    private String title;
    private String content;
    private int stars;

    private Pokemon pokemon;

}