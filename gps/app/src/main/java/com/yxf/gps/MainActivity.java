package com.yxf.gps;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager;
        String serviceName= Context.LOCATION_SERVICE;
        locationManager=(LocationManager)getSystemService(serviceName);
        Criteria criteria=new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(criteria.POWER_LOW);
        String provider=locationManager.getBestProvider(criteria,true);
        Location location1=locationManager.getLastKnownLocation(provider);
        updateWithNewLocation(location);
        locationManager.requestLocationUpdates(provider,2000,10,locationListener);
    }


    private void updateWithNewLocation(Location location){
        String latLongString;
        TextView myLocationText;
        myLocationText=(TextView)findViewById(R.id.myLocationText);
        if(location!=null){
            double lat =location.getLatitude();
            double lng =location.getLongitude();
            latLongString="纬度："+lat+"\n 经度:"+lng;
        }
        else{
            latLongString="获取地理信息失败";
        }
        myLocationText.setText("当前坐标是：\n"+latLongString);
    }


    private final LocationListener locationListener=new LocationListener(){
        @Override
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }
    };

}
