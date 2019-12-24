package com.example.inflearn_anstgram_1;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.inflearn_anstgram_1.api.MyAPI;
import com.example.inflearn_anstgram_1.data.DataPostItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    static ArrayList<MyAPI.Post> arrayList;
    PostViewAdapter postViewAdapter;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fetchPostsAsync();

        // Inflate the layout for this fragment
        View baseView = inflater.inflate(R.layout.fragment_feed, container, false);

        RecyclerView recyclerView = baseView.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        postViewAdapter = new PostViewAdapter();
        recyclerView.setAdapter(postViewAdapter);

        baseView.findViewById(R.id.fab_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permissionCheck = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
                }

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2);
                }
            }
        });

        return baseView;
    }

    private void fetchPostsAsync() {
        arrayList = new ArrayList<>();

        FetchPostsTask fetchPostsTask = new FetchPostsTask();
        fetchPostsTask.execute(MyAPI.GET_POST);
    }

    @SuppressLint("StaticFieldLeak")
    class FetchPostsTask extends AsyncTask<String, Void, MyAPI.Post[]> {

        @Override
        protected MyAPI.Post[] doInBackground(String... strings) {
            String url = strings[0];

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
//                return response.body().string();

                Gson gson = new Gson();
                MyAPI.Post[] posts = gson.fromJson(response.body().charStream(), MyAPI.Post[].class);

                return posts;
            } catch (IOException e) {
                Log.d("FetchPostsTask", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(MyAPI.Post[] posts) {
            super.onPostExecute(posts);

            for (MyAPI.Post post: posts) {
                arrayList.add(post);
            }

            postViewAdapter.notifyDataSetChanged();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2 && grantResults.length > 0) {
            dispatchTakePictureIntent();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Log.d("onActivityResult", "takePictureIntent SUCCESS!");

            /**
             * Error
             * Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.Object android.os.Bundle.get(java.lang.String)' on a null object reference
             * Debug; find "data"
             * Intent intent = new Intent(getActivity(), PostActivity.class);
             * startActivity(intent);
             */

            Intent startIntent = new Intent(getActivity(), PostActivity.class);

            if (data != null) {
                startIntent.setData(data.getData());
            }

            startActivity(startIntent);
        }
    }

    class PostViewAdapter extends RecyclerView.Adapter<PostViewHolder> {

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View baseView = getActivity().getLayoutInflater().inflate(R.layout.item_post, parent, false);
            PostViewHolder postViewHolder = new PostViewHolder(baseView);

            return postViewHolder;
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            MyAPI.Post item = arrayList.get(position);

            String url = item.getImage().getUrl();

            Glide.with(FeedFragment.this)
                    .load(url)
                    .into(holder.iv_post);

            holder.tv_username.setText(item.getUsername());
            holder.tv_posttext.setText(item.getText());
            holder.tv_likes.setText(String.valueOf(item.getLikes().getCount()));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_username, tv_likes, tv_posttext;
        public ImageView iv_post;

        public PostViewHolder(View itemView) {
            super(itemView);

            iv_post = itemView.findViewById(R.id.iv_post);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_likes = itemView.findViewById(R.id.tv_likes);
            tv_posttext = itemView.findViewById(R.id.tv_posttext);
        }
    }
}
