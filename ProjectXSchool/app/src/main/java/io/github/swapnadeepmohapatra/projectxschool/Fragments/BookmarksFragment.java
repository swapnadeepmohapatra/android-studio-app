package io.github.swapnadeepmohapatra.projectxschool.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.swapnadeepmohapatra.projectxschool.R;


public class BookmarksFragment extends Fragment {

    private View bookmarksFragmentsView;

    public BookmarksFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bookmarksFragmentsView =  inflater.inflate(R.layout.fragment_bookmarks, container, false);
        return bookmarksFragmentsView;
    }
}
