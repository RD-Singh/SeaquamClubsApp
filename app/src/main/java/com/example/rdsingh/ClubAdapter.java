package com.example.rdsingh;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rdsingh.seaquamclubsapp.R;

import java.util.ArrayList;

public class ClubAdapter extends RecyclerView.Adapter <ClubAdapter.RecyclerViewHolder>{

    private static final int TYPE_LIST = 1;
    private static final int TYPE_HEAD = 0;
    ArrayList<GetClubs> arrayList = new ArrayList<>();

    public ClubAdapter(ArrayList<GetClubs> arrayList) {
        this.arrayList = arrayList;
    }


    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_list_item, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
            return recyclerViewHolder;
        }
        else if(viewType == TYPE_LIST)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.club_list_item, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, viewType);
            return recyclerViewHolder;
        }

        return  null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if(holder.viewType==TYPE_LIST) {
            GetClubs getClubs = arrayList.get(position-1);
            holder.clubName.setText(getClubs.getClubName());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

    TextView clubName;
        int viewType;
        public RecyclerViewHolder(View itemView,int viewType) {

            super(itemView);
            if(viewType==TYPE_LIST) {
                clubName = (TextView) itemView.findViewById(R.id.clubName);
                this.viewType = TYPE_LIST;
            }
            else if(viewType == TYPE_HEAD)
            {
                this.viewType = TYPE_HEAD;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
        {
            return TYPE_HEAD;
        }
        else
        {
            return TYPE_LIST;
        }
    }
}
