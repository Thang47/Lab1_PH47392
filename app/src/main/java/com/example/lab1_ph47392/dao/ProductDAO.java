package com.example.lab1_ph47392.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab1_ph47392.database.DatabaseHelper;
import com.example.lab1_ph47392.models.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ProductDAO(Context context) {
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

    // Thêm một sản phẩm mới
    public long insertProduct(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        long result = db.insert("tb_product", null, values);
        db.close();
        return result;
    }

    // Lấy tất cả sản phẩm
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_product", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                int idCat = cursor.getInt(cursor.getColumnIndexOrThrow("id_cat"));
                products.add(new ProductDTO(id, name, price, idCat));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return products;
    }
    // Xoá sản phẩm
    public int deleteProduct(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("tb_product", "id = ?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }
    // cập nhật sản phẩm
    public int updateProduct(ProductDTO product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        int result = db.update("tb_product", values, "id = ?", new String[]{String.valueOf(product.getId())});
        db.close();
        return result;
    }

}


