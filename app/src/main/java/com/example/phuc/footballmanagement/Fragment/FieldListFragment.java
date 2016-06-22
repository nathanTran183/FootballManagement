package com.example.phuc.footballmanagement.Fragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phuc.footballmanagement.DatabaseHelper;
import com.example.phuc.footballmanagement.Model.Field;
import com.example.phuc.footballmanagement.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FieldListFragment extends Fragment {
    DatabaseHelper db;
    ListView fieldList;

    public FieldListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_field_list, container, false);
        db = new DatabaseHelper(getActivity());
        fieldList = (ListView) view.findViewById(R.id.fieldList);
        List<Field> list = db.getAllFields();
        ArrayAdapter<Field> adapter=new ArrayAdapter<Field>(getActivity(), android.R.layout.simple_list_item_1, list);
        fieldList.setAdapter(adapter);

        fieldList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Field field = (Field) fieldList.getItemAtPosition(position);
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = new FieldDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("FIELDID", field.getId());
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.contentMain,fragment )
                        .commit();
            }
        });
        return view;
    }

}
