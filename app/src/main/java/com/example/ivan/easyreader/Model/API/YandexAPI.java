package com.example.ivan.easyreader.Model.API;


import com.example.ivan.easyreader.Model.Translation.WordArticle;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by misha on 31.10.2016.
 */
public interface YandexAPI {

    @GET("api/v1/dicservice.json/lookup?key=dict.1.1.20170502T144500Z.1c33ca4209854a7b.6d4342da72cad3ae7733f34b4cba81cddeca2327&lang=en-ru")
    Observable<WordArticle> getTranslation(@Query("text") String text);

}
