package com.example.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends ListAdapter<Data,DataAdapter.ContactViewHolder> {
    ImageView imageView;
    ArrayList<Data>datas=new ArrayList<>();
    static  DiffUtil.ItemCallback<Data> diffCallback=new DiffUtil.ItemCallback<Data>() {
        @Override
        public boolean areItemsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getNumber().equals(newItem.getNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Data oldItem, @NonNull Data newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

    public DataAdapter(OnDataClicked onItemClickedListener) {
        super(diffCallback);
        this.onItemClickedListener = onItemClickedListener;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ContactViewHolder(view);
    }

    private OnDataClicked onItemClickedListener;
    interface OnDataClicked {
        void edit(Data data, int  position);
        void delete(Data data );
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.onBind(getItem(position));
        final  Data data=getItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.edit(data, position);
            }
        });
        imageView=holder.itemView.findViewById(R. id.img_delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickedListener.delete(data);

            }
        });
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textNumber;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tv_name);
            textNumber = itemView.findViewById(R.id.tv_number);
        }

        void onBind(Data contact) {
            textName.setText(contact.getName());
            textNumber.setText(contact.getNumber());
        }
    }
}
