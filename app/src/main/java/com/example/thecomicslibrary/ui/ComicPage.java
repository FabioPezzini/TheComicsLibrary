package com.example.thecomicslibrary.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.widget.TextViewCompat;
import com.example.thecomicslibrary.R;
import com.example.thecomicslibrary.domain.Comic;
import com.example.thecomicslibrary.utility.FileManager;
import com.example.thecomicslibrary.utility.RoundBorderTransformation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class ComicPage extends AppCompatActivity {
    private FileManager fileManager;
    private Comic comic;

    //Save the state when you navigate between the navbar tabs
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comic_page);

        Intent intent = getIntent();
        comic = (Comic) intent.getSerializableExtra("comic");

        // Resize the textview based on screen dimension
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int dpHeight = (int)((displayMetrics.heightPixels / displayMetrics.density)/1.8);
        TextView descTemplate = (TextView) findViewById(R.id.descTemplate);
        ViewGroup.MarginLayoutParams newLayoutParams = (ViewGroup.MarginLayoutParams) descTemplate.getLayoutParams();
        newLayoutParams.setMargins(0,0,0,dpHeight);

        initializeButtons();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(comic.getIssue_title());

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        final int radius = 10;
        final int margin = 10;
        Picasso.get().load(comic.getIssue_link_image()).transform(new RoundBorderTransformation(radius,margin)).resize(400, 550).into(imageView);

        TextView titleView = (TextView)findViewById(R.id.titleView);
        titleView.setSelected(true);
        titleView.setText(comic.getIssue_title());


        initializeTextView();
    }

    //Initialize the Bought,Read,Desire buttons
    private void initializeButtons() {
        fileManager = new FileManager(getApplicationContext());

        final FloatingActionButton fabB = findViewById(R.id.fabBought);
        //Check if the comic is in the bought list, if so color the button
        if(fileManager.containsIssue("bought",comic.getLink_albo()))
            fabB.setImageResource(R.drawable.buy_colored);

        fabB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the comic is in the bought list, if so remove from it and set the blank button, else add do it and color the button
                if(fileManager.containsIssue("bought",comic.getLink_albo())) {
                    Snackbar.make(view, "Comic removed to your Bought List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    fileManager.deleteComic("bought", comic.getLink_albo());
                    fabB.setImageResource(R.drawable.buy_blank);
                }
                else {
                    Snackbar.make(view, "Comic added to your Bought List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fileManager.addComic("bought",comic.getLink_albo());
                    fabB.setImageResource(R.drawable.buy_colored);
                }
            }
        });

        final FloatingActionButton fabR = findViewById(R.id.fabRead);
        //Check if the comic is in the read list, if so color the button
        if(fileManager.containsIssue("read",comic.getLink_albo()))
            fabR.setImageResource(R.drawable.read_colored);

        fabR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the comic is in the read list, if so remove from it and set the blank button, else add do it and color the button
                if(fileManager.containsIssue("read",comic.getLink_albo())) {
                    Snackbar.make(view, "Comic removed to your Read List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fileManager.deleteComic("read", comic.getLink_albo());
                    fabR.setImageResource(R.drawable.read_blank);
                }
                else {
                    Snackbar.make(view, "Comic added to your Read List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fileManager.addComic("read",comic.getLink_albo());
                    fabR.setImageResource(R.drawable.read_colored);
                }
            }
        });

        final FloatingActionButton fabD = findViewById(R.id.fabDesire);
        //Check if the comic is in the desire list, if so color the button
        if(fileManager.containsIssue("desire",comic.getLink_albo()))
            fabD.setImageResource(R.drawable.desire_colored);

        fabD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check if the comic is in the desire list, if so remove from it and set the blank button, else add do it and color the button
                if(fileManager.containsIssue("desire",comic.getLink_albo())) {
                    Snackbar.make(view, "Comic removed to your Desire List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fileManager.deleteComic("desire", comic.getLink_albo());
                    fabD.setImageResource(R.drawable.desire_blank);
                }
                else {
                    Snackbar.make(view, "Comic added to your Desire List", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    fileManager.addComic("desire",comic.getLink_albo());
                    fabD.setImageResource(R.drawable.desire_colored);
                }
            }
        });
    }

    private void initializeTextView() {
        if (!comic.getIssue_subtitle().equals("null")) {
            TextView subTitleView = (TextView) findViewById(R.id.subTitleView);
            subTitleView.setSelected(true);
            subTitleView.setText(comic.getIssue_subtitle());
        }

        if (!comic.getSerie_title().equals("null")) {
            TextView seriesView = (TextView) findViewById(R.id.seriesView);
            seriesView.setSelected(true);
            seriesView.setText(comic.getSerie_title());
        }

        if (!comic.getIssue_description().equals("null")) {
            TextView descView = (TextView) findViewById(R.id.descView);
            descView.setMovementMethod(new ScrollingMovementMethod());
            descView.setText(comic.getIssue_description());
        }

        if (!comic.getIssue_date().equals("null")) {
            TextView dateView = (TextView) findViewById(R.id.dateView);
            //String date = comic.getIssue_date().replace("-01", "");
            //String year = date.split("-")[0];
            //String month = date.split("-")[1];
            //String textDate = month + "/" + year;
            dateView.setSelected(true);
            dateView.setText(comic.getIssue_date());
        }
        if (!comic.getIssue_originalstories().equals("null")) {
            TextView includeView = (TextView) findViewById(R.id.includeView);
            includeView.setMovementMethod(new ScrollingMovementMethod());
            includeView.setText(comic.getIssue_originalstories());
        }

        if (!comic.getPublisher().equals("null")) {
            TextView publisherView = (TextView) findViewById(R.id.publisherView);
            publisherView.setSelected(true);
            publisherView.setText(comic.getPublisher());
        }

        if (!comic.getSerie_numbers().equals("null")) {
            TextView numbersView = (TextView) findViewById(R.id.numbersView);
            numbersView.setSelected(true);
            numbersView.setText(comic.getSerie_numbers());
        }
    }
}
