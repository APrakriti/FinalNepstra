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
import com.sonika.nepstra.helpers.JwelleryHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.helpers.SportsHelper;
import com.sonika.nepstra.pojo.Jwellery_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.sonika.nepstra.pojo.Sports_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/18/2017.
 */

public class SportsAdapter extends BaseAdapter{
    Context context;
    List<Sports_pojo> sports_pojoList = new ArrayList<Sports_pojo>();
    int resource;
    SportsHelper dbHelper;
    OrderHelper orderHelper;
    DetailsHelper detailsHelper;
    String cname, cimage, cprice, cdesc;
    Integer cid, imgid;
    public SportsAdapter(Context context, List<Sports_pojo> sports_pojoList, int resource) {
        this.context = context;
        this.sports_pojoList = sports_pojoList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return sports_pojoList.size();
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

            holder.sportsname = row.findViewById(R.id.sports_product_name);
            holder.sportsprice= row.findViewById(R.id.sports_product_price);
            holder.sportsimg_product = row.findViewById(R.id.sports_product_image);
            holder.btn_sport_add_to_cart = row.findViewById(R.id.sports_btn_add_to_cart);
            holder.btn_sport_view_more = row.findViewById(R.id.sports_btn_view_more);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Sports_pojo sportsinfo =sports_pojoList.get(i);

        dbHelper = new SportsHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper = new DetailsHelper(context);
        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.sportsname.setText("Name:"+" "+sportsinfo.getSportsname());
        holder.sportsprice.setText("A$" + " "+sportsinfo.getSportsprice());
        Picasso.with(context).load(sportsinfo.getSportsimage()).into(holder.sportsimg_product);

        holder.btn_sport_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cname = sports_pojoList.get(i).getSportsname();
                cimage = sports_pojoList.get(i).getSportsimage();
                cprice = sports_pojoList.get(i).getSportsprice();
                cdesc = sports_pojoList.get(i).getSportsdesc();
                cid = sports_pojoList.get(i).getSportscid();
                ContentValues cvdetails = new ContentValues();
                cvdetails.put("name", cname);
                cvdetails.put("price", cprice);
                cvdetails.put("imageone", cimage);
                cvdetails.put("desc" , cdesc);
                cvdetails.put("c_id" , cid);
                detailsHelper.insertdetails(cvdetails);
                Intent intent = new Intent(context , DetailsActivity.class);

                context.startActivity(intent);
            }
        });

        holder.btn_sport_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname = sports_pojoList.get(i).getSportsname();
                cimage = sports_pojoList.get(i).getSportsimage();
                cprice =sports_pojoList.get(i).getSportsprice();
                imgid = sports_pojoList.get(i).getImg_id();

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
        TextView sportsname,sportsprice, sid, scid;
        Button btn_sport_add_to_cart, btn_sport_view_more;
        ImageView sportsimg_product;
    }
}

