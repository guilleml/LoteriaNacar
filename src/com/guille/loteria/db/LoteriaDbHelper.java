package com.guille.loteria.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class LoteriaDbHelper extends SQLiteOpenHelper {
	
	private static final int VALUE_OF_TRUE = 1;
	private static final int VALUE_OF_FALSE = 0;
	
	private static final int DATABASE_VERSION = 8;
	private static final String DATABASE_NAME = "loteriaNacar";
	private static final String NUMBERS_TABLE_NAME = "table_users";
	
	private static final String FIELD_NUMERO = "password";
	
	private static final String CREATE_TABLE_NUMBERS = "create table "
		+NUMBERS_TABLE_NAME+" ("+FIELD_NUMERO+" text, primary key ("+FIELD_NUMERO+") )";
	
	/**
	 * 
	 * @param context
	 */
	public LoteriaDbHelper(Context context){
		this(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	private LoteriaDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_NUMBERS);
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	public ArrayList<String> findRemeberedUser(){
//		ArrayList<String> listaNumeros = new ArrayList<String>();
//		SQLiteDatabase db = getReadableDatabase();
//		String[] fields = new String[]{FIELD_NUMERO};
//		Cursor cursor = db.query(NUMBERS_TABLE_NAME, fields,null,null, null, null, null);
//		if(cursor.moveToFirst()){
//			user = new UserDao(cursor.getString(1), cursor.getString(2),
//					getBooleanValueOfInt(cursor.getInt(3)),
//					getBooleanValueOfInt(cursor.getInt(4)));
//			user.setRowId(cursor.getLong(0));
//		}
//		cursor.close();
//		db.close();
//		return user;
//	}
	
//	/**
//	 * 
//	 * @param user
//	 */
//	public void findUser(UserDao user){
//		SQLiteDatabase db = getReadableDatabase();
//		String[] fields = new String[]{FIELD_ROWID,FIELD_REMEMBERME,FIELD_AUTOLOGIN};
//		String clausule = FIELD_EMAIL+"=? and "+FIELD_PASSWORD+"=?";
//		String[] clausuleValues = new String[]{user.getEmail(),user.getPassword()};
//		Cursor cursor = db.query(USER_TABLE_NAME, fields, clausule,clausuleValues, null, null, null);
//		if(cursor.moveToFirst()){
//			user.setRowId(cursor.getLong(0));
//			user.setRememberme(getBooleanValueOfInt(cursor.getInt(1)));
//			user.setAutologin(getBooleanValueOfInt(cursor.getInt(2)));
//		}
//		cursor.close();
//		db.close();
//	}
	
//	/**
//	 * 
//	 * @param user
//	 */
//	public void createUser(UserDao user){
//		if(user.isRememberme()){
//			clearRemeberMeUsers();
//		}
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues values = new ContentValues();
//		values.put(FIELD_EMAIL, user.getEmail());
//		values.put(FIELD_PASSWORD,user.getPassword());
//		values.put(FIELD_REMEMBERME,getIntValueOfBoolean(user.isRememberme()));
//		long rowId = db.insert(USER_TABLE_NAME, null, values);
//		user.setRowId(rowId);
//		db.close();
//	}
	
	
	/**
	 * 
	 * @param user
	 */
	public void findNumeros(ArrayList<String> numeros){
		SQLiteDatabase db = getReadableDatabase();
		String[] fields = new String[]{FIELD_NUMERO};
		
		Cursor cursor = db.query(NUMBERS_TABLE_NAME, fields, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				String numero = cursor.getString(0);
				if(numero.length()>0)
					numeros.add(numero);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
	}
	
	
	/**
	 * 
	 * @param userId
	 * @param card
	 */
	public void addNumero(String num){
			SQLiteDatabase db = getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(FIELD_NUMERO, num);
			long rowid = db.insert(NUMBERS_TABLE_NAME, null, values);
			db.close();
		
	}
	
	
	/**
	 * 
	 * @param cardId
	 */
	public void deleteNumero(String numero){
		SQLiteDatabase db = getWritableDatabase();
		String clausule = FIELD_NUMERO+"=?";
		String[] clausuleValues = new String[]{numero};
		db.delete(NUMBERS_TABLE_NAME, clausule, clausuleValues);
		db.close();
	}
	
//	
//	private CardDao findCard(String cardNumber){
//		CardDao card = null;
//		SQLiteDatabase db = getReadableDatabase();
//		String[] fields = new String[]{FIELD_ROWID};
//		String clausule = "cardnumber=?";
//		String[] clausuleValues = new String[]{cardNumber};
//		Cursor cursor = db.query(CARD_TABLE_NAME, fields, clausule,clausuleValues , null, null, null);
//		if(cursor.moveToFirst()){
//			card = new CardDao(cursor.getLong(0),null,null);
//		}
//		cursor.close();
//		db.close();
//		return card;
//	}
	
	
	private int getIntValueOfBoolean(boolean booleanValue){
		return booleanValue?VALUE_OF_TRUE:VALUE_OF_FALSE;
	}
	
	private boolean getBooleanValueOfInt(int intValue){
		return VALUE_OF_TRUE == intValue;
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
