package com.example.quanlybanhang;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quanlybanhang.dao.UserDao;
import com.example.quanlybanhang.model.User;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<User> arrNguoidung;
    public Activity context;
    public LayoutInflater inflater;
    UserDao nguoiDungDao;

    CallBack callBack;

    public NguoiDungAdapter(List<User> arrNguoidung, Activity context) {
        this.arrNguoidung = arrNguoidung;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new UserDao(context);
    }

    @Override
    public int getCount() {
        return arrNguoidung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoidung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView imgavataruser;
        TextView txtName;
        TextView txtphone;
        ImageView imgDelete;
        LinearLayout lnInfo;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.customuser, null);
            holder.imgavataruser = (ImageView) convertView.findViewById(R.id.imgavataruser);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvname);
            holder.txtphone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.imgdeleteuser);
            holder.lnInfo = convertView.findViewById(R.id.lnInfo);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
        User _entry = (User) arrNguoidung.get(position);
        holder.txtName.setText(_entry.getHoTen());
        holder.txtphone.setText(_entry.getDienThoai());
        holder.imgDelete.setOnClickListener(v -> {
            if (callBack != null)
                callBack.onItemDelete(arrNguoidung.get(position), position);
        });
        holder.imgavataruser.setOnClickListener(v -> {
            if (callBack != null)
                callBack.onItemSelect(arrNguoidung.get(position), position);
        });
        holder.lnInfo.setOnClickListener(v -> {
            if (callBack != null)
                callBack.onItemSelect(arrNguoidung.get(position), position);
        });
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<User> items) {
        this.arrNguoidung = items;
        notifyDataSetChanged();
    }

    interface CallBack {
        void onItemSelect(User user, int position);

        void onItemDelete(User user, int position);
    }
}
