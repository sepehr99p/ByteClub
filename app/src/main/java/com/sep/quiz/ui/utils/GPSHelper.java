package com.sep.quiz.ui.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.util.List;
import java.util.Locale;

public class GPSHelper {

    private final LocationManager locationManager;
    private Location location;
    private double latitude = 0;
    private double longitude = 0;

    public GPSHelper(Context context) {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }

    public void getMyLocation() {
        List<String> providers = locationManager.getProviders(true);

        Location l = null;
        for (int i = 0; i < providers.size(); i++) {
            l = locationManager.getLastKnownLocation(providers.get(i));
            if (l != null)
                break;
        }
        if (l != null) {
            latitude = l.getLatitude();
            longitude = l.getLongitude();
        }
    }

    public boolean isGPSenabled() {
        boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        boolean isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        return (isGPSEnabled || isNetworkEnabled);
    }


    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }

    public String cityName(Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            return addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            return "";
        }

    }

}
