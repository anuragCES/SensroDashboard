package sensordashboard.com.sensordashboard.Sensor;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by anurags on 5/20/2016.
 */
public class SensorSenderService extends WearableListenerService {
    private static final String TAG = "SensorSenderService";

    private DeviceClient deviceClient;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Sensor Service ");
        deviceClient = DeviceClient.getInstance(this);
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        super.onDataChanged(dataEvents);
        Log.d(TAG, "onDataChanged");
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                DataItem dataItem = dataEvent.getDataItem();
                Uri uri = dataItem.getUri();
                String path = uri.getPath();

//                if (path.startsWith("/filter")) {
//                    DataMap dataMap = DataMapItem.fromDataItem(dataItem).getDataMap();
//                    int filterById = dataMap.getInt(DataMapKeys.FILTER);
//                    deviceClient.setSensorFilter(filterById);
//                }
            }
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "Received message: " + messageEvent.getPath());

//        if (messageEvent.getPath().equals(ClientPaths.START_MEASUREMENT)) {
//            startService(new Intent(this, SensorService.class));
//        }
//
//        if (messageEvent.getPath().equals(ClientPaths.STOP_MEASUREMENT)) {
//            stopService(new Intent(this, SensorService.class));
//        }
    }
}
