package com.example.emili.friendbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.data.model.Pictogramme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emili on 27/11/2017.
 */


public class PictogrammeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable{


    List<Pictogramme> theme;
    List<Pictogramme> themeFilter;

    LayoutInflater layoutInflater;
    Context context;
    int taille;
    ItemsClick itemsClick;


    public PictogrammeAdapter(Context context, List<Pictogramme> theme, ItemsClick itemsClick){
        this.itemsClick = itemsClick;

        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.theme = theme;
        this.themeFilter = theme;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.theme_items, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Holder holder1 = (Holder) holder;

        Pictogramme pictogramme = themeFilter.get(position);
        holder1.picto.setImageResource(pictogramme.getImage());
        holder1.description.setText(pictogramme.getNom());
        ((Holder) holder).bind(pictogramme, itemsClick);

    }

    @Override
    public int getItemCount() {
        taille = themeFilter.size();
        return taille;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String chaeString = charSequence.toString();

                if(chaeString.length() == 0){

                    themeFilter = theme;
                }
                else{

                    List<Pictogramme> themeFilteredList = new ArrayList<>();

                    for(Pictogramme pico: themeFilteredList){

                        if(pico.getNom().toLowerCase().contains(chaeString)){

                            themeFilteredList.add(pico);
                        }

                    }
                    themeFilter = themeFilteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = themeFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                themeFilter = (ArrayList<Pictogramme>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView picto;
        TextView description;

        public Holder(View itemView) {
            super(itemView);

            picto = (ImageView) itemView.findViewById(R.id.imagePicto);
            description = (TextView) itemView.findViewById(R.id.description);
        }

        private void bind(final Pictogramme pictogramme, final ItemsClick itemsClick){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsClick.onClick(pictogramme, getAdapterPosition());

                }
            });
        }
    }

    public interface ItemsClick{
        void onClick(Pictogramme pictogramme, int position );
    }
}
