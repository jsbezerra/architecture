package br.com.architecture.utils;

import java.lang.reflect.Field;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityUtils {

	private static final transient Logger LOGGER = LoggerFactory.getLogger(EntityUtils.class);

	private EntityUtils() {
	}

	// TODO esse método só funciona se a anotação @Id estiver no atributo e não
	// no método. Verificar se há uma forma melhor de fazer isso.
	// TODO internacionalizar
	@SuppressWarnings("rawtypes")
	public static Object getId(Object entity, Class entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				try {
					field.setAccessible(true);
					Object value = field.get(entity);
					field.setAccessible(false);
					return value;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					LOGGER.error("Não foi possível localizar o id da entidade", e);
				}
			}
		}
		return null;
	}

}
