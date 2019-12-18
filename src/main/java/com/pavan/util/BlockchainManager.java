package com.pavan.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

import com.pavan.datastructure.BlockChainDatastructure;
import com.pavan.datastructure.BlockChainDatastructureImpl;
import com.pavan.model.ShipmentBean;

public class BlockchainManager {
	public static LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>> mainStream = null;
	
	public static boolean addToBlockChain(ShipmentBean bean) {
		initilizeMainStream();
		if (mainStream.get(bean.getShipmentRefNo() + bean.getHouseNo()) == null) {
			BlockChainDatastructure<ShipmentBean> datastructure = new BlockChainDatastructureImpl<>();
			datastructure.addFirst(bean);
			mainStream.put(bean.getShipmentRefNo() + bean.getHouseNo(), datastructure);
		} else {
			BlockChainDatastructure<ShipmentBean> datastructure = mainStream.get(bean.getShipmentRefNo() + bean.getHouseNo());
			datastructure.addLast(bean);
			mainStream.put(bean.getShipmentRefNo() + bean.getHouseNo(), datastructure);
		}
		return true;
	}

	private static void initilizeMainStream() {
		if (mainStream == null) {
			mainStream = new LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>>();
			BlockChainDatastructure<ShipmentBean> first = new BlockChainDatastructureImpl<ShipmentBean>();
			
			ShipmentBean bean = new ShipmentBean();
			bean.setShipmentRefNo("ShipmentRef1");
			bean.setHouseNo("House1");
			bean.setDestinationBranchDepartment("BOM");
			bean.setOriginBranchDepartment("HYD");
			bean.setPol_pod("POD");
			bean.setEta_etd("ETA" );
			bean.setShippers("SHIPPERS");
			bean.setConsinee("CONSINEE");
			first.addFirst(bean);
			
			ShipmentBean bean2 = new ShipmentBean();
			bean2.setShipmentRefNo("ShipmentRef1");
			bean2.setHouseNo("House1");
			bean2.setDestinationBranchDepartment("BOM");
			bean2.setOriginBranchDepartment("HYD");
			bean2.setPol_pod("POD1");
			bean2.setEta_etd("ETA1" );
			bean2.setShippers("SHIPPERS1");
			bean2.setConsinee("CONSINEE1");
			first.addLast(bean2);	
			mainStream.put("ShipmentRef1House1", first);
			
			
		}
	}
	
	public static LinkedHashMap<String, BlockChainDatastructure<ShipmentBean>> getMainStram() {
		initilizeMainStream();
		return mainStream;
	}

	public static Collection<ShipmentBean> getShipemntBean() {
		initilizeMainStream();
	    Set<String> keys =mainStream.keySet();
	    ArrayList<ShipmentBean> list = new ArrayList<>();
	    for(String key :keys) {
	    	BlockChainDatastructure <ShipmentBean> shipment=mainStream.get(key);
	    	ShipmentBean bean =shipment.iterateForward(1);
	    	list.add(bean);
	    }
		return list;
	}

	public static Collection<ShipmentBean> getShipemntBean(ShipmentBean bean) {
		initilizeMainStream();
		if (mainStream.get(bean.getShipmentRefNo() + bean.getHouseNo()) != null) {
			BlockChainDatastructure<ShipmentBean> dataStructure = mainStream.get(bean.getShipmentRefNo() + bean.getHouseNo());
			return dataStructure.getAllNodes();
		}
		return null;
	}
	
}
