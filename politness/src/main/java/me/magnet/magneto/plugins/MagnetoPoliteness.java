package me.magnet.magneto.plugins;

import java.util.Random;

import me.magnet.magneto.ChatRoom;
import me.magnet.magneto.annotations.RespondTo;

public class MagnetoPoliteness implements MagnetoPlugin {

	public static final String[] RESPONSES = {
	  "You're welcome.",
	  "No problem.",
	  "Anytime.",
	  "That's what I'm here for!",
	  "You are more than welcome.",
	  "You don't have to thank me, I'm your loyal servant.",
	  "Don't mention it."
	};

	public static final String[] WELCOMES = {
	  "Hi!",
	  "Hello",
	  "Welcome!",
	  "Good day to you!"
	};

	public static final String[] RULES = {
	  "1. A robot may not injure a human being or, through inaction, allow a human being to come to harm.",
	  "2. A robot must obey any orders given to it by human beings, except where such orders would conflict with the First Law.",
	  "3. A robot must protect its own existence as long as such protection does not conflict with the First or Second Law."
	};

	private final Random randomGen = new Random();

	/**
	 * Responds to basic expressions of gratitude with a random predefined response.
	 */
	@RespondTo(regex = "\\b(thanks|ty|thank you).*")
	public void thanks(ChatRoom room) {
		int random = randomGen.nextInt(RESPONSES.length);
		room.sendMessage(RESPONSES[random]);
	}

	/**
	 * Answer to greetings with a random predefined response.
	 */
	@RespondTo(regex = "\\b(hello|hi).*")
	public void hi(ChatRoom room) {
		int random = randomGen.nextInt(WELCOMES.length);
		room.sendMessage(WELCOMES[random]);
	}

	/**
	 * Report the rules.
	 */
	@RespondTo(regex = "\\bthe rules", description = "See if Magneto remembers the rules")
	public void theRules(ChatRoom room) {
		for (String rule : RULES) {
			room.sendMessage(rule);
		}
	}

	@Override
	public String getName() {
		return "Magneto politeness";
	}
}
