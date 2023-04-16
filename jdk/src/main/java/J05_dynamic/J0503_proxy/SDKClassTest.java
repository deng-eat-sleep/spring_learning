package J05_dynamic.J0503_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * SDK 动态代理
 */
public class SDKClassTest {

    static interface IUserService{

        void test();
    }

    static class UserServiceImpl implements IUserService{

        public void test(){
        System.out.println("-----test-----");
    }

    }

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        IUserService userService = new UserServiceImpl();

        IUserService userServiceProxy = (IUserService) get(IUserService.class,userService);
        userServiceProxy.test();

    }

    public static Object get(Class s ,Object service){
        //创建代理类对象，userServiceProxy指向代理类对象[class $Proxy0 extends Proxy implements IUserService]
        Object userServiceProxy = Proxy.newProxyInstance(
                s.getClassLoader(),
                new Class<?>[]{s},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("enter");
                        Object result = method.invoke(service,args);
                        System.out.println("leave");
                        return result;
                    }
                }
        );
        return userServiceProxy;
    }
}
