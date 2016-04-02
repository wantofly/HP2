package com.kiwi.stripes.dao;


import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;


public class DB
{
	private static DB self = new DB();
	
	private SqlSessionFactory sqlSessionFactory;
	
	private DB()
	{
		try
		{
			this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis/mybatis-conf2.xml"));
//			this.sqlSessionFactory.getConfiguration().addMapper(UserDAO.class);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static SqlSession get()
	{
		return self.sqlSessionFactory.openSession();
	}
	
	public static SqlSession getST()
	{
		return self.sqlSessionFactory.openSession(TransactionIsolationLevel.SERIALIZABLE);
	}
	
	public static void commit(SqlSession session)
	{
		if(session != null)
			session.commit();
	}
	
	public static void rollback(SqlSession session)
	{
		if(session != null)
			session.rollback();
	}
	
	public static void close(SqlSession session)
	{
		if(session != null)
			session.close();
	}
	
}
