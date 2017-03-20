package com.example.worlo.gitlist.web_service;

import android.content.Context;
import android.util.Log;

import com.example.worlo.gitlist.background_task.IDownloadRepositorioCallBack;
import com.example.worlo.gitlist.model.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by worlo on 16/03/2017.
 */

public class RepositoriosWS {

    private final String TAG = RepositoriosWS.class.getName();
    private ApiInterface apiService;
    private Context context;

    public RepositoriosWS(Context context, ApiInterface apiService) {
        this.apiService = apiService;
        this.context = context;
    }

    /**
     * Busca a pagina com os repositorios.
     * @param pagina
     */
    public void obterRepositoriosPorPagina(int pagina, final
    IDownloadRepositorioCallBack callBack){

        Map<String, Integer> page = new HashMap<>();
        page.put("page", pagina);

        Call<Repository> call = apiService.getRepositoryPage(page);

        call.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                final Repository repositorios = response.body();
                if(repositorios != null){
                    callBack.onSuccess(repositorios.getItems());
                }
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }
}
