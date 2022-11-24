package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(getBaseURI("baseURl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON)
				.build();
		}
		
		return req;
		
		
	}
	
	public String getBaseURI(String key) throws IOException
	{
		
		Properties p= new Properties();
		FileInputStream inpSt=new FileInputStream("C:\\Users\\Owner\\eclipse-workspace-RestAPI\\APIFramework2\\src\\test\\java\\Resources\\global.properties");
		p.load(inpSt);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response,String key)
	{
		String str=response.asString();
		JsonPath js= new JsonPath(str);
		return js.get(key).toString();
	}

}
