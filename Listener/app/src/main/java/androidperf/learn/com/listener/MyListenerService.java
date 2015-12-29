package androidperf.learn.com.listener;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.IBinder;
import android.util.Log;

import java.util.Iterator;

public class MyListenerService extends Service {
    public MyListenerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        while(true)
        {
            getAppTrafficList();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppTrafficList()
    {
        PackageManager localPackageManager = getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(12288).iterator();
        PackageInfo localPackageInfo;
        String []arrayOfString;
        while( localIterator.hasNext())
        {
            localPackageInfo = (PackageInfo)localIterator.next();
            arrayOfString = localPackageInfo.requestedPermissions;
            if( arrayOfString != null && arrayOfString.length >0)
                for(int j=0;j<arrayOfString.length;j++) {
                    System.out.println(arrayOfString[j]);
                    if ("android.permission.INTERNET".equals(arrayOfString[j])) {
                        int uid = localPackageInfo.applicationInfo.uid;
                        long l1 = TrafficStats.getUidRxBytes(uid);
                        long l2 = TrafficStats.getUidTxBytes(uid);
                        Log.e("Python2Android " + localPackageInfo.applicationInfo.packageName.toString(), String.valueOf(l1 + l2));
                    }
                }
        }

    }
}
