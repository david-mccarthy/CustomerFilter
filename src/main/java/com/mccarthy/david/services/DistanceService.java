package com.mccarthy.david.services;

public class DistanceService {
    protected static final double EARTH_RADIUS = 6371.0;

    public double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
        double centralAngle = calculateCentralAngle(latitude, longitude, latitude2, longitude2);

        return centralAngle * EARTH_RADIUS;
    }

    /**
     * Calculate the central angle of the given latitudes and longitude points on a sphere.
     *
     * @param latitude
     * @param longitude
     * @param latitude2
     * @param longitude2
     * @return
     */
    protected double calculateCentralAngle(double latitude, double longitude, double latitude2, double longitude2) {
        double latitudeRadians = degreesToRadians(latitude);
        double longitudeRadians = degreesToRadians(longitude);
        double latitude2Radians = degreesToRadians(latitude2);
        double longitude2Radians = degreesToRadians(longitude2);

        return Math.acos(
                (Math.sin(latitudeRadians) * Math.sin(latitude2Radians)) +
                        (Math.cos(latitudeRadians) * Math.cos(latitude2Radians) *
                                Math.cos(Math.abs(longitudeRadians - longitude2Radians))));
    }

    protected double degreesToRadians(double degrees) {
        return (Math.PI / 180) * degrees;
    }
}
