package WS;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import Model.DetailMovieModel;
import Model.TOMovieLIst;

/**
 * Created by eltonmelo on 4/21/16.
 */


@Rest(rootUrl = "http://api.themoviedb.org/3", converters = {GsonHttpMessageConverter.class})
//@Rest("http://www.bookmarks.com")
public interface RestClient extends RestClientErrorHandling {

    @Get("/discover/movie?api_key=a573db5e752a82b043ab78deb9ab2a2b&sort_by=popularity.desc")
    TOMovieLIst getTOMovieLIst();

    @Get("/discover/movie?api_key=a573db5e752a82b043ab78deb9ab2a2b&sort_by=popularity.desc&page={page}")
    TOMovieLIst getTOMoviePage(int page);

    @Get("/movie/{idMovie}?api_key=a573db5e752a82b043ab78deb9ab2a2b")
    DetailMovieModel getDetailMovie(int idMovie);
}
