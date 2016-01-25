package com.bit.utils;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bit.entities.User;
import com.bit.singletons.CacheCollectionSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goycolea on 1/10/15.
 */
public class OfflineUserManager {

    private Context _context;

    public OfflineUserManager(Context context)
    {
        this._context = context;
    }

    public User authenticate(String user_name, String pwd){

        String json = CacheCollectionSingleton.getInstance(this._context).getInMemmoryUsers();

        List<User> users = new Gson().fromJson(json, new TypeToken<ArrayList<User>>() {
        }.getType());

        User user = null;

        if(users!=null) {
            for (User parent : users) {
                if (parent.getRut().trim().equals(user_name.trim()) && parent.getPassword().trim().equals(pwd.trim())) {
                    user =  parent;
                }
            }
        }

        return user;

    }
}
