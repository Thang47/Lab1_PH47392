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
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        return db.insert("tb_product", null, values);
    }

    // Lấy tất cả sản phẩm
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productList = new ArrayList<>();
        Cursor cursor = db.query("tb_product", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                int id_cat = cursor.getInt(cursor.getColumnIndexOrThrow("id_cat"));
                productList.add(new ProductDTO(id, name, price, id_cat));
            }
            cursor.close();
        }
        return productList;
    }

    // Xóa sản phẩm
    public int deleteProduct(int id) {
        return db.delete("tb_product", "id = ?", new String[]{String.valueOf(id)});
    }

    // Cập nhật sản phẩm
    public int updateProduct(ProductDTO product) {
        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("id_cat", product.getId_cat());
        return db.update("tb_product", values, "id = ?", new String[]{String.valueOf(product.getId())});
    }
}


