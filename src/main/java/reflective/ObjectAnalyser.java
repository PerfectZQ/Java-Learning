package reflective;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyser {

    private ArrayList<Object> visited = new ArrayList();

    public String toString(Object obj) {
        if (obj == null) return null;
        if (visited.contains(obj)) return "this";
        visited.add(obj);
        Class clazz = obj.getClass();
        String result = clazz.getName();
        do {
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            result += "[";
            for (Field filed : fields) {
                if (!Modifier.isStatic(filed.getModifiers())) {
                    if (!result.endsWith("[")) result += ",";
                    String fieldName = filed.getName();
                    try {
                        // 可能会出现无限递归
                        String filedValue = toString(filed.get(obj));
                        result += "{fieldName:" + fieldName + ", filedValue:" + filedValue + "}";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            result = result + "]";
            clazz = clazz.getSuperclass();
        } while (clazz != null && clazz != Object.class);
        return result;
    }
}
