package sensordashboard.com.sensordashboard.data;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * Created by anurags on 5/20/2016.
 */
public final class SensorData implements Serializable {

    @Nullable
    private final String sensor1Name;
    @Nullable
    private final String sensor1Value;
    @Nullable
    private final String sensor2Name;
    @Nullable
    private final String sensor2Value;
    @Nullable
    private final String sensor3Name;
    @Nullable
    private final String sensor3Value;
    @Nullable
    private final String sensor4Name;
    @Nullable
    private final String sensor4Value;
    @Nullable
    private final String sensor5Name;
    @Nullable
    private final String sensor5Value;
    @Nullable
    private final String sensor6Name;
    @Nullable
    private final String sensor6Value;

    public SensorData(String sensor1Name, String sensor1Value, String sensor2Name, String sensor2Value, String sensor3Name, String sensor3Value, String sensor4Name, String sensor4Value, String sensor5Name, String sensor5Value, String sensor6Name, String sensor6Value) {
        this.sensor1Name = sensor1Name;
        this.sensor1Value = sensor1Value;
        this.sensor2Name = sensor2Name;
        this.sensor2Value = sensor2Value;
        this.sensor3Name = sensor3Name;
        this.sensor3Value = sensor3Value;
        this.sensor4Name = sensor4Name;
        this.sensor4Value = sensor4Value;
        this.sensor5Name = sensor5Name;
        this.sensor5Value = sensor5Value;
        this.sensor6Name = sensor6Name;
        this.sensor6Value = sensor6Value;
    }

    public String getSensor1Name() {
        return this.sensor1Name;
    }

    public String getSensor1Value() {
        return this.sensor1Value;
    }

    @Nullable
    public String getSensor2Name() {
        return this.sensor2Name;
    }

    @Nullable
    public String getSensor2Value() {
        return sensor2Value;
    }

    @Nullable
    public String getSensor3Name() {
        return sensor3Name;
    }

    @Nullable
    public String getSensor3Value() {
        return sensor3Value;
    }

    @Nullable
    public String getSensor4Name() {
        return sensor4Name;
    }

    @Nullable
    public String getSensor4Value() {
        return sensor4Value;
    }

    @Nullable
    public String getSensor5Name() {
        return sensor5Name;
    }

    @Nullable
    public String getSensor5Value() {
        return sensor5Value;
    }

    @Nullable
    public String getSensor6Name() {
        return sensor6Name;
    }

    @Nullable
    public String getSensor6Value() {
        return sensor6Value;
    }
}
