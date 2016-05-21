package sensordashboard.com.sensordashboard.Sensor;

import android.content.Context;

import sensordashboard.com.sensordashboard.data.SensorData;

/**
 * Created by anurags on 5/20/2016.
 */
public class SensorUtil {

    static SensorUtil instance;
    static SensorData mSensorData;

    public String test;

    Context context;

    SensorUtil(Context context){
        context = context;
        test = "Nonde";
    }

    public static synchronized SensorUtil getInstance(Context context){
        if (instance == null) {
            instance = new SensorUtil(context.getApplicationContext());
        }
        return instance;
    }

    public void setSensorData(SensorData sensorData){
        this.mSensorData = sensorData;
    }

    public SensorData getSensorData(){
        return this.mSensorData;
    }

}
