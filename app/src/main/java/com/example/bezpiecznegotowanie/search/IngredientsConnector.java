package com.example.bezpiecznegotowanie.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bezpiecznegotowanie.R;

import java.util.List;

class IngredientsConnector extends RecyclerView.Adapter<IngredientsConnector.ViewHolder> {

    private List<String> mIngredients;
    private Context mContext;

    IngredientsConnector(Context context, List<String> ingredients){
        this.mIngredients = ingredients;
        this.mContext = context;
    }

    @NonNull
    @Override
    public IngredientsConnector.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ingredients_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsConnector.ViewHolder viewHolder, int i) {
        viewHolder.bind(mIngredients.get(i));
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mIngredient;
        private ImageButton mRemoveButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mIngredient = itemView.findViewById(R.id.ingredient);
            mRemoveButton = itemView.findViewById(R.id.removeButton);
            mRemoveButton.setOnClickListener(this);
        }

        void bind(String currentIngredient){
            mIngredient.setText(currentIngredient);
        }

        @Override
        public void onClick(View v) {
            mIngredients.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}
