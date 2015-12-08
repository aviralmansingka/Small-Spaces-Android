package com.example.aviral.ss;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
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
    private ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.map_fragment, container,
                false);
        tvName = (TextView) v.findViewById(R.id.tvName);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
        imageView = (ImageView) v.findViewById(R.id.imageView);
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
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                int id = Integer.parseInt(marker.getTitle());
                try {
                    tvName.setText(obj.getJSONArray("collection").getJSONObject(id).getString("artist"));
                    tvDesc.setText(obj.getJSONArray("collection").getJSONObject(id).getString("desc"));
                    imageView.setImageDrawable(getDrawableFromMarker(obj.getJSONArray("collection").getJSONObject(id).getString("id")));
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

    private Drawable getDrawableFromMarker(String string) {
        int id = Integer.parseInt(string);
        switch(id){
            case 1:
                return getResources().getDrawable(R.drawable.n01);
            case 2:
                return getResources().getDrawable(R.drawable.n02);
            case 3:
                return getResources().getDrawable(R.drawable.n03);
            case 4:
                return getResources().getDrawable(R.drawable.n04);
            case 5:
                return getResources().getDrawable(R.drawable.n05);
            case 6:
                return getResources().getDrawable(R.drawable.n06);
            case 7:
                return getResources().getDrawable(R.drawable.n07);
            case 8:
                return getResources().getDrawable(R.drawable.n08);
            case 9:
                return getResources().getDrawable(R.drawable.n09);
            case 10:
                return getResources().getDrawable(R.drawable.n10);
            case 11:
                return getResources().getDrawable(R.drawable.n11);
            case 12:
                return getResources().getDrawable(R.drawable.n12);
            case 13:
                return getResources().getDrawable(R.drawable.n13);
            case 14:
                return getResources().getDrawable(R.drawable.n14);
            case 15:
                return getResources().getDrawable(R.drawable.n15);
            case 16:
                return getResources().getDrawable(R.drawable.n16);
            case 17:
                return getResources().getDrawable(R.drawable.n17);
            case 18:
                return getResources().getDrawable(R.drawable.n18);
            case 19:
                return getResources().getDrawable(R.drawable.n19);
            case 20:
                return getResources().getDrawable(R.drawable.n20);
            case 21:
                return getResources().getDrawable(R.drawable.n21);
            case 22:
                return getResources().getDrawable(R.drawable.n22);
            case 23:
                return getResources().getDrawable(R.drawable.n23);
            case 24:
                return getResources().getDrawable(R.drawable.n24);
            case 25:
                return getResources().getDrawable(R.drawable.n25);
            case 26:
                return getResources().getDrawable(R.drawable.n26);
            case 27:
                return getResources().getDrawable(R.drawable.n27);
            case 28:
                return getResources().getDrawable(R.drawable.n28);
            case 29:
                return getResources().getDrawable(R.drawable.n29);
            case 30:
                return getResources().getDrawable(R.drawable.n30);
            case 31:
                return getResources().getDrawable(R.drawable.n31);
            case 32:
                return getResources().getDrawable(R.drawable.n32);
            case 33:
                return getResources().getDrawable(R.drawable.n33);
            case 34:
                return getResources().getDrawable(R.drawable.n34);
            case 35:
                return getResources().getDrawable(R.drawable.n35);
            case 36:
                return getResources().getDrawable(R.drawable.n36);
            case 37:
                return getResources().getDrawable(R.drawable.n37);
            case 38:
                return getResources().getDrawable(R.drawable.n38);
            case 39:
                return getResources().getDrawable(R.drawable.n39);
            case 40:
                return getResources().getDrawable(R.drawable.n40);
            case 41:
                return getResources().getDrawable(R.drawable.n41);
            case 42:
                return getResources().getDrawable(R.drawable.n42);
            case 43:
                return getResources().getDrawable(R.drawable.n43);
            case 44:
                return getResources().getDrawable(R.drawable.n44);
            case 45:
                return getResources().getDrawable(R.drawable.n45);
            case 46:
                return getResources().getDrawable(R.drawable.n46);
            case 47:
                return getResources().getDrawable(R.drawable.n47);
            case 48:
                return getResources().getDrawable(R.drawable.n48);
            case 49:
                return getResources().getDrawable(R.drawable.n49);
            case 50:
                return getResources().getDrawable(R.drawable.n50);
            case 51:
                return getResources().getDrawable(R.drawable.n51);
            case 52:
                return getResources().getDrawable(R.drawable.n52);
            case 53:
                return getResources().getDrawable(R.drawable.n53);
            case 54:
                return getResources().getDrawable(R.drawable.n54);
            case 55:
                return getResources().getDrawable(R.drawable.n55);
            case 56:
                return getResources().getDrawable(R.drawable.n56);
            case 57:
                return getResources().getDrawable(R.drawable.n57);
            case 58:
                return getResources().getDrawable(R.drawable.n58);
            case 59:
                return getResources().getDrawable(R.drawable.n59);
            case 60:
                return getResources().getDrawable(R.drawable.n60);
            case 61:
                return getResources().getDrawable(R.drawable.n61);
            case 62:
                return getResources().getDrawable(R.drawable.n62);
            case 63:
                return getResources().getDrawable(R.drawable.n63);
            case 64:
                return getResources().getDrawable(R.drawable.n64);
            case 65:
                return getResources().getDrawable(R.drawable.n65);
            case 66:
                return getResources().getDrawable(R.drawable.n66);
            case 67:
                return getResources().getDrawable(R.drawable.n67);
            case 68:
                return getResources().getDrawable(R.drawable.n68);
            case 69:
                return getResources().getDrawable(R.drawable.n69);
        }
        return null;
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