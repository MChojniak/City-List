package com.mchojniak.citylist;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
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

    CityPageDto getCities(int page) {
        int pageNumber = Math.max(page, 0);
        Page<City> cityPageList = cityRepository.findAll(
                PageRequest.of(pageNumber, PAGE_SIZE,
                        Sort.by(Sort.Order.asc("name"))));
        List<CityDto> cityDtos = mapToCityDtos(cityPageList.getContent());
        int totalPages = cityPageList.getTotalPages();

        return new CityPageDto(cityDtos, totalPages);
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
    
    public List<CityDto> getCityByName(String name) {
        return mapToCityDtos(cityRepository.findByName(name));
    }
}
