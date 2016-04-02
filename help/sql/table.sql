ttable
基本用户表：用户注册登陆，推荐码，控制电脑绑定个数       
User    Type    
user_id Varchar(50) 
user_name   Varchar(100)    
password    Varchar(100)    
mobile  Varchar(50) 
email   Varchar(100)    
expiry_day  date    
recommend_code  Varchar(50) 
recommend_from  Varchar(50) 
session_status? Varchar(1)  Only allow one pc login from software
status  Varchar(1)  "0: not verified  1: verified 2: frozon due to psw wrong?"
create_ts   date    
create_by   Varchar(50) 
update_ts   date    
update_by   Varchar(50) 
 
store in table or memory?       
手机验证码：登陆/注册/忘记密码        
Mobile_verification     Type
mobile  Varchar(50)
verify_code     Varchar(10)
active_ind      Varchar(1)
create_ts       date
create_by       Varchar(50)
update_ts       date
update_by       Varchar(50)
 
订单信息：包括推荐码， 产品类型，付款日和过期日                
Payment Type    
payment_id      Varchar(50)     
user_id Varchar(50)     
payday  date    
product_type    Varchar(2)      "1: one month usage 2: one quarter usage"
payment Decimal(10,2)   
status  Varchar(1)      "0: pending 1: success"
recommend_from  Varchar(50)     
expiry_day      date    
create_ts       date    
create_by       Varchar(50)     
update_ts       date    
update_by       Varchar(50)     
 
点数进出表：包括返点记录和提现记录               
Point_Journal   Type    
point_journal_id        Varchar(50)     
user_id Varchar(50)     
journal_type    Varchar(1)      "1: Deposit 2: Withdraw"
payment_id      Varchar(50)     
value_day       date    
point   Decimal(10,2)   
status  Varchar(1)      "0: pending 1: success"
create_ts       date    
create_by       Varchar(50)     
update_ts       date    
update_by       Varchar(50)     
 