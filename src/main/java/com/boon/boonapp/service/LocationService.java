package com.boon.boonapp.service;

import com.boon.boonapp.dao.LocationRepository;
import com.boon.boonapp.model.Location;
import com.boon.boonapp.service.util.LocationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Location> getLocationById(Long locationId) {
        return locationRepository.findById(locationId);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Location location) {
        return save(location);
    }

    public void deleteLocationById(Long locationId) {
        locationRepository.deleteById(locationId);
    }

    /**
     * Return (if: [lat], [lng], [distance] provided) sorted list of top closest [pageSize or {DEFAULT_PAGE_SIZE}}] locations
     * Return (if: [lat], [lng], [distance] NOT provided) sorted list of [pageSize or {DEFAULT_PAGE_SIZE}] locations
     * @param pageSize - to somehow deal without pageable
     * @param lat - lat of provided point (for example user's lat if searching for are in his location)
     * @param lng - lng of provided point (for example user's lng if searching for are in his location)
     * @param distance - radius of circle around provided point to search in (KILOMETERS)
     * @return List of locations in area of radius 'distance' around point provided sorted from the closest to the farthest
     */
    public List<Location> findLocationsInAreaSorted(Integer pageSize, Double lat, Double lng, Double distance) {
        return LocationUtils.getTopSortByDistanceFromPoint(locationRepository.findAll(), lat, lng, distance, pageSize);
    }


}
