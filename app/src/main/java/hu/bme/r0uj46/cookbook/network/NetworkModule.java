package hu.bme.r0uj46.cookbook.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import hu.bme.r0uj46.cookbook.network.api.RecipeApi;
import hu.bme.r0uj46.cookbook.network.utils.GsonHelper;
import okhttp3.OkHttpClient;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVICE_ENDPOINT).client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson())).build();
    }

    @Provides
    @Singleton
    public RecipeApi provideRecipeApi(Retrofit retrofit) {
        return retrofit.create(RecipeApi.class);
    }
}
