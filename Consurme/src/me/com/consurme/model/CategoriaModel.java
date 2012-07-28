package me.com.consurme.model;


import java.util.ArrayList;

import me.com.consurme.dao.CategoriaDao;
import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.entity.Categoria;
import android.content.Context;


public class CategoriaModel {
	
	private CreateDataBase dBase;
	private Context context;
	private CategoriaDao categoriaDao;

	public CategoriaModel(Context context) {
		
		this.context = context;
		
	}
	
	
	public void adicionarCategoria(Categoria categoria){
			
		//categoriaDao = CategoriaDao.getInstance(context);
		
		dBase = new CreateDataBase(context);
		
		dBase.inserirCategoria(categoria, context);
		
		
	}
	
	
	public ArrayList<Categoria> buscarTodasCategorias(){
		
		dBase = new CreateDataBase(context);
		
		//categoriaDao = CategoriaDao.getInstance(context);
		return dBase.consultarTodasCategorias(context);
		
	}
	
	
	public Categoria retornaCategoria(Long id){
	
		dBase = new CreateDataBase(context);
		
		return dBase.buscaCategoriaId(id, context);
		
	}
	
	
	public void atualizarCategoria(Categoria categoria){
		
		dBase = new CreateDataBase(context);
		
		dBase.atualizarCategoria(categoria, context);
		
	}
	
	public void excluirCategoria(String nomeCategoria){
		
		dBase = new CreateDataBase(context);
		
		dBase.deletarCategoria(nomeCategoria, context);
		
	}

	
	
	public void limparCategoria(Categoria categoria){
		
		dBase = new CreateDataBase(context);
		
		dBase.limparValorCategoria(categoria, context);
		
	}
	
	
	
}
