package com.youtubemimic.utils;

import com.google.gson.Gson;
import com.youtubemimic.bean.PlaylistEntity;
import com.youtubemimic.bean.PlaylistVideo;
import com.youtubemimic.service.ObjectifyWebListener;
import com.youtubemimic.utils.common.YouTubeMimicUtils;
import static com.youtubemimic.constant.YoutubeMimicConstant.SUCCESS;
import static com.youtubemimic.constant.YoutubeMimicConstant.ERROR;
import static com.youtubemimic.constant.YoutubeMimicConstant.ApiStatusCode.OK;
import static com.youtubemimic.constant.YoutubeMimicConstant.ApiStatusCode.FAILED;
import static com.youtubemimic.constant.YoutubeMimicConstant.ApiError.success;
import static com.youtubemimic.constant.YoutubeMimicConstant.ApiError.error;
import static com.youtubemimic.constant.YoutubeMimicConstant.Basic.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PlaylistUtils {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private HashMap<String, Object> detailsMap;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Date date = new Date();

	public PlaylistUtils(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public static PlaylistUtils getInstance(HttpServletRequest request, HttpServletResponse response) {
		return new PlaylistUtils(request, response);
	}

	private String getSession() {
		HttpSession session = this.request.getSession(false);
		return (String) session.getAttribute("userId");
	}

	public void processGet() throws IOException {
		String playlistTitle = this.request.getParameter("playlistName");
		try {
			YouTubeMimicUtils.writeResponse(this.response,
					(!playlistTitle.isEmpty() && playlistTitle != "") ? getDbEntity(playlistTitle) : getDbEntitybyID(),
					200);
			return;
		} catch (Exception var4) {
			errorResponse(failed_get);
			throw var4;
		}
	}

	public void processPost() throws IOException {
		try {
			PlaylistEntity entity = getInputData();
			entity.setCreateAt(formatter.format(date));
			entity.setUserId(Long.parseLong(this.getSession()));
			saveData(entity);
			successResponse(successfully_data_create);
			return;
		} catch (Exception var6) {
			errorResponse(failed_create_data);
			throw var6;
		}
	}

	public void processPut() throws IOException {
		try {
			PlaylistEntity entity = getInputData();
			PlaylistEntity dbEntity = getDbEntity(entity.getPlaylistName());
			HashMap<String, PlaylistVideo> videoupdate;
			if (dbEntity == null) {
				dbEntity = entity;
				dbEntity.setCreateAt(formatter.format(date));
				videoupdate = new HashMap<String, PlaylistVideo>();
			} else {
				videoupdate = (HashMap<String, PlaylistVideo>) dbEntity.getVideos();
			}
			videoupdate.put(entity.getVideoId(), new PlaylistVideo(entity.getVideoId(), formatter.format(date)));
			dbEntity.setVideos(videoupdate);
			saveData(dbEntity);
			successResponse(successfully_data_update);
			return;
		} catch (Exception e) {
			errorResponse(failed_data_update);
			throw e;
		}
	}

	public void processDelete() throws IOException {
		PlaylistEntity entity = getInputData();
		try {
			ObjectifyWebListener.ofy().delete().type(PlaylistEntity.class)
					.id(getDbEntity(entity.getPlaylistName()).getPlaylistId()).now();
			successResponse(successfully_data_delete);
			return;
		} catch (Exception e) {
			errorResponse(failed_data_delete);
			throw e;
		}
	}

	public void processVideoDelete() {
		PlaylistEntity entity = getInputData();

		try {
			String userId = this.getSession();
			entity.setUserId(Long.parseLong(userId));
			PlaylistEntity dbEntity = getDbEntity(entity.getPlaylistName());
			HashMap<String, PlaylistVideo> videoDelete;
			if (entity.getVideoId() != null) {
				videoDelete = (HashMap<String, PlaylistVideo>) dbEntity.getVideos();
				videoDelete.remove(entity.getVideoId());
				dbEntity.setVideos(videoDelete);
			} else {
				dbEntity.setVideos(null);
			}

			saveData(dbEntity);
			successResponse(successfully_data_delete);
			return;
		} catch (Exception var5) {
			errorResponse(failed_data_delete);
			throw var5;
		}
	}

	private PlaylistEntity getDbEntity(String playlistName) {
		return ObjectifyWebListener.ofy().load().type(PlaylistEntity.class).filter("playlistName", playlistName)
				.filter("userId", Long.parseLong(this.getSession())).first().now();
	}

	private List<PlaylistEntity> getDbEntitybyID() {
		return ObjectifyWebListener.ofy().load().type(PlaylistEntity.class)
				.filter("userId", Long.parseLong(this.getSession())).list();
	}

	private PlaylistEntity getInputData() {
		return (new Gson()).fromJson(YouTubeMimicUtils.getRequestBody(this.request), PlaylistEntity.class);
	}

	private void saveData(PlaylistEntity entity) {
		ObjectifyWebListener.ofy().save().entities(entity).now();
	}

	private void successResponse(String message) {
		detailsMap = new HashMap<String, Object>();
		detailsMap.put("id", this.getSession().toString());
		YouTubeMimicUtils.apiResponseWriter(this.response, SUCCESS, message, success, detailsMap, OK);
	}

	private void errorResponse(String message) {
		YouTubeMimicUtils.apiResponseWriter(this.response, ERROR, message, error, null, FAILED);
	}
}
