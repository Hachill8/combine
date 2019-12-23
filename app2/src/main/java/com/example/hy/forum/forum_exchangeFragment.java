package com.example.hy.forum;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hy.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class forum_exchangeFragment extends Fragment {


    public forum_exchangeFragment() {
        // Required  empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.forum_exchange_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.tx);
        textView.setText("尚無貼文，敬請期待!");
        return rootView;
    }

}
