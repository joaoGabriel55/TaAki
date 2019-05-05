package mobile.varejeira.com.taaki;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private List<HashMap<Double, Double>> listConjuntoLocalizacao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_categorias);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_filter_list_black_24dp));
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.esporte) {
                    if (item.isChecked()) {
                        preencheMarkers(1, true);
                        item.setChecked(false);
                    } else {
                        preencheMarkers(1, false);
                        item.setChecked(true);
                    }
                } else if (item.getItemId() == R.id.vestuario) {
                    if (item.isChecked()) {
                        preencheMarkers(2, true);
                        item.setChecked(false);
                    } else {
                        preencheMarkers(2, false);
                        item.setChecked(true);
                    }
                } else {
                    if (item.isChecked()) {
                        preencheMarkers(3, true);
                        item.setChecked(false);
                    } else {
                        preencheMarkers(3, false);
                        item.setChecked(true);
                    }
                }
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.clear();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_categorias, menu);
        return true;
    }

    private List<HashMap<Double, Double>> preencheOrRemoveListConjMarkers(int typeRetail, boolean isRemove) {
        if (!isRemove) {
            if (typeRetail == 1) {
                HashMap<Double, Double> getLatAndLong1 = new HashMap<>();
                getLatAndLong1.put(-5.824184, -35.210699);
                getLatAndLong1.put(-5.822367, -35.211906);
                getLatAndLong1.put(-5.817654, -35.208795);
                listConjuntoLocalizacao.add(getLatAndLong1);
            }
            if (typeRetail == 2) {
                HashMap<Double, Double> getLatAndLong2 = new HashMap<>();
                getLatAndLong2.put(-5.822226, -35.202341);
                getLatAndLong2.put(-5.812918, -35.212598);
                getLatAndLong2.put(-5.818255, -35.228649);
                listConjuntoLocalizacao.add(getLatAndLong2);
            }
            if (typeRetail == 3) {
                HashMap<Double, Double> getLatAndLong3 = new HashMap<>();
                getLatAndLong3.put(-5.835930, -35.214100);
                getLatAndLong3.put(-5.836229, -35.220409);
                getLatAndLong3.put(-5.838662, -35.211225);
                listConjuntoLocalizacao.add(getLatAndLong3);
            }
        } else {
            if (typeRetail == 1) {
                listConjuntoLocalizacao.remove(0);
            }
            if (typeRetail == 2) {
                listConjuntoLocalizacao.remove(1);
            }
        }
        return listConjuntoLocalizacao;
    }

    private void preencheMarkers(int typeRetail, boolean isRemove) {
        List<HashMap<Double, Double>> listConjuntoLocalizacao;

        listConjuntoLocalizacao = preencheOrRemoveListConjMarkers(typeRetail, isRemove);

        for (int i = 0; i < listConjuntoLocalizacao.size(); i++) {
            for (Map.Entry<Double, Double> entry : listConjuntoLocalizacao.get(i).entrySet()) {
                Double latitude = entry.getKey();
                Double longitude = entry.getValue();

                LatLng latLng = new LatLng(latitude, longitude);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                switch (i) {
                    case 0:
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        mMap.addMarker(markerOptions);
                        break;
                    case 1:
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        mMap.addMarker(markerOptions);
                        break;
                    case 2:
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                        mMap.addMarker(markerOptions);
                        break;
                }


            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }
}
