package com.example.mahfo.gtas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Teacher extends AppCompatActivity {
    public final static String USER = "com.example.mahfo.gtas.idUser";
    public final static String NAME = "com.example.mahfo.gtas.login";
    private EditText inputname;
    private EditText inputpass;
    public String idUser;
    public String login;
    public static String url = "http://flashcode.000webhostapp.com/";  //the url used to get database information

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_teacher);
        inputname = findViewById(R.id.log);
        inputpass = findViewById(R.id.pass);
        Button btnLogin = findViewById(R.id.connect2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   // set on click listener on the login button
                String name = inputname.getText().toString();
                String pass = inputpass.getText().toString();

                if (!name.isEmpty() && !pass.isEmpty()) {
                    connection(name,pass);
                } else {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir vos données!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void connection(final String name, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {  //set a listener on the Method POST the the url defined at the top

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jobj = new JSONObject(response);
                            // get the Json array which contains information about the existence of the account
                            boolean error = jobj.getBoolean("error");
                            if (!error) { // if the account exists and the login and password are valid
                                idUser = jobj.getString("id_enseignant"); // get the idUser stored in the database
                                JSONObject user = jobj.getJSONObject("user"); //get the Json array which contains the login and password
                                login = user.getString("nom");
                                // create a new intent to start the History activity
                                Intent intent = new Intent(Teacher.this, Generate.class);
                                intent.putExtra(NAME, login);
                                intent.putExtra(USER, idUser);  // transmit the login and idUser to the History activity
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Mot de passe ou identifiant incorrect", Toast.LENGTH_LONG).show();
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
                /*
                    use php code and execute the post request and database request to connect to the account
                     with the information taped by the user
                    and create a JSON array with it's response used by the onResponse method
                 */
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "connectionPro");
                params.put("nom", name);
                params.put("password", password);
                return params;
            }
        };
        Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
    }
}
