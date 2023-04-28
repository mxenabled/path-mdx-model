package com.mx.path.model.mdx.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mx.path.core.common.accessor.RequestValidationException;
import com.mx.path.model.mdx.web.EnvironmentHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationExceptionHandler {
  @Autowired
  private ErrorHandler errorHandler;

  /**
   * Determines validation failure and environment, passes exception to ErrorHandler for handling
   * @param e - MethodArgumentNotValidException thrown when @valid fails
   * @param emptyResponse - Empty HttpServletResponse due to validation failure before request is sent
   * @param failedRequest - HttpServletRequest
   * @throws IOException - thrown from ErrorHandler.handleException()
   */
  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final void handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse emptyResponse, HttpServletRequest failedRequest) throws IOException {
    String failureDetails = getFailureMessage(e);
    boolean report = isIntegrationOrProduction();
    errorHandler.handleException(
        new RequestValidationException(failureDetails, failureDetails, e).withReport(report),
        emptyResponse,
        failedRequest);
  }

  /**
   * Concatenates failure messages into single internal message
   * @param e - MethodArgumentNotValidException triggered when @valid fails on a request object
   * @return - String failureMessage
   */
  private String getFailureMessage(MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getBindingResult().getAllErrors();
    StringBuilder failureDetails = new StringBuilder();
    for (ObjectError error : errors) {
      String err = error.toString();
      err = err.substring(0, err.indexOf(";")) + "\n";
      failureDetails.append(err);
    }
    return failureDetails.toString();
  }

  /**
   * True if integration or production
   * @return - boolean isIntegrationOrProduction
   */
  private boolean isIntegrationOrProduction() {
    if (EnvironmentHelper.getEnvironment() != null) {
      String environment = EnvironmentHelper.getEnvironment().getActiveProfiles()[0];
      return environment.equals("integration") || environment.equals("production");
    }
    return false;
  }
}
