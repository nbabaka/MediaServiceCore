package com.liskovsoft.youtubeapi.auth;

import com.liskovsoft.youtubeapi.auth.models.RefreshToken;
import com.liskovsoft.youtubeapi.auth.models.AccessToken;
import com.liskovsoft.youtubeapi.auth.models.UserCode;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthManager {
    @FormUrlEncoded
    @POST("https://www.youtube.com/o/oauth2/device/code")
    Call<UserCode> getUserCode(@Field("client_id") String clientId,
                               @Field("scope") String scope);

    @FormUrlEncoded
    @POST("https://www.youtube.com/o/oauth2/token")
    Call<RefreshToken> getRefreshToken(@Field("code") String deviceCode,
                                       @Field("client_id") String clientId,
                                       @Field("client_secret") String clientSecret,
                                       @Field("grant_type") String grantType);

    @FormUrlEncoded
    @POST("https://www.youtube.com/o/oauth2/token")
    Call<AccessToken> getAccessToken(@Field("refresh_token") String refreshToken,
                                     @Field("client_id") String clientId,
                                     @Field("client_secret") String clientSecret,
                                     @Field("grant_type") String grantType);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("https://www.youtube.com/o/oauth2/token")
    Call<AccessToken> getAccessToken(@Body RequestBody rawBody);
}
