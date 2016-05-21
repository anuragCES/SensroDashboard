package sensordashboard.com.sensordashboard.Sensor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.Arrays;

import sensordashboard.com.sensordashboard.data.SensorData;

/**
 * Created by anurags on 5/20/2016.
 */
public class SensorReceiverService extends WearableListenerService {
    private final String TAG = "mobile/SensorReceiverService";
    //private RemoteSensorManager sensorManager;
    SensorUtil sensorManager;
    private Intent intent;

    private final Handler handler = new Handler();

    public static final String BROADCAST_ACTION = "sensordashboard.com.sensordashboard.Sensor";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "- Start Mobile Service - ");
        sensorManager = SensorUtil.getInstance(this);
        intent = new Intent(BROADCAST_ACTION);
        sensorManager.setSensorData(new SensorData("sensorType","values", "", "","","","","","","","",""));
        handler.removeCallbacks(sendUpdatesToUI());
        handler.postDelayed(sendUpdatesToUI(),1000);
    }

    private Runnable sendUpdatesToUI() {
        Log.d(TAG, "sendUpdates "+sensorManager.getSensorData().getSensor1Value());
        SensorData temp = sensorManager.getSensorData();
        intent.putExtra("Service", sensorManager.test);


        sendBroadcast(intent);
        return null;
    }

    @Override
    public void onPeerConnected(Node peer) {
        super.onPeerConnected(peer);

        Log.i(TAG, "Connected: " + peer.getDisplayName() + " (" + peer.getId() + ")");
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        Log.d(TAG, "onDataChanged()");

        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                DataItem dataItem = dataEvent.getDataItem();
                Uri uri = dataItem.getUri();
                String path = uri.getPath();
                Log.d(TAG, "PATH  - "+DataMapItem.fromDataItem(dataItem).getDataMap());
                if (path.startsWith("/sensors/")) {
                    unpackSensorData(
                            Integer.parseInt(uri.getLastPathSegment()),
                            DataMapItem.fromDataItem(dataItem).getDataMap()
                    );
                }
            }
        }
    }

    private void unpackSensorData(int sensorType, DataMap dataMap) {
        int accuracy = dataMap.getInt("ACCURACY");
        long timestamp = dataMap.getLong("TIMESTAMP");
        float[] values = dataMap.getFloatArray("VALUES");

        Log.d(TAG, "Received sensor data " + sensorType + " = " + Arrays.toString(values));
        SensorData temp = new SensorData(""+sensorType,""+values, "", "","","","","","","","","");
        sensorManager.test = Arrays.toString(values);
    }
}
