package br.com.leonam.bppmbiletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.leonam.bppmbiletest.objects.Transaction;
import br.com.leonam.bppmbiletest.util.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_transactions)
    RecyclerView mRvTransastions;

    private List<Transaction> mTransactions = new ArrayList<>();
    private TransactionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        getTransationData();
    }

    private void initializeViews() {

        mAdapter = new TransactionAdapter(mTransactions);
        mRvTransastions.setLayoutManager(new LinearLayoutManager(this));
        mRvTransastions.setAdapter(mAdapter);
    }

    private void getTransationData() {

        StringRequest request = new StringRequest(Constants.URL_INVOICE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        mTransactions = gson.fromJson(response, new TypeToken<List<Transaction>>(){}.getType());
                        initializeViews();
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(getApplicationContext()).add(request);
    }
}
