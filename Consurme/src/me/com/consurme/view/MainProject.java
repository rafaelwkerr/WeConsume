package me.com.consurme.view;

import java.util.ArrayList;
import java.util.List;

import greendroid.app.ActionBarActivity;
import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ActionBar.OnActionBarListener;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import greendroid.widget.item.TextItem;
import me.com.consurme.R;
import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.entity.Categoria;
import me.com.consurme.entity.Consumo;
import me.com.consurme.model.CategoriaModel;
import me.com.consurme.model.ConsumoModel;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import com.google.ads.*;


public class MainProject extends GDActivity {

	
	private final int ADD = 0;
    private final int EDIT = 1;
    private Button imaBtn;
    private Button btn;
    private Spinner spinnerCategoria;
    
    private ImageView btnAddValor;
    //private Button btnAddValor;
    private EditText addValorCons;
    
    private Long selectCategoria;

    private QuickActionWidget quickActionsEdit;
    private QuickActionWidget quickActions;
    
	private ConsumoModel consumoNegocio;
	private CreateDataBase dBase;
	
	
	private TextView valorLimiteCat;
	private TextView consumoTotal;	
	
	
    @Override
    public void onBackPressed() {
    	
    	fecharAplicativo();
    	
    }
    

    private void closeApplication() {
    	
    	android.os.Process.killProcess(android.os.Process.myPid());
        System.out.println("closeApplication ");
        this.finish();
        
    }
    
	@Override
	protected void onRestart()
	{
		// TODO Auto-generated method stub
		super.onRestart();
		populaSpinnerCategoria();
		populaCamposValores();
		
	}
	
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		populaSpinnerCategoria();
		populaCamposValores();
		
	}
	
	@Override
	protected void onStart()
	{
		// TODO Auto-generated method stub
		super.onStart();
		populaSpinnerCategoria();
		populaCamposValores();
		
	}
	

	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
        setActionBarContentView(R.layout.main);
        
        //adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        
        
        
		consumoNegocio = new ConsumoModel(this.getApplication());
        //dBase = new CreateDataBase(getApplicationContext());
        //dBase.open();
        
        
        initActionBar();
        initQuickActionBar();
        
  
        
        
        valorLimiteCat = (TextView) findViewById(R.id.limite_categoria);
        consumoTotal = (TextView) findViewById(R.id.valor_consumido);
        
        
        spinnerCategoria = (Spinner) findViewById(R.id.listaCategorias);
        spinnerCategoria.setOnItemSelectedListener(getSelecaoSpinner);
        
        
        addValorCons = (EditText) findViewById(R.id.addValorCons);
        btnAddValor = (ImageView) findViewById(R.id.add_button);    
        btnAddValor.setOnClickListener(mClickHandler);
    
    
        //dBase.close();
        
    }
        
    
    

    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	
    	super.onCreateOptionsMenu(menu);
    	   MenuInflater inflater = getMenuInflater();
    	   inflater.inflate(R.menu.opcao, menu);
    	   return true;
    	   
