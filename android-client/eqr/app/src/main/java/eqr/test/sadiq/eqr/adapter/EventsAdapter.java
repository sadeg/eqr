package eqr.test.sadiq.eqr.adapter;

import android.arch.lifecycle.ReportFragment;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import eqr.test.sadiq.eqr.ActReport;
import eqr.test.sadiq.eqr.R;
import eqr.test.sadiq.eqr.model.Event;

public class EventsAdapter extends ArrayAdapter<Event> {
    public EventsAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = getContext();
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_eq, parent, false);
        }


//        // Lookup view for data population
        TextView txv_mag = (TextView) convertView.findViewById(R.id.txv_mag);
        TextView txv_reg = (TextView) convertView.findViewById(R.id.txv_reg);
        TextView txv_datetime = (TextView) convertView.findViewById(R.id.txv_datetime);
        TextView txv_time = (TextView) convertView.findViewById(R.id.txv_time);
        TextView txv_dep = (TextView) convertView.findViewById(R.id.txv_dep);
//        TextView txv_lat = (TextView) convertView.findViewById(R.id.txv_lat);
//        TextView txv_lng = (TextView) convertView.findViewById(R.id.txv_lng);
//        TextView txv_reg1 = (TextView) convertView.findViewById(R.id.txv_reg1);
//        TextView txv_reg2 = (TextView) convertView.findViewById(R.id.txv_reg2);
//        TextView txv_reg3 = (TextView) convertView.findViewById(R.id.txv_reg3);
//        TextView txv_date = (TextView) convertView.findViewById(R.id.txv_date);
//        TextView txv_time = (TextView) convertView.findViewById(R.id.txv_time);
//
        // get text from resource
        String dep = getContext().getResources().getString(R.string.dep);
        String km = getContext().getResources().getString(R.string.km);
        String lat = getContext().getResources().getString(R.string.lat);
        String lng = getContext().getResources().getString(R.string.lng);
//
//        // Populate the data into the template view using the data object
        Double m;
        m = event.getMag();
        if(m < 3){
            txv_mag.setBackgroundResource((R.drawable.circle_green));
        }
        else if(m < 5){
            txv_mag.setBackgroundResource((R.drawable.circle_yellow));
        }
        else if(m < 7){
            txv_mag.setBackgroundResource((R.drawable.circle_orange));
        }
        else {
            txv_mag.setBackgroundResource((R.drawable.circle_red));
        }
        txv_mag.setText(String.valueOf(event.getMag()));
        txv_reg.setText(event.getReg1());
        txv_datetime.setText(event.getDateTime().substring(0, 10));
        txv_time.setText(event.getDateTime().substring(11, 18));
        txv_dep.setText(String.valueOf(event.getDep() + km));

        Button btn_report = (Button)convertView.findViewById(R.id.btn_report);
        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActReport.class);
                context.startActivity(intent);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}