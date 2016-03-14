DROP SCHEMA IF EXISTS txyspring;
CREATE SCHEMA txyspring;
USE txyspring;

CREATE TABLE SLIDERS (
	ID INT NOT NULL AUTO_INCREMENT,
	Title VARCHAR (100) NOT NULL,
	Image_Path TEXT NOT NULL,
	Link TEXT NOT NULL,
	PRIMARY KEY ( ID )
);


CREATE TABLE SCH_CODE(
	Sch_Code INT NOT NULL,
	Area_Code INT NOT NULL,
	Description VARCHAR(100),
	PRIMARY KEY (Sch_Code, Area_Code)
);


CREATE TABLE ACT_CODE(
	Act_Type INT NOT NULL,
	Act_Subtype INT NOT NULL,
	Description_Subtype VARCHAR(100),
	PRIMARY KEY (Act_Type, Act_Subtype)
);


CREATE TABLE COMMON_ACT_INFO (
	ID INT NOT NULL AUTO_INCREMENT,
	Title TEXT NOT NULL,
	Start_Date TIMESTAMP, 
	End_Date TIMESTAMP,
	Pub_Time TIMESTAMP,
	Location TEXT,
	People_Number INT,
 	NumRead INT,
	NumFavo INT,
	CovImg_Uri TEXT,
	Intro TEXT,
	CtPer_Tel VARCHAR(100),
	CtPer_Mail VARCHAR(100),
	CtPer_QQ VARCHAR(100),
	OutLink TEXT,
	Stat_Code INT,
	
	Sch_Code INT,
	Area_Code INT,
	Act_Type INT,
	Act_subtype INT,
	
	PRIMARY KEY (ID),
	FOREIGN KEY (Sch_Code,Area_Code) REFERENCES SCH_CODE(Sch_Code,Area_Code)
	ON DELETE RESTRICT
	ON UPDATE CASCADE,
	FOREIGN KEY (Act_Type,Act_Subtype) REFERENCES ACT_CODE(Act_Type,Act_Subtype)
	ON DELETE RESTRICT
	ON UPDATE CASCADE
);

CREATE TABLE ACT_EXTRA_INFO(
	ID INT NOT NULL,
	Organizer VARCHAR(100),
	Sponsor VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
);

CREATE TABLE POST_EXTRA_INFO(
	ID INT NOT NULL,
	Organizer VARCHAR(100),
	Sponsor VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
);

CREATE TABLE JOB_EXTRA_INFO(
	ID INT NOT NULL,
	Vld_Time VARCHAR(100),
	Salary VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
);

#---------------------------------
# const data insert

insert SCH_CODE(Sch_Code,Area_Code,Description)
values (1,0,'同济大学');

insert SCH_CODE(Sch_Code,Area_Code,Description)
values (1,1,'同济大学嘉定校区');

insert SCH_CODE(Sch_Code,Area_Code,Description)
values (1,2,'同济大学四平路校区');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (1,0,'校园活动');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (1,11,'公共讲座');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (1,12,'文艺演出');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (2,0,'招聘宣讲');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (3,0,'兼职赚钱');

insert ACT_CODE(Act_Type,Act_Subtype,Description_Subtype)
values (4,0,'生活周边');


#---------------------------------
# test data insert

insert SLIDERS (Title,Image_Path,Link) 
values ('Test data','http://tp3.sinaimg.cn/2424186582/180/5746253598/1','http://weibo.com');

insert SLIDERS (Title,Image_Path,Link) 
values ('Test data 2','https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png','http://weibo.com');

insert COMMON_ACT_INFO(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Sch_Code,Area_Code,Act_Type,Act_subtype)
values ('Test data','2016-07-01 23:22:11','2016-08-01 23:22:11','2016-06-01 23:22:11','妯辫姳澶ч亾','10','12','11','https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png','TEST DATA',
	'110','xxx@ggg.com','123456','http://weibo.com',1,1,1,1,11);
	
insert COMMON_ACT_INFO(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Sch_Code,Area_Code,Act_Type,Act_subtype)
values ('Test data 1','2016-07-01 23:22:11','2016-08-01 23:22:11','2016-06-01 23:22:11','妯辫姳澶ч亾1','10','12','11','https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png','TEST DATA',
	'110','xxx@ggg.com','123456','http://weibo.com',1,1,1,1,11);
	
    
#---------------------------------

#select * from SLIDERS;



