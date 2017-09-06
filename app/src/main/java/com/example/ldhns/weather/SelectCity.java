package com.example.ldhns.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

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
    ArrayAdapter<String> adapter;

    private String updateCityCode="-1";
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.select_city);

        backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);

        myApplication = (MyApplication)getApplication();
        mCityList = myApplication.getCityList();
        mArrayList = new ArrayList<String>();//不new会指向空
        for(int i=0;i<mCityList.size();i++){
            String No_ = Integer.toString(i+1);
            String number= mCityList.get(i).getNumber();
            String provinceName = mCityList.get(i).getProvince();
            String cityName = mCityList.get(i).getCity();
            //mArrayList.add(cityName);
            mArrayList.add("NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
            Log.d("cityName",cityName);
        }

        cityListLv = (ListView)findViewById(R.id.selectcity_listview);
        adapter = new ArrayAdapter<String>(SelectCity.this,R.layout.list_item,mArrayList);
        adapter.notifyDataSetChanged();
        cityListLv.setAdapter(adapter);

        //添加ListView项的点击事件动作
        //为组件绑定监听
        cityListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCityCode = mCityList.get(position).getNumber();
                //Toast.makeText(SelectCity.this,updateCityCode,Toast.LENGTH_LONG).show();
                Log.d("update city code",updateCityCode);
            }
        });
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.title_selectCity_back:
                //finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
