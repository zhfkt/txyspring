CREATE SCHEMA txyspring;

use txyspring;

CREATE TABLE SLIDERS (
 ID INT NOT NULL AUTO_INCREMENT,
 TITLE VARCHAR (100) NOT NULL,
 IMAGE TEXT NOT NULL,
 PRIMARY KEY ( ID )
);


# --------------------------------



#---------------------------------
# test data insert

insert sliders(TITLE,IMAGE) 
values ("Test data","http://tp3.sinaimg.cn/2424186582/180/5746253598/1");

insert sliders(TITLE,IMAGE) 
values ("Test data 2","https://pic2.zhimg.com/ec0128df835b2ffaba6d50771c875545_b.png");
	

# select * from sliders;



