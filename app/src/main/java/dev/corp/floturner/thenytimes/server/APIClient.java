package dev.corp.floturner.thenytimes.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String API_KEY = "OAxMKhGEXC2JZtTRztbOxn6UhcdnLnBb";
    private static final String BASE_URL = "https://api.nytimes.com/svc/topstories/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .setDateFormat("dd-MM-yyyy HH:mm:ss")
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
