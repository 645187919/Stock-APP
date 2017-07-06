package Fragments_News;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by magic on 2017/3/31.
 */
public class MyImageView extends ImageView {

    public MyImageView(Context context) {
        super(context);
    }
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Handler handler = new Handler(){

        public void handleMessage(Message msg) {

            Bitmap bitmap = (Bitmap) msg.obj;

            MyImageView.this.setImageBitmap(bitmap);
        };
    };

    public void setImageUrl(final String url_str){


        new Thread(new Runnable() {

            @Override
            public void run() {
                try{
                    //鑾峰彇url瀵瑰簲鐨勫浘鐗囪祫婧愶紝bitmap
                    URL url = new URL(url_str);

                    HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();

                    openConnection.setRequestMethod("GET");
                    openConnection.setConnectTimeout(10*1000);

                    int code = openConnection.getResponseCode();
                    if(code == 200){
                        InputStream inputStream = openConnection.getInputStream();


                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.obj = bitmap;
                        handler.sendMessage(msg);


                    }


                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}

