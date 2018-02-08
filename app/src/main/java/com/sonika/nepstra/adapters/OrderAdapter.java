package com.sonika.nepstra.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.listener.CountListener;
import com.sonika.nepstra.listener.ListViewListener;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sonika on 5/9/2017.
 */

public class OrderAdapter extends BaseAdapter  {
    SharedPreferences sm;
    String spname, spprice, spquantity;
    OrderedProducts_pojo orderedProducts_pojo;

    Context context;
    List<OrderedProducts_pojo> cartlist = new ArrayList<OrderedProducts_pojo>();
    int resource;
    OrderHelper dbHelper;
    private ListViewListener mListener;
    private CountListener mcountListener;


    public OrderAdapter(Context context, List<OrderedProducts_pojo> objects, int resource) {
        this.context = context;
        this.cartlist = objects;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return cartlist.size();
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
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.name = row.findViewById(R.id.txt_name_add_to_cart);
            holder.price = row.findViewById(R.id.txt_price_add_to_cart);
            holder.img_product = row.findViewById(R.id.img_add_to_cart);
            holder.qty = row.findViewById(R.id.ordered_productlist_id);
            holder.btnRemove = row.findViewById(R.id.btn_add_to_cart_remove);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        final OrderedProducts_pojo orderInfo = cartlist.get(position);
        dbHelper = new OrderHelper(context);
        final ViewHolder finalHolder = holder;
        finalHolder.name.setText(orderInfo.getOrderedname());
        final String itemTotalPrice = String.valueOf(Integer.valueOf(orderInfo.getCount()) * (Integer.valueOf(orderInfo.getOrderedprice())));
        finalHolder.price.setText(itemTotalPrice);
        Picasso.with(context).load(orderInfo.getOrderedimage()).into(finalHolder.img_product);
        finalHolder.qty.setText(orderInfo.getCount() + " ");






        holder.btnRemove.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view)
                {
                   int a = position;
                   int count = mcountListener.getItemCount(a);
                   finalHolder.qty.setText(count + "");
//                   notifyDataSetChanged();
                   final String itemTotalPrice = String.valueOf(Integer.valueOf(count) * (Integer.valueOf(orderInfo.getOrderedprice())));
                   finalHolder.price.setText(itemTotalPrice);
                    }



            });


        spname = finalHolder.name.getText().toString();
        spprice = finalHolder.price.getText().toString();
        spquantity= finalHolder.qty.getText().toString();
//        ArrayList userList   = new ArrayList();
//        userList.add(new Users());
//        userList.add(new Users());
//        SharedPreferences prefs = context.getSharedPreferences("ORDERPREF", Context.MODE_PRIVATE);
////save the user list to preference
//        SharedPreferences.Editor editor = prefs.edit();
//        try {
//            editor.putString("UserList", ObjectSerializer.serialize(userList));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        editor.commit();

        SharedPreferences sp = context.getSharedPreferences("ORDERPREF", MODE_PRIVATE);
        SharedPreferences.Editor spedit = sp.edit();
        spedit.putString("name", spname);
        spedit.putString("price", spprice);
        spedit.putString("quantity", spquantity);
        spedit.commit();


        String name = sp.getString("email", null);



//        int sum = 0;
//        for (int i = 0; i < cartlist.size(); i++)
//        {
//            sum = Integer.parseInt(holder.price.getText().toString());
//
//        }
//        Log.e("sum", String.valueOf(sum));
        return row;

//        Log.e("price", String.valueOf(holder.price.getText()));
//        OrderHelper dbhelper = new OrderHelper(context);
//        ContentValues cv =  new ContentValues();
//        cv.put("price", holder.orderid.getText().toString());
//        dbhelper.insertOrderInfo(cv);
    }

//    }
    public void setListener(ListViewListener listener) {
        mListener = listener;
    }

    public void setCountListener(CountListener countListener){
        mcountListener = countListener;
    }


    static class ViewHolder {
        TextView name, price, qty, catid;
        Button btnRemove;
        ImageView img_product;
    }
}