package com.example.aviral.ss;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aviral on 11/24/2015.
 */
public class GalleryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gallery_fragment,container,false);

        GridView gView = (GridView) v.findViewById(R.id.gridview);
        gView.setAdapter(new MyAdapter(getActivity()));

        return v;
    }

    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<Item>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            items.add(new Item("Image 1", R.drawable.n01));
            items.add(new Item("Image 2", R.drawable.n02));
            items.add(new Item("Image 3", R.drawable.n03));
            items.add(new Item("Image 4", R.drawable.n04));
            items.add(new Item("Image 5", R.drawable.n05));
            items.add(new Item("Image 6", R.drawable.n06));
            items.add(new Item("Image 7", R.drawable.n07));
            items.add(new Item("Image 1", R.drawable.n08));
            items.add(new Item("Image 2", R.drawable.n09));
            items.add(new Item("Image 3", R.drawable.n10));
            items.add(new Item("Image 1", R.drawable.n11));
            items.add(new Item("Image 2", R.drawable.n12));
            items.add(new Item("Image 3", R.drawable.n13));
            items.add(new Item("Image 4", R.drawable.n14));
            items.add(new Item("Image 5", R.drawable.n15));
            items.add(new Item("Image 6", R.drawable.n16));
            items.add(new Item("Image 7", R.drawable.n17));
            items.add(new Item("Image 1", R.drawable.n18));
            items.add(new Item("Image 2", R.drawable.n19));
            items.add(new Item("Image 3", R.drawable.n20));
            items.add(new Item("Image 1", R.drawable.n21));
            items.add(new Item("Image 2", R.drawable.n22));
            items.add(new Item("Image 3", R.drawable.n23));
            items.add(new Item("Image 4", R.drawable.n24));
            items.add(new Item("Image 5", R.drawable.n25));
            items.add(new Item("Image 6", R.drawable.n26));
            items.add(new Item("Image 7", R.drawable.n27));
            items.add(new Item("Image 1", R.drawable.n28));
            items.add(new Item("Image 2", R.drawable.n29));
            items.add(new Item("Image 3", R.drawable.n30));
            items.add(new Item("Image 1", R.drawable.n31));
            items.add(new Item("Image 2", R.drawable.n32));
            items.add(new Item("Image 3", R.drawable.n33));
            items.add(new Item("Image 4", R.drawable.n34));
            items.add(new Item("Image 5", R.drawable.n35));
            items.add(new Item("Image 6", R.drawable.n36));
            items.add(new Item("Image 7", R.drawable.n37));
            items.add(new Item("Image 1", R.drawable.n38));
            items.add(new Item("Image 2", R.drawable.n39));
            items.add(new Item("Image 3", R.drawable.n40));
            items.add(new Item("Image 1", R.drawable.n41));
            items.add(new Item("Image 2", R.drawable.n42));
            items.add(new Item("Image 3", R.drawable.n43));
            items.add(new Item("Image 4", R.drawable.n44));
            items.add(new Item("Image 5", R.drawable.n45));
            items.add(new Item("Image 6", R.drawable.n46));
            items.add(new Item("Image 7", R.drawable.n47));
            items.add(new Item("Image 1", R.drawable.n48));
            items.add(new Item("Image 2", R.drawable.n49));
            items.add(new Item("Image 3", R.drawable.n50));
            items.add(new Item("Image 1", R.drawable.n51));
            items.add(new Item("Image 2", R.drawable.n52));
            items.add(new Item("Image 3", R.drawable.n53));
            items.add(new Item("Image 4", R.drawable.n54));
            items.add(new Item("Image 5", R.drawable.n55));
            items.add(new Item("Image 6", R.drawable.n56));
            items.add(new Item("Image 7", R.drawable.n57));
            items.add(new Item("Image 1", R.drawable.n58));
            items.add(new Item("Image 2", R.drawable.n59));
            items.add(new Item("Image 3", R.drawable.n60));
            items.add(new Item("Image 1", R.drawable.n61));
            items.add(new Item("Image 2", R.drawable.n62));
            items.add(new Item("Image 3", R.drawable.n63));
            items.add(new Item("Image 4", R.drawable.n64));
            items.add(new Item("Image 5", R.drawable.n65));
            items.add(new Item("Image 6", R.drawable.n66));
            items.add(new Item("Image 7", R.drawable.n67));
            items.add(new Item("Image 1", R.drawable.n68));
            items.add(new Item("Image 2", R.drawable.n69));
            items.add(new Item("Image 1", R.drawable.n70));
            //items.add(new Item("Image 2", R.drawable.n71));
            items.add(new Item("Image 1", R.drawable.n72));
            items.add(new Item("Image 2", R.drawable.n73));
            items.add(new Item("Image 1", R.drawable.n74));
            items.add(new Item("Image 2", R.drawable.n75));
            items.add(new Item("Image 1", R.drawable.n76));
            items.add(new Item("Image 2", R.drawable.n77));
            items.add(new Item("Image 1", R.drawable.n78));


        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            TextView name;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
}
