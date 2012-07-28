package me.com.consurme.view;


import me.com.consurme.R;
import me.com.consurme.dataBase.CreateDataBase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class ConsurmeActivity extends Activity  {

	
	private CreateDataBase dBase;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        

        setContentView(R.layout.index);
        
        dBase = new CreateDataBase(getApplicationContext());
        dBase.open();
             
        dBase.close();
        
    	final Intent it = new Intent(this, MainProject.class);
        
        Handler handler = new Handler(); 
        handler.postDelayed(new Runnable() { 
            public void run() { 

        		startActivity(it);         	
            } 
        }, 2000); 
              
    }
    
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
        this.finish();
    	
    }
    
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop(); 	
    	this.finish();
    	
    }
    

    
    
}