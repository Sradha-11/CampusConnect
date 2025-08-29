package com.example.campusconnect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class LostFoundAdapter extends RecyclerView.Adapter<LostFoundAdapter.ViewHolder> {

    private Context context;
    private List<LostFoundItem> itemList;

    public LostFoundAdapter(Context context, List<LostFoundItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public LostFoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lost_found, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LostFoundAdapter.ViewHolder holder, int position) {
        LostFoundItem item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.date.setText("Date: " + item.getDate());
        holder.location.setText("Location: " + item.getLocation());
        holder.status.setText("Status: " + item.getStatus());
        holder.contact.setText("Contact: " + item.getContact());

        // Load image with Glide
        Glide.with(context)
                .load(item.getImageurl())
                .placeholder(R.drawable.ic_image_placeholder) // add a placeholder drawable
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, date, location, status, contact;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lostFoundTitle);
            description = itemView.findViewById(R.id.lostFoundDescription);
            date = itemView.findViewById(R.id.lostFoundDate);
            location = itemView.findViewById(R.id.lostFoundLocation);
            status = itemView.findViewById(R.id.lostFoundStatus);
            contact = itemView.findViewById(R.id.lostFoundContact);
            imageView = itemView.findViewById(R.id.lostFoundImage);
        }
    }
}
