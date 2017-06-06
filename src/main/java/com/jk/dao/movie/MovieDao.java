package com.jk.dao.movie;

import java.util.List;

import com.jk.entity.movie.Movie;

public interface MovieDao {

	void insertMoviePrice(Movie movie);

	void insertMovie(Movie movie);

	List<Movie> selectMovieTypeListJson();

	int selectMovieJsonCount(Movie movie);

	List<Movie> selectMovieJsonList(Movie movie);

	Movie selectMovieInfoByID(int flvID);

	Movie selectFileByMD5(Movie movie);

	void insertFile(Movie movie);

	List<Movie> selectMovieListByIDs(List<Integer> idsList);

}
