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

import com.hp.hpl.jena.ontology.Individual;
import eu.gyza.eap.eapsocialontology.ontology.SocialOntology;
import java.util.Iterator;
import javax.inject.Inject;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterTimelineController {

    private final Twitter twitter;
    private final SocialOntology ontology;

    @Inject
    public TwitterTimelineController(Twitter twitter, SocialOntology ontology) {
        this.twitter = twitter;
        this.ontology = ontology;
    }

    @RequestMapping(value = "/twitter/timeline", method = RequestMethod.GET)
    public String showTimeline(Model model) {
        return showTimeline("Home", model);
    }

    @RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
    public String showTimeline(@PathVariable("timelineType") String timelineType, Model model) {
        if (timelineType.equals("Home")) {
            model.addAttribute("timeline", twitter.timelineOperations().getHomeTimeline(100));
        } else if (timelineType.equals("User")) {
            model.addAttribute("timeline", twitter.timelineOperations().getUserTimeline());
        } else if (timelineType.equals("Mentions")) {
            model.addAttribute("timeline", twitter.timelineOperations().getMentions());
        } else if (timelineType.equals("Favorites")) {
            model.addAttribute("timeline", twitter.timelineOperations().getFavorites());
        }
        model.addAttribute("timelineName", timelineType);
        return "twitter/timeline";
    }

    @RequestMapping(value = "/twitter/tweet", method = RequestMethod.POST)
    public String postTweet(String message) {
        twitter.timelineOperations().updateStatus(message);
        return "redirect:/twitter";
    }

    @RequestMapping(value = "/twitter/owltweet", method = RequestMethod.POST)
    public String postOwlTweet(MessageForm message) {
        Iterator<Individual> friends = null;
        if ("eduFriends".equals(message.getFriend())) {
            friends = ontology.getScienceFriends();
        } else if ("healthFriends".equals(message.getFriend())) {
            friends = ontology.getHealthFriends();
        } else if ("muscFriends".equals(message.getFriend())) {
            friends = ontology.getMusicFiends();
        } else if ("foodFriends".equals(message.getFriend())) {
            friends = ontology.getFoodFiends();
        } else if ("eduFriends".equals(message.getFriend())) {
            friends = ontology.getEduFiends();
        }

        for (Iterator<Individual> i = friends; i.hasNext();) {
            Individual o = i.next();
            twitter.timelineOperations().updateStatus("@" + o.getLocalName() + " " + message.getText()+   
                " #"+message.getFriend());
            }
           
            return "redirect:/twitter/timeline";
    }

}
