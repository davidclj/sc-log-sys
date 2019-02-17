package cn.sccfc.dubbo.filter;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * dubbo日志拦截插件
 * @author cuilijin
 */
@Activate(group = Constants.CONSUMER, order = -999)
public class DubboConsumerTraceFilter implements Filter{


    private final static String TRACE_NO = "traceNo";
    private static final Logger logger = LoggerFactory.getLogger(DubboConsumerTraceFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //设置log的trace_no
        String traceNo= MDC.get(TRACE_NO);
        // if (StringUtils.isBlank(traceNo)) {
        //     traceNo =  RpcContext.getContext().getAttachment(TRACE_NO);
        // }
        if (StringUtils.isBlank(traceNo)) {
            traceNo = UUID.randomUUID().toString();
            traceNo = traceNo.replace("-", "");
        }
        MDC.put(TRACE_NO, traceNo);
        logger.info("put traceNo ({}) to logger", traceNo);

        Map<String, String> context = new HashMap<>();
        context.put(TRACE_NO, traceNo);
        RpcContext.getContext().setAttachments(context);

        try {
            FilterDesc filterReq = new FilterDesc() ;
            filterReq.setInterfaceName(invocation.getInvoker().getInterface().getName());
            filterReq.setMethodName(invocation.getMethodName()) ;
            filterReq.setArgs(invocation.getArguments());


            logger.debug("dubbo请求数据:"+JSON.toJSONString(filterReq));

            Result result = invoker.invoke(invocation);
            if (result.hasException() && invoker.getInterface() != GenericService.class){
                logger.error("dubbo执行异常",result.getException());
            }else {
                logger.info("dubbo执行成功");

                FilterDesc filterRsp = new FilterDesc() ;
                filterRsp.setMethodName(invocation.getMethodName());
                filterRsp.setInterfaceName(invocation.getInvoker().getInterface().getName());
                filterRsp.setArgs(new Object[]{result.getValue()});
                logger.debug("dubbo返回数据"+JSON.toJSONString(filterRsp));

            }
            return result ;

        }catch (RuntimeException e){
            logger.error("dubbo未知异常" + RpcContext.getContext().getRemoteHost()
                    + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
                    + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            throw e ;
        }
    }
}
