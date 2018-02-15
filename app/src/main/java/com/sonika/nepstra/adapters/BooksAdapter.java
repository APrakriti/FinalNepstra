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
import com.sonika.nepstra.Navigations.ArtAndCraft;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.BooksHelper;
import com.sonika.nepstra.helpers.DetailsHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Books_Pojo;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonika on 10/17/2017.
 */

public class BooksAdapter extends BaseAdapter {
    Context context;
    List<Books_Pojo> books_pojos = new ArrayList<Books_Pojo>();
    int resource;
    BooksHelper dbHelper;
    DetailsHelper detailsHelper;
    OrderHelper orderHelper;
    String aname, aprice, aimage, adesc;
    Integer cid, i_id;
    public BooksAdapter(Context context, List<Books_Pojo> books_pojos, int resource) {
        this.context = context;
        this.books_pojos = books_pojos;
        this.resource = resource;
    }


    @Override
    public int getCount() {
        return books_pojos.size();
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

            holder.bookname = row.findViewById(R.id.books_product_name);
            holder.bookprice= row.findViewById(R.id.books_product_price);
            holder.bookimg_product = row.findViewById(R.id.books_product_image);
            holder.btn_book_add_to_cart = row.findViewById(R.id.books_btn_add_to_cart);
            holder.btn_book_view_more = row.findViewById(R.id.books_btn_view_more);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        final Books_Pojo orderInfo = books_pojos.get(i);

        dbHelper = new BooksHelper(context);
        orderHelper = new OrderHelper(context);
        detailsHelper = new DetailsHelper(context);
        //holder.orderid.setText(orderInfo.getOrderid().toString());




        holder.bookname.setText("Name:" + " " + orderInfo.getBookname());
        holder.bookprice.setText("A$" + " " + orderInfo.getBookprice());
        Picasso.with(context).load(orderInfo.getBookimgid()).into(holder.bookimg_product);


        holder.btn_book_view_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view more", Toast.LENGTH_SHORT).show();
                aname = books_pojos.get(i).getBookname();
                aimage = books_pojos.get(i).getBookimage();
                aprice = books_pojos.get(i).getBookprice();
                adesc = books_pojos.get(i).getBookdesc();
                cid  = books_pojos .get(i).getBookcid();
                ContentValues cv1 = new ContentValues();
                cv1.put("name", aname);
                cv1.put("price", aprice);
                cv1.put("imageone", aimage);
                cv1.put("desc" , adesc);
                cv1.put("c_id", cid);
                detailsHelper.insertdetails(cv1);

                Intent intent = new Intent(context , DetailsActivity.class);
                context.startActivity(intent);

            }
        });

        holder.btn_book_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aname = books_pojos.get(i).getBookname();
                aimage = books_pojos.get(i).getBookimage();
                aprice = books_pojos.get(i).getBookprice();
                //adesc = artAndCraft_pojos.get(i).getArtdesc();
                i_id = books_pojos.get(i).getBookimgid();

                ContentValues cv = new ContentValues();
                ArrayList<OrderedProducts_pojo> cartItems = orderHelper.getOrderMessage();
                for(OrderedProducts_pojo cartItem : cartItems){
                    if(cartItem.getOrderedcat_id().equals(String.valueOf(i_id))){
                        cv.put("count",cartItem.count+1);
                        orderHelper.updateCount(i_id.toString(),cv);
                        return;
                    }
                }
                cv.put("name", aname);
                cv.put("price", aprice);
                cv.put("imageone", aimage);
                cv.put("cat_id", i_id);
                cv.put("count", 1);
                orderHelper.insertOrderInfo(cv);
                Toast.makeText(context, "added to cart", Toast.LENGTH_SHORT).show();
            }
        });
        return row;
    }

    static class ViewHolder {
        TextView bookname, bookprice, bookid, bookcid;
        Button btn_book_add_to_cart, btn_book_view_more;
        ImageView bookimg_product;
    }
}
