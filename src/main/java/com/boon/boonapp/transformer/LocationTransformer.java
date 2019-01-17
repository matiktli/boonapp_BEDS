package com.boon.boonapp.transformer;

import com.boon.boonapp.domain.LocationDTO;
import com.boon.boonapp.model.Location;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public LocationDTO toDto(Location source) {
        LocationDTO result = modelMapper.map(source, LocationDTO.class);
        return result;
    }

    public Location toEntity(LocationDTO source) {
        Location result = modelMapper.map(source, Location.class);
        return result;
    }
}
