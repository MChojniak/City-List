package com.mchojniak.citylist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

import static com.mchojniak.citylist.CityDtoMapper.mapToCity;
import static com.mchojniak.citylist.CityDtoMapper.mapToCityDto;
import static com.mchojniak.citylist.CityDtoMapper.mapToCityDtos;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
class CityService {
    private final CityRepository cityRepository;
    private static final int PAGE_SIZE = 5;

    List<CityDto> getCities(int page) {
        int pageNumber = Math.max(page, 0);
        return mapToCityDtos(cityRepository.findAllCities(PageRequest.of(pageNumber, PAGE_SIZE,
                Sort.by(Sort.Order.asc("name")))));
    }

    CityDto getCity(Long id) throws EntityNotFoundException {
        return mapToCityDto(
                cityRepository.findById(id)
                        .orElseThrow(() ->
                                new CityNotFoundException(format("City with id: %s not found", id))));
    }

    public Long saveCity(CityDto cityDto) {
        return cityRepository.save(mapToCity(cityDto)).getId();
    }

    public Long updateCity(Long id, CityDto cityDto) {
        log.info("updateCity for id: {} and city: {}", id, cityDto);
        if (StringUtils.isEmpty(cityDto.getName()) || StringUtils.isEmpty(cityDto.getPhotoSource())) {
            throw new IllegalArgumentException();
        }
        City cityToSave = cityRepository.findById(id)
                .orElse(City.builder()
                        .name(cityDto.getName())
                        .photoSource(cityDto.getPhotoSource())
                        .build());

        if (Objects.equals(cityToSave.getId(), id)) {
            cityToSave.setName(cityDto.getName());
            cityToSave.setPhotoSource(cityDto.getPhotoSource());
        }

        return cityRepository.save(cityToSave).getId();
    }

    public Integer getMaxPages() {
        return cityRepository.getAmountOfRows() / PAGE_SIZE;
    }

    public List<CityDto> getCityByName(String name) {
        return mapToCityDtos(cityRepository.findCityByName(name));
    }
}
