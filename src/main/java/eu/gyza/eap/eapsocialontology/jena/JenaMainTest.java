/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gyza.eap.eapsocialontology.jena;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM_MICRO_RULE_INF;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.util.PrintUtil;
import com.hp.hpl.jena.vocabulary.VCARD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author ydespotopoulos
 */
public class JenaMainTest {

    static String personURI = "http://somewhere/JohnSmith";
    static String fullName = "John Smith";

    public static void ginasOWL() throws MalformedURLException, IOException{
       // ontology that will be used
        String ont = "http://www.mindswap.org/2004/owl/mindswappers";
        
// create an empty ontology model using Pellet spec
            

        
        // create an empty Model
        
        Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
        
        Model model = ModelFactory.createDefaultModel();
        OntModel m = ModelFactory.createOntologyModel();        // create the base model
        
        String SOURCE = "http://www.eap.gr/gyza/ontology/social";
        //String SOURCE = "http://www.semanticweb.org/tmns/ontologies/2014/4/eapOntology";
        String NS = SOURCE + "#";
        String foafNS ="http://xmlns.com/foaf/0.1/";
        //String file = "C://projects/eap/eapOntology3.owl";
        String file = "C:\\projects\\GinasThesis_EAP\\protegeOntology\\eapOntology6.owl";
     //   String urlSting = "https://github.com/nikolakg/EAPSocialOntology/blob/master/src/main/resources/eapOntology3.owl";
        
        
       // URL url = new URL(urlSting);
	// read text returned by server
	
		    
        
        File f = new File(file);
        Reader reader = null;
        try {
          // reader = new FileReader(f);
             reader = new InputStreamReader(
                      new FileInputStream(f), "UTF8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JenaMainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // BufferedReader reader2 = new BufferedReader(new InputStreamReader(url.openStream()));
        OntModel base = ModelFactory.createOntologyModel(OWL_MEM);
        base.read(reader, "RDF/XML");

// create the reasoning model using the base
        //OntModel inf = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);
        OntModel inf = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC, base );   

        
        
// create a dummy paper for this example
        OntClass personOnt = base.getOntClass(foafNS + "Person");
        OntClass friendOnt = base.getOntClass(NS + "Friend");
        OntClass interestOnt = base.getOntClass(NS + "Interest");
        OntClass musicGroup = base.getOntClass(NS + "MusicGroup");
        OntClass ftOnt = base.getOntClass(NS + "FoodAndDrinkInterest");
        OntClass eduOnt = base.getOntClass(NS + "EducationInterest");
        OntClass musOnt = base.getOntClass(NS + "MusicInterest");
        OntClass messageOnt = base.getOntClass(NS + "Message");
        OntProperty likesOntProp = base.getOntProperty( NS + "likes" );
        OntProperty ownerOntProp = base.getOntProperty( NS + "owner" );
        OntProperty hasInterestOntProp = base.getOntProperty( NS + "hasInterest" );
        DatatypeProperty messageTextProp = base.getDatatypeProperty(NS + "messageText" );
 

        Individual personInd,ind,musciInt,musicGrp1,msg1;
        personInd  = base.createIndividual(foafNS + "Georgia", personOnt);
        musciInt = base.createIndividual(NS + "Music1", musOnt);
        musicGrp1 = base.createIndividual(NS + "MusicGroup1", musicGroup);
        msg1 = base.createIndividual(NS + "Msg1", messageOnt);
        //msg1.setPropertyValue(messageTextProp, "Banana Test" );
         
      //  msg1.set
        
        ind = base.createIndividual(NS + "FriendYannis", friendOnt);
        ind.setPropertyValue(ownerOntProp, msg1);
        //msg1.addLiteral(messageTextProp, );
       
        model.add(msg1, messageTextProp,  "Banana Test");
//        musicGrp1.setPropertyValue(hasInterestOntProp, musciInt);
//        ind.setPropertyValue(likesOntProp, musciInt);
        base.createIndividual(NS + "FriendMaria", friendOnt);
        base.createIndividual(NS + "FriendSophia", friendOnt);

         
       /* for (ExtendedIterator<? extends OntResource> instances = personOnt.listInstances(); instances.hasNext(); ) {
            OntResource equipeInstance = instances.next();
            System.out.println( "Equipe instance: " + equipeInstance.getProperty( knows ).getString() );
        }*/
         
        InfModel inf1 = ModelFactory.createInfModel(reasoner, base);
        Resource nForce = inf1.getResource(NS + "FriendYannis");
        System.out.println("nForce :");
      //  printStatements(inf1, nForce, null, null);
        
// list the asserted types
        personInd = base.getIndividual(foafNS + "Georgia");
        
     //  base.listObjectsOfProperty(likes)
        
        for (Iterator i = personInd.listRDFTypes(true); i.hasNext();) {
            Object o = i.next();
            System.out.println(personInd.getLocalName() + " is asserted in class " + o);
            
          //  for(Iterator j = ((Resource)o).listProperties();j.hasNext();) {
          //          System.out.println(" Property is  " + j.next());
          //  }
        }

// list the inferred types
        personInd = inf.getIndividual(NS + "FriendYannis");
        for(Iterator h = personInd.listProperties();h.hasNext();){
           Statement p =  (Statement)h.next();
           System.out.println(personInd.getLocalName()+ " property " +p.getObject().asResource().getURI());
        }
          
        for (Iterator<Resource> i = personInd.listRDFTypes(true); i.hasNext();) {   
            Object o = i.next();
            System.out.println(personInd.getLocalName()+ " is inferred to have RDFTypes  " + o);
        }
        Resource eduFiendResouce = inf1.getResource(NS + "EducationFriend");
       // inf.listIndividuals(eduFiendResouce);
        
        for (Iterator<Individual> i = inf.listIndividuals(eduFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is EducationFriend" );
        }
        
        Resource foodAndDrinkMessageResource = inf1.getResource(NS + "FoodAndDrinkMessage");
       // inf.listIndividuals(foodAndDrinkMessageResource);
        
        for (Iterator<Individual> i = inf.listIndividuals(foodAndDrinkMessageResource); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is foodAndDrinkMessage" );
        }
        
        Resource fanddFiendResouce = inf.getResource(NS + "FoodAndDrinkFriend");
       // inf.listIndividuals(fanddFiendResouce);
        
        for (Iterator<Individual> i = inf.listIndividuals(fanddFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is FoodAndDrinkFriend" );
        }
               
        
        
    }
    
    
    public static void printStatements(Model m, Resource s, Property p, Resource o) {
        for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
            Statement stmt = i.nextStatement();
            System.out.println(" - " + PrintUtil.print(stmt));
        }
    }
    
    public static void defaultExample(){
        
        // create an empty Model
        Model model = ModelFactory.createDefaultModel();
        OntModel m = ModelFactory.createOntologyModel();
        // create the resource
        Resource johnSmith = model.createResource(personURI);

        // add the property
        johnSmith.addProperty(VCARD.FN, fullName);

        // create the base model
        String SOURCE = "http://www.eswc2006.org/technologies/ontology";
        String NS = SOURCE + "#";
        String file = "C://Users/TMNS/Documents/NetBeansProjects/2006-09-21.rdf";
        File f = new File(file);
        Reader reader = null;
        try {
            reader = new FileReader(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JenaMainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        OntModel base = ModelFactory.createOntologyModel(OWL_MEM);
        base.read(reader, "RDF/XML");

// create the reasoning model using the base
        OntModel inf = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);

// create a dummy paper for this example
        OntClass paper = base.getOntClass(NS + "Paper");
        Individual p1 = base.createIndividual(NS + "paper1", paper);

// list the asserted types
        for (Iterator i = p1.listRDFTypes(false); i.hasNext();) {
            System.out.println(p1.getURI() + " is asserted in class " + i.next());
        }

// list the inferred types
        p1 = inf.getIndividual(NS + "paper1");
        for (Iterator<Resource> i = p1.listRDFTypes(false); i.hasNext();) {
            System.out.println(p1.getURI() + " is inferred to be in class " + i.next());
        }

        OntClass programme = m.createClass(NS + "Programme");
        OntClass orgEvent = m.createClass(NS + "OrganizedEvent");

        ObjectProperty hasProgramme = m.createObjectProperty(NS + "hasProgramme");

     //   hasProgramme.addDomain(orgEvent);
      //  body.addRange(programme);
      ///  body.addLabel("has programme", "en");
    }
    
    public static void main(String args[]) throws IOException {
      ginasOWL();
    }

}
