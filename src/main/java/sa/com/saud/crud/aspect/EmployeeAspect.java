package sa.com.saud.crud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import sa.com.saud.crud.security.jwt.JwtProvider;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
//import java.util.logging.Logger;

@Aspect
@Configuration
public class EmployeeAspect {

	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	/*
	 * @Pointcut("within(@org.springframework.stereotype.Controller *)") public void
	 * controller() { }
	 * 
	 * @Pointcut("execution(* *.*(..))") protected void allMethod() { }
	 * 
	 * @Pointcut("execution(public * *(..))") protected void
	 * loggingPublicOperation() { }
	 * 
	 * @Pointcut("execution(* *.*(..))") protected void loggingAllOperation() { }
	 * 
	 * @Pointcut("within(sa.saus.crud.security..*)") private void
	 * logAnyFunctionWithinResource() { }
	 */

	/**
	 * @param joinPoint
	 */
	// @Before("controller() && allMethod() && args(..,request)")
	@Before("execution(* sa.com.saud.crud.*.*.*(..))&& args(..,request)")
	public void logBeforeMethod(JoinPoint joinPoint, HttpServletRequest request) {

		log.info("Invoking method: " + joinPoint.getSignature().getName());

		if (request != null)
			log.info("Auth Type : " + request.getAuthType());
		else
			log.info("req is null");

	}

	/**
	 * @param joinPoint
	 * @param retval
	 */
	// @AfterReturning(pointcut = "controller() && allMethod()", returning =
	// "retval")
	@AfterReturning(pointcut = "execution(* sa.com.saud.crud.*.*.*(..))", returning = "retval")
	public void logInsideMethod(JoinPoint joinPoint, Object retval) {

		log.warn("method: " + joinPoint.getSignature().getName() + " Invoked successfully");
	}

	/**
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "execution(* sa.com.saud.crud.*.*.*(..))", throwing = "e")
	public void logExceptionInMethod(JoinPoint joinPoint, Exception e) throws Throwable {
		log.error("method: " + joinPoint.getSignature().getName() + " has exception : \n ");
		e.printStackTrace();
	}

}
