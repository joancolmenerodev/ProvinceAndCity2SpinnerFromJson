package com.example.joancolmenero.provinceandcity2spinnersfromjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.joancolmenero.provinceandcity2spinnersfromjson.model.City;
import com.example.joancolmenero.provinceandcity2spinnersfromjson.model.ResponseJSON;
import com.example.joancolmenero.provinceandcity2spinnersfromjson.network.ApiClient;
import com.example.joancolmenero.provinceandcity2spinnersfromjson.network.ApiInterface;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiService;
    private Spinner spinner_province;
    private Spinner spinner_city;
    private ArrayAdapter<String> provinceAdapter;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> provincesList = new ArrayList<>();
    private ArrayList<String> citiesList = new ArrayList<>();
    private Map<String, ResponseJSON> responseJson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Map<String, ResponseJSON>> call = apiService.getProvinceAndCity();
        call.enqueue(new Callback<Map<String, ResponseJSON>>() {
            @Override
            public void onResponse(Call<Map<String, ResponseJSON>>call, Response<Map<String, ResponseJSON>> response) {
                responseJson  = response.body();
                for(Map.Entry<String, ResponseJSON> e : responseJson.entrySet())
                {
                    provincesList.add(e.getKey());
                    for(City c : e.getValue().getCity()){
                        citiesList.add(c.getCityName());
                    }
                }
                createAdapter();
            }

            @Override
            public void onFailure(Call<Map<String, ResponseJSON>>call, Throwable t) {

            }
        });
    }

    private void initUI() {
        spinner_city = findViewById(R.id.spinner_cities);
        spinner_province = findViewById(R.id.spinner_provinces);
    }

    private void createAdapter(){

        provinceAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, provincesList);

        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_province.setAdapter(provinceAdapter);
        cityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, citiesList);

        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(cityAdapter);

        spinner_province.setOnItemSelectedListener(provinceListener);


    }

    private AdapterView.OnItemSelectedListener provinceListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            citiesList.clear();
            String provinceSelected = spinner_province.getItemAtPosition(position).toString();
            for(Map.Entry<String, ResponseJSON> e : responseJson.entrySet())
            {
                if(e.getKey().equals(provinceSelected)){
                    for(City c : e.getValue().getCity()){
                        citiesList.add(c.getCityName());
                    }
                }
            }
            cityAdapter.notifyDataSetChanged();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


}
