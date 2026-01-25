package pod.tsu.spring.pokemon.service;

import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.dto.AllPokemonDto;

public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);

    AllPokemonDto getAllPokemon();

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    void deletePokemonById(int id);

}