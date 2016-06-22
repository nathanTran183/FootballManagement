package com.example.phuc.footballmanagement.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phuc.footballmanagement.DatabaseHelper;
import com.example.phuc.footballmanagement.Model.Match;
import com.example.phuc.footballmanagement.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchListFragment extends Fragment {
    ListView listMatch;
    DatabaseHelper db;

    public MatchListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);
        db = new DatabaseHelper(getActivity());
        listMatch = (ListView) view.findViewById(R.id.listMatch);
        List<Match> list = db.getAllMatch();
        ArrayAdapter<Match> adapter=new ArrayAdapter<Match>(getActivity(), android.R.layout.simple_list_item_1, list);
        //4. Đưa Data source vào ListView
        listMatch.setAdapter(adapter);
        return view;
    }

}
