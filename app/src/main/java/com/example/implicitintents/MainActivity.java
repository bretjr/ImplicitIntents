package com.example.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class MainActivity extends AppCompatActivity {

    // EditText variables
    private EditText websiteEditText;
    private EditText locationEditText;
    private EditText shareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        websiteEditText = findViewById(R.id.website_editText);
        locationEditText = findViewById(R.id.location_editText);
        shareEditText = findViewById(R.id.share_editText);

    }

    public void openWebsite(View view) {
        // Get the URL text
        String url = websiteEditText.getText().toString();

        // Parse the Uri and create the intent
        Uri webpageUri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpageUri);

        // Find an activity to handle the intent and start that activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void openLocation(View view) {
        // Get the location text
        String loc = locationEditText.getText().toString();

        // Parse the location Uri and create the intent
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent and start that activity
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    public void shareText(View view) {
        // Get the message from the share text EditText
        String txt = shareEditText.getText().toString();

        // Create the ShareCompat.IntentBuilder
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
}
