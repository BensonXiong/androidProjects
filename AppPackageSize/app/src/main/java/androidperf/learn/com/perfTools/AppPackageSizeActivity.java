package androidperf.learn.com.perfTools;

import android.app.Activity;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;



public class AppPackageSizeActivity extends Activity {

    public String TAG = "QUERY_PACKAGE_SIZE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryPackageSize();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void queryPackageSize()
    {

        try {
        PackageManager pm = getPackageManager();
        Method getPackageSizeInfo =    pm.getClass().getDeclaredMethod("getPackageSizeInfo",String.class,IPackageStatsObserver.class);
            getPackageSizeInfo.invoke(pm,"com.lufax.android",new IPackageStatsObserver.Stub(){

                @Override
                public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                 long    cachesize=    pStats.cacheSize;
                    long   datasize =   pStats.dataSize;
                    long   codesize = pStats.codeSize;

                    Log.i(TAG, "cachesize--->" + cachesize + " datasize---->"
                            + datasize + " codeSize---->" + codesize);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
