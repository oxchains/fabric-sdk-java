新增包 org.hyperledger.fabric.sdk.NettyChannlPool
包下新增类   ChannelSource.java
		    CNManagedChannel.java
			ConnectionMaxAgeThread.java
			ConnectionPartition.java
			ConnectionTesterThread.java
			CustomThreadFactory.java
			ManagedChannelPool.java
			PoolWatchThread.java



EndPoint    
            新增方法     getHandle()      
                        用来获得连接的包装类ManagedChannelHandle 
						Endpoint(String url)    
						为了调用回收连接的方法	
                        closeManagedChannel(ManagedChannelHandle managedChannelHandle)
                        回收连接
			修改方法     Endpoint(String url, Properties properties)
			            改变连接的获取方式  由new  -》 get form pool



EventHub	
            修改方法     connect(final TransactionContext transactionContext)						
						改变连接的获取方式 
						 shutdown()
						 改变连接的关闭为回收




OrderClient    
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



Orderer     
            修改方法      sendTransaction
                         改变orderclient的获得方式
					     sendDeliver
					     改变orderclient的获得方式



peer        修改方法 	     sendProposalAsync(FabricProposal.SignedProposal proposal)
						 改变EndorserClient的获得方式   153行
						 shutdown 
                         改变连接的回收方式   


EndorserClient 
            新增方法      EndorserClient(ManagedChannelHandle managedChannelHandle)
						 shutdown(boolean force,String url)						 						 



配置文件规则
    文件名 ：channel.properties
     
    channel.pool.poolAvailabilityThreshold 当我们的可用连接达到最大连接数的百分之多少时维护链接数量  默认值20  也就是20%  	
	channel.pool.minConnectionsPerPartition  每一个端口（分区）的最小连接数    							不设置默认为 50
	channel.pool.maxConnectionsPerPartition  每一个端口（分区）的最大连接数    							不设置默认为 300 最大为1000
	channel.pool.incrConnectNum 每一个端口的连接数不够用时以多少个的数量扩展   		不设置默认为50   最大为300
	channel.pool.addr    连接的环境的ip   不设置默认为127.0.0.1 
	channel.pool.port  连接的端口可以有多个以","英文逗号分隔					 不设置默认为 7050,7051,7052,7053,7054,7055
	channel.pool.maxConnectionAgeInSeconds  一个连接的生命时间达到了多少就关闭该连接  不设置默认为 一天 60*60*24
	channel.pool.idleMaxAgeInSeconds    一个连接空闲多长时间后关闭该连接        不设置默认为一小时 60*60
