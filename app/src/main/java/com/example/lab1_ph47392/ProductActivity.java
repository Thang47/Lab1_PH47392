package com.example.lab1_ph47392;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1_ph47392.adapter.ProductAdapter;
import com.example.lab1_ph47392.models.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView listViewProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);

        listViewProducts = findViewById(R.id.listViewProducts);

        // Tạo dữ liệu mẫu
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO(1, "Product 1", 10.99, 1));
        products.add(new ProductDTO(2, "Product 2", 15.49, 1));
        products.add(new ProductDTO(3, "Product 3", 7.99, 2));

        // Khởi tạo adapter và gán vào ListView
        ProductAdapter adapter = new ProductAdapter(this, products);
        listViewProducts.setAdapter(adapter);
    }

}