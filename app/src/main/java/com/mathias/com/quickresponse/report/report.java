
package com.mathias.com.quickresponse.report;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mathias.com.quickresponse.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class report extends AppCompatActivity {
    EditText loaction, descupriction;
    String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Bundle bundle = getIntent().getExtras();
        select =  bundle.getString("selected");
        TextView textView = findViewById(R.id.title);
        textView.setText(select);

       loaction = findViewById(R.id.report_address);
      descupriction = findViewById(R.id.disc);
        CheckBox checkBox = findViewById(R.id.direction);
        Button summit = findViewById(R.id.summit);
        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public JSONArray makejson(){
        JSONArray array= new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("title", select);
            jsonObject.put("location",loaction.getText().toString().trim());
            jsonObject.put("discpriction", descupriction.getText().toString().trim());

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return array;
    }
}
