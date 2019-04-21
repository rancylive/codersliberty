package codersliberty.jsonparser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import codersliberty.pojo.Address;
import codersliberty.pojo.Course;
import codersliberty.pojo.Student;

public class JsonParser {
	boolean useGetter=false;
	
	String serialize(Object object) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder builder = new StringBuilder();
		List<String> jsonFields = new ArrayList<>();
		List<Field> fields = getFields(object);
		serializeRecursive(fields, jsonFields, object);
		String serializedFields = StringUtils.join(jsonFields, ",");
		return builder.append("{").append(serializedFields).append("}").toString();
	}

	void serializeRecursive(List<Field> fields, List<String> jsonFields, Object vo)
			throws IllegalArgumentException, IllegalAccessException {
		for (int i = 0; i < fields.size(); i++) {
			StringBuilder builder = new StringBuilder();
			Field field = fields.get(i);
			if (field == null) {
				return;
			}
			String fieldName = field.getName();
			builder.append("\"" + fieldName + "\":");
			String type = field.getType().toString();
			type = StringUtils.substring(type, type.lastIndexOf(".") + 1).toLowerCase();
			Object value;
			if(useGetter) {
				 value = runGetter(field, vo);
			} else {
				field.setAccessible(true);
				value = field.get(vo);
			}
			switch (type) {
			case "string":
				if (value != null)
					jsonFields.add(builder.append("\"").append(value).append("\"").toString());
				else
					jsonFields.add(builder.append(value).toString());
				break;
			case "int":
			case "integer":
			case "double":
				jsonFields.add(builder.append(value).toString());
				break;

			default:
				if (value != null) {
					builder.append(serialize(value));
					jsonFields.add(builder.toString());
				} else {
					jsonFields.add(builder.append(value).toString());
				}

				break;
			}
		}
	}

	public List<Field> getFields(Object object) {
		List<Field> totalFields = new ArrayList<>();
		for (Class<?> cl = object.getClass(); cl != null; cl = cl.getSuperclass()) {
			Field[] fields = cl.getDeclaredFields();
			totalFields.addAll(Arrays.asList(fields));
		}
		return totalFields;
	}
	
	public List<Method> getMethods(Object object) {
		List<Method> totalMethods = new ArrayList<>();
		for (Class<?> cl = object.getClass(); cl != null; cl = cl.getSuperclass()) {
			Method[] fields = cl.getMethods();
			totalMethods.addAll(Arrays.asList(fields));
		}
		return totalMethods;
	}

	//To fetch field values using getter method
	public Object runGetter(Field field, Object o) {
		List<Method> methods = getMethods(o);
		for (Method method : methods) {
			if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
				if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
					try {
						return method.invoke(o);
					} catch (IllegalAccessException e) {
						System.out.println("Could not determine method: " + method.getName());
					} catch (InvocationTargetException e) {
						System.out.println("Could not determine method: " + method.getName());
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Address address = new Address();
		address.setCity("Ghy");
		address.setLocality("LastGate");
		address.setState("Assam");
		Student person = new Student();
		person.setName("John");
		person.setAddress(address);
		Course course = new Course();
		course.setName("English");
		course.setMarks(55.0);
		person.setCourse(course);
		person.setAge(23);
		person.setInstitute("SSVN");
		
		JsonParser jsonParser = new JsonParser();
		jsonParser.useGetter=true;
		String json = jsonParser.serialize(person);
		System.out.println(json);
	}
}
