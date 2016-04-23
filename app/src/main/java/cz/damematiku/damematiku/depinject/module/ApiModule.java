package cz.damematiku.damematiku.depinject.module;

import com.google.gson.Gson;

import cz.damematiku.damematiku.BuildConfig;
import cz.damematiku.damematiku.data.MathService;
import cz.damematiku.damematiku.depinject.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    @ApplicationScope
    @Provides
    OkHttpClient provideClient(HttpLoggingInterceptor loggingInterceptor, Interceptor headersInterceptor) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headersInterceptor)
                .build();

        return client;
    }

    @ApplicationScope
    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);

        return interceptor;
    }

    @ApplicationScope
    @Provides
    Interceptor provideHeadersInterceptor() {
        // add common headers
        return chain -> {
            Request originalRequest = chain.request();
            return chain.proceed(originalRequest.newBuilder()
//                    .header("Accept-Language", "cs")
                    .build());
        };
    }


    @ApplicationScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    @ApplicationScope
    @Provides
    MathService provideApiService(Retrofit retrofit) {
        return retrofit.create(MathService.class);
    }

//    @ApplicationScope
//    @Provides
//    ApiResponseParser provideApiResponseParser(Gson gson) {
//        return new ApiResponseParser(gson);
//    }

}
