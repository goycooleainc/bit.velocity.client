package com.bit.entities;

import java.util.List;

/**
 * Created by nicolasmarin on 02-06-13.
 */
public class LiderList
{
    List<Lider> lideres;
    String error;

    public List<Lider> getLideres() {
        return lideres;
    }

    public void setLideres(List<Lider> lideres) {
        this.lideres = lideres;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
