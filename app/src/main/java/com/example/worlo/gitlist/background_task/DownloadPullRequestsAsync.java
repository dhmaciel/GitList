package com.example.worlo.gitlist.background_task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.worlo.gitlist.activity.PullRequestActivity;
import com.example.worlo.gitlist.model.PullRequest;
import com.example.worlo.gitlist.web_service.ApiClient;
import com.example.worlo.gitlist.web_service.ApiInterface;
import com.example.worlo.gitlist.web_service.PullRequestWS;

import java.util.List;

/**
 * Created by worlo on 18/03/2017.
 */

public class DownloadPullRequestsAsync extends AsyncTask<String, Void, Void> implements IDownloadPullRequestCallBack {

    private PullRequestActivity activity;
    private final String TAG = DownloadPullRequestsAsync.class.getName();

    public DownloadPullRequestsAsync(PullRequestActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoadPull.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(String... strings) {

        String criador = strings[0];
        String repositorio = strings[1];

        ApiInterface apiService =
                ApiClient.getClient(activity).create(ApiInterface.class);

        new PullRequestWS(apiService, activity).obterPullRequestPorCriadorRepositorio(criador, repositorio, this);

        return null;
    }

    @Override
    public void onSuccess(List<PullRequest> pullRequestList) {
        activity.progressLoadPull.setVisibility(View.GONE);
        activity.onSuccess(pullRequestList);
    }

    @Override
    public void onError(String msgError) {
        activity.progressLoadPull.setVisibility(View.GONE);
        activity.onError(msgError);
        Log.e(TAG, msgError);
    }
}
