package br.com.inadimplente.utils;

import java.lang.reflect.Field;

import javax.persistence.Id;

public class EntityUtils {
	
	private EntityUtils() {}

	// TODO esse método só funciona se a anotação @Id estiver no atributo e não no método. Verificar se há uma forma melhor de fazer isso.
	@SuppressWarnings("rawtypes")
	public static Object getId(Object entity, Class entityClass) {
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)){
				try {
					field.setAccessible(true);
					Object value = field.get(entity);
					field.setAccessible(false);
					return value;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Criar log da aplicação
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
