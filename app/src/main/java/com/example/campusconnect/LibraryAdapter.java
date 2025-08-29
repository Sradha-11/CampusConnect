package com.example.campusconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class LibraryAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LibraryBook> bookList;

    public LibraryAdapter(Context context, ArrayList<LibraryBook> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    public LibraryAdapter(List<LibraryBook> books) {
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_library_book, parent, false);
        }

        LibraryBook book = bookList.get(position);

        TextView title = convertView.findViewById(R.id.bookTitle);
        TextView author = convertView.findViewById(R.id.bookAuthor);
        TextView status = convertView.findViewById(R.id.bookStatus);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        status.setText(book.getStatus());

        return convertView;
    }
}
