package me.com.consurme.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Cria a base de dados ou atualiza a Categoria.
 * 
 * @author Rafa Kerr
 * 
 */
public class SQLiteHelper extends SQLiteOpenHelper {
	
	
	private String[] insertScript;
	private String[] scriptSQLCreate;

	SQLiteHelper(Context context, String nomeBanco, int versaoBanco,
			String[] scriptSQLCreate, String[] insertScritp) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.insertScript = insertScritp;
	}


	@Override
	public void onCreate(SQLiteDatabase db) {

		// Criação das tabelas do DB.
		for (String script : scriptSQLCreate) {
			db.execSQL(script);
			//db.close();
		}
		
		// Inserção dos registros principais.
		for (String insert : insertScript) {
			db.execSQL(insert);
			//db.close();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		
		
		//db.close();
	}
	
	
	
	
	
	
	
	

}
