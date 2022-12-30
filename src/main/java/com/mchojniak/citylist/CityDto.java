package com.mchojniak.citylist;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class CityDto {
    private Long id;
    private String name;
    private String photoSource;
}
