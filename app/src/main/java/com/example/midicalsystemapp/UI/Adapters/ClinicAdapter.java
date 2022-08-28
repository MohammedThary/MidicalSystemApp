package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.Lat;
import static com.example.midicalsystemapp.App.AppClass.Lng;
import static com.example.midicalsystemapp.App.AppClass.chosen_clinic;
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
import com.example.midicalsystemapp.Model.Clinic;
import com.example.midicalsystemapp.UI.Activities.ClinicActivity;
import com.example.midicalsystemapp.UI.Activities.MapsActivity;
import com.example.midicalsystemapp.databinding.ClinicItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.MyViewHolder> {

    FirebaseConnection firebaseConnection = new FirebaseConnection();
    Context context;
    ArrayList<Clinic> data;
    ArrayList<Clinic> dataforsearch;

    public ClinicAdapter(Context context, ArrayList<Clinic> clinics) {
        this.context = context;
        this.dataforsearch = clinics;
        this.data = new ArrayList<>(dataforsearch);
    }

    @NonNull

    @Override
    public ClinicAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ClinicItemBinding binding = ClinicItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ClinicAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicAdapter.MyViewHolder holder, int position) {
        holder.binding.clinicName.setText(data.get(position).getName());
        holder.binding.clinicLocation.setText(data.get(position).getLocation());
        holder.binding.clinicSpecialize.setText(data.get(position).getSpecialize());
        Picasso.get().load(data.get(position).getImage()).into(holder.binding.clinicImage);
          holder.binding.clinicLocation.setOnClickListener(v->{
              Lat = data.get(position).getLat();
              Lng = data.get(position).getLng();
              Intent intent = new Intent(context, MapsActivity.class);
              context.startActivity(intent);
          });
        holder.binding.clinicCard.setOnClickListener(v->{
            Log.e("TAG1", ""+data.get(position).clinic_doctors.size());
            Log.e("TAG1", ""+governorates.get(data.get(position).getGovernorate_id()).clinics.size() );
            Log.e("TAG1", ""+governorates.get(data.get(position).getGovernorate_id()).getName());
            chosen_clinic = data.get(position);

            Log.e("TAG1", ""+chosen_clinic.clinic_doctors.size());

            Intent intent = new Intent(context, ClinicActivity.class);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }





    public Filter getFilter() {
        return ClinicFilter;
    }

    private  final Filter ClinicFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Clinic> filterdannouncment  = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filterdannouncment.addAll(dataforsearch);
            }else{
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (Clinic clinic : dataforsearch){
                            if (clinic.getName().toLowerCase().contains(filterPattern)){
                                filterdannouncment.add(clinic);
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
        ClinicItemBinding binding;

        public MyViewHolder(@NonNull ClinicItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
