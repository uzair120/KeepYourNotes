package com.example.uzair.keepyournotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Uzair Raza on 1/25/2018.
 */

public class CustomArrayAdapter extends RecyclerView.Adapter<CustomArrayAdapter.ViewHolder> {

    private ArrayList<NotesModel> data;
    private Context context;
    NotesInterface notesInterface;

    public CustomArrayAdapter(Context context, ArrayList<NotesModel> data, NotesInterface notesInterface) {
        this.data = data;
        this.context = context;
         this.notesInterface = notesInterface;
    }

    @Override
    public CustomArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.note_recyclerview, parent, false);
        CustomArrayAdapter.ViewHolder holder = new CustomArrayAdapter.ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesInterface.itemClicked(v);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomArrayAdapter.ViewHolder holder, final int position) {
        NotesModel notesmodels = (NotesModel) data.get(position);
        holder.notesTitle.setText(notesmodels.getName());
        holder.notesBody.setText(notesmodels.getBody());
        holder.notesDate.setText(notesmodels.getDate());

    }

    public void insertItem (int position, NotesModel notesmodels) {
        data.add(position, notesmodels);
        notifyItemInserted(position);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private TextView notesTitle;
        private TextView notesBody;
        private TextView notesDate;

        public ViewHolder(View itemView) {
            super(itemView);
            notesTitle = itemView.findViewById(R.id.tvTitle);
            notesBody = itemView.findViewById(R.id.tvBody);
            notesDate = itemView.findViewById(R.id.tvDate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
