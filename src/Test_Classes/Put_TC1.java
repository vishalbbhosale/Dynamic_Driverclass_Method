package Test_Classes;

import java.io.IOException;
import java.time.LocalDateTime;

import org.testng.Assert;

import Common_API_Method.Put_Api_Method;
import Req_Repository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC1 {
	
	
public static void extractor() throws IOException  {
		
		for(int i=0;i<5;i++) {
		int statuscode=Put_Api_Method.responsestatuscode(Put_Req_Repository.baseURI(),Put_Req_Repository.Put_Resourse(),Put_Req_Repository.Put_TC1());
		System.out.println(statuscode);
		
		if(statuscode==200) {
			
			String Responsebody=Put_Api_Method.Responsebody(Put_Req_Repository.baseURI(),Put_Req_Repository.Put_Resourse(),Put_Req_Repository.Put_TC1());
			System.out.println(Responsebody);
			
			String Requestbody=Put_Req_Repository.Put_TC1();
			
			validator(Requestbody,Responsebody);
			
			break;}
		else {
			System.out.println("status code is invalid");
			
		}
		
		}
	}
     public static void validator(String Requestbody,String Responsebody) {
    	 
    		
			
			//parse req body
			JsonPath jspRequest=new JsonPath(Requestbody);
			String Req_name=jspRequest.getString("name");
			String Req_job=jspRequest.getString("job");
			LocalDateTime currentdate=LocalDateTime.now();
			String expecteddate=currentdate.toString().substring(0,11);
			
			//parse response body
			JsonPath jspResponse=new JsonPath(Responsebody);
			String Res_name=jspResponse.getString("name");
			String Res_job=jspResponse.getString("job");
			String Res_updatedAt=jspResponse.getString("updatedAt");
			Res_updatedAt=Res_updatedAt.substring(0,11);
			
			
			//validate response parameter
			Assert.assertEquals(Res_name, Req_name);
			Assert.assertEquals(Res_job, Req_job);
			Assert.assertEquals(Res_updatedAt, expecteddate);
		
    	 
     }


}
