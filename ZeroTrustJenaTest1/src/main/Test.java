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
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.rdf.model.InfModel; 
import org.apache.jena.ontology.*; 
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.util.iterator.ExtendedIterator;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;

import org.apache.jena.vocabulary.XSD;

public class Test {
	public static String Res="F:/codes/Protege/zerotrustjenatest1.owl";
	public static String Des = "F:/codes/Protege/zerotrustjenatest2.owl";
    public static String NS = "http://www.semanticweb.org/1111/ontologies/2021/2/untitled-ontology-13#";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Initiate starter = new Initiate();
        starter.init();
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM );
        m.read(Res);
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        InfModel inf = ModelFactory.createInfModel(reasoner,m);
        ValidityReport validity = inf.validate();
        if (validity.isValid()){
            System.out.println("Reasoning Completed.");
        }
        //OntClass sub = m.getOntClass(NS + "testSubject1");
        Resource nForce = inf.getResource(NS + "testSubject1");
        System.out.println("testSubject1 *:");
        //printStatements(inf, nForce, null, null);

	}
	public static void printStatements(Model m, Resource s, Property p, Resource o) { 
		for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) { 
			Statement stmt = i.nextStatement(); 
			System.out.println(" - " + PrintUtil.print(stmt)); 
		} 
	}

}
