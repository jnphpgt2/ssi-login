package com.jk.service.movie.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.movie.MovieDao;
import com.jk.entity.movie.Movie;
import com.jk.service.movie.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Override
	public void insertMovie(Movie movie) {
		//1、先保存价格，把价格ID取回来
		movieDao.insertMoviePrice(movie);
		//2、再保存视频信息，把价格ID一块保存到视频表中
		movieDao.insertMovie(movie);
	}
	
	@Override
	public List<Movie> selectMovieTypeListJson() {
		return movieDao.selectMovieTypeListJson();
	}
	
	@Override
	public int selectMovieJsonCount(Movie movie) {
		return movieDao.selectMovieJsonCount(movie);
	}
	
	@Override
	public List<Movie> selectMovieJsonList(Movie movie) {
		return movieDao.selectMovieJsonList(movie);
	}
	
	@Override
	public Movie selectMovieInfoByID(int flvID) {
		return movieDao.selectMovieInfoByID(flvID);
	}
	
	@Override
	public Movie selectFileByMD5(Movie movie) {
		return movieDao.selectFileByMD5(movie);
	}
	
	@Override
	public void insertFile(Movie movie) {
		movieDao.insertFile(movie);
	}
	
	@Override
	public List<Movie> selectMovieListByIDs(String movieIDs) {
		List<Movie> mlist = null;
		if (null != movieIDs && !"".equals(movieIDs)) {
			List<Integer> idsList = new ArrayList<Integer>();
			String[] split = movieIDs.split(",");
			for (String string : split) {
				idsList.add(Integer.valueOf(string));
			}
			//调用dao查询
			mlist = movieDao.selectMovieListByIDs(idsList);
		}
		return mlist;
	}
}
