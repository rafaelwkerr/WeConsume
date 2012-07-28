package me.com.consurme.view;


import java.util.ArrayList;

import me.com.consurme.R;
import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.entity.Categoria;
import me.com.consurme.model.CategoriaModel;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent.OnFinished;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import greendroid.app.GDActivity;
import greendroid.app.GDListActivity;
import greendroid.graphics.drawable.ActionBarDrawable;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ItemAdapter;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import greendroid.widget.item.TextItem;

public class EditarCategoriaActivity extends GDListActivity {
	
    private QuickActionWidget quickActions;
    
    private final int EDIT = 1;
    
    
    
    @Override
    protected void onRestart() {
    	
    	// TODO Auto-generated method stub
    	
    	super.onRestart();

    	populaListView();
    
    }
    
    
 
    
    @Override
    protected void onResume() {
    	
    	// TODO Auto-generated method stub
    	
    	super.onResume();

    	populaListView();
    
    }
    
    
    
    @Override
    protected void onStart() {
    	
    	// TODO Auto-generated method stub
    
    	super.onStart();
    	
    	populaListView();
    
    }
    
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	onDestroy();
    	
    }
    
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop(); 	
    	onDestroy();
    	
    }
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);        
        //setActionBarContentView(R.layout.buscar_categoria);
        
        
        initActionBar();
        initQuickActionBar();
        
        

       
        
              
    }	
    
    
    
    
    public void populaListView(){
    	
    	
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        
        CategoriaModel categMod = new CategoriaModel(this);
        
        categorias =  categMod.buscarTodasCategorias();
        
        
        ArrayAdapter<Categoria> categoriaAdapter = new ArrayAdapter<Categoria>
        			(this,android.R.layout.simple_list_item_1,categorias);
        
        
        setListAdapter(categoriaAdapter);
    	
    	
    	
    }
    
    
    public void initActionBar(){
    	
    	//addActionBarItem(Type.Add, ADD);
    	addActionBarItem(Type.Edit, EDIT);
    	//addActionBarItem(Type.Share, SHARE);        	

    }
    
   private TextItem createTextItem(int stringId, Class<?> klass) {
        final TextItem textItem = new TextItem(getString(stringId));
        textItem.setTag(klass);
        return textItem;
    }
    
    
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        
    	Categoria categoria;
    	
    	categoria = (Categoria) l.getAdapter().getItem(position);
    	
        Intent intent = new Intent("alterarCategoria").putExtra("codigo",categoria.getId());
        
        startActivity(intent);
    
    
    }

    @Override
    public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
    	
    	
		switch (item.getItemId()) {
        case EDIT:
        	
           /* Toast.makeText(getApplicationContext(),
                "Você cliclou o botão Adicionar",
                Toast.LENGTH_SHORT).show();*/
                   
            quickActions.show(item.getItemView());
            break;
            

            /*      case EDIT:
        	
            Toast.makeText(getApplicationContext(),
                "Você cliclou o botão Editar",
                Toast.LENGTH_SHORT).show();
            
            quickActionsEdit.show(item.getItemView());
            break; */


        default:
            return super.onHandleActionBarItemClick(item, position);
   }

   return true;
    	
    	
    }
    


	private void initQuickActionBar() {
		
		
        quickActions = new QuickActionBar(this);
        //add   
        //quickActions.addQuickAction(new QuickAction(this, R.drawable.search,"Usuário"));
        quickActions.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Adicionar Categoria"));
   
        //edit    
        //quickActionsEdit.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Categoria"));
        //quickActions.addQuickAction(new QuickAction(this, R.drawable.icon_edit,"Editar Categoria"));
        

        
        final Intent itAddCat = new Intent(this, CriarCategoriaActivity.class);
        //final Intent itEditCat = new Intent(this, EditarCategoriaActivity.class);
        
        quickActions.setOnQuickActionClickListener(new OnQuickActionClickListener() {
        	
                       public void onQuickActionClicked(QuickActionWidget widget,int position) {
                    	    
                    	   
                    	   
                    	   switch(position){
                    	   
                    	   case 0:
                    		   
                    		   startActivity(itAddCat); 
                    		   
                    		   break;
                    	   
                    	   case 1:
                    		   
                    		
                    		   
                    		   break;
                    		   
                    	   case 2:
                    		   
                    		   break;
                    		   
                    	   default : onQuickActionClicked(widget, position); 	   
                    	   
                    	   }
                    	    
                            /*Toast.makeText(MainProject.this,
                                      "Item " + position + " pulsado",
                                      Toast.LENGTH_SHORT).show();*/
                       }
                  });
        
 
   }	
	
	
	
    
}
