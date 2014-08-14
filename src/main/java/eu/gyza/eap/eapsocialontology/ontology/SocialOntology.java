/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eu.gyza.eap.eapsocialontology.ontology;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.gyza.eap.eapsocialontology.twitter.TwitterOntologyController;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.mindswap.pellet.jena.PelletReasonerFactory;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

/**
 *
 * @author nigkalge
 */
public class SocialOntology {
    private OntModel infOntModel;
    private static String SOURCE = "http://www.eap.gr/gyza/ontology/social";
    private static String NS = SOURCE + "#";
   // private static String file = "C://projects/GinasThesis_EAP/protegeOntology/eapOntology3.owl";
    private static String ontologyResource = "eapOntology4.owl";
    
    
    public Iterator<Individual> getMusicFiends() {
        Resource musicFiendResouce = infOntModel.getResource(NS + "MusicFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(musicFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is MusicFriend" );
        }
        return infOntModel.listIndividuals(musicFiendResouce);
    }
    
    public Iterator<Individual> getFoodFiends() {
        Resource foodFiendResouce = infOntModel.getResource(NS + "FoodAndDrinkFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(foodFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is FoodAndDrinkFriend" );
        }
        return infOntModel.listIndividuals(foodFiendResouce);
    }
    public Iterator<Individual> getEduFiends() {
        Resource foodFiendResouce = infOntModel.getResource(NS + "EducationFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(foodFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is EducationFriend" );
        }
        return infOntModel.listIndividuals(foodFiendResouce);
    }
    public Iterator<Individual> getHealthFriends() {
        Resource foodFiendResouce = infOntModel.getResource(NS + "HealthFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(foodFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is HealthFriend" );
        }
        return infOntModel.listIndividuals(foodFiendResouce);
    }
    
    public Iterator<Individual> getScienceFriends() {
        Resource foodFiendResouce = infOntModel.getResource(NS + "ScienceFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(foodFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is ScienceFriend" );
        }
        return infOntModel.listIndividuals(foodFiendResouce);
    }
    
    public void save() {
          String fileName = "new_"+ontologyResource;
        FileWriter out = null;
        try {
            out = new FileWriter( fileName );
        } catch (IOException ex) {
            Logger.getLogger(TwitterOntologyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            infOntModel.write( out, "RDF/XML" );
        }
        finally {
           try {
               out.close();
           }
           catch (IOException closeException) {
               // ignore
           }
        }
    
    }

    public void loadModel( Twitter twitter) {
        List<Tweet> tweetList = twitter.timelineOperations().getHomeTimeline(100); 
        InputStream inputStream = 
            getClass().getClassLoader().getResourceAsStream(ontologyResource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream ));
        OntModel base = ModelFactory.createOntologyModel(OWL_MEM);
        base.read(reader, "RDF/XML");
       
        // Load twitter friends in Ontology
        OntClass friendOnt = base.getOntClass(NS + "Friend");
        OntProperty ownerOntProp = base.getOntProperty( NS + "owner" );
        
        OntClass messageOnt = base.getOntClass(NS + "Message");
        DatatypeProperty messageTextProp = base.getDatatypeProperty(NS + "messageText" );
        OntProperty hasOwnerOntProp = base.getOntProperty( NS + "hasOwner" );
        
        Individual friendInd;
        int j=0;
        for (Tweet t : tweetList) {
            friendInd = base.getIndividual(NS +  t.getFromUser());
            if( friendInd == null){
                friendInd = base.createIndividual(NS +  t.getFromUser(), friendOnt);
            
            }
            Individual msg1 = base.createIndividual(NS + "Msg"+(j++), messageOnt);
            //friendInd.setPropertyValue(ownerOntProp, msg1);
            msg1.setPropertyValue(hasOwnerOntProp, friendInd);
            base.add(msg1, messageTextProp,  t.getText());
            System.out.println("In Model, added to ["+t.getFromUser()+"] message ["+t.getText()+"]");
        }
        
        
        
        //infOntModel = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);
        // create the reasoning model using the base
        //OntModel inf = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);
        infOntModel = ModelFactory.createOntologyModel( PelletReasonerFactory.THE_SPEC, base );   

      
    }

    public Iterator<Individual> getFriendList(String friend) {
         
        if("eduFriends".equals(friend)){
            return  getScienceFriends();
        }else if ("healthFriends".equals(friend)){
             return  getHealthFriends();
        }else if("muscFriends".equals(friend)){
             return  getMusicFiends();
        }else if("foodFriends".equals(friend)){
             return  getFoodFiends();
        }else if("eduFriends".equals(friend)){
             return  getEduFiends();
        }
        else return null;
    }
}
