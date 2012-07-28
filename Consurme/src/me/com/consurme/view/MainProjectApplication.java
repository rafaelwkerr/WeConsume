package me.com.consurme.view;

import me.com.consurme.R;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.net.Uri;
import greendroid.app.GDApplication;

public class MainProjectApplication extends GDApplication {

	
	@Override
    public Class<?> getHomeActivityClass() {
        return MainProject.class;
    }
 
	
    @Override
    public Intent getMainApplicationIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_url)));
    }
	
}
