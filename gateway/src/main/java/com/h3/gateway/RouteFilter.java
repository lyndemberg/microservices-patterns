package com.h3.gateway;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class RouteFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(RouteFilter.class);
    private final String MSG_INDISPONIVEL = "O serviço não está disponível";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object breakerReturn = executeCircuitBreaker(request);
        if(breakerReturn.equals(MSG_INDISPONIVEL)){
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody(breakerReturn.toString());
            ctx.setResponseStatusCode(404);
        }else{
            log.info(String.format("%s Requisição para -> %s",
                    request.getMethod(), request.getRequestURL().toString()));
        }
        return null;
    }

    @HystrixCommand(fallbackMethod = "servicoNaoDisponivel")
    public Object executeCircuitBreaker(HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        URI uri = URI.create(request.getRequestURL().toString());
        return restTemplate.getForObject(uri, Object.class);
    }

    public String servicoNaoDisponivel(){
        return MSG_INDISPONIVEL;
    }

}
