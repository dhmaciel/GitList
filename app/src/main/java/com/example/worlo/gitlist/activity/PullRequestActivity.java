package com.example.worlo.gitlist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.worlo.gitlist.R;
import com.example.worlo.gitlist.adapter.PullRequestAdapter;
import com.example.worlo.gitlist.adapter.RepositorioAdapter;
import com.example.worlo.gitlist.background_task.DownloadPullRequestsAsync;
import com.example.worlo.gitlist.background_task.IDownloadPullRequestCallBack;
import com.example.worlo.gitlist.model.Item;
import com.example.worlo.gitlist.model.PullRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestActivity extends AppCompatActivity implements IDownloadPullRequestCallBack {

    @BindView(R.id.recycler_view_pull_request)
    public RecyclerView recyclerView;

    @BindView(R.id.progress_load_pull)
    public ProgressBar progressLoadPull;

    private final String TAG = PullRequestActivity.class.getName();
    private Item itemAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            itemAtual = (Item) bundle.get("item");
            setTitle(itemAtual.getName());

            new DownloadPullRequestsAsync(this).execute(itemAtual.getOwner().getLogin(), itemAtual.getName());
        }
    }

    private void popularListagemPullRequests(List<PullRequest> pullRequestList){
        recyclerView.setAdapter(new PullRequestAdapter(pullRequestList, R.layout.list_item_pull_request, this));
    }

    @Override
    public void onSuccess(List<PullRequest> pullRequestList) {
        if(pullRequestList != null && pullRequestList.size() > 0){
            popularListagemPullRequests(pullRequestList);
        }else {
            Toast.makeText(this, getString(R.string.toast_lista_pull_request_vazia), Toast.LENGTH_LONG).show();
            finish();

        }
    }

    @Override
    public void onError(String msgError) {
        Toast.makeText(this, getString(R.string.toast_erro_load_pull_request), Toast.LENGTH_LONG).show();
        Log.e(TAG, msgError);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
