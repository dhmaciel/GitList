package com.example.worlo.gitlist.background_task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.worlo.gitlist.activity.MainActivity;
import com.example.worlo.gitlist.model.Item;
import com.example.worlo.gitlist.web_service.ApiClient;
import com.example.worlo.gitlist.web_service.ApiInterface;
import com.example.worlo.gitlist.web_service.RepositoriosWS;

import java.util.List;

/**
 * Created by worlo on 16/03/2017.
 */

public class DownloadRepositorioAsync extends AsyncTask<Integer, Void, Void> implements IDownloadRepositorioCallBack {

    private MainActivity activity;
    private final String TAG = DownloadRepositorioAsync.class.getName();

    public DownloadRepositorioAsync(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.progressLoadRepo.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Integer... params) {

        Integer page = params[0];

        if(page != null){
            ApiInterface apiService =
                    ApiClient.getClient(activity).create(ApiInterface.class);

            new RepositoriosWS(activity, apiService).obterRepositoriosPorPagina(page, this);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    public void onSuccess(List<Item> items) {
        activity.progressLoadRepo.setVisibility(View.GONE);
        activity.onSuccess(items);
    }

    @Override
    public void onError(String msgError) {
        activity.progressLoadRepo.setVisibility(View.GONE);
        activity.onError(msgError);
        Log.e(TAG, msgError);
    }
}
