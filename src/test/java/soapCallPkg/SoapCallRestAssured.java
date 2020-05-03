package soapCallPkg;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
 
public class SoapCallRestAssured {
	
	@Test
	 public void PostCall () throws Exception {

		 File inputXML = new File(".\\xmlSource\\SoapPost.xml");
		String b="Palani";
		 BufferedReader br = null;
	        String newString = "Palani";
	        String newString2 = "Aditya";
	        StringBuilder strTotale = new StringBuilder();
	        try {
	        FileReader reader = new FileReader(inputXML);
	        String search = "CountryNameParamener";
	        br = new BufferedReader(reader);
	        while ((newString = br.readLine()) != null){
	            newString = newString.replaceAll(search, "US");
	            strTotale.append(newString);
	        }
	        } catch ( IOException  e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println(strTotale.toString());
	       	  //FileInputStream fileinputStream = new
			  //FileInputStream(".\\xmlSource\\SoapPost.xml");
		  
			  RestAssured.baseURI
			  ="http://webservices.oorsprong.org/websamples.countryinfo";
			  
			  
			  RestAssured.basePath = "./CountryInfoService.wso"; 
			  
			  Response resp =
			  given () .header("Content-Type", "text/xml") .and()
			//  .body(IOUtils.toString(fileinputStream, "UTF-8")) .when() .post() .then()
			  .body(strTotale.toString()) .when() .post() .then()
			  .statusCode(200) .and() .log().all().extract().response();	
			  
			  XmlPath xmlpath = new XmlPath(resp.asString());
			  
			  String countryName = xmlpath.getString("m:CountryNameResult"); 
			  
			  System.out.println(countryName);
			 
	}
	 

}