/*        new MenuInflater(this).inflate(R.menu.opcao, menu);
        //setMenuBackground();
        return super.onCreateOptionsMenu(menu);*/
        
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	switch (item.getItemId()) {
    	   case R.id.menu_close:
    	      
    		   fecharAplicativo();
    		   
    	      break;
    	      
    	   case R.id.menu_home:
    		   //startActivity(new Intent(this, MainProject.class));
    	       return true;
    	       
    	       
    	   case R.id.menu_sobre:     
    		   
    		   //startActivity(new Intent(this, CriarCategoriaActivity.class));
    		   
    		   openDialog();
    		   
    	       return true;
    	       
    	   }
    	
    	   return false;
    
    }
    
    
    
    protected void setMenuBackground(){                     

    	
    	getLayoutInflater().setFactory( new Factory(){

			public View onCreateView(String name, Context context,
					AttributeSet attrs) {
				
				
                if ( name.equalsIgnoreCase( "com.android.internal.view.menu.IconMenuItemView" ) ) {
                    // Ask our inflater to create the view  
                	try {
                       LayoutInflater f = getLayoutInflater();  
                       final View view = f.createView( name, null, attrs );  
                       /* The background gets refreshed each time a new item is added the options menu.  
                       * So each time Android applies the default background we need to set our own  
                       * background. This is done using a thread giving the background change as runnable 
                       * object */
                       new Handler().post( new Runnable() {  
                           public void run () {  
                        	   
                               // sets the background color   
                               view.setBackgroundColor(R.color.menu_focused);
                               // sets the text color              
                               ((TextView) view).setTextColor(Color.GRAY);
                               // sets the text size              
                               ((TextView) view).setTextSize(18);
               }
                       } );  
                   return view;

             } 
                	catch (InflateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				

			}
    		
                return null;
                
    	}
		   
   });
    	
  }
        
    	
        
/*    private void setMenuBackground() {
		
    	getLayoutInflater().setFactory(new LayoutInflater.Factory() {
    		 
            *//**
             * Step 2. Implementing the onCreateView method 
             * that will actually set the background selector.
             * {@inheritDoc}
             *//*
            public View onCreateView(final String name, 
                                     final Context context, 
                                     final AttributeSet attributeSet) {
                *//**
                 *  Step 3. Checking if the view that is to be created 
                 *  is IconMenuItemView.
                 *  Notice that this is an internal class.
                 *//*
                if (name.equalsIgnoreCase
                      ("com.android.internal.view.menu.IconMenuItemView")) {
                   
                        *//**
                         * Step 4. If the view is IconMenuItemView then 
                         * create the view using the LayoutInflater.
                         *//*
                        final LayoutInflater f = getLayoutInflater();
                        final View view = f.createView(name, null, attributeSet);
                        *//**
                         * Step 5. This is the key part.  
                         * The view instance that was created in step 4 
                         * is an instance of IconMenuItemView.  
                         * This is the view whose background color 
                         * we want to change.  Unfortunately we just cannot 
                         * change the background color at this place, 
                         * since even if we change it here, 
                         * framework overrides this value and 
                         * we see the default background selector.
                         * Because of this reason the below line is commented.  
                         * It does not work.
                         *//*
                        //view.setBackgroundResource(R.drawable.menu_selector);
                        *//**
                         * Step 6. We have to change the background color 
                         * after the view has rendered, using the Handler api.
                         *//*
                        new Handler().post(new Runnable() {
                            public void run() {
                               *//** Step 7.  Changing the backgound color. *//*
                               view.setBackgroundResource(
                                               R.drawable.menu_selector);
                            }
                        });
                        return view;
                    
                }
                return null;
            }


        });
    }*/
    	
    	
    	

	
    
    

    
    
    
    private OnClickListener mClickHandler = new OnClickListener() {

    	public void onClick(View v) {

    		switch (v.getId()) {

    		case R.id.add_button:
    			
    			
    			btnAddValor.setImageResource(R.drawable.add_button_consume_click);
    			
    	        Handler handler = new Handler(); 
    	        handler.postDelayed(new Runnable() { 
    	            public void run() { 

    	            	btnAddValor.setImageResource(R.drawable.add_button_consume);    
    	            	
    	            } 
    	        }, 100); 
    	        
    			  			
    			
    			if(validaCampos()){
    			
	    			Consumo consumo = recuperarInformacaoConsumo();
	    			
	    			enviarConsumo(consumo);
	    			
	    			limparCampos();
	    			
	    			populaCamposValores();
	    			
    			}
    			
    			break;
    			


    		}

    	}

    };    
    
	/**
	 * Envia o objeto Consumo para ser validado.
	 * 
	 */
	public void enviarConsumo(Consumo consumo) {

		  consumoNegocio.adicionarConsumo(consumo);

	}
	
	
	
	public void populaSpinnerCategoria(){
		
		 
		 ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		    
		 CategoriaModel categMod = new CategoriaModel(this);
	           
	     categorias =  categMod.buscarTodasCategorias();        
	        
	     ArrayAdapter<Categoria> categoriaAdapter = new ArrayAdapter<Categoria>
	        			(this,android.R.layout.simple_spinner_item,categorias);
	        
	     categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);	    
	     
	     spinnerCategoria.setAdapter(categoriaAdapter);
	        
	        
	}
	
	
	
	
	
	
	
	
	public String buscarConsumo(Categoria categoria){
		
		String valorLimite;
		
		valorLimite = consumoNegocio.totalConsumoCategoria(categoria);
		
		return valorLimite;
		
	}
	
	
	
	public String buscarConsumoTotal(Categoria categoria){
		
		String valorTotal;
		
		valorTotal = consumoNegocio.totalValorConsumo(categoria);
		
		return valorTotal;
		
	}	
	
	
	
    
