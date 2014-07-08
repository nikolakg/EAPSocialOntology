/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.gyza.eap.eapsocialontology.twitter;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM;
import static com.hp.hpl.jena.ontology.OntModelSpec.OWL_MEM_MICRO_RULE_INF;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import eu.gyza.eap.eapsocialontology.jena.JenaMainTest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Tweet;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterOntologyController {

    private final Twitter twitter;
    private OntModel infOntModel;
    private static String SOURCE = "http://www.eap.gr/gyza/ontology/social";
    private static String NS = SOURCE + "#";
    private static String file = "C://projects/GinasThesis_EAP/protegeOntology/eapOntology3.owl";

    @Inject
    public TwitterOntologyController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(value = "/twitter/ontology", method = RequestMethod.GET)
    public String ontologies(Model model) {
        loadModel();
        model.addAttribute("musicFriends", getMusicFiends());
        model.addAttribute("foodFriends", getFoodFiends());
       
        return "twitter/ontology";
    }

    public void loadModel() {
      //  CursoredList<TwitterProfile> friendsList = twitter.friendOperations().getFriends();
        List<Tweet> tweetList = twitter.timelineOperations().getHomeTimeline(50); 
       
        File f = new File(file);
        Reader reader = null;
        try {
            reader = new FileReader(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JenaMainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        OntModel base = ModelFactory.createOntologyModel(OWL_MEM);
        base.read(reader, "RDF/XML");
       
        // Load twitter friends in Ontology
        OntClass friendOnt = base.getOntClass(NS + "Friend");
        OntProperty ownerOntProp = base.getOntProperty( NS + "owner" );
        
        OntClass messageOnt = base.getOntClass(NS + "Message");
        DatatypeProperty messageTextProp = base.getDatatypeProperty(NS + "messageText" );
        Individual friendInd;
        int j=0;
        for (Tweet t : tweetList) {
            friendInd = base.getIndividual(NS +  t.getFromUserId());
            if( friendInd == null){
                friendInd = base.createIndividual(NS +  t.getFromUserId(), friendOnt);
            }
            Individual msg1 = base.createIndividual(NS + "Msg"+(j++), messageOnt);
            friendInd.setPropertyValue(ownerOntProp, msg1);
            base.add(msg1, messageTextProp,  t.getText());
        }
        
        
        
        infOntModel = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);
      
    }

    public Iterator<Individual> getMusicFiends() {
        Resource musicFiendResouce = infOntModel.getResource(NS + "MusicFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(musicFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is MusicFriend" );
        }
        return infOntModel.listIndividuals(musicFiendResouce);
    }
    
    public Iterator<Individual> getFoodFiends() {
        Resource foodFiendResouce = infOntModel.getResource(NS + "FoodFriend");

        for (Iterator<Individual> i = infOntModel.listIndividuals(foodFiendResouce); i.hasNext();) {   
            Individual o = i.next();
            System.out.println(o.getLocalName()+ " is FoodFriend" );
        }
        return infOntModel.listIndividuals(foodFiendResouce);
    }
}
