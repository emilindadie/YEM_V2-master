package com.example.emili.friendbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.data.model.Pictogramme;
import com.example.emili.friendbox.ui.mainActivity.Pokemon;
import com.example.emili.friendbox.ui.mainActivity.PokemonList;

import java.util.List;

import io.reactivex.Observer;

/**
 * Created by emili on 29/11/2017.
 */

public class PokemonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Pokemon> dictionnaire;
    LayoutInflater layoutInflater;
    Context context;
    int taille;
    ItemsClick itemsClick;

    public PokemonAdapter(Context context, List<Pokemon> dictionnaire, ItemsClick itemsClick){
        this.itemsClick = itemsClick;
        layoutInflater = LayoutInflater.from(context);
        context = context;
        this.dictionnaire = dictionnaire;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.pokemon, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Holder holder1 = (Holder) holder;
        Pokemon pokemon = dictionnaire.get(position);
        holder1.nom.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        taille = dictionnaire.size();
        return taille;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView nom;

        public Holder(View itemView) {
            super(itemView);
            nom = (TextView) itemView.findViewById(R.id.pokemonName);
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
