/*import the txt file converted from excel, as id field in t_country_code
 is auto increment, use a view or a temp table to transfer data  */
create view v_country_code
as
select country_name,country_code
from t_country_code

bulk insert v_country_code
from 'D:\wei\projectDoc\Calling_Codes2.txt'
with(
FIRSTROW = 2,
fieldterminator = '\t',
rowterminator = '\n'
)
select * from t_country_code;
drop view v_country_code;

create table t_country_temp
(
country_name varchar(50),
country_code smallint,
PRIMARY KEY(country_code)
)

bulk insert dbo.t_country_temp
from 'D:\wei\projectDoc\Calling_Codes2.txt'
with(
FIRSTROW = 2,
fieldterminator = '\t',
rowterminator = '\n'
)
select * from t_country_temp;
/*delete from t_country_temp; */

INSERT INTO t_country_code(country_name, country_code) 
   SELECT country_name, country_code
   FROM t_country_temp
 
select * from t_country_code;


use billinng;

drop proc get_commission_detail

/*test sp for update rates*/
Create PROC get_commission_detail
@start_date datetime,
@end_date datetime,
@Return_Message VARCHAR(1024) = ''  OUT
AS
BEGIN
	set nocount on 
	
	DECLARE     @ErrorCode  int  
    --DECLARE     @ErrorStep  varchar(200)
    SELECT @ErrorCode = @@ERROR
    
    begin try
		truncate table t_month_commission_detail;
		--create a temporaty table to store t_customer_info join t_call_detail result
		create table #t_month_commission_temp_1
		(
			id int identity(1,1),
			full_name varchar(60) ,
			service_type varchar(40) ,
			salesrep_id int ,
			commission_level float,			
			src_phone_num varchar(50) ,
			dest_phone_num varchar(50) ,
			src_country_code int ,
			dest_country_code int ,
			duration int ,
			call_date datetime ,
			call_time smallint 	
		)
		
		
		Insert into #t_month_commission_temp_1
		SELECT t.full_name, t.service_type, t.salesrep_id, CONVERT(float, t.commission_level) as commission_level, 
		s.src_phone_num, s.dest_phone_num, s.src_country_code, 
		s.dest_country_code, s.duration, s.call_date, s.call_time
		FROM   t_customer_info t
		right JOIN   t_call_detail s ON t.phone_num = s.src_phone_num 
		--where s.call_date between '11/01/2013' and '11/30/2013'
		where s.call_date between @start_date and @end_date
		
		
		create table #t_month_commission_temp_2
		(
			id int identity(1,1),
			customer_full_name varchar(60) ,
			salesrep_id int,
			salesrep_name varchar(60) ,
			commission_level float,
			src_phone_num varchar(50) ,
			dest_phone_num varchar(50) ,
			service_type varchar(40) ,
			src_country_code int ,
			src_country_name varchar(50),
			dest_country_code int ,
			dest_country_name varchar(50) ,
			duration int ,
			call_date datetime ,
			call_time smallint ,
			off_peak_time smallint ,
			peak_time smallint ,
			peak float ,
			off_peak float ,
			cost float ,
			commission as cost * commission_level
		)
		
		--drop table #t_month_commission_temp_2
		--select * from #t_month_commission_temp_2

		--#t_month_call_temp join 
		Insert into #t_month_commission_temp_2
		 SELECT t.full_name as customer_full_name, t.salesrep_id, u.full_name as salesrep_name,
		  t.commission_level, t.src_phone_num, t.dest_phone_num, t.service_type, t.src_country_code, r.src_country_name, 
		  t.dest_country_code, r.dest_country_name, t.duration,
          t.call_date, t.call_time, CONVERT(smallint, s.off_peak_time) as off_peak_time, 
          CONVERT(smallint,s.peak_time) as peak_time, r.peak, r.off_peak,
          case
			--peak cost calculation
			when t.call_time >= peak_time and t.call_time <= off_peak_time 
				then round(cast(t.duration as float) /60  * peak, 2)
		    else
				round(cast(t.duration as float) / 60 * off_peak, 2)
		  end as cost
				     
		 FROM  #t_month_commission_temp_1 t
		 join t_service_info s on t.src_country_code = s.country_code and 
		 t.service_type = s.service_type
		 join t_rate_history r on  t.src_country_code = r.src_country_code
		 and t.service_type = r.service_type and t.dest_country_code = r.dest_country_code
		 and (t.call_date between r.effective_time and r.expire_time)
		 join t_user_info u on t.salesrep_id = u.id 
		 
	     --import commission table
	      insert into t_month_commission_detail( salesrep_id, salesrep_name, commission)
	     select  salesrep_id, salesrep_name, sum(commission)
	     from #t_month_commission_temp_2
	     group by salesrep_id,salesrep_name
	
	--for hibernate, return a reslut set
	select top 2 * from t_month_commission_detail ;	 		
	end try
		
	BEGIN CATCH 
	-- Get the Error Message for @@Error
		SELECT @ErrorCode = ERROR_NUMBER()
			-- , @Return_Message = @ErrorStep + ' '
			, @Return_Message = ' '
			+ cast(ERROR_NUMBER() as varchar(20)) + ' line: '
			+ cast(ERROR_LINE() as varchar(20)) + ' ' 
			+ ERROR_MESSAGE() + ' > ' 
			+ ERROR_PROCEDURE()

		RETURN @ErrorCode                               -- =0 if success,  <>0 if failure
	END CATCH	
