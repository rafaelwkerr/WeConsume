package me.com.consurme.dataBase;


import java.util.ArrayList;

import me.com.consurme.dataBase.model.CategoriaModelBD;
import me.com.consurme.dataBase.model.ConsumoModelBD;
import me.com.consurme.entity.Categoria;
import me.com.consurme.entity.Consumo;
import me.com.consurme.util.FormatarCampos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * Cria o modelo do banco de dados Categoria
 * 
 * @author Rafa Kerr
 * 
 */
public class CreateDataBase {
	
	
	public static final String TABELA_CATEGORIA = "categoria";

	public static final String COLUNA_ID_CATEGORIA = "id_categoria";
	public static final String COLUNA_DESCRICAO_CATEGORIA = "descricao";
	public static final String COLUNA_VALOR_CATEGORIA = "valor_limite";
	
	public static final String TABELA_CONSUMO = "consumo";

	public static final String COLUNA_ID_CONSUMO = "id_consumo";
	public static final String COLUNA_VALOR_CONSUMO = "valor_consumo";
	public static final String COLUNA_ID_CATEGORIA_CONSUMO = "id_categoria";


	private static final String CATEGORIA_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_CATEGORIA + "  (" + COLUNA_ID_CATEGORIA
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUNA_VALOR_CATEGORIA + " FLOAT (15,2) NOT NULL, "
			+ COLUNA_DESCRICAO_CATEGORIA + " VARCHAR not null);";

	private static final String CONSUMO_CREATE_TABLE = "CREATE TABLE "
			+ TABELA_CONSUMO + "  (" + COLUNA_ID_CONSUMO
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_VALOR_CONSUMO
			+ " FLOAT (15,2) NOT NULL," + COLUNA_ID_CATEGORIA_CONSUMO
			+ " INTEGER NOT NULL," 
			+ " FOREIGN KEY ( " + COLUNA_ID_CATEGORIA_CONSUMO
			+ " )  REFERENCES " + TABELA_CATEGORIA + " (" + COLUNA_ID_CATEGORIA
			+ " ) ON DELETE RESTRICT ON UPDATE CASCADE);";

	private static final String TAG = "DbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static final String DB_NAME = "consurme";
	private static final int DATABASE_VERSION = 1;

