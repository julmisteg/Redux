package com.codepath.apps.mysimpletweets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by gaetanejulmiste on 5/31/16.
 */
public class UserTimeLineFragment  extends TweetsListFragment {


    private TwitterClient client;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TwitterApplication.getRestClient();

        populateTimeline();
    }


    public static UserTimeLineFragment newInstance( String screenName) {
        UserTimeLineFragment userFragment = new UserTimeLineFragment();
        Bundle args = new Bundle();
       // args.putInt("someInt", someInt);
        args.putString("screen_name", screenName);
        userFragment.setArguments(args);
        return userFragment;
    }

    private void populateTimeline() {
        String username=getArguments().getString("screen_name");
        client.getUserTimeline(username ,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG",json.toString());
                //ArrayList<Tweet> tweets = Tweet.fromJsonArray(json);
                AddAll(Tweet.fromJsonArray(json));
                //Log.d("DEBUG",aTweets.toString());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG",errorResponse.toString());

            }
        });
    }
}
