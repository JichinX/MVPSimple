package me.xujichang.testapp.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;

import me.xujichang.testapp.R;
import me.xujichang.testapp.bean.ZhiHuStory;

/**
 * Created by Administrator on 2017/2/22.
 */
public class ZhiHuStoriesAdapter extends RecyclerView.Adapter<ZhiHuStoriesAdapter.ViewHolder> {

    private ArrayList<ZhiHuStory> zhiHuStories;
    private RequestManager manager;

    public ZhiHuStoriesAdapter(ArrayList<ZhiHuStory> zhiHuStories, RequestManager manager) {
        this.zhiHuStories = zhiHuStories;
        this.manager = manager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 20);
        view.setLayoutParams(params);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(zhiHuStories.get(position));
    }

    @Override
    public int getItemCount() {
        return zhiHuStories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivItem;

        private ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivItem = (ImageView) itemView.findViewById(R.id.iv_item);
        }

        void bindData(ZhiHuStory story) {
            tvTitle.setText(story.getTitle());
            String url = null;
            if (story.getImages().size() > 0) {
                url = story.getImages().get(0);
            }
            manager.load(url).error(R.drawable.bg_edit_login).into(ivItem);
        }
    }
}

