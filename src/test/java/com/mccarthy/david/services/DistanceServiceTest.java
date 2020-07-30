package com.mccarthy.david.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceServiceTest {
    private static final double ACCEPTABLE_MARGIN_OF_ERROR = 0.005;
    protected DistanceService distanceService;

    @Before
    public void initialise() {
        distanceService = new DistanceService();
    }

    @Test
    public void testGetCentralAngle() {
        double centralAngle = distanceService.calculateCentralAngle(90.0, 135.0, 0.0, 0.0);
        //Central angle is calculated in radians.
        //The central angle should be 90 degrees, or (90 * (pi /180)) radians
        double ninetyDegreesAsRadians = 90.0 * (Math.PI / 180);
        assertEquals(ninetyDegreesAsRadians, centralAngle, 0);
    }

    @Test
    public void testGetDistance() {
        //Distance between 2 points on the sphere equals earth radius * central angle.
        //As already tested the central angle is 90degrees (returned as radians).
        //To calculate the distance, multiply the radians by the earth radius, or 6371.
        //Distance between the poles and the equator is 10000km, by definition of km - 1/10000th distance between pole and equator.
        double distance = distanceService.calculateDistance(90.0, 135.0, 0.0, 0.0);
        // As per definition, there is a margin of error in the calculation of 0.5%, due to assumption of a spherical earth.
        assertEquals(10000, distance, (10000 * ACCEPTABLE_MARGIN_OF_ERROR));
    }
}