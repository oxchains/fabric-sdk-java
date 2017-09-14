package org.hyperledger.fabric.sdk.ManageChannelPool;

public class test {
   public static void main(String[] args) throws InterruptedException {
   try {
	while (true) {
		   ManagedChannelHandle handle = ManagedChannelPool.getConnection("192.168.116.145", "7051");
		   System.out.println(handle);
	   }
} catch (Exception e) {
	e.printStackTrace();
}		
	
}
   
}
