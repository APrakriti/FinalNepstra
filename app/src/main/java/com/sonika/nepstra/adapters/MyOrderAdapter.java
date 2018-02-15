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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrder> {
    public Context context;
    private List<Myorder> myorderlist;


    public MyOrderAdapter(Context context, List<Myorder> myOrders) {
        this.context = context;
        this.myorderlist =myOrders;
    }

    @Override
    public MyOrder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.activity_my_orderdetails_adapter, parent, false);

        return new MyOrder(view);
    }

    @Override
    public void onBindViewHolder(MyOrder holder, int position) {

            holder.productName.setText(myorderlist.get(position).getLine_item_name());
            holder.productQuantity.setText(myorderlist.get(position).getLine_quantity() + "");
            String totalprice_item = String.valueOf(Integer.valueOf(myorderlist.get(position).getLine_quantity()) * (Integer.valueOf(myorderlist.get(position).getLine_price())));

            holder.productPrice.setText(totalprice_item+ "");
            holder.email.setText(myorderlist.get(position).getBilling_email());
            holder.phone.setText(myorderlist.get(position).getBilling_phone());
            holder.fname.setText(myorderlist.get(position).getBilling_fname()+ myorderlist.get(position).getBilling_last_name());
            holder.company.setText(myorderlist.get(position).getBilling_company());
            holder.address1.setText(myorderlist.get(position).getBilling_address_1());
            holder.address2.setText(myorderlist.get(position).getBilling_adddress_2());
            holder.city.setText(myorderlist.get(position).getBilling_city());
            holder.state.setText(myorderlist.get(position).getBilling_state());
            holder.postcode.setText(myorderlist.get(position).getBilling_postcode());
            holder.country.setText(myorderlist.get(position).getBilling_country());

//            for (int g = 0; g < myorderlist.size(); g++)
//        {
//            holder.productName.setText(myorderlist.get(position).getLine_item_name());
//            holder.productQuantity.setText(myorderlist.get(position).getLine_quantity() + "");
//            holder.productPrice.setText(myorderlist.get(position).getLine_price() + "");
//        }

    }
    @Override
    public int getItemCount() {
        return myorderlist.size();
    }

    public class MyOrder extends RecyclerView.ViewHolder {

        public TextView productName, productQuantity, productPrice;
        TextView email, phone;
        TextView fname, company, country, state, postcode, address1, address2, city;

        public MyOrder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txt_name_myorders);
            productQuantity =  itemView.findViewById(R.id.txt_quantity_myorders);
            productPrice = itemView.findViewById(R.id.txt_price_myorders);

            email = itemView.findViewById(R.id.txt_email);
            phone = itemView.findViewById(R.id.txt_phone);

            fname = itemView.findViewById(R.id.txt_fname_lstname);
            company = itemView.findViewById(R.id.txt_company);
            country = itemView.findViewById(R.id.txt_country);
            state = itemView.findViewById(R.id.txt_state);
            postcode = itemView.findViewById(R.id.txt_postcode);
            address1= itemView.findViewById(R.id.txt_address1);
            address2 = itemView.findViewById(R.id.txt_address2);
            city = itemView.findViewById(R.id.txt_city);
            postcode = itemView.findViewById(R.id.txt_postcode);
        }
    }
}