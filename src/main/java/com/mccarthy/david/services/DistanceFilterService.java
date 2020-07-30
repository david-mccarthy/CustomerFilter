package com.mccarthy.david.services;

import com.mccarthy.david.model.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DistanceFilterService {
    private static final double INTERCOM_OFFICE_LATITUDE = 53.339428;
    private static final double INTERCOM_OFFICE_LONGITUDE = -6.257664;

    protected final DistanceService distanceService;

    public DistanceFilterService(DistanceService distanceService) {
        this.distanceService = distanceService;
    }

    public List<Customer> filterCustomersByDistance(List<Customer> customers, double maxDistance) {
        ArrayList<Customer> filteredCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            double distanceToOffice = distanceService.calculateDistance(customer.getLatitude(), customer.getLongitude(), INTERCOM_OFFICE_LATITUDE, INTERCOM_OFFICE_LONGITUDE);
            //get distance from this customer to the latitude and longitude.
            if (maxDistance > distanceToOffice) {
                filteredCustomers.add(customer);
            }
        }

        return filteredCustomers;
    }
}
