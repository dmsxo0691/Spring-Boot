package com.opendata.data.service;

import com.opendata.data.model.BoxOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BoxOfficeService {
    @Autowired
    private BoxOffice boxOffice;
    public HashMap<String, String> RunSearch(String targetDt) {
        MovieListModel.MovieListRequest movieListRequest = new MovieListModel.MovieListRequest();
        MovieListModel.MovieListResponse movieListResponse = null;

        HashMap<String, String> result = new HashMap<String, String>();

        try{
            //movieListRequest.setRepNationCd("22041011"); //한국
            movieListRequest.setMovieTypeCd("220101"); //장편
            movieListResponse = boxOffice.SearchMovieList(movieListRequest);

            result.put("MovieList", MovieListCard(movieListResponse));
        }catch (Exception e){
        }

        return result;
    }

    public String MovieListCard(MovieListModel.MovieListResponse movieListResponse){
        StringBuffer movieListCard = new StringBuffer();

        for(MovieListModel.Movie movie : movieListResponse.getMovieListResult().getMovieList()){
            // 대표장르 | 영화명
            movieListCard.append(movie.getRepGenreNm()  + " | " + movie.getMovieNm());

            movieListCard.append(System.lineSeparator());
        }

        return movieListCard.toString();
    }

}
