package com.boon.boonapp.service.util;

import com.boon.boonapp.model.Location;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
public class LocationUtilsTest {

    SoftAssertions softly = new SoftAssertions();
    private static final Double ACCURACY = 0.3; //in KILOMETERS
    private static final Location TEST_POINT = Location.builder().lat(40.0).lng(-120.0).name("TEST_POINT").build();
    private List<Location> testLocations = populateTestLocations();

    @Before
    public void setup() {
        Collections.shuffle(testLocations);
    }

    @Test
    public void test_distancesBetweenTwoPoints_multiple() {
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(0.0).lng(0.0).build(),
                Location.builder().lat(0.0).lng(70.0).build(),
                7784.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(40.0).lng(30.0).build(),
                Location.builder().lat(80.0).lng(70.0).build(),
                4748.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(-70.0).lng(30.0).build(),
                Location.builder().lat(90.0).lng(-70.0).build(),
                17779.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(-70.0).lng(-30.0).build(),
                Location.builder().lat(-30.0).lng(-70.0).build(),
                5093.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(-70.0).lng(40.0).build(),
                Location.builder().lat(-30.0).lng(70.0).build(),
                4825.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(1.0).lng(165.0).build(),
                Location.builder().lat(-89.0).lng(-180.0).build(),
                10005.0);
        assertAndTestDistanceTwoPoints(
                Location.builder().lat(90.0).lng(180.0).build(),
                Location.builder().lat(-90.0).lng(-180.0).build(),
                20002.0);
    }

    @Test
    public void test_getAllSortByDistanceFromPoint() {
        List<Location> result = LocationUtils.getTopSortByDistanceFromPoint(testLocations, TEST_POINT.getLat(), TEST_POINT.getLng(), 0.0, null);
        softly.assertThat(result).isNotNull();
        softly.assertThat(result.size()).isEqualTo(testLocations.size());
        softly.assertThat(result.get(0).getName()).isEqualTo("6.0");
        softly.assertThat(result.get(2).getName()).isEqualTo("8265.0");
        softly.assertThat(result.get(4).getName()).isEqualTo("14446.0");
    }

    @Test
    public void test_getTopThreeSortByDistanceFromPoint() {
        List<Location> result = LocationUtils.getTopSortByDistanceFromPoint(testLocations, TEST_POINT.getLat(), TEST_POINT.getLng(), 0.0, 3);
        softly.assertThat(result).isNotNull();
        softly.assertThat(result.size()).isEqualTo(3);
        softly.assertThat(result.get(0).getName()).isEqualTo("6.0");
        softly.assertThat(result.get(1).getName()).isEqualTo("5557.0");
        softly.assertThat(result.get(2).getName()).isEqualTo("8265.0");
    }

    public void assertAndTestDistanceTwoPoints(Location locOne, Location locTwo, Double expected) {
        Double result = LocationUtils.calculateDistanceBetweenPoints(locOne.getLat(), locOne.getLng(), locTwo.getLat(), locTwo.getLng());
        softly.assertThat(result).isBetween(expected - ACCURACY, expected + ACCURACY);
    }

    public List<Location> populateTestLocations() {
        List<Location> result = new ArrayList<>();
        result.add(Location.builder().lat(-90.0000).lng(180.0000).name("14446.0").build());
        result.add(Location.builder().lat(89.0000).lng(-30.0000).name("5557.0").build());
        result.add(Location.builder().lat(45.0000).lng(130.0000).name("8265.0").build());
        result.add(Location.builder().lat(-75.0000).lng(-50.0000).name("13732.0").build());
        result.add(Location.builder().lat(40.05).lng(-120.0000).name("6.0").build());
        return result;
    }

}