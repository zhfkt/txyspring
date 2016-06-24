package com.tongmeng.txyspring.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.qcloud.*;
import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.hibernate.ActCode;
import com.tongmeng.txyspring.model.hibernate.CommonActImage;
import com.tongmeng.txyspring.model.hibernate.CommonActInfo;
import com.tongmeng.txyspring.model.hibernate.JobExtraInfo;
import com.tongmeng.txyspring.model.hibernate.SchCode;
import com.tongmeng.txyspring.model.web.BackendCommonActInfo;

@Service
public class BackendService {

	private static final Logger logger = LoggerFactory.getLogger(BackendService.class);
	
	@Autowired
	private CommonActInfoDao commonActInfoDao;

	@Transactional
	public void insertBackendCommonActInfo(BackendCommonActInfo backendCommonActInfo) {


		//if needOrder exists
		int needOrder = 0;
		if(backendCommonActInfo.getNeedOrder()!=null)
		{
			needOrder = backendCommonActInfo.getNeedOrder();
		}
		
		//2016-03-01 15:00:00
		
		Date startDate = DateTime.parse("1971-01-0100:02",DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();
		Date endDate = DateTime.parse("2038-01-1903:14",DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();
		
		try
		{
			startDate = DateTime.parse(backendCommonActInfo.getStartDate() + backendCommonActInfo.getStartTime(),
					DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();
			endDate =  DateTime.parse( backendCommonActInfo.getEndDate() +  backendCommonActInfo.getEndTime(),
					DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();	
		}
		catch(IllegalArgumentException e)
		{
			logger.warn(e.toString());
			logger.warn("DateTime.parse failed");
		}
		
		
		Date pubTime =  new DateTime().toDate();
		
		ActCode actCode = null;
		SchCode schCode = null;
		
		
		if(backendCommonActInfo.getActsubtype()!=null)
		{
			actCode = new ActCode(backendCommonActInfo.getActsubtype());	
		}
		else
		{
			actCode = new ActCode(0);
		}
		
		if(backendCommonActInfo.getCampus()!=null)
		{
			schCode = new SchCode(backendCommonActInfo.getCampus());
		}
		else
		{
			schCode = new SchCode(0);
		}
		
		
		CommonActInfo commonActInfo = new CommonActInfo(actCode,
				schCode,
				backendCommonActInfo.getTitle(),
				startDate,
				endDate,
				pubTime,
				backendCommonActInfo.getLocation(), 
				backendCommonActInfo.getPersonNum(), 
				0,
				0, 
				"",
				backendCommonActInfo.getDetail(),
				backendCommonActInfo.getTel(),
				backendCommonActInfo.getEmail(),
				backendCommonActInfo.getQq(),
				"",
				0,
				backendCommonActInfo.getAuthor(),
				"",
				needOrder,
				null,
				null,
				null,
				null,
				null); 
		
		//if Job exists
		
		JobExtraInfo jobExtraInfo = null;
		if(backendCommonActInfo.getInfo()!=null)
		{
			jobExtraInfo = new JobExtraInfo(commonActInfo, "", backendCommonActInfo.getInfo());
			commonActInfo.setJobExtraInfo(jobExtraInfo);
		}

		//Pictures
		
		ImageServiceUpload imageServiceUpload = new ImageServiceUpload();
		
		Set<CommonActImage> commonActImages = new HashSet<CommonActImage>();
		List<MultipartFile> pictures = backendCommonActInfo.getPictures();
        if(pictures != null && pictures.size() != 0) {
            for(MultipartFile picture : pictures) {  	
            	if(!picture.isEmpty())
            	{
                	CommonActImage commonActImage = new CommonActImage(commonActInfo,imageServiceUpload.cloudImagePathSave(picture));
                	commonActImages.add(commonActImage);	
            	}
            	
            }
        }
        
        commonActInfo.setCommonActImages(commonActImages);
        
        
        //Cover Image
        
        MultipartFile coverImg = backendCommonActInfo.getCoverImg();
        if(!coverImg.isEmpty())
        {
        	String covImgUri = imageServiceUpload.cloudImagePathSave(coverImg);
    		commonActInfo.setCovImgUri(covImgUri);	
        }
        
		
		
		//save using hibernate
		
		commonActInfoDao.saveCommonActInfo(commonActInfo);
		for(CommonActImage commonActImage : commonActImages)
		{
			commonActInfoDao.saveCommonActInfo(commonActImage);
		}
		if(jobExtraInfo!=null)
		{
			commonActInfoDao.saveJobExtraInfo(jobExtraInfo);	
		}
		
		return;
	}

}



class ImageServiceUpload
{
	private static final int APP_ID_V2 = 10042156;
    private static final String SECRET_ID_V2 = "AKIDY0p0aiqAp7HOcG4OQlX2y4V5AgDXC2vu";
    private static final String SECRET_KEY_V2 = "k9yR8HUe4aObQ6vBoTL1Yzm85YdIpXPq";
    private static final String BUCKET = "txyspring";
    private PicCloudSliceUpload pc;
	
	public ImageServiceUpload()
	{
		pc = new PicCloudSliceUpload(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
		
	}
	
	public String cloudImagePathSave(MultipartFile img)
	{
		if(img.isEmpty())
		{
			return null;
		}
	
        UploadResult result;
		try {
			//result = pc.upload(img.getInputStream());
			result = pc.simpleUploadSlice(img.getBytes(),(int) img.getSize(),512*1024);
			
		} catch (IOException e) {
			throw new IllegalArgumentException(e.toString());
		}

        if(result != null){
            result.print();
        }
        else
        {
        	throw new IllegalArgumentException("UploadResult result NULL "+ pc.getErrMsg() + ": " + pc.getError());
        }
        
        String imageMobileUrl = result.downloadUrl + "/mobile";
        
		return imageMobileUrl;
	}
	
}

class PicCloudSliceUpload extends PicCloud
{
	
	public PicCloudSliceUpload(int appId, String secret_id, String secret_key, String bucket) 
	{
		super(appId, secret_id, secret_key, bucket);
	}
	
	
    public SliceUploadInfo simpleUploadSlice(byte[] data ,int fileSize,int sliceSize){

        SliceUploadInfo info = initUploadSlice("", data, fileSize, sliceSize);
        if(null == info){
            return info;
        }
        
        while(false == info.finishFlag){
            SliceUploadInfo newInfo = UploadSlice(data, info);
            if(newInfo == null){
                setError(-1, "slice upload failed, need retry");
                return null;
            }
            info = newInfo;
        }
        
        setError(0, "success");
        return info;
    }
	
}


