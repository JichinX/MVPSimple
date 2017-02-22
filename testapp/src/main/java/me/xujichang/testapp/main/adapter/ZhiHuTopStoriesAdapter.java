package me.xujichang.testapp.main.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import me.xujichang.testapp.R;
import me.xujichang.testapp.bean.ZhiHuStory;

/**
 * Created by Administrator on 2017/2/22.
 */
public class ZhiHuTopStoriesAdapter extends PagerAdapter {
    private ArrayList<ZhiHuStory> stories;
    private RequestManager manager;


    public ZhiHuTopStoriesAdapter(ArrayList<ZhiHuStory> stories, RequestManager manager) {
        this.stories = (stories == null ? new ArrayList<ZhiHuStory>() : stories);
        this.manager = manager;
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Holder holder = null;
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.item_pager, null);
        holder = new Holder(view);
        holder.bindData(stories.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private class Holder {
        TextView tvTitle;
        ImageView ivStory;


        Holder(View itemView) {
            ivStory = (ImageView) itemView.findViewById(R.id.iv_pager_item_image);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_pager_item_title);
        }

        void bindData(ZhiHuStory zhiHuStory) {
            tvTitle.setText(zhiHuStory.getTitle());
            manager.load(zhiHuStory.getImage()).error(R.drawable.bg_edit_login).into(ivStory);
        }
    }
}
