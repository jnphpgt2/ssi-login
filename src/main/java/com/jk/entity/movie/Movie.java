package com.jk.entity.movie;

import java.io.Serializable;

import common.util.Page;

public class Movie extends Page implements Serializable {

	private int movieID;
	
	private String movieTitle;
	
	private String movieContent;

	private String movieCreateDate;
	
	private int fileID;
	
	private String fileMD5;
	
	private String moviePath;
	
	private String movieSuffix;
	
	private long movieSize;
	
	private int movieTypeID;
	
	private String movieTypeName;
	
	private int priceID;
	
	private float moviePrice;
	
	private int userID;
	
	private String userName;

	public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}

	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public String getMovieCreateDate() {
		return movieCreateDate;
	}

	public void setMovieCreateDate(String movieCreateDate) {
		this.movieCreateDate = movieCreateDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMovieTypeName() {
		return movieTypeName;
	}

	public void setMovieTypeName(String movieTypeName) {
		this.movieTypeName = movieTypeName;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getPriceID() {
		return priceID;
	}

	public void setPriceID(int priceID) {
		this.priceID = priceID;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieContent() {
		return movieContent;
	}

	public void setMovieContent(String movieContent) {
		this.movieContent = movieContent;
	}

	public int getMovieTypeID() {
		return movieTypeID;
	}

	public void setMovieTypeID(int movieTypeID) {
		this.movieTypeID = movieTypeID;
	}

	public float getMoviePrice() {
		return moviePrice;
	}

	public void setMoviePrice(float moviePrice) {
		this.moviePrice = moviePrice;
	}

	public String getMoviePath() {
		return moviePath;
	}

	public void setMoviePath(String moviePath) {
		this.moviePath = moviePath;
	}

	public String getMovieSuffix() {
		return movieSuffix;
	}

	public void setMovieSuffix(String movieSuffix) {
		this.movieSuffix = movieSuffix;
	}

	public long getMovieSize() {
		return movieSize;
	}

	public void setMovieSize(long movieSize) {
		this.movieSize = movieSize;
	}
}
