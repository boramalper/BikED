package org.boramalper.labs.biked;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by bora on 07.10.2017.
 */

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.ViewHolder> {
    private Route[] routes;

    RoutesAdapter(Route[] routes) {
        this.routes = routes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.routes_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ((TextView) holder.itemView.findViewById(R.id.textViewTitle)).setText(routes[position].title);
        holder.itemView.findViewById(R.id.textViewTitle).setBackgroundColor(routes[position].titleColor);

        ((TextView) holder.itemView.findViewById(R.id.textViewLevel)).setText(routes[position].level);
        ((TextView) holder.itemView.findViewById(R.id.textViewLength)).setText(String.format(Locale.US, "%.1f km", routes[position].length));

        ((TextView) holder.itemView.findViewById(R.id.textViewTag1)).setText(routes[position].tags[0]);
        ((TextView) holder.itemView.findViewById(R.id.textViewTag2)).setText(routes[position].tags[1]);
        ((TextView) holder.itemView.findViewById(R.id.textViewTag3)).setText(routes[position].tags[2]);
    }

    @Override
    public int getItemCount() {
        return this.routes.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
