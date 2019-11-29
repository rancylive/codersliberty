package codersliberty.dynamic_enum;

import codersliberty.dynamic_pojo.AbstractPojo;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;

public class EnumGenerationUtil {

	public static Class<?> generateClass() throws CannotCompileException, NotFoundException {
		ClassPool pool=ClassPool.getDefault();
		CtClass cls = pool.makeClass("com.ranjan.practice.PojoFromJson");
		cls.addField(new CtField(pool.get("String.class"), "field1", cls));
		cls.setSuperclass(pool.get("codersliberty.dynamic_pojo.AbstractPojo"));
		return cls.toClass();
	}
	
	public static void main(String[] args) {
		try {
			Class<?> cls = generateClass();
			AbstractPojo obj = (AbstractPojo) cls.newInstance();
			
			
		} catch (CannotCompileException | NotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
