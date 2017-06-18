# ImageViewer
图片浏览器
## 1.代码参考
    //初始化图片集合
    List<String> paths = new ArrayList
    ImageViewer.newInstance()
     //用来设置默认位置
    .setIndex(0)
    //传入所需的pathList 以及加载方式 ImageLoadHelper（必须）
    //paths里的对象可以是任意的，在tramsformPaths完成转换即可
    //showImage方法里可以自定义你想要的加载方式 例子中的是Glide
    .setPaths(paths, new ImageLoadHelper<String>() {

        @Override
        public String tramsformPaths(String path) {
            return path;
        }

        @Override
        public void showImage(int position, String path, ImageView imageView) {
            Glide.with(OCApplication.getContext())
                    .load(path)
                    .into(imageView);
        }

    })
    //传入你所需要的点击反应 ImageClickHelper（非必须） 
    //onLongClick为长按事件
    //onSingleClick为单点事件
    .setClickHelper(new ImageClickHelper() {
        @Override
        public boolean onLongClick(View view, int position) {
            Toast.makeText(MainActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();
            return false;
        }

        @Override
        public void onSingleClick(View view, int position) {
            Toast.makeText(MainActivity.this, "onSingleClick", Toast.LENGTH_SHORT).show();
        }
    })
    .show(getSupportFragmentManager(), "ImageViewer");
        }
    });
