package com.bit.vending;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.ConnectivityManager;
import com.bit.async.tasks.GetEventosTask;
import com.bit.async.tasks.GetUsersFromServerTask;
import com.bit.entities.Eventos;
import com.bit.entities.User;
import com.bit.singletons.CacheCollectionSingleton;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by goycolea on 1/10/15.
 */
public class AssetLoader extends AsyncTaskLoader<List<User>> {



    public AssetLoader(Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {


        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        GetUsersFromServerTask users_mediator = new GetUsersFromServerTask(getContext());
        GetEventosTask events_mediator = new GetEventosTask(getContext());
        List<User> list = null;
        try {
            list = (List) users_mediator.execute(new Void[0]).get();
            List<Eventos> eventos = (List) events_mediator.execute(new Void[0]).get();
            CacheCollectionSingleton.getInstance(getContext()).setInMemmoryUsers(new Gson().toJson((Object) list));
            TransactionHashmapCollectionSingleton.getInstance();
            TransactionHashmapCollectionSingleton.eventos = eventos;
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return list;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return list;
        }
    }
}
