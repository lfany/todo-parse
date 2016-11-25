package cc.ifnot.todoparse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.Logger;
import com.parse.ParseUser;

import cc.ifnot.todoparse.R;
import cc.ifnot.todoparse.TodoApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAnalytics firebaseAnalytics = TodoApp.getTodoApp().getFirebaseAnalytics();
        firebaseAnalytics.setAnalyticsCollectionEnabled(true);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference message = firebaseDatabase.getReference("message");
        message.setValue("Hello world!");
        message.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Logger.d("value changed: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Logger.w("Failed to read value: ", databaseError.toException());
            }
        });

        if(ParseUser.getCurrentUser() == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Welcome " + ParseUser.getCurrentUser().getUsername()
                    , Toast.LENGTH_SHORT).show();
        }

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

    }
}
