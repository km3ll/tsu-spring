package pod.tsu.spring.pokemon.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pod.tsu.spring.pokemon.dto.AllPokemonDto;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.model.Pokemon;
import pod.tsu.spring.pokemon.repository.PokemonRepository;
import testkit.Generators;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@Tag("Unit")
@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @Mock
    private PokemonRepository mockRepository;

    @InjectMocks
    private PokemonServiceImpl service;

    @Test
    public void service_create_returnsPokemonDto() {

        // Given
        Pokemon pokemon = Generators.pokemon();
        when(mockRepository.save(any(Pokemon.class))).thenReturn(pokemon);

        // When
        PokemonDto requestDto = Generators.pokemonDto(pokemon);
        PokemonDto responseDto = service.createPokemon(requestDto);

        // Then
        verify(mockRepository, times(1)).save(any());
        Assertions.assertThat(responseDto).isNotNull();

    }

    @Test
    public void service_getAll_returnsMultiplePokemonDtos() {

        // Given
        List<Pokemon> pokemons = List.of(Generators.pokemon(), Generators.pokemon());
        when(mockRepository.findAll()).thenReturn(pokemons);

        // When
        AllPokemonDto responseDto = service.getAllPokemon();

        // Then
        verify(mockRepository, times(1)).findAll();
        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getTotalElements()).isEqualTo(2);

    }

    @Test
    public void service_getById_returnsPokemonDto() {

        // Given
        Pokemon pokemon = Generators.pokemon();
        when(mockRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));

        // When
        PokemonDto responseDto = service.getPokemonById(pokemon.getId());

        // Then
        verify(mockRepository, times(1)).findById(pokemon.getId());
        Assertions.assertThat(responseDto).isNotNull();
        Assertions.assertThat(responseDto.getName()).isEqualTo(pokemon.getName());
        Assertions.assertThat(responseDto.getType()).isEqualTo(pokemon.getType());

    }

    @Test
    public void service_update_returnsPokemonDto() {

        // Given
        Pokemon pokemon = Generators.pokemon();
        when(mockRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));
        when(mockRepository.save(any())).thenReturn(pokemon);

        // When
        PokemonDto requestDto = Generators.pokemonDto(pokemon);
        PokemonDto responseDto = service.updatePokemon(requestDto, pokemon.getId());

        // Then
        verify(mockRepository, times(1)).findById(any());
        verify(mockRepository, times(1)).save(any());
        Assertions.assertThat(responseDto).isNotNull();

    }

    @Test
    public void service_delete_returnsVoid() {

        // Given
        Pokemon pokemon = Generators.pokemon();
        when(mockRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));
        doNothing().when(mockRepository).delete(any());

        // When
        service.deletePokemonById(pokemon.getId());

        // Then
        verify(mockRepository, times(1)).findById(any());
        verify(mockRepository, times(1)).delete(any());

    }

}