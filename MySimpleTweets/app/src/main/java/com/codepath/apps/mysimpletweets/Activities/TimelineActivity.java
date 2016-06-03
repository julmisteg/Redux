package com.codepath.apps.mysimpletweets.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.mysimpletweets.Fragments.HomeTimelineFragment;
import com.codepath.apps.mysimpletweets.Fragments.MentionsTimeLineFragment;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.models.Tweet;

import java.io.IOException;

public class TimelineActivity extends ActionBarActivity implements OnClickListener
        //extends AppCompatActivity implements ComposeTweets.OnNewTweetCreatedListener
{

    private Tweet tweet;

   // private TwitterClient client;
   // private TweetsListFragment fragmentTweetsList;
   /* private ArrayList<Tweet> tweets;
    private TweetsArrayAdapter aTweets;
    private ListView lvTweets;
    private String max_id;
    private User user;
    private SwipeRefreshLayout swipeContainer;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);




       /* lvTweets =(ListView)findViewById(R.id.lvtweets);
        tweets = new ArrayList<>();
        aTweets = new TweetsArrayAdapter(this,tweets);
        lvTweets.setAdapter(aTweets);*/


       // client = TwitterApplication.getRestClient();
        //getUserProfile();
       // populateTimeline(0);


        //if(savedInstanceState == null) {
         //   fragmentTweetsList = (TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_timeline);
       // }

      /* max_id = null;

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //populateTimeline(page);

               // aTweets.clear();
                getData();

            }
        });

        */





        ///


      /*  swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.


               // populateTimeline(0);
              //  getData();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

*/

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));
        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabStrip.setViewPager(viewPager);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       // if (id == R.id.miProfile){
       //     return  true;
       // }
        return super.onOptionsItemSelected(item);
      /*  switch (item.getItemId()) {
            case R.id.action_add:
                FragmentManager fm = getSupportFragmentManager();
                ComposeTweets createTweetDialog = ComposeTweets.newInstance();
                createTweetDialog.show(fm, "compose_fragment");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        */
    }


    /*

    private void populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG",json.toString());
                //ArrayList<Tweet> tweets = Tweet.fromJsonArray(json);
                aTweets.addAll(Tweet.fromJsonArray(json));
                Log.d("DEBUG",aTweets.toString());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG",errorResponse.toString());

            }
        });
    }


    */




    /*private void populateTimeline(int page) {
        client.getHomeTimeline(page,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG",json.toString());
                //ArrayList<Tweet> tweets = Tweet.fromJsonArray(json);
               // aTweets.addAll(Tweet.fromJsonArray(json));
                fragmentTweetsList.AddAll(Tweet.fromJsonArray(json));



               // Log.d("DEBUG",aTweets.toString());

               // swipeContainer.setRefreshing(false);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG",errorResponse.toString());

            }
        });
    }*/

public void onComposeTweets (MenuItem menuItem){
    Intent i = new Intent(this,ComposeActivity.class);
    startActivity(i);

}

 public void onProfileView (MenuItem mi){
     Intent i = new Intent(this,ProfileActivity.class);
     startActivity(i);

 }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivProfileImage) {
            Intent i = new Intent(this, ProfileActivity.class);
            i.putExtra("user", tweet.getUser());
            startActivity(i);
        }
    }




   /* private void getData(){
        if(isOnline()){
            populateTimeline(0);
            swipeContainer.setRefreshing(false);
        }else{
            getStoredTweets(0);
            //Toast.makeText(getApplicationContext(),"Network Connection not available !!!",Toast.LENGTH_SHORT).show();
        }


    }*/

    /*private void getUserProfile() {
        client.getUserProfile(new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                Log.d("DEBUG", "user: " + jsonObject.toString());
             //   user = User.fromJSON(jsonObject);
              //  user.save();

            }
        });
    }
    */





    /*public void composeTweet(String tweetBody) {
        client.composeTweet(tweetBody, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                populateTimeline(0);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                Toast.makeText(TimelineActivity.this, errorResponse.toString(), Toast.LENGTH_LONG).show();
            }

        });
    }
**/

   /* private void getStoredTweets(int page){
        List<Tweet> tweetList = Tweet.getAll(page);
        if(tweetList.size() > 0){
            tweets.addAll(tweetList);

        }
    }
*/

public class TweetsPagerAdapter extends FragmentPagerAdapter{
   final int PAGE_COUNT=2;
    private String tabTitles[]={"Home","TimeLine"};
    public TweetsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new  HomeTimelineFragment();
        }else if (position == 1){
            return new MentionsTimeLineFragment();
        }else {
            return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}
}
