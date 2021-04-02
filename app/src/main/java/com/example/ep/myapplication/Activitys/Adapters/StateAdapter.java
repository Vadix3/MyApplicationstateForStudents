package com.example.ep.myapplication.Activitys.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.ep.myapplication.Activitys.Activitys.MainActivity;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.R;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {
    private static final String TAG = "pttt";

    private Context context;
    private ArrayList<State> allstates;

    public StateAdapter(Context context, ArrayList<State> allstates) {
        this.context = context;
        this.allstates = allstates;
    }

    public ArrayList<State> custumeFilter(ArrayList<State> allstates, String toString) {
        return null;
    }

    @NonNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.country_row, parent, false);
        return new StateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateAdapter.ViewHolder holder, int position) {
        final State temp = allstates.get(position);
        if (holder != null) {
            holder.stateName.setText(temp.getName());
            holder.nativeName.setText(temp.getNativeName());
            if (allstates.size() > 50) {
                holder.rowCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(context, R.anim.shake);
                        view.startAnimation(hyperspaceJumpAnimation);
                        ((MainActivity) context).LoadSecFragment(temp);
                        
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return allstates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stateName, nativeName;
        CardView rowCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        /**
         * A method to initialize the row views
         */
        private void initViews() {
            rowCard = itemView.findViewById(R.id.row_LAY_cardView);
            stateName = itemView.findViewById(R.id.row_LBL_name);
            nativeName = itemView.findViewById(R.id.row_LBL_nativeName);
        }

    }
}
