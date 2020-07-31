package com.mccarthy.david.services;

/**
 * Service to calulate the great circle distance between two points, given as latitude and longitude.
 */
public class DistanceService {
    protected static final double EARTH_RADIUS = 6371.0;

    /**
     * Calculate the distance between 2 points on earth, as represented by their latitude and longitude.
     *
     * @param latitude1  Latitude of the first point.
     * @param longitude1 Longitude of the first point.
     * @param latitude2  Latitude of the second point.
     * @param longitude2 Longitude of the second point.
     * @return Double distance in KM from first to second point.
     */
    public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double centralAngle = calculateCentralAngle(latitude1, longitude1, latitude2, longitude2);

        return centralAngle * EARTH_RADIUS;
    }

    /**
     * Calculate the central angle of the given latitudes and longitude points on a sphere.
     *
     * @param latitude1  Latitude of the first point.
     * @param longitude1 Longitude of the first point.
     * @param latitude2  Latitude of the second point.
     * @param longitude2 Longitude of the second point.
     * @return Central angle at the center of the sphere formed from the intersecting lines from the 2 given points.
     */
    protected double calculateCentralAngle(double latitude1, double longitude1, double latitude2, double longitude2) {
        double latitude1Radians = degreesToRadians(latitude1);
        double longitude1Radians = degreesToRadians(longitude1);
        double latitude2Radians = degreesToRadians(latitude2);
        double longitude2Radians = degreesToRadians(longitude2);

        return Math.acos(
                (Math.sin(latitude1Radians) * Math.sin(latitude2Radians)) +
                        (Math.cos(latitude1Radians) * Math.cos(latitude2Radians) *
                                Math.cos(Math.abs(longitude1Radians - longitude2Radians))));
    }

    /**
     * Convert degrees to radians.
     *
     * @param degrees Degrees.
     * @return Radians.
     */
    protected double degreesToRadians(double degrees) {
        return (Math.PI / 180) * degrees;
    }
}