end

/*test sp for bill calculation*/
Create PROC get_month_cost_detail
@start_date datetime,
@end_date datetime,
@Return_Message VARCHAR(1024) = ''  OUT
AS
BEGIN
	set nocount on 
	
	DECLARE     @ErrorCode  int  
    --DECLARE     @ErrorStep  varchar(200)
    SELECT @ErrorCode = @@ERROR
    
    begin try
		truncate table t_month_call_detail;
		--create a temporaty table to store t_customer_info join t_call_detail result
		--drop table #t_month_call_temp
		create table #t_month_call_temp
		(
			id int identity(1,1),
			full_name varchar(60) ,
			service_type varchar(40) ,
			src_phone_num varchar(50) ,
			dest_phone_num varchar(50) ,
			src_country_code int ,
			dest_country_code int ,
			duration int ,
			call_date datetime ,
			call_time smallint 	
		)
		
		Insert into #t_month_call_temp
		SELECT t.full_name, t.service_type, s.src_phone_num, s.dest_phone_num, s.src_country_code, 
				s.dest_country_code, s.duration, s.call_date, s.call_time
		FROM   t_customer_info t
	     right JOIN   t_call_detail s ON t.phone_num = s.src_phone_num 
		--where s.call_date between '11/01/2013' and '11/30/2013'
		where s.call_date between @start_date and @end_date
		
		--#t_month_call_temp join 
		Insert into t_month_call_detail(full_name, src_phone_num, dest_phone_num, service_type,
		src_country_code, src_country_name, dest_country_code, dest_country_name, duration,
		call_date, call_time, off_peak_time, peak_time, peak,off_peak, cost)
		 SELECT t.full_name, t.src_phone_num, t.dest_phone_num, t.service_type, t.src_country_code,
		  r.src_country_name, 
		  t.dest_country_code, r.dest_country_name, t.duration,
          t.call_date, t.call_time, CONVERT(smallint, s.off_peak_time) as off_peak_time, 
          CONVERT(smallint,s.peak_time) as peak_time, r.peak, r.off_peak,
          case
			--peak cost calculation
			when t.call_time >= peak_time and t.call_time <= off_peak_time 
				then round(cast(t.duration as float) /60  * peak, 2)
		    else
				round(cast(t.duration as float) / 60 * off_peak, 2)
		  end as cost
				     
		 FROM  #t_month_call_temp t
		 left join t_service_info s on t.src_country_code = s.country_code and 
		 t.service_type = s.service_type
		 left join t_rate_history r on  t.src_country_code = r.src_country_code
		 and t.service_type = r.service_type  and t.dest_country_code = r.dest_country_code
		 and (t.call_date between r.effective_time and r.expire_time)
	
	--for hibernate, return a reslut set
	select top 2 * from t_month_call_detail ;	 		
	end try
		
	BEGIN CATCH 
	-- Get the Error Message for @@Error
		SELECT @ErrorCode = ERROR_NUMBER()
			-- , @Return_Message = @ErrorStep + ' '
			, @Return_Message = ' '
			+ cast(ERROR_NUMBER() as varchar(20)) + ' line: '
			+ cast(ERROR_LINE() as varchar(20)) + ' ' 
			+ ERROR_MESSAGE() + ' > ' 
			+ ERROR_PROCEDURE()

		RETURN @ErrorCode                               -- =0 if success,  <>0 if failure
	END CATCH	
