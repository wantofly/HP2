package com.kiwi.stripes;


import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

@Intercepts({ LifecycleStage.ActionBeanResolution,
		LifecycleStage.HandlerResolution, LifecycleStage.BindingAndValidation,
		LifecycleStage.CustomValidation, LifecycleStage.EventHandling,
		LifecycleStage.ResolutionExecution })
public class NoisyInterceptor implements Interceptor {
	
	public Resolution intercept(ExecutionContext ctx) throws Exception {
		System.out.println("Before " + ctx.getLifecycleStage());
		Resolution resolution = ctx.proceed();
		System.out.println("After " + ctx.getLifecycleStage());
		return resolution;
	}
}
