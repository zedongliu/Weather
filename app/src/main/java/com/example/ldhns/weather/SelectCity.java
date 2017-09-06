package com.example.ldhns.weather;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldhns on 2017/9/5.
 */

public class SelectCity extends Activity implements View.OnClickListener{
    private ImageView backBtn;
    private ListView cityListLv;

    private List<City> mCityList;
    private MyApplication myApplication;
    private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);

        myApplication = (MyApplication)getApplication();
        mCityList = myApplication.getCityList();
        mArrayList = new ArrayList<String>();
        for(int i=0;i<mCityList.size();i++){
            String cityName = mCityList.get(i).getCity();
            mArrayList.add(cityName);
        }
        cityListLv = (ListView)findViewById(R.id.selectcity_lv);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);
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
