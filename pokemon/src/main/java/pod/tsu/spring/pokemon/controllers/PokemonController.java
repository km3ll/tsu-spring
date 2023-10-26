package pod.tsu.spring.pokemon.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.dto.PokemonResponse;
import pod.tsu.spring.pokemon.service.PokemonService;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private final Logger logger = LoggerFactory.getLogger(PokemonController.class);

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
        logger.info("Created");
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons() {
        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> pokemonDetail(@PathVariable int id) {
        return ResponseEntity.ok(pokemonService.getPokemonById(id));
    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int pokemonId) {
        PokemonDto response = pokemonService.updatePokemon(pokemonDto, pokemonId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId) {
        pokemonService.deletePokemonById(pokemonId);
        return ResponseEntity.ok(String.format("Pokemon with id '%d' was deleted", pokemonId));
    }

}