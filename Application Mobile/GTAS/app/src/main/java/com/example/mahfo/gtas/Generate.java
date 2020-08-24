package com.example.mahfo.gtas;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.layout.simple_list_item_1;
import static com.android.volley.Request.Method.POST;

public class Generate extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private List<String> list = new ArrayList<>();
    EditText begin, end, room;
    String data;
    Button beginBut, endBut, submit, nfcBut;
    Spinner spinner;
    ImageView showQr;
    String id, name;
    public static String url = "http://flashcode.000webhostapp.com/";  //the url used to get database information
    public String idUser;
    ArrayAdapter<String> adp1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        begin = findViewById(R.id.begin);
        end = findViewById(R.id.end);
        Intent intent = getIntent();
        submit = findViewById(R.id.generate);
        idUser = intent.getStringExtra(Teacher.USER);
        endBut = findViewById(R.id.endBut);
        beginBut = findViewById(R.id.beginBut);
        nfcBut = findViewById(R.id.nfc);
        endBut.setOnClickListener(this);
        room = findViewById(R.id.salle);
        beginBut.setOnClickListener(this);
        spinner = findViewById(R.id.spinner);
        showQr = findViewById(R.id.qrCode);
        list.add("Vuillez choisir votre cours");

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String currentDateandTime = sdf.format(new Date());
        getMatiere(idUser);
        adp1 = new ArrayAdapter<String>(this,
                simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adp1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // set on click listener on the login button
                String beginCourse = currentDateandTime+" "+begin.getText().toString();
                String endCourse = currentDateandTime+" "+end.getText().toString();
                String roomNum = room.getText().toString();

                if(beginCourse.isEmpty() || endCourse.isEmpty() || roomNum.isEmpty() || spinner.getSelectedItem().toString() == "Veuillez choisir votre cours"){
                    Toast.makeText(getApplicationContext(), "Veuillez saisir tous les champs !!", Toast.LENGTH_LONG).show();
                }else{
                    String part[] = roomNum.split(" " );
                    String groupe = part[0];
                    String filiere = part[1];
                    setCourse(spinner.getSelectedItem().toString(), beginCourse, endCourse, groupe, filiere);
                    data = idUser+"@"+roomNum+"@"+spinner.getSelectedItem().toString()+"@"+endCourse+"@"+beginCourse;
                    Toast.makeText(getApplicationContext(), "Cours enregistré ! ", Toast.LENGTH_LONG).show();
                    generateQR(data);
                }

            }
        });

        nfcBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // set on click listener on the login button
                String beginCourse = currentDateandTime+" "+begin.getText().toString();
                String endCourse = currentDateandTime+" "+end.getText().toString();
                String roomNum = room.getText().toString();

                if(beginCourse.isEmpty() || endCourse.isEmpty() || roomNum.isEmpty() || spinner.getSelectedItem().toString() == "Veuillez choisir votre cours"){
                    Toast.makeText(getApplicationContext(), "Veuillez saisir tous les champs !!", Toast.LENGTH_LONG).show();
                }else{
                    String part[] = roomNum.split(" " );
                    String groupe = part[0];
                    String filiere = part[1];
                    setCourse(spinner.getSelectedItem().toString(), beginCourse, endCourse, groupe, filiere);
                    Toast.makeText(getApplicationContext(), "Cours enregistré ! ", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void setCourse(final String course, final String begin, final String end, final String groupe, final String filiere){
        StringRequest stringRequest = new StringRequest(POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("tagjsonexp", response);
                        try {
                            JSONObject jobj = new JSONObject(response);
                            boolean error = jobj.getBoolean("error");
                            if (error) {
                                Toast.makeText(getApplicationContext(), "Petit problème ! Veuillez réessayer ultérieurement", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            Log.i("tagjsonexp", "" + e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("tag", "course");
                params.put("teacher", idUser);
                params.put("course", course);
                params.put("begin", begin);
                params.put("group", groupe);
                params.put("filiere", filiere);
                params.put("end", end);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
    public void onClick(View v) {
        int mMinute;
        int mHour;
        if (v == beginBut) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            begin.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if (v == endBut) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            end.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (spinner.getSelectedItem() != "Vuillez choisir votre cours") Log.d("sdsafa", "zfds");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void  getMatiere(final String data) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jsonarray = object.getJSONArray("user");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                id = jsonobject.getString("id");
                                name = jsonobject.getString("name");
                                list.add(name);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() {
                 /*
                    use php code and execute the post request and database request to get information from data base
                     and create the user's history. That's create a JSON array with it's response used by the onResponse method
                 */
                Map<String, String> params = new HashMap<>();
                params.put("tag", "getMatiere");
                params.put("idProf", data);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }


    public void generateQR(String idqr) { //genearate the QrCode using the zxing API (https://zxing.github.io/zxing/apidocs/com/google/zxing/MultiFormatWriter.html)
        MultiFormatWriter multi = new MultiFormatWriter();
        try {
            //crypt the idqr (sha1) for more security
            BitMatrix bitMatrix = multi.encode((idqr), BarcodeFormat.QR_CODE, 300, 300);
            BarcodeEncoder encode = new BarcodeEncoder();
            Bitmap bitmap = encode.createBitmap(bitMatrix);
            showQr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
