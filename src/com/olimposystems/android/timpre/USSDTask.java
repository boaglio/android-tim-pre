package com.olimposystems.android.timpre;

import android.os.AsyncTask;
import android.util.Log;

public  class USSDTask extends AsyncTask<Void, Void, String> {
	
	private static final String CATEGORIA = "timpre";
	
    protected String doInBackground(Void... params) {
    
    	String log="";
    	USSD ussd = new USSD(4000, 4000);
 		if (ussd.IsFound()) {
 			log="";
 			Log.i(CATEGORIA, "USSD=" + ussd.getMsg());
 		} else
 			log="" + R.string.error_ussd_msg;
 		
		return log;
 		
     }
 
 }
 