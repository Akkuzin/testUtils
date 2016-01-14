package aaa.test.spring;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.springframework.test.context.TestContextManager;

public class SpringRule implements MethodRule {

	@Override
	public Statement apply(final Statement base, final FrameworkMethod method, final Object target) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				TestContextManager testContextManager = new TestContextManager(target.getClass());
				testContextManager.prepareTestInstance(target);
				testContextManager.beforeTestClass();
				testContextManager.beforeTestMethod(target, method.getMethod());
				Throwable throwable = null;
				try {
					base.evaluate();
				} catch (Throwable e) {
					throwable = e;
				} finally {
					testContextManager.afterTestMethod(target, method.getMethod(), throwable);
					testContextManager.afterTestClass();
					if (throwable != null) {
						throw throwable;
					}
				}
			}
		};
	}
}
