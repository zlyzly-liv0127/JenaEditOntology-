package main;
import org.apache.jena.ontology.*; 
import java.util.Iterator;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import org.apache.jena.rdf.model.*;
import org.apache.jena.*;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.ontology.*; 
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.util.iterator.ExtendedIterator;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;

import org.apache.jena.vocabulary.XSD;

public class Initiate extends Object {
	public static String Res="F:/codes/Protege/zerotrust3rdfxml.owl";
	public static String Des = "F:/codes/Protege/zerotrustjenatest1.owl";
    public static String NS = "http://www.semanticweb.org/1111/ontologies/2021/2/untitled-ontology-13#";
    public void init() {
    	//创建本体模型
    	OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        m.read(Res);
        OntClass sub = m.getOntClass(NS + "Subject");
        addSubjects(m, sub, NS);
        OntClass Inf = m.getOntClass(NS + "InformationSystem");
        addInformationSystems(m, Inf, NS);
        Individual sub1 = m.getIndividual(NS + "testSubject1");
        Individual sub2 = m.getIndividual(NS + "testSubject2");
        Individual sub3 = m.getIndividual(NS + "testSubject3");
        Individual sub4 = m.getIndividual(NS + "testSubject4");
        Individual Inf1 = m.getIndividual(NS + "testIS1");
		ObjectProperty hasDim1 = m.getObjectProperty(NS + "hasDimension1");
		ObjectProperty hasDim2 = m.getObjectProperty(NS + "hasDimension2");
		hasTicket(m, NS, sub1, Inf1, hasDim1, hasDim2);
		hasTicket(m, NS, sub2, Inf1, hasDim1, hasDim2);
		hasTicket(m, NS, sub3, Inf1, hasDim1, hasDim2);
		hasTicket(m, NS, sub4, Inf1, hasDim1, hasDim2);

		
        WriteBackToFile(Des, m);
        m.close();
        
    }
	static void addSubjects(OntModel m, OntClass sub, String NS) {
    	OntClass Cer = m.getOntClass(NS + "Certificate");
    	ObjectProperty hasIde = m.getObjectProperty(NS + "hasIdentity");
    	ObjectProperty hasCom = m.getObjectProperty(NS + "hasCompany");
    	ObjectProperty hasOSV = m.getObjectProperty(NS + "hasOSVersion");
    	ObjectProperty hasCer = m.getObjectProperty(NS + "hasCertificate");
    	DatatypeProperty accessTime = m.getDatatypeProperty(NS + "AccessTime");
    	//创建新主体实例1
    	Individual sub1 = m.createIndividual(NS + "testSubject1", sub);
    	Individual cer1 = m.createIndividual(NS + "Certificate1", Cer);
    	Individual ide1 = m.getIndividual(NS + "Administrator");
    	Individual com1 = m.getIndividual(NS + "SJTU");
    	Individual OS1 = m.getIndividual(NS + "Windows1");
    	//m.addLiteral (indi, riskFactor, r);
    	sub1.addProperty(hasIde, ide1);
    	sub1.addProperty(hasCom, com1);
    	sub1.addProperty(hasOSV, OS1);
    	sub1.addProperty(hasCer, cer1); 
    	String  str1= "17:17:10";
    	sub1.addProperty(accessTime, str1);
    	//创建主体实例2
    	Individual sub2 = m.createIndividual(NS + "testSubject2", sub);
    	Individual cer2 = m.createIndividual(NS + "Certificate2", Cer);
    	Individual ide2 = m.getIndividual(NS + "Administrator");
    	Individual com2 = m.getIndividual(NS + "SJTU");
    	Individual OS2 = m.getIndividual(NS + "Windows1");
    	//m.addLiteral (indi, riskFactor, r);
    	sub2.addProperty(hasIde, ide2);
    	sub2.addProperty(hasCom, com2);
    	sub2.addProperty(hasOSV, OS2);
    	sub2.addProperty(hasCer, cer2); 
    	String  str2= "22:17:10";
    	sub2.addProperty(accessTime, str2);
    	//String  = "17:17:10";
    	//创建主体实例3
    	Individual sub3 = m.createIndividual(NS + "testSubject3", sub);
    	//Individual cer3 = m.createIndividual(NS + "Certificate3", Cer);
    	Individual ide3 = m.getIndividual(NS + "Administrator");
    	Individual com3 = m.getIndividual(NS + "SJTU");
    	Individual OS3 = m.getIndividual(NS + "Windows1");
    	//m.addLiteral (indi, riskFactor, r);
    	sub3.addProperty(hasIde, ide3);
    	sub3.addProperty(hasCom, com3);
    	sub3.addProperty(hasOSV, OS3);
    	//sub3.addProperty(hasCer, cer3); 
    	String  str3= "22:17:10";
    	sub3.addProperty(accessTime, str3);
    	//创建主体实例4
    	Individual sub4 = m.createIndividual(NS + "testSubject4", sub);
    	//Individual cer4 = m.createIndividual(NS + "Certificate4", Cer);
    	Individual ide4 = m.getIndividual(NS + "Administrator");
    	Individual com4 = m.getIndividual(NS + "SJTU");
    	Individual OS4 = m.getIndividual(NS + "Windows1");
    	//m.addLiteral (indi, riskFactor, r);
    	sub4.addProperty(hasIde, ide4);
    	sub4.addProperty(hasCom, com4);
    	sub4.addProperty(hasOSV, OS4);
    	//sub4.addProperty(hasCer, cer4); 
    	String  str4= "17:17:10";
    	sub4.addProperty(accessTime, str4);
    }
	static void addInformationSystems(OntModel m, OntClass Inf, String NS) {
		//创建信息系统安全状态实例,使用单一积分的信任算法
		//OntClass Inf = m.getOntClass(NS + "InformationSystem");
		OntClass Dim1 = m.getOntClass(NS + "Dimension1");
		OntClass Dim2 = m.getOntClass(NS + "Dimension2");
		OntClass Sof = m.getOntClass(NS + "SoftwareFlaws");
		//OntClass Sof = m.getOntClass(NS + "SoftwareFlaws");
		OntClass Mal = m.getOntClass(NS + "NewlyIdentifiedMalwares");
		Individual Inf1 = m.createIndividual(NS + "testIS1", Inf);
		ObjectProperty hasDim1 = m.getObjectProperty(NS + "hasDimension1");
		ObjectProperty hasDim2 = m.getObjectProperty(NS + "hasDimension2");
		ObjectProperty hasSof = m.getObjectProperty(NS + "hasSoftwareFlaws");
		ObjectProperty hasMal = m.getObjectProperty(NS + "hasNewlyIdentifiedMalwares");
		Individual Dim1_1 = m.createIndividual(NS + "ScoreBased", Dim1);
		Individual Dim2_1 = m.createIndividual(NS + "Singular", Dim2);
		Individual Sof1 = m.createIndividual(NS + "SoftwareFlaws1", Sof);
		Individual Mal1 = m.createIndividual(NS + "NewlyIdentifiedMalwares1", Mal);
		Inf1.addProperty(hasDim1, Dim1_1);
		Inf1.addProperty(hasDim2, Dim2_1);
		//Inf1.addProperty(hasSof, Sof1);
		Inf1.addProperty(hasMal, Mal1);
	}
	//根据算法判定是否有ticket,授予什么样的ticket
	static void hasTicket(OntModel m, String NS, Individual sub, Individual Inf1, ObjectProperty hasDim1, ObjectProperty hasDim2) {
		//判定使用的信任算法
		int flag1 = 1;//0代表基于分数 1代表基于标准
		int flag2 = 1;//0代表单一 1代表基于上下文
		int res = 0;
		Individual Dim1_1 = m.getIndividual(NS + "ScoreBased");
		if(Inf1.hasProperty(hasDim1, Dim1_1)) {
			flag1 = 0;
		}
		Individual Dim2_1 = m.getIndividual(NS + "Singular");
		if(Inf1.hasProperty(hasDim2, Dim2_1)) {
			flag2 = 0;
		}
		if(flag1==0&&flag2==0) {
			//基于分数算法
			int score = 100;//初始100分
			ObjectProperty hasSof = m.getObjectProperty(NS + "hasSoftwareFlaws");
			ObjectProperty hasMal = m.getObjectProperty(NS + "hasNewlyIdentifiedMalwares");
			ObjectProperty hasAtt = m.getObjectProperty(NS + "hasAttacks");
			ObjectProperty hasCer = m.getObjectProperty(NS + "hasCertificate");
			if(Inf1.hasProperty(hasSof)) {
				//System.out.println("hasSof");
				score -= 10;
			}
			if(Inf1.hasProperty(hasMal)) {
				score -= 10;
				//System.out.println("hasMal");
			}			
			if(Inf1.hasProperty(hasAtt)) {
				score -= 10;
				//System.out.println("hasAtt");
			}
			if(!sub.hasProperty(hasCer)) {
				score -= 20;
				//System.out.println("hasnoCer");
			}
			if(score<60) {
				res = 0;
			}
			else if(score<90) {
				res = 1;
			}
			else{res = 2;}
		}
		else {
			System.out.println("Error!");
			res = 0;
		}
		DatatypeProperty accessTime = m.getDatatypeProperty(NS + "AccessTime");
		String accesstimestr = ((Literal) sub.getPropertyValue(accessTime)).getString();
		int hour = Integer.parseInt(accesstimestr.substring(0, 2));
		if(hour>21||hour<7) {
			res = 0;
		}
		String substr = sub.toString();
		if(res == 2) {
			System.out.println(substr +"has Senior Ticket!");
			Individual Set1 = m.getIndividual(NS + "SeniorTicket1");
			ObjectProperty hasTic = m.getObjectProperty(NS + "hasTicket");
			sub.addProperty(hasTic, Set1); 
		}
		else if(res == 1) {
			System.out.println(substr +"has Ordinary Ticket!");
			Individual Ort1 = m.getIndividual(NS + "OrdinaryTicket1");
			ObjectProperty hasTic = m.getObjectProperty(NS + "hasTicket");
			sub.addProperty(hasTic, Ort1); 
		}
		else {
			System.out.println(substr +"has No Ticket!");
		}
	}
    static void WriteBackToFile(String testOntologyfile, OntModel m)
    {
    String filepath = testOntologyfile;  
    
    try
    {
        FileOutputStream fileOS = new FileOutputStream(filepath);
        RDFWriter rdfWriter = m.getWriter("RDF/XML");  
        rdfWriter.setProperty("showXMLDeclaration","true");  
        rdfWriter.setProperty("showDoctypeDeclaration", "true");  
        rdfWriter.setProperty("allowBadURIs","true");
        rdfWriter.setProperty("relativeURIs","");
        rdfWriter.setProperty("tab","0");
        
        rdfWriter.write(m, fileOS, null);  
        
        fileOS.flush();
        fileOS.close(); 
        System.out.println("Successfully write back to file: " + filepath);    
    }
    catch(Exception e)
    { 
        System.out.println("Error!");
    }         
}
}
    

