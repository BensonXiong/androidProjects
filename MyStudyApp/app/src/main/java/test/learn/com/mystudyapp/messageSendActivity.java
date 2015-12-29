package test.learn.com.mystudyapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class messageSendActivity extends Activity implements View.OnClickListener {

    private TextView  staticText;
    private Button btn;

    private static final int COMPLETED = 0;

    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            if( msg.what == COMPLETED)
            {
                staticText.setText("completed");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);
        staticText = (TextView)findViewById(R.id.textView);
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View c){
        new WorkerThread().start();
    }


    private class WorkerThread extends Thread{
        @Override
        public void run() {
            Message msg=new Message();
            msg.what = COMPLETED;
            handler.sendMessage(msg);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_send, menu);
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
}
