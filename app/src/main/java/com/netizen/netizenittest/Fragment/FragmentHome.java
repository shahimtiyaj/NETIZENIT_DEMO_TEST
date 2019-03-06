package com.netizen.netizenittest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netizen.netizenittest.Activity.AllStudentInfoActivity;
import com.netizen.netizenittest.R;

/**
 * Created by Q-soft on 18/2/2018.
 */

public class FragmentHome extends Fragment {
    private CardView collCard, reportCard;
    private View view;

    public FragmentHome() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        collCard = (CardView) view.findViewById(R.id.collcardId);
        reportCard = (CardView) view.findViewById(R.id.repoCardId);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        OnclickView();
        return view;
    }


    public void OnclickView() {
        collCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent it = new Intent(getActivity(), AllStudentInfoActivity.class);
                    startActivity(it);
                    getActivity().finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        reportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Not Implemented", Toast.LENGTH_LONG).show();
            }
        });
    }
}
