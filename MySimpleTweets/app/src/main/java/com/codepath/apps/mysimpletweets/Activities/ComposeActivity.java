package com.codepath.apps.mysimpletweets.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.Fragments.UserTimeLineFragment;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

public class ComposeActivity extends AppCompatActivity {
    TwitterClient client;
    User user;
    public static final int MAX_COUNT = 140;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        client = TwitterApplication.getRestClient();
        client.getUserInfo(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                user=User.fromJSON(response);
                getSupportActionBar().setTitle("@" +user.getScreenName());
                populateComposeHeader(user);
            }
        });





    }



    private void populateComposeHeader(User user) {
        TextView tvName = (TextView) findViewById(R.id.etUserId);
        final TextView tvCount = (TextView) findViewById(R.id.tvCount);
        ImageView ivProfileImage = (ImageView) findViewById(R.id.ivPhoto);

       final  Button btnTweet =(Button) findViewById(R.id.btnTweet);
        tvName.setText(user.getName());
        Picasso.with(this).load(user.getProfileImageUrl()).into(ivProfileImage);


        ImageView ivClosed = (ImageView) findViewById(R.id.ivClosed);
        ivClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),TimelineActivity.class);
                startActivity(i);

            }
        });


        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TimelineActivity.class);
                startActivity(i);

            }
        });
        EditText etTweetBody = (EditText) findViewById(R.id.etTweetBody);
        etTweetBody.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length == 0) {
                    tvCount.setText("");
                    tvCount.setTextColor(BLACK);
                    btnTweet.setEnabled(false);



                } else if (length <= MAX_COUNT) {
                    tvCount.setText(Integer.toString(length));
                    tvCount.setTextColor(BLACK);
                    btnTweet.setEnabled(true);

                } else {
                    tvCount.setText(Integer.toString(MAX_COUNT - length));
                    tvCount.setTextColor(RED);
                    btnTweet.setEnabled(false);

                }


            }
        });
    }






}
