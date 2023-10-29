package pod.tsu.spring.pokemon.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import pod.tsu.spring.pokemon.dto.PokemonDto;
import pod.tsu.spring.pokemon.service.PokemonService;
import testkit.Generators;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("UnitTest")
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = {PokemonController.class})
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService mockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void controller_postCreatePokemon_returnsCreated() throws Exception {

        // Given
        PokemonDto pokemonDto = Generators.pokemonDto();
        given(mockService.createPokemon(any())).willReturn(pokemonDto);

        String url = "/api/pokemon/create";
        String body = objectMapper.writeValueAsString(pokemonDto);
        RequestBuilder requestBuilder = post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(body);

        // When
        ResultActions response = mockMvc.perform(requestBuilder);

        // Then
        response.andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", is(pokemonDto.getName())))
            .andExpect(jsonPath("$.type", is(pokemonDto.getType())))
            .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void controller_getPokemonDetail_returnsOk() throws Exception {

        // Given
        PokemonDto pokemonDto = Generators.pokemonDto();
        given(mockService.getPokemonById(pokemonDto.getId())).willReturn(pokemonDto);

        String url = String.format("/api/pokemon/%s", pokemonDto.getId());
        RequestBuilder requestBuilder = get(url)
            .accept(MediaType.APPLICATION_JSON);

        // When
        ResultActions response = mockMvc.perform(requestBuilder);

        // Then
        response.andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(pokemonDto.getName())))
            .andExpect(jsonPath("$.type", is(pokemonDto.getType())))
            .andDo(MockMvcResultHandlers.print());

    }

}