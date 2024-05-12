package sg.edu.np.mad.madpractical4;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements UserListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<User> Users = new ArrayList<User>();
        Random rand = new Random();
        //Generates the user and description
        for (int i = 0; i <20; i++) {
          int name = rand.nextInt(10000);
          int description = rand.nextInt(10000);
          boolean followed = rand.nextBoolean();
          User user = new User("John Doe", "MAD Developer", 1, false);
          user.setName("Name " + String.valueOf(name));
          user.setDescription("Description " + description);
          user.setFollowed(followed);
          user.setId(name);
          Users.add(user);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        UserAdapter userAdapter = new UserAdapter(Users, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);
    }
    @Override
    public void onUserClicked(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Profile");
        builder.setMessage(user.getName());
        builder.setCancelable(false);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Intent PassId = new Intent(ListActivity.this, MainActivity.class);
                PassId.putExtra("Id", user.getName());
                PassId.putExtra("Desc", user.getDescription());
                startActivity(PassId);
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.show();
    }

}