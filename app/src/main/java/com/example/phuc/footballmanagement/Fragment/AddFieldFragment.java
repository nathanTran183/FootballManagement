package com.example.phuc.footballmanagement.Fragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phuc.footballmanagement.DatabaseHelper;
import com.example.phuc.footballmanagement.Model.District;
import com.example.phuc.footballmanagement.Model.Field;
import com.example.phuc.footballmanagement.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFieldFragment extends Fragment {
    EditText edtAddress, edtName, edtLatitude,edtLongitude,edtPhone;
    Spinner spnDistrict;
    Button btnCreate;
    DatabaseHelper db;

    public AddFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_add_field, container, false);
        db = new DatabaseHelper(getActivity());
        edtAddress = (EditText) view.findViewById(R.id.txtAddressAddField);
        edtName = (EditText) view.findViewById(R.id.txtFieldNameAdd);
        edtLatitude = (EditText) view.findViewById(R.id.txtLatitudeAddField);
        edtLongitude = (EditText) view.findViewById(R.id.txtLongitudeAddField);
        edtPhone = (EditText) view.findViewById(R.id.txtPhoneAddField);
        spnDistrict = (Spinner) view.findViewById(R.id.spnDistrictAddField);
        btnCreate = (Button) view.findViewById(R.id.btnCreateField);

        List<District> list = db.getAllDistricts();
        ArrayAdapter<District> spinnerArrayAdapter = new ArrayAdapter<District>(getActivity(),android.R.layout.simple_spinner_item,list);
        spnDistrict.setAdapter(spinnerArrayAdapter);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = edtAddress.getText().toString();
                String name = edtName.getText().toString();
                String latitude = edtLatitude.getText().toString();
                String longitude = edtLongitude.getText().toString();
                String phone = edtPhone.getText().toString();
                District d = (District) spnDistrict.getSelectedItem();

                if ("".equals(address) || "".equals(name) || "".equals(latitude) || "".equals(longitude) || "".equals(phone)) {
                    Toast.makeText(getActivity(), "No field is blanked", Toast.LENGTH_SHORT).show();
                } else {
                    db.insertField(new Field(name, d.getId(), address, Float.parseFloat(latitude), Float.parseFloat(longitude), phone));
                    Toast.makeText(getActivity(), "Add Field succeed!", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getFragmentManager();
                    Fragment fragment = new FieldListFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.contentMain, fragment)
                            .commit();
                }
            }
        });
        return view;
    }

}
