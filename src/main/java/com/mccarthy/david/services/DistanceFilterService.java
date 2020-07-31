package com.mccarthy.david.services;

import com.mccarthy.david.model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to filter customers based on theire distance from the office.
 */
public class DistanceFilterService {
    private static final double OFFICE_LATITUDE = 53.339428;
    private static final double OFFICE_LONGITUDE = -6.257664;

    protected final DistanceService distanceService;

    /**
     * Constructor.
     *
     * @param distanceService Service to calculate the distance from the office for given coordinates.
     */
    public DistanceFilterService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    /**
     * Filter the given list of customers and return the list of customers with {@code maxDistance} of the office.
     *
     * @param customers   List of customers.
     * @param maxDistance Maximum distance allowed.
     * @return Filtered list of customers within {@code maxDistance} of the office.
     */
    public List<Customer> filterCustomersByDistance(List<Customer> customers, double maxDistance) {
        ArrayList<Customer> filteredCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            double distanceToOffice = distanceService.calculateDistance(customer.getLatitude(), customer.getLongitude(), OFFICE_LATITUDE, OFFICE_LONGITUDE);
            if (maxDistance > distanceToOffice) {
                filteredCustomers.add(customer);
            }
        }

        return filteredCustomers;
    }
}
