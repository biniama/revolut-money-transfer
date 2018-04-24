package com.revolut.biniam.money.transfer.exception;

import com.revolut.biniam.money.transfer.exception.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * An exception mapper provider that intercepts all 500 exceptions and
 * shows a proper and meaningful message to the end user.
 *
 * @author Biniam Asnake
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<ServiceException>{

    @Override
    public Response toResponse(ServiceException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), ex.getCode());
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(errorMessage)
                .build();
    }
}
