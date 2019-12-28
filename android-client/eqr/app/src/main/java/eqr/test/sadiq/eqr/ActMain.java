package eqr.test.sadiq.eqr;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import eqr.test.sadiq.eqr.adapter.EventsAdapter;
import eqr.test.sadiq.eqr.model.Event;
import eqr.test.sadiq.eqr.rest.ApiClient;
import eqr.test.sadiq.eqr.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActMain extends AppCompatActivity {
    private static final String TAG = ActMain.class.getSimpleName();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        Button btn_update = (Button) findViewById(R.id.btn_update);

        // Construct the data source
        ArrayList<Event> arrayOfEvents = new ArrayList<Event>();
        // Create the adapter to convert the array to views
        final EventsAdapter adapter = new EventsAdapter(this, arrayOfEvents);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.lsv);
        listView.setAdapter(adapter);
        updateEvents(adapter);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.clear();
                updateEvents(adapter);
                Toast.makeText(context, getResources().getString(R.string.msg_update),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public static void updateEvents(final EventsAdapter adapter){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Event>> call = apiService.getLastEvents();
        call.enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>>call, Response<ArrayList<Event>> response) {
                ArrayList<Event> events = response.body();
                Log.e(TAG, events.toString());
                adapter.addAll(events);
            }

            @Override
            public void onFailure(Call<ArrayList<Event>>call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}

