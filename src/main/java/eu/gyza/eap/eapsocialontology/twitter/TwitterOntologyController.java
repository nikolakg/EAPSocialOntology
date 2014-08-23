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

import javax.inject.Inject;

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
        model.addAttribute("sportFriends", ontology.getSportFiends());
        model.addAttribute("messageForm", new MessageForm());
        ontology.save();        
        return "twitter/ontology";
    }
}
