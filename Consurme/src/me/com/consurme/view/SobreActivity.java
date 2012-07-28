package me.com.consurme.view;

import me.com.consurme.R;
import android.os.Bundle;
import android.view.Window;
import greendroid.app.GDActivity;

public class SobreActivity extends GDActivity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setActionBarContentView(R.layout.sobre);
	
	
	
	}
	
	

}
