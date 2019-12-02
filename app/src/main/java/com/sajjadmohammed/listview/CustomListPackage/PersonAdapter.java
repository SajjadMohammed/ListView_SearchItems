package com.sajjadmohammed.listview.CustomListPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sajjadmohammed.listview.BuildConfig;
import com.sajjadmohammed.listview.R;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter {

    private Context context;
    private List<PersonModel> personModels;
    private List<PersonModel> baseList;

    public PersonAdapter(Context context, List<PersonModel> personModels) {
        this.context = context;
        this.personModels = personModels;
        baseList=personModels;
    }

    @Override
    public int getCount() {
        return personModels.size();
    }

    @Override
    public Object getItem(int position) {
        return personModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void removeItem(int position){
        personModels.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PersonHolder personHolder;
        View view = convertView;

        if (view == null) {
            personHolder=new PersonHolder();
            view = LayoutInflater.from(context).inflate(R.layout.info_item, parent, false);

           personHolder.personId = view.findViewById(R.id.personId);
            personHolder.personName = view.findViewById(R.id.personName);
            personHolder.personAge = view.findViewById(R.id.personAge);
            personHolder.removePerson=view.findViewById(R.id.removePerson);

           view.setTag(personHolder);
        }
        else {
            personHolder = (PersonHolder) view.getTag();
        }

        personHolder.personId.setText(String.valueOf(personModels.get(position).getId()));
        personHolder.personName.setText(personModels.get(position).getFullName());
        personHolder.personAge.setText(String.valueOf(personModels.get(position).getAge()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),personModels.get(position).getFullName(),Toast.LENGTH_LONG).show();
            }
        });

        personHolder.removePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });

        return view;
    }

    public void searchPerson(String text) {
        personModels = new ArrayList<>();
        if (text.isEmpty()) {
            personModels = baseList;
        } else {
            for (PersonModel item : baseList) {
                if (item.getFullName().toLowerCase().trim().contains(text.toLowerCase().trim())) {
                    personModels.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    class PersonHolder{
       TextView personId,personName,personAge ;
       Button removePerson;
    }

}