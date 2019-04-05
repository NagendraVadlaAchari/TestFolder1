package com.java;
import java.util.Hashtable;

import javax.naming.*;
import javax.naming.ldap.*;
import javax.naming.directory.*;

public class test1 {
	
	
	public void  testMethod() {
		
		
			
			try {
				
				 Hashtable<String, String> environment = new Hashtable<String, String>();
		//java.naming.security.credentials
			        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			        environment.put(Context.PROVIDER_URL, "ldap://192.168.5.185.:389");//if using SSL - 636; else 389  192.168.5.185 / 58
			        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			        environment.put(Context.SECURITY_PRINCIPAL, "ABNL\\sr102384");//    sr102384  Birla$456
			        environment.put(Context.SECURITY_CREDENTIALS, "Birla$456");//MGp8user$017

			        try 
			        {
			            DirContext context = new InitialDirContext(environment);
			            System.out.println("Connected..");
			            System.out.println(context.getEnvironment());
			            context.close();
			        } 
			        catch (AuthenticationNotSupportedException exception) 
			        {
			            System.out.println("The authentication is not supported by the server");
			        }

			        catch (AuthenticationException exception)
			        {
			            System.out.println("Incorrect password or username");
			        }

			        catch (NamingException exception)
			        {
			            System.out.println("Error when trying to create the context");
			        }		

			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public static void main(String[] args) {
		
		test1 test1 = new test1();
		System.out.println("test method started..!");
		//test1.testMethod();
		
	}
	

}