/*    private TextItem createTextItem(int stringId, Class<?> klass) {
        final TextItem textItem = new TextItem(getString(stringId));
        textItem.setTag(klass);
        return textItem;
    }*/
    
    //add os menus no ActionBar
    public void initActionBar(){
        	
        	//addActionBarItem(Type.Add, ADD);
        	addActionBarItem(Type.Edit, EDIT);
        	//addActionBarItem(Type.Share, SHARE);        	
	
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
        quickActionsEdit = new QuickActionBar(this);
        //add   
        //quickActions.addQuickAction(new QuickAction(this, R.drawable.search,"Usuário"));
        quickActions.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Adicionar Categoria"));
   
        //edit    
        //quickActionsEdit.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Categoria"));
        quickActions.addQuickAction(new QuickAction(this, R.drawable.,"Editar Categoria"));
        

        //quickActions.addQuickAction(new QuickAction(this, R.drawable.search,"Buscar Categoria"));
        
        final Intent itAddCat = new Intent(this, CriarCategoriaActivity.class);
        final Intent itEditCat = new Intent(this, EditarCategoriaActivity.class);
        
        OnQuickActionClickListener 
        
        
        quickActions.setOnQuickActionClickListener(new OnQuickActionClickListener() {
        	
                       public void onQuickActionClicked(QuickActionWidget widget,int position) {
                    	    
                    	   
                    	   
                    	   switch(position){
                    	   
                    	   case 0:
                    		   
                    		   itAddCat.putExtra(ActionBarActivity.GD_ACTION_BAR_TITLE, "Next screen title");
                    		   startActivity(itAddCat); 
                    		   
                    		   break;
                    	   
                    	   case 1:
                    		   
                    		   startActivity(itEditCat); 
                    		   
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
        
        
        
        quickActionsEdit.setOnQuickActionClickListener(new OnQuickActionClickListener() {
        	
			            public void onQuickActionClicked(QuickActionWidget widget,int position) {
			         	    
			         	   
			         	   
			         	   switch(position){
			         	   
			         	   case 0:
			         		   
			         		   startActivity(itEditCat); 
			         		   
			         		   break;
			         	   
			         	   case 1:
			         		   
			         		   break;
			         		   
			         	   case 2:
			         		   
			         		   break;
			         		   
			         	   default : onQuickActionClicked(widget, position); 	   
			         	   
			         	   }
			         	    
			               /*  Toast.makeText(MainProject.this,
			                           "Item " + position + " pulsado",
			                           Toast.LENGTH_SHORT).show();*/
			            }
			      });
   }	
	
	
	
	
	
	/**
	 * Abre a tela para Add Valores.
	 */
	private void abrirTelaAddNovoValor() {

		Intent it = new Intent(this, AlterarCategoriaActivity.class);
		startActivity(it);

	}


	
	/**
	 * Recupera informações da tela e preenche o objeto consumo.
	 * 
	 * @return
	 */
	private Consumo recuperarInformacaoConsumo() {

		Consumo consumo = new Consumo(selectCategoria, Double.parseDouble(addValorCons.getText().toString()));

		return consumo;

	}
	
	
	
	public void populaCamposValores(){
		

		Categoria categoria = (Categoria) spinnerCategoria.getSelectedItem();
		
		if(categoria != null){
		
		String valorLimite = buscarConsumo(categoria);
		String valorConsumido = buscarConsumoTotal(categoria);
			
		preencheBarraConsumo(valorLimite, valorConsumido);
		
		}
		
	}
	
	
	private OnItemSelectedListener getSelecaoSpinner = new  OnItemSelectedListener() {
				  

		public void onItemSelected(AdapterView parent, View arg1, int arg2,
				long arg3) {

			Categoria categoria = (Categoria) parent.getSelectedItem();  
			
			selectCategoria = categoria.getId();
			
			
			String valorLimite = buscarConsumo(categoria);
			String valorConsumido = buscarConsumoTotal(categoria);
				
			preencheBarraConsumo(valorLimite, valorConsumido);
			
			
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	/**
	 * Limpa os campos da tela de Categoria.
	 */
	private void limparCampos() {

		Toast.makeText(getApplication(), R.string.sucesso_salva_consumo, Toast.LENGTH_LONG).show();

		addValorCons.setText("");

	}
	
	
	
	
	public void preencheBarraConsumo(String valorLimite, String valorTotal){
		
		valorLimiteCat.setText(valorLimite);
		consumoTotal.setText(valorTotal);	
			
	}
	
	
	public boolean validaCampos(){
		
		boolean sucess = true;
		
		
		if(addValorCons.length() == 0){
			
			sucess = false;
			Toast.makeText(getApplication(), R.string.erro_valor_consumo_null, Toast.LENGTH_LONG).show();
			
		}
	
		
		return sucess;
		
		
	}	
	
	
	public void fecharAplicativo(){
		
	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
        builder.setMessage("Você tem certeza que quer sair?")//.setCancelable(false).setPositiveButton("YES", null);     
        
            .setCancelable(false)
            .setPositiveButton("YES",
            
            new DialogInterface.OnClickListener() {
                // On
                // clicking
                // "Yes"
                // button

                public void onClick(DialogInterface dialog,int id) {
                    System.out.println(" onClick ");
                    closeApplication(); // Close Application method called
                }
            })
            .setNegativeButton("NO",
            new DialogInterface.OnClickListener() {
                // On
                // clicking
                // "No"
                // button
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            
	}
	
	private void openDialog(){
		 
    final Dialog dialog = new Dialog(this);

	dialog.setTitle("Sobre WeConsume");

	dialog.setContentView(R.layout.sobre);

	dialog.setCancelable(true);

	dialog.show();

	}
	
}
