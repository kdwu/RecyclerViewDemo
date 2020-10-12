package com.zjgsu.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);

        // 线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "第" + position + "条数据被点击", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 添加数据
     * @param v
     */
    public void onAddDataClick(View v) {
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String s = "第" + i + "条数据";
            data.add(s);
        }

        mAdapter.setDataSource(data);
    }

    /**
     * 切换布局
     */
    public void onChangeLayoutClick(View v) {
        // 网格布局
        if (mRecyclerView.getLayoutManager().getClass() == LinearLayoutManager.class) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        }
        // 瀑布流布局
        else if (mRecyclerView.getLayoutManager().getClass() == GridLayoutManager.class) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        }
        // 线性布局
        else if (mRecyclerView.getLayoutManager().getClass() == StaggeredGridLayoutManager.class) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    /**
     * 插入一条数据
     * @param v
     */
    public void onInsertDataClick(View v) {
        mAdapter.addData(1);
    }

    /**
     * 删除一条数据
     * @param v
     */
    public void onRemoveDataClick(View v) {
        mAdapter.removeData(1);
    }
}
