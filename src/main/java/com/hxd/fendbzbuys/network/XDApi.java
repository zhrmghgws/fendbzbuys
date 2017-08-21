package com.hxd.fendbzbuys.network;


import com.hxd.fendbzbuys.base.HttpResult;
import com.hxd.fendbzbuys.domain.AutherBooksList;
import com.hxd.fendbzbuys.domain.BookContentInfo;
import com.hxd.fendbzbuys.domain.BookInfo;
import com.hxd.fendbzbuys.domain.BookMuluInfo;
import com.hxd.fendbzbuys.domain.GenderInfo;
import com.hxd.fendbzbuys.domain.ShuSourceInfo;
import com.hxd.fendbzbuys.domain.StatisticsInfo;
import com.hxd.fendbzbuys.domain.ZuireBangInfo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lichao on 16/9/22.
 */
public interface XDApi {


    @GET("/ranking/gender")
    Flowable<GenderInfo> getGender();

    @GET("/cats/lv2/statistics")
    Flowable<StatisticsInfo> getStatistics();

    @GET("/ranking/{paihangID}")
    Flowable<ZuireBangInfo> getMaleZuireZong(@Path("paihangID") String paihangID);


    @GET("/book/{bookID}")
    Flowable<BookInfo> getBookInfo(@Path("bookID") String bookID);

    @GET("/book/{bookID}/recommend")
    Flowable<AutherBooksList> getBooksTuijian(@Path("bookID") String bookID);

    @GET("/btoc/{bookID}?view=chapters")///btoc/57e932d5bf649ec11272d9b4?view=chapters
    Flowable<BookMuluInfo> getBookmulu(@Path("bookID") String bookID);

    @GET("/book/accurate-search")
    Flowable<AutherBooksList> getAutherTuijian(@Query("author") String auther);

    @GET("/chapter/{url}")
    Flowable<BookContentInfo> getContent(@Path("url") String url);

    @GET("/toc")//?view=summary&book=537461911079ec10040006ee
    Flowable<List<ShuSourceInfo>> getShuSource(@Query("view") String view, @Query("book") String book);


}