package com.example.paex3hockeyapp;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends ListActivity {

    static final String URL = "http://172.20.240.11:7002";
    static final String KEY_MATCH = "match";
    static final String KEY_HOMETEAM = "home_team";
    static final String KEY_HOMEGOALS = "home_goals";
    static final String KEY_AWAYTEAM = "visitor_team";
    static final String KEY_AWAYGOALS = "visitor_goals";
    ArrayList<HashMap<String,String>> results;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        results = new ArrayList<>();

        XMLParser parser = new XMLParser();
        String XML = parser.getXML(URL);
        Document document = parser.getDomElement(XML);

        NodeList nodeList = document.getElementsByTagName(KEY_MATCH);

        for(int i = 0; i<nodeList.getLength(); i++){
            HashMap<String,String> map = new HashMap<>();
            Element element = (Element) nodeList.item(i);

            map.put(KEY_HOMETEAM, parser.getValue(element, KEY_HOMETEAM));
            map.put(KEY_AWAYTEAM, parser.getValue(element, KEY_AWAYTEAM));
            map.put(KEY_HOMEGOALS, parser.getValue(element, KEY_HOMEGOALS));
            map.put(KEY_AWAYGOALS, parser.getValue(element, KEY_AWAYGOALS));

            results.add(map);
        }

        ListAdapter adapter = new SimpleAdapter(this, results, R.layout.list_item, new String[] {KEY_HOMETEAM, KEY_AWAYTEAM, KEY_HOMEGOALS, KEY_AWAYGOALS}, new int[] {
                R.id.textviewhome, R.id.textviewaway, R.id.textviewhomescore, R.id.textviewawayscore
        });

        setListAdapter(adapter);

        ListView listView = getListView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hometeam = ((TextView) view.findViewById(R.id.textviewhome)).getText().toString();
                String homegoals = ((TextView) view.findViewById(R.id.textviewhomescore)).getText().toString();
                String awayteam = ((TextView) view.findViewById(R.id.textviewaway)).getText().toString();
                String awaygoals = ((TextView) view.findViewById(R.id.textviewawayscore)).getText().toString();

                Intent intent = new Intent(getApplicationContext(), DisplayResultActivity.class);
                intent.putExtra(KEY_HOMETEAM, hometeam);
                intent.putExtra(KEY_HOMEGOALS, homegoals);
                intent.putExtra(KEY_AWAYTEAM, awayteam);
                intent.putExtra(KEY_AWAYGOALS, awaygoals);
                startActivity(intent);
            }
        });

    }


    public void parseXML() {


    }
}