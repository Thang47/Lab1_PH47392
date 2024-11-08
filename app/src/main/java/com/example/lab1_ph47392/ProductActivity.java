package com.example.lab1_ph47392;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1_ph47392.adapter.ProductAdapter;
import com.example.lab1_ph47392.dao.ProductDAO;
import com.example.lab1_ph47392.models.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private ListView listViewProducts;
    private EditText edtProductName, edtProductPrice, edtProductCatId;
    private Button btnAddProduct, btnUpdateProduct, btnDeleteProduct;
    private ProductDAO productDAO;
    private ProductAdapter productAdapter;
    private List<ProductDTO> productList;
    private ProductDTO selectedProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);

        edtProductName = findViewById(R.id.edtProductName);
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductCatId = findViewById(R.id.edtProductCatId);
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        listViewProducts = findViewById(R.id.listViewProducts);

        productDAO = new ProductDAO(this);
        productList = productDAO.getAllProducts();
        productAdapter = new ProductAdapter(this, productList);
        listViewProducts.setAdapter(productAdapter);

        listViewProducts.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy sản phẩm đã chọn
            selectedProduct = productList.get(position);

            // Hiển thị thông tin sản phẩm vào các EditText
            edtProductName.setText(selectedProduct.getName());
            edtProductPrice.setText(String.valueOf(selectedProduct.getPrice()));
            edtProductCatId.setText(String.valueOf(selectedProduct.getId_cat()));
        });
        // thêm sản phầm
        btnAddProduct.setOnClickListener(v -> {
            String productName = edtProductName.getText().toString();
            double productPrice = Double.parseDouble(edtProductPrice.getText().toString());
            int productCatId = Integer.parseInt(edtProductCatId.getText().toString());

            ProductDTO newProduct = new ProductDTO(0, productName, productPrice, productCatId);

            long result = productDAO.insertProduct(newProduct);
            if (result > 0) {
                updateProductList();  // Cập nhật danh sách sản phẩm
            }
        });

        //cập nhật sản phẩm

        btnUpdateProduct.setOnClickListener(v -> {
            if (selectedProduct != null) {
                String productName = edtProductName.getText().toString();
                double productPrice = Double.parseDouble(edtProductPrice.getText().toString());
                int productCatId = Integer.parseInt(edtProductCatId.getText().toString());

                ProductDTO updatedProduct = new ProductDTO(selectedProduct.getId(), productName, productPrice, productCatId);

                int result = productDAO.updateProduct(updatedProduct);
                if (result > 0) {
                    updateProductList();  // Cập nhật danh sách sản phẩm
                }
            }else {
                Toast.makeText(ProductActivity.this,"Vui lòng chọn sản phẩm cần cập nhật",Toast.LENGTH_SHORT).show();
            }
        });

        // Xóa sản phẩm
        btnDeleteProduct.setOnClickListener(v -> {
            if (selectedProduct != null) {
                int result = productDAO.deleteProduct(selectedProduct.getId());
                if (result > 0) {
                    updateProductList();  // Cập nhật danh sách sản phẩm
                }
            }else {
                Toast.makeText(ProductActivity.this, "Vui lòng chọn sản phẩm để xóa", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateProductList() {
        productList.clear();
        productList.addAll(productDAO.getAllProducts());  // Lấy lại danh sách sản phẩm sau khi thay đổi
        productAdapter.notifyDataSetChanged();
    }
}





