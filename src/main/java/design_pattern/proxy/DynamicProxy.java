package design_pattern.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        // 委托人对象
        CaseClient caseClient = new CaseClient();
        Class clientClass = caseClient.getClass();
        // 为委托人添加代理律师的扩展功能
        /**
         * 代理类：律师
         * 我现在不需要知道委托类的想要委托我干什么了(实现委托类接口)，我会用反射机制去动态获取
         */
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 调用委托对象方法
                Object obj = method.invoke(caseClient, args);
                // 扩展功能
                if ("litigation".equals(method.getName())) {
                    System.out.println("了解案情");
                    System.out.println("收集证据");
                    System.out.println("辩护");
                    System.out.println("打赢官司");

                } else if ("draftingContracts".equals(method.getName())) {
                    // 扩展功能
                    System.out.println("审核合同");
                    System.out.println("修订合同");
                    System.out.println("保证当事人合法利益");
                }
                return obj;
            }
        };

        // 根据传递的委托类接口，通过反射创建对应的代理对象
        SomethingCantDoByYourself proxy = (SomethingCantDoByYourself) Proxy.newProxyInstance(clientClass.getClassLoader(), clientClass.getInterfaces(), handler);
        // 调用代理对象方法
        proxy.litigation();
        System.out.println("================== 笔直的分割线 ====================");
        proxy.draftingContracts();
    }
}


