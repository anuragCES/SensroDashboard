package sensordashboard.com.sensordashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import sensordashboard.com.sensordashboard.data.SensorData;
import sensordashboard.com.sensordashboard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Mobile Tag", "MainActivity");
        // Model data
        SensorData sensorData = new SensorData("TestSensorValue", "test", "sensor2Name", "sensor2Value", "sensor3Name", "sensor3Value", "sensor4Name", "sensor4Value", "sensor5Name", "sensor5Value", "sensor6Name", "sensor6Value");

        // Binding object - Set view to work with binding object
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setSensor(sensorData);
        registerReceiver(broadcastReceiver, new IntentFilter("sensordashboard.com.sensordashboard.Sensor"));
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("OnReceive", "Service" );
            updateUI(intent);
        }
    };

    private void updateUI(Intent intent) {
        String temp = intent.getStringExtra("Service");
        TextView txtCounter = (TextView) findViewById(R.id.sensor1Name);
        //binding.setSensor(temp);
        if(temp!=null)
            txtCounter.setText(temp);
//        Log.d("RAG", temp.getSensor1Name());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
