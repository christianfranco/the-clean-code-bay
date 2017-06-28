package com.github.christianfranco.geomatch.helper;

import com.github.christianfranco.geomatch.entities.Point;

import javax.annotation.Nonnull;

/**
 * Based on GeoDataSource.com (C) All Rights Reserved 2015
 */
final class DistanceCalculator {

    private static final double KM = 1.609344;
    private static final double MILES = 1.1515;
    private static final int BASE = 60;
    private static final double DEGREES_D = 180.0;
    private static final int DEGREES_I = 180;

    private DistanceCalculator() {
    }

    public static double distance(@Nonnull final Point point1, @Nonnull final Point point2) {
        double theta = point1.getLongitude() - point2.getLongitude();
        double dist = Math.sin(deg2rad(point1.getLatitude())) *
                Math.sin(deg2rad(point2.getLatitude())) +
                Math.cos(deg2rad(point1.getLatitude())) *
                        Math.cos(deg2rad(point2.getLatitude())) *
                        Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * BASE * MILES;
        dist = dist * KM;

        return (dist);
    }

    /*
     * This function converts decimal degrees to radians
     */
    private static double deg2rad(double deg) {
        return (deg * Math.PI / DEGREES_D);
    }

    /*
     * This function converts radians to decimal degrees
     */
    private static double rad2deg(double rad) {
        return (rad * DEGREES_I / Math.PI);
    }
}
