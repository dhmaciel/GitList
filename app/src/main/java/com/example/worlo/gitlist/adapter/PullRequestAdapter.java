package com.example.worlo.gitlist.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worlo.gitlist.R;
import com.example.worlo.gitlist.model.PullRequest;
import com.example.worlo.gitlist.util.Utils;

import java.util.List;

/**
 * Created by worlo on 18/03/2017.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>{

    private final String TAG = PullRequestAdapter.class.getName();
    private List<PullRequest> pullRequests;
    private int rowLayout;
    private Context context;

    public static class PullRequestViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAutor;
        TextView txtAutor;
        TextView txtTituloPullRequest;
        TextView txtBodyPullRequest;
        TextView txtDataPullRequest;

        public PullRequestViewHolder(View itemView) {
            super(itemView);
            imageViewAutor = (ImageView) itemView.findViewById(R.id.img_pull_img_autor);
            txtAutor = (TextView) itemView.findViewById(R.id.txt_pull_autor);
            txtTituloPullRequest = (TextView) itemView.findViewById(R.id.txt_pull_title);
            txtBodyPullRequest = (TextView) itemView.findViewById(R.id.txt_pull_body);
            txtDataPullRequest = (TextView) itemView.findViewById(R.id.txt_pull_data);
        }
    }

    public PullRequestAdapter(List<PullRequest> pullRequests, int rowLayout, Context context) {
        this.pullRequests = pullRequests;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new PullRequestAdapter.PullRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PullRequestViewHolder holder, int position) {
        final PullRequest pullRequest = pullRequests.get(position);

        Utils.loadImageCacheOrOnLine(context, pullRequest.getUser().getAvatarUrl(), holder.imageViewAutor);

        holder.txtAutor.setText(pullRequest.getUser().getLogin());
        holder.txtTituloPullRequest.setText(pullRequest.getTitle());
        holder.txtBodyPullRequest.setText(pullRequest.getBody());
        holder.txtDataPullRequest.setText(Utils.convertDate(pullRequest.getUpdatedAt()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!pullRequest.getHtmlUrl().equals("")){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pullRequest.getHtmlUrl()));
                    context.startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
