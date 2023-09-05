package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.bg;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;

public class BgViewHolder extends RecyclerView.ViewHolder{

    ImageView roundedImageView;

    public BgViewHolder(@NonNull View itemView, int screenWidth, int screenHeight) {
        super(itemView);

        LinearLayout layout = itemView.findViewById(R.id.themeView);

        ViewGroup.LayoutParams params = layout.getLayoutParams();
        params.height = screenHeight;
        params.width = screenWidth;
        layout.setLayoutParams(params);

        roundedImageView = itemView.findViewById(R.id.round_image);


    }
}
