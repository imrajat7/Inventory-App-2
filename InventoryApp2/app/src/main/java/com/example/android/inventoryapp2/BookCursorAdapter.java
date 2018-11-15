package com.example.android.inventoryapp2;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp2.data.BookContract.BookEntry;

public class BookCursorAdapter extends CursorAdapter {

    public BookCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = view.findViewById(R.id.name_text_view);
        TextView priceTextView = view.findViewById(R.id.price_text_view);
        TextView quantityTextView = view.findViewById(R.id.quantity_text_view);
        Button saleButton = view.findViewById(R.id.saleButton);

        final int bookId = cursor.getColumnIndex(BookEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_NAME);
        int priceColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(BookEntry.COLUMN_BOOK_QUANTITY);

        final String productID = cursor.getString(bookId);
        final String bookName = cursor.getString(nameColumnIndex);
        final int bookPrice = cursor.getInt(priceColumnIndex);
        final int bookQuantity = cursor.getInt(quantityColumnIndex);

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity Activity = (MainActivity) context;
                Activity.productSaleCount(Integer.valueOf(productID), Integer.valueOf(bookQuantity));
            }
        });

        nameTextView.setText(bookName);
        priceTextView.setText(String.valueOf(bookPrice));
        quantityTextView.setText(String.valueOf(bookQuantity));
    }
}