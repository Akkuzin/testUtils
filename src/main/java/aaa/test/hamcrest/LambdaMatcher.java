package aaa.test.hamcrest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.function.Function;

@AllArgsConstructor(staticName = "by")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LambdaMatcher<T> extends BaseMatcher<T> {

	String description;
	Function<T, Boolean> matcher;

	@Override
	public boolean matches(Object argument) {
		return matcher.apply((T) argument);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText(this.description);
	}

	public static <F> LambdaMatcher byLambda(String description, Function<F, Boolean> matcher) {
		return LambdaMatcher.<F> by(description, matcher);
	}

	public static <F> LambdaMatcher byLambda(Function<F, Boolean> matcher) {
		return byLambda("", matcher);
	}

}