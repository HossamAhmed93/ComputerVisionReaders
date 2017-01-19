package SQLLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "OCR";
    // Contacts table name
    private static final String TABLE_ITEM_BARCODE = "ITEM_OCR";
    // Shops Table Columns names
    private static final String KEY_ID = "ID";
    private static final String KEY_ITEM_CODE = "CODE";
    private static final String KEY_ITEM_NAME = "NAME";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEM_BARCODE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_ITEM_CODE + " TEXT," +
                KEY_ITEM_NAME + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM_BARCODE);
        // Creating tables again
        onCreate(db);
    }

    // Adding new item
    public void addItem(ItemOCR ItemOCR) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_CODE, ItemOCR.getCode()); // Item Code
        values.put(KEY_ITEM_NAME, ItemOCR.getName()); // Item Name
        // Inserting Row
        db.insert(TABLE_ITEM_BARCODE, null, values);
        db.close(); // Closing database connection
    }

    // Getting one item
    public ItemOCR getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEM_BARCODE, new String[] { KEY_ID,
                        KEY_ITEM_CODE, KEY_ITEM_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        ItemOCR item = new ItemOCR(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return item
        return item;
    }

    // Getting All Items
    public List<ItemOCR> getAllItems() {
        List<ItemOCR> itemList = new ArrayList<ItemOCR>();
        // Select All Query
        String selectQuery = "SELECT ID, CODE, NAME FROM " + TABLE_ITEM_BARCODE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ItemOCR item = new ItemOCR();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setCode(cursor.getString(1));
                item.setName(cursor.getString(2));
                // Adding item to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        // return item
        return itemList;
    }

    // Getting items Count
    public int getItemsCount() {
        String countQuery = "SELECT * FROM " + TABLE_ITEM_BARCODE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating an item
    public int updateItem(ItemOCR item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ITEM_CODE, item.getCode());
        values.put(KEY_ITEM_NAME, item.getName());
        // updating row
        return db.update(TABLE_ITEM_BARCODE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(item.getId())});
    }

    // Deleting an item
    public void deleteItem(ItemOCR item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ITEM_BARCODE, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();
    }
}