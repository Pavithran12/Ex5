package com.feed.rss;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

//import com.example.rssfeed.Employee;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView1);
        
        
        try {
			tv.setText(readRSS());
			
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
    
    public String readRSS() throws XmlPullParserException, IOException{
		String data="";
		Reader in = null;
		try {
			in = new InputStreamReader(getAssets().open("main_x.xml"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XmlPullParserFactory fac=XmlPullParserFactory.newInstance();
		fac.setNamespaceAware(true);
		XmlPullParser par=fac.newPullParser();
		par.setInput(in);
		
		int event=par.getEventType();
		//Toast.makeText(getApplicationContext(),String.valueOf(event), Toast.LENGTH_LONG).show();
		
		while(event!=XmlPullParser.END_DOCUMENT){
			String name=par.getName();
			//Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
			if(event==XmlPullParser.START_DOCUMENT){
				Toast.makeText(getApplicationContext(), "Parsing Starts", Toast.LENGTH_SHORT).show();
			}
			
			else if(event==XmlPullParser.START_TAG){
				if(name.equals("id"))
				data = data + "ID: " + par.nextText() + "\n";
				else if(name.equals("name"))
					data = data + "Name :" +par.nextText() + "\n";
				else if(name.equals("email"))
					data = data + "Email: " + par.nextText() + "\n";
				else if(name.equals("phoneno"))
					data = data+ "Ph.No: " + par.nextText() + "\n";
				Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
			}
			
			else if(event==XmlPullParser.END_TAG){
				if(name.equals("employee")){
					data = data + "\n";
				}
			}
			
			else if(event==XmlPullParser.END_DOCUMENT){
				//Toast.makeText(getApplicationContext(),"Parsing Ends", Toast.LENGTH_SHORT).show();
			}
			
			else if(event==XmlPullParser.TEXT){
			
			}
			event=par.nextToken();
		}
		Toast.makeText(getApplicationContext(),"Parsing Ends", Toast.LENGTH_SHORT).show();
    	return data;
    	
    }
    
}