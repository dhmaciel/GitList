package com.example.worlo.gitlist.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.worlo.gitlist.R;
import com.example.worlo.gitlist.adapter.RepositorioAdapter;
import com.example.worlo.gitlist.background_task.DownloadRepositorioAsync;
import com.example.worlo.gitlist.background_task.IDownloadRepositorioCallBack;
import com.example.worlo.gitlist.model.Item;
import com.example.worlo.gitlist.util.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IDownloadRepositorioCallBack{

    @BindView(R.id.recycler_view_repositorios)
    public RecyclerView recyclerView;

    @BindView(R.id.progress_load_repo)
    public ProgressBar progressLoadRepo;

    private final String TAG = MainActivity.class.getName();
    private EndlessRecyclerViewScrollListener scrollListener;
    private List<Item> itemsList;
    private RepositorioAdapter repositorioAdapter;
    private final int initialPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setTitle(getString(R.string.app_name) + " " + getString(R.string.title_java));

        itemsList = new ArrayList<>();
        repositorioAdapter = new RepositorioAdapter(itemsList, R.layout.list_item_repositorio, this);
        recyclerView.setAdapter(repositorioAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        scrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                //loadNextDataFromApi(page);

                new DownloadRepositorioAsync(MainActivity.this).execute(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        new DownloadRepositorioAsync(MainActivity.this).execute(initialPage);
    }

    private void popularListagemRepositorios(int count){
        repositorioAdapter.notifyItemRangeInserted(count, itemsList.size() - 1);
    }

    @Override
    public void onSuccess(List<Item> items) {
        itemsList.addAll(items);
        int curSize = repositorioAdapter.getItemCount();
        popularListagemRepositorios(curSize);
    }

    @Override
    public void onError(String msgError) {
        Toast.makeText(this, getString(R.string.toast_erro_load_repositorios), Toast.LENGTH_LONG).show();
        Log.e(TAG, msgError);
    }

}
