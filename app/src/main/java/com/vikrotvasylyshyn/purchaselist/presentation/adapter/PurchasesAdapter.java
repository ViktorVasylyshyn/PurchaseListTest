package com.vikrotvasylyshyn.purchaselist.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vikrotvasylyshyn.purchaselist.R;
import com.vikrotvasylyshyn.purchaselist.data.model.Purchase;
import com.vikrotvasylyshyn.purchaselist.utill.ImageLoader;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PurchasesAdapter extends RecyclerView.Adapter<PurchasesAdapter.PurchasesViewHolder> {

    @Nullable
    private AdapterCallback.OnPurchaseClickListener clickListener;

    private List<Purchase> list = Collections.emptyList();

    @Inject
    public PurchasesAdapter() {
    }

    public void setData(List<Purchase> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PurchasesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_purchase, parent, false);
        return new PurchasesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasesViewHolder holder, int position) {
        holder.bindData(list.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return list.isEmpty() ? 0 : list.size();
    }

    public void setOnClickListener(AdapterCallback.OnPurchaseClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class PurchasesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.purchase_cell_title)
        TextView title;
        @BindView(R.id.purchase_cell_number)
        TextView count;
        @BindView(R.id.purchase_cell_image)
        ImageView imageView;

        PurchasesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindData(Purchase item, AdapterCallback.OnPurchaseClickListener clickListener) {
            title.setText(item.getTitle());
            count.setText(item.getCount());
            ImageLoader.loadPurchaseImage(imageView, item.getImageUri());
            itemView.setOnClickListener(view -> {
                if (null != clickListener)
                    clickListener.onPurchaseClicked(item);
            });
        }
    }
}
