package me.com.consurme.model;

import java.util.ArrayList;


import me.com.consurme.dao.ConsumoDao;
import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.entity.Categoria;
import me.com.consurme.entity.Consumo;
import android.content.Context;

public class ConsumoModel {

	
	private CreateDataBase dBase;
	private Context context;
	private ConsumoDao consumoDao;

	public ConsumoModel(Context context) {
		
		//super();
		this.context = context;
		
	}
	
	
	public void adicionarConsumo(Consumo consumo){
		
		
		//consumoDao = ConsumoDao.getInstance(context);
		
		
		dBase = new CreateDataBase(context);		
		
		dBase.inserirConsumo(consumo, context);
		
		
	}
	
	
	public String totalConsumoCategoria(Categoria categoria){
		
		
		dBase = new CreateDataBase(context);		
		
		String valorLimite = dBase.valorLimiteCategoria(categoria.getId(), context);
		
		
		//dBase.close();
		
		return valorLimite ;
		
	}
	
	
	public String totalValorConsumo(Categoria categoria){
		
		
		dBase = new CreateDataBase(context);		
		
		String valorTotalConsumo = dBase.valorTotalConsumoCategoria(categoria.getId(), context);
		
		return valorTotalConsumo ;
		
	}	
	
	
	
	
}
