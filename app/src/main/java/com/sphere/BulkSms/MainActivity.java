package com.sphere.BulkSms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//                Snackbar.make(view, "Your message sending command has been initiated", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                MainActivityFragment maf = new MainActivityFragment();
//                maf.sending(oneBatch, totalBatch, phoneNum, msgToSend);



    }

    public void sendBtn(View view){
        EditText msg = (EditText) findViewById(R.id.messageToSend);
        String msgToSend = msg.getText().toString();

        EditText ba = (EditText) findViewById(R.id.oneBatch);
        String oneBatch = ba.getText().toString();

        EditText baTotal = (EditText) findViewById(R.id.totalBatch);
        String totalBatch = baTotal.getText().toString();

        EditText phone = (EditText) findViewById(R.id.phoneNum);
        String phoneNum = phone.getText().toString();

        String[] str = {phoneNum,oneBatch, totalBatch,msgToSend};

        EditText pass = (EditText) findViewById(R.id.password);
        String val = pass.getText().toString();
        int password = Integer.parseInt(val);

        if (password == 1234){
            Bundle b=new Bundle();
            b.putStringArray("params", str);
            Intent intent = new Intent(getApplicationContext(), checking_activity.class)
                    .putExtras(b);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter correct password", Toast.LENGTH_SHORT).show();
        }

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
}
