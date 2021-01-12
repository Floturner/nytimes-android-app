package dev.corp.floturner.thenytimes.server;

import dev.corp.floturner.thenytimes.models.ArticlesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("{section}.json")
    Call<ArticlesResponse> getStoriesBySection(@Path("section") String section, @Query("api-key") String apiKey);
}