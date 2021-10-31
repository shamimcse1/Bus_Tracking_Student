package codercamp.com.bustrackingstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    String value = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Location");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                TextView textView = (TextView) findViewById(R.id.locText);
                textView.setText(value);
                String[] separated = value.split(",");
                String latiPos = separated[0].trim();
                String longiPos = separated[1].trim();
                double dLat = Double.parseDouble(latiPos);
                double dLong = Double.parseDouble(longiPos);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("LOCVAL", value);
        startActivity(intent);
    }
}