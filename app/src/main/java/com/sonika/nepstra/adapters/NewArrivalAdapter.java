package com.sonika.nepstra.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sonika.nepstra.DetailsActivity;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.DetailsHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/16/2017.
 */

public class NewArrivalAdapter extends BaseAdapter{
    Context context;
    List<Newarrivals_pojo> newarrivals_pojos = new ArrayList<Newarrivals_pojo>();
    int resource;
    NewArrivalsHelper dbHelper;
    OrderHelper orderHelper;
    DetailsHelper detailsHelper;
    String cname, cimage, cprice, cdesc;
    Integer cat_id, img_id;

    public NewArrivalAdapter(Context context, List<Newarrivals_pojo> newarrivals_pojos, int resource) {
        this.context = context;
        this.newarrivals_pojos = newarrivals_pojos;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return newarrivals_pojos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = null;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, viewGroup, false);
            holder = new ViewHolder();

            holder.newname = row.findViewById(R.id.newArrival_product_name);
            holder.newprice= row.findViewById(R.id.newArrival_product_price);
            holder.newimg_product = row.findViewById(R.id.newArrival_product_image);
            holder.add_to_cart_arrival = row.findViewById(R.id.newArrival_btn_add_to_cart);
            holder.view_more_arrival = row.findViewById(R.id.newArrival_btn_view_more);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Newarrivals_pojo orderInfo = newarrivals_pojos.get(i);
        orderHelper =  new OrderHelper(context);
        dbHelper = new NewArrivalsHelper(context);
        detailsHelper = new DetailsHelper(context);

        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.newname.setText("Name:"+" "+orderInfo.getNewname());
        holder.newprice.setText("A$" + " "+orderInfo.getNewprice());
        Picasso.with(context).load(orderInfo.getNewimage()).into(holder.newimg_product);


        holder.view_more_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cname = newarrivals_pojos.get(i).getNewname();
                cimage = newarrivals_pojos.get(i).getNewimage();
                cprice = newarrivals_pojos.get(i).getNewprice();
                cdesc = newarrivals_pojos.get(i).getNewdesc();
                cat_id = newarrivals_pojos.get(i).getNewcid();

                ContentValues cvdetails = new ContentValues();
                cvdetails.put("name", cname);
                cvdetails.put("price", cprice);
                cvdetails.put("imageone", cimage);
                cvdetails.put("desc" , cdesc);
                cvdetails.put("c_id", cat_id);
                detailsHelper.insertdetails(cvdetails);
                Intent intent = new Intent(context , DetailsActivity.class);

                context.startActivity(intent);
            }
        });

        holder.add_to_cart_arrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname = newarrivals_pojos.get(i).getNewname();
                cimage = newarrivals_pojos.get(i).getNewimage();
                cprice = newarrivals_pojos.get(i).getNewprice();
                img_id = newarrivals_pojos.get(i).getImg_id();
                //cdesc = newarrivals_pojos.get(i).getNewdesc();
                ContentValues cv = new ContentValues();
                ArrayList<OrderedProducts_pojo> cartItems = orderHelper.getOrderMessage();
                for(OrderedProducts_pojo cartItem : cartItems){
                    if(cartItem.getOrderedcat_id().equals(String.valueOf(img_id))){
                        cv.put("count",cartItem.count+1);
                        orderHelper.updateCount(img_id.toString(),cv);
                        return;
                    }
                }

                cv.put("name", cname);
                cv.put("price", cprice);
                cv.put("imageone", cimage);
                cv.put("cat_id", img_id);
                cv.put("count", 1);
                orderHelper.insertOrderInfo(cv);
                Toast.makeText(context, "added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

    static class ViewHolder {
        TextView newname, newprice, newid, newcid;
        Button add_to_cart_arrival, view_more_arrival;
        ImageView newimg_product;
    }
}

