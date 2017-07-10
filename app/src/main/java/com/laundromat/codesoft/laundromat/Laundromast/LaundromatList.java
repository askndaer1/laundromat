package com.laundromat.codesoft.laundromat.Laundromast;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import  com.laundromat.codesoft.laundromat.*;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodeSoft on 7/10/2017.
 */

public class LaundromatList extends Activity {
    private final String URL_TO_HIT = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";
    private RecyclerView recyclerView;
    private LaundromatAdapter adapter;
    private List<laundromat> laundromatList;
    private ProgressDialog dialog;


    DatabaseReference databaseCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laundromat_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        setupWindowAnimations();

//        initCollapsingToolbar();

        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data

        new JSONTask().execute();

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        laundromatList = new ArrayList<>();
        adapter = new LaundromatAdapter(this, laundromatList,LaundromatList.this);
//
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
//
//       // prepareAlbums();
//
        try {


            Glide.with(this).load(R.drawable.back).into((ImageView) findViewById(R.id.backdrop));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */


    /**
     * Adding few albums for testing
     */



    public class JSONTask extends AsyncTask<String,String, List<laundromat> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<laundromat> doInBackground(String... params) {







            databaseCustomer = FirebaseDatabase.getInstance().getReference("laundromat");
            databaseCustomer.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    laundromatList.clear();
                    for (DataSnapshot artSnapshot : dataSnapshot.getChildren()) {

                        laundromat customer = artSnapshot.getValue(laundromat.class);
                        laundromatList.add(customer);
                        Log.d("111",customer.getLaundromatName());


                    }

                    adapter.notifyDataSetChanged();
                //   recyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            return laundromatList;
           // laundromatList.add(movieModel);

           //     return laundromatList;


        }

        @Override
        protected void onPostExecute(final List<laundromat> result) {
            super.onPostExecute(result);
            dialog.dismiss();

            recyclerView.setAdapter(adapter);
        }
    }



    public int s (){
        return 9;
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void setupWindowAnimations() {

       // Explode enterTransition = new Explode();
      //  enterTransition.setDuration(300);
       // getWindow().setEnterTransition(enterTransition);

        // For overlap between Exiting  MainActivity.java and Entering TransitionActivity.java
     //   getWindow().setAllowEnterTransitionOverlap(false);


//
//        // Re-enter transition is executed when returning back to this activity
//        Slide slideTransition = new Slide();
//        slideTransition.setSlideEdge(Gravity.LEFT); // Use START if using right - to - left locale
//        slideTransition.setDuration(1000);
//
//        getWindow().setReenterTransition(slideTransition);  // When MainActivity Re-enter the Screen
//        getWindow().setExitTransition(slideTransition);     // When MainActivity Exits the Screen
//
//        // For overlap of Re Entering Activity - MainActivity.java and Exiting TransitionActivity.java
//        getWindow().setAllowReturnTransitionOverlap(false);
    }
}