end

-- service traffic summary
Create PROC get_service_summary
@start_date datetime,
@end_date datetime,
@Return_Message VARCHAR(1024) = ''  OUT
AS
BEGIN
	set nocount on 
	
	DECLARE     @ErrorCode  int  
    SELECT @ErrorCode = @@ERROR
    
    begin try	
    
		--create a temporaty table to store t_customer_info join t_call_detail result
		create table #t_month_service_temp
		(
			id int identity(1,1),
			src_country_code int ,
			dest_country_code int ,
			service_type varchar(40) ,
			duration int
		)
		
		Insert into #t_month_service_temp
		SELECT  s.src_country_code, s.dest_country_code, t.service_type, s.duration
		FROM   t_customer_info t
		JOIN   t_call_detail s ON t.phone_num = s.src_phone_num 
		--where s.call_date between '12/01/2013' and '12/31/2013'
		where s.call_date between @start_date and @end_date
		
		--##t_month_service_temp join t_country_code to map country_name
		 create table #t_month_service_1
		(
			id int identity(1,1),
			src_country_code int not null,
			src_country_name varchar(50) not null,
			dest_country_code int not null,
			service_type varchar(40) not null,
			duration int not null
		)
		Insert into #t_month_service_1	
		SELECT c.country_code as src_country_code, c.country_name as src_country_name, m.dest_country_code,
		m.service_type, m.duration		     
		 FROM  t_country_code c
		 join #t_month_service_temp m on  c.country_code = m.src_country_code
		 
		--sum duration for specific service and country name
		Insert into t_month_service_summary(src_country_code, src_country_name, dest_country_code,
										dest_country_name, service_type, sum_duration)	
		SELECT m.src_country_code, m.src_country_name, m.dest_country_code, c.country_name as dest_country_name,
		m.service_type, sum(duration) as sum_duration		     
		 FROM  t_country_code c
		 join #t_month_service_1 m on  c.country_code = m.dest_country_code
		 group by m.src_country_code, m.src_country_name, m.dest_country_code, c.country_name, m.service_type
		
		
	--for hibernate, return a reslut set
	select top 2 * from t_month_service_summary ;	 		
	end try
		
	BEGIN CATCH 
	-- Get the Error Message for @@Error
		SELECT @ErrorCode = ERROR_NUMBER()
			-- , @Return_Message = @ErrorStep + ' '
			, @Return_Message = ' '
			+ cast(ERROR_NUMBER() as varchar(20)) + ' line: '
			+ cast(ERROR_LINE() as varchar(20)) + ' ' 
			+ ERROR_MESSAGE() + ' > ' 
			+ ERROR_PROCEDURE()

		RETURN @ErrorCode                               -- =0 if success,  <>0 if failure
	END CATCH	
end

