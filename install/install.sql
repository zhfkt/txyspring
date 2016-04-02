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
	ON UPDATE CASCADE	    
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
values (10011,'同济大学嘉定校区');

insert sch_code(Area_Code,Description)
values (10012,'同济大学四平路校区');

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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '内容无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10012,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '内容无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10012,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '人间四月草长莺飞，又到一年樱花烂漫纷飞时节，如云似霞。花团锦簇的同济校园里，飘落的樱花如飞舞的精灵，划过发梢，绕过裙角，香了风，也香了伊人裳……花能解语，亦能通过诗词、歌赋来传情达意。美的无可方物的樱花，被多少诗人赞许、爱怜，化作句句字里行间那轻描淡写的流年的诗篇。',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10012,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '暂无',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10012,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '篮球大赛',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10011,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '跳舞吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10011,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '招聘吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10011,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
        '招聘吧少年',
		'1111',
        '1210525@qq.com',
        '415929235',
        '',
        1,
        10011,
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
		'https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png',
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

insert into job_extra_info (ID,Salary,Job_Info) 
values (LAST_INSERT_ID(),
		'',
        '测试、数据库设计');
        
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
		values ('',10011, CONCAT('1212', i), CONCAT('比利海灵顿',i), CONCAT('billy',i),40,'Aniki','110',CONCAT(i,'xxx@ggg.com'),0,
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
#select * from sliders;
#select * from common_act_info;

 



