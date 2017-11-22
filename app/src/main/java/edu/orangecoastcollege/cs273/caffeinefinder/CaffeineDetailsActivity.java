package edu.orangecoastcollege.cs273.caffeinefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Location mLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);
            mLocation = getIntent().getParcelableExtra("location");
        ((TextView) findViewById(R.id.nameTextView)).setText(mLocation.getName());
        ((TextView) findViewById(R.id.addressTextView)).setText(mLocation.getAddress());
        ((TextView) findViewById(R.id.phoneTextView)).setText(mLocation.getPhone());
        ((TextView) findViewById(R.id.positionTextView)).setText(
                Double.toString(mLocation.getLatitude()) + " " + mLocation.getLongitude());

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.caffeineDetailsMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng pos = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());

        mMap.addMarker(new MarkerOptions()
                .title(mLocation.getName())
                .position(pos)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder()
                .target(pos)
                .zoom(15.0f)
                .build()));
    }
}
