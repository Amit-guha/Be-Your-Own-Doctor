package com.example.beyourowndoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.sharmin.charging.AdsLib;
import com.sharmin.charging.SP;

import static com.sharmin.charging.SP.getSubCode;
import static com.sharmin.charging.SP.setSubCode;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout linearSyntom,linDoctor,linearhospital,linearhealtcare,lineFirsAids,linDeveloped;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    AdsLib adsLib;

    private TextView tvCorona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.naviatinBAr);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCorona=findViewById(R.id.tvCorona);
        tvCorona.setSelected(true);


        adsLib= ChargingInstance.getAdsLib();
        adsLib.checkSubStatus(getSubCode());



        linearSyntom=findViewById(R.id.LinSyntom);
        linDoctor=findViewById(R.id.LinDoct);
        linearhospital=findViewById(R.id.LinHospital);
        linearhealtcare=findViewById(R.id.LinHealthCare);
        lineFirsAids=findViewById(R.id.LinFirsAids);
        linDeveloped=findViewById(R.id.LinDeveloped);


        tvCorona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SP.getSubscriptionStatus()) {
                    showDialog((Activity) MainActivity.this);
                } else {
                    Intent intent = new Intent(MainActivity.this, CoronaUpdate.class);
                    startActivity(intent);
                }
            }
        });

        linDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinDoct){
                   Intent intent=new Intent(MainActivity.this,Specilist_Doctor.class);
                    startActivity(intent);
                }
            }
        });

        linearSyntom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinSyntom){
                    Intent intent=new Intent(MainActivity.this,DiseasesCatagorey.class);
                    startActivity(intent);
                }
            }
        });

        lineFirsAids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinFirsAids){
                    Intent intent=new Intent(MainActivity.this,FirstAids.class);
                    startActivity(intent);
                }
            }
        });


        linearhealtcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinHealthCare){
                    Intent intent=new Intent(MainActivity.this,HealthCare.class);
                    startActivity(intent);
                }
            }
        });

        linDeveloped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinDeveloped){
                    Intent intent=new Intent(MainActivity.this,bloodbank.class);
                    startActivity(intent);
                }
            }
        });

        linearhospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId()==R.id.LinHospital){
                    Intent intent=new Intent(MainActivity.this,Hospital.class);
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.about){
            Intent intent=new Intent(MainActivity.this,about.class);
            startActivity(intent);
        }
       else if(menuItem.getItemId()==R.id.Share){
          Intent intent=new Intent();
          intent.setAction(Intent.ACTION_SEND);
          intent.putExtra(Intent.EXTRA_TEXT,"This app is very Useful for all user who are thinking about their health and others;");
          intent.putExtra(Intent.EXTRA_TEXT,"Be Your Own Doctor");
          intent.setType("text/plain");
          startActivity(Intent.createChooser(intent,null));

        }

        else if(menuItem.getItemId()==R.id.feebackid){
            Intent intent=new Intent(MainActivity.this,Feedback.class);
            startActivity(intent);
        }

        else if(menuItem.getItemId()==R.id.biod){

            Intent intent=new Intent(MainActivity.this,BYOD.class);
            startActivity(intent);
        }

        else if(menuItem.getItemId()==R.id.aboutus){
            Intent intent=new Intent(MainActivity.this,DevelopedBy.class);
            startActivity(intent);
        }


        return false;
    }
}
