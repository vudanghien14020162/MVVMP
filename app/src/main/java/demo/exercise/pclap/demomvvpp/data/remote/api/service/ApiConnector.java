package demo.exercise.pclap.demomvvpp.data.remote.api.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import demo.exercise.pclap.demomvvpp.data.model.ItemSongRepose;
import demo.exercise.pclap.demomvvpp.data.model.ItemSongSearch;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PC Lap on 8/25/2017.
 */
public class ApiConnector {

    private String BASE_URL;
    private static ApiConnector mApiConnector = new ApiConnector();
    private APIServer apiServer;

    private ApiConnector() {
//        Nó sẽ in các yêu cầu / phản hồi HTTP thông qua LogCat.
//        Bạn cũng có thể tận dụng dự án Stetho của Facebook để sử dụng Chrome để kiểm tra tất cả lưu lượng mạng.
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
        Retrofit.Builder retrofit = new Retrofit.Builder();

        retrofit.baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);

        apiServer = retrofit.build().create(APIServer.class);

    }

    static ApiConnector getApiConnector() {
        return mApiConnector;
    }


    //tuong tac voi server
    public Observable<List<ItemSongRepose>> getMusic(final String name){
        final String nameNew = name.replace(" ", "+");

        return Observable.create(new ObservableOnSubscribe<List<ItemSongRepose>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<ItemSongRepose>> e) throws Exception {
                List<ItemSongRepose> responses = new ArrayList<ItemSongRepose>();

                //thu vien joup de boc tach html
                Document document = Jsoup.connect("http://mp3.zing.vn/tim-kiem/bai-hat.html?q=" + nameNew).get();

                Element element = document.select("div.wrap-content").first();

                Elements elements = element.select("div.item-song");
                for (Element elSong : elements) {
                    String dataCode = elSong.attr("data-code").toString();
                    String link = elSong.select("h3").select("a").first().attr("href").toString();
                    String title = elSong.select("h3").select("a").first().attr("title").toString();
                    responses.add(new ItemSongRepose(dataCode, title));
                }
//                mỗi item được đưa ra bởi Observable, nó sẽ được bắt bởi phương thức Subcriber.onNext().
                e.onNext(responses);
            }
//            mỗi item được đưa ra bởi Observable, nó sẽ được bắt bởi phương thức Subcriber.onNext().
        });
    }

    //lay ra list danh sach item search
    public Observable<List<ItemSongSearch>> getListMusic(String nameMusic, String webSite, String code){
        return apiServer.queryMusic(nameMusic, webSite, code);
    }
}
