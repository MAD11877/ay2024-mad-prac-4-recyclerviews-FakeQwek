package sg.edu.np.mad.madpractical4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        User user = new User("John Doe", "MAD Developer", 1, false);
        Intent receiveData = getIntent();

        String Id = receiveData.getStringExtra("Id");
        String Desc = receiveData.getStringExtra("Desc");
        //Set and apply text views
        TextView tvName = findViewById(R.id.title);
        TextView tvDescription = findViewById(R.id.desc);
        TextView btnFollow = findViewById(R.id.button);
        TextView btnMessage = findViewById(R.id.button2);
        TextView tvTitle = findViewById(R.id.headLine);
        tvName.setText("John Doe");
        if (Id == null) {
            tvTitle.setText("MAD");
            tvDescription.setText("Practical 4");
        }
        else {
            tvTitle.setText(Id);
            tvDescription.setText(Desc);

        }
        tvName.setText("MAD Practical");
        btnFollow.setText("Follow");
        btnMessage.setText("Message");

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnFollow.getText() == "Follow") {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                else {
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usersView = new Intent(MainActivity.this, ListActivity.class);
                startActivity(usersView);
            }
        });
    }
}