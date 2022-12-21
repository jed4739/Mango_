package com.example.mango_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mango_.databinding.Rv_item;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private Rv_item binding;
    private Context context;
    private List list;
    private ItemClick itemClick;

    public RVAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    public final ItemClick getItemClick() {
        return itemClick;
    }

    public final void setItemClick(@Nullable ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindItems(@NonNull ContentsModel item) {
            TextView rv_text = itemView.findViewById(R.id.rvTextArea);
            ImageView rv_img = itemView.findViewById(R.id.rvImageArea);

            rv_text.setText(item.getTitleText());
            Glide.with(context)
                    .load(item.getImageUrl())
                    .into(rv_img);
        }
    }

    @NonNull
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RVAdapter.ViewHolder holder, int position) {
        if (itemClick != null) {
            holder.itemView.setOnClickListener(v -> itemClick.onClick(v, position));
        }
        holder.bindItems((ContentsModel) list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
