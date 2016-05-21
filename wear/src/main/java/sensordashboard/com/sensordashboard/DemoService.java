package sensordashboard.com.sensordashboard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by anurags on 5/20/2016.
 */
public class DemoService extends Service {

    @Override
    public void onCreate(){
        Log.d("TAG", "DemoService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
