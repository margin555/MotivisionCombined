package com.example.appsteam.motivision.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appsteam.motivision.Home.Edit_Profile_Activity;
import com.example.appsteam.motivision.Interface.OnItemClickListener;
import com.example.appsteam.motivision.ModelClass.Model_Motivibe_Activities;
import com.example.appsteam.motivision.R;

import java.util.List;

/**
 * Created by appsteam on 06-12-2017.
 */
public class Adapter_Profile extends RecyclerView.Adapter<Adapter_Profile.MyViewHolder> {
    List<Model_Motivibe_Activities> mlist;
    Context context;
   OnItemClickListener itemClickListener;
    int i;

    public Adapter_Profile(List<Model_Motivibe_Activities> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public Adapter_Profile.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (this.context instanceof Edit_Profile_Activity) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_edit_profile_recycler, parent, false);
            i = 1;
            return new MyViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_recycler, parent, false);
            return new MyViewHolder(itemView);

        }
    }


    @Override
    public void onBindViewHolder(final Adapter_Profile.MyViewHolder holder, int position) {
        Model_Motivibe_Activities model_profile = mlist.get(position);
        if (this.context instanceof Edit_Profile_Activity) {
            holder.txtname1.setText(model_profile.getAct_name());
            holder.txtdes1.setText(model_profile.getAct_des());


        } else {
            holder.txtname.setText(model_profile.getAct_name());
            holder.txtdes.setText(model_profile.getAct_des());
        }
    }


    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setitemClickListener(OnItemClickListener itemClickLListenr) {
        this.itemClickListener = itemClickLListenr;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtname, txtdes, txtname1, txtdes1;
        ImageButton btn;

        public MyViewHolder(View itemView) {
            super(itemView);

            if (i == 1) {
                txtname1 = (TextView) itemView.findViewById(R.id.editprofilerecyclername);
                txtdes1 = (TextView) itemView.findViewById(R.id.editprofilerecyclerdes);
                btn = (ImageButton) itemView.findViewById(R.id.edtdelete);
                btn.setOnClickListener(this);
            } else {


                txtname = (TextView) itemView.findViewById(R.id.profilerecyclername);
                txtdes = (TextView) itemView.findViewById(R.id.profilerecyclerdes);


            }
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onClick(v, getAdapterPosition());
            }
        }
    }
}
