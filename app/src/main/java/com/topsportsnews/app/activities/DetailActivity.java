package com.topsportsnews.app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.topsportsnews.activities.R;
import com.topsportsnews.app.models.SportsItem;


public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleText;
    private TextView imageCaptionText;
    private TextView bylineText;
    private TextView publishedDateText;
    private TextView descriptionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView= (ImageView) findViewById(R.id.image);
        titleText = (TextView)findViewById(R.id.titleTextView);
        imageCaptionText = (TextView) findViewById(R.id.imageCaptionText);
        bylineText = (TextView) findViewById(R.id.bylineText);
        publishedDateText = (TextView)findViewById(R.id.publishedDateText);
        descriptionText = (TextView)findViewById(R.id.descriptionText);


        SportsItem sportsItem = (SportsItem) getIntent().getSerializableExtra("sportsItem");
      if(sportsItem!=null)
      {   Picasso.with(DetailActivity.this).load(sportsItem.getThumbnail()).into(imageView);
        titleText.setText(sportsItem.getTitle());
        bylineText.setText(sportsItem.getByline());
        imageCaptionText.setText(sportsItem.getImageCaption());
        publishedDateText.setText("Published on " + sportsItem.getPublishedDate());
        descriptionText.setText("Stroy - "+sportsItem.getDescription());

    }}
}
