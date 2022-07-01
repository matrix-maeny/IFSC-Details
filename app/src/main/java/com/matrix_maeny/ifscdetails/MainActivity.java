package com.matrix_maeny.ifscdetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.matrix_maeny.ifscdetails.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private String ifscCode = null;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please wait while fetching");

        binding.getDetailsBtn.setOnClickListener(v -> {
            if (checkIFSC()) {
                new Thread() {
                    public void run() {
                        searchIFSC();

                    }
                }.start();
            }
        });
    }

    private boolean checkIFSC() {
        try {
            ifscCode = Objects.requireNonNull(binding.ifscCodeEt.getText()).toString().trim();
            if (!ifscCode.equals("")) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Enter valid IFSC Code", Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // go to about activity
        startActivity(new Intent(MainActivity.this,AboutActivity.class));
        return super.onOptionsItemSelected(item);
    }

    private void searchIFSC() {

        handler.post(() -> progressDialog.show());


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.getCache().clear();

        String url = "https://ifsc.razorpay.com/" + ifscCode;

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String ifsc = "", bank = "", bankCode = "", branch = "",micr="", address = "", contact = "", district = "", city = "", state = "", swift = "", centre = "";//, neft, imps;
                boolean rtgs, upi, neft, imps;


//                "BRANCH": "Karnataka Bank IMPS",
//                        "MICR": "",
//                        "RTGS": true,
//                        "ADDRESS": "REGD. & HEAD OFFICE, P.B.NO.599, MAHAVEER CIRCLE, KANKANADY, MANGALORE - 575002",
//                        "SWIFT": "",
//                        "UPI": true,
//                        "CONTACT": "+918242228222",
//                        "CENTRE": "DAKSHINA KANNADA",
//                        "DISTRICT": "DAKSHINA KANNADA",
//                        "CITY": "MANGALORE",
//                        "STATE": "KARNATAKA",
//                        "NEFT": true,
//                        "IMPS": true,
//                        "BANK": "Karnataka Bank",
//                        "BANKCODE": "KARB",
//                        "IFSC": "KARB0000001"

                try {
                    ifsc = response.getString("IFSC");
                    bank = response.getString("BANK");
                    bankCode = response.getString("BANKCODE");
                    branch = response.getString("BRANCH");
                    micr = response.getString("MICR");
                    address = response.getString("ADDRESS");
                    contact = response.getString("CONTACT");
                    state = response.getString("STATE");
                    rtgs = response.getBoolean("RTGS");
                    swift = response.getString("BANK");
                    upi = response.getBoolean("UPI");
                    neft = response.getBoolean("NEFT");
                    imps = response.getBoolean("IMPS");
                    centre = response.getString("CENTRE");
                    district = response.getString("DISTRICT");
                    city = response.getString("CITY");

                    BankModel.model = new BankModel(ifsc, bank, bankCode, branch,micr, address, centre, district, contact, city, state, rtgs, swift, upi, neft, imps);
                    handler.post(() -> {
                        Toast.makeText(MainActivity.this, "successfully fetched", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    handler.post(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());

                }
                handler.post(() -> progressDialog.dismiss());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                String msg = error.getMessage();
                if(msg != null && msg.contains("UnknownHostException")){
                    msg = "No Internet";
                }else{
                    msg = "Invalid IFSC";
                }
                String finalMsg = msg;
                handler.post(() -> {
                    Toast.makeText(MainActivity.this, "Error: " + finalMsg, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                });

            }
        });

        queue.add(jsonObjectRequest);
    }
}