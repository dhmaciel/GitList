package com.example.worlo.gitlist.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.worlo.gitlist.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by worlo on 18/03/2017.
 */

public class Utils {

    private static final String TAG = Utils.class.getName();

    public static String convertDate(String dataOriginal) {

        String dataConvertida = "";
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            Date data = sdf.parse(dataOriginal);
            dataConvertida = fmt.format(data);

        } catch (Exception ex) {
            Log.e(TAG, "Erro ao converter a data", ex);
        }

        return dataConvertida;
    }

    public static void loadImageCacheOrOnLine(final Context context, final String imgUrl, final ImageView imageView) {
        Picasso.with(context)
                .load(imgUrl)
                .placeholder(R.mipmap.ic_action_device_now_wallpaper)
                .error(R.mipmap.ic_action_device_now_wallpaper)
                .resize(100, 100)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.e("Load Image", "Sucesso OFFLine");
                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(imgUrl)
                                .placeholder(R.mipmap.ic_action_device_now_wallpaper)
                                .error(R.mipmap.ic_action_device_now_wallpaper)
                                .resize(100, 100)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Log.e("Load Image", "Sucesso OnLine");
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e("Picasso", "Could not fetch image");
                                    }
                                });
                    }
                });
    }

    public static boolean isInternetAtiva(Context ctx) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            boolean isAtiva = cm.getActiveNetworkInfo() != null &&
                    cm.getActiveNetworkInfo().isConnectedOrConnecting();

            if (isAtiva) {
                return true;
            }

        } catch (Exception ex) {
            Log.e(TAG, "isInternetAtivaComMsg", ex);
        }

        return false;
    }
}
