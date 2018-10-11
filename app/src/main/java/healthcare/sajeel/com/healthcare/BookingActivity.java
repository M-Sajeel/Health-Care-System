package healthcare.sajeel.com.healthcare;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import healthcare.sajeel.com.healthcare.adapter.BookingAdapter;
import healthcare.sajeel.com.healthcare.model.BookingListing;
import healthcare.sajeel.com.healthcare.model.CustomTextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class BookingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SwipeMenuListView swipeListView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarBooking);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_booking);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_booking);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            View headerView = navigationView.getHeaderView(0);
            CustomTextView email = (CustomTextView) headerView.findViewById(R.id.userEmail);
            email.setText(currentUser.getEmail());
        }

        getBookingItems();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_booking);
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
            startActivity(new Intent(BookingActivity.this, DashboardActivity.class));
        } else if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Toast.makeText(this, "You can see reports in this section",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(BookingActivity.this, AppointmentsActivity.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(BookingActivity.this, ProfileActivity.class));
        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "You can share the application with your friends and family",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Invite your friends via email",
                    Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(BookingActivity.this, SigninActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_booking);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getBookingItems() {
        swipeListView = (SwipeMenuListView) findViewById(R.id.bookingListView);
        final BookingAdapter bookingAdapter = new BookingAdapter(this, booking);
        swipeListView.setAdapter(bookingAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem detailsItem = new SwipeMenuItem(getApplicationContext());
                detailsItem.setBackground(new ColorDrawable(Color.rgb(5, 144,80)));
                detailsItem.setWidth(160);
                detailsItem.setTitleColor(Color.WHITE);
                detailsItem.setTitle("Details");
                detailsItem.setTitleSize(15);
//                upVoteItem.setIcon(R.drawable.ic_vote_up);
                menu.addMenuItem(detailsItem);

                // create "delete" item
                SwipeMenuItem bookItem = new SwipeMenuItem(getApplicationContext());
                bookItem.setBackground(new ColorDrawable(Color.rgb(228,32, 17)));
                bookItem.setWidth(160);
                bookItem.setTitleColor(Color.WHITE);
                bookItem.setTitle("Book");
                bookItem.setTitleSize(15);
//                downVoteItem.setIcon(R.drawable.ic_vote_down);
                menu.addMenuItem(bookItem);
            }
        };

        swipeListView.setMenuCreator(creator);

        swipeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "You can view the details of this physician!", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "You have booked this doctor on one of his upcoming suggested date!", Toast.LENGTH_LONG).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    private BookingListing[] booking = {
            new BookingListing("Dr. Tariq Zahid", "Andrologist","Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar2),
            new BookingListing("Dr. Ismail Khan", "Allergist","Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar3),
            new BookingListing("Dr. Farzana Shahrukh", "Radiologist", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar4),
            new BookingListing("Dr. Kamal Hasan", "Epidemiologist","Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar5),
            new BookingListing("Dr. Farzana Shahrukh", "Intensivist", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar6),
            new BookingListing("Dr. Tariq Zahid", "Chiropractor", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar7),
            new BookingListing("Dr. Kamal Hasan", "Oncologist", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar2),
            new BookingListing("Dr. Ismail Khan", "Physiotherapist", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar6),
            new BookingListing("Dr. Farzana Shahrukh", "Radiologist", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar4),
            new BookingListing("Dr. Tariq Zahid", "Chiropractor", "Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar7),
            new BookingListing("Dr. Ismail Khan", "Allergist","Gulshan-e-Iqbal Block 13 D Karachi, Pakistan", R.drawable.avatar3),
    };
}