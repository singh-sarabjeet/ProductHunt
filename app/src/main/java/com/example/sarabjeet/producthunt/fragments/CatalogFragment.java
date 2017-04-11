package com.example.sarabjeet.producthunt.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarabjeet.producthunt.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by sarabjeet on 11/4/17.
 */

public class CatalogFragment extends Fragment {

    @BindView(R.id.product_recycler_view)
    RecyclerView gridRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        return null;
    }
}
