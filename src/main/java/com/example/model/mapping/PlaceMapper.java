package com.example.model.mapping;

import com.example.model.dto.PlaceDTO;
import com.example.model.response.geosearch.GeoSearch;
import com.example.service.CountryService;
import jakarta.inject.Inject;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PlaceMapper {

    @Inject
    CountryService countryService;

    @AfterMapping
    protected void convertCountryIsoName(GeoSearch place, @MappingTarget PlaceDTO placeDTO) {
        placeDTO.setCountry(place.country() != null ?
                countryService.getCountryName(place.country()) : "NOT SPECIFIED");
    }

    @Mapping(target = "latitude", source = "lat")
    @Mapping(target = "longitude", source = "lon")
    public abstract PlaceDTO toPlaceDto(GeoSearch place);

    public abstract List<PlaceDTO> toPlaceDtoList(List<GeoSearch> places);


}
