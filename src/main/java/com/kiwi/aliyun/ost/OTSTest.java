package com.kiwi.aliyun.ost;

import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.model.CapacityUnit;
import com.aliyun.openservices.ots.model.ColumnValue;
import com.aliyun.openservices.ots.model.Condition;
import com.aliyun.openservices.ots.model.CreateTableRequest;
import com.aliyun.openservices.ots.model.DeleteRowRequest;
import com.aliyun.openservices.ots.model.DeleteRowResult;
import com.aliyun.openservices.ots.model.GetRowRequest;
import com.aliyun.openservices.ots.model.GetRowResult;
import com.aliyun.openservices.ots.model.PrimaryKeyType;
import com.aliyun.openservices.ots.model.PrimaryKeyValue;
import com.aliyun.openservices.ots.model.PutRowRequest;
import com.aliyun.openservices.ots.model.PutRowResult;
import com.aliyun.openservices.ots.model.Row;
import com.aliyun.openservices.ots.model.RowDeleteChange;
import com.aliyun.openservices.ots.model.RowExistenceExpectation;
import com.aliyun.openservices.ots.model.RowPrimaryKey;
import com.aliyun.openservices.ots.model.RowPutChange;
import com.aliyun.openservices.ots.model.RowUpdateChange;
import com.aliyun.openservices.ots.model.SingleRowQueryCriteria;
import com.aliyun.openservices.ots.model.TableMeta;
import com.aliyun.openservices.ots.model.UpdateRowRequest;
import com.aliyun.openservices.ots.model.UpdateRowResult;

public class OTSTest 
{
	final static String endPoint = "http://hpdb01.cn-hangzhou.ots.aliyuncs.com";
	final static String accessId = "bDnm9ZNOMDrb9Ixc";
	final static String accessKey = "";
	final static String instanceName = "hpdb01";

	final static String COLUMN_GID_NAME = "gid";
	final static String COLUMN_UID_NAME = "uid";
	final static String COLUMN_NAME_NAME = "name";
	final static String COLUMN_ADDRESS_NAME = "address";
	final static String COLUMN_AGE_NAME = "age";
	final static String COLUMN_MOBILE_NAME = "mobile";
	final static String tableName = "myTable";
	
	public static void main(String[] args) {
		OTSClient client = new OTSClient(endPoint, accessId, accessKey, instanceName);
//		createTable(client);
//		putRow(client);
//		updRow(client);
		getRow(client);
		delRow(client);
	}
	
	public static void putRow(OTSClient client){

		RowPutChange rowChange = new RowPutChange(tableName);
		RowPrimaryKey primaryKey = new RowPrimaryKey();
		primaryKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(1));
		primaryKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(101));
		rowChange.setPrimaryKey(primaryKey);
		rowChange.addAttributeColumn(COLUMN_NAME_NAME, ColumnValue.fromString("张三"));
		rowChange.addAttributeColumn(COLUMN_MOBILE_NAME, ColumnValue.fromString("111111111"));
		rowChange.addAttributeColumn(COLUMN_ADDRESS_NAME, ColumnValue.fromString("中国A地"));
		rowChange.addAttributeColumn(COLUMN_AGE_NAME, ColumnValue.fromLong(20));
		rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST)); 

		PutRowRequest request = new PutRowRequest();
		request.setRowChange(rowChange);

		PutRowResult result = client.putRow(request);
		int consumedWriteCU = result.getConsumedCapacity().getCapacityUnit().getWriteCapacityUnit();

		System.out.println("成功插入数据, 消耗的写CapacityUnit为：" + consumedWriteCU);
	}
	public static void updRow(OTSClient client)
	{
		RowUpdateChange rowChange = new RowUpdateChange(tableName);
		RowPrimaryKey primaryKeys = new RowPrimaryKey();
		primaryKeys.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(1));
		primaryKeys.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(101));
		rowChange.setPrimaryKey(primaryKeys);
		// 更新以下三列的值
		rowChange.addAttributeColumn(COLUMN_NAME_NAME, ColumnValue.fromString("张三"));
		rowChange.addAttributeColumn(COLUMN_ADDRESS_NAME, ColumnValue.fromString("中国B地"));
		// 删除mobile和age信息
		rowChange.deleteAttributeColumn(COLUMN_MOBILE_NAME);
		rowChange.deleteAttributeColumn(COLUMN_AGE_NAME);

		rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_EXIST));

		UpdateRowRequest request = new UpdateRowRequest();
		request.setRowChange(rowChange);

		UpdateRowResult result = client.updateRow(request);
		int consumedWriteCU = result.getConsumedCapacity().getCapacityUnit().getWriteCapacityUnit();

		System.out.println("成功更新数据, 消耗的写CapacityUnit为：" + consumedWriteCU);
	}
	public static void getRow(OTSClient client)
	{
		SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(tableName);
		RowPrimaryKey primaryKeys = new RowPrimaryKey();
		primaryKeys.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(1));
		primaryKeys.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(101));
		criteria.setPrimaryKey(primaryKeys);
		criteria.addColumnsToGet(new String[] {
		        COLUMN_NAME_NAME,
		        COLUMN_ADDRESS_NAME,
		        COLUMN_AGE_NAME
		});

		GetRowRequest request = new GetRowRequest();
		request.setRowQueryCriteria(criteria);
		GetRowResult result = client.getRow(request);
		Row row = result.getRow();

		int consumedReadCU = result.getConsumedCapacity().getCapacityUnit().getReadCapacityUnit();
		System.out.println("本次读操作消耗的读CapacityUnti为：" + consumedReadCU);
		System.out.println("name信息：" + row.getColumns().get(COLUMN_NAME_NAME));
		System.out.println("address信息：" + row.getColumns().get(COLUMN_ADDRESS_NAME));
		System.out.println("age信息：" + row.getColumns().get(COLUMN_AGE_NAME));
	}
	public static void delRow(OTSClient client)
	{
		RowDeleteChange rowChange = new RowDeleteChange(tableName);
		RowPrimaryKey primaryKeys = new RowPrimaryKey();
		primaryKeys.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(1));
		primaryKeys.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(101));
		rowChange.setPrimaryKey(primaryKeys);

		DeleteRowRequest request = new DeleteRowRequest();
		request.setRowChange(rowChange);

		DeleteRowResult result = client.deleteRow(request);
		int consumedWriteCU = result.getConsumedCapacity().getCapacityUnit().getWriteCapacityUnit();

		System.out.println("成功删除数据, 消耗的写CapacityUnit为：" + consumedWriteCU);
	}
	public static void createTable(OTSClient client){

		String tableName = "myTable";
		String COLUMN_GID_NAME = "gid";
		String COLUMN_UID_NAME = "uid";

		TableMeta tableMeta = new TableMeta(tableName);
		tableMeta.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyType.INTEGER);
		tableMeta.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyType.INTEGER);
		// 将该表的读写CU都设置为100
		CapacityUnit capacityUnit = new CapacityUnit(0, 0);

		CreateTableRequest request = new CreateTableRequest();
		request.setTableMeta(tableMeta);
		request.setReservedThroughput(capacityUnit);
		client.createTable(request);

		System.out.println("表已创建");
	}
	
	
}
