package com.hx.mypresent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hx.mypresent.R;
import com.hx.mypresent.bean.GiftBean;
import com.hx.mypresent.other.SodukuGridVIew;

import it.sephiroth.android.library.picasso.Picasso;
import za.co.immedia.pinnedheaderlistview.SectionedBaseAdapter;

/**
 * Created by dllo on 16/5/18.
 */
public class GiftAdapter extends SectionedBaseAdapter {
    private Context context;
    private GiftBean giftBean;

    public GiftAdapter(Context context) {
        this.context = context;
    }

    public void setGiftBean(GiftBean giftBean) {
        this.giftBean = giftBean;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i, int i1) {
        return giftBean.getData().getCategories().get(i).getSubcategories().get(i1);
    }

    @Override
    public long getItemId(int i, int i1) {
        return i1;
    }

    @Override
    public int getSectionCount() {
        return giftBean == null? 0: giftBean.getData().getCategories().size();
    }

    @Override
    public int getCountForSection(int i) {
        return 1;
    }

    @Override
    public View getItemView(final int i, final int i1, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fragment_category_gift_fragment_right, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return giftBean == null? 0: giftBean.getData().getCategories().get(i).getSubcategories().size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_giftview_gv, parent, false);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.textView.setText(giftBean.getData().getCategories().get(i).getSubcategories().get(position).getName());
                Picasso.with(context).load(giftBean.getData().getCategories().get(i).getSubcategories().get(position).getIcon_url()).into(holder.imageView);
                return convertView;
            }

            class ViewHolder {
                ImageView imageView;
                TextView textView;

                public ViewHolder(View itemView) {
                    imageView = (ImageView) itemView.findViewById(R.id.item_fragment_category_gv_iv);
                    textView = (TextView) itemView.findViewById(R.id.item_fragment_category_gv_tv);
                }
            }
        });
        return view;
    }

    class ViewHolder {
        SodukuGridVIew gridView;
       TextView textView;
        public ViewHolder(View itemView) {
            gridView = (SodukuGridVIew) itemView.findViewById(R.id.item_fragment_category_gift_fragment_right_gv);

        }
    }


    @Override
    public View getSectionHeaderView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.item_sort_left_gift_lv, viewGroup, false);
        } else {
            layout = (LinearLayout) view;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.item_sort_left_tv)).setText(giftBean.getData().getCategories().get(i).getName());
        return layout;
    }
}
