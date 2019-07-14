package com.example.hy.forum;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class forum_discussionFragment extends Fragment {


    public forum_discussionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.forum_discussion_fragment, container, false);
    }

}
