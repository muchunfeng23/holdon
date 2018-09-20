package com.mcf.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerInfoUtil {

    private static final String IPADDRESS_PATTERN = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";

    private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);

    private static String LOCAL_HOST_IP = null;

    public static String getServerIP(){
        if (LOCAL_HOST_IP != null) return LOCAL_HOST_IP;
        Enumeration<NetworkInterface> interfaces = null;
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface face = interfaces.nextElement();
                if (face.isLoopback()) continue;
                Enumeration<InetAddress> inetAddresses = face.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress address = inetAddresses.nextElement();
                    Matcher matcher = pattern.matcher(address.toString());
                    if (matcher.find() && matcher.group().length() > 0) {
                        LOCAL_HOST_IP = matcher.group();
                        return LOCAL_HOST_IP;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "0.0.0.0";
	}
	
	public static String getServerHostname(){
		String hostname = null;
        try {

            InetAddress inetAddr = InetAddress.getLocalHost();
            hostname = inetAddr.getHostName();

        }catch (UnknownHostException e) {
            System.out.println("Host not found: " + e.getMessage());
        }
        return hostname;
	}
	
	public static void main(String[] args){
		System.out.println(getServerIP());
	}
}
