package com.example.worlo.gitlist.web_service;

import com.example.worlo.gitlist.model.PullRequest;
import com.example.worlo.gitlist.model.Repository;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by worlo on 16/03/2017.
 */

public interface ApiInterface {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<Repository> getRepositoryPage(@QueryMap Map<String, Integer> page);

    @GET("repos/{criador}/{repositorio}/pulls")
    Call<List<PullRequest>> getAllPullRequest(@Path("criador") String criador,
                                              @Path("repositorio") String repositorio);
}
