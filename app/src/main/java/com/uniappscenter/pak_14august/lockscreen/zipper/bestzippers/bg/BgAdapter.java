package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.bg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;


public class BgAdapter extends RecyclerView.Adapter<BgViewHolder> {

    private final int imageWidth;
    private final int imageHeight;
    private final ImageClickListener clickListener;

    private final Integer[] frameWallPaper;


    public BgAdapter(Integer[] frameWallPaper1, int width, int height, ImageClickListener listener) {
        this.frameWallPaper = frameWallPaper1;
        this.imageWidth = width;
        this.imageHeight = height;
        this.clickListener = listener;

    }

    @NonNull
    @Override
    public BgViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bg_adapter, viewGroup, false);
        return new BgViewHolder(view, imageWidth, imageHeight);
    }

    @Override
    public void onBindViewHolder(@NonNull BgViewHolder viewHolder, int i) {

        viewHolder.roundedImageView.setBackgroundResource(frameWallPaper[i]);
        viewHolder.itemView.setTag(i);
        viewHolder.itemView.setOnClickListener(v -> {
            int position = (Integer) v.getTag();
            clickListener.clickOnImage(position);
        });
    }

    @Override
    public int getItemCount() {
        return frameWallPaper.length;
    }
}