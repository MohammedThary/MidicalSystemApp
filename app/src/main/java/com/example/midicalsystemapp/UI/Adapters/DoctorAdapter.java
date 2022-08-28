package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.*;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;
import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Appointment;
import com.example.midicalsystemapp.Model.Doctor;
import com.example.midicalsystemapp.UI.Activities.DoctorDetails;
import com.example.midicalsystemapp.databinding.DoctorItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.MyViewHolder> {

    FirebaseConnection firebaseConnection = new FirebaseConnection();
    Context context;
    ArrayList<Doctor> data;
    ArrayList<Doctor> dataforsearch;

    public DoctorAdapter(Context context, ArrayList<Doctor> data) {
        this.context = context;
        this.dataforsearch = data;
        this.data = new ArrayList<>(dataforsearch);
    }

    @NonNull

    @Override
    public DoctorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DoctorItemBinding binding = DoctorItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.MyViewHolder holder, int position) {
        holder.binding.ratingBar.setRating(Float.parseFloat(data.get(position).getEvaluation()));
        holder.binding.ratingBar.setIsIndicator(true);
        holder.binding.doctorname.setText(data.get(position).getUsername());
        holder.binding.doctorspesalize.setText(data.get(position).getSpecialize());
        Picasso.get().load(doctors_array.get(data.get(position).getClone_id()).getImage()).into(holder.binding.doctorImage);

        holder.binding.doctorCard.setOnClickListener(v->{
            String doctor_id = data.get(position).getClinic_id();
            choosedDoctor = doctors_array.get(data.get(position).getClone_id());
            Log.e("TAG6", doctor_id );
            chosen_Department = departments.get(data.get(position).getDepartment_id());

            Log.e("TAG", ""+doctor_appointments.size() );
            Log.e("doc", "slected doctor"+choosedDoctor.toString() );
                Intent intent = new Intent(context, DoctorDetails.class);
                context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }





     public Filter getFilter() {
        return doctorFilter;
    }

    private  final Filter doctorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Doctor> filterdannouncment  = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filterdannouncment.addAll(dataforsearch);
            }else{
                switch (search_type){
                    case "name":
                        {
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (Doctor doctor : dataforsearch){
                            if (doctor.getUsername().toLowerCase().contains(filterPattern)){
                                filterdannouncment.add(doctor);
                            }

                        }
                    }
                    case "specialty":
                    {
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (Doctor doctor : dataforsearch){
                            if (doctor.getSpecialize().toLowerCase().contains(filterPattern)){
                                filterdannouncment.add(doctor);
                            }

                        }
                    }
//                    case "location":
//                    {
//                        String filterPattern = constraint.toString().toLowerCase().trim();
//                        for (Doctor doctor : dataforsearch){
//                            if (doctor.getClinic_location().toLowerCase().contains(filterPattern)){
//                                filterdannouncment.add(doctor);
//                            }
//
//                        }
//                    }
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
        DoctorItemBinding binding;

        public MyViewHolder(@NonNull DoctorItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    }

