package com.example.mahfo.gtas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.POST;

public class History extends AppCompatActivity {
    public static String url = "http://flashcode.000webhostapp.com/";
    HtmlTextView textResponse ;  // Create an HTMLTextView which will be used to show the user's History
    String htmlcode = "<h1> WELCOME ";
    public String idUser;
    String cours, arrivee, begin;
    public String login;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();
        idUser = intent.getStringExtra(Student.STUDENTID);
        login = intent.getStringExtra(Student.STUDENTNAME);
        htmlcode += login+" !</h1></html>";
        textResponse = findViewById(R.id.htmlHistory);
        textResponse.setMovementMethod(new ScrollingMovementMethod());
        history(idUser);

    }
    public void scan(View view) {
        IntentIntegrator integrator =  new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }
    public void sendScan(final String cours, final String begin, final String arrival, final String end) {
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
                params.put("tag", "scan");
                params.put("student", idUser);
                params.put("matiere", cours);
                params.put("begin", begin);
                params.put("arrival", arrival);
                params.put("end", end);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Annulé ! Veuillez réessayer ! ", Toast.LENGTH_LONG).show();

            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String currentDateandTime = sdf.format(new Date());

                String get = result.getContents();
                Log.i("tagjsonexp", get+" get");
                String[] parts = get.split("@");
                Log.i("tagjsonexp", parts[0] + parts[1] + parts[2] + parts[3]+ parts[4]+" parts");
                String cours = parts[2];
                String begin = parts[4];
                String end = parts[3];

                Log.i("tagjsonexp", cours+" "+begin+" "+currentDateandTime);
                sendScan(cours, begin, currentDateandTime, end);
                Toast.makeText(this, "Emargé ! Bon cours", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void history(final String idUser){
        String url = Student.url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                cours = jsonobject.getString("cours");
                                arrivee  = jsonobject.getString("arrivee");
                                begin = jsonobject.getString("debut");
                                // complete the HTMLTextView with information from the database
                                htmlcode += "<html><h4> "+cours + "<h4/>" + "<ul><li> Début : "+ begin + " Arrivée : "+ arrivee+"</li> </ul>";
                                htmlcode += "</br></html>";
                                if(htmlcode!=null) textResponse.setHtml(htmlcode); // shows the History
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
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
                params.put("tag", "history");
                params.put("student", idUser);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);

    }
}
