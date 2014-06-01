package com.hwt.hpt.app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.AdapterView;

import com.hwt.hpt.app.database.MyDBhelper;


public class MainActivity extends ListActivity {

    private Spinner countryFrom,countryTo;
    private String strCountryFrom , strCountryTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);



        initDatabase();
        intiWidget();
        initSpinner();
        initMenu();




    }

    private void intiWidget() {

            countryFrom = (Spinner) findViewById(R.id.list_of_countries1);

            countryTo = (Spinner) findViewById(R.id.list_of_countries2);



    }


    private void initMenu() {

       MainMenu mainMenu[] = new MainMenu[]{
               new MainMenu(R.drawable.conversation,"Basic Conversation"),
               new MainMenu(R.drawable.thing,"I would like to..."),
               new MainMenu(R.drawable.place ,"Where is the..."),
               new MainMenu(R.drawable.help,"Assistance/Emergency"),
               new MainMenu(R.drawable.logistic,"Logistics")
       };

        MainMenuAdapter mainMenuAdapter = new MainMenuAdapter(this,R.layout.menu_item,mainMenu);
        setListAdapter(mainMenuAdapter);


    }




    /*
    * Spinner
    *
    * */

    private void initSpinner() {

        final Spinner countryFrom = (Spinner) findViewById(R.id.list_of_countries1);
        final Spinner countryTo = (Spinner) findViewById(R.id.list_of_countries2);


        Country countries[] = new Country[]{
                new Country(R.drawable.brunei_flag, "Brunei"),
                new Country(R.drawable.cambodia_flag,"Cambodia"),
                new Country(R.drawable.china_flag,"china"),
                new Country(R.drawable.indonesia_flag,"Indonesia"),
                new Country(R.drawable.laos_flag,"Laos"),
                new Country(R.drawable.malaysia_flag,"Malaysia"),
                new Country(R.drawable.myanmar_flag,"Myanmar"),
                new Country(R.drawable.philippines_flag,"Philippines"),
                new Country(R.drawable.singapore_flag,"Singapore"),
                new Country(R.drawable.thailand_flag,"Thailand"),
                new Country(R.drawable.vietnam_flag,"Vietnam")
        };


        CountryAdapter countryFromAdapter = new CountryAdapter(this,R.layout.item,countries);
        countryFrom.setAdapter(countryFromAdapter);
        countryFrom.setOnItemSelectedListener(new MyOnItemSelectedListener());


        CountryAdapter countryToAdapter = new CountryAdapter(this,R.layout.item,countries);
        countryTo.setAdapter(countryToAdapter);
        countryTo.setOnItemSelectedListener(new MyOnItemSelectedListener());
    }


    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent,View view,int pos,long id){

            switch (parent.getId()){
                case R.id.list_of_countries1:

                    strCountryFrom = String.valueOf(countryFrom.getSelectedItem());
                    break;
                case R.id.list_of_countries2:
                    strCountryTo = String.valueOf(countryTo.getSelectedItem());
                    break;
                default:
                    break;
            }
        }
        public void onNothingSelected(AdapterView parent){
            //Do noting.
        }
    }


    /*
    *
    * end spinner
    *
    * */


    private void initDatabase() {
        MyDBhelper db;
        db = new MyDBhelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
