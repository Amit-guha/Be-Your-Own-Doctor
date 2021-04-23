package com.example.beyourowndoctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sharmin.charging.AdsLib;
import com.sharmin.charging.SP;

import java.util.ArrayList;

import static com.sharmin.charging.SP.getSubCode;
import static com.sharmin.charging.SP.setSubCode;

public class DoctorName extends AppCompatActivity {

    RecyclerView recyclerView;
    DoctorAdapter doctorAdapter;
    AdsLib adsLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_name);

        adsLib= ChargingInstance.getAdsLib();
        adsLib.checkSubStatus(getSubCode());

      //  ArrayList<ExampleItem> ExampleList=new ArrayList<>();

        Bundle bundle = this.getIntent().getExtras();
        final String[] names=bundle.getStringArray("arrayOfName");
        final String [] designation=bundle.getStringArray("designaiton");
        final String [] Expertise=bundle.getStringArray("Expertise");
        final String [] Instituaton=bundle.getStringArray("Instituaton");
        final String [] Champber=bundle.getStringArray("champer");
        final String [] Mobile=bundle.getStringArray("Phone");

        String specilaname=bundle.getString("data");

        getSupportActionBar().setTitle(specilaname);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.RecylerDoctorName);
        doctorAdapter=new DoctorAdapter(this,names,designation,Expertise,Instituaton);
        recyclerView.setAdapter(doctorAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //OnItemclick
       doctorAdapter.setOnItemClickLisitiner(new DoctorAdapter.ClickLIsitiner() {
           @Override
           public void onItemclick(int position, View view) {
               if (SP.getSubscriptionStatus()) {
                   showDialog((Activity) DoctorName.this);
               } else {
                   //  Toast.makeText(DoctorName.this, ""+Mobile[position], Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(DoctorName.this, DoctorPHone.class);
                   intent.putExtra("arrayOfName", names[position]);
                   // intent.putExtra("designation",designation[position]);
                   intent.putExtra("Expertise", Expertise[position]);
                   intent.putExtra("Instituaton", Instituaton[position]);
                   intent.putExtra("champer", Champber[position]);
                   intent.putExtra("Phone", Mobile[position]);
                   startActivity(intent);
               }
           }
       });


    }


    //Create Showdialgoue for subcription
    public void showDialog(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.sub);




        final TextView textView_sub = dialog.findViewById(R.id.textView_sub);
        final TextView textView_sub1 = dialog.findViewById(R.id.textView_sub1);

        Button button_s_daily = dialog.findViewById(R.id.button_s_daily);
        Button button_s_daily_api = dialog.findViewById(R.id.button_s_daily_api);
        final Button bt_send_sms = dialog.findViewById(R.id.bt_send_sms);
        final Button submit_code = dialog.findViewById(R.id.submit_code);

        final LinearLayout ll_sub = dialog.findViewById(R.id.ll_sub);
        final LinearLayout ll_sub_1 = dialog.findViewById(R.id.ll_sub_1);
        final EditText otp_code = dialog.findViewById(R.id.otp_code);


        button_s_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView_sub.setText("সাবস্ক্রাইব করতে আপনার মোবাইল নাম্বার দিন");
                textView_sub1.setText("শুধুমাত্র রবি এবং এয়ারটেল গ্রাহকদের জন্য");
                // ll_sub.setVisibility(View.VISIBLE);
                ll_sub_1.setVisibility(View.GONE);
                bt_send_sms.setVisibility(View.VISIBLE);
                adsLib.subscribe();

            }
        });

        button_s_daily_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call sub api
            }
        });

        bt_send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("smsto:21213");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                // intent.putExtra("sms_body", "start "+ ChargingInstance.MSG_TEXT);
                intent.putExtra("sms_body", ChargingInstance.MSG_TEXT);
                activity.startActivity(intent);
                dialog.dismiss();
            }
        });


        submit_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked Laugh Vote", Toast.LENGTH_SHORT).show();
                setSubCode(otp_code.getText().toString().isEmpty() ? "111111" : otp_code.getText().toString());
                adsLib.checkSubStatus(otp_code.getText().toString().isEmpty() ? "111111" : otp_code.getText().toString());
                dialog.dismiss();
            }
        });

        Button dialogButton = (Button) dialog.findViewById(R.id.video_ad);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        dialog.show();

    }

}
