package com.example.thecomicslibrary.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.thecomicslibrary.R;
import com.example.thecomicslibrary.domain.Comic;
import com.example.thecomicslibrary.ui.ComicPage;
import com.example.thecomicslibrary.utility.SyncGrid;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private GridView gridView;
    private static int scrollIndex = 0;
    private final String endpoint = "http://192.168.1.210/comics/all";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Remove title bar
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        gridView = (GridView) root.findViewById(R.id.gridView);

        final ArrayList<Comic> comicsList = new ArrayList<>();

        SyncGrid homeGrid = new SyncGrid(endpoint,gridView,getActivity(),getContext(),comicsList);
        homeGrid.execute();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer selectedItem =(Integer)gridView.getItemAtPosition(position);

                //save index before entering in a comic
                scrollIndex = gridView.getFirstVisiblePosition();

                //Load ComicActivity page infos
                Intent intent = new Intent(getActivity(), ComicPage.class);
                intent.putExtra("comic", comicsList.get(selectedItem));
                startActivity(intent);
            }
        });

        return root;
    }


    @Override
    public void onPause() {
        super.onPause();
        scrollIndex = gridView.getFirstVisiblePosition();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(scrollIndex != -1) {
            gridView.setSelection(scrollIndex);
        }
    }

    public HomeFragment() {}
}
