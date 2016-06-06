package com.bit.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bit.client.R;

/**
 * Created by nelson on 30/05/16.
 */
public class TextViewPersonalizado extends TextView {

    public TextViewPersonalizado(Context context) {
        super(context);
    }

    public TextViewPersonalizado(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(context, attrs);
    }

    public TextViewPersonalizado(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(context, attrs);
    }

    public void setTypeface(Context context, AttributeSet attrs){
        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Extra, 0, 0);
        String fuente = fuente = styledAttributes.getString(R.styleable.Extra_fuente);
        styledAttributes.recycle();

        if (fuente != null)
            setTypeface(TypefacesUtils.get(context, fuente));
    }
}