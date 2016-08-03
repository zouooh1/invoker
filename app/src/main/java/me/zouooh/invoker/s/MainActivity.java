package me.zouooh.invoker.s;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import me.zouooh.invoker.IndexPath;
import me.zouooh.invoker.InvokerAdapter;
import me.zouooh.invoker.InvokerCell;
import me.zouooh.invoker.OnItemListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView1);
       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return  2;
                return 1;
            }
        });*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        TestAdapter testAdapter = new TestAdapter(null);
        testAdapter.init();
        recyclerView.setAdapter(testAdapter);
    }

    public class  TestAdapter extends InvokerAdapter{

        public TestAdapter(OnItemListener onRecyclerViewListener) {
            super(onRecyclerViewListener);
        }

        @Override
        public int getRowCountInSection(int sectionIndex) {
            if (sectionIndex == 0)
                return  1;
            return 8;
        }

        @Override
        public int getSectionCount() {
            return 2;
        }

        @Override
        protected boolean hasFootViewInSection(int sectionIndex) {
            return false;
        }

        @Override
        protected boolean hasHeadViewInSection(int sectionIndex) {
            return false;
        }

        @Override
        protected void initIndexPathType(IndexPath indexPath) {

        }

        @Override
        protected void onBindCellViewHolder(RecyclerView.ViewHolder viewHolder, IndexPath indexPath) {

        }

        @Override
        protected void onBindFootViewHolder(RecyclerView.ViewHolder viewHolder, IndexPath indexPath) {

        }

        @Override
        protected void onBindHeadViewHolder(RecyclerView.ViewHolder viewHolder, IndexPath indexPath) {

        }

        @Override
        protected RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int type) {
            return InvokerCell.viewHolder(parent,R.layout.cell_card);
        }

        @Override
        protected RecyclerView.ViewHolder onCreateFootViewHolder(ViewGroup parent, int type) {
            return null;
        }

        @Override
        protected RecyclerView.ViewHolder onCreateHeadViewHolder(ViewGroup parent, int type) {
            return null;
        }
    }
}
