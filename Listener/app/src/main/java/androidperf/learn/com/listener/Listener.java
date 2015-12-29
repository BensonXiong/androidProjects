package androidperf.learn.com.listener;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Iterator;


public class Listener extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        startService(new Intent(this,MyListenerService.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listener, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getAppTrafficList()
    {
        PackageManager localPackageManager = getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
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
                    Log.e("Python2Android "+ localPackageInfo.applicationInfo.packageName.toString(),String.valueOf(l1+l2));
                }
            }
        }

    }
}
