package com.Korea_boxoffice.jsonparsing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleMenuItemActivity  extends Activity {
	
	// JSON node keys
	private static final String TAG_MOVIENM = "movieNm";
	private static final String TAG_OPENDT = "openDt";
	private static final String TAG_AUDIACC = "audiAcc";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String name = in.getStringExtra(TAG_MOVIENM);
        String cost = in.getStringExtra(TAG_OPENDT);
        String description = in.getStringExtra(TAG_AUDIACC);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.movieNm);
        TextView lblCost = (TextView) findViewById(R.id.openDt);
        TextView lblDesc = (TextView) findViewById(R.id.audiAcc);
        
        lblName.setText(name);
        lblCost.setText(cost);
        lblDesc.setText(description);
    }
}
