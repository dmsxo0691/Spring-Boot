package com.opendata.data.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opendata.data.util.Utility;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class BoxOffice {
    static String _url = "http://kobis.or.kr";
    static final String _key = {발급키};

    public MovieListModel.MovieListResponse SearchMovieList(MovieListModel.MovieListRequest movieListRequest) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String urlPath = "/kobisopenapi/webservice/rest/movie/searchMovieList.json";
        String result = "";
        MovieListModel.MovieListResponse response= null;

        params.add("key", _key);
        params.add("repNationCd", movieListRequest.getRepNationCd());
        params.add("movieTypeCd", movieListRequest.getMovieTypeCd());

        result = Utility.GetHttp(_url, urlPath, params);

        response = objectMapper.readValue(result, MovieListModel.MovieListResponse.class);

        return response;
    }
}
