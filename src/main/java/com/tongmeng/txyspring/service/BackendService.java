package com.tongmeng.txyspring.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tongmeng.txyspring.backendmodel.BackendCommonActInfo;
import com.tongmeng.txyspring.dao.CommonActInfoDao;
import com.tongmeng.txyspring.model.ActCode;
import com.tongmeng.txyspring.model.CommonActImage;
import com.tongmeng.txyspring.model.CommonActInfo;
import com.tongmeng.txyspring.model.JobExtraInfo;
import com.tongmeng.txyspring.model.SchCode;

@Service
public class BackendService {

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
		Date startDate = DateTime.parse(backendCommonActInfo.getStartDate() + backendCommonActInfo.getStartTime(),
				DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();
		Date endDate =  DateTime.parse( backendCommonActInfo.getEndDate() +  backendCommonActInfo.getEndTime(),
				DateTimeFormat.forPattern("yyyy-MM-ddHH:mm")).toDate();
		Date pubTime =  new DateTime().toDate();
		
		CommonActInfo commonActInfo = new CommonActInfo(new ActCode(backendCommonActInfo.getActsubtype()),
				new SchCode(backendCommonActInfo.getCampus()),
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
				"",
				backendCommonActInfo.getAuthor(),
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
		
		Set<CommonActImage> commonActImages = new HashSet<CommonActImage>();
		List<MultipartFile> pictures = backendCommonActInfo.getPictures();
        if(pictures != null && pictures.size() != 0) {
            for(MultipartFile picture : pictures) {  	
            	if(!picture.isEmpty())
            	{
                	CommonActImage commonActImage = new CommonActImage(commonActInfo,cloudImagePathSave(picture));
                	commonActImages.add(commonActImage);	
            	}
            	
            }
        }
        
        commonActInfo.setCommonActImages(commonActImages);
        
        
        //Cover Image
        
        MultipartFile coverImg = backendCommonActInfo.getCoverImg();
        if(!coverImg.isEmpty())
        {
        	String covImgUri = cloudImagePathSave(coverImg);
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
	
	
	
	String cloudImagePathSave(MultipartFile img)
	{
		if(img.isEmpty())
		{
			return null;
		}
		
		return "https://raw.githubusercontent.com/zhfkt/txyspring/master/src/main/webapp/resources/images/gorilla.jpg";
	}
	
	
	
}
