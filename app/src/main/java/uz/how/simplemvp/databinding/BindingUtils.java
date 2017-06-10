package uz.how.simplemvp.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import uz.how.simplemvp.dagger.module.GlideApp;

/**
 * Created by abdulatif on 6/10/17.
 */

public class BindingUtils {

    @BindingAdapter(value = "imageUrl")
    public static void setImageUrl(ImageView view, String imageUrl) {
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(view);
    }
}
