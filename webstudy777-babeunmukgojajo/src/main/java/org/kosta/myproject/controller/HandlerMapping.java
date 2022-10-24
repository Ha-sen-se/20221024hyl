package org.kosta.myproject.controller;

public class HandlerMapping {
	private static HandlerMapping instance=new HandlerMapping();
	private HandlerMapping() {}
	public static HandlerMapping getInstance() {
		return instance;
	}
	public Controller create(String controllerName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		//String packageInfo=this.getClass().getPackage().getName();
		//System.out.println("packageInfo: "+packageInfo);
		//String과 StringBuilder 차이점: 자체가 변경되므로 자원을 절약할 수 있다.
		StringBuilder packageInfo=new StringBuilder(this.getClass().getPackage().getName());
		String classInfo=packageInfo.append(".").append(controllerName).toString();
		//System.out.println("classInfo: "+classInfo);
		return (Controller) Class.forName(classInfo).newInstance();
	}
	
}
