package com.sonika.nepstra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonika.nepstra.R;
import com.sonika.nepstra.pojo.Myorder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyOrderAdpater extends RecyclerView.Adapter<MyOrderAdpater.MyOrder> {
    public Context context;
    private List<Myorder> myorderlist;

    //  private List<AllProducts> modelItemList;
    ArrayList<HashMap<String, String>> data;
    // private Context context;

    private ArrayList<Myorder> arraylistitem;
    LayoutInflater inflater;
    //Food_Activity main;

    public MyOrderAdpater(Context context, List<Myorder> myOrders) {
        this.context = context;
        this.myorderlist =myOrders;
    }

    @Override
    public MyOrder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_my_order_adpater, parent, false);

        return new MyOrder(view);
    }

    @Override
    public void onBindViewHolder(MyOrder holder, int position) {
        holder.productName.setText(myorderlist.get(position).getShipping_meta_data_value());
        holder.productquantity.setText(myorderlist.get(position).getTotal());
//        holder.productName.setText(myorderlist.get(position).getLine_quantity());
        //allholder.allproductName1.setText(allProductList.get(position).getI_name());

        //Glide.with(context).load(allProductList.get(position).getI_src()).into(allholder.allproductImage);
        //Glide.with(context).load(allProductList.get(position).getI_date_created()).into(allholder.allproductImage);

        Log.e("chankhey", "monkey");


    }



    @Override
    public int getItemCount() {
        //Log.e("sanjeev", String.valueOf(allProductList.size()));
        return myorderlist.size();
    }

    public class MyOrder extends RecyclerView.ViewHolder {
        public TextView productImage;
        public TextView productName, productquantity;

        public MyOrder(View itemView) {
            super(itemView);
         //   productImage = (TextView) itemView.findViewById(R.id.txt_name_myorder);
            productName = (TextView) itemView.findViewById(R.id.txt_name_myorder);
            productquantity = itemView.findViewById(R.id.quantityMyorder);



        }
    }
}





