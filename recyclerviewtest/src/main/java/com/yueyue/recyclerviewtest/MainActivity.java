package com.yueyue.recyclerviewtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    public static final long DRAWER_CLOSE_DELAY = 230L;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_fresh)
    SwipeRefreshLayout mSwipeFresh;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Watermelon", R.drawable.watermelon),
            new Fruit("Pear", R.drawable.pear),
            new Fruit("Grape", R.drawable.grape),
            new Fruit("Pineapple", R.drawable.pineapple),
            new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Mango", R.drawable.mango)};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter mAdapter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initNavigationView();
        initSwipeFresh();
        initAdapter();
        initFruits();

    }

    private void initSwipeFresh() {
        mSwipeFresh.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent);

        mSwipeFresh.setOnRefreshListener(() -> refreshFruits());
    }


    private void initToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }


    private void initNavigationView() {
        View headerView = mNavView.getHeaderView(0);
        ImageView ivPortrait = (ImageView) headerView.findViewById(R.id.iv_portrait);
        ivPortrait.setOnClickListener(v -> {
            Toast.makeText(this, "点击了头像", Toast.LENGTH_SHORT).show();
        });

        mNavView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            return true;
        });

    }

    private void initAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new FruitAdapter(fruitList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnClickListenter(fruit -> {
            FruitActivity.launch(MainActivity.this, fruit);
        });
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.fab)
    void showDeleteSnackbar() {
        Snackbar.make(mDrawerLayout, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo", v -> {
                    Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                })
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void refreshFruits() {
        mSwipeFresh.setRefreshing(true);
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                initFruits();
                mSwipeFresh.setRefreshing(false);
            });

        }).start();
    }
}
