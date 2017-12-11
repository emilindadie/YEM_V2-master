package com.example.emili.friendbox.utils;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.data.model.Pictogramme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emili on 05/12/2017.
 */

public class PictogrammeUtils {

    private static final List<Pictogramme> alimentaion = new ArrayList<Pictogramme>(){{

        add(new Pictogramme("gateau", "j'ai envi d'un gateau", R.drawable.gateau_au_chocolat, R.raw.gateau_au_chocolat));
        add(new Pictogramme("amuse_gueule", "j'ai envi d'un amuse gueule", R.drawable.amuse_gueule, R.raw.amuse_gueule));
        add(new Pictogramme("fromage", "j'ai envi d'un fromage", R.drawable.fromage, R.raw.fromage));
        add(new Pictogramme("legumes", "j'ai envi d'un legume", R.drawable.legumes, R.raw.legumes));
        add(new Pictogramme("spaghettis", "j'ai envi d'un spaghettis", R.drawable.spaghettis, R.raw.spaghettis));
        add(new Pictogramme("viande_d_agneau", "j'ai envi de viande d'agneau", R.drawable.viande_d_agneau, R.raw.viande_d_agneau));
        add(new Pictogramme("vin", "j'ai envi de vin", R.drawable.vins, R.raw.vins));

    }
    };

    public static final List<Pictogramme> allItems = new ArrayList<Pictogramme>(){{

        add(new Pictogramme("Alimentation",null , R.drawable.gateau_au_chocolat, 0));
        add(new Pictogramme("Maison",null , R.drawable.gateau_au_chocolat, 0));
        add(new Pictogramme("Boisson",null , R.drawable.gateau_au_chocolat, 0));
        add(new Pictogramme("a",null , R.drawable.gateau_au_chocolat, 0));
        add(new Pictogramme("a",null , R.drawable.gateau_au_chocolat, 0));
        add(new Pictogramme("a",null , R.drawable.gateau_au_chocolat, 0));

    }
    };

    public static List<Pictogramme> getAlimentaion(){
        return  alimentaion;
    }
}
