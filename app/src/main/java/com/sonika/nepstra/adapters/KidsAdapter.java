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
import com.sonika.nepstra.Navigations.Kids;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.DetailsHelper;
import com.sonika.nepstra.helpers.JwelleryHelper;
import com.sonika.nepstra.helpers.KidsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.Jwellery_pojo;
import com.sonika.nepstra.pojo.Kids_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/17/2017.
 */

public class KidsAdapter extends BaseAdapter{
    Context context;
    List<Kids_pojo> kids_pojoListt = new ArrayList<Kids_pojo>();
    int resource;
    OrderHelper orderHelper;
    KidsHelper dbHelper;
    DetailsHelper detailsHelper;
    String cname, cprice, cimage, cdesc;
    Integer cid, imgid;
    public KidsAdapter(Context context, List<Kids_pojo> kids_pojoListt, int resource) {
        this.context = context;
        this.kids_pojoListt = kids_pojoListt;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return kids_pojoListt.size();
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

            holder.kidsname = row.findViewById(R.id.kids_product_name);
            holder.kidsprice= row.findViewById(R.id.kids_product_price);
            holder.kidsimg_product = row.findViewById(R.id.kids_product_image);
            holder.btn_kids_add_to_cart = row.findViewById(R.id.kids_btn_add_to_cart);
            holder.btn_kids_view_more = row.findViewById(R.id.kids_btn_view_more);


            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Kids_pojo orderInfo = kids_pojoListt.get(i);

        dbHelper = new KidsHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper = new DetailsHelper(context);
        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.kidsname.setText("Name:"+" "+orderInfo.getKidsname());
        holder.kidsprice.setText("A$" + " "+orderInfo.getKidsprice());
        Picasso.with(context).load(orderInfo.getKidsimage()).into(holder.kidsimg_product);

        holder.btn_kids_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cname =kids_pojoListt.get(i).getKidsname();
                cimage = kids_pojoListt.get(i).getKidsimage();
                cprice = kids_pojoListt.get(i).getKidsprice();
                cdesc = kids_pojoListt.get(i).getKidsdesc();
                cid = kids_pojoListt.get(i).getKidscid();
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

        holder.btn_kids_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname =kids_pojoListt.get(i).getKidsname();
                cimage = kids_pojoListt.get(i).getKidsimage();
                cprice =kids_pojoListt.get(i).getKidsprice();
                imgid = kids_pojoListt.get(i).getKidsi_id();

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
        TextView kidsname,kidsprice, kid, kcid;
        Button btn_kids_add_to_cart, btn_kids_view_more;
        ImageView kidsimg_product;
    }
}



