package com.example.lab1_ph47392.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1_ph47392.database.DatabaseHelper;
import com.example.lab1_ph47392.models.CatDTO;

import java.util.ArrayList;
import java.util.List;

public class CatDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CatDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Mở kết nối database
    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    // Đóng kết nối database
    public void close() {
        dbHelper.close();
    }

    // Thêm một danh mục mới
    public long insertCat(CatDTO cat) {
        ContentValues values = new ContentValues();
        values.put("name", cat.getName());
        return db.insert("tb_cat", null, values);
    }

    // Lấy tất cả danh mục
    public List<CatDTO> getAllCats() {
        List<CatDTO> catList = new ArrayList<>();
        Cursor cursor = db.query("tb_cat", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                catList.add(new CatDTO(id, name));
            }
            cursor.close();
        }
        return catList;
    }

    // Xóa danh mục
    public int deleteCat(int id) {
        return db.delete("tb_cat", "id = ?", new String[]{String.valueOf(id)});
    }

    // Cập nhật danh mục
    public int updateCat(CatDTO cat) {
        ContentValues values = new ContentValues();
        values.put("name", cat.getName());
        return db.update("tb_cat", values, "id = ?", new String[]{String.valueOf(cat.getId())});
    }
}
