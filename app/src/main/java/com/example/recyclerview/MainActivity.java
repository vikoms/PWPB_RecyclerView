package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Song> SongList;
    private SongAdapter songAdapter;

    String[] names = {
            "I Took A pill in Ibiza",
            "7 Years",
            "Pillow Talk",
            "Work From Home",
            "Never Forget You",
            "Don't Let Me Down",
            "Love Yourself",
            "Me, Myself & I ",
            "Cake By The Ocean",
            "Dangerous Woman",
            "My House",
            "Stressed Out",
            "One Dance ",
            "middle",
            "NO"
    };
    int[] pics = {
            R.drawable.took_a_pill,
            R.drawable.seven_years,
            R.drawable.pillow_talk,
            R.drawable.work,
            R.drawable.never_forget_you,
            R.drawable.dont_let_me_down,
            R.drawable.love_yourself,
            R.drawable.me_myself_and_i,
            R.drawable.cake_by_the_ocean,
            R.drawable.dangerous_woman,
            R.drawable.my_house_florida,
            R.drawable.stressed_out,
            R.drawable.one_dance,
            R.drawable.middle,
            R.drawable.no
    };

    String[] singers = {
            "Mike Posner",
            "Lukas Graham",
            "Zayn",
            "Fifth Harmony",
            "Zara Larsson & MNEK",
            "The Chainsmoker",
            "Justin Bieber",
            "G-Eazy x Bebe Rexha",
            "DNCE",
            "Ariana Grande",
            "Flo rida",
            "Twenty one Pilots",
            "Drake",
            "DJ Snake",
            "Meghan Trainer"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        SETTING UNTUK IMPROVE PERFOMANCE
//        CONTENT TIDAK DAPAT MENGUBAH SIZE LAYOUT RECYCLERVIEW

        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
        }

//        Using a linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        SongList = new ArrayList<>();

//     add Song List
        for (int i = 0; i < names.length; i++) {
            Song song = new Song(names[i], singers[i], i + 1, pics[i]);
            SongList.add(song);
        }

        songAdapter = new SongAdapter(SongList);
//        Specifying an adapter to access data, create views and replace the content

        mRecyclerView.setAdapter(songAdapter);
        songAdapter.notifyDataSetChanged();

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.onItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Card At " + (position + 1) + " Is  clicked", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_grid:
                mLayoutManager = new GridLayoutManager(this, 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.item_staggered_grid:
                mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
            case R.id.item_horizontal:
                mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
