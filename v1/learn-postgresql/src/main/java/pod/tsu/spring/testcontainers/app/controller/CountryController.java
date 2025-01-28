package pod.tsu.spring.testcontainers.app.controller;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pod.tsu.spring.testcontainers.app.dto.ResponseDto;
import pod.tsu.spring.testcontainers.model.Country;
import pod.tsu.spring.testcontainers.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostMapping("country")
    public ResponseEntity<ResponseDto> save(@RequestBody Country country) {
        countryRepository.save(country);
        return ResponseEntity.ok(new ResponseDto("Country created with id: " + country.getId()));
    }

    @PutMapping("country/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody Country country) {
        if (!id.equals(country.getId())) {
            return ResponseEntity.badRequest().body(new ResponseDto("Country id mismatch"));
        }
        if (!countryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseDto("Country not found"));
        }
        countryRepository.save(country);
        return ResponseEntity.ok(new ResponseDto("Country updated"));
    }

    @GetMapping("country")
    public ResponseEntity<List<Country>> findByAll() {
        var countries = IterableUtils.toList(countryRepository.findAll());
        return ResponseEntity.ok(countries);
    }

    @GetMapping("country/{id}")
    public ResponseEntity<Country> findById(@PathVariable("id") Long id) {
        return countryRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("country/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        if (!countryRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseDto("Country not found"));
        }
        countryRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseDto("Country deleted"));
    }

}