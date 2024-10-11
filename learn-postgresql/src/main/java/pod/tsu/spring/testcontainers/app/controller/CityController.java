package pod.tsu.spring.testcontainers.app.controller;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pod.tsu.spring.testcontainers.app.dto.ResponseDto;
import pod.tsu.spring.testcontainers.model.City;
import pod.tsu.spring.testcontainers.repository.CityRepository;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostMapping("city")
    public ResponseEntity<ResponseDto> save(@RequestBody City city) {
        cityRepository.save(city);
        return ResponseEntity.ok(new ResponseDto("City created with id: " + city.getId()));
    }

    @PutMapping("city/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id, @RequestBody City city) {
        if (!id.equals(city.getId())) {
            return ResponseEntity.badRequest().body(new ResponseDto("City id mismatch"));
        }
        if (!cityRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseDto("City not found"));
        }
        cityRepository.save(city);
        return ResponseEntity.ok(new ResponseDto("City updated"));
    }

    @GetMapping("city")
    public ResponseEntity<List<City>> findByAll() {
        var cities = IterableUtils.toList(cityRepository.findAll());
        return ResponseEntity.ok(cities);
    }

    @GetMapping("city/{id}")
    public ResponseEntity<City> findById(@PathVariable("id") Long id) {
        return cityRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("city/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable("id") Long id) {
        if (!cityRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new ResponseDto("City not found"));
        }
        cityRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseDto("City deleted"));
    }

}