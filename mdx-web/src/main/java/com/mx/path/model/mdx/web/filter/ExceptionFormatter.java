package com.mx.path.model.mdx.web.filter;

import javax.servlet.http.HttpServletResponse;

import com.mx.common.accessors.PathResponseStatus;
import com.mx.common.exception.PathRequestException;
import com.mx.common.exception.PathRequestExceptionWrapper;
import com.mx.common.http.HttpStatus;
import com.mx.common.lang.Strings;

/**
 *
 */
class ExceptionFormatter {
  /**
   * @param exception the exception thrown
   * @param response the response given
   */
  public static PathRequestException convertToPathRequestException(Exception exception, HttpServletResponse response) {
    HttpStatus httpStatus = HttpStatus.resolve(response.getStatus());
    PathResponseStatus pathResponseStatus = httpStatus != null ? httpStatus.toPathResponseStatus(PathResponseStatus.INTERNAL_ERROR) : PathResponseStatus.INTERNAL_ERROR;

    PathRequestException pathRequestException = new PathRequestExceptionWrapper(exception.getMessage(), exception)
        .withStatus(pathResponseStatus);
    pathRequestException.setUserMessage("A system error has occurred. Please try again later.");

    return pathRequestException;
  }

  /**
   * @param pathRequestException the exception thrown
   */
  public static PathRequestException ensureUserMessage(PathRequestException pathRequestException) {
    if (Strings.isBlank(pathRequestException.getUserMessage())) {
      pathRequestException.setUserMessage("A system error has occurred. Please try again later.");
    }

    return pathRequestException;
  }
}
