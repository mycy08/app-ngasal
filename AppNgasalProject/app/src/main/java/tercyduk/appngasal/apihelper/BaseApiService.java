package tercyduk.appngasal.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by User on 11/30/2017.
 */

public interface BaseApiService {
    @FormUrlEncoded
    @POST("/user")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password);
    @FormUrlEncoded
    @POST("/user")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);



}
