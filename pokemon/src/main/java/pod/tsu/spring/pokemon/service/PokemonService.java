package pod.tsu.spring.pokemon.service;

import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.dto.PokemonResponse;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getAllPokemon();

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    void deletePokemonById(int id);

}