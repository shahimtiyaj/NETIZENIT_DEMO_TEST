package com.netizen.netizenittest;


import android.content.Context;

/**
 * Created by PT on 3/12/2017.
 */

public class ProgressDialogue {

    private android.app.ProgressDialog progressDialog;
    private Context context;

    public ProgressDialogue(Context context) {
        this.context = context;
    }

    public void showProgress(){
        progressDialog = new android.app.ProgressDialog(context);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Data Loading......");
        progressDialog.show();
    }
    public void hideProgress(){
        progressDialog.hide();
    }

}
