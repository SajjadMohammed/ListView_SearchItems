package com.sajjadmohammed.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sajjadmohammed.listview.CustomListPackage.PersonAdapter;
import com.sajjadmohammed.listview.CustomListPackage.PersonModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar mainToolbar;
    Button defaultList,customList;
    ListView languageList;
    ArrayAdapter listSource;

    PersonAdapter personAdapter;
    List<PersonModel> personModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar=findViewById(R.id.mainToolbar);
        setSupportActionBar(mainToolbar);

        defaultList=findViewById(R.id.defaultList);
        customList=findViewById(R.id.customList);

        languageList=findViewById(R.id.languageList);
        listSource=ArrayAdapter.createFromResource(this,R.array.listSource,android.R.layout.simple_list_item_1);


//        languageList.setOnItemClickListener(itemClickListener);
        //languageList.setOnItemLongClickListener(itemLongClickListener);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem searchItem=menu.findItem(R.id.searchItem);

        SearchView searchView= (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(searchListener);

        return super.onCreateOptionsMenu(menu);
    }

    SearchView.OnQueryTextListener searchListener=new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            personAdapter.searchPerson(newText);
            return true;
        }
    };

    public void showDefaultList(View view) {
        languageList.setAdapter(listSource);
    }

    ListView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(view.getContext(),(String)languageList.getItemAtPosition(position),Toast.LENGTH_LONG).show();
        }
    };

    ListView.OnItemLongClickListener itemLongClickListener=new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            return false;
        }
    };

    public void showCustomList(View view) {

      fillPersonData();
        personAdapter=new PersonAdapter(this,personModels);
        languageList.setAdapter(personAdapter);
    }

    private void fillPersonData() {
        personModels=new ArrayList<>();
        personModels.add(new PersonModel(1,22,"Sajjad"));
        personModels.add(new PersonModel(2,23,"Ahmed"));
        personModels.add(new PersonModel(3,26,"Suha"));
    }
}