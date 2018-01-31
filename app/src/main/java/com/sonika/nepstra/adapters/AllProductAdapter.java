package com.sonika.nepstra.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sonika.nepstra.DetailsActivity;
import com.sonika.nepstra.Navigations.Jwellery;
import com.sonika.nepstra.R;
import com.sonika.nepstra.helpers.ArtAndCraftHelper;
import com.sonika.nepstra.helpers.BooksHelper;
import com.sonika.nepstra.helpers.DetailsHelper;
import com.sonika.nepstra.helpers.JwelleryHelper;
import com.sonika.nepstra.helpers.MensHelper;
import com.sonika.nepstra.helpers.NewArrivalsHelper;
import com.sonika.nepstra.helpers.OrderHelper;
import com.sonika.nepstra.helpers.SportsHelper;
import com.sonika.nepstra.helpers.WomenHelper;
import com.sonika.nepstra.pojo.AllProducts;
import com.sonika.nepstra.pojo.ArtAndCraft_pojo;
import com.sonika.nepstra.pojo.Books_Pojo;
import com.sonika.nepstra.pojo.Jwellery_pojo;
import com.sonika.nepstra.pojo.Mens_pojo;
import com.sonika.nepstra.pojo.Newarrivals_pojo;
import com.sonika.nepstra.pojo.OrderedProducts_pojo;
import com.sonika.nepstra.pojo.Sports_pojo;
import com.sonika.nepstra.pojo.WomenPoducts_pojo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sonika on 9/19/2017.
 */

public class AllProductAdapter extends RecyclerView.Adapter<AllProductHolder> {
    public Context context;
    private List<AllProducts> allProductList;

    String oname, oprice, oimage, odesc;
    Integer cat_id, img_id;
    OrderHelper dbHelper;
    List<OrderedProducts_pojo> cartProductsList;
    //DetailsHelper detailsHelper;
    WomenHelper womenHelper;
    BooksHelper booksHelper;
    NewArrivalsHelper arrivalsHelper;
    ArtAndCraftHelper artAndCraftHelper;
    MensHelper mensHelper;
    JwelleryHelper jwelleryHelper;
    SportsHelper sportsHelper;
    DetailsHelper detailsHelper;
    public AllProductAdapter(Context context, List<AllProducts> allproductList) {
        this.context = context;
        this.allProductList = allproductList;
    }

    @Override
    public AllProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.all_productlist, parent, false);


        dbHelper = new OrderHelper(context);
        womenHelper = new WomenHelper(context);
        arrivalsHelper = new NewArrivalsHelper(context);
        artAndCraftHelper = new ArtAndCraftHelper(context);
        mensHelper = new MensHelper(context);
        jwelleryHelper = new JwelleryHelper(context);
        sportsHelper = new SportsHelper(context);
        booksHelper = new BooksHelper(context);
        detailsHelper = new DetailsHelper(context);
        return new AllProductHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllProductHolder allholder, final int position) {
        allholder.allproductName.setText(allProductList.get(position).getName());
        allholder.allproductPrice.setText("A$" + allProductList.get(position).getPrice());
        Picasso.with(context).load(allProductList.get(position).getI_src()).into(allholder.allproductImage);


        allholder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oname = allProductList.get(position).getName();
                oprice = allProductList.get(position).getPrice();
                oimage = allProductList.get(position).getI_src();
                odesc = allProductList.get(position).getDescription();
                cat_id = allProductList.get(position).getC_id();

                ContentValues contentValue = new ContentValues();
                contentValue.put("name", oname);
                contentValue.put("price", oprice);
                contentValue.put("imageone", oimage);
                contentValue.put("desc", odesc);
                contentValue.put("c_id", cat_id);
                detailsHelper.insertdetails(contentValue);

                Intent intent =  new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
//                AllProducts intentprod = allProductList.get(position);
//                Intent intent = new Intent(context, DetailsActivity.class);
//                intent.putExtra("hello", intentprod);
//                context.startActivity(intent);
            }
        });

        assert allholder.addtocart != null;
        allholder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                oname = allProductList.get(position).getName();
                oprice = allProductList.get(position).getPrice();
                oimage = allProductList.get(position).getI_src();
                img_id = allProductList.get(position).getI_id();

                ContentValues contentValues = new ContentValues();
                ArrayList<OrderedProducts_pojo> cartItems = dbHelper.getOrderMessage();
                for(OrderedProducts_pojo cartItem: cartItems){
                    if(cartItem.getOrderedcat_id().equals(String.valueOf(img_id))){
                        contentValues.put("count",cartItem.count+1);
                        dbHelper.updateCount(img_id.toString(),contentValues);
                        return;
                    }
                }

                contentValues.put("name", oname);
                contentValues.put("price", oprice);
                contentValues.put("imageone", oimage);
                contentValues.put("cat_id", img_id);
                contentValues.put("count", 1);
                dbHelper.insertOrderInfo(contentValues);






                //detailsHelper.insertdetails(contentValues);
