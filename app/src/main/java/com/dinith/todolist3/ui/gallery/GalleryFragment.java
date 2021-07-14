package com.dinith.todolist3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinith.todolist3.R;
import com.dinith.todolist3.ui.home.DatabaseHelper;
import com.dinith.todolist3.ui.home.NoteModel;
import com.dinith.todolist3.ui.home.NotesAdapter;
import com.dinith.todolist3.ui.home.NotesAdapter2;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    RecyclerView recyclerView;
    ArrayList<NoteModel> arrayList;
    DatabaseHelper database_helper;
    private GridLayoutManager mGridLayoutManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        database_helper = new DatabaseHelper(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view2);

        displayNotes();
        return v;
    }

    public void displayNotes() {
        arrayList = new ArrayList<>(database_helper.getNotes());
        recyclerView.setLayoutManager(mGridLayoutManager = new GridLayoutManager(getActivity(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NotesAdapter2 adapter = new NotesAdapter2(getContext(), getActivity(), arrayList);
        recyclerView.setAdapter(adapter);
    }
}