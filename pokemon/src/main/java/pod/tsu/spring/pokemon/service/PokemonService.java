package pod.tsu.spring.pokemon.service;

import pod.tsu.spring.pokemon.dto.PokemonDto;

public interface PokemonService {

    //PokemonDto createPokemon(PokemonDto pokemonDto);

    //PokemonResponse getAllPokemon(int pageNo, int pageSize);

    PokemonDto getPokemonById(int id);

    //PokemonDto updatePokemon(PokemonDto pokemonDto, int id);

    //void deletePokemonId(int id);

}