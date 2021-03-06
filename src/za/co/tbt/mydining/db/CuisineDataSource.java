package za.co.tbt.mydining.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CuisineDataSource {
	public static final String TABLE_NAME = "cuisine";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	private String allColumns[] = {COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION}; 
	
	SQLiteDatabase db;
	MyDiningDbOpenHelper dbHelper;
	
	public CuisineDataSource(Context context) {
		dbHelper = new MyDiningDbOpenHelper(context);
	}
	
	public void open() throws SQLException {
	    db = dbHelper.getOpenDatabase();
	}

	public void close() {
	}
	
	public List<DBItem> getAllCuisines() {
	    List<DBItem> cuisines = new ArrayList<DBItem>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	Cuisine cuis = getCuisine(cursor);
	    	cuisines.add(cuis);
	    	cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return cuisines;
	}

	public List<DBItem> searchForCuisines(String selection, String[] args) {
	    List<DBItem> cuisines = new ArrayList<DBItem>();

	    Cursor cursor = db.query(TABLE_NAME,
	        allColumns, selection, args, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Cuisine cuisine = getCuisine(cursor);
	      cuisines.add(cuisine);
	      cursor.moveToNext();
	    }
	    // make sure to close the cursor
	    cursor.close();
	    return cuisines;
	}
	
	private Cuisine getCuisine(Cursor cursor) {
		Cuisine cuisine = new Cuisine(cursor.getString(1));
		cuisine.setId(cursor.getLong(0));
		cuisine.setDescription(cursor.getString(2));
	    return cuisine;
	}
}
