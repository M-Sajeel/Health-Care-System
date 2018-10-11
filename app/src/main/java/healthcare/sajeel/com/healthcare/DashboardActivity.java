package healthcare.sajeel.com.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import healthcare.sajeel.com.healthcare.adapter.DashboardAdapter;
import healthcare.sajeel.com.healthcare.model.CustomTextView;
import healthcare.sajeel.com.healthcare.model.Dashboard;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FrameLayout mainScreen;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainScreen = (FrameLayout) findViewById(R.id.fragments);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            View headerView = navigationView.getHeaderView(0);
            CustomTextView email = (CustomTextView) headerView.findViewById(R.id.userEmail);
            email.setText(currentUser.getEmail());
        }

        GridView dashboardGridView = findViewById(R.id.dashboardGridView);
        final DashboardAdapter dashboardAdapter = new DashboardAdapter(this, dashboardData);
        dashboardGridView.setAdapter(dashboardAdapter);

        dashboardGridViewEventHandler(dashboardGridView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().findFragmentByTag("appointmentsFragment") != null) {
            getSupportFragmentManager().popBackStackImmediate("appointmentsFragment",0);
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

        } else if (id == R.id.nav_camera) {
            startActivity(new Intent(DashboardActivity.this, BookingActivity.class));
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "You can see reports in this section",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(DashboardActivity.this, AppointmentsActivity.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "You can share the application with your friends and family",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Invite your friends via email",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardActivity.this, SigninActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void dashboardGridViewEventHandler(GridView item) {
        item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(DashboardActivity.this, BookingActivity.class));
            }
        });
    }

    private Dashboard[] dashboardData = {
            new Dashboard("Dr. Tariq Zahid", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar2),
            new Dashboard("Dr. Ismail Khan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar3),
            new Dashboard("Dr. Farzana Shahrukh", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar4),
            new Dashboard("Dr. Kamal Hasan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar5),
            new Dashboard("Dr. Farzana Shahrukh", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar6),
            new Dashboard("Dr. Tariq Zahid", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar7),
            new Dashboard("Dr. Kamal Hasan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar2),
            new Dashboard("Dr. Ismail Khan", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar6),
    };
}