package com.example.ep.myapplication.Activitys.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.ep.myapplication.Activitys.Adapters.StateAdapter;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.Activitys.Services.DataService;
import com.example.ep.myapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFirstFragment.OnFirstFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFirstFragment extends Fragment {  // first fragment - listview with all states

    private static final String TAG = "pttt";
    private static final String STATE_LIST = "state_list";
    private StateAdapter stateListAdapter;
    private RecyclerView stateList;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<State> allStates;

    private OnFirstFragmentInteractionListener mListener;

    public MainFirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param states An array of states
     * @return A new instance of fragment MainFirstFragment.
     */

    public static MainFirstFragment newInstance(ArrayList<State> states) {
        Log.d(TAG, "newInstance: ");
        MainFirstFragment fragment = new MainFirstFragment();
        Bundle args = new Bundle();
        Gson gson = new Gson();
        args.putString(STATE_LIST, gson.toJson(states));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState: Calling to save");
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        outState.putString(STATE_LIST, gson.toJson(allStates));
        Log.d(TAG, "onSaveInstanceState: Saved state");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Creating main first fragment");
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        final DataService ds = new DataService();
        final View v = inflater.inflate(R.layout.fragment_main_first, container, false);
        if (getArguments() == null) {
            Log.d(TAG, "onCreateView: No args!");
            allStates = ds.getArrState();
        } else {
            Log.d(TAG, "onCreateView: Args are available");
        }

        stateListAdapter = new StateAdapter(getActivity(), allStates);
        stateList = v.findViewById(R.id.ListViewsir);
        layoutManager = new LinearLayoutManager(getContext());
        stateList.setLayoutManager(layoutManager);
        stateList.setAdapter(stateListAdapter);

        //  theListView.setOnTouchListener(sd);
//        theListView.setTextFilterEnabled(true);
        EditText inputSearch = (EditText) v.findViewById(R.id.inputSearch);

        // Adding items to listview

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

                stateListAdapter = new StateAdapter(getActivity(), stateListAdapter.custumeFilter(allStates, cs.toString()));
                stateList.setAdapter(stateListAdapter);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        return v;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFirstFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: ");
        super.onAttach(context);
        if (context instanceof OnFirstFragmentInteractionListener) {
            mListener = (OnFirstFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach: ");
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFirstFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFirstFragmentInteraction(Uri uri);
    }
}
