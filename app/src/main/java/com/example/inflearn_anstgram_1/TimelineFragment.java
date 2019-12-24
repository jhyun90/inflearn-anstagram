package com.example.inflearn_anstgram_1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.inflearn_anstgram_1.data.DataPostItem;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends Fragment {

    static ArrayList<DataPostItem> arrayList;

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        arrayList = new ArrayList<>();

        arrayList.add(new DataPostItem(0,
                "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "불꽃놀이했어요~", "ansta_", 1234, false));
        arrayList.add(new DataPostItem(1, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하이!", "g82", 200000, false));
        arrayList.add(new DataPostItem(2, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하ggggg이!", "g82", 200000, false));

        arrayList.add(new DataPostItem(0,
                "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "불꽃놀이했어요~", "ansta_", 1234, false));
        arrayList.add(new DataPostItem(1, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하이!", "g82", 200000, false));
        arrayList.add(new DataPostItem(2, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하ggggg이!", "g82", 200000, false));

        arrayList.add(new DataPostItem(0,
                "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "불꽃놀이했어요~", "ansta_", 1234, false));
        arrayList.add(new DataPostItem(1, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하이!", "g82", 200000, false));
        arrayList.add(new DataPostItem(2, "https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/3zR/image/ajpgDxkePtUXrN9nqZAfq_iKU70",
                "하ggggg이!", "g82", 200000, false));

        // Inflate the layout for this fragment
        View baseView = inflater.inflate(R.layout.fragment_timeline, container, false);

        RecyclerView recyclerView = baseView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        PostViewAdapter postViewAdapter = new PostViewAdapter();
        recyclerView.setAdapter(postViewAdapter);

        return baseView;
    }

    class PostViewAdapter extends RecyclerView.Adapter<PostViewHolder> {

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View baseView = getActivity().getLayoutInflater().inflate(R.layout.item_photo, parent, false);
            PostViewHolder postViewHolder = new PostViewHolder(baseView);

            return postViewHolder;
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            DataPostItem item = arrayList.get(position);

            String url = item.getPostImgUrl();

            Glide.with(TimelineFragment.this)
                    .load(url)
                    .into(holder.iv_photo);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_photo;

        public PostViewHolder(View itemView) {
            super(itemView);

            iv_photo = itemView.findViewById(R.id.iv_photo);
        }
    }
}