//                cartProductsList = dbHelper.getOrderMessage();
//                Log.e("orderedsonika", String.valueOf(cartProductsList.size()));

//                mySharedPreference = new MySharedPreference(context);
////
//                mySharedPreference.addProductCount(cartProductsList.size());

            }
        });


        oname = allProductList.get(position).getName();
        oprice = allProductList.get(position).getPrice();
        oimage = allProductList.get(position).getI_src();
        odesc = allProductList.get(position).getDescription();
        cat_id = allProductList.get(position).getC_id();
        img_id = allProductList.get(position).getI_id();

        if (cat_id == 29) {
            ArrayList<WomenPoducts_pojo> womenItems = womenHelper.getwomen();
                ContentValues contentValues = new ContentValues();
                Log.e("insidefor", "hello");
                contentValues.put("c_id", cat_id);
                contentValues.put("i_id", img_id);
                contentValues.put("name", oname);
                contentValues.put("price", oprice);
                contentValues.put("imageone", oimage);
                contentValues.put("desc", odesc);
                womenHelper.insertwomen(contentValues);
                for (int i = 0; i < womenItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(womenItems.get(i).getImg_id())) {
                    womenHelper.deleteWomen();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    womenHelper.insertwomen(contentValues1);

                }
            }
//        }}
        if (cat_id == 17) {
            ArrayList<Newarrivals_pojo> newItems = arrivalsHelper.getarrivals();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            arrivalsHelper.insertarrivals(contentValues);
            for (int i = 0; i < newItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(newItems.get(i).getImg_id())) {
                    arrivalsHelper.deleteArrival();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    arrivalsHelper.insertarrivals(contentValues1);

                }
        }
        if (cat_id == 30) {
            ArrayList<ArtAndCraft_pojo> artandcraftItems = artAndCraftHelper.getartandcraft();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            artAndCraftHelper.insertwartandcraft(contentValues);
            for (int i = 0; i < artandcraftItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(artandcraftItems.get(i).getArtimgid())) {
                    artAndCraftHelper.deleteArtAndCraft();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    artAndCraftHelper.insertwartandcraft(contentValues1);
                }
        }
        if (cat_id == 28){
            ArrayList<Mens_pojo> menItems = mensHelper.getmen();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            mensHelper.insertmen(contentValues);
            for (int i = 0; i < menItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(menItems.get(i).getMen_i_id())) {
                    mensHelper.deleteMen();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    mensHelper.insertmen(contentValues1);
                }
        }

        if (cat_id == 25){
            ArrayList<Jwellery_pojo> jwellertItems = jwelleryHelper.getjwellery();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            jwelleryHelper.insertjwellery(contentValues);
            for (int i = 0; i < jwellertItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(jwellertItems.get(i).getJwelleryi_id())) {
                    jwelleryHelper.deleteJwellery();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    jwelleryHelper.insertjwellery(contentValues1);
                }
        }
        if (cat_id == 27){
            ArrayList<Sports_pojo> sportsItems = sportsHelper.getsports();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            sportsHelper.insertsports(contentValues);
            for (int i = 0; i < sportsItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(sportsItems.get(i).getImg_id())) {
                    sportsHelper.deleteSports();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    sportsHelper.insertsports(contentValues1);
                }
        }
        if (cat_id == 26) {
            ArrayList<Books_Pojo> booksItems = booksHelper.getbooks();
            ContentValues contentValues = new ContentValues();
            contentValues.put("c_id", cat_id);
            contentValues.put("i_id", img_id);
            contentValues.put("name", oname);
            contentValues.put("price", oprice);
            contentValues.put("imageone", oimage);
            contentValues.put("desc", odesc);
            booksHelper.insertbooks(contentValues);
            for (int i = 0; i < booksItems.size(); i++)
                if (allProductList.get(position).getI_id().equals(booksItems.get(i).getBookimgid())) {
                    booksHelper.deleteBooks();
                    ContentValues contentValues1 = new ContentValues();
                    contentValues1.put("c_id", cat_id);
                    contentValues1.put("i_id", img_id);
                    contentValues1.put("name", oname);
                    contentValues1.put("price", oprice);
                    contentValues1.put("imageone", oimage);
                    contentValues1.put("desc", odesc);
                    booksHelper.insertbooks(contentValues1);
                }
        }



    }



    @Override
    public int getItemCount() {
        Log.e("sanjeev", String.valueOf(allProductList.size()));
        return allProductList.size();
    }
}




