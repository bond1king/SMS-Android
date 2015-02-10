package com.bhargav.readmessages;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import com.bhargav.readmessages.R;


public class MainActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try{
			getSMS();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getSMS() throws IOException{
	     List<String> sms = new ArrayList<String>();
	        Uri uriSMSURI = Uri.parse("content://sms/inbox");
	        Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
	        System.out.print(Environment.getExternalStorageDirectory() + File.separator + "test.txt");
	        File file = new File(Environment.getExternalStorageDirectory(),"test.txt");
	        file.createNewFile();
	        StringBuilder sb = new StringBuilder();
	        OutputStream fo = new FileOutputStream(file);
	        while (cur.moveToNext()) {
	               String address = cur.getString(cur.getColumnIndex("address"));
	               String body = cur.getString(cur.getColumnIndexOrThrow("body"));
	               //sms.add("Number: " + address + " .Message: " + body);
	               sb.append("Number: ").append(address).append(" ----- Message: ").append(body);
		           fo.write(sb.toString().getBytes());
		    }
	        fo.close();
            System.out.println("file created: "+file);
	    
	}
}

