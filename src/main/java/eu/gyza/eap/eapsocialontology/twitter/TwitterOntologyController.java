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
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import eu.gyza.eap.eapsocialontology.ontology.SocialOntology;

@Controller
public class TwitterOntologyController {

    private final Twitter twitter;
    private SocialOntology ontology;
    private static String SOURCE = "http://www.eap.gr/gyza/ontology/social";
    private static String NS = SOURCE + "#";
   // private static String file = "C://projects/GinasThesis_EAP/protegeOntology/eapOntology3.owl";
    private static String ontologyResource = "eapOntology4.owl";
    
    @Inject
    public TwitterOntologyController(Twitter twitter, SocialOntology ontology) {
        this.twitter = twitter;
        this.ontology = ontology;
    }
    
    
    
    @RequestMapping(value = "/twitter/ontology", method = RequestMethod.GET)
    public String ontologies(Model model) {
        ontology.loadModel(twitter);
        model.addAttribute("musicFriends", ontology.getMusicFiends());
        model.addAttribute("foodFriends", ontology.getFoodFiends());
        model.addAttribute("eduFriends", ontology.getEduFiends());
        model.addAttribute("healthFriends", ontology.getHealthFriends());
        model.addAttribute("messageForm", new MessageForm());
        ontology.save();        
        return "twitter/ontology";
    }
}
