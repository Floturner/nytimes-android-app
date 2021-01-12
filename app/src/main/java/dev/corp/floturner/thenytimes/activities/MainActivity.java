package dev.corp.floturner.thenytimes.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import dev.corp.floturner.thenytimes.R;
import dev.corp.floturner.thenytimes.fragments.HomeFragment;
import dev.corp.floturner.thenytimes.fragments.MoviesFragment;
import dev.corp.floturner.thenytimes.fragments.PoliticsFragment;
import dev.corp.floturner.thenytimes.fragments.ScienceFragment;
import dev.corp.floturner.thenytimes.fragments.SportsFragment;
import dev.corp.floturner.thenytimes.fragments.TechnologyFragment;
import dev.corp.floturner.thenytimes.server.APIClient;
import dev.corp.floturner.thenytimes.server.APIService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static APIService apiService;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private HomeFragment homeFragment;
    private MoviesFragment moviesFragment;
    private PoliticsFragment politicsFragment;
    private ScienceFragment scienceFragment;
    private SportsFragment sportsFragment;
    private TechnologyFragment technologyFragment;

    private static final int HOME_FRAGMENT = 0;
    private static final int MOVIES_FRAGMENT = 1;
    private static final int POLITICS_FRAGMENT = 2;
    private static final int SCIENCE_FRAGMENT = 3;
    private static final int SPORTS_FRAGMENT = 4;
    private static final int TECHNOLOGY_FRAGMENT = 5;

    public static final String SAVED_STATE = "saved_state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawer = findViewById(R.id.drawer_layout);

        this.navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();

        this.navigationView.setNavigationItemSelectedListener(this);

        apiService = APIClient.getClient().create(APIService.class);

        showFirstFragment();
    }

    @Override
    public void onBackPressed() {
        if (this.drawer.isDrawerOpen(GravityCompat.START)) {
            this.drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (!homeFragment.isVisible()) {
                this.navigationView.setCheckedItem(R.id.nav_home);
                this.showFragment(HOME_FRAGMENT);
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                this.showFragment(HOME_FRAGMENT);
                break;

            case R.id.nav_movies:
                this.showFragment(MOVIES_FRAGMENT);
                break;

            case R.id.nav_politics:
                this.showFragment(POLITICS_FRAGMENT);
                break;

            case R.id.nav_science:
                this.showFragment(SCIENCE_FRAGMENT);
                break;

            case R.id.nav_sports:
                this.showFragment(SPORTS_FRAGMENT);
                break;

            case R.id.nav_technology:
                this.showFragment(TECHNOLOGY_FRAGMENT);
                break;

            default:
                break;
        }

        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFirstFragment() {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (visibleFragment == null)
            this.showFragment(HOME_FRAGMENT);
    }

    private void showFragment(int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case HOME_FRAGMENT:
                if (this.homeFragment == null)
                    this.homeFragment = HomeFragment.newInstance();
                this.startTransactionFragment(this.homeFragment);
                break;

            case MOVIES_FRAGMENT:
                if (this.moviesFragment == null)
                    this.moviesFragment = MoviesFragment.newInstance();
                this.startTransactionFragment(this.moviesFragment);
                break;

            case POLITICS_FRAGMENT:
                if (this.politicsFragment == null)
                    this.politicsFragment = PoliticsFragment.newInstance();
                this.startTransactionFragment(this.politicsFragment);
                break;

            case SCIENCE_FRAGMENT:
                if (this.scienceFragment == null)
                    this.scienceFragment = ScienceFragment.newInstance();
                this.startTransactionFragment(this.scienceFragment);
                break;

            case SPORTS_FRAGMENT:
                if (this.sportsFragment == null)
                    this.sportsFragment = SportsFragment.newInstance();
                this.startTransactionFragment(this.sportsFragment);
                break;

            case TECHNOLOGY_FRAGMENT:
                if (this.technologyFragment == null)
                    this.technologyFragment = TechnologyFragment.newInstance();
                this.startTransactionFragment(this.technologyFragment);
                break;

            default:
                break;
        }
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}
