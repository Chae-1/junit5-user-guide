package junit;

import static org.junit.platform.commons.util.AnnotationUtils.*;

import java.lang.reflect.Field;
import java.util.function.Predicate;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.commons.support.ModifierSupport;

public class RandomNumberExtension implements BeforeAllCallback, TestInstancePostProcessor, ParameterResolver {

	private final java.util.Random random = new java.util.Random(System.nanoTime());

	@Override
	public void beforeAll(ExtensionContext extensionContext) throws Exception {
		Class<?> testClass = extensionContext.getRequiredTestClass();
		injectFields(testClass, null, ModifierSupport::isStatic);
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws
		ParameterResolutionException {
		return parameterContext.isAnnotated(Random.class) && isInteger(parameterContext.getParameter().getType());

	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws
		ParameterResolutionException {
		return null;
	}

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext) throws Exception {
		Class<?> testClass = extensionContext.getRequiredTestClass();
		injectFields(testClass, testInstance, ModifierSupport::isStatic);
	}

	private void injectFields(Class<?> testClass, Object testInstance,
		Predicate<Field> predicate) {
		predicate = predicate.and(field -> isInteger(field.getType()));
		findAnnotatedFields(testClass, Random.class, predicate)
			.forEach(field -> {
				try {
					field.setAccessible(true);
					field.set(testInstance, this.random.nextInt());
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			});
	}

	private static boolean isInteger(Class<?> type) {
		return type == Integer.class || type == int.class;
	}

}
