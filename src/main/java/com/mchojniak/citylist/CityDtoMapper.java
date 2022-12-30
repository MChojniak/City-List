package com.mchojniak.citylist;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CityDtoMapper {
    static List<CityDto> mapToCityDtos(List<City> cities) {
        return cities
                .stream()
                .map(CityDtoMapper::mapToCityDto)
                .collect(Collectors.toList());
    }

    static CityDto mapToCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .photoSource(city.getPhotoSource())
                .build();
    }

    static City mapToCity(CityDto cityDto) {
        return City.builder()
                .id(null)
                .name(cityDto.getName())
                .photoSource(cityDto.getPhotoSource())
                .build();
    }
}
