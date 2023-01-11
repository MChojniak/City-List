package com.mchojniak.citylist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
class CityRestController {
    private final CityService cityService;
    
    @GetMapping("/")
    public ResponseEntity<CityPageDto> getCities(@RequestParam(required = false) int page) {
        return new ResponseEntity<>(cityService.getCities(page), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getCity(@PathVariable Long id) {
        return new ResponseEntity<>(cityService.getCity(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CityDto>> getCityByName(@PathVariable String name) {
        return new ResponseEntity<>(cityService.getCityByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> saveCity(@RequestBody CityDto cityDto) {
        return new ResponseEntity<>(cityService.saveCity(cityDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editCity(@PathVariable Long id, @RequestBody CityDto cityDto) {
        try {
            return editCityResponse(id, cityDto);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    private ResponseEntity<Void> editCityResponse(Long id, CityDto cityDto) {
        Long updatedId = this.cityService.updateCity(id, cityDto);
        if (Objects.equals(updatedId, id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.created(URI.create("/cities/" + updatedId)).build();
        }
    }
}
