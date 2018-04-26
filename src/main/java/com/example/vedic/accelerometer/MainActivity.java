package com.example.vedic.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
SensorManager sm=null;
TextView textView;
List list;

SensorEventListener sel=new SensorEventListener() {
    @Override
ff    public void onSensorChanged(SensorEvent sensorEvent) {
float[] values= sensorEvent.values;
textView.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        textView=(TextView)findViewById(R.id.tv);
        list=sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if(list.size()>0){
            sm.registerListener(sel,(Sensor)list.get(0),SensorManager.SENSOR_DELAY_GAME);
        }
        else{
            Toast.makeText(getBaseContext(),"No Accelerometer Support Found",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
     protected  void onStop(){
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
     }
}

