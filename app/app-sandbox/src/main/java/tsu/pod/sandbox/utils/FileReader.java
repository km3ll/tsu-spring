package tsu.pod.sandbox.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileReader {

	public List<String[]> readCsvFile(String path) {
		ClassPathResource resource = new ClassPathResource(path);
		try {

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));

			List<String[]> content = reader.lines().skip(1).map(line -> line.split(",")).toList();
			log.info("Read {} lines from CSV file: {}", content.size(), path);

			reader.close();
			return content;

		}
		catch (Exception e) {
			log.error("Cannot read CSV file: {}", path, e);
			return new ArrayList<>();
		}
	}

}