	private final Context mCtx;
	
	
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
			if (!db.isReadOnly()) {
				db.execSQL("PRAGMA foreign_keys=ON;");
			}
		}

		DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(CATEGORIA_CREATE_TABLE);
			db.execSQL(CONSUMO_CREATE_TABLE);

			// Insere valores iniciais na tabela CATEGORIA
			ContentValues values = new ContentValues();
			values.put(COLUNA_DESCRICAO_CATEGORIA, "Roupas");
			db.insert(TABELA_CATEGORIA, null, values);
			values.clear();
			values.put(COLUNA_DESCRICAO_CATEGORIA, "Compras do Mês");
			db.insert(TABELA_CATEGORIA, null, values);
			values.clear();
			values.put(COLUNA_DESCRICAO_CATEGORIA, "Livros");
			db.insert(TABELA_CATEGORIA, null, values);

			//Log.w("DbAdapter", "DB criado com sucesso!");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*			Log.w(TAG, "Atualizando o banco de dados da versão " + oldVersion
					+ " para " + newVersion
					+ ", todos os dados serão perdidos!");*/
			db.execSQL("DROP TABLE IF EXISTS " + TABELA_CATEGORIA);
			db.execSQL("DROP TABLE IF EXISTS " + CONSUMO_CREATE_TABLE);
			onCreate(db);
		}
	}
	
	

	public CreateDataBase(Context context) {
		
		this.mCtx = context;

	}


	public CreateDataBase open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
		mDb.close();
	}	
	
	
	
	public void inserirCategoria(Categoria categoria, Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		ContentValues value = new ContentValues();
		value.put(CategoriaModelBD.DESCRICAO, categoria.getCategoria());
		value.put (CategoriaModelBD.VALOR_LIMITE, categoria.getValorLimite());
		mDb.insertOrThrow(CategoriaModelBD.CATEGORIA, null, value);
		
		mDb.close();

	}
	
	
	/**
	 * Atualiza uma categoria.
	 * 
	 * @param categoria
	 */
	public void atualizarCategoria(Categoria categoria, Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		Long idCategoria = categoria.getId();
		ContentValues values = new ContentValues();
		String[] whereArgs = new String[] { idCategoria.toString() };
		String where = CategoriaModelBD.ID_CATEGORIA + "=?";

		values.put(CategoriaModelBD.DESCRICAO, categoria.getCategoria());
		values.put(CategoriaModelBD.VALOR_LIMITE, categoria.getValorLimite());
		mDb.update(CategoriaModelBD.CATEGORIA, values, where, whereArgs);
		
		mDb.close();

	}
	
	/**
	 * Atualiza uma categoria.
	 * 
	 * @param categoria
	 */
	public void limparValorCategoria(Categoria categoria, Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
        //Long idCategoria = categoria.getId();
		ContentValues values = new ContentValues();
		String[] whereArgs = new String[] { categoria.getCategoria() };
		String where = CategoriaModelBD.DESCRICAO + "=?";

		values.put(CategoriaModelBD.DESCRICAO, categoria.getCategoria());
		values.put(CategoriaModelBD.VALOR_LIMITE, "");
		mDb.update(CategoriaModelBD.CATEGORIA, values, where, whereArgs);
		
		mDb.close();

	}

	/**
	 * Deleta um carro
	 * 
	 * @param idCarro
	 */
	public void deletarCategoria(String nomeCategoria, Context context) {
		
		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		mDb.delete(CategoriaModelBD.CATEGORIA,
				   CategoriaModelBD.DESCRICAO + "=" + "'"+nomeCategoria+"'", null);
		
		
		mDb.close();
		
	}
	
	
	public void inserirConsumo(Consumo consumo, Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		ContentValues value = new ContentValues();
		value.put(ConsumoModelBD.ID_CATEGORIA, consumo.getId_categoria());
		value.put (ConsumoModelBD.VALOR_CONSUMO, consumo.getValor_consumo());
		mDb.insertOrThrow(ConsumoModelBD.CONSUMO, null, value);
		
		mDb.close();

	}	
	
	public ArrayList<Categoria> consultarTodasCategorias(Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		//ORDENADO ALFABETICAMENTE POR descricao
		Cursor cursor =  mDb.query(CategoriaModelBD.CATEGORIA, CategoriaModelBD.COLUNAS, null, null, null, null, "descricao ASC");
		
		
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

		}
		
		mDb.close();
		cursor.close();
		
		return categorias;
		
	}
	

	public Categoria buscaCategoriaId(long idCategoria, Context context) {

		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		
		Cursor cursor = 
			
			mDb.query(true, CategoriaModelBD.CATEGORIA, CategoriaModelBD.COLUNAS, COLUNA_ID_CATEGORIA + "=?",
					new String[] { String.valueOf(idCategoria) }, null, null, null,
					null);
		
		
        Categoria categoria = new Categoria();
        
		if (cursor != null) {
			
			cursor.moveToFirst();
			
			categoria.setId(cursor.getInt(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.ID_CATEGORIA)));

			categoria.setCategoria(cursor.getString(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.DESCRICAO)));

			categoria.setValorLimite(cursor.getDouble(cursor
					.getColumnIndexOrThrow(CategoriaModelBD.VALOR_LIMITE)));

		
		}
		
		mDb.close();
		cursor.close();
		
		return categoria;
		
	}	
	
		
		
		
	public String valorLimiteCategoria(long idCategoria, Context context) {
		
		
		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		
		Cursor mCursor =

		mDb.query(true, CategoriaModelBD.CATEGORIA, CategoriaModelBD.COLUNAS, COLUNA_ID_CATEGORIA + "=?",
				new String[] { String.valueOf(idCategoria) }, null, null, null,
				null);
		
		
		if (mCursor != null) {
		
			
			mCursor.moveToFirst();
		
			float valorLimite = mCursor.getFloat(mCursor.getColumnIndexOrThrow(CategoriaModelBD.VALOR_LIMITE));
			
			mDb.close();
			mCursor.close();
			
			
			return FormatarCampos.formatarFloat(valorLimite);
					
		}
		
		mDb.close();
		
		return null;
		
	}
	
	
	public String valorTotalConsumoCategoria(long idCategoria, Context context) {
		
		
		mDb =   context.openOrCreateDatabase(
				DB_NAME, Context.MODE_PRIVATE,
				null);
		
		
		Cursor mCursor = 

		mDb.query(true, ConsumoModelBD.CONSUMO, ConsumoModelBD.COLUNAS, COLUNA_ID_CATEGORIA_CONSUMO + "=?",
				new String[] { String.valueOf(idCategoria) }, null, null, null,
				null);
		
		
		if (mCursor != null) {
		
			
			float valorTotal;
			float soma = 0;
			
			for (int i=0;i < mCursor.getCount();i++) {
				
				mCursor.moveToPosition(i);
				
				valorTotal = mCursor.getFloat(mCursor.getColumnIndexOrThrow(ConsumoModelBD.VALOR_CONSUMO));
				
				soma = soma + valorTotal;
				
			}
			
			mDb.close();
			mCursor.close();
			
						
			return FormatarCampos.formatarFloat(soma);
					
		}
		
		mDb.close();
		return null;
		
	}	
	
	
	
	
	
	
	
	
	
	

}
