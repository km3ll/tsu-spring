package testkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pod.tsu.spring.pokemon.model.Pokemon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileUtils {

    private static final String COMMA_SEPARATOR = ",";
    private static final String HEADER_PREFIX = "#";
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static List<String> readLines(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            result.addAll(lines.toList()) ;
        } catch (IOException ex) {
            logger.warn("Error reading lines from file '{}'", fileName, ex);
        }
        return result;
    }

    public static Optional<Pokemon> buildPokemon(String csvLine) {
        String[] fields = csvLine.split(COMMA_SEPARATOR);
        try {
            Pokemon pokemon = Pokemon.builder()
                .id(Integer.parseInt(fields[0]))
                .name(fields[1])
                .type(fields[2])
                .build();
            return Optional.of(pokemon);
        } catch (NumberFormatException ex) {
            logger.warn("Error reading building pokemon from CSV line '{}'", csvLine, ex);
            return Optional.empty();
        }
    }

    public static List<Pokemon> readPokemons(String csvFileName) {
        return readLines(csvFileName).stream()
            .filter(line -> !line.startsWith(HEADER_PREFIX))
            .map(FileUtils::buildPokemon)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
