package com.example.aviral.ss;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment that launches other parts of the demo application.
 */
public class MapFragment extends Fragment {

    String s = "{\"collection\": [ " +
            "{\"artist\":\"Zach Medler\",\"desc\":\"DESCRIPTION GOES HERE\", \"geo_lat\":\"40.4189319\", \"geo_long\":\"-86.89487767\", \"id\":\"1\", \"image_location\":\"img_01.png\"}," +
            "" +
            "{\"artist\":\"Aaron Bumgardner\",\"desc\":\"DESCRIPTION GOES HERE\", \"geo_lat\":\"40.41987803\", \"geo_long\":\"-86.89433289\", \"id\":\"2\", \"image_location\":\"img_02.png\"}," +
            "" +
            "{\"artist\":\"Lisa Wicka\",\"desc\":\"DESCRIPTION GOES HERE\", \"geo_lat\":\"40.42007323\", \"geo_long\":\"-86.89256559\", \"id\":\"3\", \"image_location\":\"img_03.png\"}," +
            "" +
            "{\"artist\":\"Paul Meadows\",\"desc\":\"DESCRIPTION GOES HERE\", \"geo_lat\":\"40.4189319\", \"geo_long\":\"-86.89487767\", \"id\":\"4\", \"image_location\":\"img_04.png\"}] }";
    MapView mMapView;
    protected GoogleMap googleMap;
    //private ListView mDrawerList;
    private RelativeLayout mRLDrawer;
    private DrawerLayout mDrawerLayout;
    private Object mActivityTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayAdapter<String> mAdapter;
    private JSONObject obj = null;
    private TextView tvName;
    private TextView tvDesc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.map_fragment, container,
                false);
        tvName = (TextView) v.findViewById(R.id.tvName);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        // latitude and longitude
        if(googleMap!=null)
        addMarkers();
        else{
            Log.e("SS","Google map is null");
        }
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int id = Integer.parseInt(marker.getTitle());
                try {
                    tvName.setText(obj.getJSONArray("collection").getJSONObject(id).getString("artist"));
                    tvDesc.setText(obj.getJSONArray("collection").getJSONObject(id).getString("desc"));
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("MarkerClick", "Issue with marker JSON");
                }
                return true;
            }
        });
        Button b =(Button) v.findViewById(R.id.bToggle);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //mDrawerList = (ListView)v.findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)v.findViewById(R.id.drawer_layout);
        mRLDrawer = (RelativeLayout) v.findViewById(R.id.rlDrawer);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mActivityTitle = getActivity().getTitle().toString();

        addDrawerItems();
        setupDrawer();

        return v;
    }


    private void setupDrawer() {


        //mDrawerToggle.setDrawerIndicatorEnabled(true);

    }

    private void addDrawerItems() {
//        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
//        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, osArray);
//        mDrawerList.setAdapter(mAdapter);
//
//        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "Time for an upgrade!", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void addMarkers() {

        //TODO add Marker imports here

        try {
            obj = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundMarker","Error in writing JSON");
        }
        Log.d("BackgroundMarker", s);
        try {
            JSONArray arr = obj.getJSONArray("collection");
            for(int i=0; i<arr.length();i++){
                JSONObject object = arr.getJSONObject(i);
                double lat = Double.parseDouble(object.getString("geo_lat"));
                double lng = Double.parseDouble(object.getString("geo_long"));
                MarkerOptions a = new MarkerOptions()
                        .position(new LatLng(lat,lng)).title(object.getString("id"));
                Marker m = googleMap.addMarker(a);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BackgroundMarker", "Error in getting JSONArray");
        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(40.42, -86.89)).zoom(14).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
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

}