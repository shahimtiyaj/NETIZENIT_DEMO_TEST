package com.netizen.netizenittest.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netizen.netizenittest.R;

public class SingleStudentActivity extends AppCompatActivity {

    private TextView txtNmae;
    private TextView inputBirthDate;
    private TextView inputEmail, etGender;
    private TextView blood;
    private TextView inputCity, inputArea, inputPhone, inputPostCode, inputAgree,
            inputContact, inputClass, inputSession, inputHouse;
    private ImageView imageView;

    private String sname, sbirthdate, semail, sgender, sblood, sphone, sarea, scity,
            spostcode, sagree, scontactperson, sclass, ssession, shouse;
    private byte[] simage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);
        initializeToolbar();

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                simage = null;
                sname = null;
                sbirthdate = null;
                semail = null;
                sgender = null;
                sblood = null;

                sphone = null;
                sarea = null;
                scity = null;
                spostcode = null;
                sagree = null;
                scontactperson = null;
                sclass = null;
                ssession = null;

                getSupportActionBar().setTitle("Student Profile");

            } else {
                try {
                    simage = extras.getByteArray("simage");
                    sname = extras.getString("sname");
                    sbirthdate = extras.getString("birtdate");
                    semail = extras.getString("email");
                    sgender = extras.getString("gender");
                    sblood = extras.getString("blood");

                    sphone = extras.getString("phone");
                    sarea = extras.getString("area");
                    scity = extras.getString("city");
                    spostcode = extras.getString("postcode");
                    sagree = extras.getString("agree");
                    scontactperson = extras.getString("contactperson");
                    sclass = extras.getString("class");
                    ssession = extras.getString("session");

                    initializeView(simage, sname, sbirthdate, semail, sgender, sblood,
                            sphone, sarea, scity, spostcode, sagree, scontactperson, sclass, ssession);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void initializeView(final byte[] simage, final String sname, final String date, final String email,
                                final String gender, final String sblood,
                                final String sphone, final String sarea, final String scity, final String spostcode,
                                final String sagree, final String scontactperson, final String sclass, final String ssession) {

        imageView = (ImageView) findViewById(R.id.profile_pic_id);

        txtNmae = (TextView) findViewById(R.id.txt_profile_name);
        inputBirthDate = (TextView) findViewById(R.id.birth_date);
        inputEmail = (TextView) findViewById(R.id.email);
        etGender = (TextView) findViewById(R.id.gender);
        blood = (TextView) findViewById(R.id.blood);

        inputPhone = (TextView) findViewById(R.id.phone_no);
        inputCity = (TextView) findViewById(R.id.city);
        inputArea = (TextView) findViewById(R.id.area);
        inputPostCode = (TextView) findViewById(R.id.postcode);
        inputAgree = (TextView) findViewById(R.id.agree);
        inputContact = (TextView) findViewById(R.id.contact_person);
        inputClass = (TextView) findViewById(R.id.class_id);
        inputSession = (TextView) findViewById(R.id.session);
        //inputHouse = (TextView) findViewById(R.id.house_no);

        imageView.setImageBitmap(convertToBitmap(simage));
        // user_id.setText(id);
        txtNmae.setText(sname);
        inputBirthDate.setText(date);
        inputEmail.setText(email);
        etGender.setText(gender);
        blood.setText(sblood);

        inputPhone.setText(sphone);
        inputArea.setText(sarea);
        inputCity.setText(scity);
        inputPostCode.setText(spostcode);
        inputAgree.setText(sagree);
        inputContact.setText(scontactperson);
        inputClass.setText(sclass);
        inputSession.setText(ssession);
        //inputHouse.setText(shouse);
    }

    private Bitmap convertToBitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    private void initializeToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Profile");
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, AllStudentInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }
}
