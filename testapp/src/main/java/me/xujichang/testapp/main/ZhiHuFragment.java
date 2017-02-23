package me.xujichang.testapp.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import me.xujichang.testapp.R;
import me.xujichang.testapp.bean.ZhiHuNewsBean;
import me.xujichang.testapp.bean.ZhiHuStory;
import me.xujichang.testapp.main.adapter.ZhiHuStoriesAdapter;
import me.xujichang.testapp.main.adapter.ZhiHuTopStoriesAdapter;

public class ZhiHuFragment extends Fragment implements MainContract.ZhiHuView {

    private ZhiHuTopStoriesAdapter topStoriesAdapter;
    private ZhiHuStoriesAdapter storiesAdapter;
    private MainContract.ZhiHuPresenter presenter;
    private OnFragmentInteractionListener mListener;
    private ArrayList<String> list;
    private ArrayList<ZhiHuStory> stories;
    private ArrayList<ZhiHuStory> topStories;
    private SwipeRefreshLayout refreshLayout;

    public ZhiHuFragment() {
        // Required empty public constructor
    }

    public static ZhiHuFragment newInstance() {
        ZhiHuFragment fragment = new ZhiHuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_zhi_hu, container, false);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_refresh);
        initViewPager((ViewPager) view.findViewById(R.id.vp_top_stories));
        initRecyclerView((RecyclerView) view.findViewById(R.id.rv_stories));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.add("sdadsd");
//                presenter.loadZhiHuInformation();

            }
        });
//        Button btnException = (Button) view.findViewById(R.id.btn_exception);
//
//        btnException.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("CrashHandler", "创建Exception");
//                list.add("haha");
//            }
//        });


        return view;
    }

    /**
     * 初始化RecyclerView
     *
     * @param recyclerView
     */
    private void initRecyclerView(RecyclerView recyclerView) {
        stories = new ArrayList<>();
        storiesAdapter = new ZhiHuStoriesAdapter(stories, Glide.with(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(storiesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * 初始化viewpager
     *
     * @param viewpager viewpager
     */
    private void initViewPager(ViewPager viewpager) {
        topStories = new ArrayList<>();
        topStoriesAdapter = new ZhiHuTopStoriesAdapter(topStories, Glide.with(this));
        viewpager.setAdapter(topStoriesAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(MainContract.ZhiHuPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onInitSuccess(ZhiHuNewsBean bean) {
        //初始化数据成功
        Log.i("ZhiHuFragment", "onInitSuccess: " + bean.getStories().toString());
        topStories.clear();
        topStories.addAll(bean.getTop_stories());
        topStoriesAdapter.notifyDataSetChanged();
        stories.clear();
        stories.addAll(bean.getStories());
        storiesAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInitFail() {
        //初始化数据失败
    }

    @Override
    public void onLoadSuccess() {
        //加载数据成功
    }

    @Override
    public void onLoadNothing() {
        //加载数据失败或者没更新数据

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
