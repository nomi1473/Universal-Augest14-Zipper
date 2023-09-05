package com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperstyle;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.media.Media;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.R;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.utils.ConstantData;
import com.uniappscenter.pak_14august.lockscreen.zipper.bestzippers.zipperview.Data.UserDataAdapter;


public class ZipperStyleAdapter extends RecyclerView.Adapter<ZipperStyleViewHolder> {

    private Context context;
    private int[] shirtArr;
    private String categoryNam;

    public ZipperStyleAdapter(Context context, int[] shirtArr, String categoryNam) {
        this.context = context;
        this.shirtArr = shirtArr;
        this.categoryNam = categoryNam;
    }

    @NonNull
    @Override
    public ZipperStyleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.zipper_style_adapter, viewGroup, false);
        return new ZipperStyleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZipperStyleViewHolder logosViewHolder, int i) {

        logosViewHolder.styleImageView.setImageResource(shirtArr[i]);

        logosViewHolder.itemView.setTag(i);
        logosViewHolder.itemView.setOnClickListener(v -> {
            int pos = (int) v.getTag();
            if (categoryNam.equalsIgnoreCase("ZipperStyle")) {
                Toast.makeText(context, "Zipper Style " + pos + " Selected", Toast.LENGTH_SHORT).show();
                UserDataAdapter.saveZipperStyle(ConstantData.ZIPPER_STYLE, pos, context);
                Media.Clear();
                Activity activity= (Activity) context;
                activity.finish();
            } else {
                Toast.makeText(context, "Hook Style " + pos + " Selected", Toast.LENGTH_SHORT).show();
                UserDataAdapter.saveHookStyle(ConstantData.HOOK_STYLE, pos, context);
                Media.Clear();
                Activity activity= (Activity) context;
                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return shirtArr.length;
    }
}
