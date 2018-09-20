//package com.mcf.util;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.Stat;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ZooKeeperUtil {  
//	
//	@Value("${dubbo.registry.address}")
//	String zooKeeperAddress;
//	
//	public List<String> getZooKeeperNodeIpList(String groupNode) throws Exception { 
//		return getZooKeeperNodeIpList(zooKeeperAddress,groupNode);
//	}
//  
//    public static List<String> getZooKeeperNodeIpList(String zooKeeperAddress,String groupNode) throws Exception { 
//    	List<String> serverList = new ArrayList<String>();  
//    	
//    	Stat stat = new Stat();
//    	ZooKeeper zk = new ZooKeeper(zooKeeperAddress, 5000, new Watcher() {  
//            public void process(WatchedEvent event) {
//            	
//            }  
//        });  
//                
//        List<String> subList = zk.getChildren(groupNode, true);  
//        for (String subNode : subList) {  
//            // 获取每个子节点下关联的server地址  
//            byte[] data = zk.getData(groupNode + "/" + subNode, false, stat);  
//            serverList.add(new String(data, "utf-8"));  
//        }     
//        
//        return serverList; 
//    }
//    
////    public static List<String> getZooKeeperNodeIpList(Registry registry,String groupNode){
////		List<String> serverList = new ArrayList<String>();
////        try {
////			List<String> subList = registry.getChildren(groupNode);  
////			for (String subNode : subList) {  
////			    // 获取每个子节点下关联的server地址  
////			    PathData data = registry.getData(groupNode + "/" + subNode);  
////			    serverList.add(new String(data.getData(), "utf-8"));  
////			}
////		} catch (UnsupportedEncodingException e) {
////			e.printStackTrace();
////		} catch (RegistryException e) {
////			e.printStackTrace();
////		}
////        return serverList;
////    }
//  
//    public static void main(String[] args) throws Exception {  
//        System.out.println("server ip list: " + ZooKeeperUtil.getZooKeeperNodeIpList("192.168.2.18:2181","/retail_price/priceindex/executetask")); 
//        
//    }  
//}  
