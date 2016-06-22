package com.example.phuc.footballmanagement.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phuc.footballmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FieldDetailFragment extends Fragment {


    public FieldDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field_detail, container, false);

        return view;
    }

}
