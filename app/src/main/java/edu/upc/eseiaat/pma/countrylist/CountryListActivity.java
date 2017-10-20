package edu.upc.eseiaat.pma.countrylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class CountryListActivity extends AppCompatActivity {

    private ArrayList<String> country_list;
    private ArrayAdapter<String> adapter;
    private String title = "";
    private TextView title_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        final String[] countries = getResources().getStringArray(R.array.countries);
        country_list = new ArrayList<>(Arrays.asList(countries));

        ListView list = (ListView) findViewById(R.id.country_list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, country_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        CountryListActivity.this,
                        String.format(getString(R.string.chosen) + " %s", country_list.get(position)),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                /*AlertDialog.Builder builder = new AlertDialog.Builder(CountryListActivity.this);
                String msg = getResources().getString(R.string.sure);
                builder.setMessage(msg + " " + country_list.get(position) + "?");

                builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country_list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton(android.R.string.cancel,null);
                builder.create().show();*/

                Intent intent = new Intent(CountryListActivity.this, EditCountryList.class);
                intent.putExtra("country", country_list.get(position));
                intent.putExtra("pos", position);
                startActivityForResult(intent, 0);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case 0:
            if (resultCode == AppCompatActivity.RESULT_OK) {
                int pos = data.getIntExtra("pos", -1);
                country_list.set(pos, data.getStringExtra("country"));
            }
        }
        adapter.notifyDataSetChanged();
    }
}