/*test sp for commission calculation*/
Create PROC update_rate
@Return_Message VARCHAR(1024) = ''  OUT
AS
BEGIN
	set nocount on 
    --count t_rate_history records
    declare @count  as int
    declare @count_temp  as int
    SELECT @count = COUNT(1) FROM t_rate_history;
    SELECT @count_temp = COUNT(1) FROM t_rate_history_temp;
    if(@count_temp = 0)
    begin
		select @Return_Message = 'no records in t_rate_history_temp'
		--return;
	end
    
    declare @i as int
    declare @src_country_code as int
	declare @src_country_name as varchar(100)
	declare @dest_country_code as int
	declare @dest_country_name as varchar(100)
	declare @service_type as varchar(50)
	declare @peak as float
	declare @off_peak as float
	declare @effective_time as datetime
	
	DECLARE     @ErrorCode  int  
    --DECLARE     @ErrorStep  varchar(200)

    SELECT @ErrorCode = @@ERROR
    
    --if count == 0, insert all records into t_rate_history
    begin try
		begin tran
			if(@count = 0)
			begin
					set @i = 1;
					while(@i <= @count_temp)
					begin
						/*INSERT INTO t_rate_history(src_country, dest_country, sevice_type, peak, off_peak,effective_time) 
						SELECT src_country, dest_country, sevice_type, peak, off_peak,effective_time
						FROM t_rate_history_temp;
						*/	
						--look up src country code from src country name
						--look up des country name from dest country code	
						select @src_country_name = src_country_name,@dest_country_code = dest_country_code, @service_type = service_type,
						@peak = peak, @off_peak = off_peak, @effective_time = effective_time
						from t_rate_history_temp WHERE id = @i;
		
						select @src_country_code = country_code from t_country_code where country_name = @src_country_name;
						select @dest_country_name = country_name from t_country_code where country_code = @dest_country_code;
		
						--insert new rate 
						insert into t_rate_history(src_country_code, src_country_name,dest_country_code, dest_country_name, 
			                           service_type, peak, off_peak,effective_time) 
						values (@src_country_code, @src_country_name, @dest_country_code, @dest_country_name, @service_type, @peak, @off_peak, @effective_time);
			 	 
						set @i = @i + 1;	
					end				
			end
	
			/*update the record which has the same values of src_country, dest_country, sevice_type
			 as the record in t_rate_history_temp, using transaction*/
			else 
			begin	
				set @i = 1;
				while(@i <= @count_temp)
				begin
		
					--look up src country code from src country name
					--look up des country name from dest country code
					select @src_country_name = src_country_name,@dest_country_code = dest_country_code, @service_type = service_type,
					 @peak = peak, @off_peak = off_peak, @effective_time = effective_time
					 from t_rate_history_temp WHERE id = @i;
		
					select @src_country_code = country_code from t_country_code where country_name = @src_country_name;
					select @dest_country_name = country_name from t_country_code where country_code = @dest_country_code;
			 
					--update same service between same src and dest country
					update t_rate_history set expire_time = @effective_time where src_country_code = @src_country_code and
					dest_country_code = @dest_country_code and service_type = @service_type;
			
					--insert new rate 
					insert into t_rate_history(src_country_code, src_country_name,dest_country_code, dest_country_name, 
			                           service_type, peak, off_peak,effective_time) 
					values (@src_country_code, @src_country_name, @dest_country_code, @dest_country_name, @service_type, @peak, @off_peak, @effective_time);
			 
					set @i = @i + 1;
				end
			end
			--delete records in t_rate_history_temp
			truncate table t_rate_history_temp; 
		
		commit tran
    select top 2 * from t_rate_history ;
	end try
		
	BEGIN CATCH 
	-- Get the Error Message for @@Error
		IF @@TRANCOUNT > 0 ROLLBACK

		SELECT @ErrorCode = ERROR_NUMBER()
			-- , @Return_Message = @ErrorStep + ' '
			, @Return_Message = ' '
			+ cast(ERROR_NUMBER() as varchar(20)) + ' line: '
			+ cast(ERROR_LINE() as varchar(20)) + ' ' 
			+ ERROR_MESSAGE() + ' > ' 
			+ ERROR_PROCEDURE()

		RETURN @ErrorCode                               -- =0 if success,  <>0 if failure

	END CATCH
	
end
