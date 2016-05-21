package sensordashboard.com.sensordashboard;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import sensordashboard.com.sensordashboard.Sensor.DeviceClient;

/**
 * Created by anurags on 5/20/2016.
 */
public class SensorService extends Service implements SensorEventListener {

    final private String TAG = "Sensor Service";


    SensorManager mSensorManager;
    DeviceClient client;

    @Override
    public void onCreate()
    {
        Log.d(TAG, "Sensor Service - Start - ");
        mSensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        List<Sensor> sensorList =  mSensorManager.getSensorList(Sensor.TYPE_ALL);
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(this, accelerometerSensor, mSensorManager.SENSOR_DELAY_NORMAL);

        client = DeviceClient.getInstance(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "Sensor Service - Changed - ");
        int test = event.sensor.getType();
        float[] temp  = event.values;

        client.sendSensorData(event.sensor.getType(), event.accuracy, event.timestamp, event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
