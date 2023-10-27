package pod.tsu.spring.pokemon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.dto.AllPokemonDto;
import pod.tsu.spring.pokemon.exceptions.PokemonNotFoundException;
import pod.tsu.spring.pokemon.models.Pokemon;
import pod.tsu.spring.pokemon.repository.PokemonRepository;
import pod.tsu.spring.pokemon.service.PokemonService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);

    private final PokemonRepository pokemonRepository;

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToModel(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        logger.info("Created");
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto dto = new PokemonDto();
        dto.setId(newPokemon.getId());
        dto.setName(newPokemon.getName());
        dto.setType(newPokemon.getType());
        return dto;
    }

    @Override
    public AllPokemonDto getAllPokemon() {
        Iterable<Pokemon> pokemons = pokemonRepository.findAll();
        List<PokemonDto> result = new ArrayList<>();
        pokemons.forEach(pokemon -> result.add(mapToDto(pokemon)));
        AllPokemonDto response = new AllPokemonDto();
        response.setContent(result);
        response.setTotalElements(result.size());
        return response;
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id)
            .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id)
            .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id)
            .orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be deleted"));
        pokemonRepository.delete(pokemon);
    }

}