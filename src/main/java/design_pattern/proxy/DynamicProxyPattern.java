package design_pattern.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyPattern {
    public static void main(String[] args) {
        // 委托人对象
        CaseClient caseClient = new CaseClient();
        Class clientClass = caseClient.getClass();
        // 为委托人添加代理律师的扩展功能
        LawyerHandler lawyerHandler4CaseClient = new LawyerHandler(caseClient);
        // 创建代理对象
        Litigation lawyer4CaseClient = (Litigation) Proxy.newProxyInstance(clientClass.getClassLoader(), clientClass.getInterfaces(), lawyerHandler4CaseClient);
        // 调用代理对象方法
        lawyer4CaseClient.prosecute();
        System.out.println("============== 我是笔直的分割线 ==============");
        // 小狗对象
        Dog dog = new Dog();
        Class clientClass_ = dog.getClass();
        // 为小狗添加代理律师的扩展功能
        LawyerHandler lawyerHandler4Dog = new LawyerHandler(dog);
        // 创建代理对象
        Litigation lawyer4Dog = (Litigation) Proxy.newProxyInstance(clientClass_.getClassLoader(), clientClass_.getInterfaces(), lawyerHandler4Dog);
        // 调用代理对象方法
        lawyer4Dog.prosecute();
    }
}


class Dog implements Litigation {

    @Override
    public void prosecute() {
        System.out.println("汪汪汪");
    }
}

/**
 * 代理类：律师
 */
class LawyerHandler implements InvocationHandler {

    private Object client;

    public LawyerHandler(Object client) {
        this.client = client;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 调用委托对象方法
        Object obj = method.invoke(client, args);
        // 扩展功能
        System.out.println("了解案情");
        System.out.println("收集证据");
        System.out.println("辩护");
        System.out.println("打赢官司");
        return obj;
    }
}

