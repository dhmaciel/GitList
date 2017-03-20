package com.example.worlo.gitlist.background_task;

import com.example.worlo.gitlist.model.Item;

import java.util.List;

/**
 * Created by worlo on 17/03/2017.
 */

public interface IDownloadRepositorioCallBack {

    public void onSuccess(List<Item> items);
    public void onError(String msgError);
}
