package sensordashboard.com.sensordashboard;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by anurags on 5/20/2016.
 */
public class DemoService  extends WearableListenerService {
    private final String TAG = "mobile/SensorReceiverService";
    //private RemoteSensorManager sensorManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "- Start Mobile Service - ");
        //sensorManager = RemoteSensorManager.getInstance(this);
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

//                if (path.startsWith("/sensors/")) {
//                    unpackSensorData(
//                            Integer.parseInt(uri.getLastPathSegment()),
//                            DataMapItem.fromDataItem(dataItem).getDataMap()
//                    );
//                }
            }
        }
    }
}
