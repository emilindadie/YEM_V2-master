package com.example.emili.friendbox.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.emili.friendbox.R;
import com.example.emili.friendbox.data.model.Notification;

import java.util.List;

/**
 * Created by emili on 09/12/2017.
 */


public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    List<Notification> notifications;
    LayoutInflater layoutInflater;
    Context context;
    int taille;
    ItemsClick itemsClick;

    public NotificationAdapter(Context context, List<Notification> notifications, ItemsClick itemsClick){
        this.itemsClick = itemsClick;

        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.notifications= notifications;
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
        Notification notification = notifications.get(position);

        holder1.notificationTextView.setText(notification.getNotificationContent());

    }

    @Override
    public int getItemCount() {
        taille = notifications.size();
        return taille;
    }

    public void addNotification(Notification notification) {
        notifications.add(notification);
    }


    class Holder extends RecyclerView.ViewHolder{

        ImageView notificationImageView;
        TextView notificationTextView;

        public Holder(View itemView) {
            super(itemView);

            notificationImageView = (ImageView) itemView.findViewById(R.id.notificationImageView);
            notificationTextView = (TextView) itemView.findViewById(R.id.notificationContentTextView);
        }

        private void bind(final Notification notification, final ItemsClick itemsClick){

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                itemsClick.onClick(notification, getAdapterPosition());

                }
            });
        }
    }

    public interface ItemsClick{
        void onClick(Notification notification, int position );
    }
}
