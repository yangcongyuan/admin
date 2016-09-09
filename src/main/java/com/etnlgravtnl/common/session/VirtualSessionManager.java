package com.etnlgravtnl.common.session;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class VirtualSessionManager {
	
	private Map<String, VirtualSession> sessions=new HashMap<String, VirtualSession>();
	private static VirtualSessionManager instance;
	
	private VirtualSessionManager(){
		
	}
	
	public static VirtualSessionManager getInstance(){
		if (instance==null)
			instance=new VirtualSessionManager();
		return instance;
	}
	
	public VirtualSession getSession(String key, boolean create){
		VirtualSession session =sessions.get(key);
		if (session==null && create) {
			session=new VirtualSession(key);
			sessions.put(key, session);
		}
		
		return session;
	}

	public static void dumpSession() {
		Gson gson=new Gson();
		System.out.println(gson.toJson(getInstance()));
		
	}
}
