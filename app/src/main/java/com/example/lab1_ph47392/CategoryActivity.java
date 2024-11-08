package com.example.lab1_ph47392;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1_ph47392.adapter.CategoryAdapter;
import com.example.lab1_ph47392.dao.CatDAO;
import com.example.lab1_ph47392.models.CatDTO;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private ListView listView;
    private CatDAO catDAO;
    private CategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        listView = findViewById(R.id.listView);
        catDAO = new CatDAO(this);
        catDAO.open();

        List<CatDTO> categories = catDAO.getAllCats();
        adapter = new CategoryAdapter(this, categories);
        listView.setAdapter(adapter);
        catDAO.close();
    }
}
