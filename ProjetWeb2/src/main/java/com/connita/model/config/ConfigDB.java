package com.connita.model.config;


public interface ConfigDB {
	public final static String URL = "jdbc:mysql://127.0.0.1:3306/mydb?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";      
        public final static String USER ="root";
        public final static String PASSWORD ="root";
        public final static String DRIVER ="com.mysql.cj.jdbc.Driver";
}
