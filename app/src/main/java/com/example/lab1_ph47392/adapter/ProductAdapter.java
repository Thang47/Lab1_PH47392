package com.example.lab1_ph47392.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab1_ph47392.R;
import com.example.lab1_ph47392.models.ProductDTO;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<ProductDTO> {
    public ProductAdapter(Context context, List<ProductDTO> products) {
        super(context, 0, products);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        ProductDTO product = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.textViewProductName);
        TextView priceTextView = convertView.findViewById(R.id.textViewProductPrice);

        nameTextView.setText(product.getName());
        priceTextView.setText("Price: $" + product.getPrice());
        return convertView;
    }
}

