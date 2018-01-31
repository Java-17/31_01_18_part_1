package com.sheygam.java_17_31_01_18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class MainActivity extends AppCompatActivity /*implements MainActivity.MyItemTouchHelperCallback.MyTouchCallbackListener*/ {

    private RecyclerView myList;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new MyAdapter();
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);

        myList = findViewById(R.id.myList);
        myList.setLayoutManager(manager);
        myList.setAdapter(adapter);
        myList.addItemDecoration(divider);
//        ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelperCallback(this));
        ItemTouchHelper helper = new ItemTouchHelper(new MyItemTouchHelperCallback());
        helper.attachToRecyclerView(myList);
    }

//    @Override
//    public void swipedToStart(int position) {
//        adapter.remove(position);
//    }
//
//    @Override
//    public void swipedToEnd(int position) {
//        adapter.remove(position);
//    }
//
//    @Override
//    public void moved(int from, int to) {
//        adapter.move(from, to);
//    }

     class MyItemTouchHelperCallback extends ItemTouchHelper.Callback{

//        public MyItemTouchHelperCallback(MyTouchCallbackListener listener) {
//            this.listener = listener;
//        }

//        private MyTouchCallbackListener listener;
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.START|ItemTouchHelper.END);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            adapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
//            listener.moved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d("MY_TAG", "onSwiped() called with: viewHolder = [" + viewHolder + "], direction = [" + direction + "]");
            if(direction == ItemTouchHelper.START){
                adapter.remove(viewHolder.getAdapterPosition());
            }else if(direction == ItemTouchHelper.END){
                adapter.remove(viewHolder.getAdapterPosition());
            }

//            if(direction == ItemTouchHelper.START){
//                listener.swipedToStart(viewHolder.getAdapterPosition());
//            }else if(direction == ItemTouchHelper.END){
//                listener.swipedToEnd(viewHolder.getAdapterPosition());
//            }

        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

//        public interface MyTouchCallbackListener{
//            void swipedToStart(int position);
//            void swipedToEnd(int position);
//            void moved(int from, int to);
//        }

    }
}
