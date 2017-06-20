package cn.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import cn.imageviewer.helper.ImageLoadHelper;
import cn.imageviewer.helper.OnImageLongClick;
import cn.imageviewer.helper.OnImageSingleClick;
import cn.imageviewer.view.ImageViewer;
import uk.co.senab.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {

    List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        paths = new ArrayList<>();
        paths.add("http://i2.sinaimg.cn/travel/2015/0715/U10172P704DT20150715110013.png");
        paths.add("http://img02.tooopen.com/images/20150628/tooopen_sy_132149827682.jpg");
        paths.add("http://img02.tooopen.com/images/20160330/tooopen_sy_157749743148.jpg");
        paths.add("http://pic.qiantucdn.com/58pic/17/99/58/34R58PICpKy_1024.jpg");
        paths.add("http://tupian.enterdesk.com/2013/mxy/12/07/3/4.jpg");
        paths.add("http://pic.58pic.com/58pic/13/40/15/62958PICTq7_1024.jpg");
        paths.add("http://pic.qiantucdn.com/58pic/11/69/82/58PIC2Q58PICsY9.jpg");
        paths.add("http://img05.tooopen.com/images/20150630/tooopen_sy_132344141259.jpg");
        paths.add("http://img3.3lian.com/2013/v11/41/d/81.jpg");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ImageViewer imageViewer = ImageViewer.newInstance();
                imageViewer.setIndex(0)
                        .setOnImageSingleClick(new OnImageSingleClick() {
                            @Override
                            public void onImageSingleClick(int position, String path, PhotoView photoView) {
                                Toast.makeText(MainActivity.this, "onSingleClick" + position, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setOnImageLongClick(new OnImageLongClick() {
                            @Override
                            public boolean onImageLongClick(int position, String path, PhotoView photoView) {
                                Toast.makeText(MainActivity.this, "onLongClick" + position, Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        })
                        .setPaths(paths, new ImageLoadHelper<String>() {
                            @Override
                            public String tramsformPaths(String path) {
                                return path;
                            }

                            @Override
                            public void showImage(final int position, String path, PhotoView photoView) {
                                imageViewer.showProgress(position);
                                Glide.with(OCApplication.getContext())
                                        .load(path)
                                        .listener(new RequestListener<String, GlideDrawable>() {
                                            @Override
                                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                                imageViewer.hideProgress(position);
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                                imageViewer.hideProgress(position);
                                                return false;
                                            }
                                        })
                                        .into(photoView);
                            }
                        })
                        .show(getSupportFragmentManager(), "ImageViewer");
            }
        });
    }
}
