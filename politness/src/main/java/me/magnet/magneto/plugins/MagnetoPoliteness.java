package me.magnet.magneto.plugins;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import me.magnet.magneto.Response;
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

	public static final ImmutableList<String> RULES = ImmutableList.of(
	  "1. A robot may not injure a human being or, through inaction, allow a human being to come to harm.",
	  "2. A robot must obey any orders given to it by human beings, except where such orders would conflict with the First Law.",
	  "3. A robot must protect its own existence as long as such protection does not conflict with the First or Second Law."
	);

	private final Random randomGen = new Random();

	/*
	 * Responds to basic expressions of gratitude with a random predefined response.
	 */
	@RespondTo("\\b([tT]hanks|ty|TY|[tT]hank you).*")
	public Response thanks() {
		int random = randomGen.nextInt(RESPONSES.length);
		return Response.fireAndForget().sendMessage(RESPONSES[random]);
	}

	/*
	 * Answer to greetings with a random predefined response.
	 */
	@RespondTo("\\b([Hh]ello|[Hh]i).*")
	public Response hi() {
		int random = randomGen.nextInt(WELCOMES.length);
		return Response.fireAndForget().sendMessage(WELCOMES[random]);
	}

	/*
	 * Report the rules.
	 */
	@RespondTo("\\bthe rules")
	public Response theRules() {
		Response response = Response.fireAndForget();
		for (String rule : RULES) {
			response.sendMessage(rule);
		}
		return response;
	}

	@Override
	public String getName() {
		return "Magneto politeness";
	}
}
