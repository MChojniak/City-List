package com.mchojniak.citylist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityPageDto {
    private List<CityDto> cityDtoList = Collections.emptyList();
    private int totalPages = 0;
}
