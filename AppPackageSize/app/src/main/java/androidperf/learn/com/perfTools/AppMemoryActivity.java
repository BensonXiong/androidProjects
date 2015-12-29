package androidperf.learn.com.perfTools;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;

import androidperf.learn.com.perfTools.R;

public class AppMemoryActivity extends Activity {
    public String TAG = "GET_MEMORY_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__cpu);
        getMemoryInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__cpu, menu);
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

    public void getMemoryInfo()
    {

        try {
            ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo outinfo = new ActivityManager.MemoryInfo();
            actManager.getMemoryInfo(outinfo);
            Log.i(TAG,"系统剩余内存:"+ (outinfo.availMem)+"k");
            Log.i(TAG,"系统是否处于低内存运行:"+ outinfo.lowMemory);
            Log.i(TAG,"当系统剩余内存低于:"+ (outinfo.threshold)+"时就看成低内存运行");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
