package com.sonika.nepstra.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.helpers.WomenHelper;
import com.sonika.nepstra.listener.ListViewListener;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/16/2017.
 */

public class WomenAdapter extends BaseAdapter {
    Context context;
    List<WomenPoducts_pojo> womenPoductsPojos = new ArrayList<WomenPoducts_pojo>();
    int resource;
    WomenHelper dbHelper;
    OrderHelper orderHelper;
    DetailsHelper detailsHelper;
    String cname, cimage, cprice, cdesc;
    Integer catid, imgid;

    public WomenAdapter(Context context, List<WomenPoducts_pojo> womenPoductsPojos, int resource) {
        this.context = context;
        this.womenPoductsPojos = womenPoductsPojos;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return womenPoductsPojos.size();
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

            holder.wname = row.findViewById(R.id.women_product_name);
            holder.wprice= row.findViewById(R.id.women_product_price);
            holder.wimg_product = row.findViewById(R.id.women_product_image);
            holder.btn_women_add_to_cart = row.findViewById(R.id.women_btn_add_to_cart);
            holder.btn_women_view_more = row.findViewById(R.id.women_btn_view_more);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final WomenPoducts_pojo orderInfo = womenPoductsPojos.get(i);

        dbHelper = new WomenHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper =  new DetailsHelper(context);
        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.wname.setText("Name:"+" "+orderInfo.getWname());
        holder.wprice.setText("A$" + " "+orderInfo.getWprice());
        Picasso.with(context).load(orderInfo.getWimage()).into(holder.wimg_product);

        holder.btn_women_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cimage = womenPoductsPojos.get(i).getWimage();
                cname = womenPoductsPojos.get(i).getWname();
                cprice = womenPoductsPojos.get(i).getWprice();
                cdesc = womenPoductsPojos.get(i).getWdesc();
                catid = womenPoductsPojos.get(i).getCid();

                ContentValues cv1 = new ContentValues();
                cv1.put("name", cname);
                cv1.put("price", cprice);
                cv1.put("imageone", cimage);
                cv1.put("desc" , cdesc);
                cv1.put("c_id", catid);
                detailsHelper.insertdetails(cv1);

                Intent intent = new Intent(context , DetailsActivity.class);
                context.startActivity(intent);
                Log.e("joker", "lol");
            }
        });
        assert holder.btn_women_add_to_cart != null;
        holder.btn_women_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                cimage = womenPoductsPojos.get(i).getWimage();
                cname = womenPoductsPojos.get(i).getWname();
                cprice = womenPoductsPojos.get(i).getWprice();
                imgid = womenPoductsPojos.get(i).getImg_id();

                ContentValues cv = new ContentValues();
                ArrayList<OrderedProducts_pojo> cartItems = orderHelper.getOrderMessage();
                for(OrderedProducts_pojo cartItem : cartItems){
                    if(cartItem.getOrderedcat_id().equals(String.valueOf(imgid))){
                        cv.put("count",cartItem.count+1);
                        orderHelper.updateCount(imgid.toString(),cv);
                        return;
                    }
                }

                cv.put("name", cname);
                cv.put("price", cprice);
                cv.put("imageone", cimage);
                cv.put("cat_id", imgid);
                cv.put("count", 1);
                orderHelper.insertOrderInfo(cv);
                Toast.makeText(context, "added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        return row;
    }

    static class ViewHolder {
        TextView wname, wprice, wid, wcid;
        Button btn_women_add_to_cart, btn_women_view_more;
        ImageView wimg_product;
    }

}

