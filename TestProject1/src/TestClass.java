import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClass {
	
	TestClass(){
		
		System.out.println(".....");
		
	}
	
	private void method1() {
		// TODO Auto-generated method stub
		
	}

	public String getipAddress() {
		System.out.println("in getipAddress Method");
		try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            String hostAddress = ipAddr.getHostAddress();
            System.out.println(ipAddr+"...ipAddr...");
            System.out.println(hostAddress+"...hostAddress...");
            return hostAddress;
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
          String systemException = ex.getMessage();
            return "Exception: "+systemException ;
        }
		
		
		
	}
	
	public static void main(String[] args) {
		System.out.println("Main Method start ");
		TestClass getIPAddress = new TestClass();
		String getipAddress2 = getIPAddress.getipAddress();
		System.out.println(getipAddress2+"...getipAddress2...");
		
	}

}
