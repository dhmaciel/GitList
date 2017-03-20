package com.example.worlo.gitlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worlo.gitlist.R;
import com.example.worlo.gitlist.activity.PullRequestActivity;
import com.example.worlo.gitlist.model.Item;
import com.example.worlo.gitlist.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by worlo on 17/03/2017.
 */

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.RepositorioViewHolder>{

    private final String TAG = RepositorioAdapter.class.getName();
    private List<Item> items;
    private int rowLayout;
    private Context context;

    public static class RepositorioViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAutor;
        TextView txtAutor;
        TextView txtNomeRepositorio;
        TextView txtDescRepositorio;
        TextView txtQtdStar;
        TextView txtQtdFork;

        public RepositorioViewHolder(View itemView) {
            super(itemView);
            imageViewAutor = (ImageView) itemView.findViewById(R.id.img_repo_img_autor);
            txtAutor = (TextView) itemView.findViewById(R.id.txt_repo_autor);
            txtNomeRepositorio = (TextView) itemView.findViewById(R.id.txt_repo_nome_repo);
            txtDescRepositorio = (TextView) itemView.findViewById(R.id.txt_repo_desc_repo);
            txtQtdStar = (TextView) itemView.findViewById(R.id.txt_repo_qtd_star);
            txtQtdFork = (TextView) itemView.findViewById(R.id.txt_repo_qtd_fork);
        }
    }

    public RepositorioAdapter(List<Item> items, int rowLayout, Context context) {
        this.items = items;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RepositorioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RepositorioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositorioViewHolder holder, int position) {
        final Item item = items.get(position);

        Utils.loadImageCacheOrOnLine(context, item.getOwner().getAvatarUrl(), holder.imageViewAutor);

        //loadImage(item.getOwner().getAvatarUrl(), holder.imageViewAutor);
        holder.txtAutor.setText(item.getOwner().getLogin());
        holder.txtNomeRepositorio.setText(item.getName());
        holder.txtDescRepositorio.setText(item.getDescription());
        holder.txtQtdStar.setText(item.getStargazersCount().toString());
        holder.txtQtdFork.setText(item.getForksCount().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PullRequestActivity.class);
                intent.putExtra("item", item);
                context.startActivity(intent);
            }
        });
    }

    private void loadImage(String url, ImageView imageView){
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_action_device_now_wallpaper)
                .error(R.mipmap.ic_action_device_now_wallpaper)
                .resize(100, 100)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
