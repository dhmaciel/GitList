package com.example.worlo.gitlist.background_task;

import com.example.worlo.gitlist.model.PullRequest;

import java.util.List;

/**
 * Created by worlo on 18/03/2017.
 */

public interface IDownloadPullRequestCallBack {

    public void onSuccess(List<PullRequest> pullRequestList);
    public void onError(String msgError);
}
