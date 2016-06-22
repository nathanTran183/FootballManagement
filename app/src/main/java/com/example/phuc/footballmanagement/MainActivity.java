package com.example.phuc.footballmanagement;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phuc.footballmanagement.Fragment.AddFieldFragment;
import com.example.phuc.footballmanagement.Fragment.FieldListFragment;
import com.example.phuc.footballmanagement.Fragment.AddMatchFragment;
import com.example.phuc.footballmanagement.Fragment.MatchListFragment;
import com.example.phuc.footballmanagement.Model.ItemSlidingMenu;
import com.example.phuc.footballmanagement.Model.User;
import com.example.phuc.footballmanagement.adapter.SlidingMenuAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout slidingLayout;
    private ListView lvSlidingMenu;
    String colorActionBar = "#95a5a6";
    AlertDialog.Builder b;
    TextView tevUserName,tevEmail;

    private ArrayList<ItemSlidingMenu> itemSlidingMenu;
    public SlidingMenuAdapter slidingMenuAdapter;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        assert getSupportActionBar() != null;
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(colorActionBar)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        User user = (User)intent.getSerializableExtra("USER");
        tevUserName.setText(user.getUsername());
        tevEmail.setText(user.getEmail());

        itemSlidingMenu = new ArrayList<ItemSlidingMenu>();
        itemSlidingMenu.add(new ItemSlidingMenu(R.drawable.iconarrow, "Setup Match"));
        itemSlidingMenu.add(new ItemSlidingMenu(R.drawable.iconarrow, "Match List"));
        itemSlidingMenu.add(new ItemSlidingMenu(R.drawable.iconarrow, "Add Field"));
        itemSlidingMenu.add(new ItemSlidingMenu(R.drawable.iconarrow, "Field List"));
        itemSlidingMenu.add(new ItemSlidingMenu(R.drawable.iconarrow, "Logout"));

        slidingMenuAdapter = new SlidingMenuAdapter(this, R.layout.item_sliding_menu, itemSlidingMenu);
        lvSlidingMenu.setAdapter(slidingMenuAdapter);

        lvSlidingMenu.setOnItemClickListener(new DrawerItemClickListener());

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        b = new AlertDialog.Builder(MainActivity.this);
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = new MatchListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.contentMain, fragment)
                .commit();
    }

    private void initView() {
        lvSlidingMenu = (ListView) findViewById(R.id.lvSlidingMenu);
        lvSlidingMenu.setDivider(null);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        slidingLayout = (LinearLayout) findViewById(R.id.slidingMenu);
        tevUserName = (TextView) findViewById(R.id.lblUserNameMenu);
        tevEmail = (TextView) findViewById(R.id.lblEmailMenu);
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setTitle(itemSlidingMenu.get(position).getItemName());
            drawerLayout.closeDrawer(slidingLayout);
            moveFragment(itemSlidingMenu.get(position).getItemName());


        }
    }

    private void moveFragment(String position) {
        FragmentManager fragmentManager = getFragmentManager();
        if(position.equals("Setup Match")){
            Fragment fragment = new AddMatchFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentMain,fragment )
                    .commit();
        }
        if(position.equals("Match List")){
            Fragment fragment = new MatchListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentMain,fragment )
                    .commit();
        }
        if(position.equals("Add Field")){
            Fragment fragment = new AddFieldFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentMain,fragment )
                    .commit();
        }
        if(position.equals("Field List")){
            Fragment fragment = new FieldListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentMain,fragment )
                    .commit();
        }
        if(position.equals("Logout"))
            getB().create().show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            getB().create().show();
        }
        return super.onKeyDown(keyCode, event);
    }

    public AlertDialog.Builder getB() {
        b.setTitle("Football Network Management");
        b.setMessage("Are you want to logout?");
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return b;
    }
}