package com.yueyue.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class FruitActivity extends BaseActivity {

    private static final String EXTRA_FURIT = "furit";

    @BindView(R.id.iv_fruit)
    ImageView mIvFruit;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.tv_fruit_content)
    TextView mTvFruitContent;

    private Fruit mFruit;

    public static void launch(Context context, Fruit fruit) {
        Intent intent = new Intent(context, FruitActivity.class);
        if (fruit != null) {
            intent.putExtra(EXTRA_FURIT, fruit);
        }
        context.startActivity(intent);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_fruit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intData();
        initToolbar();
        setupView();
    }


    private void intData() {
        mFruit = getIntent().getParcelableExtra(EXTRA_FURIT);
    }


    private void setupView() {
        if (mFruit != null) {
            mIvFruit.setImageResource(mFruit.getImageId());
            String fruitContent = generateFruitContent(mFruit.getName());
            mTvFruitContent.setText(fruitContent);
        }
    }


    private void initToolbar() {
        if (mFruit != null)
            mCollapsingToolbar.setTitle(mFruit.getName());
    }


    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
