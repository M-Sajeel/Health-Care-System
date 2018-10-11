package healthcare.sajeel.com.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import healthcare.sajeel.com.healthcare.adapter.AppointmentsAdapter;
import healthcare.sajeel.com.healthcare.model.Appointments;
import healthcare.sajeel.com.healthcare.model.CustomTextView;

public class AppointmentsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAppointments);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_appointments);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_appointments);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            View headerView = navigationView.getHeaderView(0);
            CustomTextView email = (CustomTextView) headerView.findViewById(R.id.userEmail);
            email.setText(currentUser.getEmail());
        }

        GridView gridView = findViewById(R.id.gridView);
        final AppointmentsAdapter appointmentsAdapter = new AppointmentsAdapter(this, appointments);
        gridView.setAdapter(appointmentsAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_appointments);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(AppointmentsActivity.this, DashboardActivity.class));
        } else if (id == R.id.nav_camera) {
            startActivity(new Intent(AppointmentsActivity.this, BookingActivity.class));
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "You can see reports in this section",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(AppointmentsActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "You can share the application with your friends and family",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Invite your friends via email",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AppointmentsActivity.this, SigninActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_appointments);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Appointments[] appointments = {
            new Appointments("1", "Dr. Tariq Zahid", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan",
                    "13:00 - 13:30", R.drawable.background1, R.drawable.avatar6),
            new Appointments("1", "Dr. Faisal Shahzad", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan",
                    "09:00 - 10:30", R.drawable.background1, R.drawable.avatar2),
            new Appointments("1", "Dr. Ismail Khan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan",
                    "17:00 - 18:00", R.drawable.background1, R.drawable.avatar5),
            new Appointments("1", "Dr. Farzana Shahrukh", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan",
                    "12:00 - 13:30", R.drawable.background1, R.drawable.avatar7),
            new Appointments("1", "Dr. Kamal Hasan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan",
                    "10:00 - 10:30", R.drawable.background1, R.drawable.avatar3),
    };
}