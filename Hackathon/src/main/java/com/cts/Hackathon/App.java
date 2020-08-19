package com.cts.Json;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.*;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.*;

public class Cpu{
	

	public static void main(String args[]) throws Exception
	{
		FileReader fr= new FileReader("D:\\CPU.txt");
		BufferedReader br=new BufferedReader(fr);
		String f;
		List<String> list=new ArrayList<String>();
		while((f=br.readLine())!=null)
		{
			
			String[] c=f.split(" ");
			//System.out.println(c[14]);
			list.add(c[14]);
		}
		 writeJsonSimpleDemo("abcd.json",list);
	}
	
	
	public static void writeJsonSimpleDemo(String name,List<String> array)throws Exception
	{
		JSONArray v=new JSONArray();
		JSONObject sampletransaction=new JSONObject();
		JSONObject r=new JSONObject();
		
		int a=1;
		for(String s:array) {
			if(s!="")
			{
		    	 JSONObject so = new JSONObject(); 

				so.put(a+"s",s);
				v.add(so);
				a=a+1;
			}
		}
		List<Double> n=new ArrayList<Double>();
		double total=0;
		for(String str:array)
		{
			if(NumberUtils.isNumber(str)) {
				n.add(Double.parseDouble(str));
				total=total+Double.parseDouble(str);
				
			}
		}
		
		double max=Collections.max(n);
		double average=total/(n.size());
		
		double roundOffMax = Math.round(max * 100.0) / 100.0;
    	double roundOffAvg = Math.round(average * 100.0) / 100.0;
    	
    	sampletransaction.put("values", v);
    	sampletransaction.put("maxcpu", roundOffMax );
    	sampletransaction.put("avgcpu", roundOffAvg );
    	r.put("sampletransaction", sampletransaction);
    	System.out.println(r);
    	
	    Files.write(Paths.get(name),r.toJSONString().getBytes());
	}
}
