package com.Korea_boxoffice.jsonparsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AndroidJSONParsingActivity extends ListActivity {

	// url to make request
	private static String url =  "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=cb126c3a6eb0ad55f1d75067cd9a6060&targetDt=20171018";
	
	// JSON Node names
	private static final String TAG_DAILYBOXOFFICELIST = "dailyBoxOfficeList";

	private static final String TAG_RNUM = "rnum";
	private static final String TAG_RANK = "rank";
	private static final String TAG_RANKINTEN = "rankInten";
	private static final String TAG_RANKOLDANDNEW = "rankOldAndNew";
	private static final String TAG_MOVIECD = "movieCd";
	private static final String TAG_MOVIENM = "movieNm";
	private static final String TAG_OPENDT = "openDt";
	private static final String TAG_SALESAMT = "salesAmt";
	private static final String TAG_SALESSHARE = "salesShare";
	private static final String TAG_SALESINTEN = "salesInten";
	private static final String TAG_SALESCHANGE = "salesChange";
	private static final String TAG_SALESACC = "salesAcc";
	private static final String TAG_AUDICNT = "audiCnt";
	private static final String TAG_AUDIINTEN = "audiInten";
	private static final String TAG_AUDICHANGE = "audiChange";
	private static final String TAG_AUDIACC = "audiAcc";
	private static final String TAG_SCRNCNT = "scrnCnt";
	private static final String TAG_SHOWCNT = "showCnt";

	// contacts JSONArray
	//JSONArray dailyBoxOfficeList = null;

	public String loadJSONFromAsset() {
		String json = null;
		try {

			InputStream is = getAssets().open("boxoffice.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json = new String(buffer, "UTF-8");

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			// Getting Array of Contacts
			//dailyBoxOfficeList = json.getJSONArray(TAG_DAILYBOXOFFICELIST);

			JSONObject obj = new JSONObject(loadJSONFromAsset());
			JSONArray dailyBoxOfficeList = obj.getJSONArray("dailyBoxOfficeList");
			// looping through All Contacts
			for(int i = 0; i < dailyBoxOfficeList.length(); i++){
				JSONObject c = dailyBoxOfficeList.getJSONObject(i);

				String rnum = c.getString(TAG_RNUM);
				String rank = c.getString(TAG_RANK);
				String rankInten = c.getString(TAG_RANKINTEN);
				String rankOldAndNew = c.getString(TAG_RANKOLDANDNEW);
				String movieCd = c.getString(TAG_MOVIECD);
				String movieNm = c.getString(TAG_MOVIENM);
				String openDt = c.getString(TAG_OPENDT);
				String salesAmt = c.getString(TAG_SALESAMT);
				String salesShare = c.getString(TAG_SALESSHARE);
				String salesInten = c.getString(TAG_SALESINTEN);
				String salesCahge = c.getString(TAG_SALESCHANGE);
				String salesAcc = c.getString(TAG_SALESACC);
				String audiCnt = c.getString(TAG_AUDICNT);
				String audiInten = c.getString(TAG_AUDIINTEN);
				String audiChange = c.getString(TAG_AUDICHANGE);
				String audiAcc = c.getString(TAG_AUDIACC);
				String scrnCnt = c.getString(TAG_SCRNCNT);
				String showCnt = c.getString(TAG_SHOWCNT);
				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();
				
				// adding each child node to HashMap key => value
				map.put(TAG_RANK, rank);
				map.put(TAG_MOVIENM, movieNm);
				map.put(TAG_OPENDT, openDt);
				map.put(TAG_AUDIACC, audiAcc);

				// adding HashList to ArrayList
				contactList.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		/**
		 * Updating parsed JSON data into ListView
		 * */
		ListAdapter adapter = new SimpleAdapter(this, contactList,
				R.layout.list_item,
				new String[] { TAG_MOVIENM, TAG_OPENDT, TAG_AUDIACC }, new int[] {
						R.id.movieNm, R.id.openDt, R.id.audiAcc });

		setListAdapter(adapter);

		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String name = ((TextView) view.findViewById(R.id.movieNm)).getText().toString();
				String cost = ((TextView) view.findViewById(R.id.openDt)).getText().toString();
				String description = ((TextView) view.findViewById(R.id.audiAcc)).getText().toString();
				
				// Starting new intent
				Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
				in.putExtra(TAG_MOVIENM, name);
				in.putExtra(TAG_OPENDT, cost);
				in.putExtra(TAG_AUDIACC, description);
				startActivity(in);

			}
		});
		
		

	}

}