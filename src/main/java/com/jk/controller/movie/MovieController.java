package com.jk.controller.movie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jk.entity.movie.Movie;
import com.jk.service.movie.MovieService;

import common.util.Constant;
import common.util.FTPUtil;
import common.util.FileUtil;

@Controller
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@RequestMapping("toMovieListPage")
	public String toMovieListPage() {
		return "movie/movie_list";
	}
	
	@RequestMapping("toAddMoviePage")
	public String toAddMoviePage() {
		return "movie/add_movie";
	}
	
//	@RequestMapping("uploadMovieByAsync")
//	@ResponseBody
//	public Movie uploadMovieByAsync(MultipartFile movieFile, HttpServletRequest request) {
//		Movie movie = null;
//		//判断文件是否为空
//		if (null != movieFile) {
//			movie = new Movie();
//			//设置文件大小
//			movie.setMovieSize(movieFile.getSize());
////			//上传文件
////			String uploadFile = FileUtil.uploadFile(movieFile, request);
////			if (null != uploadFile) {
////				//设置文件的保存路径
////				movie.setMoviePath(uploadFile);
////				//设置文件后缀名
////				movie.setMovieSuffix(uploadFile.substring(uploadFile.lastIndexOf(".")));
////			}
//			
//			
//			//ftp保存的方式
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//			String format = "movies/" + sdf.format(new Date()) + "/";
//			
//			//获取原始名称
//			String originalFilename = movieFile.getOriginalFilename();
//			//从原始名称中截取后缀名
//			String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
//			//生成新的文件名
//			String fileName = UUID.randomUUID().toString() + fileSuffix;
//			try {
//				//调用保存
//				boolean boo = FTPUtil.uploadFile(movieFile.getInputStream(), fileName, format);
//				if (boo) {
//					//设置文件的保存路径
//					movie.setMoviePath("/" + format + fileName);
//					//设置文件后缀名
//					movie.setMovieSuffix(fileSuffix);
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return movie;
//	}
	
	@RequestMapping("uploadMovieByAsync")
	@ResponseBody
	public Movie uploadMovieByAsync(MultipartFile movieFile, HttpServletRequest request) {
		long btime = System.currentTimeMillis();
		Movie movie = null;
		//判断文件是否为空
		if (null != movieFile) {
			movie = new Movie();
			//判断文件指纹
			try {
				String md5 = FileUtil.getMD5(movieFile.getInputStream(), "md5");
				movie.setFileMD5(md5);
				//查询数据库，验证文件是否存在
				Movie m = movieService.selectFileByMD5(movie);
				if (null != m && null != m.getFileMD5() && !"".equals(m.getFileMD5())) {
					//文件存在
					movie = m;
				} else {
					//文件不存在
					//设置文件大小
					movie.setMovieSize(movieFile.getSize());
					//ftp保存的方式
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					String format = "movies/" + sdf.format(new Date()) + "/";
					//获取原始名称
					String originalFilename = movieFile.getOriginalFilename();
					//从原始名称中截取后缀名
					String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
					//生成新的文件名
					String fileName = UUID.randomUUID().toString() + fileSuffix;
					//调用保存
					boolean boo = FTPUtil.uploadFile(movieFile.getInputStream(), fileName, format);
					if (boo) {
						//设置文件的保存路径
						movie.setMoviePath("/" + format + fileName);
						//设置文件后缀名
						movie.setMovieSuffix(fileSuffix);
					}
					//把文件信息保存到数据库
					movieService.insertFile(movie);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		long etime = System.currentTimeMillis();
		System.out.println(etime - btime);
		return movie;
	}
	
	@RequestMapping("insertMovie")
	@ResponseBody
	public String insertMovie(Movie movie) {
		movieService.insertMovie(movie);
		return "{}";
	}
	
	@RequestMapping("selectMovieTypeListJson")
	@ResponseBody
	public List<Movie> selectMovieTypeListJson() {
		return movieService.selectMovieTypeListJson();
	}
	
	@RequestMapping("selectMovieJsonList")
	@ResponseBody
	public Map<String, Object> selectMovieJsonList(Movie movie, int page, int rows) {
		//查询总条数
		int totalCount = movieService.selectMovieJsonCount(movie);
		if (0 == page) {
			page = 1;
		}
		if (0 == rows) {
			rows = 3;
		}
		movie.setTotalCount(totalCount);
		movie.setPageIndex(page);
		movie.setPageSize(rows);
		movie.countInfo();
		//查询列表
		List<Movie> list = movieService.selectMovieJsonList(movie);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalCount);
		map.put("rows", list);
		return map;
	}
	
	@RequestMapping("toMoviePlayPage")
	public String toMoviePlayPage(Movie movie, Model model) {
		model.addAttribute("movie", movie);
		return "movie/movie_play";
	}
	
	@RequestMapping("moviePlay")
	public void moviePlay(int FlvID, HttpServletRequest request, HttpServletResponse response) {
		//根据ID查出这个视频的信息
		Movie movie = movieService.selectMovieInfoByID(FlvID);
		if (null != movie) {
			movie.setMoviePath(Constant.FTP_FILE_ADDR + movie.getMoviePath());
		}
		request.setAttribute("movie", movie);
		try {
			//把本次请求交给movie_play_set.jsp页面处理
			request.getRequestDispatcher("WEB-INF/view/movie/movie_play_set.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("downLoadMovieByMovieID")
	public void downLoadMovieByMovieID(Movie movie, HttpServletResponse response) {
		//根据movieID查询电影详细信息
		Movie m = movieService.selectMovieInfoByID(movie.getMovieID());
		if (null != m && null != m.getMoviePath() && !"".equals(m.getMoviePath())) {
			//拼接新的文件名
			String movieName = m.getMovieTitle()
					+ m.getMoviePath().substring(m.getMoviePath().lastIndexOf("."));
			try {
				//设置编码集
				response.setCharacterEncoding("utf-8");
				//设置两个header信息
				response.addHeader("Content-Disposition", "attachment;filename=" + new String(movieName.getBytes("gbk"), "iso-8859-1"));
				response.addHeader("Content-Length", "" + m.getMovieSize());
				//获取outputstream
				ServletOutputStream os = response.getOutputStream();
				FTPUtil.downLoadFile(m.getMoviePath(), os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("downLoadMoreMovie")
	public void downLoadMoreMovie(String movieIDs, HttpServletRequest request, HttpServletResponse response) {
		//查询电影集合
		List<Movie> mlist = movieService.selectMovieListByIDs(movieIDs);
		if (null != mlist && 0 < mlist.size()) {
			//临时路径
			String tempPath = request.getSession().getServletContext().getRealPath("/temp/") + "/" + UUID.randomUUID().toString() + "/";
			String tempZipName = UUID.randomUUID().toString() + ".zip";
			List<String> pathList = new ArrayList<String>();
			List<String> fileNameList = new ArrayList<String>();
			for (Movie m : mlist) {
				pathList.add(m.getMoviePath());
				fileNameList.add(m.getMovieTitle());
			}
			FTPUtil.downLoadZipFile(tempPath, tempZipName, pathList, fileNameList, response);
		}
	}
}
