**新增包** 
            NettyChannlPool
**新增类**   
            ChannelSource.java
		    CNManagedChannel.java
			ConnectionMaxAgeThread.java
			ConnectionPartition.java
			ConnectionTesterThread.java
			CustomThreadFactory.java
			ManagedChannelPool.java
			PoolWatchThread.java

修改内容

**EndPoint**    
            新增方法     getHandle()      
                        用来获得连接的包装类ManagedChannelHandle 
						Endpoint(String url)    
						为了调用回收连接的方法	
                        closeManagedChannel(ManagedChannelHandle managedChannelHandle)
                        回收连接
			修改方法     Endpoint(String url, Properties properties)
			            改变连接的获取方式  由new  -》 get form pool



**EventHub**	
            修改方法     connect(final TransactionContext transactionContext)						
						改变连接的获取方式 
						 shutdown()
						 改变连接的关闭为回收




**OrderClient**    
                        取消成员变量  ManagedChannelBuilder channelBuilder;
                        新增变量     ManagedChannelHandle managedChannelHandle;   
		    
		    修改方法       OrdererClient(Orderer orderer, ManagedChannelBuilder<?> channelBuilder, Properties properties)
		                 为OrdererClient(Orderer orderer, ManagedChannelHandle managedChannelHandle)
		                        
						 shutdown(boolean force) 
						 改变回收连接方式    
						 sendTransaction
						 改变连接获得方式           
						 sendDeliver
						 改变连接获得方式 



**Orderer**     
            修改方法      sendTransaction
                         改变orderclient的获得方式
					     sendDeliver
					     改变orderclient的获得方式



**peer**       
            修改方法 	     sendProposalAsync(FabricProposal.SignedProposal proposal)
						 改变EndorserClient的获得方式   153行
						 shutdown 
                         改变连接的回收方式   


**EndorserClient** 
            新增方法      EndorserClient(ManagedChannelHandle managedChannelHandle)
						 shutdown(boolean force,String url)						 						 



**配置文件规则**
    文件名 ：channel.properties，放在项目的第一级下
     
    #------If the value is 20, maintain the number of links when our available connections reach 20 percent of the maximum number of connections
    #channel.pool.poolAvailabilityThreshold=20
    
    #------Minimum number of connections per partition
    #channel.pool.minConnectionsPerPartition=20
    
    #------Maxmum number of connections per partition
    #channel.pool.maxConnectionsPerPartition=100
    
    #------The growth level of the number of connections per partition
    #channel.pool.incrConnectNum=20
    
    #------Gets the timeout of the connection from the connection pool, in seconds
    #channel.pool.connectionTimeoutInMs=2
    
    #------Describes the connection max alive time ,in seconds
    #channel.pool.maxConnectionAgeInSeconds=86400
    
    #------Describes the connection max idle time ,in seconds
    #channel.pool.idleMaxAgeInSeconds=3600
    
    #------Configure the connection pool to maintain the connection port and IP information
    #channel.pool.coreConnect=192.168.116.145:7051,192.168.116.145:7052,192.168.116.145:7053,192.168.116.145:7054,192.168.116.145:7050
    #coreConnect=192.168.116.145:7051,192.168.116.145:7052,192.168.116.145:7053,192.168.116.145:7054,192.168.116.145:7050