package com.yueyue.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * author : yueyue on 2018/3/25 21:04
 * desc   :
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private static final String TAG = FruitAdapter.class.getSimpleName();

    private OnClickListenter onClickListenter;

    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    @Override
    public int getItemCount() {
        return mFruitList == null ? 0 : mFruitList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.bind(fruit, onClickListenter);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        ImageView mIvFruit;

        TextView mIvName;

        public ViewHolder(View view) {
            super(view);
            this.view=view;
            mIvFruit =view.findViewById(R.id.iv_fruit);
            mIvName =view.findViewById(R.id.tv_fruit);
        }

        void bind(Fruit fruit, OnClickListenter onClickListenter) {
            if (fruit == null) {
                return;
            }
            mIvName.setText(fruit.getName());
            mIvFruit.setImageResource(fruit.getImageId());

            view.setOnClickListener(v -> {
                if (onClickListenter != null) {
                    onClickListenter.onClick(fruit);
                }
            });
        }
    }

    public void setOnClickListenter(OnClickListenter onClickListenter) {
        this.onClickListenter = onClickListenter;
    }

    public interface OnClickListenter {
        void onClick(Fruit fruit);
    }

}