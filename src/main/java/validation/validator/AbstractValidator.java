package validation.validator;

import java.util.ArrayList;
import java.util.List;

import validation.feedback.FeedBack;
import validation.rule.Rule;

public abstract class AbstractValidator<R> implements Validator<R> {

	protected List<Rule<R>> rules = new ArrayList<Rule<R>>();

	protected List<FeedBack<R>> feedBacks = new ArrayList<FeedBack<R>>();

	public void addRule(Rule<R> rule) {
		rules.add(rule);
	}

	public void removeRule(Rule<R> rule) {
		rules.remove(rule);
	}

	public void addFeedBack(FeedBack<R> feedBack) {
		feedBacks.add(feedBack);
	}

	public void removeFeedBack(FeedBack<R> feedBack) {
		feedBacks.remove(feedBack);
	}

	protected void triggerValidation() {
		for (Rule<R> rule : rules) {
			R result = rule.validate();
			for (FeedBack<R> feedBack : feedBacks) {
				feedBack.feedback(result);
			}
		}
	}
}
