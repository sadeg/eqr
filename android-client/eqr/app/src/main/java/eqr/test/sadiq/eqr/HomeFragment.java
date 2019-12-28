package eqr.test.sadiq.eqr;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eqr.test.sadiq.eqr.adapter.EventsAdapter;
import eqr.test.sadiq.eqr.model.Event;
import eqr.test.sadiq.eqr.rest.ApiClient;
import eqr.test.sadiq.eqr.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static eqr.test.sadiq.eqr.ActMain.updateEvents;


public class HomeFragment extends Fragment {
    private static final String TAG = ActMain.class.getSimpleName();
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        // Construct the data source
        final View rootView = inflater.inflate(R.layout.fragment_home, null);

        // Set title bar
        ((ActMainMenu) getActivity()).setActionBarTitle(getString(R.string.title_home));.3


        // Construct the data source
        ArrayList<Event> arrayOfEvents = new ArrayList<Event>();
        // Create the adapter to convert the array to views
        final EventsAdapter adapter = new EventsAdapter(rootView.getContext(), arrayOfEvents);
        // Attach the adapter to a ListView
        final ListView listView = (ListView)rootView.findViewById(R.id.lsv);
        listView.setAdapter(adapter);
        updateEvents(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        updateEvents(adapter);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },5000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Event event = (Event) adapterView.getItemAtPosition(position);
//                Toast.makeText(rootView.getContext(), "sp = " + String.valueOf(event.getSelected_position()), Toast.LENGTH_SHORT).show();
//                Toast.makeText(rootView.getContext(), "po = " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                if(event.getSelected_position() != -1){
                    if(position == event.getSelected_position()){
                        //hiding last selected item
                        LinearLayout last_expanded = getViewByPosition(event.getSelected_position(), listView).findViewById(R.id.lyt_expand);
                        last_expanded.setVisibility(View.GONE);
                        event.setSelected_position(-1);
                    }
                    else{
                        //hiding last selected item
                        LinearLayout last_expanded = getViewByPosition(event.getSelected_position(), listView).findViewById(R.id.lyt_expand);
                        last_expanded.setVisibility(View.GONE);
                        event.setSelected_position(position);
                        LinearLayout lyt_expand = (LinearLayout) view.findViewById(R.id.lyt_expand);
                        // slide-down animation
                        Animation slideUp = AnimationUtils.loadAnimation(rootView.getContext(), R.anim.slide_down);
                        lyt_expand.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    event.setSelected_position(position);
                    LinearLayout lyt_expand = (LinearLayout) view.findViewById(R.id.lyt_expand);
                    lyt_expand.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
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

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}