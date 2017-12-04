package tercyduk.appngasal.modules.auth.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.BaseApiService;
import tercyduk.appngasal.apihelper.UtilsApi;

public class registerTest extends AppCompatActivity {
    EditText etName,etEmail,etPassword,etRepassword,etNohp;
    TextInputLayout tilName,tilemail,tilhp,tilpassword,tilrepassword;
    TextView txtLogin;
    Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);


        mContext = this;
        mApiService = UtilsApi.getAPIService();
        initComponents();
    }

    private void initComponents() {

       etNohp = (EditText) findViewById(R.id.phone_number);
       etRepassword = (EditText)findViewById(R.id.repassword);
        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);
        tilName = (TextInputLayout)findViewById(R.id.register_name_container);
        tilemail = (TextInputLayout)findViewById(R.id.register_name_email);
        tilhp = (TextInputLayout)findViewById(R.id.register_name_hp);
        tilpassword = (TextInputLayout)findViewById(R.id.register_name_pass);
        tilrepassword = (TextInputLayout)findViewById(R.id.register_name_pass2);
        btnRegister = (Button) findViewById(R.id.btnCreateAccount);
        txtLogin = (TextView)findViewById(R.id.txtSignin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, Lapangan.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _isvalid = true;
                tilName.setErrorEnabled(false);
                tilemail.setErrorEnabled(false);
                tilhp.setErrorEnabled(false);
                tilpassword.setErrorEnabled(false);
                tilrepassword.setErrorEnabled(false);
                if (TextUtils.isEmpty(etName.getText())) {
                    _isvalid = false;
                    tilName.setErrorEnabled(true);
                    tilName.setError("Name is required");
                } else if (etName.getText().length() < 7) {
                    _isvalid = false;
                    tilName.setErrorEnabled(true);
                    tilName.setError("Name minimal 7");

                }
                else if(!AuthUser.isemailvalid(etEmail.getText().toString()))
                {
                    _isvalid=false;
                    tilemail.setErrorEnabled(true);
                    tilemail.setError("Email is not valid");
                }
                else if (TextUtils.isEmpty(etEmail.getText())) {
                    _isvalid = false;
                    tilemail.setErrorEnabled(true);
                    tilemail.setError("Email is required");
                }
                else if(TextUtils.isEmpty((etNohp.getText())))
                {
                    _isvalid=false;
                    tilhp.setErrorEnabled(true);
                    tilhp.setError("Handphone is required");
                }
                else if(etNohp.getText().length()<12 && etNohp.getText().length()>11)
                {
                    _isvalid = false;
                    tilhp.setErrorEnabled(true);
                    tilhp.setError("Hanphone minimal 12");
                }
                else if(!AuthUser.ispasswordvalid(etPassword.getText().toString()))
                {
                    _isvalid = false;
                    tilpassword.setErrorEnabled(true);
                    tilpassword.setError("Password is not valid. Password must contains " +
                            "at least 1 lowercase, 1 uppercase, 1 number, 1 special character and minimum 8 characters");
                }
                else if (TextUtils.isEmpty(etPassword.getText())) {
                    _isvalid = false;
                    tilpassword.setErrorEnabled(true);
                    tilpassword.setError("Password is required");
                }  else if (TextUtils.isEmpty(etRepassword.getText())) {
                    _isvalid = false;
                    tilrepassword.setErrorEnabled(true);
                    tilrepassword.setError("Re-Password is required");
                } else if (!etPassword.getText().toString().equals(etRepassword.getText().toString())) {
                    _isvalid = false;
                    tilrepassword.setErrorEnabled(true);
                    tilrepassword.setError("Password not match");

                }
                if(_isvalid ) {
                    requestRegister();
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                }
            }

            private void requestRegister() {
                    mApiService.registerRequest(etName.getText().toString(),
                            etEmail.getText().toString(),
                            etPassword.getText().toString())
                            .enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()){
                                        Log.i("debug", "onResponse: BERHASIL");
                                        startActivity(new Intent(mContext, logintest.class));
                                        loading.dismiss();
                                        try {
                                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                            if (jsonRESULTS.getString("error").equals("false")){
                                                Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_LONG).show();

                                            } else {
                                                String error_message = jsonRESULTS.getString("error_msg");
                                                Toast.makeText(mContext, error_message, Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Log.i("debug", "onResponse: GA BERHASIL");
                                        loading.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                                    Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

        });
    }
}
