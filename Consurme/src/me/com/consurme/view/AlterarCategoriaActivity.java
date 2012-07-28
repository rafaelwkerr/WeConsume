package me.com.consurme.view;

import greendroid.app.GDActivity;
import greendroid.widget.ActionBarItem;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.ActionBarItem.Type;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;
import me.com.consurme.R;
import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.entity.Categoria;
import me.com.consurme.model.CategoriaModel;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterarCategoriaActivity extends GDActivity {
	
	
	
    private QuickActionWidget quickActions;
    
    private final int EDIT = 1;
    
	private EditText descCategoria;
	private EditText valorCategoria;

	private Button bntAlterar;
	private Button bntExcluir;
	private Button bntLimpar;	
	
	private Categoria categoria;
	private CategoriaModel categoriaNegocio;
	private CreateDataBase dBase;
	
	
	
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

        setActionBarContentView(R.layout.alterar_categoria);
        
        initActionBar();
        initQuickActionBar();
        
        
        categoriaNegocio = new CategoriaModel(this.getApplication());
     
        dBase = new CreateDataBase(getApplicationContext());
        dBase.open();
        
        
        descCategoria = (EditText) findViewById(R.id.add_categoria_input_edit);
        valorCategoria = (EditText) findViewById(R.id.add_categoria_valor_input_edit);
        
        bntAlterar = (Button) findViewById(R.id.btn_alterar_categoria);
        bntExcluir = (Button) findViewById(R.id.btn_excluir_categoria);
        bntLimpar = (Button) findViewById(R.id.btn_limpar_categoria);        
        
        
        
        Intent it = getIntent();
        
        CategoriaModel categMod = new CategoriaModel(this);
   
      
        categoria = categMod.retornaCategoria(it.getLongExtra("codigo", 1));  
        
        setarValoresCampo(categoria);
        
        
        
        bntAlterar.setOnClickListener(mClickHandler);
        bntExcluir.setOnClickListener(mClickHandler);
        bntLimpar.setOnClickListener(mClickHandler);
        
        
        //dBase.close();
    }


    

	
    private OnClickListener mClickHandler = new OnClickListener() {
    	
    	
    	public void onClick(View v) {    	          		
    		
    		switch (v.getId()) {

    		case R.id.btn_alterar_categoria:
    			
    			if(validaCampos()){
    			    			
	    			categoria = recuperarInformacaoCategoria();
	    			alterarCategoria(categoria);
	    			Toast.makeText(getApplication(), R.string.sucesso_altera_categoria, Toast.LENGTH_LONG).show();
	    			
    			}
    			
    			break;
    		
    		case R.id.btn_excluir_categoria:

    			
    			deletarCategoria();
 		
    			
    			break;

    		case R.id.btn_limpar_categoria:

    	        
    	        
    			categoria = recuperarInformacaoCategoria();
    			limparCategoria(categoria);
    			limparCamposClean();
    			break;	
    			
    			
    		}
    	}
    };    
    
    
    
    
    public void deletarCategoria(){
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
        builder.setMessage("Você tem certeza que quer Excluir essa Categoria?")    
        
            .setCancelable(false)
            .setPositiveButton("YES",
            
            new DialogInterface.OnClickListener() {
                // On
                // clicking
                // "Yes"
                // button

                public void onClick(DialogInterface dialog,int id) {

                	
        			categoria = recuperarInformacaoCategoria();
        			excluirCategoria(categoria);
        			limparCampos();
                	
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
    
	/**
	 * Envia o objeto Categoria para ser validado.
	 * 
	 */
	private void alterarCategoria(Categoria categoria) {

			categoriaNegocio.atualizarCategoria(categoria);

	}
    
	
	private void limparCategoria(Categoria categoria){
		
		
		categoriaNegocio.limparCategoria(categoria);
		
	}	
	
	
	
	private void excluirCategoria(Categoria categoria){
		
		
		categoriaNegocio.excluirCategoria(categoria.getCategoria());
		
	}
	
	/**
	 * Recupera informações da tela e preenche o objeto categoria.
	 * 
	 * @return
	 */
	private Categoria recuperarInformacaoCategoria() {
		
		Categoria categoria = new Categoria(descCategoria.getText().toString(), 
							  Double.parseDouble(valorCategoria.getText().toString()));
		
		Intent it = getIntent();
		
		categoria.setId(it.getLongExtra("codigo", 1));  
		
		return categoria;

	}
	
	/**
	 * Limpa os campos da tela de Categoria.
	 */
	private void limparCampos() {

		Toast.makeText(getApplication(), R.string.sucesso_excluir_categoria, Toast.LENGTH_LONG).show();

		descCategoria.setText("");
		valorCategoria.setText("");

	}
    
	private void limparCamposClean() {

		Toast.makeText(getApplication(), R.string.sucesso_limpar_valor_categoria, Toast.LENGTH_LONG).show();

		valorCategoria.setText("0.00");

	}
	
    
    public void setarValoresCampo(Categoria categoria){
    	
    	descCategoria.setText(categoria.getCategoria());
        valorCategoria.setText(categoria.getValorLimite().toString());
    	
    }
    
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
        //add   
        //quickActions.addQuickAction(new QuickAction(this, R.drawable.search,"Usuário"));
        quickActions.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Adicionar Categoria"));
        quickActions.addQuickAction(new QuickAction(this, R.drawable.icon_edit,"Editar Categoria"));
        
        //edit    
        //quickActionsEdit.addQuickAction(new QuickAction(this, R.drawable.tags_icon,"Categoria"));
        //quickActions.addQuickAction(new QuickAction(this, R.drawable.icon_edit,"Editar Categoria"));
        

        
        final Intent itAddCat = new Intent(this, CriarCategoriaActivity.class);
        final Intent itEditCat = new Intent(this, EditarCategoriaActivity.class);
        
        quickActions.setOnQuickActionClickListener(new OnQuickActionClickListener() {
        	
                       public void onQuickActionClicked(QuickActionWidget widget,int position) {
                    	    
                    	   
                    	   
                    	   switch(position){
                    	   
                    	   case 0:
                    		   
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
        
 
   }	
    
    


	
	public boolean validaCampos(){
		
		boolean sucess = true;
		
		
		if(descCategoria.length() == 0){
			
			sucess = false;
			Toast.makeText(getApplication(), R.string.erro_nome_categoria_null, Toast.LENGTH_LONG).show();
			
		}
		
		if(valorCategoria.length() == 0){
			
			sucess = false;
			Toast.makeText(getApplication(), R.string.erro_valor_limite_null, Toast.LENGTH_LONG).show();
			
		}		
		
	
		
		return sucess;
		
		
	}	
	
    
}
