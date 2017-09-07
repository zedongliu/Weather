package com.example.ldhns.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText searchEt;
    private Button button;

    private List<City> mCityList;
    private MyApplication myApplication;
    private ArrayList<String> mArrayList;
    ArrayAdapter<String> adapter;

    private String updateCityCode="-1";
    @Override
    protected void onCreate(final Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.select_city);

        //backBtn = (ImageView)findViewById(R.id.title_selectCity_back);
        //backBtn.setOnClickListener(this);

        searchEt = (EditText)findViewById(R.id.selectcity_search);
        button  = (Button)findViewById(R.id.selectcity_search_button);
        button.setOnClickListener(this);

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

        final Intent intent = new Intent(this,MainActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加ListView项的点击事件动作
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateCityCode = mCityList.get(position).getNumber();
                Log.d("update city code",updateCityCode);

                //用Shareperference存储最近一次的citycode
                SharedPreferences sharedPreferences = getSharedPreferences("CityCodePreference",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("citycode",updateCityCode);
                editor.commit();

                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
            }
        };
        //为组件绑定监听
        cityListLv.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
           /* case R.id.title_selectCity_back:
                //finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
                break;*/
            case R.id.selectcity_search_button:
                String searchText = searchEt.getText().toString();
                Log.d("Search",searchText);

                ArrayList<String> mSearchList = new ArrayList<String>();
                 for(int i=0;i<mCityList.size();i++){
                     String No_ = Integer.toString(i+1);
                     String number = mCityList.get(i).getNumber();
                     String provinceName = mCityList.get(i).getProvince();
                     String cityName = mCityList.get(i).getCity();
                     if(number.contains(searchText) || cityName.contains(searchText) || provinceName.contains(searchText)){
                         mSearchList.add("No."+No_+":"+number+"-"+provinceName+"-"+cityName);
                         Log.d("changed adapter data","No."+No_+":"+number+"-"+provinceName+"-"+cityName);
                     }
                     adapter = new ArrayAdapter<String>(SelectCity.this,R.layout.list_item,mSearchList);
                     cityListLv.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
                 }


                /*Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("citycode",searchText);
                startActivity(intent);*/
                break;
            default:
                break;
        }
    }
}
