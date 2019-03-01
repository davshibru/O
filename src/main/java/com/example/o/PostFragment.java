package com.example.o;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {
    private RecyclerView myrecyclerView;
    private static List<PostsExempels> lstPost;

    public PostFragment() {
    }

    public static PostFragment newInstance(){
        PostFragment fragment = new PostFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




            //        lstPost.add(new PostsExempels("eum et est occaecati", "ullam et saepe reiciendis voluptatem adipisci\nsit amet autem assumenda provident rerum culpa\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\nquis sunt voluptatem rerum illo velit", "4", false));
            //        lstPost.add(new PostsExempels("rem alias distinctio quo quis", "ullam consequatur ut\nomnis quis sit vel consequuntur\nipsa eligendi ipsum molestiae et omnis error nostrum\nmolestiae illo tempore quia et distinctio", "25", false));
            //        lstPost.add(new PostsExempels("ullam ut quidem id aut vel consequuntur", "debitis eius sed quibusdam non quis consectetur vitae\nimpedit ut qui consequatur sed aut in\nquidem sit nostrum et maiores adipisci atque\nquaerat voluptatem adipisci repudiandae", "31", false));
            //        lstPost.add(new PostsExempels("optio dolor molestias sit", "temporibus est consectetur dolore\net libero debitis vel velit laboriosam quia\nipsum quibusdam qui itaque fuga rem aut\nea et iure quam sed maxime ut distinctio quae", "44", false));
            //        lstPost.add(new PostsExempels("ut voluptatem illum ea doloribus itaque eos", "voluptates quo voluptatem facilis iure occaecati\nvel assumenda rerum officia et\nillum perspiciatis ab deleniti\nlaudantium repellat ad ut et autem reprehenderit", "48", false));
            //        lstPost.add(new PostsExempels("soluta aliquam aperiam consequatur illo quis voluptas", "sunt dolores aut doloribus\ndolore doloribus voluptates tempora et\ndoloremque et quo\ncum asperiores sit consectetur dolorem", "51", false));
            //        lstPost.add(new PostsExempels("consequatur placeat omnis quisquam quia reprehenderit fugit veritatis facere", "asperiores sunt ab assumenda cumque modi velit\nqui esse omnis\nvoluptate et fuga perferendis voluptas\nillo ratione amet aut et omnis", "60", false));
            //        lstPost.add(new PostsExempels("enim unde ratione doloribus quas enim ut sit sapiente", "odit qui et et necessitatibus sint veniam\nmollitia amet doloremque molestiae commodi similique magnam et quam\nblanditiis est itaque\nquo et tenetur ratione occaecati molestiae tempora", "74", false));
            //        lstPost.add(new PostsExempels("labore in ex et explicabo corporis aut quas", "ex quod dolorem ea eum iure qui provident amet\nquia qui facere excepturi et repudiandae\nasperiores molestias provident\nminus incidunt vero fugit rerum sint sunt excepturi provident", "80", false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ((MainActivity) getActivity()).getPost();

        ((MainActivity) getActivity()).getAlbums();

        lstPost = ((MainActivity) getActivity()).getPost();

//        lstPost.add(new PostsExempels("ea molestias quasi exercitationem repellat qui ipsa sit aut", "et iusto sed quo iure\nvoluptatem occaecati omnis eligendi aut ad\nvoluptatem doloribus vel accusantium quis pariatur\nmolestiae porro eius odio et labore et velit aut", "3", false));



        View view = inflater.inflate(R.layout.fragment_post, container, false);

        int size = lstPost.size();

        myrecyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(getContext(), lstPost, size);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recycleViewAdapter);

        return view;

    }


}