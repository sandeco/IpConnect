package sandeco.ipconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    private RequestQueue queue;

    private EditText n1;
    private EditText n2;
    private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get fields
        n1     = (EditText)findViewById(R.id.n1);
        n2     = (EditText)findViewById(R.id.n2);
        output = (TextView)findViewById(R.id.output);

        //Create Volley
        queue  = Volley.newRequestQueue(this);


    }


    public void enviar(View v){
        // Instantiate the RequestQueue.

        String x = n1.getText().toString();
        String y = n2.getText().toString();

        String url ="http://192.168.25.24/arduino/?x=" + x +"&y="+y;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,this,this);
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


    @Override
    public void onResponse(String response) {
        output.setText("Resultado : "+response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        output.setText("Ocorreu na comunicação com o servidor");
    }

}
