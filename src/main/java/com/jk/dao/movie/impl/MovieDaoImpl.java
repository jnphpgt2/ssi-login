package com.jk.dao.movie.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.dao.movie.MovieDao;
import com.jk.entity.movie.Movie;

@Repository
public class MovieDaoImpl extends SqlMapClientDaoSupport implements MovieDao {

	@Override
	public void insertMoviePrice(Movie movie) {
		this.getSqlMapClientTemplate().insert("movie.insertMoviePrice", movie);
	}
	
	@Override
	public void insertMovie(Movie movie) {
		this.getSqlMapClientTemplate().insert("movie.insertMovie", movie);
	}
	
	@Override
	public List<Movie> selectMovieTypeListJson() {
		return this.getSqlMapClientTemplate().queryForList("movie.selectMovieTypeListJson");
	}
	
	@Override
	public int selectMovieJsonCount(Movie movie) {
		return (int) this.getSqlMapClientTemplate().queryForObject("movie.selectMovieJsonCount", movie);
	}
	
	@Override
	public List<Movie> selectMovieJsonList(Movie movie) {
		return this.getSqlMapClientTemplate().queryForList("movie.selectMovieJsonList", movie);
	}
	
	@Override
	public Movie selectMovieInfoByID(int flvID) {
		return (Movie) this.getSqlMapClientTemplate().queryForObject("movie.selectMovieInfoByID", flvID);
	}
	
	@Override
	public Movie selectFileByMD5(Movie movie) {
		return (Movie) this.getSqlMapClientTemplate().queryForObject("movie.selectFileByMD5", movie);
	}
	
	@Override
	public void insertFile(Movie movie) {
		this.getSqlMapClientTemplate().insert("movie.insertFile", movie);
	}
	
	@Override
	public List<Movie> selectMovieListByIDs(List<Integer> idsList) {
		return this.getSqlMapClientTemplate().queryForList("movie.selectMovieListByIDs", idsList);
	}
}
