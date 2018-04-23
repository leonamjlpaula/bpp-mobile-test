package br.com.leonam.bppmbiletest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.et_login)
    TextInputEditText mEtLogin;

    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;

    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mEtLogin.setText("waldisney@brasilprepagos.com.br");
        mEtPassword.setText("Br@silPP123");
    }

    @OnClick(R.id.bt_login)
    public void loginUser() {

        if(mEtLogin.getText().toString().length() == 0 || mEtLogin.getText().toString().length() == 0){
            showErrorDialog(getString(R.string.no_email_password));
            return;
        }

        mPbLoading.setVisibility(View.VISIBLE);

        StringRequest postRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d(TAG, response);
                        mPbLoading.setVisibility(View.GONE);
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            if(jsonObject.optInt(Constants.CODE) == Constants.CODE_SUCCESS ){
                                startMainActivity();
//                            }
//                            else
//                                showErrorDialog(jsonObject.optString(Constants.MESSAGE));
//                        }
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.e(TAG, error.toString());
                        showErrorDialog(getString(R.string.unexpected_error));
                        mPbLoading.setVisibility(View.GONE);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put(Constants.EMAIL, mEtLogin.getText().toString());
                String base64 = Base64.encodeToString(mEtPassword.getText().toString().getBytes(), Base64.DEFAULT);
                Log.d(TAG, mEtLogin.getText().toString());
                Log.d(TAG, base64);
                params.put(Constants.PASSWORD, base64);
                Log.d(TAG, params.toString());

                return params;
            }
        };

        Volley.newRequestQueue(getApplicationContext()).add(postRequest);
    }

    private void showErrorDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle(R.string.warning);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, null);
        builder.create().show();
    }

    private void startMainActivity() {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);
    }
}
