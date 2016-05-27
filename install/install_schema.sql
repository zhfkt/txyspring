DROP SCHEMA IF EXISTS txyspring;
CREATE SCHEMA txyspring;
USE txyspring;

CREATE TABLE sch_code(
	Area_Code INT NOT NULL,
	Description VARCHAR(100),
	PRIMARY KEY (Area_Code)
)DEFAULT CHARSET=utf8;

CREATE TABLE act_code(
	Act_Subtype INT NOT NULL,
	Description_Subtype VARCHAR(100),
	PRIMARY KEY (Act_Subtype)
)DEFAULT CHARSET=utf8;

CREATE TABLE common_act_info (
	ID INT NOT NULL AUTO_INCREMENT,
	Title TEXT,
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
	Area_Code INT NOT NULL,
	Act_Subtype INT NOT NULL,
	Organizer VARCHAR(100),
	Sponsor VARCHAR(100),
    Is_Reversed INT,
    
	PRIMARY KEY (ID),
	FOREIGN KEY (Area_Code) REFERENCES sch_code(Area_Code)
	ON DELETE RESTRICT
	ON UPDATE CASCADE,
	FOREIGN KEY (Act_Subtype) REFERENCES act_code(Act_Subtype)
	ON DELETE RESTRICT
	ON UPDATE CASCADE
)DEFAULT CHARSET=utf8;

CREATE TABLE sliders (
	ID INT NOT NULL AUTO_INCREMENT,
	Title VARCHAR (100),
    Act_ID INT,
	Image_Path TEXT,
	Link TEXT,
	
    PRIMARY KEY (ID),
	FOREIGN KEY (Act_ID) REFERENCES common_act_info(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
)DEFAULT CHARSET=utf8;


CREATE TABLE act_extra_info(
	ID INT NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES common_act_info(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
)DEFAULT CHARSET=utf8;


CREATE TABLE job_extra_info(
	ID INT NOT NULL,
    Salary VARCHAR(100),
    Job_Info VARCHAR(100),
    
	PRIMARY KEY (ID),
	FOREIGN KEY (ID) REFERENCES common_act_info(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE	
)DEFAULT CHARSET=utf8;

CREATE TABLE user_all(
	ID INT NOT NULL AUTO_INCREMENT,
    PSW VARCHAR(100),
	Area_Code INT NOT NULL,
    Ori_ID VARCHAR(100) NOT NULL,
    Ori_Name VARCHAR(10),
    Nick_Name VARCHAR(100),
    Age INT,
    Major VARCHAR(100),
	Tel VARCHAR(100),
	Mail VARCHAR(100),    
    Gender INT,
    Hdimg_Uri TEXT,
    Type_code INT,
	PRIMARY KEY (ID),
	FOREIGN KEY (Area_Code) REFERENCES sch_code(Area_Code)
	ON DELETE RESTRICT
	ON UPDATE CASCADE,
	UNIQUE (Area_Code, Ori_ID)
)DEFAULT CHARSET=utf8;

CREATE TABLE user_act_clt(
	ID INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID),
	User_ID INT NOT NULL,
    Act_ID INT NOT NULL,
    
	FOREIGN KEY (User_ID) REFERENCES user_all(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
	FOREIGN KEY (Act_ID) REFERENCES common_act_info(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE        
)DEFAULT CHARSET=utf8;

CREATE TABLE common_act_image(
	ID INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (ID),
    
	Act_ID INT NOT NULL,
    Image TEXT,
    
	FOREIGN KEY (Act_ID) REFERENCES common_act_info(ID)
	ON DELETE CASCADE
	ON UPDATE CASCADE        
)DEFAULT CHARSET=utf8;


#---------------------------------
# const data insert

insert sch_code(Area_Code,Description)
values (0,'校外');

insert sch_code(Area_Code,Description)
values (10000,'同济大学');

insert sch_code(Area_Code,Description)
values (10001,'同济大学嘉定校区');

insert sch_code(Area_Code,Description)
values (10002,'同济大学四平路校区');

insert sch_code(Area_Code,Description)
values (10003,'同济大学沪西校区');

insert act_code(Act_Subtype,Description_Subtype)
values (10000,'校园活动');

insert act_code(Act_Subtype,Description_Subtype)
values (10011,'讲座报告');

insert act_code(Act_Subtype,Description_Subtype)
values (10012,'文艺演出');

insert act_code(Act_Subtype,Description_Subtype)
values (10013,'体育赛事');

insert act_code(Act_Subtype,Description_Subtype)
values (10014,'社团社交');

insert act_code(Act_Subtype,Description_Subtype)
values (10015,'竞赛培训');

insert act_code(Act_Subtype,Description_Subtype)
values (10016,'其他活动');

insert act_code(Act_Subtype,Description_Subtype)
values (20000,'企业直通');

insert act_code(Act_Subtype,Description_Subtype)
values (20011,'企业宣讲');

insert act_code(Act_Subtype,Description_Subtype)
values (20012,'工作实习');

insert act_code(Act_Subtype,Description_Subtype)
values (20013,'其他信息');

select * from common_act_image;
select * from common_act_info;



