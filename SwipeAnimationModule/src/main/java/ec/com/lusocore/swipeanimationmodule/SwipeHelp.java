package ec.com.lusocore.swipeanimationmodule;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class SwipeHelp extends ConstraintLayout {



    private Context context;
    private int option;




    public SwipeHelp(Context context,int option) {
        super(context);
        this.context = context;
        this.option = option;
        initialize(context,null);

    }

    public SwipeHelp(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        initialize(context,attrs);
    }




    @SuppressLint("ResourceType")
    private void initialize(Context context, AttributeSet attrs){

        try {

            inflate(context, R.layout.view_gesture, this);
            ImageView imageView = (ImageView) findViewById(R.id.gesture);
            Drawable drawable = getImageDirection(option);
            if(drawable != null){

                imageView.setImageDrawable(drawable);
                imageView.bringToFront();
                ObjectAnimator animation = getObjectAnimator(option,imageView);
                if(animation != null){
                    animation.setDuration(700);
                    animation.start();
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Drawable getImageDirection(int option){

        try {


            if(option == ShowSwipeHelp.UP){
                return context.getResources().getDrawable(R.drawable.swipe_up);
            }else if(option == ShowSwipeHelp.DOWN){
                return context.getResources().getDrawable(R.drawable.swipe_down);
            }else if(option == ShowSwipeHelp.LEFT){
                return context.getResources().getDrawable(R.drawable.swipe_left);
            }else if(option == ShowSwipeHelp.RIGTH){
                return context.getResources().getDrawable(R.drawable.swipe_right);
            }

            return null;



        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }


    }

    private ObjectAnimator getObjectAnimator(int option, ImageView imageView){

        try {

            ConstraintLayout.LayoutParams newLayoutParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams();


            if(option == ShowSwipeHelp.UP){
                return ObjectAnimator.ofFloat(imageView, "translationY", -400f);
            }else if(option == ShowSwipeHelp.DOWN){
                return ObjectAnimator.ofFloat(imageView, "translationY", 400f);
            }else if(option == ShowSwipeHelp.LEFT){
                newLayoutParams.leftMargin = (int)dbToPixel(200f);
                imageView.setLayoutParams(newLayoutParams);
                return ObjectAnimator.ofFloat(imageView, "translationX", -400f);
            }else if(option == ShowSwipeHelp.RIGTH){
                return ObjectAnimator.ofFloat(imageView, "translationX", 400f);
            }

            return null;



        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }


    }


    float dbToPixel(float value){

        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                this.context.getResources().getDisplayMetrics()
        );


    }


}
