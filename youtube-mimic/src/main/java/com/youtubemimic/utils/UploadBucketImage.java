
package com.youtubemimic.utils;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.util.Base64;

import static com.youtubemimic.auth.UserPropertyConstant.*;

public class UploadBucketImage {
	

	public static UploadBucketImage getInstance() {
		return new UploadBucketImage();
	}

	public String uploadImage(String fileName, String base64String) {
		String[] strings = base64String.split(",");
		String contentType;
		switch (strings[0]) {
		case "data:image/png;base64":
			contentType = "image/png";
			break;
		case "data:image/jpeg;base64":
			contentType = "image/jpeg";
			break;
		default:
			contentType = "jpg";
			break;
		}

		byte[] data = Base64.getDecoder().decode(strings[1]);
		return uploadToCloudStorage(fileName, data, contentType);
	}

	private static String uploadToCloudStorage(String fileName, byte[] fileInputStream, String contentType) {
		
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		String name = System.nanoTime()+fileName;
		BlobId blobId = BlobId.of(bucketName,  name);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
		Blob blob = storage.create(blobInfo, fileInputStream);
		return blob.getMediaLink();
	}
}
