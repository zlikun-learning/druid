CREATE TABLE TBL_USER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT ,
    NAME VARCHAR(64) NOT NULL COMMENT '用户姓名' ,
    CTIME DATETIME NOT NULL COMMENT '创建时间'
) ;