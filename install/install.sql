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
	Description_Type VARCHAR(100),
	PRIMARY KEY (Act_Type)
);

CREATE TABLE SUBACT_CODE(
	Act_Type INT NOT NULL,
	Act_Subtype INT NOT NULL,
	Description_Subtype VARCHAR(100),
	PRIMARY KEY (Act_Type, Act_Subtype),
	FOREIGN KEY (Act_Type) REFERENCES ACT_CODE(Act_Type)
	ON DELETE RESTRICT
	ON UPDATE CASCADE
);


CREATE TABLE COMMON_ACT_INFO (
	ID INT NOT NULL AUTO_INCREMENT,
	Title TEXT NOT NULL,
	Start_Date TIMESTAMP, 
	End_Date TIMESTAMP,
	Pub_Time TIMESTAMP,
	Location TEXT,
	People_Number int,
 	NumRead int,
	NumFavo int,
	CovImg_Uri TEXT,
	Intro TEXT,
	CtPer_Tel VARCHAR(100),
	CtPer_Mail VARCHAR(100),
	CtPer_QQ VARCHAR(100),
	OutLink TEXT,
	Stat_Code int,
	
	Sch_Code INT,
	Area_Code INT,
	Act_Type int,
	Act_subtype int,
	
	PRIMARY KEY (ID),
	FOREIGN KEY (Sch_Code,Area_Code) REFERENCES SCH_CODE(Sch_Code,Area_Code)
	ON DELETE RESTRICT
	ON UPDATE CASCADE,
	FOREIGN KEY (Act_Type,Act_Subtype) REFERENCES SUBACT_CODE(Act_Type,Act_Subtype)
	ON DELETE RESTRICT
	ON UPDATE CASCADE
);

CREATE TABLE ACT_EXTRA_INFO(
	ID INT NOT NULL,
	Organizer VARCHAR(100),
	Sponsor VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE RESTRICT
	ON UPDATE CASCADE	
);

CREATE TABLE POST_EXTRA_INFO(
	ID INT NOT NULL,
	Organizer VARCHAR(100),
	Sponsor VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE RESTRICT
	ON UPDATE CASCADE	
);

CREATE TABLE JOB_EXTRA_INFO(
	ID INT NOT NULL,
	Vld_Time VARCHAR(100),
	Salary VARCHAR(100),
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES COMMON_ACT_INFO(ID)
	ON DELETE RESTRICT
	ON UPDATE CASCADE	
);




#---------------------------------
# test data insert

insert SLIDERS (Title,Image_Path,Link) 
values ("Test data","http://tp3.sinaimg.cn/2424186582/180/5746253598/1","http://weibo.com");

insert SLIDERS (Title,Image_Path,Link) 
values ("Test data 2","https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png","http://weibo.com");
	
    
#---------------------------------

#select * from SLIDERS;



