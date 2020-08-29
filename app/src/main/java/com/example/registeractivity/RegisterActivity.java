package com.example.registeractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText name,email,mobile,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        mobile=findViewById(R.id.mobile);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")
                || email.getText().toString().equals("")
                || mobile.getText().toString().equals("")
                || password.getText().toString().equals(""))
                {
                    Toast.makeText(RegisterActivity.this,
                            "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url="http://bsfshshillong.org.in/test/register.php";
                    StringRequest rs=new StringRequest(1, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(RegisterActivity.this,response, Toast.LENGTH_SHORT).show();
                                    name.setText("");
                                    email.setText("");
                                    mobile.setText("");
                                    password.setText("");
                                    Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> map=new HashMap<>();
                            map.put("namekey",name.getText().toString());
                            map.put("emailkey",email.getText().toString());
                            map.put("mobilekey",mobile.getText().toString());
                            map.put("passwordkey",password.getText().toString());
                            return map;
                        }
                    };
                    RequestQueue rq= Volley.newRequestQueue(RegisterActivity.this);
                    rq.add(rs);
                }
            }
        });
    }
}
