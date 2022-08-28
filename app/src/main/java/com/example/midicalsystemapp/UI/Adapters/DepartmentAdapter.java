package com.example.midicalsystemapp.UI.Adapters;

import static com.example.midicalsystemapp.App.AppClass.chosen_Department;
import static com.example.midicalsystemapp.App.AppClass.departments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.midicalsystemapp.DBConnection.FirebaseConnection;
import com.example.midicalsystemapp.Model.Department;
import com.example.midicalsystemapp.UI.Activities.DepartmentActivity;
import com.example.midicalsystemapp.databinding.DepartmentItemBinding;

import java.util.ArrayList;

public class DepartmentAdapter  extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

    FirebaseConnection firebaseConnection = new FirebaseConnection();
    Context context;
    ArrayList<Department> data;
    ArrayList<Department> dataforsearch;

    public DepartmentAdapter(Context context, ArrayList<Department> Departments) {
        this.context = context;
        this.dataforsearch = Departments;
        this.data = new ArrayList<>(dataforsearch);
    }

    @NonNull

    @Override
    public DepartmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DepartmentItemBinding binding = DepartmentItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new DepartmentAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentAdapter.MyViewHolder holder, int position) {
        holder.binding.departmentName.setText(data.get(position).getName());

        holder.binding.departmentCard.setOnClickListener(v->{
            chosen_Department = departments.get(data.get(position).getId());
            Intent intent = new Intent(context, DepartmentActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }





    public Filter getFilter() {
        return DepartmentFilter;
    }

    private  final Filter DepartmentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Department> filterdannouncment  = new ArrayList<>();
            if (constraint == null || constraint.length()==0){
                filterdannouncment.addAll(dataforsearch);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Department Department : dataforsearch){
                    if (Department.getName().toLowerCase().contains(filterPattern)){
                        filterdannouncment.add(Department);
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
        DepartmentItemBinding binding;

        public MyViewHolder(@NonNull DepartmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }



}
