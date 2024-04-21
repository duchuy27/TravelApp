package com.android.travelapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.travelapp.R;
import com.android.travelapp.ui.TourDetail;
import com.android.travelapp.Model.touristModel;

import java.util.List;

public class touristAdapter extends RecyclerView.Adapter<touristAdapter.ViewHolder> {
    private final Context context;
    private List<touristModel> touristModelList;
    public touristAdapter(Context context, List<touristModel> touristModelList){
        this.context = context;
        this.touristModelList = touristModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final touristModel touristModel = touristModelList.get(position);

        holder.nameTour.setText(touristModel.getAl_name_tour());
        holder.priceTour.setText(Integer.toString(touristModel.getAl_price_tour()));

        //chuyen byte -> bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(touristModel.getAl_img_tour(),0, touristModel.getAl_img_tour().length);
        holder.imgTour.setImageBitmap(bitmap);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TourDetail.class);
                intent.putExtra("imgTour", touristModel.getAl_img_tour());
                intent.putExtra("nameTour", touristModel.getAl_name_tour());
                intent.putExtra("descTour", touristModel.getAl_desc_tour());
                intent.putExtra("locTour", touristModel.getAl_location());
                intent.putExtra("priceTour", touristModel.getAl_price_tour());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return touristModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTour;
        TextView nameTour, priceTour, locTour;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTour = itemView.findViewById(R.id.img_tour);
            nameTour = itemView.findViewById(R.id.name_tour);
            locTour = itemView.findViewById(R.id.btn_img_loc);
            priceTour = itemView.findViewById(R.id.price_tour);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
