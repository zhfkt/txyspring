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
values (10003,'同济大学彰武路校区');

insert sch_code(Area_Code,Description)
values (10004,'同济大学沪西校区');

insert act_code(Act_Subtype,Description_Subtype)
values (10000,'校园活动');


insert act_code(Act_Subtype,Description_Subtype)
values (0,'未归类');

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


#---------------------------------
# test data insert

delimiter #


insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('"我的社区我做主"——参与式社区规划的理念与运作机制',
		'2016-03-01 15:00:00',
        '2016-03-01 17:00:00',
        '2016-02-27 17:00:00',
        '建筑与城市规划学院C楼二楼C1会议室',
        '200',
        '18',
		'5',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '内容无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10002,
        10011,
        '建筑与城市规划学院',
        '',
        0);
 

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('化学系学术报告：Theoretical understanding of water splitting and oxygen reduction',
		'2016-04-01 10:00:00',
        '2016-04-01 11:00:00',
        '2016-03-27 17:00:00',
        '化学馆 241',
        '100',
        '30',
		'23',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '内容无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10002,
        10011,
        '化学系',
        '',
        0);    
        
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('【茶话清谈】樱花盛开诗意校园',
		'2016-04-02 10:00:00',
        '2016-04-02 11:00:00',
        '2016-03-28 17:00:00',
        '图书馆十楼（南）闻学堂',
        '300',
        '120',
		'80',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '人间四月草长莺飞，又到一年樱花烂漫纷飞时节，如云似霞。花团锦簇的同济校园里，飘落的樱花如飞舞的精灵，划过发梢，绕过裙角，香了风，也香了伊人裳……花能解语，亦能通过诗词、歌赋来传情达意。美的无可方物的樱花，被多少诗人赞许、爱怜，化作句句字里行间那轻描淡写的流年的诗篇。',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10002,
        10011,
        '闻学堂',
        '',
        0); 	
        
        

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('樱花树演唱会',
		'2016-04-02 18:00:00',
        '2016-04-02 20:00:00',
        '2016-03-30 17:00:00',
        '129大礼堂',
        '800',
        '1024',
		'460',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '暂无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10002,
        10011,
        '化学系',
        '',
        1); 	
              
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('U联赛大学生篮球挑战赛',
		'2016-04-02 00:00:00',
        '2016-06-03 00:00:00',
        '2016-04-01 17:00:00',
        '篮球场',
        '1000',
        '600',
		'20',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '篮球大赛',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10001,
        10012,
        '团学联体育部',
        '',
        1); 	

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('4月假面舞会',
		'2016-04-08 19:00:00',
        '2016-04-08 21:00:00',
        '2016-04-01 17:00:00',
        '茶水吧',
        '200',
        '601',
		'302',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '跳舞吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10001,
        10013,
        '同济舞鞋',
        '',
        1); 	

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('华为2016校招宣讲会',
		'2016-04-09 19:00:00',
        '2016-04-09 20:30:00',
        '2016-04-02 17:00:00',
        '智信馆117',
        '300',
        '150',
		'20',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '招聘吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10001,
        20011,
        '电信学院研究生会',
        '',
        0); 	

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('安居客2016校招宣讲会',
		'2016-04-10 19:00:00',
        '2016-04-10 20:00:00',
        '2016-04-02 17:00:00',
        '智信馆307',
        '300',
        '15',
		'20',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '招聘吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10001,
        20011,
        '电信学院研究生会',
        '',
        0); 	

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,
	CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_subtype,Organizer,Sponsor,Is_Reversed)
values ('Oracle暑期实习招聘',
		'2016-04-02 17:00:00',
        '2016-05-02 17:00:00',
        '2016-04-02 17:00:00',
        '杨浦区某地',
        '10',
        '21',
		'20',
		'http://115.28.68.32:8080/resources/images/gorilla.jpg',
        '招聘吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        0,
        20012,
        '电信学院研究生会',
        '',
        0); 	

insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','测试、数据库设计');
        

insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告001','2016/3/1 15:00','2016/3/1 17:00','2016/2/27 17:00','讲座报告地点1','804','608','356','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210525@qq.com','415929235','',0,'10002','10011','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告002','2016/4/1 10:00','2016/4/1 11:00','2016/3/27 17:00','讲座报告地点2','822','578','86','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210525@qq.com','415929235','',0,'10001','10011','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告003','2016/4/2 10:00','2016/4/2 11:00','2016/3/27 18:00','讲座报告地点3','770','651','261','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210526@qq.com','415929235','',0,'10001','10011','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告004','2016/4/3 10:00','2016/4/3 11:00','2016/3/27 19:00','讲座报告地点4','899','752','46','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210527@qq.com','415929235','',0,'10002','10011','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告005','2016/4/4 10:00','2016/4/4 11:00','2016/3/28 19:00','讲座报告地点5','755','719','127','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210528@qq.com','415929235','',0,'10002','10011','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告006','2016/4/5 10:00','2016/4/5 11:00','2016/3/30 19:00','讲座报告地点6','628','584','345','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210529@qq.com','415929235','',0,'10002','10011','同济舞鞋','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告007','2016/4/6 10:00','2016/4/6 11:00','2016/3/31 19:00','讲座报告地点7','654','732','83','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210530@qq.com','415929235','',0,'10002','10011','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告008','2016/4/7 10:00','2016/4/7 11:00','2016/4/1 19:00','讲座报告地点8','851','560','231','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210531@qq.com','415929235','',0,'10002','10011','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告009','2016/4/8 10:00','2016/4/8 11:00','2016/4/2 19:00','讲座报告地点9','982','795','282','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210532@qq.com','415929235','',0,'10002','10011','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告010','2016/4/9 10:00','2016/4/9 11:00','2016/4/3 19:00','讲座报告地点10','534','663','4','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210533@qq.com','415929235','',0,'10001','10011','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'讲座报告011','2016/4/10 10:00','2016/4/10 11:00','2016/4/2 19:00','讲座报告地点11','531','682','442','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210534@qq.com','415929235','',0,'10002','10011','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出001','2016/4/11 10:00','2016/4/11 11:00','2016/4/3 19:00','文艺演出地点1','189','639','13','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986021','1210535@qq.com','415929235','',0,'10002','10012','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出002','2016/4/12 10:00','2016/4/12 11:00','2016/4/4 19:00','文艺演出地点2','680','573','134','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986022','1210536@qq.com','415929235','',0,'10002','10012','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出003','2016/4/13 10:00','2016/4/13 11:00','2016/4/5 19:00','文艺演出地点3','881','502','377','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986023','1210537@qq.com','415929235','',0,'10001','10012','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出004','2016/4/14 10:00','2016/4/14 11:00','2016/4/6 19:00','文艺演出地点4','394','647','241','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986024','1210538@qq.com','415929235','',0,'10002','10012','同济舞鞋','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出005','2016/4/15 10:00','2016/4/15 11:00','2016/4/7 19:00','文艺演出地点5','980','680','187','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986025','1210539@qq.com','415929235','',0,'10001','10012','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出006','2016/4/16 10:00','2016/4/16 11:00','2016/4/8 19:00','文艺演出地点6','574','776','233','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986026','1210540@qq.com','415929235','',0,'10002','10012','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出007','2016/4/17 10:00','2016/4/17 11:00','2016/4/9 19:00','文艺演出地点7','573','538','473','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986027','1210541@qq.com','415929235','',0,'10001','10012','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出008','2016/4/18 10:00','2016/4/18 11:00','2016/4/10 19:00','文艺演出地点8','838','617','128','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986028','1210542@qq.com','415929235','',0,'10001','10012','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出009','2016/4/19 10:00','2016/4/19 11:00','2016/4/11 19:00','文艺演出地点9','312','777','478','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986029','1210543@qq.com','415929235','',0,'10001','10012','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出010','2016/4/20 10:00','2016/4/20 11:00','2016/4/12 19:00','文艺演出地点10','467','719','211','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986030','1210544@qq.com','415929235','',0,'10002','10012','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'文艺演出011','2016/4/21 10:00','2016/4/21 11:00','2016/4/13 19:00','文艺演出地点11','498','599','234','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986031','1210545@qq.com','415929235','',0,'10001','10013','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事001','2016/4/22 10:00','2016/4/22 11:00','2016/4/14 19:00','体育地点01','93','707','123','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986032','1210546@qq.com','415929235','',0,'10002','10013','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事002','2016/4/23 10:00','2016/4/23 11:00','2016/4/15 19:00','体育地点02','790','693','195','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986033','1210547@qq.com','415929235','',0,'10001','10013','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事003','2016/4/24 10:00','2016/4/24 11:00','2016/4/16 19:00','体育地点03','914','657','46','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986034','1210548@qq.com','415929235','',0,'10002','10013','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事004','2016/4/25 10:00','2016/4/25 11:00','2016/4/17 19:00','体育地点04','946','579','362','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986035','1210549@qq.com','415929235','',0,'10002','10013','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事005','2016/4/26 10:00','2016/4/26 11:00','2016/4/18 19:00','体育地点05','527','511','278','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986036','1210550@qq.com','415929235','',0,'10002','10013','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事006','2016/4/27 10:00','2016/4/27 11:00','2016/4/19 19:00','体育地点06','12','580','195','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986037','1210551@qq.com','415929235','',0,'10002','10013','同济舞鞋','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事007','2016/4/28 10:00','2016/4/28 11:00','2016/4/20 19:00','体育地点07','774','722','121','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986038','1210552@qq.com','415929235','',0,'10002','10013','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事008','2016/4/29 10:00','2016/4/29 11:00','2016/4/21 19:00','体育地点08','614','796','290','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986039','1210553@qq.com','415929235','',0,'10001','10013','建筑与城市规划学院','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事009','2016/4/30 10:00','2016/4/30 11:00','2016/4/22 19:00','体育地点09','50','535','144','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986040','1210554@qq.com','415929235','',0,'10001','10013','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事010','2016/5/1 10:00','2016/5/1 11:00','2016/4/23 19:00','体育地点10','936','598','269','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986041','1210555@qq.com','415929235','',0,'10002','10013','闻学堂','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事011','2016/5/2 10:00','2016/5/2 11:00','2016/4/24 19:00','体育地点11','354','604','408','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986042','1210556@qq.com','415929235','',0,'10002','10013','化学系','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事012','2016/5/3 10:00','2016/5/3 11:00','2016/4/25 19:00','体育地点12','554','559','82','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986043','1210557@qq.com','415929235','',0,'10001','10013','团学联体育部','','0's
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事013','2016/5/4 10:00','2016/5/4 11:00','2016/4/26 19:00','体育地点13','489','778','357','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986044','1210558@qq.com','415929235','',0,'10001','10013','同济舞鞋','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'体育赛事014','2016/5/5 10:00','2016/5/5 11:00','2016/4/27 19:00','体育地点14','904','608','325','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986045','1210559@qq.com','415929235','',0,'10002','10013','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交001','2016/5/6 10:00','2016/5/6 11:00','2016/4/28 19:00','社团社交地点01','500','738','465','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986046','1210560@qq.com','415929236','',0,'10002','10014','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交002','2016/5/7 10:00','2016/5/7 11:00','2016/4/29 19:00','社团社交地点02','761','651','173','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986047','1210561@qq.com','415929237','',0,'10002','10014','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交003','2016/5/8 10:00','2016/5/8 11:00','2016/4/30 19:00','社团社交地点03','477','515','429','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986048','1210562@qq.com','415929238','',0,'10002','10014','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交004','2016/5/9 10:00','2016/5/9 11:00','2016/5/1 19:00','社团社交地点04','507','798','195','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986049','1210563@qq.com','415929239','',0,'10002','10014','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交005','2016/5/10 10:00','2016/5/10 11:00','2016/5/2 19:00','社团社交地点05','873','719','490','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986050','1210564@qq.com','415929240','',0,'10001','10014','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交006','2016/5/11 10:00','2016/5/11 11:00','2016/5/3 19:00','社团社交地点06','441','577','237','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986051','1210565@qq.com','415929241','',0,'10002','10014','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交007','2016/5/12 10:00','2016/5/12 11:00','2016/5/4 19:00','社团社交地点07','645','634','432','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986052','1210566@qq.com','415929242','',0,'10002','10014','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交008','2016/5/13 10:00','2016/5/13 11:00','2016/5/5 19:00','社团社交地点08','614','621','360','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986053','1210567@qq.com','415929243','',0,'10001','10014','同济舞鞋','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交009','2016/5/14 10:00','2016/5/14 11:00','2016/5/6 19:00','社团社交地点09','629','591','440','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986054','1210568@qq.com','415929244','',0,'10002','10014','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交010','2016/5/15 10:00','2016/5/15 11:00','2016/5/7 19:00','社团社交地点10','447','680','341','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986055','1210569@qq.com','415929245','',0,'10002','10014','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交011','2016/5/16 10:00','2016/5/16 11:00','2016/5/8 19:00','社团社交地点11','884','510','144','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986056','1210570@qq.com','415929246','',0,'10001','10014','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交012','2016/5/17 10:00','2016/5/17 11:00','2016/5/9 19:00','社团社交地点12','699','569','29','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986057','1210571@qq.com','415929247','',0,'10002','10014','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交013','2016/5/18 10:00','2016/5/18 11:00','2016/5/10 19:00','社团社交地点13','734','574','494','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986058','1210572@qq.com','415929248','',0,'10002','10014','闻学堂','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'社团社交014','2016/5/19 10:00','2016/5/19 11:00','2016/5/11 19:00','社团社交地点14','353','592','400','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986059','1210573@qq.com','415929249','',0,'10001','10014','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训001','2016/5/20 10:00','2016/5/20 11:00','2016/5/12 19:00','竞赛培训地点01','924','777','188','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986060','1210574@qq.com','415929250','',0,'10001','10015','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训002','2016/5/21 10:00','2016/5/21 11:00','2016/5/13 19:00','竞赛培训地点02','520','759','361','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986061','1210575@qq.com','415929251','',0,'10001','10015','同济舞鞋','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训003','2016/5/22 10:00','2016/5/22 11:00','2016/5/14 19:00','竞赛培训地点03','851','688','109','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986062','1210576@qq.com','415929252','',0,'10002','10015','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训004','2016/5/23 10:00','2016/5/23 11:00','2016/5/15 19:00','竞赛培训地点04','198','651','62','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986063','1210577@qq.com','415929253','',0,'10002','10015','化学系','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训005','2016/5/24 10:00','2016/5/24 11:00','2016/5/16 19:00','竞赛培训地点05','129','520','278','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986064','1210578@qq.com','415929254','',0,'10002','10015','闻学堂','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训006','2016/5/25 10:00','2016/5/25 11:00','2016/5/17 19:00','竞赛培训地点06','900','772','38','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986065','1210579@qq.com','415929255','',0,'10002','10015','化学系','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训007','2016/5/26 10:00','2016/5/26 11:00','2016/5/18 19:00','竞赛培训地点07','233','589','150','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986066','1210580@qq.com','415929256','',0,'10002','10015','团学联体育部','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训008','2016/5/27 10:00','2016/5/27 11:00','2016/5/19 19:00','竞赛培训地点08','346','769','353','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986067','1210581@qq.com','415929257','',0,'10002','10015','同济舞鞋','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训009','2016/5/28 10:00','2016/5/28 11:00','2016/5/20 19:00','竞赛培训地点09','952','749','398','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986068','1210582@qq.com','415929258','',0,'10002','10015','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训010','2016/5/29 10:00','2016/5/29 11:00','2016/5/21 19:00','竞赛培训地点10','885','598','211','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986069','1210583@qq.com','415929259','',0,'10002','10015','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训011','2016/5/30 10:00','2016/5/30 11:00','2016/5/22 19:00','竞赛培训地点11','977','519','329','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986070','1210584@qq.com','415929260','',0,'10002','10015','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训012','2016/5/31 10:00','2016/5/31 11:00','2016/5/23 19:00','竞赛培训地点12','638','659','49','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986071','1210585@qq.com','415929261','',0,'10001','10015','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训013','2016/6/1 10:00','2016/6/1 11:00','2016/5/24 19:00','竞赛培训地点13','218','733','278','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986072','1210586@qq.com','415929262','',0,'10001','10015','化学系','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'竞赛培训014','2016/6/2 10:00','2016/6/2 11:00','2016/5/25 19:00','竞赛培训地点14','718','521','229','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986073','1210587@qq.com','415929263','',0,'10002','10015','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动001','2016/6/3 10:00','2016/6/3 11:00','2016/5/26 19:00','其他活动地点01','53','744','220','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986074','1210588@qq.com','415929264','',0,'10002','10016','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动002','2016/6/4 10:00','2016/6/4 11:00','2016/5/27 19:00','其他活动地点02','568','786','208','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986075','1210589@qq.com','415929265','',0,'10002','10016','团学联体育部','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动003','2016/6/5 10:00','2016/6/5 11:00','2016/5/28 19:00','其他活动地点03','902','707','86','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986076','1210590@qq.com','415929266','',0,'10002','10016','同济舞鞋','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动004','2016/6/6 10:00','2016/6/6 11:00','2016/5/29 19:00','其他活动地点04','977','568','8','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986077','1210591@qq.com','415929267','',0,'10002','10016','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动005','2016/6/7 10:00','2016/6/7 11:00','2016/5/30 19:00','其他活动地点05','396','522','402','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986078','1210592@qq.com','415929268','',0,'10002','10016','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动006','2016/6/8 10:00','2016/6/8 11:00','2016/5/31 19:00','其他活动地点06','954','707','201','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986079','1210593@qq.com','415929269','',0,'10001','10016','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动007','2016/6/9 10:00','2016/6/9 11:00','2016/6/1 19:00','其他活动地点07','990','584','469','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986080','1210594@qq.com','415929270','',0,'10001','10016','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动008','2016/6/10 10:00','2016/6/10 11:00','2016/6/2 19:00','其他活动地点08','642','619','162','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986081','1210595@qq.com','415929271','',0,'10001','10016','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动009','2016/6/11 10:00','2016/6/11 11:00','2016/6/3 19:00','其他活动地点09','509','583','77','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986082','1210596@qq.com','415929272','',0,'10001','10016','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动010','2016/6/12 10:00','2016/6/12 11:00','2016/6/4 19:00','其他活动地点10','420','784','191','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986083','1210597@qq.com','415929273','',0,'10002','10016','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动011','2016/6/13 10:00','2016/6/13 11:00','2016/6/5 19:00','其他活动地点11','103','552','334','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986084','1210598@qq.com','415929274','',0,'10002','10016','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动012','2016/6/14 10:00','2016/6/14 11:00','2016/6/6 19:00','其他活动地点12','661','577','243','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986085','1210599@qq.com','415929275','',0,'10001','10016','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动013','2016/6/15 10:00','2016/6/15 11:00','2016/6/7 19:00','其他活动地点13','305','561','255','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986086','1210600@qq.com','415929276','',0,'10002','10016','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'其他活动014','2016/6/16 10:00','2016/6/16 11:00','2016/6/8 19:00','其他活动地点14','15','578','480','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986087','1210601@qq.com','415929277','',0,'10001','10016','团学联体育部','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲001','2016/6/17 10:00','2016/6/17 11:00','2016/6/9 19:00','企业宣讲地点01','501','570','173','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986088','1210602@qq.com','415929278','',0,'10002','20011','同济舞鞋','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲002','2016/6/18 10:00','2016/6/18 11:00','2016/6/10 19:00','企业宣讲地点02','758','678','263','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986089','1210603@qq.com','415929279','',0,'10002','20011','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲003','2016/6/19 10:00','2016/6/19 11:00','2016/6/11 19:00','企业宣讲地点03','588','772','104','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986090','1210604@qq.com','415929280','',0,'10002','20011','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲004','2016/6/20 10:00','2016/6/20 11:00','2016/6/12 19:00','企业宣讲地点04','907','677','146','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986091','1210605@qq.com','415929281','',0,'10002','20011','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲005','2016/6/21 10:00','2016/6/21 11:00','2016/6/13 19:00','企业宣讲地点05','556','760','393','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986092','1210606@qq.com','415929282','',0,'10002','20011','建筑与城市规划学院','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲006','2016/6/22 10:00','2016/6/22 11:00','2016/6/14 19:00','企业宣讲地点06','826','717','16','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986093','1210607@qq.com','415929283','',0,'10002','20011','化学系','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲007','2016/6/23 10:00','2016/6/23 11:00','2016/6/15 19:00','企业宣讲地点07','173','765','33','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986094','1210608@qq.com','415929284','',0,'10001','20011','闻学堂','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲008','2016/6/24 10:00','2016/6/24 11:00','2016/6/16 19:00','企业宣讲地点08','785','661','143','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986095','1210609@qq.com','415929285','',0,'10001','20011','化学系','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲009','2016/6/25 10:00','2016/6/25 11:00','2016/6/17 19:00','企业宣讲地点09','504','781','376','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986096','1210610@qq.com','415929286','',0,'10001','20011','团学联体育部','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲010','2016/6/26 10:00','2016/6/26 11:00','2016/6/18 19:00','企业宣讲地点10','557','776','450','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986097','1210611@qq.com','415929287','',0,'10002','20011','同济舞鞋','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲011','2016/6/27 10:00','2016/6/27 11:00','2016/6/19 19:00','企业宣讲地点11','22','556','193','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986098','1210612@qq.com','415929288','',0,'10002','20011','电信学院研究生会','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'企业宣讲012','2016/6/28 10:00','2016/6/28 11:00','2016/6/20 19:00','企业宣讲地点12','409','788','393','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986099','1210613@qq.com','415929289','',0,'10001','20011','电信学院研究生会','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习001','2016/6/29 10:00','2016/6/29 11:00','2016/6/21 19:00','工作实习地点01','747','689','283','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986100','1210614@qq.com','415929290','',0,'10003','20012','公司01','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','测试');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习002','2016/6/30 10:00','2016/6/30 11:00','2016/6/22 19:00','工作实习地点02','423','727','488','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986101','1210615@qq.com','415929291','',0,'10003','20012','公司02','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','UI设计，平面设计');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习003','2016/7/1 10:00','2016/7/1 11:00','2016/6/23 19:00','工作实习地点03','458','592','173','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986102','1210616@qq.com','415929292','',0,'10003','20012','公司03','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','开发');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习004','2016/7/2 10:00','2016/7/2 11:00','2016/6/24 19:00','工作实习地点04','907','734','1','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986103','1210617@qq.com','415929293','',0,'10003','20012','公司04','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','数据库');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习005','2016/7/3 10:00','2016/7/3 11:00','2016/6/25 19:00','工作实习地点05','600','718','253','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986104','1210618@qq.com','415929294','',0,'10003','20012','公司05','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','算法工程师');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习006','2016/7/4 10:00','2016/7/4 11:00','2016/6/26 19:00','工作实习地点06','868','600','263','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986105','1210619@qq.com','415929295','',0,'10003','20012','公司06','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','销售，前台');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习007','2016/7/5 10:00','2016/7/5 11:00','2016/6/27 19:00','工作实习地点07','906','507','224','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986106','1210620@qq.com','415929296','',0,'10003','20012','公司07','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','测试');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习008','2016/7/6 10:00','2016/7/6 11:00','2016/6/28 19:00','工作实习地点08','541','787','1','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986107','1210621@qq.com','415929297','',0,'10003','20012','公司08','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','UI设计，平面设计');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习009','2016/7/7 10:00','2016/7/7 11:00','2016/6/29 19:00','工作实习地点09','801','596','39','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986108','1210622@qq.com','415929298','',0,'10003','20012','公司09','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','开发');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习010','2016/7/8 10:00','2016/7/8 11:00','2016/6/30 19:00','工作实习地点10','269','564','95','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986109','1210623@qq.com','415929299','',0,'10003','20012','公司10','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','数据库');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习011','2016/7/9 10:00','2016/7/9 11:00','2016/7/1 19:00','工作实习地点11','631','641','395','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986110','1210624@qq.com','415929300','',0,'10003','20012','公司11','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','算法工程师');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习012','2016/7/10 10:00','2016/7/10 11:00','2016/7/2 19:00','工作实习地点12','226','510','270','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986111','1210625@qq.com','415929301','',0,'10003','20012','公司12','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','销售，前台');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习013','2016/7/11 10:00','2016/7/11 11:00','2016/7/3 19:00','工作实习地点13','702','685','41','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986112','1210626@qq.com','415929302','',0,'10003','20012','公司13','','0'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','测试');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习014','2016/7/12 10:00','2016/7/12 11:00','2016/7/4 19:00','工作实习地点14','640','742','479','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986113','1210627@qq.com','415929303','',0,'10003','20012','公司14','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','UI设计，平面设计');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习015','2016/7/13 10:00','2016/7/13 11:00','2016/7/5 19:00','工作实习地点15','779','705','497','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986114','1210628@qq.com','415929304','',0,'10003','20012','公司15','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','开发');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'工作实习016','2016/7/14 10:00','2016/7/14 11:00','2016/7/6 19:00','工作实习地点16','163','796','363','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986115','1210629@qq.com','415929305','',0,'10003','20012','公司16','','1'
);
insert into job_extra_info (ID,Salary,Job_Info) values (LAST_INSERT_ID(),'','数据库');
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯01','2016/7/15 10:00','2016/7/15 11:00','2016/7/7 19:00','测试地点01','594','780','197','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986116','1210630@qq.com','415929306','',0,'10003','20013','发布1','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯02','2016/7/16 10:00','2016/7/16 11:00','2016/7/8 19:00','测试地点02','573','554','317','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986117','1210631@qq.com','415929307','',0,'10003','20013','发布2','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯03','2016/7/17 10:00','2016/7/17 11:00','2016/7/9 19:00','测试地点03','180','675','105','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986118','1210632@qq.com','415929308','',0,'10003','20013','发布3','','0'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯04','2016/7/18 10:00','2016/7/18 11:00','2016/7/10 19:00','测试地点04','311','622','189','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986119','1210633@qq.com','415929309','',0,'10003','20013','发布4','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯05','2016/7/19 10:00','2016/7/19 11:00','2016/7/11 19:00','测试地点05','455','566','482','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986120','1210634@qq.com','415929310','',0,'10003','20013','发布5','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯06','2016/7/20 10:00','2016/7/20 11:00','2016/7/12 19:00','测试地点06','232','653','460','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986121','1210635@qq.com','415929311','',0,'10003','20013','发布6','','1'
);
insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values ( 
'测试下咯07','2016/7/21 10:00','2016/7/21 11:00','2016/7/13 19:00','测试地点07','991','656','132','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986122','1210636@qq.com','415929312','',0,'10003','20013','发布7','','1'
);        
        
