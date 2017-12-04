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
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tercyduk.appngasal.MainActivity;
import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.BaseApiService;
import tercyduk.appngasal.apihelper.UtilsApi;

public class logintest extends AppCompatActivity {

    EditText etEmail,etPassword;
    TextInputLayout tilEmail,tilPassword;
    Button btnLogin;
    TextView txtRegis;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintest);
        mContext = this;
        mApiService = UtilsApi.getAPIService(); // meng-init yang ada di package apihelper
        initComponents();
    }

    private void initComponents() {
        etPassword = (EditText) findViewById(R.id.etxtPass);
        etEmail = (EditText)findViewById(R.id.etxtEmail);
        txtRegis = (TextView)findViewById(R.id.txtRegis);
        btnLogin = (Button) findViewById(R.id.btnCreateAccount);
        tilEmail = (TextInputLayout)findViewById(R.id.login_email);
        tilPassword = (TextInputLayout)findViewById(R.id.login_pass);
        txtRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, registerTest.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _isvalid = true;
                tilEmail.setErrorEnabled(false);
                tilPassword.setErrorEnabled(false);
                if (TextUtils.isEmpty(etEmail.getText())) {
                    _isvalid = false;
                    tilEmail.setErrorEnabled(true);
                    tilEmail.setError("Email is required");
                }
//                else if (!AuthUser.isemailvalid(etEmail.getText().toString())) {
//                    _isvalid = false;
//                    tilEmail.setErrorEnabled(true);
//                    tilEmail.setError("Email is not valid");
//                }
//                else if (!_isemailexist) {
//                    emailcontainer.setErrorEnabled(true);
//                    emailcontainer.setError("Email is not registered.");
//                    _isvalid = false;
//                }
                else if (TextUtils.isEmpty(etPassword.getText())) {

                    tilPassword.setErrorEnabled(true);
                    tilPassword.setError("Password is required");
                    _isvalid = false;
                }
//                else if (!user.getPassword().equals(password.getText().toString())) {
//                    passwordcontainer.setErrorEnabled(true);
//                    passwordcontainer.setError("Password is incorrect.");
//                    _isvalid = false;
//                }

                if (_isvalid) {
                    requestLogin();
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    startActivity(new Intent(mContext, Lapangan.class));
                }

            }
        });
    }

    private void requestLogin(){
        mApiService.loginRequest(etEmail.getText().toString(), etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    // Jika login berhasil maka data nama yang ada di response API
                                    // akan diparsing ke activity selanjutnya.
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                    String nama = jsonRESULTS.getJSONObject("user").getString("name");
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    intent.putExtra("result_nama", nama);
                                    startActivity(intent);
                                } else {
                                    // Jika login gagal
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
