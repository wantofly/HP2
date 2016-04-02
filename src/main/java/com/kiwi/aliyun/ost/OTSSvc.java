package com.kiwi.aliyun.ost;

import com.aliyun.openservices.ots.OTSClient;

public class OTSSvc 
{
	final static String endPoint = "http://hpdb01.cn-hangzhou.ots.aliyuncs.com";
	final static String accessId = "bDnm9ZNOMDrb9Ixc";
	final static String accessKey = "";
	final static String instanceName = "hpdb01";
	
	public static OTSClient client = new OTSClient(endPoint, accessId, accessKey, instanceName);
	
}
