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
import com.sonika.nepstra.helpers.MensHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.Mens_pojo;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/17/2017.
 */

public class MensAdapter extends BaseAdapter {
    Context context;
    List<Mens_pojo> mens_pojos = new ArrayList<Mens_pojo>();
    int resource;
    MensHelper dbHelper;
    DetailsHelper detailsHelper;
    OrderHelper orderHelper;
    String cname, cprice, cimage,cdesc;
    Integer cid, imgid;

    public MensAdapter(Context context, List<Mens_pojo> mens_pojos, int resource) {
        this.context = context;
        this.mens_pojos = mens_pojos;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return mens_pojos.size();
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

            holder.menname = row.findViewById(R.id.mens_product_name);
            holder.menprice= row.findViewById(R.id.mens_product_price);
            holder.menimg_product = row.findViewById(R.id.mens_product_image);
            holder.btn_men_add_to_cart = row.findViewById(R.id.mens_btn_add_to_cart);
            holder.btn_men_view_more = row.findViewById(R.id.mens_btn_view_more);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Mens_pojo orderInfo = mens_pojos.get(i);

        dbHelper = new MensHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper = new DetailsHelper(context);
        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.menname.setText("Name:"+" "+orderInfo.getMenname());
        holder.menprice.setText("A$" + " "+orderInfo.getMenprice());
        Picasso.with(context).load(orderInfo.getMenimage()).into(holder.menimg_product);

        holder.btn_men_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cname =mens_pojos.get(i).getMenname();
                cimage = mens_pojos.get(i).getMenimage();
                cprice =mens_pojos.get(i).getMenprice();
                cid = mens_pojos.get(i).getMencid();

                cdesc = mens_pojos.get(i).getMenDesc();
                ContentValues cv1 = new ContentValues();
                cv1.put("name", cname);
                cv1.put("price", cprice);
                cv1.put("imageone", cimage);
                cv1.put("desc" , cdesc);
                cv1.put("c_id" , cid);
                detailsHelper.insertdetails(cv1);

                Intent intent = new Intent(context , DetailsActivity.class);
                context.startActivity(intent);

            }
        });


        holder.btn_men_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname =mens_pojos.get(i).getMenname();
                cimage = mens_pojos.get(i).getMenimage();
                cprice =mens_pojos.get(i).getMenprice();
                imgid = mens_pojos.get(i).getMen_i_id();


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
        TextView menname, menprice, menid, mencid;
        Button btn_men_add_to_cart, btn_men_view_more;

        ImageView menimg_product;

    }
}
