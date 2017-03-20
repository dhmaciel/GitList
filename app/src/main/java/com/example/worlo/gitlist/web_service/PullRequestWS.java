package com.example.worlo.gitlist.web_service;

import android.content.Context;

import com.example.worlo.gitlist.background_task.IDownloadPullRequestCallBack;
import com.example.worlo.gitlist.background_task.IDownloadRepositorioCallBack;
import com.example.worlo.gitlist.model.PullRequest;
import com.example.worlo.gitlist.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by worlo on 18/03/2017.
 */

public class PullRequestWS {

    private final String TAG = PullRequestWS.class.getName();
    private ApiInterface apiService;
    private Context context;

    public PullRequestWS(ApiInterface apiService, Context context) {
        this.apiService = apiService;
        this.context = context;
    }

    public void obterPullRequestPorCriadorRepositorio(String criador, String repositorio, final
    IDownloadPullRequestCallBack callBack){

        Call<List<PullRequest>> call = apiService.getAllPullRequest(criador, repositorio);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                final List<PullRequest> pullRequestList = response.body();
                callBack.onSuccess(pullRequestList);
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}
