package com.example.deveemlabs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deveemlabs.R;
import com.example.deveemlabs.interfaces.OnItemClickListener;
import com.example.deveemlabs.model.Post;

import java.util.List;

public class AdapterMainActivity extends RecyclerView.Adapter<AdapterMainActivity.ViewHolder>{

    private List<Post> list;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public AdapterMainActivity(List<Post> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post_holder,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle,textBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.itemClick(getAdapterPosition());
                }
            });
            textTitle = itemView.findViewById(R.id.tv_title);
            textBody = itemView.findViewById(R.id.tv_body);

        }

        public void onBind(Post post) {
            textTitle.setText(post.getTitle());
            textBody.setText(post.getBody());




        }
    }
}
