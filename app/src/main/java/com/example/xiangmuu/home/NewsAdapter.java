package com.example.xiangmuu.home;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

import com.bumptech.glide.Glide;
import com.example.xiangmuu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private NewsBean newsBean;

    private String tabID;
    private final int ITEM_TYPE_BANNER = 0;
    private final int ITEM_TYPE_MARQUEE = 1;
    private final int ITEM_TYPE_SMLLIMAGE = 2;
    private final int ITEM_TYPE_BIGIMAGE = 3;
    private final int ITEM_TYPE_BIGVIDEO = 4;
    private Timer timer;
    private TimerTask timerTask;



    private BannerHolder bannerHolder;


    public NewsAdapter(Activity context, NewsBean newsBean) {
        this.context = context;
        this.newsBean = newsBean;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       if (viewType ==  ITEM_TYPE_BANNER) {//轮播图
            View bannerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_banner,parent,false);
            return new BannerHolder(bannerView);
        } else if (viewType == ITEM_TYPE_SMLLIMAGE) {//小图
           View smallImageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_smallimage,parent,false);
           return new SmallImageHolder(smallImageView);
        }else if (viewType == ITEM_TYPE_BIGIMAGE) {//大图
           View bigImageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_bigimage,parent,false);
           return new BigImageHolder(bigImageView);
       }else if (viewType == ITEM_TYPE_BIGVIDEO) {//视频
           View videoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_bigvideo,parent,false);
           return new BigVideoHolder(videoView);
        }else if(viewType == ITEM_TYPE_MARQUEE){
           View videoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_item_banner,parent,false);
           return new MarqueeHolder(videoView);
       }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.e("TAG","viewHolder执行");

        if(holder instanceof BannerHolder){//轮播图

            BannerHolder bannerHolder = (BannerHolder) holder;
            this.bannerHolder = bannerHolder;
            initBanner(newsBean,bannerHolder);

        }else if(holder instanceof SmallImageHolder){//小图

            SmallImageHolder smallImageHolder = (SmallImageHolder) holder;

            Log.e("TAG","当前小图布局imageURL="+newsBean.getData().getArticle_list().get(position).getImage_url());

            Glide.with(context).load(newsBean.getData().getArticle_list().get(position).getImage_url()).into(smallImageHolder.small_image);
            smallImageHolder.smll_image_title.setText(newsBean.getData().getArticle_list().get(position).getTheme());
            smallImageHolder.smll_image_type.setText(newsBean.getData().getArticle_list().get(position).getColumn_name());
        }else if(holder instanceof BigImageHolder){//大图
            BigImageHolder bigImageHolder = (BigImageHolder) holder;
            Log.e("TAG","当前大图布局imageURL="+newsBean.getData().getArticle_list().get(position).getImage_url());
            Glide.with(context).load(newsBean.getData().getArticle_list().get(position).getImage_url()).into(bigImageHolder.big_image);
            bigImageHolder.big_image_title.setText(newsBean.getData().getArticle_list().get(position).getTheme());
            bigImageHolder.big_image_type.setText(newsBean.getData().getArticle_list().get(position).getColumn_name());

        }else if(holder instanceof BigVideoHolder){//视频
            BigVideoHolder bigVideoHolder = (BigVideoHolder) holder;
            bigVideoHolder.jzvdStd.setUp(newsBean.getData().getArticle_list().get(position).getVideo_url(),
                    newsBean.getData().getArticle_list().get(position).getTheme(), Jzvd.SCREEN_WINDOW_NORMAL);
            bigVideoHolder.jzvdStd.positionInList = position;
            Glide.with(context)
                    .load(newsBean.getData().getArticle_list().get(position).getImage_url())
                    .into(bigVideoHolder.jzvdStd.thumbImageView);
        }else if(holder instanceof MarqueeHolder){//跑马灯

        }

    }


    @Override
    public int getItemViewType(int position) {

        if(position ==0){
            return ITEM_TYPE_BANNER;
        }
        if (null !=newsBean.getData().getFlash_list() && newsBean.getData().getFlash_list().size()>0 && position==1){
            return ITEM_TYPE_MARQUEE;
        }
        if (newsBean.getData().getArticle_list().get(position).getView_type() == 4){
            return ITEM_TYPE_BIGVIDEO;
        }else if (newsBean.getData().getArticle_list().get(position).getView_type() == 2){
            return ITEM_TYPE_BIGIMAGE;
        }else {
            return ITEM_TYPE_SMLLIMAGE;
        }
    }

    @Override
    public int getItemCount() {

        return (null !=newsBean.getData().getFlash_list() && newsBean.getData().getFlash_list().size()>0)?newsBean.getData().getArticle_list().size()+2:newsBean.getData().getArticle_list().size()+1;
    }
    /**
     * 轮播图
     */
    class BannerHolder extends RecyclerView.ViewHolder {

        private ViewPager banner_viewPager;
        private Banner_Indicator banner_indicator;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            banner_viewPager = itemView.findViewById(R.id.banner_viewpage);
            banner_indicator =itemView.findViewById(R.id.banner_indicator);
        }
    }

    /**
     * 跑马灯
     */
    class MarqueeHolder extends RecyclerView.ViewHolder{

        public MarqueeHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    /**
     * 小图
     */
    class SmallImageHolder extends RecyclerView.ViewHolder{
        private ImageView small_image;
        private TextView smll_image_title;
        private TextView smll_image_type;
        public SmallImageHolder(@NonNull View itemView) {
            super(itemView);
            small_image = itemView.findViewById(R.id.small_image);
            smll_image_title = itemView.findViewById(R.id.smll_image_title);
            smll_image_type = itemView.findViewById(R.id.small_image_type);
        }
    }

    /**
     * 大图
     */
    class BigImageHolder extends RecyclerView.ViewHolder{
        private ImageView big_image;
        private TextView big_image_title;
        private TextView big_image_type;
        public BigImageHolder(@NonNull View itemView) {
            super(itemView);
            big_image = itemView.findViewById(R.id.big_image);
            big_image_title = itemView.findViewById(R.id.big_image_title);
            big_image_type = itemView.findViewById(R.id.big_image_title);
        }
    }


    /**
     * 视频
     */
    class BigVideoHolder extends RecyclerView.ViewHolder{
        private JzvdStd jzvdStd;
        private TextView video_title;
        private TextView video_type;
        public BigVideoHolder(@NonNull View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.big_video_player);
            video_title = itemView.findViewById(R.id.big_video_title);
            video_type = itemView.findViewById(R.id.big_video_type);
        }
    }


    private int viewpage_Current_Pos = 0;

    int current_banner_item;
    private List<View> banner_views = new ArrayList<>();

    private void initBanner(final NewsBean newsBean, final BannerHolder holder){

        for (int i = 0; i <newsBean.getData().getBanner_list().size(); i++) {
            current_banner_item = i;
            View ban_view = LayoutInflater.from(context).inflate(R.layout.news_banner_item,null,false);
            TextView bannerContent = ban_view.findViewById(R.id.banner_content);
            ImageView bannerImage =  ban_view.findViewById(R.id.benner_image);
            bannerContent.setText(newsBean.getData().getBanner_list().get(i).getDescription());
            Glide.with(context).load(newsBean.getData().getBanner_list().get(i).getImage_url()).into(bannerImage);
            banner_views.add(ban_view);
            ban_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "点击了"+current_banner_item+"个view", Toast.LENGTH_SHORT).show();
                }
            });
        };

        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(banner_views);
        holder.banner_viewPager.setAdapter(bannerAdapter);

        holder.banner_indicator.setBannerImageSize(newsBean.getData().getBanner_list().size());

        holder.banner_indicator.setCurrentBannerItem(0);

        holder.banner_viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                current_banner_item = position;
                holder.banner_indicator.setCurrentBannerItem(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initTimer();
    }

    /**
     * 开启轮播图Timer
     */
    private void initTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                viewpage_Current_Pos+=1;
                Log.e("TAG","当前位置"+viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bannerHolder.banner_viewPager.setCurrentItem(viewpage_Current_Pos%(newsBean.getData().getBanner_list().size()));
                    }
                });
            }
        };
        timer.schedule(timerTask,2000,2000);
    }



    public  void isCurrentVisibleToUser(boolean isVisible){
        if (isVisible){
            initTimer();
        }else {
            timer.cancel();
        }

    }

}
