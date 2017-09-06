package com.example.ldhns.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ldhns on 2017/9/5.
 */

public class SelectCity extends Activity implements View.OnClickListener{
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_selectCity_back:
                finish();
                break;
            default:
                break;
        }
    }
}
