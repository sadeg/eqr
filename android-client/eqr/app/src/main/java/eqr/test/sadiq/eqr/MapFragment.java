package eqr.test.sadiq.eqr;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import eqr.test.sadiq.eqr.adapter.EventsAdapter;
import eqr.test.sadiq.eqr.model.Event;
import eqr.test.sadiq.eqr.rest.ApiClient;
import eqr.test.sadiq.eqr.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Belal on 1/23/2018.
 */

public class MapFragment extends Fragment {
    private static final String TAG = ActMain.class.getSimpleName();
    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // Set title bar
        ((ActMainMenu) getActivity()).setActionBarTitle(getString(R.string.title_map));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Event>> call = apiService.getLastEvents();

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
               //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);

                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
                updateEvents(getContext() ,googleMap);
                // For zooming automatically to the location of the marker
                LatLng camera = new LatLng(35.992075,49.664209);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(camera).zoom(4).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public static void updateEvents(final Context c, final GoogleMap mMap){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Event>> call = apiService.getLastEvents();
        call.enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>>call, Response<ArrayList<Event>> response) {
                ArrayList<Event> events = response.body();
                Log.e(TAG, events.toString());
                for (int i=0; i<events.size(); i++) {
                    // For dropping a marker at a point on the Map
                    LatLng event = new LatLng(events.get(i).getLat(), events.get(i).getLng());
                    //LatLng event = new LatLng(10, 20);
                    String datetime = events.get(i).getDateTime().substring(0, 18);
                    String mag = events.get(i).getMag().toString() + "  " + c.getString(R.string.mag_postfix);

                    int height = 80;
                    int width = 80;
                    BitmapDrawable mg=(BitmapDrawable)c.getResources().getDrawable(R.drawable.marker_green);
                    Bitmap b_mg=mg.getBitmap();
                    Bitmap green_marker = Bitmap.createScaledBitmap(b_mg, width, height, false);

                    BitmapDrawable my=(BitmapDrawable)c.getResources().getDrawable(R.drawable.marker_yellow);
                    Bitmap b_my=my.getBitmap();
                    Bitmap yellow_marker = Bitmap.createScaledBitmap(b_my, width, height, false);

                    BitmapDrawable mo=(BitmapDrawable)c.getResources().getDrawable(R.drawable.marker_green);
                    Bitmap b_mo=mo.getBitmap();
                    Bitmap orange_marker = Bitmap.createScaledBitmap(b_mo, width, height, false);

                    BitmapDrawable mr=(BitmapDrawable)c.getResources().getDrawable(R.drawable.marker_red);
                    Bitmap b_mr=mr.getBitmap();
                    Bitmap red_marker = Bitmap.createScaledBitmap(b_mr, width, height, false);

                    Double m;
                    m = events.get(i).getMag();
                    if(m < 3){
                        mMap.addMarker(new MarkerOptions().position(event).title(mag).snippet(datetime).icon(BitmapDescriptorFactory.fromBitmap(green_marker)));
                    }
                    else if(m < 5){
                        mMap.addMarker(new MarkerOptions().position(event).title(mag).snippet(datetime).icon(BitmapDescriptorFactory.fromBitmap(yellow_marker)));
                    }
                    else if(m < 7){
                        mMap.addMarker(new MarkerOptions().position(event).title(mag).snippet(datetime).icon(BitmapDescriptorFactory.fromBitmap(orange_marker)));
                    }
                    else {
                        mMap.addMarker(new MarkerOptions().position(event).title(mag).snippet(datetime).icon(BitmapDescriptorFactory.fromBitmap(red_marker)));
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }


}