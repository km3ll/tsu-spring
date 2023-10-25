package pod.tsu.spring.pokemon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.exceptions.PokemonNotFoundException;
import pod.tsu.spring.pokemon.models.Pokemon;
import pod.tsu.spring.pokemon.repository.PokemonRepository;
import pod.tsu.spring.pokemon.service.PokemonService;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final Logger logger = LoggerFactory.getLogger(PokemonServiceImpl.class);

    private PokemonRepository pokemonRepository;

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        logger.info("Created");
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found"));
        return mapToDto(pokemon);
    }

}
