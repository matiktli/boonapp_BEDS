package com.boon.boonapp.service.util;

import com.boon.boonapp.model.Location;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class LocationUtils {

    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Double EARTH_RADIUS = 6371e3;

    /**
     *  isInArea -> check if THIS loc is in area made by circle with radius provided and centre in provided point
     * @param xLat -> provided point latitude
     * @param xLng -> provided point longitude
     * @param distance -> radius of "circle" around provided point where we are checking if THIS loc is in;
     * @return is THIS loc in distance of '@distance' (KILOMETERS) from provided point
     */
    public static Predicate<Location> isInArea(Double xLat, Double xLng, Double distance) {
        return loc -> calculateDistanceBetweenPoints(loc.getLat(), loc.getLng(), xLat, xLng) <= distance;
    }

    public static Predicate<Location> isInArea(Location loc2, Double distance) {
        return loc -> calculateDistanceBetweenPoints(loc.getLat(), loc.getLng(), loc2.getLat(), loc2.getLng()) <= distance;
    }

    /**
     * Returns distance between two points on Earth sphere
     * @param latOne -> point one latitude
     * @param lngOne -> point one longitude
     * @param latTwo -> point two latitude
     * @param lngTwo -> point two longitude
     * @return distance between point one and two (KILOMETERS)
     */
    public static Double calculateDistanceBetweenPoints(Double latOne, Double lngOne, Double latTwo, Double lngTwo) {
        final Double latOneRad = Math.toRadians(latOne);
        final Double latTwoRad = Math.toRadians(latTwo);
        final Double deltaLatRad = Math.toRadians(latTwo - latOne);
        final Double deltaLngRad = Math.toRadians(lngTwo - lngOne);
        final Double a = Math.pow(Math.sin(deltaLatRad/2), 2) + Math.cos(latOneRad) * Math.cos(latTwoRad)
                * Math.pow(Math.sin(deltaLngRad/2), 2);
        final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (EARTH_RADIUS * c) / 1000; //return in KILOMETERS
    }

    public static List<Location> getTopSortByDistanceFromPoint(List<Location> locations, Double pointLat, Double pointLng, Double distance, Integer howMany) {
        if (pointLat != null && pointLng != null && distance != null) {
            locations = sortByDistanceFromPoint(locations, pointLat, pointLng, distance);
        }
        return locations.stream()
                .limit((long) (howMany != null && howMany > 0 ? howMany : DEFAULT_PAGE_SIZE))
                .collect(Collectors.toList());
    }

    public static List<Location> sortByDistanceFromPoint(List<Location> locations, double pointLat, double pointLng, double distance) {
        return locations.stream()
                .filter(loc -> calculateDistanceBetweenPoints(pointLat, pointLng, loc.getLat(), loc.getLng()) <= distance)
                .sorted( (loc1, loc2) -> {
                    Double locOneDistance = LocationUtils.calculateDistanceBetweenPoints(pointLat, pointLng, loc1.getLat(), loc1.getLng());
                    Double locTwoDistance = LocationUtils.calculateDistanceBetweenPoints(pointLat, pointLng, loc2.getLat(), loc2.getLng());
                    return locOneDistance.compareTo(locTwoDistance);
                })
                .collect(Collectors.toList());
    }
}
