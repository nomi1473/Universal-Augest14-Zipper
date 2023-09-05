package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperstyle;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;


public class ZipperStyleViewHolder extends RecyclerView.ViewHolder {

    ImageView styleImageView;

    public ZipperStyleViewHolder(@NonNull View itemView) {
        super(itemView);
        styleImageView = itemView.findViewById(R.id.styleImageView);
    }
}
