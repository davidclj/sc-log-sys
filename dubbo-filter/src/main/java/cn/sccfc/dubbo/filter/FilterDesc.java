package cn.sccfc.dubbo.filter;
/**
 * 拦截对象
 * @author cuilijin
 */
public class FilterDesc {

    //接口名
    private String interfaceName ;
    //方法名
    private String methodName ;
    //参数
    private Object[] args ;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
