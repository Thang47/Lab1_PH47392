package com.example.lab1_ph47392.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lab1_ph47392.R;
import com.example.lab1_ph47392.models.CatDTO;

import java.util.List;

public class CategoryAdapter extends ArrayAdapter<CatDTO> {

    public CategoryAdapter(Context context, List<CatDTO> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_category, parent, false);
        }

        CatDTO cat = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.categoryName);
        nameTextView.setText(cat.getName());

        return convertView;

    }
}
