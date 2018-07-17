package com.l3.one_up.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.l3.one_up.R;
import com.l3.one_up.model.Activity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InputFragment extends DialogFragment {

    private static final String TAG = "DialogFragment";
    private Unbinder unbinder;
    private Activity activity;

    @BindView(R.id.spInputType) Spinner spInputType;
    @BindView(R.id.etValue) EditText etValue;

    // Empty constructor required
    public InputFragment(){}

    public static InputFragment newInstance(Activity activity) {
        InputFragment frag = new InputFragment();
        Bundle args = new Bundle();
        args.putParcelable("activity", activity);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getArguments().getParcelable("activity");

        List<String> spinnerArray =  new ArrayList<String>();
        for(int i =0; i< activity.getInput().names().length(); i++)
        {
            try {
                spinnerArray.add(activity.getInput().names().getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInputType.setAdapter(adapter);


    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnSubmit)
    public void submit(){
        Toast.makeText(getContext(), "TODO submit", Toast.LENGTH_SHORT).show();
    }



}
