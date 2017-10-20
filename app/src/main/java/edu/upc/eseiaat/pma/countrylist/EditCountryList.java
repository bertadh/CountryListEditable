package edu.upc.eseiaat.pma.countrylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditCountryList extends AppCompatActivity {

    private EditText edit_country;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_country_list);

        // (II)
        Intent intent = getIntent();
        String country = intent.getStringExtra("country");
        pos = intent.getIntExtra("pos", -1);
        edit_country = (EditText)findViewById(R.id.newCountryName);
        edit_country.setText(country);
    }

    public void saveCounty(View view) {
        // (III)
        String new_country = edit_country.getText().toString();
        Intent data = new Intent();
        data.putExtra("country", new_country);
        data.putExtra("pos", pos);
        setResult(RESULT_OK, data);
        finish();
    }
}
