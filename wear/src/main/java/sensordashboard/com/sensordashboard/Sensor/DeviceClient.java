package sensordashboard.com.sensordashboard.Sensor;

import android.content.Context;
import android.util.Log;
import android.util.SparseLongArray;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by anurags on 5/20/2016.
 */
public class DeviceClient {
    private static final String TAG = "SensorDashboard/DeviceClient";
    private static final int CLIENT_CONNECTION_TIMEOUT = 15000;

    public static DeviceClient instance;

    public static DeviceClient getInstance(Context context) {
        if (instance == null) {
            instance = new DeviceClient(context.getApplicationContext());
        }

        return instance;
    }

    private Context context;
    private GoogleApiClient googleApiClient;
    private ExecutorService executorService;
    private int filterId;

    private SparseLongArray lastSensorData;

    private DeviceClient(Context context) {
        this.context = context;

        googleApiClient = new GoogleApiClient.Builder(context).addApi(Wearable.API).build();

        executorService = Executors.newCachedThreadPool();
        lastSensorData = new SparseLongArray();
    }

    public void setSensorFilter(int filterId) {
        Log.d(TAG, "Now filtering by sensor: " + filterId);

        this.filterId = filterId;
    }

    public void sendSensorData(final int sensorType, final int accuracy, final long timestamp, final float[] values) {
        long t = System.currentTimeMillis();

        long lastTimestamp = lastSensorData.get(sensorType);
        long timeAgo = t - lastTimestamp;

        if (lastTimestamp != 0) {
            if (filterId == sensorType && timeAgo < 100) {
                return;
            }

            if (filterId != sensorType && timeAgo < 3000) {
                return;
            }
        }

        lastSensorData.put(sensorType, t);

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                sendSensorDataInBackground(sensorType, accuracy, timestamp, values);
            }
        });
    }

    private void sendSensorDataInBackground(int sensorType, int accuracy, long timestamp, float[] values) {
        if (sensorType == filterId) {
            Log.i(TAG, "Sensor " + sensorType + " = " + Arrays.toString(values));
        } else {
            Log.d(TAG, "Sensor " + sensorType + " = " + Arrays.toString(values));
        }

        PutDataMapRequest dataMap = PutDataMapRequest.create("/sensors/" + sensorType);

        dataMap.getDataMap().putInt("ACCURACY", accuracy);
        dataMap.getDataMap().putLong("TIMESTAMP", timestamp);
        dataMap.getDataMap().putFloatArray("VALUES", values);

        PutDataRequest putDataRequest = dataMap.asPutDataRequest();
        send(putDataRequest);
    }

    private boolean validateConnection() {
        if (googleApiClient.isConnected()) {
            return true;
        }

        Log.d(TAG, "I'm here");
        ConnectionResult result = googleApiClient.blockingConnect(CLIENT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);

        return result.isSuccess();
    }

    private void send(PutDataRequest putDataRequest) {
        if (validateConnection()) {
            Wearable.DataApi.putDataItem(googleApiClient, putDataRequest).setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
                @Override
                public void onResult(DataApi.DataItemResult dataItemResult) {
                    Log.v(TAG, "Sending sensor data: " + dataItemResult.getStatus().isSuccess());
                }
            });
        }
    }
}