create procedure insertMultipleFakeData()
begin
	
declare fakeCount int;
declare i int;
set fakeCount = 10;
set i = 1;

	start transaction;
	
	while i < fakeCount do
	    
		insert sliders (Title,Act_ID,Image_Path,Link)
		values ('Test data',i,'http://ww2.sinaimg.cn/small/7b254335gw1f24wt3bb8wj21ao0q9afd.jpg','http://weibo.com');
			
		insert common_act_image(Act_ID,Image) 
        values (i,'http://ww2.sinaimg.cn/small/7b254335gw1f24wt3bb8wj21ao0q9afd.jpg');
		insert common_act_image(Act_ID,Image) 
        values (i,'http://ww2.sinaimg.cn/small/7b254335gw1f24wt3bb8wj21ao0q9afd.jpg');

        insert user_all(PSW,Area_Code,Ori_ID,Ori_Name,Nick_Name,Age,Major,Tel,Mail,Gender,Hdimg_Uri,Type_code)
		values ('',10001, CONCAT('1212', i), CONCAT('比利海灵顿',i), CONCAT('billy',i),40,'Aniki','110',CONCAT(i,'xxx@ggg.com'),0,
        'http://ww2.sinaimg.cn/small/7b254335gw1f24wt3bb8wj21ao0q9afd.jpg',0);
        
        insert user_act_clt(User_ID,Act_ID)
        values (i,i);
        
		set i = i+1;
	end while;
    
    insert sliders (Title,Act_ID,Image_Path,Link)
		values ('Test data',null,'http://ww2.sinaimg.cn/small/7b254335gw1f24wt3bb8wj21ao0q9afd.jpg','http://weibo.com');
    
	commit;

end #

delimiter ;

call insertMultipleFakeData();        

#---------------------------------


select * from job_extra_info;
select * from sliders;
select * from common_act_info;

#insert common_act_info(Title,Start_Date,End_Date,Pub_Time,Location,People_Number,NumRead,NumFavo,CovImg_Uri,Intro,CtPer_Tel,CtPer_Mail,CtPer_QQ,OutLink,Stat_Code,Area_Code,Act_Subtype,Organizer,Sponsor,Is_Reversed) values (
#'讲座报告001','2016/3/1 15:00','2016/3/1 17:00','2016/2/27 17:00','讲座报告地点1','804','608','356','http://115.28.68.32:8080/resources/images/gorilla.jpg','内容无','13262986020','1210525@qq.com','415929235','',0,'10002','10011','建筑与城市规划学院','','1'
#);
 



