//package com.example.busisiwe_magae_2110949_resit;
//
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.view.MenuItem;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserFactory;
//
//import java.io.StringReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    public static final String DRIVER_STANDINGS_URL = "https://example.com/driverStandings";
//    public static final String RACE_SCHEDULE_URL = "https://example.com/raceSchedule";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Allow network operation on the main thread for simplicity (not recommended for production apps)
//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
//
//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        // Load the default fragment
//        loadFragment(new DriverStandingsFragment());
//    }
//
//    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            Fragment fragment = null;
//            switch (item.getItemId()) {
//                case R.id.navigation_drivers:
//                    fragment = new DriverStandingsFragment();
//                    break;
//                case R.id.navigation_schedule:
//                    fragment = new RaceScheduleFragment();
//                    break;
//                default:
//                    return false;
//            }
//            return loadFragment(fragment);
//        }
//    };
//
//    private boolean loadFragment(Fragment fragment) {
//        // switch the fragment
//        if (fragment != null) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, fragment)
//                    .commit();
//            return true;
//        }
//        return false;
//    }
//
//    // Fetch data from the API
//    public String getDataFromApi(String urlString) {
//        StringBuilder result = new StringBuilder();
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int data;
//            try (var reader = new InputStreamReader(connection.getInputStream())) {
//                while ((data = reader.read()) != -1) {
//                    result.append((char) data);
//                }
//            }
//            connection.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result.toString();
//    }
//
//    // Parse driver standings from XML
//    public List<Driver> parseDriverStandings(String xml) {
//        List<Driver> drivers = new ArrayList<>();
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = factory.newPullParser();
//            parser.setInput(new StringReader(xml));
//
//            int eventType = parser.getEventType();
//            Driver currentDriver = null;
//
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                String tagName = parser.getName();
//                switch (eventType) {
//                    case XmlPullParser.START_TAG:
//                        if ("driver".equalsIgnoreCase(tagName)) {
//                            currentDriver = new Driver();
//                        } else if (currentDriver != null) {
//                            if ("name".equalsIgnoreCase(tagName)) {
//                                currentDriver.setName(parser.nextText());
//                            } else if ("team".equalsIgnoreCase(tagName)) {
//                                currentDriver.setTeam(parser.nextText());
//                            } else if ("points".equalsIgnoreCase(tagName)) {
//                                currentDriver.setPoints(Integer.parseInt(parser.nextText()));
//                            }
//                        }
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if ("driver".equalsIgnoreCase(tagName) && currentDriver != null) {
//                            drivers.add(currentDriver);
//                        }
//                        break;
//                }
//                eventType = parser.next();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return drivers;
//    }
//
//    // Parse race schedule from XML
//    public List<Race> parseRaceSchedule(String xml) {
//        List<Race> races = new ArrayList<>();
//        try {
//            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//            XmlPullParser parser = factory.newPullParser();
//            parser.setInput(new StringReader(xml));
//
//            int eventType = parser.getEventType();
//            Race currentRace = null;
//
//            while (eventType != XmlPullParser.END_DOCUMENT) {
//                String tagName = parser.getName();
//                switch (eventType) {
//                    case XmlPullParser.START_TAG:
//                        if ("race".equalsIgnoreCase(tagName)) {
//                            currentRace = new Race();
//                        } else if (currentRace != null) {
//                            if ("location".equalsIgnoreCase(tagName)) {
//                                currentRace.setLocation(parser.nextText());
//                            } else if ("date".equalsIgnoreCase(tagName)) {
//                                currentRace.setDate(parser.nextText());
//                            }
//                        }
//                        break;
//
//                    case XmlPullParser.END_TAG:
//                        if ("race".equalsIgnoreCase(tagName) && currentRace != null) {
//                            races.add(currentRace);
//                        }
//                        break;
//                }
//                eventType = parser.next();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return races;
//    }
//}

package com.example.busisiwe_magae_2110949_resit;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MainActivity extends AppCompatActivity {

    public static final String DRIVER_STANDINGS_URL = "https://example.com/driverStandings";
    public static final String RACE_SCHEDULE_URL = "https://example.com/raceSchedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Allow network operation on the main thread for simplicity (not recommended for production apps)
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Load the default fragment
        loadFragment(new DriverStandingsFragment());
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_drivers) {
                fragment = new DriverStandingsFragment();
            } else if (itemId == R.id.navigation_schedule) {
                fragment = new RaceScheduleFragment();
            } else {
                return false;
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        // Switch the fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    // Fetch data from the API
    public String getDataFromApi(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int data;
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                while ((data = reader.read()) != -1) {
                    result.append((char) data);
                }
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    // Parse driver standings from XML
    public List<Driver> parseDriverStandings(String xml) {
        List<Driver> drivers = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            Driver currentDriver = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                if (eventType == XmlPullParser.START_TAG) {
                    if ("driver".equalsIgnoreCase(tagName)) {
                        currentDriver = new Driver();
                    } else if (currentDriver != null) {
                        if ("name".equalsIgnoreCase(tagName)) {
                            currentDriver.setName(parser.nextText());
                        } else if ("team".equalsIgnoreCase(tagName)) {
                            currentDriver.setName(parser.nextText());
                        } else if ("points".equalsIgnoreCase(tagName)) {
                            currentDriver.setPoints(String.valueOf(Integer.parseInt(parser.nextText())));
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if ("driver".equalsIgnoreCase(tagName) && currentDriver != null) {
                        drivers.add(currentDriver);
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Parse race schedule from XML
    public List<Race> parseRaceSchedule(String xml) {
        List<Race> races = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));

            int eventType = parser.getEventType();
            Race currentRace = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                if (eventType == XmlPullParser.START_TAG) {
                    if ("race".equalsIgnoreCase(tagName)) {
                        currentRace = new Race();
                    } else if (currentRace != null) {
                        if ("location".equalsIgnoreCase(tagName)) {
                            currentRace.setCircuitName(parser.nextText());
                        } else if ("date".equalsIgnoreCase(tagName)) {
                            currentRace.setDate(parser.nextText());
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if ("race".equalsIgnoreCase(tagName) && currentRace != null) {
                        races.add(currentRace);
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return races;
    }
}
