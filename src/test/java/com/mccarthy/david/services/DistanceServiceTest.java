package com.mccarthy.david.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test the distance service.
 */
public class DistanceServiceTest {
    private static final double ACCEPTABLE_MARGIN_OF_ERROR = 0.005;
    private static final double NINETY_DEGREES_AS_RADIANS = 90.0 * (Math.PI / 180);
    private static final int DISTANCE_BETWEEN_POLE_AND_EQUATOR = 10000;
    private static final double NORTH_POLE_LATITUDE = 90.0;
    private static final double NORTH_POLE_LONGITUDE = 135.0;
    private static final double EQUATOR_POINT_LATITUDE = 0.0;
    private static final double EQUATOR_POINT_LONGITUDE = 0.0;

    protected DistanceService distanceService;

    @Before
    public void initialise() {
        distanceService = new DistanceService();
    }

    @Test
    public void testGetCentralAngle() {
        //Central angle is the angle at the center of the earth where 2 points on the surface are given with latitude and longitude.
        //We know the central angle on earth where one point is on the equator and the other is at the north pole, should be 90degrees.
        double centralAngle = distanceService.calculateCentralAngle(NORTH_POLE_LATITUDE, NORTH_POLE_LONGITUDE, EQUATOR_POINT_LATITUDE, EQUATOR_POINT_LONGITUDE);

        assertEquals("Central angle for north pole and point on the equator should be 90 degrees",
                NINETY_DEGREES_AS_RADIANS, centralAngle, 0);
    }

    @Test
    public void testGetDistance() {
        //Distance between 2 points on earth equals earth radius * central angle.
        //As already proven the central angle from north pole to a point on the equator is 90degrees.
        //We know the earth radius ( for our purpose ) is 6371 km.

        //We know that the distance between the poles and the equator is 10000km, by definition of a km - 1/10000th distance between pole and equator.
        double distance = distanceService.calculateDistance(NORTH_POLE_LATITUDE, NORTH_POLE_LONGITUDE, EQUATOR_POINT_LATITUDE, EQUATOR_POINT_LONGITUDE);
        // Due to the shape of the earth not being a perfect sphere, there is a margin of error in the calculation of 0.5%, due to assumption of a spherical earth.
        assertEquals("Distance between north pole and equator should be 10000km += 0.5%",
                DISTANCE_BETWEEN_POLE_AND_EQUATOR, distance, (DISTANCE_BETWEEN_POLE_AND_EQUATOR * ACCEPTABLE_MARGIN_OF_ERROR));
    }
}