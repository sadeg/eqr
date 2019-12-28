package eqr.test.sadiq.eqr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import eqr.test.sadiq.eqr.rest.ApiClient;
import eqr.test.sadiq.eqr.rest.ApiInterface;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActReport extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_report);

        final int e_id = getIntent().getIntExtra("EVENT_ID", 0);

        final RadioGroup rdg_sex = (RadioGroup) findViewById(R.id.rdg_sex);

        final int lng = 50;
        final int lat = 20;
        final String building_floors = "12";
        final String floor = "12";
        final RadioGroup rdg_blt = (RadioGroup) findViewById(R.id.rdg_blt);

        final RadioGroup rdg_blc = (RadioGroup) findViewById(R.id.rdg_blc);

        final RadioGroup rdg_felt = (RadioGroup) findViewById(R.id.rdg_felt);

        final RadioGroup rdg_lcn = (RadioGroup) findViewById(R.id.rdg_lcn);

        final RadioGroup rdg_sleep = (RadioGroup) findViewById(R.id.rdg_sleep);

        final RadioGroup rdg_ppf = (RadioGroup) findViewById(R.id.rdg_ppf);

        final RadioGroup rdg_vib = (RadioGroup) findViewById(R.id.rdg_vib);

        final RadioGroup rdg_emr = (RadioGroup) findViewById(R.id.rdg_emr);

        final RadioGroup rdg_per = (RadioGroup) findViewById(R.id.rdg_per);

        final RadioGroup rdg_rcn = (RadioGroup) findViewById(R.id.rdg_rcn);

        final String reaction_comment = "";

        final RadioGroup rdg_wlk = (RadioGroup) findViewById(R.id.rdg_wlk);

        final RadioGroup rdg_lmv = (RadioGroup) findViewById(R.id.rdg_lmv);

        final RadioGroup rdg_snd = (RadioGroup) findViewById(R.id.rdg_snd);

        final RadioGroup rdg_omv = (RadioGroup) findViewById(R.id.rdg_omv);

        final RadioGroup rdg_fmv = (RadioGroup) findViewById(R.id.rdg_fmv);

        final RadioGroup rdg_bom = (RadioGroup) findViewById(R.id.rdg_bom);

        final RadioGroup rdg_hom = (RadioGroup) findViewById(R.id.rdg_hom);

        final RadioGroup rdg_wll = (RadioGroup) findViewById(R.id.rdg_wll);

        final RadioGroup rdg_dmg = (RadioGroup) findViewById(R.id.rdg_dmg);


        final Button btn_submit = (Button) findViewById(R.id.btn_submit);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rdb_sex_id = rdg_sex.getCheckedRadioButtonId();
                RadioButton rdb_sex = (RadioButton) rdg_sex.findViewById(rdb_sex_id);
                final int sex = rdg_sex.indexOfChild(rdb_sex);

                int rdb_blt_id = rdg_blt.getCheckedRadioButtonId();
                RadioButton rdb_blt = (RadioButton) rdg_blt.findViewById(rdb_blt_id);
                final int building_type = rdg_blt.indexOfChild(rdb_blt);

                int rdb_blc_id = rdg_blc.getCheckedRadioButtonId();
                RadioButton rdb_blc = (RadioButton) rdg_blc.findViewById(rdb_blc_id);
                final int building_condition = rdg_blc.indexOfChild(rdb_blc);

                int rdb_felt_id = rdg_felt.getCheckedRadioButtonId();
                RadioButton rdb_felt = (RadioButton) rdg_felt.findViewById(rdb_felt_id);
                final int felt = rdg_felt.indexOfChild(rdb_felt);

                int rdb_lcn_id = rdg_lcn.getCheckedRadioButtonId();
                RadioButton rdb_lcn = (RadioButton) rdg_lcn.findViewById(rdb_lcn_id);
                final int location = rdg_lcn.indexOfChild(rdb_lcn);

                int rdb_sleep_id = rdg_sleep.getCheckedRadioButtonId();
                RadioButton rdb_sleep = (RadioButton) rdg_sleep.findViewById(rdb_sleep_id);
                final int sleep = rdg_sleep.indexOfChild(rdb_sleep);

                int rdb_ppf_id = rdg_ppf.getCheckedRadioButtonId();
                RadioButton rdb_ppf = (RadioButton) rdg_ppf.findViewById(rdb_ppf_id);
                final int people_felt = rdg_ppf.indexOfChild(rdb_ppf);

                int rdb_vib_id = rdg_vib.getCheckedRadioButtonId();
                RadioButton rdb_vib = (RadioButton) rdg_vib.findViewById(rdb_vib_id);
                final int vibration = rdg_vib.indexOfChild(rdb_vib);

                int rdb_emr_id = rdg_emr.getCheckedRadioButtonId();
                RadioButton rdb_emr = (RadioButton) rdg_emr.findViewById(rdb_emr_id);
                final int emotional_reaction = rdg_emr.indexOfChild(rdb_emr);

                int rdb_per_id = rdg_per.getCheckedRadioButtonId();
                RadioButton rdb_per = (RadioButton) rdg_per.findViewById(rdb_per_id);
                final int people_emotional_reaction = rdg_per.indexOfChild(rdb_per);

                int rdb_rcn_id = rdg_rcn.getCheckedRadioButtonId();
                RadioButton rdb_rcn = (RadioButton) rdg_rcn.findViewById(rdb_rcn_id);
                final int reaction = rdg_rcn.indexOfChild(rdb_rcn);

                int rdb_wlk_id = rdg_wlk.getCheckedRadioButtonId();
                RadioButton rdb_wlk = (RadioButton) rdg_wlk.findViewById(rdb_wlk_id);
                final int walking = rdg_wlk.indexOfChild(rdb_wlk);

                int rdb_lmv_id = rdg_lmv.getCheckedRadioButtonId();
                RadioButton rdb_lmv = (RadioButton) rdg_lmv.findViewById(rdb_lmv_id);
                final int little_movement = rdg_lmv.indexOfChild(rdb_lmv);

                int rdb_snd_id = rdg_snd.getCheckedRadioButtonId();
                RadioButton rdb_snd = (RadioButton) rdg_snd.findViewById(rdb_snd_id);
                final int sounds = rdg_snd.indexOfChild(rdb_snd);

                int rdb_omv_id = rdg_omv.getCheckedRadioButtonId();
                RadioButton rdb_omv = (RadioButton) rdg_omv.findViewById(rdb_omv_id);
                final int objects_movement = rdg_omv.indexOfChild(rdb_omv);

                int rdb_fmv_id = rdg_fmv.getCheckedRadioButtonId();
                RadioButton rdb_fmv = (RadioButton) rdg_fmv.findViewById(rdb_fmv_id);
                final int frames_movement = rdg_fmv.indexOfChild(rdb_fmv);

                int rdb_bom_id = rdg_bom.getCheckedRadioButtonId();
                RadioButton rdb_bom = (RadioButton) rdg_bom.findViewById(rdb_bom_id);
                final int bulky_objects_movement = rdg_bom.indexOfChild(rdb_bom);

                int rdb_hom_id = rdg_hom.getCheckedRadioButtonId();
                RadioButton rdb_hom = (RadioButton) rdg_hom.findViewById(rdb_hom_id);
                final int heavy_objects_movement = rdg_hom.indexOfChild(rdb_hom);

                int rdb_wll_id = rdg_wll.getCheckedRadioButtonId();
                RadioButton rdb_wll = (RadioButton) rdg_wll.findViewById(rdb_wll_id);
                final int walls = rdg_wll.indexOfChild(rdb_wll);

                int rdb_dmg_id = rdg_dmg.getCheckedRadioButtonId();
                RadioButton rdb_dmg = (RadioButton) rdg_dmg.findViewById(rdb_dmg_id);
                final int damages = rdg_dmg.indexOfChild(rdb_dmg);

                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<String> call = apiService.report(136896, sex, lng, lat, building_type, 10, 12, building_condition,
                                felt, location, sleep, people_felt, vibration, emotional_reaction, people_emotional_reaction,
                                reaction, "sdfa", walking, little_movement, sounds, objects_movement, frames_movement, bulky_objects_movement,
                                heavy_objects_movement, walls, damages);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if(response.body() == "ok"){
//                                    Toast.makeText(context, getResources().getString(R.string.msg_submited),
//                                            Toast.LENGTH_LONG).show();
                                    Toast.makeText(context, getResources().getText(R.string.msg_submited), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, ActMainMenu.class);
                                    context.startActivity(intent);
                                }
                                else{
//                                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(context, getResources().getText(R.string.msg_submited), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(context, ActMainMenu.class);
                                    context.startActivity(intent);
                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
//                                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, getResources().getText(R.string.msg_submited), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context, ActMainMenu.class);
                                context.startActivity(intent);
                            }
                        });
                    }
                });



            }
        });

        Button btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActMainMenu.class);
                context.startActivity(intent);
            }
        });
    }
}
