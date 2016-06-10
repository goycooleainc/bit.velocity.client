package com.bit.utils;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by nelson on 30/05/16.
 */
public class TypefacesUtils {
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    public static Typeface get(Context context, String rutaFuente) {
        synchronized (cache) {
            if (!cache.containsKey(rutaFuente)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), rutaFuente);
                    cache.put(rutaFuente, t);
                } catch (Exception e) {
                    System.out.println("No se ha podido cargar la fuente en '" + rutaFuente
                            + "' porque " + e.getMessage());
                    return null;
                }
            }
            return cache.get(rutaFuente);
        }
    }
}