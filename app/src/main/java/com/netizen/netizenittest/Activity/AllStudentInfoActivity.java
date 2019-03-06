package com.netizen.netizenittest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netizen.netizenittest.Database.DatabaseHandler;
import com.netizen.netizenittest.MainActivity;
import com.netizen.netizenittest.R;
import com.netizen.netizenittest.adapter.StudentInfoAdapter;
import com.netizen.netizenittest.model.StudentInfo;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.netizen.netizenittest.app.AppController.getContext;

public class AllStudentInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentInfoAdapter adapter;
    private ArrayList<StudentInfo> studentInfoArrayList;
    private SwipeRefreshLayout swipeRefresh;
    private Boolean isScrolling = false;
    private LinearLayoutManager mLayoutManager;
    private ProgressBar progress;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyler_list);
        toolBarInit();
        recyclerViewInit();
        loadMessageList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
        finish();
    }

    /**
     * SetUp toolbar method
     */
    public void toolBarInit() {
        // Lookup the toolbar in activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Lookup the toolbar title  in activity
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        // Lookup the toolbar in activity layout
        setSupportActionBar(toolbar);
        //Default home button enable false
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTitle.setText("All Student List");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /*
recycler view initialization method
 */
    public void recyclerViewInit() {
        // Initialize item list
        studentInfoArrayList = new ArrayList<StudentInfo>();
        // Lookup the recyclerview in activity layout
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // Create adapter passing in the sample item data
        adapter = new StudentInfoAdapter(getApplicationContext(), studentInfoArrayList);
        //GridLayoutManager shows items in a grid.
        mLayoutManager = new LinearLayoutManager(this);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(mLayoutManager);
        //Set grid item decoration
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));        // Set the default animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
    }

    private void loadMessageList() {
        // DAO dao = new DAO(AppController.getInstance());
        // dao.open();
        db = new DatabaseHandler(this);

        studentInfoArrayList = db.getAllContacts();
        adapter = new StudentInfoAdapter(getContext(), studentInfoArrayList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        // dao.close();
    }
}
