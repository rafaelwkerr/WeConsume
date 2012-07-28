package me.com.consurme.dao;

import java.util.ArrayList;

import me.com.consurme.dataBase.CreateDataBase;
import me.com.consurme.dataBase.model.CategoriaModelBD;
import me.com.consurme.dataBase.model.ConsumoModelBD;
import me.com.consurme.entity.Categoria;
import me.com.consurme.entity.Consumo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ConsumoDao {

/*
	private Context context;
	private static ConsumoDao instance;
	private static SQLiteDatabase db;
	private CreateDataBase dBase;
	
	
	public static final String CATEGORIA_DB = "consurme.db";
	
	
	
	public static ConsumoDao getInstance(Context context) {
		
		
		if (instance == null) {
			synchronized (ConsumoDao.class) {

				if (instance == null) {

					instance = new ConsumoDao(context);
					
					db = context.openOrCreateDatabase(
							CreateDataBase.CONSURME_DB, Context.MODE_PRIVATE,
							null);
				}
			}
		}
		return instance;
	}

	public ConsumoDao(Context context) {
		//super();
		
		this.context = context;
	}
	
	
	*//**
	 * Persiste informações de categoria.
	 * 
	 * @param categoria
	 *//*
	public void inserirConsumo(Consumo consumo) {

		ContentValues value = new ContentValues();
		value.put(ConsumoModelBD.ID_CATEGORIA, consumo.getId_categoria());
		value.put (ConsumoModelBD.VALOR_CONSUMO, consumo.getValor_consumo());
		db.insertOrThrow(ConsumoModelBD.CONSUMO, "", value);

	}
	
	
	public ArrayList<Categoria> buscarCategorias() {

		Cursor cursor = db.query(CategoriaModelBD.CATEGORIA, CategoriaModelBD.COLUNAS,
				null, null, null,
				null, null);
		

        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		for (int i=0;i<cursor.getCount();i++) {
			
			Categoria categoria = new Categoria();
			
			cursor.moveToPosition(i);
			
			categoria.setId(cursor.getInt(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.ID_CATEGORIA)));

			categoria.setCategoria(cursor.getString(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.DESCRICAO)));

			categoria.setValorLimite(cursor.getDouble(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.VALOR_LIMITE)));
			
			categorias.add(categoria);
			
			//i++;

		}
		
		return categorias;
	}
	
	
	*/
	
	
	
}
