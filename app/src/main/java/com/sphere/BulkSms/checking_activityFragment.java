package com.sphere.BulkSms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class checking_activityFragment extends Fragment {

    private final String TAG = MainActivityFragment.class.getSimpleName();

    public checking_activityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_checking_activity, container, false);

        Intent intent = getActivity().getIntent();
        Bundle b = intent.getExtras();
        String[] array=b.getStringArray("params");

        int oneBatch = Integer.parseInt(array[1]);
        int totalBatch = Integer.parseInt(array[2]);
        long phoneNum = Long.parseLong(array[0]);
        long disPh = phoneNum + (oneBatch*totalBatch - 1);
        int timeToSend = ((oneBatch-1)*10*totalBatch + (totalBatch-1)*30)/60;
        int totalSmsToSend = (oneBatch*totalBatch);

        String msgToSend = array[3];

        ((TextView) rootView.findViewById(R.id.startNum)).setText(array[0]);
        ((TextView) rootView.findViewById(R.id.msgToBroadcast)).setText(msgToSend);
        ((TextView) rootView.findViewById(R.id.endNum)).setText(String.valueOf(disPh));
        ((TextView) rootView.findViewById(R.id.timeToSend)).setText(String.valueOf(timeToSend)+" Minutes");
        ((TextView) rootView.findViewById(R.id.totalSms)).setText(String.valueOf(totalSmsToSend));

        return rootView;
    }


    public void sending(String phoneNum, String oneBatch, String totalBatch, String msgToSend){

        Sms sms = new Sms();
        sms.execute(phoneNum, oneBatch, totalBatch, msgToSend);
    }

    public class Sms extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params){
            int k = 1;

            int oneBatch = Integer.parseInt(params[1]);
            int totalBatch = Integer.parseInt(params[2]);
            String phoneNumber = "+91"+params[0];
            String messageToSend = params[3];
            long ph = Long.parseLong(params[0]);

            try{
                for(int i=0; i<totalBatch; i++){
                    for(int j=0; j<oneBatch; j++){
//                        Log.v(TAG, "msg number is  " + k + "  Mobile number is " + phoneNumber + " Msg to seng --- " + messageToSend + "\n");
//                Toast.makeText(getApplicationContext(),
//                        "Message sent to " + phoneNumber, Toast.LENGTH_LONG).show();
                        try {
                            SmsManager.getDefault().sendTextMessage(phoneNumber, null, messageToSend, null, null);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        ph++;
                        phoneNumber = "+91"+String.valueOf(ph);
                        k++;
                        Thread.sleep(10000, 0);
                    }
                    Thread.sleep(30000,0);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }String Str = "All messages sent !!";
            return Str ;
        }
    }


}
