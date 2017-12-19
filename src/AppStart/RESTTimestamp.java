package AppStart;


import com.rits.cloning.Cloner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class RESTTimestamp {
    private static final SimpleDateFormat timestampWithTZDParam = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat timestampParam = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat time = new SimpleDateFormat("h:mma");
    private static final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    Timestamp timestamp;

    public RESTTimestamp(String timestampStr) {
        try {
            timestamp = (new Timestamp(timestampParam.parse(timestampStr).getTime()));
        } catch (Exception ext) {
            try {
                timestamp = (new Timestamp(date.parse(timestampStr).getTime()));
            } catch (Exception exxx) {
                try {
                    timestamp = (new Timestamp(time.parse(timestampStr).getTime()));
                } catch (Exception exxxx) {
                }
            }
        }
    }

    public static Timestamp Parse(String timestampStr) {
        Cloner cloner = new Cloner();
        try {
            return (new Timestamp(cloner.deepClone(timestampWithTZDParam).parse(timestampStr).getTime()));
        } catch (Exception ex) {
            try {
                return (new Timestamp(cloner.deepClone(timestampParam).parse(timestampStr).getTime()));
            } catch (Exception ext) {
                try {
                    return (new Timestamp(cloner.deepClone(date).parse(timestampStr).getTime()));
                } catch (Exception exxx) {
                    try {
                        return (new Timestamp(cloner.deepClone(time).parse(timestampStr).getTime()));
                    } catch (Exception exxxx) {
                    }
                }
            }
        }
        return null;
    }

    public static String toString(Timestamp timestamp) {
        if (timestamp == null) return null;
        return timestamp.toString();
    }

    public String toString() {
        if (timestamp == null) {
            return null;
        } else {
            return timestamp.toString();
        }
    }
}