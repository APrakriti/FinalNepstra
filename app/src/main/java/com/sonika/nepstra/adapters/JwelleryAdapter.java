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
import com.sonika.nepstra.Navigations.Jwellery;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.DetailsHelper;
import com.sonika.nepstra.helpers.JwelleryHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Jwellery_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import org.jsoup.Connection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/17/2017.
 */

public class JwelleryAdapter extends BaseAdapter{
    Context context;
    List<Jwellery_pojo> jwellery_pojoList = new ArrayList<Jwellery_pojo>();
    int resource;
    JwelleryHelper dbHelper;
    OrderHelper orderHelper;
    DetailsHelper detailsHelper;
    String cname, cprice, cimage,cdesc;
    Integer cid, i_id;
    public JwelleryAdapter(Context context, List<Jwellery_pojo> jwellery_pojoList, int resource) {
        this.context = context;
        this.jwellery_pojoList = jwellery_pojoList;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return jwellery_pojoList.size();
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

            holder.jwelleryname = row.findViewById(R.id.jwellery_product_name);
            holder.jwelleryprice= row.findViewById(R.id.jwellery_product_price);
            holder.jwelleryimg_product = row.findViewById(R.id.jwellery_product_image);
            holder.btn_jwellery_add_to_cart = row.findViewById(R.id.jwellery_btn_add_to_cart);
            holder.btn_jwellery_view_more = row.findViewById(R.id.jwellery_btn_view_more);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Jwellery_pojo orderInfo = jwellery_pojoList.get(i);

        dbHelper = new JwelleryHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper = new DetailsHelper(context);

        //holder.orderid.setText(orderInfo.getOrderid().toString());


        holder.jwelleryname.setText("Name:"+" "+orderInfo.getJwelleryname());
        holder.jwelleryprice.setText("A$" + " "+orderInfo.getJwelleryprice());
        Picasso.with(context).load(orderInfo.getJwelleryimage()).into(holder.jwelleryimg_product);


        holder.btn_jwellery_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                cname = jwellery_pojoList.get(i).getJwelleryname();
                cimage = jwellery_pojoList.get(i).getJwelleryimage();
                cprice = jwellery_pojoList.get(i).getJwelleryprice();
                cdesc = jwellery_pojoList.get(i).getJwellerydesc();
                cid = jwellery_pojoList.get(i).getJwellerycid();
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

        holder.btn_jwellery_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cname = jwellery_pojoList.get(i).getJwelleryname();
                cimage = jwellery_pojoList.get(i).getJwelleryimage();
                cprice = jwellery_pojoList.get(i).getJwelleryprice();
                i_id = jwellery_pojoList.get(i).getJwelleryi_id();

                ContentValues cv = new ContentValues();
                ArrayList<OrderedProducts_pojo> cartItems = orderHelper.getOrderMessage();
                for(OrderedProducts_pojo cartItem : cartItems){
                    if(cartItem.getOrderedcat_id().equals(String.valueOf(i_id))){
                        cv.put("count",cartItem.count+1);
                        orderHelper.updateCount(i_id.toString(),cv);
                        return;
                    }
                }
                cv.put("name", cname);
                cv.put("price", cprice);
                cv.put("imageone", cimage);
                cv.put("cat_id", i_id);
                cv.put("count", 1);
                orderHelper.insertOrderInfo(cv);
                Toast.makeText(context, "added to cart", Toast.LENGTH_SHORT).show();
            }
        });


        return row;
    }

    static class ViewHolder {
        TextView jwelleryname,jwelleryprice, jid, jcid;
        Button btn_jwellery_add_to_cart, btn_jwellery_view_more;
        ImageView jwelleryimg_product;
    }
}

