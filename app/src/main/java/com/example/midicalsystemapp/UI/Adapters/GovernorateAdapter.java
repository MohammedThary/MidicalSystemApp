package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.chosen_Governorate;
import static com.example.midicalsystemapp.App.AppClass.governorates;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Governorate;
import com.example.midicalsystemapp.UI.Activities.GovernorateActivity;
import com.example.midicalsystemapp.databinding.GovernorateItemBinding;

import java.util.ArrayList;

public class GovernorateAdapter extends RecyclerView.Adapter<GovernorateAdapter.MyViewHolder> {

    FirebaseConnection firebaseConnection = new FirebaseConnection();
    Context context;
    ArrayList<Governorate> data;
    ArrayList<Governorate> dataforsearch;

    public GovernorateAdapter(Context context, ArrayList<Governorate> Governorates) {
        this.context = context;
        this.dataforsearch = Governorates;
        this.data = new ArrayList<>(dataforsearch);
    }

    @NonNull

    @Override
    public GovernorateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GovernorateItemBinding binding = GovernorateItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new GovernorateAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GovernorateAdapter.MyViewHolder holder, int position) {
       holder.binding.governorateName.setText(data.get(position).getName());

        holder.binding.governorateCard.setOnClickListener(v->{
            chosen_Governorate = governorates.get(data.get(position).getId());
            Intent intent = new Intent(context, GovernorateActivity.class);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }





    public Filter getFilter() {
        return GovernorateFilter;
    }

    private  final Filter GovernorateFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Governorate> filterdannouncment  = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filterdannouncment.addAll(dataforsearch);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Governorate Governorate : dataforsearch){
                    if (Governorate.getName().toLowerCase().contains(filterPattern)){
                        filterdannouncment.add(Governorate);
                    }
                }
            }


            FilterResults results = new FilterResults();
            results.values = filterdannouncment;
            results.count = filterdannouncment.size();
            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };




    public class MyViewHolder extends RecyclerView.ViewHolder {
        GovernorateItemBinding binding;

        public MyViewHolder(@NonNull GovernorateItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
