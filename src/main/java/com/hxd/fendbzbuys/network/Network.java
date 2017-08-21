package com.hxd.fendbzbuys.network;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.google.gson.Gson;
import com.hxd.fendbzbuys.Common;
import com.hxd.fendbzbuys.Constant;
import com.hxd.fendbzbuys.base.HttpResult;
import com.hxd.fendbzbuys.domain.AutherBooksList;
import com.hxd.fendbzbuys.domain.BookContentInfo;
import com.hxd.fendbzbuys.domain.BookInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.GenderInfo;
import com.hxd.fendbzbuys.domain.ShuSourceInfo;
import com.hxd.fendbzbuys.domain.StatisticsInfo;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by lichao on 16/9/22.
 */
public class Network {
    private Retrofit retrofit;
    private XDApi xdApi;
    BasicParamsInterceptor basicParamsInterceptor;
    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(this::showRetrofitLog);


    private Network(int i) {
        basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .build();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS).addInterceptor(basicParamsInterceptor).addInterceptor(httpLoggingInterceptor).build())
                .baseUrl(i==1?Common.getContentUrl():Common.getCommonUrl())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        xdApi = retrofit.create(XDApi.class);
    }
    private static class SingletonHolder {
        private static final Network INSTANCE = new Network(0);
        private static final Network INSTANCE_NEW = new Network(1);
    }

    public static Network getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public static Network getNewInstance() {
        return SingletonHolder.INSTANCE_NEW;
    }
    private static final class HttpResultFuncC<T> implements FlowableTransformer<T, T> {

        @Override
        public Publisher<T> apply(Flowable<T> upstream) {
            return upstream.map(new Function<T, T>() {
                @Override
                public T apply(@NonNull T tBasetHttpResult) throws Exception {
                    return tBasetHttpResult;
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }

    HttpResultFuncC mBaseHttpresultFunc = new HttpResultFuncC();






    public Flowable<GenderInfo> getGender() {
        Flowable<GenderInfo> homepageBannerInfo = xdApi.getGender();
        return homepageBannerInfo.compose(mBaseHttpresultFunc);
    }
    public Flowable<StatisticsInfo> getStatistics() {
        Flowable<StatisticsInfo> homepageBannerInfo = xdApi.getStatistics();
        return homepageBannerInfo.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleZuireyue(){
        Log.e("getMaleZuirezong:::",Constant.male_zuire.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_zuire.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleZuirezhou(){
        Log.e("getMalemonthRank:::",Constant.male_zuire.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_zuire.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleZuirezong(){
        Log.e("getMalemonthRank:::",Constant.male_zuire._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_zuire._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleQianlizong(){
        Log.e("getMalemonthRank:::",Constant.male_qianli._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_qianli._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleQianlizhou(){
        Log.e("getMalemonthRank:::",Constant.male_qianli.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_qianli.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleQianliyue(){
        Log.e("getMalemonthRank:::",Constant.male_qianli.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_qianli.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleWanjieyue(){
        Log.e("getMalemonthRank:::",Constant.male_wanjie.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_wanjie.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleWanjiezhou(){
        Log.e("getMalemonthRank:::",Constant.male_wanjie.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_wanjie.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getMaleWanjiezong(){
        Log.e("getMalemonthRank:::",Constant.male_wanjie._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.male_wanjie._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleZuirezong(){
        Log.e("getMalemonthRank:::",Constant.female_zuire._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_zuire._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleZuireyue(){
        Log.e("getMalemonthRank:::",Constant.female_zuire.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_zuire.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleZuirezhou(){
        Log.e("getMalemonthRank:::",Constant.female_zuire.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_zuire.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleQianlizhou(){
        Log.e("getMalemonthRank:::",Constant.female_qianli.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_qianli.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleQianliyue(){
        Log.e("getMalemonthRank:::",Constant.female_qianli.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_qianli.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleQianlizong(){
        Log.e("getMalemonthRank:::",Constant.female_qianli._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_qianli._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleWanjiezong(){
        Log.e("getMalemonthRank:::",Constant.female_wanjie._id );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_wanjie._id);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleWanjieyue(){
        Log.e("getMalemonthRank:::",Constant.female_wanjie.totalRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_wanjie.totalRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<ZuireBangInfo> getFemaleWanjiezhou(){
        Log.e("getMalemonthRank:::",Constant.female_wanjie.monthRank );
        Flowable<ZuireBangInfo> malezuirezong=xdApi.getMaleZuireZong(Constant.female_wanjie.monthRank);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<BookInfo> getBookInfo(String bookID){
        Flowable<BookInfo> malezuirezong=xdApi.getBookInfo(bookID);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<AutherBooksList> getAutherBooks(String auther){
        Flowable<AutherBooksList> malezuirezong=xdApi.getAutherTuijian(auther);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<AutherBooksList> getBooksTuijian(String bookID){
        Flowable<AutherBooksList> malezuirezong=xdApi.getBooksTuijian(bookID);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<BookMuluInfo> getBookmulu(String bookSourceID){
        Flowable<BookMuluInfo> malezuirezong=xdApi.getBookmulu(bookSourceID);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<List<ShuSourceInfo>> getShuSources(String bookID){
        Flowable<List<ShuSourceInfo>> malezuirezong=xdApi.getShuSource("summary",bookID);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }
    public Flowable<BookContentInfo> getContent(String url){
        Flowable<BookContentInfo> malezuirezong=xdApi.getContent(url);
        return malezuirezong.compose(mBaseHttpresultFunc);
    }


    private void showRetrofitLog(String message) {
        if (message.startsWith("{")) {
           // LogUtils.json(message);
           // LogUtils.e(message);
        } else {
            //LogUtils.e(message);
        }
    }



}
