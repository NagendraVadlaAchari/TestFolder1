/*package com.java;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import org.apache.log4j.Logger;

import com.kpmg.rekonnect.constants.RekonnectConstants;
import com.kpmg.rekonnect.exception.KPMGException;
import com.kpmg.rekonnect.resourceloader.ResourceLoader;
import com.kpmg.rekonnect.util.DBConnectionUtil;

*//**
 * Contains methods to execute Re-konnect procedures/views
 * @author MITS
 *
 *//*
public class FAGSTTestInvoiceProcessing {

	private Logger logger=Logger.getLogger(FAGSTTestInvoiceProcessing.class);
	private static ResourceLoader loader = ResourceLoader.getDBPropInstance(RekonnectConstants.WASCONFIGURED_PROPERTIES_FILENAME);

	private Connection rekonnectCon = null;
	private DBConnectionUtil dbUtil = null;
	boolean isDebugLevel = false;

	public FAGSTTestInvoiceProcessing() throws KPMGException{
		dbUtil = new DBConnectionUtil();
		rekonnectCon = dbUtil.connectToReKonnectDatabase();
		isDebugLevel = logger.isDebugEnabled();
	}


	*//**
	 * Executes the procedure with given input and out parameters
	 * @param procedureName
	 * @param propertyNames
	 * @param propertyValues
	 * @param dataTypes
	 * @param outParamDataTypes
	 * @param outParamCount
	 * @return out values array
	 * @throws Exception
	 *//*
	public void  invoice_Processing() throws Exception {



		try
		{

			CallableStatement stmt = rekonnectCon.prepareCall("call APPS.XXKPMG_BPM_FA_INV_IN_PKG.XXKPMG_FA_BPM_MAIN(?,?,?,?,?,?,?,?)");
			StructDescriptor recDescriptorInvRecType = StructDescriptor.createDescriptor("APPS.INVOICE_REC_TYPE", rekonnectCon);
			ArrayDescriptor arrayDescriptorInvRecType = ArrayDescriptor.createDescriptor("APPS.LCU_INV_RECORDS_TBL_TYPE", rekonnectCon);


			StructDescriptor recDescriptorPrepaidInv = StructDescriptor.createDescriptor("APPS.PREPAID_INVOICE_REC_TYPE", rekonnectCon);
			ArrayDescriptor arrayDescriptorPrepaidInv = ArrayDescriptor.createDescriptor("APPS.LCU_PRPDINV_RECORDS_TBL_TYPE", rekonnectCon);



			StructDescriptor recDescriptorInv_revtax = StructDescriptor.createDescriptor("APPS.INVOICE_REVTAX_TYPE", rekonnectCon);
			ArrayDescriptor arrayDescriptorInv_revtax = ArrayDescriptor.createDescriptor("APPS.LCU_REVTAX_RECORDS_TBL_TYPE", rekonnectCon);



			StructDescriptor recDescriptorPrepay_inv = StructDescriptor.createDescriptor("APPS.PREPAY_INVOICE_REC_TYPE", rekonnectCon);
			ArrayDescriptor arrayDescriptorPrepay_inv = ArrayDescriptor.createDescriptor("APPS.LCU_PREPAYINV_REC_TBL_TYPE", rekonnectCon);

			// Stage values for each field in the Oracle record in an array
			Object[] invRecArray = new Object[42];
			Object[] invitemsArray = new Object[1];

			// Stage values for each field in the Oracle record in an array
			Object[]  prepaidInvRecArray = new Object[4];
			Object[] prepInvitemsArray = new Object[1];

			Object[]  revInvRecArray = new Object[6];
			Object[] revInvitemsArray = new Object[1];


			Object[]  prepayInvRecArray = new Object[22];
			Object[] prepayInvitemsArray = new Object[1];

//						
			//for multiple records, comment starts
			Object[] multipleInvRecArray = new Object[2];
			STRUCT oracleInvRec = null;
			//for (int i = 0; i < 3; i++) {
				invitemsArray =   getStandardInvoiceLine1();
				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
				multipleInvRecArray[0] = oracleInvRec;
				
				invitemsArray =   getStandardDistribLine1();
				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
				multipleInvRecArray[1] = oracleInvRec;
				
//				invitemsArray =   getStandardInvoiceLine2();
//				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
//				multipleInvRecArray[1] = oracleInvRec;
//				
//				invitemsArray =   getStandardInvoiceLine3();
//				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
//				multipleInvRecArray[2] = oracleInvRec;
//				
//				invitemsArray =   getStandardDistribLine1();
//				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
//				multipleInvRecArray[1] = oracleInvRec;
				
//				invitemsArray =   getStandardDistribLine2();
//				oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invitemsArray);
//				multipleInvRecArray[2] = oracleInvRec;
//				
			//}
			ARRAY invArray = new ARRAY(arrayDescriptorInvRecType, rekonnectCon, multipleInvRecArray);
			//for multiple records, comment ends
			
			//for multiple records, prepay comment starts
			Object[] multiplePrepayArray = new Object[1];
			STRUCT oraclePrepay = null;
			//prepayInvRecArray=   getPrepayDetails();
				oraclePrepay = new STRUCT(recDescriptorPrepay_inv , rekonnectCon , prepayInvRecArray);
				multiplePrepayArray[0] = oraclePrepay;

			ARRAY prepayInvArray = new ARRAY(arrayDescriptorPrepay_inv, rekonnectCon, multiplePrepayArray);
			//for multiple records, comment ends
			
			
			
			
//			// Cast the Invoice Rec type java array into the oracle record type
//			STRUCT oracleInvRec = new STRUCT(recDescriptorInvRecType , rekonnectCon , invRecArray);
//			invitemsArray[0] = oracleInvRec;
//			//  itemsArray[1] = oracleInvRec;
//			ARRAY invArray = new ARRAY(arrayDescriptorInvRecType, rekonnectCon, invitemsArray);
//			// Bind Values to the IN parameter


			// Cast the Prepaid  Invoice Rec type java array into the oracle record type
			//prepaidInvRecArray = getPrepaidDetails();
			STRUCT oraclePrepaidInvInvRec = new STRUCT(recDescriptorPrepaidInv , rekonnectCon , prepaidInvRecArray);
			prepInvitemsArray[0] = oraclePrepaidInvInvRec;
			//  itemsArray[1] = oracleInvRec;
				
			ARRAY prepaidInvArray = new ARRAY(arrayDescriptorPrepaidInv, rekonnectCon, prepInvitemsArray);
			// Bind Values to the IN parameter

			
			// Cast the reverse  Invoice Rec type java array into the oracle record type
			//STRUCT oracleReverseInvRec = new STRUCT(recDescriptorInv_revtax , rekonnectCon , revInvRecArray);
			//revInvitemsArray[0] = oracleReverseInvRec;
			//ARRAY revInvArray = new ARRAY(arrayDescriptorInv_revtax, rekonnectCon, revInvitemsArray);

			//  itemsArray[1] = oracleInvRec;
			//for reverse charge tax
			Object[] multipleRevChargeArray = new Object[3];
			STRUCT oracleReverseInvRec = null;
			oracleReverseInvRec = new STRUCT(recDescriptorInv_revtax , rekonnectCon , revInvRecArray);
			multipleRevChargeArray[0] = oracleReverseInvRec;
//			revInvRecArray =   getReverseChargeDetails1();
//				oracleReverseInvRec = new STRUCT(recDescriptorInv_revtax , rekonnectCon , revInvRecArray);
//				multipleRevChargeArray[0] = oracleReverseInvRec;
//				
//				revInvRecArray =   getReverseChargeDetails2();
//				oracleReverseInvRec = new STRUCT(recDescriptorInv_revtax , rekonnectCon , revInvRecArray);
//				multipleRevChargeArray[1] = oracleReverseInvRec;
//				
//				revInvRecArray =   getReverseChargeDetails3();
//				oracleReverseInvRec = new STRUCT(recDescriptorInv_revtax , rekonnectCon , revInvRecArray);
//				multipleRevChargeArray[2] = oracleReverseInvRec;
				
			ARRAY revInvArray = new ARRAY(arrayDescriptorInv_revtax, rekonnectCon, multipleRevChargeArray);
			// Bind Values to the IN parameter
			//Till here for rev charge

//			// Cast the reverse  Invoice Rec type java array into the oracle record type
//			STRUCT oraclePrepayInvRec = new STRUCT(recDescriptorPrepay_inv , rekonnectCon,prepayInvRecArray);
//			//prepayInvitemsArray[0] = oraclePrepayInvRec;
//			//  itemsArray[1] = oracleInvRec;
//			ARRAY prepayInvArray = new ARRAY(arrayDescriptorPrepay_inv, rekonnectCon, prepayInvitemsArray);
//			// Bind Values to the IN parameter


			ARRAY temp = null;
			
			stmt.setObject(1, invArray);
			
			stmt.setObject(2,null);
			//stmt.registerOutParameter(2, java.sql.Types.ARRAY);
			
			stmt.setObject(3,null);
			//stmt.registerOutParameter(3, java.sql.Types.ARRAY);
			
			stmt.setObject(4,null);
			stmt.setObject(2,prepayInvArray);
			//stmt.registerOutParameter(2, java.sql.Types.ARRAY);
			
			stmt.setObject(3,prepaidInvArray);
			//stmt.registerOutParameter(3, java.sql.Types.ARRAY);
			
			stmt.setObject(4,revInvArray);
			stmt.setObject(2,prepaidInvArray);
			stmt.setObject(3, revInvArray);
			stmt.setObject(4,prepayInvArray);

			// Register output parameters as per the data types
			
			
			
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			stmt.registerOutParameter(7, java.sql.Types.NUMERIC);
			stmt.registerOutParameter(8, java.sql.Types.NUMERIC);



			//Execute procedure
			stmt.execute();
			//java.sql.SQLException: Internal Error: Inconsistent catalog view - 

			System.out.println("1 _errBuf="+stmt.getString(5));
			System.out.println("2_retcode="+stmt.getString(6));
			System.out.println("3_vou_num="+stmt.getString(7));
			System.out.println("4_inv_num="+stmt.getString(8));

		}catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Exception Message ::"+exception.getMessage(),exception);
			throw new KPMGException("Exception Message ::"+exception.getMessage(),exception);
		}
		finally{
			dbUtil.closeDBConnection();
		}
		if(isDebugLevel){
			//     logger.debug("End:::execute Procedure method::: proc:"+procedureName+ " completed");
		}
		//return outParamValues;
	}
	public static void main(String[] args) {
		try {
			FAGSTTestInvoiceProcessing test = new FAGSTTestInvoiceProcessing();
			System.out.println(new Date());
			test.invoice_Processing();
			System.out.println(new Date());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private Object[] getBasicInvoiceDetails() throws Exception{
		
		Object[] invRecArray = new Object[42];
		
		java.sql.Date sqlDate = null;
		String value = "2018-02-19T08:00:00";
		logger.info("Date value::" +value);
		if(value!=null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateValue = formatter.parse(value);
			sqlDate = new java.sql.Date(dateValue.getTime());
			logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
			//stmt.setDate(i+1, sqlDate);
		}
		
		System.out.println("getBasicInvoiceDetails()::::::::::::;;");
//		
//		invRecArray[0] = "KPMG";//operating_unit
//		invRecArray[1] = "SR001501";//service req no
//		invRecArray[2] = "34881";//vendor code 
//		invRecArray[3] = "KOCHI";//vendor_site_code
//		invRecArray[4] = "TestingBPM_INV_08032017_003";//invoice_num
//		invRecArray[5] = "STANDARD";//invoice_type
//		invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
//		invRecArray[7] = "Date change Shravan & Shawn traveling Jeddah-Mum 17Oct for OTIS Elevator (1068238-99)Date change Shravan & Shawn traveling Jeddah-Mum 17Oct for OTIS Elevator (1068238-99)Date change Shravan & Shawn traveling Jeddah-Mum 17Oct for OTIS Elevat";//invoicedescription
//		invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
//		invRecArray[9] = "INR";//inv currentcy
//		invRecArray[10] = 0;//exchange_rate
//		invRecArray[11] = "";//exchange_rate_type
//		invRecArray[12] = "KPMG_NEFT";//payment_method
//		invRecArray[13] = "HOTELS";//pay)group
//		invRecArray[14] = "BATTENJOHN";//reg_name
//		invRecArray[15] = "";//ext_doc_ref
//		invRecArray[16] = "Item";//line_type
//		invRecArray[17] = 10;//quantity
//		invRecArray[18] = 10;//amount
//		invRecArray[19] = "Date change Shravan & Shawn traveling Jeddah-Mum 17Oct for OTIS Elevator (1068238-99)";//description
//		invRecArray[20] = "736169";//po_number
//		invRecArray[21] = 1;//po_linenumber
//		invRecArray[22] = 1;//po_shipment_number
//		invRecArray[23] = "149935";//receiptnumber----grn number
//		invRecArray[24] = "1";//receiptlinenumber
//		invRecArray[25] = "101.721314.3722.1162572.103.9999.10498";//"13585414";//dist_concat_segments //CCID
//		invRecArray[26] = "";//creditmemodist_description 
//		invRecArray[27] = "";//"TDS_SECTION";//TDS Section
//		invRecArray[28]= "TDS 194(I)-Comp-Bldg-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
//		invRecArray[29] = "";
//		invRecArray[30] = "";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
//		invRecArray[31] = "";
//		invRecArray[32] = "";
//		invRecArray[33] = "";
//		invRecArray[34] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
//		invRecArray[35] = "1";//invoice line number
//		invRecArray[36] = "";
//		invRecArray[37] = 1;
//		invRecArray[38] = 1;
//		invRecArray[39] = 1;
//		invRecArray[40] = null;
//		invRecArray[41] = null;
//	
		

		invRecArray[0] = "KPMG";//operating_unit
		invRecArray[1] = "FIP170000000528";//service req no
		invRecArray[2] = "2597";//vendor code 
		invRecArray[3] = "NEW DELHI";//vendor_site_code
		invRecArray[4] = "M11718A020013760";//invoice_num
		invRecArray[5] = "STANDARD";//invoice_type
		invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
		invRecArray[7] = "Post IT Pad 3X3";//invoicedescription
		invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
		invRecArray[9] = "INR";//inv currentcy
		invRecArray[10] = 0;//exchange_rate
		invRecArray[11] = "";//exchange_rate_type
		invRecArray[12] = "KPMG_DD";//payment_method
		invRecArray[13] = "OTHER SUPPLIER";//pay)group
		invRecArray[14] = "puneetchauhan";//reg_name
		invRecArray[15] = "";//ext_doc_ref
		invRecArray[16] = "Item";//line_type
		invRecArray[17] = 1;//quantity
		invRecArray[18] = 697.5;//amount
		invRecArray[19] = "Post IT Pad 3X3";//description
		invRecArray[20] = "798874";//po_number
		invRecArray[21] = 10;//po_linenumber
		invRecArray[22] = 1;//po_shipment_number
		invRecArray[23] = "212751";//receiptnumber----grn number
		invRecArray[24] = "1";//receiptlinenumber
		invRecArray[25] = "101.721902.5005.1187951.103.9999.18330";//"13585414";//dist_concat_segments //CCID
		invRecArray[26] = "";//creditmemodist_description 
		invRecArray[27] = "";//"TDS_SECTION";//TDS Section
		invRecArray[28]= "";//"TDS 194(J)-Non-Comp-10%";//TDS Category
		invRecArray[29] = "";
		invRecArray[30] = "INTRA (SGST+ CGST) @ 18%_101k";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
		invRecArray[31] = "";
		invRecArray[32] = "";
		invRecArray[33] = "";
		invRecArray[34] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
		invRecArray[35] = "1";//invoice line number
		invRecArray[36] = "";
		invRecArray[37] = 1;
		invRecArray[38] = 1;
		invRecArray[39] = 1;
		invRecArray[40] = null;
		invRecArray[41] = null;
		
	
		return invRecArray;
		
	}
//	private String[] getInvoiceDetailsWithTax(){
//		
//	}
//	private String[] getCreditMemoInvoiceDetails(){
//		
//	}

*//**
 * @return
 * @throws Exception
 *//*
private Object[] getStandardInvoiceLine1() throws Exception{
	
	System.out.println(" getStandardInvoiceLine1()::::::::::::::");
		
		Object[] invRecArray = new Object[42];
		
		java.sql.Date sqlDate = null;
		String value = "2018-02-19T08:00:00";
		logger.info("Date value::" +value);
		if(value!=null)
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateValue = formatter.parse(value);
			sqlDate = new java.sql.Date(dateValue.getTime());
			logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
			//stmt.setDate(i+1, sqlDate);
		}
		
		
		invRecArray[0] = "KPMG";//operating_unit
		invRecArray[1] = "FIP170000000528";//service req no
		invRecArray[2] = "2597";//vendor code 
		invRecArray[3] = "NEW DELHI";//vendor_site_code
		invRecArray[4] = "M11718A020013760";//invoice_num
		invRecArray[5] = "STANDARD";//invoice_type
		invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
		invRecArray[7] = "Post IT Pad 3X3";//invoicedescription
		invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
		invRecArray[9] = "INR";//inv currentcy
		invRecArray[10] = 0;//exchange_rate
		invRecArray[11] = "";//exchange_rate_type
		invRecArray[12] = "KPMG_DD";//payment_method
		invRecArray[13] = "OTHER SUPPLIER";//pay)group
		invRecArray[14] = "puneetchauhan";//reg_name
		invRecArray[15] = "";//ext_doc_ref
		invRecArray[16] = "Item";//line_type
		invRecArray[17] = 15.5;//quantity
		invRecArray[18] = 697.5;//amount
		invRecArray[19] = "Post IT Pad 3X3";//description
		invRecArray[20] = "798874";//po_number
		invRecArray[21] = 10;//po_linenumber
		invRecArray[22] = 1;//po_shipment_number
		invRecArray[23] = "212751";//receiptnumber----grn number
		invRecArray[24] = "1";//receiptlinenumber
		invRecArray[25] = "101.721902.5005.1187951.103.9999.18330";//"13585414";//dist_concat_segments //CCID
		invRecArray[26] = "";//creditmemodist_description 
		invRecArray[27] = "";//"TDS_SECTION";//TDS Section
		invRecArray[28]= "";//"TDS 194(J)-Non-Comp-10%";//TDS Category
		invRecArray[29] = "111";
		invRecArray[30] = "INTRA (SGST+ CGST) @ 18%_101k";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
		invRecArray[31] = "100";
		invRecArray[32] = "2597";
		invRecArray[33] = "FIP170000000528";
		invRecArray[34] = "IBM India Pvt. Ltd.";//Leasing vendor site
		invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
		invRecArray[36] = "1";
		invRecArray[37] = "NEW DELHI";
		invRecArray[38] = 1;
		invRecArray[39] = 1;
		invRecArray[40] = null;
		invRecArray[41] = null;
		
		return invRecArray;
		
	}
	
private Object[] getStandardInvoiceLine2() throws Exception{
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2017-07-03T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "B S R & Co. LLP";//operating_unit
	invRecArray[1] = "SR000011";//service req no
	invRecArray[2] = "43148";//vendor code 
	invRecArray[3] = "MUMBAI";//vendor_site_code
	invRecArray[4] = "TestingBPM_INV_03uly2017_002";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "test desc";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_NEFT";//payment_method
	invRecArray[13] = "INSURANCE";//pay)group
	invRecArray[14] = "SWEZPANDEY";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 20;//quantity
	invRecArray[18] = 20;//amount
	invRecArray[19] = "test desc";//desscription
	invRecArray[20] = "111737";//po_number
	invRecArray[21] = 2;//po_linenumber
	invRecArray[22] = 1;//po_shipment_number
	invRecArray[23] = "32840";//receiptnumber----grn number
	invRecArray[24] = "1";//receiptlinenumber
	invRecArray[25] = "101.721606.5005.1139680.103.9999.18330";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "";
	invRecArray[30] = "";//15% Service Tax with Swach Bharat and Krishi Cess";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "";
	invRecArray[32] = "";
	invRecArray[33] = "";
	invRecArray[34] = "";
	invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";
	invRecArray[36] = "";
	invRecArray[37] = 1;
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	return invRecArray;
	
}


private Object[] getStandardInvoiceLine3() throws Exception{
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2017-07-03T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "B S R & Co. LLP";//operating_unit
	invRecArray[1] = "SR000011";//service req no
	invRecArray[2] = "43148";//vendor code 
	invRecArray[3] = "MUMBAI";//vendor_site_code
	invRecArray[4] = "TestingBPM_INV_03uly2017_003";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "test desc";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_NEFT";//payment_method
	invRecArray[13] = "INSURANCE";//pay)group
	invRecArray[14] = "SWEZPANDEY";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 30;//quantity
	invRecArray[18] = 30;//amount
	invRecArray[19] = "test desc";//desscription
	invRecArray[20] = "111737";//po_number
	invRecArray[21] = 3;//po_linenumber
	invRecArray[22] = 1;//po_shipment_number
	invRecArray[23] = "32840";//receiptnumber----grn number
	invRecArray[24] = "1";//receiptlinenumber
	invRecArray[25] = "101.721606.5005.1139680.103.9999.18330";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "";
	invRecArray[30] = "";//15% Service Tax with Swach Bharat and Krishi Cess";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "";
	invRecArray[32] = "";
	invRecArray[33] = "";
	invRecArray[34] = "";
	invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";
	invRecArray[36] = "";
	invRecArray[37] = 1;
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	return invRecArray;
	
}




private Object[] getStandardInvoiceLine222() throws Exception{
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2018-06-10T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "KPMG"; //"B S R & Co. LLP";//operating_unit
	invRecArray[1] = "SR000011";//service req no
	invRecArray[2] = "611";//vendor code 
	invRecArray[3] = "GURGAON";//vendor_site_code
	invRecArray[4] = "TestingBPM_INV_03July2017_02";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "test desc";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_NEFT";//payment_method
	invRecArray[13] = "INSURANCE";//pay)group
	invRecArray[14] = "SWEZPANDEY";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 10;//quantity
	invRecArray[18] = 10;//amount
	invRecArray[19] = "test desc1";//description
	invRecArray[20] = "772916";//po_number
	invRecArray[21] = 1;//po_linenumber
	invRecArray[22] = 1;//po_shipment_number
	invRecArray[23] = "187527";//receiptnumber----grn number
	invRecArray[24] = "1";//receiptlinenumber
	invRecArray[25] = "101.721901.5008.1164148.100.9999.46351";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "";
	invRecArray[30] = "";//15% Service Tax with Swach Bharat and Krishi Cess";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "";
	invRecArray[32] = "";
	invRecArray[33] = "";
	invRecArray[34] = "";
	invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";
	invRecArray[36] = "";
	invRecArray[37] = 1;
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	return invRecArray;
	
}

private Object[] getStandardDistribLine1() throws Exception{
	
	System.out.println("getStandardDistribLine1():::::::::::");
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2018-02-19T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "KPMG";//operating_unit
	invRecArray[1] = "FIP180000000528";//service req no
	invRecArray[2] = "2597";//vendor code
	invRecArray[3] = "NEW DELHI";//vendor_site_code
	invRecArray[4] = "M11718A020013760";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "testing_ravi";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_DD";//payment_method
	invRecArray[13] = "OTHER SUPPLIER";//pay)group
	invRecArray[14] = "puneetchauhan";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 0;//quantity
	invRecArray[18] = -200;//amount
	invRecArray[19] = "Testing";//description
	invRecArray[20] = "";//po_number
	invRecArray[21] = 0;//po_linenumber
	invRecArray[22] = 0;//po_shipment_number
	invRecArray[23] = "";//receiptnumber----grn number
	invRecArray[24] = "0";//receiptlinenumber
	invRecArray[25] = "101.721902.5005.1187951.103.9999.18330"; //1163257.103.9999.18330";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "111";
	invRecArray[30] = "INTRA (SGST+ CGST) @ 18%_101k";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "100";
	invRecArray[32] = "2597";
	invRecArray[33] = "FIP170000000528";
	invRecArray[34] = "IBM India Pvt. Ltd.";//Leasing vendor site
	invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
	invRecArray[36] = "1";
	invRecArray[37] = "NEW DELHI";
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	
	return invRecArray;
	
}


private Object[] getStandardDistribLine2() throws Exception{
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2017-07-03T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "B S R & Co. LLP";//operating_unit
	invRecArray[1] = "SR000011";//service req no
	invRecArray[2] = "43148";//vendor code
	invRecArray[3] = "MUMBAI";//vendor_site_code
	invRecArray[4] = "TestingBPM_INV_06July2017_003";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "test invoice desc";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_NEFT";//payment_method
	invRecArray[13] = "INSURANCE";//pay)group
	invRecArray[14] = "BATTENJOHN";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 0;//quantity
	invRecArray[18] = -2;//amount
	invRecArray[19] = "test invoice desc";//description
	invRecArray[20] = "";//po_number
	invRecArray[21] = 0;//po_linenumber
	invRecArray[22] = 0;//po_shipment_number
	invRecArray[23] = "";//receiptnumber----grn number
	invRecArray[24] = "0";//receiptlinenumber
	invRecArray[25] = "201.711117.5005.1163257.103.9999.18330";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "";
	invRecArray[30] = "";//15% Service Tax with Swach Bharat and Krishi Cess";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "";
	invRecArray[32] = "";
	invRecArray[33] = "";
	invRecArray[34] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
	invRecArray[35] = "1";//invoice line number
	invRecArray[36] = "";
	invRecArray[37] = 1;
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	
	return invRecArray;
	
}


private Object[] getStandardDistribLine222() throws Exception{
	
	Object[] invRecArray = new Object[42];
	
	java.sql.Date sqlDate = null;
	String value = "2017-06-19T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	
	invRecArray[0] = "KPMG";//operating_unit
	invRecArray[1] = "IVC001502";//service req no
	invRecArray[2] = "11442";//vendor code
	invRecArray[3] = "GURGAON";//vendor_site_code
	invRecArray[4] = "TestingBPM_INV_28June2017_01";//invoice_num
	invRecArray[5] = "STANDARD";//invoice_type
	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
	invRecArray[7] = "test invoice desc";//invoicedescription
	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
	invRecArray[9] = "INR";//inv currentcy
	invRecArray[10] = 0;//exchange_rate
	invRecArray[11] = "";//exchange_rate_type
	invRecArray[12] = "KPMG_NEFT";//payment_method
	invRecArray[13] = "OTHER SUPPLIER";//pay)group
	invRecArray[14] = "BATTENJOHN";//reg_name
	invRecArray[15] = "";//ext_doc_ref
	invRecArray[16] = "Item";//line_type
	invRecArray[17] = 0;//quantity
	invRecArray[18] = -2;//amount
	invRecArray[19] = "test invoice desc";//description
	invRecArray[20] = "";//po_number
	invRecArray[21] = 0;//po_linenumber
	invRecArray[22] = 0;//po_shipment_number
	invRecArray[23] = "";//receiptnumber----grn number
	invRecArray[24] = "0";//receiptlinenumber
	invRecArray[25] = "101.721108.9999.9999999.999.9999.99999";//"13585414";//dist_concat_segments //CCID
	invRecArray[26] = "";//creditmemodist_description 
	invRecArray[27] = "";//194 (A) - COMP";//"TDS_SECTION";//TDS Section
	invRecArray[28]= "";//TDS 194(J)-Comp-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
	invRecArray[29] = "";
	invRecArray[30] = "";//15% Service Tax with Swach Bharat and Krishi Cess";//"SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
	invRecArray[31] = "";
	invRecArray[32] = "";
	invRecArray[33] = "";
	invRecArray[34] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";//Invoice DFF
	invRecArray[35] = "1";//invoice line number
	invRecArray[36] = "";
	invRecArray[37] = 1;
	invRecArray[38] = 1;
	invRecArray[39] = 1;
	invRecArray[40] = null;
	invRecArray[41] = null;
	
	
	
//	invRecArray[0] = "B S R & Co. LLP";//operating_unit
//	invRecArray[1] = "SR000011";//service req no
//	invRecArray[2] = "11264";//vendor code 
//	invRecArray[3] = "CHENNAI";//vendor_site_code
//	invRecArray[4] = "TestingBPM_INV_20July2017_01";//invoice_num
//	invRecArray[5] = "STANDARD";//invoice_type
//	invRecArray[6] = sqlDate;//"2016-06-21 12:00:00";//invoice_date 30-11-2015
//	invRecArray[7] = "Rent - Office";//invoicedescription
//	invRecArray[8] = sqlDate;//"2016-06-21 12:00:00";//gldate---04-01-2016
//	invRecArray[9] = "INR";//inv currentcy
//	invRecArray[10] = 0;//exchange_rate
//	invRecArray[11] = "";//exchange_rate_type
//	invRecArray[12] = "KPMG_NEFT";//payment_method
//	invRecArray[13] = "OTHER SUPPLIER";//pay)group
//	invRecArray[14] = "";//reg_name
//	invRecArray[15] = "";//ext_doc_ref
//	invRecArray[16] = "Item";//line_type
//	invRecArray[17] = 0;//quantity
//	invRecArray[18] = 10;//amount
//	invRecArray[19] = "Rent - Office";//description
//	invRecArray[20] = "";//po_number
//	invRecArray[21] = 0;//po_linenumber
//	invRecArray[22] = 0;//po_shipment_number
//	invRecArray[23] = "";//receiptnumber----grn number
//	invRecArray[24] = "0";//receiptlinenumber
//	invRecArray[25] = "101.721108.9999.9999999.999.9999.99999"; //"103.721318.5007.1139002.100.9999.36801";//"13585414";//dist_concat_segments //CCID
//	invRecArray[26] = "";//creditmemodist_description 
//	invRecArray[27] = "";//"TDS_SECTION";//TDS Section
//	invRecArray[28]= "TDS 194(I)-Comp-Bldg-10%";//"TDS 194(J)-Non-Comp-10%";//TDS Category
//	invRecArray[29] = "";//Invoice DFF
//	invRecArray[30] = "";//SERV. TAX @ 12.36% - RECOVERABLE";//Service tax Category
//	invRecArray[31] = "";
//	invRecArray[32] = "";
//	invRecArray[33] = "";
//	invRecArray[34] = "";
//	invRecArray[35] = "in-fmbpmuser3$10$2016-2017$InvBatch01$in-fmbpmuser3$Bangalore$Carton1$File1$20161010$Dlhi$Mumbai";
//	invRecArray[36] = "";
//	invRecArray[37] = 1;
//	invRecArray[38] = 1;
//	invRecArray[39] = 1;
//	invRecArray[40] = null;
//	invRecArray[41] = null;
	
	return invRecArray;
	
}

private Object[] getPrepaidDetails(){
	//prepay inv rec type
	
	Object[] prepaidInvRecArray = new Object[4];
	
	
	prepaidInvRecArray[0] = "13485903";
	prepaidInvRecArray[1] = "13585414";
	prepaidInvRecArray[2] = ""; 
	prepaidInvRecArray[3] = "";		
	return prepaidInvRecArray;
}

private Object[] getPrepayDetails () throws ParseException{
	//prepay inv rec type
	
	Object[] prepayInvRecArray = new Object[22];
	
	java.sql.Date sqlDate = null;
	String value = "2017-07-03T08:00:00";
	logger.info("Date value::" +value);
	if(value!=null)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateValue = formatter.parse(value);
		sqlDate = new java.sql.Date(dateValue.getTime());
		logger.info("utilDate:" + dateValue+"sqlDate:" + sqlDate);
		//stmt.setDate(i+1, sqlDate);
	}
	
	prepayInvRecArray[0] = "Insurance Advance Payment - 2017-18(GTL)"; //prepay invoice num
	prepayInvRecArray[1] = 12020295;  //invoice id
	prepayInvRecArray[2] = 1; 
	prepayInvRecArray[3] = 11084362; //outstanding amount
	prepayInvRecArray[4] = 2; //prepay amount
	prepayInvRecArray[5] = sqlDate;
	prepayInvRecArray[6] = "";
	prepayInvRecArray[7] = "";
	prepayInvRecArray[8] = "";
	prepayInvRecArray[9] = "";
	prepayInvRecArray[10] = "";
	prepayInvRecArray[11] ="";
	prepayInvRecArray[12] = "";
	prepayInvRecArray[13] = "";
	prepayInvRecArray[14] = "";
	prepayInvRecArray[15] = "";
	prepayInvRecArray[16] = "";
	prepayInvRecArray[17] = 0;
	prepayInvRecArray[18] = 0;
	prepayInvRecArray[19] = 0;
	prepayInvRecArray[20] = null;
	prepayInvRecArray[21] = null;
	
	return prepayInvRecArray;
}
private Object[] getReverseChargeDetails1() {
	//prepay inv rec type
	
	Object[] revInvRecArray = new Object[6];
		
	revInvRecArray[0] = "15% ST with Swach Bharat and Krishi Cess"; //prepay invoice num
	revInvRecArray[1] = "SERV. TAX @ 14% - RECOVERABLE";  //tax name
	revInvRecArray[2] = "Service"; //tax type
	revInvRecArray[3] = 14; // tax rate
	revInvRecArray[4] = 10; //tax amount
	revInvRecArray[5] = "13485903";
	
	return revInvRecArray;
}


private Object[] getReverseChargeDetails2() {
	//prepay inv rec type
	
	Object[] revInvRecArray = new Object[6];
		
	revInvRecArray[0] = "15% ST with Swach Bharat and Krishi Cess"; //prepay invoice num
	revInvRecArray[1] = "Swachh Bharat Cess 0.5% - Non Recoverable";  //tax name
	revInvRecArray[2] = "SERVICE_SWACH_BHARAT_CESS"; 
	revInvRecArray[3] = 0.5; //tax rate
	revInvRecArray[4] = 5; //tax amount
	revInvRecArray[5] = "13485903";
	
	return revInvRecArray;
}

private Object[] getReverseChargeDetails3() {
	//prepay inv rec type
	
	Object[] revInvRecArray = new Object[6];
		
	revInvRecArray[0] = "15% ST with Swach Bharat and Krishi Cess"; //prepay invoice num
	revInvRecArray[1] = "0.5 %  Krishi Kalyan Cess - Recoverable";  //invoice id
	revInvRecArray[2] = "KRISHI_KALYAN_CESS"; 
	revInvRecArray[3] = 0.5; //outstanding amount
	revInvRecArray[4] = 5; //prepay amount
	revInvRecArray[5] = "13485903";
	
	return revInvRecArray;
}

}



*/